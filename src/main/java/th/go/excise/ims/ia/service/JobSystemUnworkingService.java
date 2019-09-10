package th.go.excise.ims.ia.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.job.JobSystemUnworking;
import th.go.excise.ims.ia.persistence.repository.IaRiskSystemUnworkingRepository;

@Service
public class JobSystemUnworkingService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);
	
	@Autowired
	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;
	
//	@Autowired
//	private SystemUnworkingService wsSystemUnworkingService;
	
	public void runBatchSystemUnworking(String year,String month) throws IOException {
		logger.info("Run Batch SystemUnworking ...");
		
//		ResponseData req = wsSystemUnworkingService.getRestFul(year, month);
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
		
		
	}
	
}
