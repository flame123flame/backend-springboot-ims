package th.go.excise.ims.ws.client.pcc.anafri0001.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE.ANAFRI0001;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.RequestData;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.ResponseData;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.util.PccServiceTestUtils;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class AnaFri0001ServiceTest {
	
	@Autowired
	private AnaFri0001Service anaFri0001Service;
	
	@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setRegistrationId("65052985684701004");
			requestData.setStartDate("20190101");
			requestData.setEndDate("20190430");
			requestData.setFormCode(ANAFRI0001.FORM_CODE_PS0307);
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = anaFri0001Service.execute(requestData);
			responseData.getFormList().forEach(System.out::println);
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void test_execute_Manual() {
		//String url = "http://webtest.excise.go.th/EDAuditServicesUAT/ana/AnaFri0001";
		String url = "http://192.168.3.136/EDAuditServicesUAT/ana/AnaFri0001";
		AnaFri0001Service anaFri0001Service = new AnaFri0001Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setRegistrationId("65052985684701004");
			requestData.setStartDate("20190101");
			requestData.setEndDate("20190430");
			requestData.setFormCode(ANAFRI0001.FORM_CODE_PS0307);
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = anaFri0001Service.execute(requestData);
			responseData.getFormList().forEach(System.out::println);
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
}
