package th.go.excise.ims.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pm.assess.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsPmAssessServiceTest {
	
	@Autowired
	private SyncWsPmAssessService syncAssessService;
	
	@Test
	public void test_syncData() {
		try {
			List<String> offCodeList = new ArrayList<>();
			offCodeList.add("010000");
			offCodeList.add("010100");
			offCodeList.add("010200");
			offCodeList.add("010300");
			offCodeList.add("010400");
			offCodeList.add("010500");
			offCodeList.add("010600");
			offCodeList.add("010700");
			offCodeList.add("010800");
			offCodeList.add("010900");
			offCodeList.add("011000");
			for (String string : offCodeList) {
				RequestData requestData = new RequestData();
				requestData.setYear("2561");
				requestData.setOfficeCode(string);
				syncAssessService.syncData(requestData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
