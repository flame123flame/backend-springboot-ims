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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class PaperSequenceServiceTest {
	
	@Autowired
	private PaperSequenceService paperSequenceService;
	
	@Test
	public void test_getBasicAnalysisNumber() {
		System.out.println(paperSequenceService.getBasicAnalysisNumber("010000", "2562"));
		System.out.println(paperSequenceService.getBasicAnalysisNumber("010000", "2562"));
		System.out.println(paperSequenceService.getBasicAnalysisNumber("010100", "2562"));
	}
	
}
