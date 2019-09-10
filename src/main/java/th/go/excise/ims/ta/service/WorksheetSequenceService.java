package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetSeqCtrlRepository;

@Service
public class WorksheetSequenceService {

	private static final Logger logger = LoggerFactory.getLogger(WorksheetSequenceService.class);
	
	private static final String RUNNING_TYPE_ANALYSIS = "A";
	private static final String RUNNING_TYPE_PLAN = "P";
	private static final String RUNNING_TYPE_OPERATE = "O";
	private static final int RUNNING_RANGE = 6;

	@Autowired
	private TaWorksheetSeqCtrlRepository taWorksheetSeqCtrlRepository;

	public String getAnalysisNumber(String officeCode, String budgetYear) {
		logger.info("getAnalysisNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder analysisNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_ANALYSIS);
		analysisNumber.append(runningNumber);
		
		logger.info("return analysisNumber={}", analysisNumber.toString());
		return analysisNumber.toString();
	}

	public String getPlanNumber(String officeCode, String budgetYear) {
		logger.info("getPlanNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder planNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_PLAN);
		planNumber.append(runningNumber);
		
		logger.info("return planNumber={}", planNumber.toString());
		return planNumber.toString();
	}
	
	public String getAuditPlanCode(String officeCode, String budgetYear) {
		logger.info("getAuditPlanCode of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder auditPlanCode = new StringBuilder(officeCode).append(budgetYear);
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_OPERATE);
		auditPlanCode.append(runningNumber);
		
		logger.info("return auditPlanCode={}", auditPlanCode.toString());
		return auditPlanCode.toString();
	}

	private String genarateRunningNumber(String officeCode, String budgetYear, String runningType) {
		String runningNumber = null;
		TaWorksheetSeqCtrl taWorksheetSeqCtrl = taWorksheetSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if (taWorksheetSeqCtrl == null) {
			runningNumber = StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0");
			taWorksheetSeqCtrl = new TaWorksheetSeqCtrl();
			taWorksheetSeqCtrl.setOfficeCode(officeCode);
			taWorksheetSeqCtrl.setBudgetYear(budgetYear);
			taWorksheetSeqCtrl.setRunningType(runningType);
			taWorksheetSeqCtrl.setRunningNumber(1);
		} else {
			taWorksheetSeqCtrl.setRunningNumber(taWorksheetSeqCtrl.getRunningNumber() + 1);
			runningNumber = StringUtils.leftPad(String.valueOf(taWorksheetSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0");
		}
		
		taWorksheetSeqCtrlRepository.save(taWorksheetSeqCtrl);
		
		return runningNumber;
	}

}
