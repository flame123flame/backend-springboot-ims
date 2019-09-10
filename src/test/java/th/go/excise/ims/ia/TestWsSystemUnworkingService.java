//package th.go.excise.ims.ia;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import th.go.excise.ims.Application;
//import th.go.excise.ims.ia.service.JobSystemUnworkingService;
//import th.go.excise.ims.ws.client.pcc.licfri6010.oxm.LicFri6010Request;
//import th.go.excise.ims.ws.client.pcc.licfri6010.service.LicFri6010Service;
//import th.go.excise.ims.ws.client.pcc.systemunworking.service.SystemUnworkingService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class TestWsSystemUnworkingService {
//	
//	private Logger logger = LoggerFactory.getLogger(TestWsSystemUnworkingService.class);
//
//	@Autowired
//	private JobSystemUnworkingService systemUnworkingService;
//	
//	@Autowired
//	private SystemUnworkingService wsSystemUnworkingService;
//	
//	@Autowired
//	LicFri6010Service licFri6010Service;
//	
//	@Test
//	public void testSystemUnworkingService() throws Exception {
//		try {
//			
//			LicFri6010Request request = new LicFri6010Request();
//			request.setOffcode("100300");
//			request.setYearMonthFrom("201801");
//			request.setYearMonthTo("201802");
//			request.setPageNo("1");
//			request.setDataPerPage("10");
//			licFri6010Service.postRestFul(request);
////			String budgetYear = "2561";
////			String month = "04";
////			
//			
////			systemUnworkingService.runBatchSystemUnworking(budgetYear,month);
//			
//			
//			
////			wsSystemUnworkingService.getRestFul(budgetYear);
//		} catch (Exception e) {
//			logger.info(" Test SystemUnworkingService : ",e);
//		}
//		
//	}
//}
