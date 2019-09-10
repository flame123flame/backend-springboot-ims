package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;
import th.go.excise.ims.ws.persistence.repository.WsRegfri4000DutyRepository;
import th.go.excise.ims.ws.persistence.repository.WsRegfri4000Repository;

@Service
public class SyncWsRegfri4000Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsRegfri4000Service.class);
	
	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private RegFri4000Service regFri4000Service;
	
	@Autowired
	private WsRegfri4000Repository wsRegfri4000Repository;
	
	@Autowired
	private WsRegfri4000DutyRepository wsRegfri4000DutyRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData Regfri4000 Start");
		long start = System.currentTimeMillis();
		
		RequestData requestData = new RequestData();
		requestData.setType("1");
		requestData.setNid("");
		requestData.setNewregId("");
		requestData.setHomeOfficeId("");
		requestData.setActive("1");
		requestData.setDataPerPage(String.valueOf(WS_DATA_SIZE));
		int indexPage = 0;
		
		List<RegMaster60> regMaster60List = null;
		WsRegfri4000 regfri4000 = null;
		List<WsRegfri4000> regfri4000List = new ArrayList<>();
		WsRegfri4000Duty regfri4000Duty = null;
		List<WsRegfri4000Duty> regfri4000DutyList = new ArrayList<>();
		LocalDate tmpRegDate = null;
		List<LocalDate> tmpRegDateList = null;
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
			if (regMaster60List != null && regMaster60List.size() > 0) {
				logger.info("Restful Post to Regfri4000 Response size: {}", regMaster60List.size());
				for (RegMaster60 regMaster60 : regMaster60List) {
					regfri4000 = new WsRegfri4000();
					regfri4000.setNewRegId(regMaster60.getNewregId());
					regfri4000.setRegId(regMaster60.getRegId());
					regfri4000.setRegStatus(regMaster60.getRegStatus());
					regfri4000.setRegStatusDesc(regMaster60.getRegStatusDesc());
					regfri4000.setRegStatusDate(LocalDate.parse(regMaster60.getStatusDate(), DateTimeFormatter.BASIC_ISO_DATE));
					regfri4000.setCusId(regMaster60.getCusId());
					regfri4000.setCusFullname(regMaster60.getCusFullname());
					regfri4000.setCusAddress(ExciseUtils.buildCusAddress(regMaster60));
					regfri4000.setCusTelno(regMaster60.getCusTelno());
					regfri4000.setCusEmail(regMaster60.getCusEmail());
					regfri4000.setCusUrl(regMaster60.getCusUrl());
					regfri4000.setFacId(regMaster60.getFacId());
					regfri4000.setFacFullname(regMaster60.getFacFullname());
					regfri4000.setFacAddress(ExciseUtils.buildFacAddress(regMaster60));
					regfri4000.setFacTelno(regMaster60.getFacTelno());
					regfri4000.setFacEmail(regMaster60.getFacEmail());
					regfri4000.setFacUrl(regMaster60.getFacUrl());
					regfri4000.setFacType(regMaster60.getNewregId().substring(13, 14));
					regfri4000.setRegCapital(NumberUtils.toBigDecimal(regMaster60.getCapital()));
					regfri4000.setOfficeCode(regMaster60.getOffcode());
					regfri4000.setActiveFlag(regMaster60.getActiveFlag());
					regfri4000.setSyncDate(LocalDateTime.now());
					regfri4000.setCreatedBy(SYSTEM_USER.BATCH);
					regfri4000.setCreatedDate(LocalDateTime.now());
					regfri4000.setUpdatedBy(SYSTEM_USER.BATCH);
					regfri4000.setUpdatedDate(LocalDateTime.now());
					if (regMaster60.getRegDutyList() != null && regMaster60.getRegDutyList().size() > 0) {
						tmpRegDateList = new ArrayList<>();
						for (RegDuty regDuty : regMaster60.getRegDutyList()) {
							tmpRegDate = LocalDate.parse(regDuty.getRegDate().substring(0, 8), DateTimeFormatter.BASIC_ISO_DATE);
							tmpRegDateList.add(tmpRegDate);
							regfri4000Duty = new WsRegfri4000Duty();
							regfri4000Duty.setNewRegId(regMaster60.getNewregId());
							regfri4000Duty.setDutyGroupId(regDuty.getGroupId());
							regfri4000Duty.setDutyGroupName(regDuty.getGroupName());
							regfri4000Duty.setRegDate(tmpRegDate);
							regfri4000Duty.setCreatedBy(SYSTEM_USER.BATCH);
							regfri4000Duty.setCreatedDate(LocalDateTime.now());
							regfri4000Duty.setUpdatedBy(SYSTEM_USER.BATCH);
							regfri4000Duty.setUpdatedDate(LocalDateTime.now());
							regfri4000DutyList.add(regfri4000Duty);
						}
						regfri4000.setRegDate(tmpRegDateList.stream().min(Comparator.comparing(LocalDate::toEpochDay)).get());
					}
					regfri4000List.add(regfri4000);
				}
			} else {
				logger.warn("WS Regfri4000 is empty ResponseData", regMaster60List.size());
			}
		} while (regMaster60List.size() == WS_DATA_SIZE);
		
		wsRegfri4000Repository.queryUpdateIsDeletedY();
		wsRegfri4000Repository.batchMerge(regfri4000List);
		logger.info("Batch Merge WS_REGFRI4000 Success");
		
		wsRegfri4000DutyRepository.queryUpdateIsDeletedY();
		wsRegfri4000DutyRepository.batchMerge(regfri4000DutyList);
		logger.info("Batch Merge WS_REGFRI4000_DUTY Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Regfri4000 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
