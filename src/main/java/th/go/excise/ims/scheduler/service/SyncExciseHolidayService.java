package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseHolidayRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryholiday.model.Holiday;
import th.go.excise.ims.ws.client.pcc.inquiryholiday.service.InquiryHolidayService;

@Service
public class SyncExciseHolidayService {
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseHolidayService.class);

	@Autowired
	private InquiryHolidayService inquiryHolidayService;
	
	@Autowired
	private ExciseHolidayRepository exciseHolidayRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryHoliday");
		
		List<Holiday> bankList = inquiryHolidayService.execute(new Object());
		
		exciseHolidayRepository.queryUpdateIsDeletedY();
		exciseHolidayRepository.batchMerge(bankList);
	}
}
