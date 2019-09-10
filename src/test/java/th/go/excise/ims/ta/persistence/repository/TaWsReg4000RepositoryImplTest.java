package th.go.excise.ims.ta.persistence.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWsReg4000RepositoryImplTest {
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Test
	public void test_findByCriteria() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setFlagPage("client");
		
		taWsReg4000Repository.countByCriteria(formVo);
	}
	
}
