package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup;
import th.go.excise.ims.preferences.persistence.repository.ExciseDutyGroupRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquirydutygroup.model.DutyGroup;
import th.go.excise.ims.ws.client.pcc.inquirydutygroup.service.InquiryDutyGroupService;

@Service
public class SyncExciseDutyGroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDutyGroupService.class);

	@Autowired
	private InquiryDutyGroupService inquiryDutyGroupService;
	
	@Autowired
	private ExciseDutyGroupRepository exciseDutyGroupRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryDutyGroup");
		
		List<DutyGroup> dutyGroupList = inquiryDutyGroupService.execute(new Object());
		
		ExciseDutyGroup exciseDutyGroup = null;
		ParamInfo paramInfo = null;
		List<ExciseDutyGroup> exciseDutyGroupList = new ArrayList<>();
		
		for (DutyGroup dutyGroup : dutyGroupList) {
			exciseDutyGroup = new ExciseDutyGroup();
			exciseDutyGroup.setDutyGroupCode(dutyGroup.getGroupId());
			exciseDutyGroup.setDutyGroupName(dutyGroup.getGroupName());
			exciseDutyGroup.setDutyGroupStatus(dutyGroup.getGroupStatus());
			exciseDutyGroup.setSupDutyGroupCode(dutyGroup.getSupGroupId());
			exciseDutyGroup.setRegStatus(dutyGroup.getRegStatus());
			exciseDutyGroup.setBeginDate(LocalDate.parse(dutyGroup.getBeginDate(), DateTimeFormatter.BASIC_ISO_DATE));
			paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.ED_DUTY_GROUP, (dutyGroup.getGroupId().substring(0, 2) + "00"));
			if (paramInfo != null) {
				exciseDutyGroup.setDutyGroupType(paramInfo.getValue2());
			}
			exciseDutyGroupList.add(exciseDutyGroup);
		}
		
		exciseDutyGroupRepository.queryUpdateIsDeletedY();
		exciseDutyGroupRepository.batchMerge(exciseDutyGroupList);
	}
	
}
