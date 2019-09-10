package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaMasCondSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSeqCtrlRepository;

@Service
public class MasterConditionSequenceService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetSequenceService.class);
	
	private static final int RUNNING_RANGE = 2;
	
	@Autowired
	private TaMasCondSeqCtrlRepository taMasCondSeqCtrlRepository;
	
	public String getConditionNumber(String officeCode, String budgetYear) {
		logger.info("getConditionNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear);
	}
	
	private String genarateRunningNumber(String officeCode, String budgetYear) {
		StringBuilder runningNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		TaMasCondSeqCtrl taMasCondSeqCtrl = taMasCondSeqCtrlRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (taMasCondSeqCtrl == null) {
			runningNumber.append(StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0"));
			taMasCondSeqCtrl = new TaMasCondSeqCtrl();
			taMasCondSeqCtrl.setOfficeCode(officeCode);
			taMasCondSeqCtrl.setBudgetYear(budgetYear);
			taMasCondSeqCtrl.setRunningNumber(1);
		} else {
			taMasCondSeqCtrl.setRunningNumber(taMasCondSeqCtrl.getRunningNumber() + 1);
			runningNumber.append(StringUtils.leftPad(String.valueOf(taMasCondSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0"));
		}
		
		taMasCondSeqCtrlRepository.save(taMasCondSeqCtrl);
		logger.info("response runningNumber : {}", runningNumber.toString());
		
		return runningNumber.toString();
	}
	
}
