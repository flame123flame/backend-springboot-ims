package th.go.excise.ims.scheduler.service;

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
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE.ANAFRI0001;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsAnafri0001ServiceTest {
	
	@Autowired
	private SyncWsAnafri0001Service syncWsAnafri0001Service;
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Test
	public void test_syncData() {
		
		List<TaWsReg4000> entityList = taWsReg4000Repository.findAll();
		RequestData requestData = null;
		String newRegId = null;
		String facType = null;
		String dateStart = "20180101";
		String dateEnd = "20190531";
		for (TaWsReg4000 wsReg4000 : entityList) {
			newRegId = wsReg4000.getNewRegId();
			if (newRegId.length() != 17) {
				continue;
			}
			facType = newRegId.substring(13, 14);
			
			requestData = new RequestData();
			requestData.setRegistrationId(newRegId);
			if ("1".equals(facType) || "3".equals(facType)) {
				requestData.setFormCode(ANAFRI0001.FORM_CODE_PS0307);
			} else {
				requestData.setFormCode(ANAFRI0001.FORM_CODE_PS0308);
			}
			requestData.setStartDate(dateStart);
			requestData.setEndDate(dateEnd);
			syncWsAnafri0001Service.syncData(requestData);
		}
		
	}
	
}
