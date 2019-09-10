package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseTitleRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquirytitle.model.Title;
import th.go.excise.ims.ws.client.pcc.inquirytitle.service.InquiryTitleService;

@Service
public class SyncExciseTitleService {

	private static final Logger logger = LoggerFactory.getLogger(SyncExciseTitleService.class);

	@Autowired
	private InquiryTitleService inquiryTitleService;
	
	@Autowired
	private ExciseTitleRepository exciseTitleRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryTitle");
		
		List<Title> titleList = inquiryTitleService.execute(new Object());
		
		exciseTitleRepository.queryUpdateIsDeletedY();
		exciseTitleRepository.batchMerge(titleList);
	}
}
