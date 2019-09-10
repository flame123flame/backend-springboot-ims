package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsIncfri8000MServiceTest {
	
	@Autowired
	private SyncWsIncfri8000MService syncWsIncfri8000MService;
	
	@Test
	public void test_syncData() throws PccRestfulException {
		LocalDate dateStart = LocalDate.of(2018, 1, 1);
		LocalDate dateEnd = LocalDate.of(2019, 3, 1);
		
		String dateType = WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME;
		List<LocalDate> dateList = LocalDateUtils.getLocalDateRange(dateStart, dateEnd);
		
		for (LocalDate localDate : dateList) {
			syncWsIncfri8000MService.syncData(dateType, localDate);
		}
	}
	
}
