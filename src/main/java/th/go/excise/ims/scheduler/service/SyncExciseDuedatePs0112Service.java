package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.preferences.persistence.entity.ExciseDuedatePs0112;
import th.go.excise.ims.preferences.persistence.repository.ExciseDuedatePs0112Repository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model.DuedatePs0112;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.service.InquiryDuedatePs0112Service;

@Service
public class SyncExciseDuedatePs0112Service {

	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDuedatePs0112Service.class);

	@Autowired
	private InquiryDuedatePs0112Service inquiryDuedatePs0112Service;

	@Autowired
	private ExciseDuedatePs0112Repository exciseDuedatePs0112Repository;

	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryDuedatePs0112");

		List<DuedatePs0112> duedatePs0112List = inquiryDuedatePs0112Service.execute(new Object());
		
		List<ExciseDuedatePs0112> exciseDuedatePs0112List = new ArrayList<>();
		ExciseDuedatePs0112 exciseDuedatePs0112 = null;
		for (DuedatePs0112 duedatePs0112 : duedatePs0112List) {
			exciseDuedatePs0112 = new ExciseDuedatePs0112();
			exciseDuedatePs0112.setYear(duedatePs0112.getYear());
			exciseDuedatePs0112.setMonth(duedatePs0112.getMonth());
			exciseDuedatePs0112.setDuedate(LocalDate.parse(duedatePs0112.getDuedate(), DateTimeFormatter.BASIC_ISO_DATE));
			exciseDuedatePs0112.setCreatedBy(SYSTEM_USER.BATCH);
			exciseDuedatePs0112.setCreatedDate(LocalDateTime.now());
			exciseDuedatePs0112.setUpdatedBy(SYSTEM_USER.BATCH);
			exciseDuedatePs0112.setUpdatedDate(LocalDateTime.now());
			exciseDuedatePs0112List.add(exciseDuedatePs0112);
		}
		
		exciseDuedatePs0112Repository.queryUpdateIsDeletedY();
		exciseDuedatePs0112Repository.batchMerge(exciseDuedatePs0112List);
	}
}
