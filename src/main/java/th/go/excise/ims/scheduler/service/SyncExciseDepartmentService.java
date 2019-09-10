package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryedoffice.model.EdOffice;
import th.go.excise.ims.ws.client.pcc.inquiryedoffice.service.InquiryEdOfficeService;

@Service
public class SyncExciseDepartmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDepartmentService.class);

	@Autowired
	private InquiryEdOfficeService inquiryEdOfficeService;
	
	@Autowired
	private ExciseDepartmentRepository exciseDepartmentRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryEdOffice");
		
		List<EdOffice> edOfficeList = inquiryEdOfficeService.execute(new Object());
		
		exciseDepartmentRepository.queryUpdateIsDeletedY();
		exciseDepartmentRepository.batchMerge(edOfficeList);
	}
}
