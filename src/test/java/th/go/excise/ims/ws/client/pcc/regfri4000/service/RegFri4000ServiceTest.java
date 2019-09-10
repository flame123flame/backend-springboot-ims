package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class RegFri4000ServiceTest {

	@Autowired
	private RegFri4000Service regFri4000Service;
	
//	@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setType("1");
			requestData.setNid("");
			requestData.setNewregId("");
			requestData.setHomeOfficeId("");
			requestData.setActive("1");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = regFri4000Service.execute(requestData);
			responseData.getRegMaster60List().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDAuditServicesUAT/reg/RegFri4000";
		RegFri4000Service regFri4000Service = new RegFri4000Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setType("1");
			requestData.setNid("");
			requestData.setNewregId("");
			requestData.setHomeOfficeId("");
			requestData.setActive("1");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = regFri4000Service.execute(requestData);
			responseData.getRegMaster60List().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void test_mock_json() throws Exception {
//		
//		String jsonFile = getJsonFile("uat_resp_regfri4000.json");
//		Gson gson = new GsonBuilder().create();
//		Reader reader = new InputStreamReader(new FileInputStream(new File(jsonFile)), "UTF-8");
//		PccResponseHeader<ResponseData> response = gson.fromJson(reader, new TypeToken<PccResponseHeader<ResponseData>>(){}.getType());
//		reader.close();
//		
//		response.getResponseData().getRegMaster60List().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
//	}
//	
//	private String getJsonFile(String fileName) {
//		return (new File(ClassLoader.getSystemResource("json/" + fileName).getPath())).getPath();
//	}
	
}
