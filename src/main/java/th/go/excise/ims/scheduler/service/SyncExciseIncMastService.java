package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseIncMastRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryincmast.model.IncomeMaster;
import th.go.excise.ims.ws.client.pcc.inquiryincmast.service.InquiryIncmastService;

@Service
public class SyncExciseIncMastService {
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseIncMastService.class);

	@Autowired
	private InquiryIncmastService inquiryIncMastService;
	
	@Autowired
	private ExciseIncMastRepository exciseIncMastRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryIncmast");
		
		List<IncomeMaster> dutyGroupList = inquiryIncMastService.execute(new Object());
		
		exciseIncMastRepository.queryUpdateIsDeletedY();
		exciseIncMastRepository.batchMerge(dutyGroupList);
	}
}
