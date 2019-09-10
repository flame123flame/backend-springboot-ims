package th.go.excise.ims.ia.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.service.JobSystemUnworkingService;

public class JobSystemUnworking implements Job {

	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			try {
				logger.info("Job SystemUnworking Working ...");
				JobSystemUnworkingService systemUnworkingJobService = (JobSystemUnworkingService) dataMap.get("jobSystemUnworkingService");
				
				String date = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
				String month = date.split("/")[0];
				String year = date.split("/")[1];
				
				logger.info("date : " + date + " month : " + month + " year : " + year);
				
				systemUnworkingJobService.runBatchSystemUnworking(year,month);
				
			}catch (Exception e) {
				logger.error("Job SystemUnworking" , e);
			}
		
	}

		
}
