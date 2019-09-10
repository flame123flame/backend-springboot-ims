package th.go.excise.ims.scheduler.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;

@Service
public class SyncTaWsReg4000Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncTaWsReg4000Service.class);
	
	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private RegFri4000Service regFri4000Service;
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData Regfri4000 to TaWsReg4000 Start");
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
		TaWsReg4000 wsReg4000 = null;
		List<TaWsReg4000> wsReg4000List = new ArrayList<>();
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
			if (regMaster60List != null && regMaster60List.size() > 0) {
				logger.info("Restful Post to Regfri4000 Response size: {}", regMaster60List.size());
				for (RegMaster60 regMaster60 : regMaster60List) {
					for (RegDuty regDuty : regMaster60.getRegDutyList()) {
						wsReg4000 = new TaWsReg4000();
						wsReg4000.setNewRegId(regMaster60.getNewregId());
						wsReg4000.setCusId(regMaster60.getCusId());
						wsReg4000.setCusFullname(regMaster60.getCusFullname());
						wsReg4000.setCusAddress(ExciseUtils.buildCusAddress(regMaster60));
						wsReg4000.setCusTelno(regMaster60.getCusTelno());
						wsReg4000.setCusEmail(regMaster60.getCusEmail());
						wsReg4000.setCusUrl(regMaster60.getCusUrl());
						wsReg4000.setFacId(regMaster60.getFacId());
						wsReg4000.setFacFullname(regMaster60.getFacFullname());
						wsReg4000.setFacAddress(ExciseUtils.buildFacAddress(regMaster60));
						wsReg4000.setFacTelno(regMaster60.getFacTelno());
						wsReg4000.setFacEmail(regMaster60.getFacEmail());
						wsReg4000.setFacUrl(regMaster60.getFacUrl());
						wsReg4000.setFacType(null); // TODO Assign value
						wsReg4000.setRegId(null); // TODO Assign value
						wsReg4000.setRegStatus(null); // TODO Assign value
						wsReg4000.setRegDate(null); // TODO Assign value
						wsReg4000.setRegCapital(null); // TODO Assign value
						wsReg4000.setOfficeCode(regMaster60.getOffcode());
						wsReg4000.setActiveFlag(regMaster60.getActiveFlag());
						wsReg4000.setDutyCode(regDuty.getGroupId());
						wsReg4000.setCreatedBy(SYSTEM_USER.BATCH);
						wsReg4000.setCreatedDate(LocalDateTime.now());
						wsReg4000.setUpdatedBy(SYSTEM_USER.BATCH);
						wsReg4000.setUpdatedDate(LocalDateTime.now());
						wsReg4000List.add(wsReg4000);
					}
				}
			} else {
				logger.warn("WS Regfri4000 is empty ResponseData", regMaster60List.size());
			}
		} while (regMaster60List.size() == WS_DATA_SIZE);
		
		taWsReg4000Repository.queryUpdateIsDeletedY();
		taWsReg4000Repository.batchMerge(wsReg4000List);
		logger.info("Batch Merge WS_REGFRI4000 Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Regfri4000 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
