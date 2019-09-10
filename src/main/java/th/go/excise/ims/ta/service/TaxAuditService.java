package th.go.excise.ims.ta.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterInfoVo;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;
import th.go.excise.ims.preferences.persistence.repository.ExciseHolidayRepository;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.AuditCalendarFormVo;
import th.go.excise.ims.ta.vo.AuditStepFormVo;
import th.go.excise.ims.ta.vo.FormDocTypeVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.TaPlanMasVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;

@Service
public class TaxAuditService {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditService.class);

	@Autowired
	private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Autowired
	private RegFri4000Service regFri4000Service;
	
	@Autowired
	private ExciseHolidayRepository exciseHolidayRepository;

	public DataTableAjax<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo) {

		if (StringUtils.isNotEmpty(formVo.getOfficeCode())) {
			if (ExciseUtils.isCentral(formVo.getOfficeCode())) {
				formVo.setOfficeCode("%");
			}else if (ExciseUtils.isSector(formVo.getOfficeCode())) {
				formVo.setOfficeCode(formVo.getOfficeCode().substring(0, 2) +"%");
			}else {
				formVo.setOfficeCode(formVo.getOfficeCode());
			}
		}else {
			formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			String whereOfficeCode = ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			formVo.setOfficeCode(whereOfficeCode);
		}

		DataTableAjax<OutsidePlanVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(taWsReg4000Repository.outsidePlan(formVo));
		dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = taWsReg4000Repository.countOutsidePlan(formVo).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);

		return dataTableAjax;
	}

	public List<ParamInfo> getAuditType(AuditCalendarCheckboxVo form) {
		List<ParamInfo> auditType = new ArrayList<>();
		auditType = ApplicationCache.getParamInfoListByGroupCode(ParameterConstants.PARAM_GROUP.TA_AUDIT_TYPE);
		return auditType;
	}

	public List<ParamInfo> getAuditStatus(AuditCalendarCheckboxVo form) {
		List<ParamInfo> auditStatus = new ArrayList<>();
		auditStatus = ApplicationCache.getParamInfoListByGroupCode(ParameterConstants.PARAM_GROUP.TA_AUDIT_STATUS);
		return auditStatus;
	}

	public List<PlanWorksheetDtlVo> getPlanWsDtl(AuditCalendarCriteriaFormVo formVo) {
		List<PlanWorksheetDtlVo> planWsDtl = new ArrayList<>();
		planWsDtl = taPlanWorksheetDtlRepository.findByCriteria(formVo);
		return planWsDtl;
	}

	// FIXME getOperatorDetails
	public WsRegfri4000FormVo getOperatorDetails(String newRegId) throws Exception {
		logger.info("getOperatorDetails newRegId={}", newRegId);
		
//		RequestData requestData = new RequestData();
//		requestData.setType("2");
//		requestData.setNid("");
//		requestData.setNewregId(newRegId);
//		requestData.setHomeOfficeId("");
//		requestData.setActive("1");
//		requestData.setPageNo("1");
//		requestData.setDataPerPage("1");
		
		WsRegfri4000FormVo formVo = null;
//		try {
//			List<RegMaster60> regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
//			formVo = new WsRegfri4000FormVo();
//			RegMaster60 regMaster60 = null;
//			if (regMaster60List != null && regMaster60List.size() > 0) {
//				regMaster60 = regMaster60List.get(0);
//				BeanUtils.copyProperties(formVo, regMaster60);
//				formVo.setNewRegId(regMaster60.getNewregId());
//				formVo.setCusFullname(regMaster60.getCusFullname());
//				formVo.setCustomerAddress(ExciseUtils.buildCusAddress(regMaster60));
//				formVo.setFacAddress(ExciseUtils.buildFacAddress(regMaster60));
//				formVo.setFactoryType(ExciseUtils.getFactoryType(formVo.getNewRegId()));
//				if (StringUtils.isNotEmpty(formVo.getFactoryType())) {
//					formVo.setFactoryTypeText(ApplicationCache.getParamInfoByCode(PARAM_GROUP.EXCISE_FACTORY_TYPE, formVo.getFactoryType()).getValue2());
//				}
//			}
//		} catch (PccRestfulException e) {
//			logger.warn("Now Found when call WS Regfri4000");
			formVo = taWsReg4000Repository.findByNewRegId(newRegId);
			if (formVo == null) {
				throw new PccRestfulException("NewRegId=" + newRegId + " Not Found");
			}
//		}
		String secDesc = ApplicationCache.getExciseDepartment(formVo.getOffcode().substring(0, 2) + "0000").getDeptShortName();
		String areaDesc = ApplicationCache.getExciseDepartment(formVo.getOffcode().substring(0, 4) + "00").getDeptShortName();
		formVo.setSecDesc(secDesc);
		formVo.setAreaDesc(areaDesc);
		return formVo;
	}
	
	public TaxAuditDetailVo getOperatorDetailsByAuditPlanCode(TaxAuditDetailFormVo formVo) {
		logger.info("getOperatorDetailsByAuditPlanCode auditPlanCode={}", formVo.getAuditPlanCode());
		
		TaPlanWorksheetDtl planDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
		
		TaxAuditDetailVo vo = new TaxAuditDetailVo();
		vo.setOfficeCode(planDtl.getOfficeCode());
		vo.setBudgetYear(planDtl.getBudgetYear());
		vo.setPlanNumber(planDtl.getPlanNumber());
		vo.setNewRegId(planDtl.getNewRegId());
		vo.setSystemType(planDtl.getSystemType());
		vo.setPlanType(planDtl.getPlanType());
		vo.setAuditStatus(planDtl.getAuditStatus());
		vo.setAuditType(planDtl.getAuditType());
		vo.setAuditTypeDesc(planDtl.getAuditType() != null ? ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_TYPE, planDtl.getAuditType()).getValue1() : "");
		vo.setAuditStartDate(planDtl.getAuditStartDate() != null ? ThaiBuddhistDate.from(planDtl.getAuditStartDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH)) : "");
		vo.setAuditEndDate(planDtl.getAuditEndDate() != null ? ThaiBuddhistDate.from(planDtl.getAuditEndDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH)) : "");
		vo.setAuditPlanCode(planDtl.getAuditPlanCode());
		vo.setAuSubdeptCode(planDtl.getAuSubdeptCode());
		vo.setAuJobResp(planDtl.getAuJobResp());
		
		try {
			vo.setWsRegfri4000Vo(getOperatorDetails(planDtl.getNewRegId()));
		} catch (Exception e) {
			logger.warn(e.getMessage());
			vo.setWsRegfri4000Vo(new WsRegfri4000FormVo());
		}
		
		return vo;
	}

	public void savePlanWsDtl(AuditStepFormVo formVo) {
		logger.info("savePlanWsDtl auditPlanCode={}", formVo.getAuditPlanCode());
		
		TaPlanWorksheetDtl planDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
		planDtl.setAuditType(formVo.getAuditType());
		planDtl.setAuditStartDate(ConvertDateUtils.parseStringToLocalDate(formVo.getAuditStartDate(), ConvertDateUtils.DD_MM_YYYY));
		planDtl.setAuditEndDate(ConvertDateUtils.parseStringToLocalDate(formVo.getAuditEndDate(), ConvertDateUtils.DD_MM_YYYY));
		
		taPlanWorksheetDtlRepository.save(planDtl);
	}
	
	public List<ParamInfo> getRegStatus() {
		logger.info("getRegStatus");
		List<ParamInfo> regStatusList = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_REG_STATUS);
		return regStatusList;
	}
	
	public List<FormDocTypeVo> getFormTsDocTypeList() {
		FormDocTypeVo vo = null;
		List<FormDocTypeVo> voList = new ArrayList<>();
		List<ParamInfo> paramInfoList = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_FORM_TS);
		for (ParamInfo paramInfo : paramInfoList) {
			vo = new FormDocTypeVo();
			vo.setCode(paramInfo.getParamCode());
			vo.setGroupCode(paramInfo.getParamGroupCode());
			vo.setValue(paramInfo.getValue1());
			vo.setDesc(paramInfo.getValue2());
			voList.add(vo);
		}
		
		return voList;
	}
	
	public List<PlanWorksheetDtlVo> getPlanWsDtlByPerson(AuditCalendarCriteriaFormVo formVo) {
		List<PlanWorksheetDtlVo> planWsDtl = new ArrayList<>();
		planWsDtl = taPlanWorksheetDtlRepository.findByPlanDtlByAssingPerson(formVo);
		return planWsDtl;
	}


	public List<ParamInfo> getCountPlanDtlByPlanStatus(String budgetYear) {
		logger.info("getRegStatus");
		String username = UserLoginUtils.getCurrentUserBean().getUsername();
		List<ParamInfo> regStatusList = ApplicationCache.getParamInfoListByGroupCode(ParameterConstants.PARAM_GROUP.TA_AUDIT_TYPE);
		List<TaPlanWorksheetDtl> listPlan = taPlanWorksheetDtlRepository.findByAuJobRespAndBudgetYear(username,budgetYear);
		List<ParamInfo> resplist = new ArrayList<>();
		for (ParamInfo param : regStatusList) {
			ParameterInfoVo info = new ParameterInfoVo();
			List<TaPlanWorksheetDtl> compareList = listPlan.stream().filter(item -> param.getParamCode().equals(item.getPlanType())).collect(Collectors.toList());
			info.setParamCode(param.getParamCode());
			info.setValue1(param.getValue1());
			info.setValue6(Integer.toString(compareList.size()));
			resplist.add(info);
		}

		return resplist;
	}
	
	public List<PlanWorksheetDtlVo> getCountPlanDtlAreaByOfficeCode(String budgetYear){
		
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(offCode)) {
			offCode ="%";
		}else if (ExciseUtils.isSector(offCode)) {
			offCode = offCode.substring(0, 2) +"%";
		}
		List<PlanWorksheetDtlVo> listAreaCount = taPlanWorksheetDtlRepository.countPlanDtlAndAreaByOfficeCode(offCode,budgetYear);
		
		List<ExciseDepartment> sectorList =  ApplicationCache.getExciseSectorList();
		List<ExciseDepartment> areaList = null;
		PlanWorksheetDtlVo planVo = null;
		List<PlanWorksheetDtlVo> listResp = new ArrayList<>();
		
		if (ExciseUtils.isCentral(UserLoginUtils.getCurrentUserBean().getOfficeCode())) {
			for (ExciseDepartment sector : sectorList) {
				areaList = new ArrayList<>();
				areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
				for (ExciseDepartment area : areaList) {
					planVo = new PlanWorksheetDtlVo();
					String preCode = area.getOfficeCode().substring(0, 4);
					List<PlanWorksheetDtlVo> compareList = listAreaCount.stream().filter(item -> preCode.equals(item.getAreaCode().substring(0, 4))).collect(Collectors.toList());
					
					int sumArea = 0;
					for (PlanWorksheetDtlVo compare : compareList) {
						sumArea += Integer.parseInt(compare.getTitle());
					}
					planVo.setAreaCode(area.getOfficeCode());
					planVo.setAreaDesc(area.getDeptShortName());
					planVo.setTitle(String.valueOf(sumArea));
					listResp.add(planVo);
				}
			}
		}else if (ExciseUtils.isSector(UserLoginUtils.getCurrentUserBean().getOfficeCode())) { 
			areaList = ApplicationCache.getExciseAreaList(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			for (ExciseDepartment area : areaList) {
				planVo = new PlanWorksheetDtlVo();
				String preCode = area.getOfficeCode().substring(0, 4);
				List<PlanWorksheetDtlVo> compareList = listAreaCount.stream().filter(item -> preCode.equals(item.getAreaCode().substring(0, 4))).collect(Collectors.toList());
				
				int sumArea = 0;
				for (PlanWorksheetDtlVo compare : compareList) {
					sumArea += Integer.parseInt(compare.getTitle());
				}
				planVo.setAreaCode(area.getOfficeCode());
				planVo.setAreaDesc(area.getDeptShortName());
				planVo.setTitle(String.valueOf(sumArea));
				listResp.add(planVo);
			}

		}else {
			planVo = new PlanWorksheetDtlVo();
			planVo.setAreaCode(listAreaCount.get(0).getAreaCode());
			planVo.setAreaDesc(listAreaCount.get(0).getAreaDesc());
			planVo.setTitle(listAreaCount.get(0).getTitle());
			listResp.add(planVo);
		}

		
		return listResp;
		
	}
	public List<TaPlanMasVo> countAuditMonth(String budgetYear){
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(offCode)) {
			offCode ="%";
		}else if (ExciseUtils.isSector(offCode)) {
			offCode = offCode.substring(0, 2) +"%";
		}
		
		return taPlanWorksheetDtlRepository.countPlanDtlMonthByOfficeCode(offCode, budgetYear);
	}
	
	public List<ParamInfo> countAuditStatus(String budgetYear){
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(offCode)) {
			offCode ="%";
		}else if (ExciseUtils.isSector(offCode)) {
			offCode = offCode.substring(0, 2) +"%";
		}
		
		List<ParamInfo> listParam = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_AUDIT_STATUS);
		List<ParamInfo> resplist = new ArrayList<>();
		List<PlanWorksheetDatatableVo>  listPlan = taPlanWorksheetDtlRepository.countPlanDtlStatusByOfficeCode(offCode,budgetYear);
		for (ParamInfo param : listParam) {
			ParameterInfoVo info = new ParameterInfoVo();
			List<PlanWorksheetDatatableVo> compareList = listPlan.stream().filter(item -> param.getParamCode().equals(item.getAuditStatus())).collect(Collectors.toList());
			info.setParamCode(param.getParamCode());
			info.setValue1(param.getValue1());
			info.setValue6(Integer.toString(compareList.size()));
			resplist.add(info);
		}
		
		return resplist;
	}
	
	public List<AuditCalendarFormVo> getPlanHoliday(AuditCalendarFormVo form){
		List<AuditCalendarFormVo> resp = new ArrayList<>();
		AuditCalendarFormVo calendar = null;
		
		
//		LocalDate start = LocalDate.of(2016, 1, 1);
//		LocalDate end =  LocalDate.of(2018, 1, 1);
		
		LocalDate startDate = ConvertDateUtils.parseStringToLocalDate(ConvertDateUtils.formatDateToString(form.getStart(),ConvertDateUtils.DD_MM_YYYY),
				ProjectConstant.SHORT_DATE_FORMAT);
		LocalDate endDate = ConvertDateUtils.parseStringToLocalDate(ConvertDateUtils.formatDateToString(form.getEnd(),ConvertDateUtils.DD_MM_YYYY),
				ProjectConstant.SHORT_DATE_FORMAT);
		List<ExciseHoliday> holiday = exciseHolidayRepository.findByDateRange(startDate, endDate);
		
		for (ExciseHoliday exciseHoliday : holiday) {
			calendar = new AuditCalendarFormVo();
			calendar.setStart(Date.valueOf(exciseHoliday.getHolidayDate()));
			calendar.setEnd(Date.valueOf(exciseHoliday.getHolidayDate()));
			calendar.setRendering("background");
			calendar.setAllDay(true);
			resp.add(calendar);
		}
		
		return resp;
	}
	
}
