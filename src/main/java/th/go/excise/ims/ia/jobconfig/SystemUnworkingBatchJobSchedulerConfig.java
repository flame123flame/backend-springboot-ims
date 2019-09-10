package th.go.excise.ims.ia.jobconfig;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.excise.ims.ia.job.JobSystemUnworking;
import th.go.excise.ims.ia.service.JobSystemUnworkingService;

//@Configuration
//@ConditionalOnProperty(name="system.unworking.job.cronExpressions" , havingValue="" ,matchIfMissing=false)
public class SystemUnworkingBatchJobSchedulerConfig {

		
		private static final Logger log = LoggerFactory.getLogger(SystemUnworkingBatchJobSchedulerConfig.class);

		
		@Autowired
		private JobSystemUnworkingService jobSystemUnworkingService;

		@Value("${system.unworking.job.cronExpressions}")
		private String cronExpressions;
		
		@PostConstruct
		public void init(){
			log.info("############################### SystemUnworkingBatchJobSchedulerConfig ###############################");
			log.info("###  ");
			log.info("###  ");
			log.info("###  cronEx : " + cronExpressions);
			log.info("###  ");
			log.info("###  ");
			log.info("###############################");
		}
		
		@Bean
		public JobDetail systemUnworkingJobDetail() {
			JobDataMap newJobDataMap = new JobDataMap();
			newJobDataMap.put("jobSystemUnworkingService", jobSystemUnworkingService);
			JobDetail job = JobBuilder.newJob(JobSystemUnworking.class)
				      .withIdentity("systemUnworkingJobDetail", "group1") // name "myJob", group "group1"
				      .usingJobData(newJobDataMap )
				      .build();
			return job;
		}
		
		@Bean("systemUnworkingCronTrigger")
		public Set<CronTrigger> systemUnworkingCronTrigger() {
			Set<CronTrigger> cornsets = new HashSet<>();
			String[] corns = StringUtils.split(cronExpressions,",");
			int i=0;
			for (String corn : corns) {
				CronTrigger trigger = TriggerBuilder.newTrigger()
					    .withIdentity("systemUnworkingCronTrigger" + i++, "group1")
					    .withSchedule(CronScheduleBuilder.cronSchedule(corn))
					    .build();
				cornsets.add(trigger);
			}

			return cornsets;
		}
		
		@Bean
		public SchedulerFactory  systemUnworkingSchedulerFactory() throws SchedulerException {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.scheduleJob(systemUnworkingJobDetail(), systemUnworkingCronTrigger(),true);
			sched.start();
			return sf;
		}
		
		@PreDestroy
		public void destroy() throws SchedulerException {
			log.info("systemUnworkingBatchJobSchedulerConfig.. shutdown");
			systemUnworkingSchedulerFactory().getScheduler().shutdown();
		}
	}
