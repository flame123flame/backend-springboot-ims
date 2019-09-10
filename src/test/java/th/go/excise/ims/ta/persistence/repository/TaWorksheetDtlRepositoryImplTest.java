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
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxDraftVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWorksheetDtlRepositoryImplTest {
	
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;
	
	@Test
	public void test_findByAnalysisNumber() {
		List<TaxDraftVo> taxDraftVoList = taWorksheetDtlRepository.findByAnalysisNumber("001401-2562-000001");
		System.out.println(ToStringBuilder.reflectionToString(taxDraftVoList.get(0), ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
