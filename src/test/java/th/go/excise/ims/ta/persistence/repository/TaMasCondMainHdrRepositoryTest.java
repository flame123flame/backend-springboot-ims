package th.go.excise.ims.ta.persistence.repository;

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
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaMasCondMainHdrRepositoryTest {
	
	@Autowired
	private TaMasCondMainHdrRepository taMasCondHdrRepository;
	
//	@Test
	public void test_insert() {
		TaMasCondMainHdr masCondHdr = new TaMasCondMainHdr();
		masCondHdr.setBudgetYear("2570");
		masCondHdr.setMonthNum(24);
		
		taMasCondHdrRepository.save(masCondHdr);
	}
	
	@Test
	public void test_findByBudgetYear() {
		TaMasCondMainHdr masCondHdr = taMasCondHdrRepository.findByCondNumber("000000-2562-01");
		System.out.println(ToStringBuilder.reflectionToString(masCondHdr, ToStringStyle.JSON_STYLE));
	}
	
}
