package th.go.excise.ims.ta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisServiceTest {
	
	@Autowired
	private BasicAnalysisService basicAnalysisService;
	
	@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setAuditPlanCode("0014012562000005");
		formVo.setYearNum("1");
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		formVo.setPaperBaNumber("001401-2562-000001");
		formVo.setCommentText1("Comment 1");
		formVo.setCommentText2("Comment 2");
		formVo.setCommentText3("Comment 3");
		formVo.setCommentText4("Comment 4");
		formVo.setCommentText5("Comment 5");
		formVo.setCommentText6("Comment 6");
		formVo.setCommentText7("Comment 7");
		formVo.setCommentText8("Comment 8");
		
		basicAnalysisService.save(formVo);
	}
	
}
