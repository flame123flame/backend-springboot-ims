package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseHospitalRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryhospital.model.Hospital;
import th.go.excise.ims.ws.client.pcc.inquiryhospital.service.InquiryHospitalService;
@Service
public class SyncExciseHospitalServive {
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseHospitalServive.class);

	@Autowired
	private InquiryHospitalService inquiryHospitalService;
	
	@Autowired
	private ExciseHospitalRepository exciseHospitalRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryHospital");
		
		List<Hospital> hospitalList = inquiryHospitalService.execute(new Object());
		
		exciseHospitalRepository.queryUpdateIsDeletedY();
		exciseHospitalRepository.batchMerge(hospitalList);
	}
}
