package th.go.excise.ims.ws.client.pcc.incfri8020.service;

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
import th.go.excise.ims.scheduler.service.SyncWsIncfri8020IncService;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.util.PccServiceTestUtils;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.RequestData;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class IncFri8020ServiceTest {
	
	@Autowired
	private IncFri8020Service incFri8020Service;
	@Autowired
	private SyncWsIncfri8020IncService  syncWsIncfri8020IncService;
	
	//@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setOfficeCode("000300");
			requestData.setYearMonthFrom("201802");
			requestData.setYearMonthTo("201802");
			requestData.setDateType("Income");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = incFri8020Service.execute(requestData);
			responseData.getIncomeList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDAuditServicesUAT/inc/IncFri8020";
		IncFri8020Service incFri8020Service = new IncFri8020Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setOfficeCode("000300");
			requestData.setYearMonthFrom("201802");
			requestData.setYearMonthTo("201802");
			requestData.setDateType("Income");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = incFri8020Service.execute(requestData);
			responseData.getIncomeList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void syncDataIncFri8020() {
		String url = "http://webtest.excise.go.th/EDAuditServicesUAT/inc/IncFri8020";
		IncFri8020Service incFri8020Service = new IncFri8020Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		RequestData requestData = new RequestData();
		requestData.setOfficeCode("000300");
		requestData.setYearMonthFrom("201802");
		requestData.setYearMonthTo("201808");
		requestData.setDateType("Income");
		syncWsIncfri8020IncService.syncData(requestData);
	}
	
}
