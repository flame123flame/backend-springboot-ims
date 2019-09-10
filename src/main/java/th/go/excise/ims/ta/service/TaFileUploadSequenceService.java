package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaFileUploadSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaFileUploadSeqCtrlRepository;

@Service
public class TaFileUploadSequenceService {
	
private static final Logger logger = LoggerFactory.getLogger(TaFileUploadSequenceService.class);
	
	private static final String RUNNING_TYPE_UPLOAD = "UP";
	private static final int RUNNING_RANGE = 6;

	@Autowired
	private TaFileUploadSeqCtrlRepository taFileUploadSeqCtrlRepository;

	public String getUploadNumber(String officeCode, String budgetYear) {
		logger.info("getUploadNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		
		StringBuilder uploadNumber = new StringBuilder(RUNNING_TYPE_UPLOAD + officeCode + budgetYear);
		String runningNumber = genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_UPLOAD);
		uploadNumber.append(runningNumber);
		
		logger.info("return getUploadNumber={}", uploadNumber.toString());
		return uploadNumber.toString();
	}

	private String genarateRunningNumber(String officeCode, String budgetYear, String runningType) {
		String runningNumber = null;
		TaFileUploadSeqCtrl taFileUploadSeqCtrl = taFileUploadSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if (taFileUploadSeqCtrl == null) {
			runningNumber = StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0");
			taFileUploadSeqCtrl = new TaFileUploadSeqCtrl();
			taFileUploadSeqCtrl.setOfficeCode(officeCode);
			taFileUploadSeqCtrl.setBudgetYear(budgetYear);
			taFileUploadSeqCtrl.setRunningType(runningType);
			taFileUploadSeqCtrl.setRunningNumber(1);
		} else {
			taFileUploadSeqCtrl.setRunningNumber(taFileUploadSeqCtrl.getRunningNumber() + 1);
			runningNumber = StringUtils.leftPad(String.valueOf(taFileUploadSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0");
		}
		
		taFileUploadSeqCtrlRepository.save(taFileUploadSeqCtrl);
		
		return runningNumber;
	}
	
}
