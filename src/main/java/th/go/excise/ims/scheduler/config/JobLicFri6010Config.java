package th.go.excise.ims.scheduler.config;

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

import th.go.excise.ims.scheduler.execute.ExecuteIaWs6010Service;
import th.go.excise.ims.scheduler.service.SyncWsLicfri6010Service;

@Configuration
@ConditionalOnProperty(name="license.list.job.cronExpressions" , havingValue="" ,matchIfMissing=false)
public class JobLicFri6010Config {

		
		private static final Logger log = LoggerFactory.getLogger(JobLicFri6010Config.class);

		
		@Autowired
		private SyncWsLicfri6010Service syncWsLicfri6010Service;

		@Value("${license.list.job.cronExpressions}")
		private String cronExpressions;
		
		@PostConstruct
		public void init(){
			log.info("############################### LicenseListServiceBatchJobSchedulerConfig ###############################");
			log.info("###  ");
			log.info("###  ");
			log.info("###  cronEx : " + cronExpressions);
			log.info("###  ");
			log.info("###  ");
			log.info("###############################");
		}
		
		@Bean
		public JobDetail IaWs6010JobDetail() {
			JobDataMap newJobDataMap = new JobDataMap();
			newJobDataMap.put("syncWsLicfri6010Service", syncWsLicfri6010Service);
			JobDetail job = JobBuilder.newJob(ExecuteIaWs6010Service.class)
				      .withIdentity("syncWsLicfri6010Service", "group1") 
				      .usingJobData(newJobDataMap )
				      .build();
			return job;
		}
		
		@Bean("IaWs6010CronTrigger")
		public Set<CronTrigger> IaWs6010CronTrigger() {
			Set<CronTrigger> cornsets = new HashSet<>();
			String[] corns = StringUtils.split(cronExpressions,",");
			int i=0;
			for (String corn : corns) {
				CronTrigger trigger = TriggerBuilder.newTrigger()
					    .withIdentity("IaWs6010CronTrigger" + i++, "group1")
					    .withSchedule(CronScheduleBuilder.cronSchedule(corn))
					    .build();
				cornsets.add(trigger);
			}

			return cornsets;
		}
		
		@Bean
		public SchedulerFactory  IaWs6010SchedulerFactory() throws SchedulerException {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.scheduleJob(IaWs6010JobDetail(), IaWs6010CronTrigger(),true);
			sched.start();
			return sf;
		}
		
		@PreDestroy
		public void destroy() throws SchedulerException {
			log.info("IaWs6010BatchJobSchedulerConfig.. shutdown");
			IaWs6010SchedulerFactory().getScheduler().shutdown();
		}
	}
