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
import th.go.excise.ims.ws.vo.WsRegfri4000Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WsRegfri4000RepositoryTest {
	
	@Autowired
	private WsRegfri4000Repository wsRegfri4000Repository;
	
	@Test
	public void test_findByCriteria() {
		String officeCode = "001401";
		
		WsRegfri4000Vo regfri4000Vo = new WsRegfri4000Vo();
		regfri4000Vo.setOfficeCode(officeCode);
		
		Long count = wsRegfri4000Repository.countByCriteria(regfri4000Vo);
		regfri4000Vo.setStart(0);
		regfri4000Vo.setLength(count.intValue());
		
		List<WsRegfri4000Vo> regfri4000VoList = wsRegfri4000Repository.findByCriteria(regfri4000Vo, true);
		regfri4000VoList.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
	}
	
}
