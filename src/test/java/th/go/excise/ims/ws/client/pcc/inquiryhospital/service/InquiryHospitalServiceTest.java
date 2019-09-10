package th.go.excise.ims.ws.client.pcc.inquiryhospital.service;

import java.util.List;

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
import th.go.excise.ims.ws.client.pcc.inquiryhospital.model.Hospital;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryHospitalServiceTest {

	@Autowired
	private InquiryHospitalService inquiryHospitalService;

	//@Test
	public void test_execute() {
		try {
			Object requestData = new Object();
			List<Hospital> hospitalList = inquiryHospitalService.execute(requestData);
			hospitalList.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDRestServicesUAT/rdb/InquiryHospital";
		InquiryHospitalService inquiryHospitalService = new InquiryHospitalService(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		try {
			Object requestData = new Object();
			//requestData.setHospCode("");
			List<Hospital> hospitalList = inquiryHospitalService.execute(requestData);
			hospitalList.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
}
