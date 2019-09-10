package th.go.excise.ims.ta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WorksheetSequenceServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(WorksheetSequenceServiceTest.class);

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	@Test
	public void getAnalysisNumber() {
		logger.info(worksheetSequenceService.getAnalysisNumber("010001", "2561"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2561"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010001", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2564"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010001", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2564"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("020000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("020001", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAnalysisNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getPlanNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getPlanNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getPlanNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getPlanNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getPlanNumber("010000", "2562"));
		logger.info(worksheetSequenceService.getAuditPlanCode("010000", "2562"));
		logger.info(worksheetSequenceService.getAuditPlanCode("010000", "2562"));
		logger.info(worksheetSequenceService.getAuditPlanCode("010000", "2562"));
	}

}
