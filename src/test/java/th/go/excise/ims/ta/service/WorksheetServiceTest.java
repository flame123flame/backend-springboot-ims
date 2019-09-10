package th.go.excise.ims.ta.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WorksheetServiceTest {
	
	@Autowired
	private WorksheetService worksheetService;
	
//	@Test
	public void test_saveWorksheet() throws Exception {
		String draftNumber = "000000-2562-000004";
		String budgetYear = "2562";
		worksheetService.saveWorksheet(draftNumber, budgetYear);
	}
	
	@Test
	public void test_findAllAnalysisNumber() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		
		 List<TaWorksheetHdr> bh = worksheetService.findAllAnalysisNumber(formVo);
		
	}
	
}
