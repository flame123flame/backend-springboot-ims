package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseOfficeAddrRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.model.OffCodeAddress;
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.service.InquiryOffcodeAddressService;

@Service
public class SyncExciseOfficeAddrService {

	private static final Logger logger = LoggerFactory.getLogger(SyncExciseOfficeAddrService.class);

	@Autowired
	private InquiryOffcodeAddressService inquiryOffcodeAddressService;
	
	@Autowired
	private ExciseOfficeAddrRepository exciseOfficeAddrRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryOffcodeAddress");
		
		List<OffCodeAddress> offCodeAddressList = inquiryOffcodeAddressService.execute(new Object());
		
		exciseOfficeAddrRepository.queryUpdateIsDeletedY();
		exciseOfficeAddrRepository.batchMerge(offCodeAddressList);
	}
}
