package th.go.excise.ims.scheduler.execute;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.scheduler.service.SyncWsLicfri6010Service;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;

public class ExecuteIaWs6010Service implements Job {
	
	private Logger logger = LoggerFactory.getLogger(ExecuteIaWs6010Service.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("execute");
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		try {
			SyncWsLicfri6010Service syncWsLicfri6010Service = (SyncWsLicfri6010Service) dataMap.get("syncWsLicfri6010Service");
			
			List<String> offCodeList = new ArrayList<>();
			List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
			List<ExciseDepartment> areaList = null;
			List<ExciseDepartment> branchList = null;
			for (ExciseDepartment sector : sectorList) {
				offCodeList.add(sector.getOfficeCode());
				areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
				for (ExciseDepartment area : areaList) {
					offCodeList.add(area.getOfficeCode());
					branchList = ApplicationCache.getExciseBranchList(area.getOfficeCode());
					if (branchList != null && branchList.size() > 0) {
						for (ExciseDepartment branch : branchList) {
							offCodeList.add(branch.getOfficeCode());
						}
					}
				}
			}
			
			String ymFrom = "201501";
			String ymTo = "201905";
			
			RequestData requestData = null;
			for (String offCode : offCodeList) {
				try {
					requestData = new RequestData();
					requestData.setOffcode(offCode);
					requestData.setYearMonthFrom(ymFrom);
					requestData.setYearMonthTo(ymTo);
					syncWsLicfri6010Service.syncData(requestData);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			requestData = new RequestData();
			requestData.setYearMonthFrom(ymFrom);
			requestData.setYearMonthTo(ymTo);
			syncWsLicfri6010Service.syncWs6010ToIaWs6010(requestData);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		System.out.println("*********************");
		System.out.println("*  ===============  *");
		System.out.println("*                   *");
		System.out.println("*  Job complete!!!  *");
		System.out.println("*                   *");
		System.out.println("*  ===============  *");
		System.out.println("*********************");

	}

}
