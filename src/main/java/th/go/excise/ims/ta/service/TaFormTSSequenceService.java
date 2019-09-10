package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaFormTsSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaFormTsSeqCtrlRepository;

@Service
public class TaFormTSSequenceService {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTSSequenceService.class);
	
	private static final int RUNNING_RANGE = 6;

	@Autowired
	private TaFormTsSeqCtrlRepository taFormTsSeqCtrlRepository;

	public String getFormTsNumber(String officeCode, String budgetYear) {
		logger.info("getFormTsNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear);
	}

	private String genarateRunningNumber(String officeCode, String budgetYear) {
		StringBuilder runningNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		TaFormTsSeqCtrl taFormTsSeqCtrl = taFormTsSeqCtrlRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (taFormTsSeqCtrl == null) {
			runningNumber.append(StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0"));
			taFormTsSeqCtrl = new TaFormTsSeqCtrl();
			taFormTsSeqCtrl.setOfficeCode(officeCode);
			taFormTsSeqCtrl.setBudgetYear(budgetYear);
			taFormTsSeqCtrl.setRunningNumber(1);
		} else {
			taFormTsSeqCtrl.setRunningNumber(taFormTsSeqCtrl.getRunningNumber() + 1);
			runningNumber.append(StringUtils.leftPad(String.valueOf(taFormTsSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0"));
		}

		taFormTsSeqCtrlRepository.save(taFormTsSeqCtrl);
		logger.info("response runningNumber : {}", runningNumber.toString());

		return runningNumber.toString();
	}

}
