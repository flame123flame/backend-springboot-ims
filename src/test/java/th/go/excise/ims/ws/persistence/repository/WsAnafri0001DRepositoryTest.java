package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WsAnafri0001DRepositoryTest {
	
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	
	@Test
	public void test_findProductListByBasicAnalysisFormVo() {
		String newRegId = "01055551727011001";
		String dutyGroupId = "0201";
		String dateStart = "20190101";
		String dateEnd = "20190531";
		
		List<WsAnafri0001Vo> voList = wsAnafri0001DRepository.findProductList(newRegId, dutyGroupId, dateStart, dateEnd);
		voList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE));
		});
	}
	
}
