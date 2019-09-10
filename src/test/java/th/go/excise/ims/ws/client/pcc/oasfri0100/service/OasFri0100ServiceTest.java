package th.go.excise.ims.ws.client.pcc.oasfri0100.service;

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
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.util.PccServiceTestUtils;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.RequestData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseData2;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class OasFri0100ServiceTest {

	@Autowired
	private OasFri0100Service oasFri0100Service;

	//@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setRegId("09920020600391004");
			requestData.setTaxYear("2561");
			requestData.setTaxMonth("10");
			ResponseData2 responseData2 = oasFri0100Service.execute(requestData);
			System.out.println(responseData2);
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDRestServicesUAT/oas/OASFRI0100";
		OasFri0100Service oasFri0100Service = new OasFri0100Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());

		try {
			RequestData requestData = new RequestData();
			requestData.setRegId("09920020600391004");
			requestData.setTaxYear("2561");
			requestData.setTaxMonth("10");
			ResponseData2 responseData2 = oasFri0100Service.execute(requestData);
			System.out.println(responseData2);
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}

}
