package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendDetailVo;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Service
public class PlanWorkSheetSendService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanWorkSheetSendService.class);

	@Autowired
	private TaPlanWorksheetSendRepository planWorksheetSendRepository;
	
	@Autowired
	private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;
	
	public List<PlanWorkSheetSendVo> getPlanWorkSheetSend(PlanWorksheetVo formVo) {
		String userLoginOfficeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("getPlanWorkSheetSend officeCode={}, budgetYear={}", userLoginOfficeCode, formVo.getBudgetYear());
		
		List<PlanWorkSheetSendVo> planWorkSheetSendVoList = new ArrayList<>();
		List<ExciseDepartment> sectorList = null;
		List<ExciseDepartment> areaList = null;

		List<TaPlanWorksheetSend> planWorksheetSendList = planWorksheetSendRepository.findByOfficeCodeAndBudgetYearAll(ExciseUtils.whereInLocalOfficeCode(userLoginOfficeCode), formVo.getBudgetYear());
		
		if (ExciseUtils.isCentral(userLoginOfficeCode)) {
			// Central
//			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR1, planWorksheetSendList));
//			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR2, planWorksheetSendList));

			String offCodeCenter = EXCISE_OFFICE_CODE.TA_CENTRAL;
			String findOfficeCode = "";
			ExciseDepartment department = null;
			for (int i = 1; i < 99 ; i++) {
				if (i < 10 ) {
					findOfficeCode = offCodeCenter.substring(0, 5)+ String.valueOf(i);
				}else {
					findOfficeCode = offCodeCenter.substring(0, 4)+ String.valueOf(i);
				}
				department = ApplicationCache.getExciseDepartment(findOfficeCode);
				if (department != null ) {
					for (int j = 0; j < planWorksheetSendList.size(); j++) {
						if (planWorksheetSendList.get(j).getOfficeCode().equals(findOfficeCode)) {
							planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(findOfficeCode, planWorksheetSendList));
							break;
						}
					}
				}else {
					break;
				}
			}
			
			
			sectorList = ApplicationCache.getExciseSectorList();
			for (ExciseDepartment sector : sectorList) {
				if (ExciseUtils.isCentral(sector.getOfficeCode())) {
					continue;
				}
				planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(sector.getOfficeCode(), planWorksheetSendList));
				areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
				for (ExciseDepartment area : areaList) {
					planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(area.getOfficeCode(), planWorksheetSendList));
				}
			}
		} else if (ExciseUtils.isSector(userLoginOfficeCode)) {
			// Sector
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(userLoginOfficeCode, planWorksheetSendList));
			areaList = ApplicationCache.getExciseAreaList(userLoginOfficeCode);
			for (ExciseDepartment area : areaList) {
				planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(area.getOfficeCode(), planWorksheetSendList));
			}
		} else {
			// Area
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(userLoginOfficeCode, planWorksheetSendList));
		}

		return planWorkSheetSendVoList;
	}
	
	private PlanWorkSheetSendVo buildPlanWorkSheetSendVo(String officeCode, List<TaPlanWorksheetSend> planWorksheetSendList) {
		PlanWorkSheetSendVo planWorkSheetSendVo = new PlanWorkSheetSendVo();
		if (ExciseUtils.isCentral(officeCode) || ExciseUtils.isSector(officeCode)) {
			planWorkSheetSendVo.setSector(ApplicationCache.getExciseDepartment(officeCode));
			planWorkSheetSendVo.setArea(new ExciseDepartmentVo());
		} else if (ExciseUtils.isArea(officeCode)) {
			planWorkSheetSendVo.setSector(new ExciseDepartmentVo());
			planWorkSheetSendVo.setArea(ApplicationCache.getExciseDepartment(officeCode));
		}
		planWorkSheetSendVo.setPlanWorksheetSend(getPlanWorksheetSendByOfficeCode(officeCode, planWorksheetSendList));
		planWorkSheetSendVo.setTotalFacNum(getTotalFacNum(planWorkSheetSendVo.getPlanWorksheetSend()));
		
		return planWorkSheetSendVo;
	}
	
	private PlanWorkSheetSendDetailVo getPlanWorksheetSendByOfficeCode(String officeCode, List<TaPlanWorksheetSend> planWorksheetSendList) {
		PlanWorkSheetSendDetailVo planWorksheetSend = null;
		
		for (TaPlanWorksheetSend taPlanWorksheetSend : planWorksheetSendList) {
			if (officeCode.equals(taPlanWorksheetSend.getOfficeCode())) {
				planWorksheetSend = new PlanWorkSheetSendDetailVo();
				planWorksheetSend.setOfficeCode(taPlanWorksheetSend.getOfficeCode());
				planWorksheetSend.setSendDate(ConvertDateUtils.formatLocalDateToString(taPlanWorksheetSend.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				planWorksheetSend.setSubmitDate(ConvertDateUtils.formatLocalDateToString(taPlanWorksheetSend.getSubmitDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				planWorksheetSend.setFacInNum(taPlanWorksheetSend.getFacInNum());
				planWorksheetSend.setFacOutNum(taPlanWorksheetSend.getFacOutNum());
				planWorksheetSend.setFacRsNum(taPlanWorksheetSend.getFacRsNum());
				break;
			}
		}
		
		if (planWorksheetSend == null) {
			planWorksheetSend = new PlanWorkSheetSendDetailVo();
			planWorksheetSend.setOfficeCode(officeCode);
		}
		
		return planWorksheetSend;
	}
	
	private Integer getTotalFacNum(PlanWorkSheetSendDetailVo planWorksheetSend) {
		return ObjectUtils.defaultIfNull(planWorksheetSend.getFacInNum(), 0) + ObjectUtils.defaultIfNull(planWorksheetSend.getFacOutNum(), 0);
	}
	
	public List<PlanWorksheetSendTableVo> getPlanWorkSheetSendToArea(PlanWorksheetVo formVo) {
		
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(officeCode)) {
			String offcode = officeCode.substring(0,4) +"%";
			formVo.setOfficeCode(offcode);
		}else {
			String offcode = officeCode.substring(0, 2) +"%"+officeCode.substring(4,6);
			formVo.setOfficeCode(offcode);
		}

		
		return planWorksheetDtlRepository.findPlanWorksheetByDtl(formVo);
		
	}
	
}
