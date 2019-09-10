package th.go.excise.ims.scheduler.execute;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.go.excise.ims.ia.job.JobSystemUnworking;
import th.go.excise.ims.scheduler.service.SyncWsPmSystemUnworkingService;

public class ExecuteIaWsPmSystemUnworkingService implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			try {
				logger.info("Job SystemUnworking Working ...");
				SyncWsPmSystemUnworkingService systemUnworkingJobService = (SyncWsPmSystemUnworkingService) dataMap.get("syncWsPmSystemUnworkingService");
				
//				String date = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
//				String month = date.split("/")[0];
//				String year = date.split("/")[1];
//				
//				logger.info("date : " + date + " month : " + month + " year : " + year);
				
//				RequestData request = new RequestData();
//				request.setMonth(month);
//				request.setYear(year);
				systemUnworkingJobService.syncData();
				
			}catch (Exception e) {
				logger.error("Job SystemUnworking" , e);
			}
		
	}


}
