package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsIncfri8020IncServiceTest {

	@Autowired 
	private SyncWsIncfri8020IncService syncWsIncfri8020IncService;
	
	@Test
	public void load() {
		RequestData requestData = null;
		
		LocalDate dateStart = LocalDate.of(2018, 1, 1);
		LocalDate dateEnd = LocalDate.of(2019, 3, 1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM", Locale.US);
		
//			System.out.println(formatter.format(localDate));
			requestData = new RequestData();
			requestData.setDateType(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME);
			requestData.setYearMonthFrom(formatter.format(dateStart));
			requestData.setYearMonthTo(formatter.format(dateEnd));
			requestData.setOfficeCode("00300");
			syncWsIncfri8020IncService.syncData(requestData);
	}
	
	
	
}
