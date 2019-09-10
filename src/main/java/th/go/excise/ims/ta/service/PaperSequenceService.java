package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaPaperSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaPaperSeqCtrlRepository;

@Service
public class PaperSequenceService {

	private static final Logger logger = LoggerFactory.getLogger(PaperSequenceService.class);
	
	private static final String RUNNING_TYPE_BASIC_ANALYSIS = "BA";
	private static final String RUNNING_TYPE_PAPER_PRODUCT = "PP";
	private static final String RUNNING_TYPE_PAPER_SERVICE = "PS";
	private static final int RUNNING_RANGE = 6;

	@Autowired
	private TaPaperSeqCtrlRepository taPaperSeqCtrlRepository;

	public String getBasicAnalysisNumber(String officeCode, String budgetYear) {
		logger.info("getBasicAnalysisNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder baNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_BASIC_ANALYSIS);
		baNumber.append(runningNumber);
		
		logger.info("return baNumber={}", baNumber.toString());
		return baNumber.toString();
	}

	public String getPaperProductNumber(String officeCode, String budgetYear) {
		logger.info("getPaperProductNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder ppNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_PAPER_PRODUCT);
		ppNumber.append(runningNumber);
		
		logger.info("return ppNumber={}", ppNumber.toString());
		return ppNumber.toString();
	}
	
	public String getPaperServiceNumber(String officeCode, String budgetYear) {
		logger.info("getPaperServiceNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder psNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_PAPER_SERVICE);
		psNumber.append(runningNumber);
		
		logger.info("return psNumber={}", psNumber.toString());
		return psNumber.toString();
	}

	private String genarateRunningNumber(String officeCode, String budgetYear, String runningType) {
		String runningNumber = null;
		TaPaperSeqCtrl taPaperSeqCtrl = taPaperSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if (taPaperSeqCtrl == null) {
			runningNumber = StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0");
			taPaperSeqCtrl = new TaPaperSeqCtrl();
			taPaperSeqCtrl.setOfficeCode(officeCode);
			taPaperSeqCtrl.setBudgetYear(budgetYear);
			taPaperSeqCtrl.setRunningType(runningType);
			taPaperSeqCtrl.setRunningNumber(1);
		} else {
			taPaperSeqCtrl.setRunningNumber(taPaperSeqCtrl.getRunningNumber() + 1);
			runningNumber = StringUtils.leftPad(String.valueOf(taPaperSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0");
		}
		
		taPaperSeqCtrlRepository.save(taPaperSeqCtrl);
		
		return runningNumber;
	}

}
