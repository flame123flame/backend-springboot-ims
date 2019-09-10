package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseBankRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquirybank.model.Bank;
import th.go.excise.ims.ws.client.pcc.inquirybank.service.InquiryBankService;

@Service
public class SyncExciseBankService {
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDutyGroupService.class);

	@Autowired
	private InquiryBankService inquiryBankService;
	
	@Autowired
	private ExciseBankRepository exciseBankRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryBank");
		
		List<Bank> bankList = inquiryBankService.execute(new Object());
		
		exciseBankRepository.queryUpdateIsDeletedY();
		exciseBankRepository.batchMerge(bankList);
	}
}
