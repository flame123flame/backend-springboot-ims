package th.co.baiwa.buckwaframework.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class LocalDateUtilsTest {
	
	@Test
	public void test_getLocalDateRangeWithoutHolodays() {
		LocalDate dateStart = LocalDate.of(2018, 10, 1);
		LocalDate dateEnd = LocalDate.of(2018, 10, 31);
		List<LocalDate> localDateList = LocalDateUtils.getLocalDateListWithoutHolodays(dateStart, dateEnd);
		localDateList.forEach(d -> {
			System.out.println(d.format(DateTimeFormatter.BASIC_ISO_DATE));
		});
	}
	
}
