package th.go.excise.ims.ws.client.pm.systemunworking.service;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pm.systemunworking.model.RequestData;
import th.go.excise.ims.ws.client.pm.systemunworking.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class PmSystemUnworkingServiceTest {
	
	@Autowired
	private PmSystemUnworkingService pmSystemUnworkingService;
	
//	@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setYear("2560");
			requestData.setMonth("10");
			ResponseData responseData = pmSystemUnworkingService.execute(requestData);
			responseData.getData().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_execute_Manual() {
		String url = "http://192.168.48.36:8080/oper-report-uat/rest/getJsonSystem";
		PmSystemUnworkingService pmSystemUnworkingService = new PmSystemUnworkingService(url, new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setYear("2561");
			requestData.setMonth("10");
			ResponseData responseData = pmSystemUnworkingService.execute(requestData);
			responseData.getData().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
