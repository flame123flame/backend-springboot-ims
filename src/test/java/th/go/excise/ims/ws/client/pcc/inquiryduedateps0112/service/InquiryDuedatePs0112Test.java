package th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model.DuedatePs0112;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.service.InquiryDuedatePs0112Service;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryDuedatePs0112Test {

	@Autowired
	private InquiryDuedatePs0112Service inquiryDuedatePs0112Service;

	//@Test
	public void test_execute() {
		try {
			Object requestData = new Object();
			List<DuedatePs0112> duedatePs0112List = inquiryDuedatePs0112Service.execute(requestData);
			duedatePs0112List.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDRestServicesUAT/rdb/InquiryDuedatePs0112";
		InquiryDuedatePs0112Service inquiryDuedatePs0112Service = new InquiryDuedatePs0112Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());

		try {
			Object requestData = new Object();
			List<DuedatePs0112> duedatePs0112List = inquiryDuedatePs0112Service.execute(requestData);
			duedatePs0112List.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
}
