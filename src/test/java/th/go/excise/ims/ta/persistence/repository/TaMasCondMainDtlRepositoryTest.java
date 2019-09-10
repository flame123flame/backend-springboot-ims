package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaMasCondMainDtlRepositoryTest {
	
	@Autowired
	private TaMasCondMainDtlRepository taMasCondDtlTaxRepository;
	
	@Test
	public void test_findByBudgetYear() {
		List<TaMasCondMainDtl> masCondDtlTaxList = taMasCondDtlTaxRepository.findByBudgetYearAndCondNumberAndCondTypeAndOfficeCode("2562", "000000-2562-01", TA_MAS_COND_MAIN_TYPE.TAX, UserLoginUtils.getCurrentUserBean().getOfficeCode());
		for (TaMasCondMainDtl masCondDtlTax : masCondDtlTaxList) {
			System.out.println(ToStringBuilder.reflectionToString(masCondDtlTax, ToStringStyle.JSON_STYLE));
		}
	}
	
}
