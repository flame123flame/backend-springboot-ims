package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
import th.go.excise.ims.ws.client.pcc.incfri8000.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsIncfri8000ServiceTest {
	
	@Autowired
	private SyncWsIncfri8000Service syncWsIncfri8000Service;
	
	@Test
	public void test_syncData() throws PccRestfulException {
		RequestData requestData = null;
		
		LocalDate dateStart = LocalDate.of(2018, 1, 1);
		LocalDate dateEnd = LocalDate.of(2019, 3, 1);
		
		List<LocalDate> dateList = LocalDateUtils.getLocalDateRange(dateStart, dateEnd);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM", Locale.US);
		
		for (LocalDate localDate : dateList) {
//			System.out.println(formatter.format(localDate));
			requestData = new RequestData();
			requestData.setDateType(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME);
			requestData.setYearMonthFrom(formatter.format(localDate));
			requestData.setYearMonthTo(formatter.format(localDate));
			syncWsIncfri8000Service.syncData(requestData);
		}
	}
	
}
