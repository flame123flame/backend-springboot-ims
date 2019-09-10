package th.go.excise.ims.ws.persistence.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WsIncfri8000MRepositoryTest {
	
	@Autowired
	private WsIncfri8000MRepository ssIncfri8000MRepository;
	
	@Test
	public void test_findByMonthRange() {
		String ymStart = "201801";
		String ymEnd = "201812";
		// key = newRegId + dutyGroupId
		List<String> keyList = new ArrayList<>();
		
		Map<String, Map<String, BigDecimal>> newRegIdMap = ssIncfri8000MRepository.findByMonthRange(keyList, ymStart, ymEnd);
		newRegIdMap.forEach((k,v) -> {
			System.out.println("newRegId=" + k);
			for (Entry<String, BigDecimal> entry : v.entrySet()) {
				System.out.println("yearMonth=" + entry.getKey() + ", taxAmount=" + entry.getValue());
			}
		});
	}
	
}
