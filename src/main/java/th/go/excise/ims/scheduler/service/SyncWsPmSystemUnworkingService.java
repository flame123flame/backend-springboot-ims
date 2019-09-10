package th.go.excise.ims.scheduler.service;

import java.io.IOException;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ws.client.pm.systemunworking.model.RequestData;
import th.go.excise.ims.ws.client.pm.systemunworking.model.ResponseData;
import th.go.excise.ims.ws.client.pm.systemunworking.service.PmSystemUnworkingService;

@Service
public class SyncWsPmSystemUnworkingService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsPmSystemUnworkingService.class);
	
//	@Autowired
//	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;
	
	@Autowired
	private PmSystemUnworkingService pmSystemUnworkingService;
	
	public void syncData() throws IOException {
		logger.info("syncData SystemUnworking Start");
		long start = System.currentTimeMillis();
		
		ThaiBuddhistDate thaiBuddhistDate = ThaiBuddhistDate.now();
		String month = String.valueOf(thaiBuddhistDate.get(ChronoField.MONTH_OF_YEAR));
		String year = String.valueOf(thaiBuddhistDate.get(ChronoField.YEAR));
		
		RequestData requestData = new RequestData();
		requestData.setMonth(month);
		requestData.setYear(year);
		
		ResponseData responseData = pmSystemUnworkingService.execute(requestData);
//		IaRiskSystemUnworking entity = new IaRiskSystemUnworking();
//		if(req!=null&&req.getData().size()>0) {
//			for (DataList element : req.getData()) {
//				entity = new IaRiskSystemUnworking();
//				
//				entity.setYear(year);
//				entity.setMonth(month);
//				entity.setStatus(req.getStatus());
//				
//				entity.setSystemCode(element.getSystemCode());
//				entity.setSystemName(element.getSystemName());
//				entity.setCountAll(element.getCountAll());
//				entity.setCountNormal(element.getCountNormal());
//				entity.setCountError(element.getCountError());
//				entity.setStartDate(element.getStartDate());
//				entity.setEndDate(element.getEndDate());
//				
//				
//				iaRiskSystemUnworkingRepository.save(entity);
//			}
//		}
		
		long end = System.currentTimeMillis();
		logger.info("syncData SystemUnworking Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
