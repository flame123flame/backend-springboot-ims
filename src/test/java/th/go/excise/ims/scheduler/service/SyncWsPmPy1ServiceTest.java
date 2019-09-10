package th.go.excise.ims.scheduler.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pm.py1.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsPmPy1ServiceTest {

	@Autowired
	private SyncWsPmPy1Service syncWsPmPy1Service;

	@Test
	public void test_syncData() throws IOException  {
		String[] oCodeList = { "010000", "010100", "010200", "010300", "010400", "010500",  "010600", "010700", "010800", "010900", "011000" };
//		String[] oCodeList = { "010600" };

		for (String oCode : oCodeList) {
			RequestData requestData = new RequestData();
			requestData.setOfficeCode(oCode);
			requestData.setYear("2561");

//			try {
				syncWsPmPy1Service.syncData(requestData);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}

}
