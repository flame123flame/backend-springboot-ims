package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ConditionMessageVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class MasterConditionServiceTest {
	
	@Autowired
	private MasterConditionMainService masterConditionService;
	
	@Test
	public void test() {
		ConditionMessageVo conditionMessageVo = masterConditionService.conditionMessage();
		System.out.println(ToStringBuilder.reflectionToString(conditionMessageVo, ToStringStyle.JSON_STYLE));
	}
	
}
