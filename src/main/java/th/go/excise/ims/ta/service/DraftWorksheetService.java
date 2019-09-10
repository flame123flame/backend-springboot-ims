package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants.TAX_COMPARE_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubRisk;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubRiskRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHisRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubRiskRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Service
public class DraftWorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(DraftWorksheetService.class);

	private static final String NO_TAX_AMOUNT = "-";

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	@Autowired
	private TaPlanWorksheetHisRepository taPlanWorksheetHisRepository;

	@Autowired
	private TaMasCondMainHdrRepository taMasCondMainHdrRepository;
	@Autowired
	private TaMasCondMainDtlRepository taMasCondMainDtlRepository;
	@Autowired
	private TaMasCondSubCapitalRepository taMasCondSubCapitalRepository;
	@Autowired
	private TaMasCondSubRiskRepository taMasCondSubRiskRepository;
	@Autowired
	private TaMasCondSubNoAuditRepository taMasCondSubNoAuditRepository;

	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;
	@Autowired
	private TaWorksheetCondSubCapitalRepository taWorksheetCondSubCapitalRepository;
	@Autowired
	private TaWorksheetCondSubRiskRepository taWorksheetCondSubRiskRepository;
	@Autowired
	private TaWorksheetCondSubNoAuditRepository taWorksheetCondSubNoAuditRepository;

	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;

	public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
		TaxOperatorVo vo = new TaxOperatorVo();
		try {
			List<TaxOperatorDetailVo> taxOperatorDetailVoList = prepareTaxOperatorDetailVoList(formVo);
			vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(taxOperatorDetailVoList, formVo));
			vo.setCount(taWsReg4000Repository.countByCriteria(formVo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;
	}

	public List<TaxOperatorDetailVo> prepareTaxOperatorDetailVoList(TaxOperatorFormVo formVo) {
		logger.info("prepareTaxOperatorDetailVoList startDate={}, endDate={}, dateRange={}", formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());
		long start = System.currentTimeMillis();
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (StringUtils.isBlank(formVo.getOfficeCode())) {
			formVo.setOfficeCode(officeCode);
		}
		
		String budgetYear = formVo.getBudgetYear();
		WorksheetDateRangeVo dateRangeVo  = new WorksheetDateRangeVo();
		if (!StringUtils.isNotBlank(formVo.getSkipCond())) {
			String taxCompareType = TaxAuditUtils.getTaxCompareType(formVo.getDateRange());
			dateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), taxCompareType);
		} else {
			dateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), TAX_COMPARE_TYPE.HALF);
		}
		List<LocalDate> subLocalDateG1List = dateRangeVo.getSubLocalDateG1List();
		List<LocalDate> subLocalDateG2List = dateRangeVo.getSubLocalDateG2List();
		List<String> monthList = new ArrayList<>();
		for (int i = 0; i < subLocalDateG1List.size(); i++) {
			monthList.add("'" + ConvertDateUtils.formatLocalDateToString(subLocalDateG1List.get(i), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN) + "' AS taxAmtG1M" + (i + 1));
		}
		for (int i = 0; i < subLocalDateG2List.size(); i++) {
			monthList.add("'" + ConvertDateUtils.formatLocalDateToString(subLocalDateG2List.get(i), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN) + "' AS taxAmtG2M" + (i + 1));
		}
		Map<String, String> auditPlanMap = new HashMap<>();
		int lastYear1 = 0;
		int lastYear2 = 0;
		int lastYear3 = 0;
		if (StringUtils.isNotBlank(budgetYear)) {
			List<String> budgetYearList = new ArrayList<>();
			lastYear1 = Integer.valueOf(budgetYear) - 1;
			lastYear2 = Integer.valueOf(budgetYear) - 2;
			lastYear3 = Integer.valueOf(budgetYear) - 3;
			budgetYearList.add(String.valueOf(lastYear1));
			budgetYearList.add(String.valueOf(lastYear2));
			budgetYearList.add(String.valueOf(lastYear3));
			auditPlanMap = taPlanWorksheetHisRepository.findAuditPlanCodeByOfficeCodeAndBudgetYearList(officeCode, budgetYearList);
		}
		
		Map<String, String> maxYearMap = taPlanWorksheetHisRepository.findMaxTaxAuditYear();
		
		//==> Check TAX, NET
		String incomeTaxType = null;
		if (StringUtils.isNotBlank(formVo.getIncomeType())) {
			incomeTaxType = formVo.getIncomeType();
		} else {
			ParamInfo taxType = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.INCOME_TYPE);
			if (taxType != null) {
				incomeTaxType = taxType.getValue1();
			} else {
				incomeTaxType = TA_CONFIG.INCOME_TYPE_TAX;
			}
		}
		
		formVo.setYearMonthList(monthList);
		List<TaxOperatorDetailVo> detailVoList = taWsReg4000Repository.findByCriteriaPivotDatatable(formVo, auditPlanMap, maxYearMap, incomeTaxType);
		long end = System.currentTimeMillis();
		logger.info("Process prepareTaxOperatorDetailVoList Success, using {} seconds", (((float) (end - start) / 1000F)));
		
		return detailVoList;
	}


	@Transactional(rollbackOn = Exception.class)
	public void saveDraftWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		if(StringUtils.isBlank(formVo.getBudgetYear())) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
			formVo.setBudgetYear(ExciseUtils.getCurrentBudgetYear());
		}else {
			budgetYear = formVo.getBudgetYear();
		}
		String analysisNumber = worksheetSequenceService.getAnalysisNumber(officeCode, budgetYear);
		logger.info("saveDraftWorksheet officeCode={}, budgetYear={}, condNumber={}, analysisNumber={}", officeCode, budgetYear, formVo.getCondNumber(), analysisNumber);

		
		String dateStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String dateEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		// ==> Save WorksheetMainCondHdr
		TaMasCondMainHdr masCondMainHdr = taMasCondMainHdrRepository.findByCondNumber(formVo.getCondNumber());
		TaWorksheetCondMainHdr conMainHdr = new TaWorksheetCondMainHdr();
		conMainHdr.setAnalysisNumber(analysisNumber);
		conMainHdr.setCondGroupDesc(masCondMainHdr.getCondGroupDesc());
		conMainHdr.setMonthNum(masCondMainHdr.getMonthNum());
		conMainHdr.setYearMonthStart(dateStart);
		conMainHdr.setYearMonthEnd(dateEnd);
		conMainHdr.setCondGroupNum(String.valueOf(masCondMainHdr.getCondGroupNum()));
		conMainHdr.setNewFacFlag(masCondMainHdr.getNewFacFlag());
		conMainHdr.setCompType(masCondMainHdr.getCompType());
		conMainHdr.setRegDateStart(masCondMainHdr.getRegDateStart());
		conMainHdr.setRegDateEnd(masCondMainHdr.getRegDateEnd());
		conMainHdr.setCompMonthNum(masCondMainHdr.getCompMonthNum());
		taWorksheetCondMainHdrRepository.save(conMainHdr);

		// ==> Save WorksheetMainCondDtl
		List<TaMasCondMainDtl> masCondMainDtlList = taMasCondMainDtlRepository.findByCondNumber(formVo.getCondNumber());
		List<TaWorksheetCondMainDtl> condMainDtlList = new ArrayList<>();
		TaWorksheetCondMainDtl condMainDtl = null;
		for (TaMasCondMainDtl masCondMainDtl : masCondMainDtlList) {
			condMainDtl = new TaWorksheetCondMainDtl();
			condMainDtl.setAnalysisNumber(analysisNumber);
			condMainDtl.setCondGroup(masCondMainDtl.getCondGroup());
			condMainDtl.setTaxFreqType(masCondMainDtl.getTaxFreqType());
			condMainDtl.setTaxMonthStart(masCondMainDtl.getTaxMonthStart());
			condMainDtl.setTaxMonthEnd(masCondMainDtl.getTaxMonthEnd());
			condMainDtl.setRangeTypeStart(masCondMainDtl.getRangeTypeStart());
			condMainDtl.setRangeStart(masCondMainDtl.getRangeStart());
			condMainDtl.setRangeTypeEnd(masCondMainDtl.getRangeTypeEnd());
			condMainDtl.setRangeEnd(masCondMainDtl.getRangeEnd());
			condMainDtl.setRiskLevel(masCondMainDtl.getRiskLevel());
			condMainDtl.setCondType(masCondMainDtl.getCondType());
			condMainDtl.setCondDtlDesc(masCondMainDtl.getCondDtlDesc());
			condMainDtlList.add(condMainDtl);
		}
		taWorksheetCondMainDtlRepository.saveAll(condMainDtlList);

		// ==> Save WorksheetCondSubCapital
		if (StringUtils.isNotBlank(formVo.getCondSub1())) {
			List<TaMasCondSubCapital> masCondSubCapitalList = taMasCondSubCapitalRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
			for (TaMasCondSubCapital masCondSubCapital : masCondSubCapitalList) {
				TaWorksheetCondSubCapital worksheetCondSubCapital = new TaWorksheetCondSubCapital();
				worksheetCondSubCapital.setAnalysisNumber(analysisNumber);
				worksheetCondSubCapital.setDutyCode(masCondSubCapital.getDutyCode());
				worksheetCondSubCapital.setHugeCapitalAmount(masCondSubCapital.getHugeCapitalAmount());
				worksheetCondSubCapital.setLargeCapitalAmount(masCondSubCapital.getLargeCapitalAmount());
				worksheetCondSubCapital.setMediumCapitalAmount(masCondSubCapital.getMediumCapitalAmount());
				worksheetCondSubCapital.setSmallCapitalAmount(masCondSubCapital.getSmallCapitalAmount());
				taWorksheetCondSubCapitalRepository.save(worksheetCondSubCapital);
			}
		}

		// ==> Save WorksheetCondSubRisk
		if (StringUtils.isNotBlank(formVo.getCondSub2())) {
			List<TaMasCondSubRisk> masCondSubRiskList = taMasCondSubRiskRepository.findByBudgetYearAndOfficeCode(budgetYear, officeCode);
			TaWorksheetCondSubRisk worksheetCondSubRisk = null;
			for (TaMasCondSubRisk masCondSubRisk : masCondSubRiskList) {
				worksheetCondSubRisk = new TaWorksheetCondSubRisk();
				worksheetCondSubRisk.setAnalysisNumber(analysisNumber);
				worksheetCondSubRisk.setDutyCode(masCondSubRisk.getDutyCode());
				worksheetCondSubRisk.setRiskLevel(masCondSubRisk.getRiskLevel());
				taWorksheetCondSubRiskRepository.save(worksheetCondSubRisk);
			}
		}

		// ==> Save WorksheetCondSubNoAudit
		//if (StringUtils.isNotBlank(formVo.getCondSub3())) {
			TaMasCondSubNoAudit masCondSubNoAudit = taMasCondSubNoAuditRepository.findByBudgetYearAndOfficeCode(budgetYear, officeCode);
			if (masCondSubNoAudit != null) {
				TaWorksheetCondSubNoAudit worksheetCondSubNoAudit = new TaWorksheetCondSubNoAudit();
				worksheetCondSubNoAudit.setAnalysisNumber(analysisNumber);
				worksheetCondSubNoAudit.setNoTaxAuditYearNum(masCondSubNoAudit.getNoTaxAuditYearNum());
				taWorksheetCondSubNoAuditRepository.save(worksheetCondSubNoAudit);
			}
		//}

		// ==> Save WorksheetHdr
		TaWorksheetHdr worksheetHdr = new TaWorksheetHdr();
		worksheetHdr.setOfficeCode(officeCode);
		worksheetHdr.setAnalysisNumber(analysisNumber);
		worksheetHdr.setBudgetYear(budgetYear);
		worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.DRAFT);
		if (StringUtils.isNotBlank(formVo.getCondSub1())) {
			worksheetHdr.setCondSubCapitalFlag(CommonConstants.FLAG.Y_FLAG);
		}
		if (StringUtils.isNotBlank(formVo.getCondSub2())) {
			worksheetHdr.setCondSubRiskFlag(CommonConstants.FLAG.Y_FLAG);
		}
		if (StringUtils.isNotBlank(formVo.getCondSub3())) {
			worksheetHdr.setCondSubNoAuditFlag(CommonConstants.FLAG.Y_FLAG);
		}
		taWorksheetHdrRepository.save(worksheetHdr);

		// ==> Save WorksheetDtl
		List<TaxOperatorDetailVo> detailVoList = prepareTaxOperatorDetailVoList(formVo);
		List<TaWorksheetDtl> worksheetfDtlList = new ArrayList<>();
		TaWorksheetDtl worksheetDtl = new TaWorksheetDtl();

		for (TaxOperatorDetailVo detailVo : detailVoList) {
			worksheetDtl = new TaWorksheetDtl();
			worksheetDtl.setAnalysisNumber(analysisNumber);
			worksheetDtl.setNewRegId(detailVo.getNewRegId());
			worksheetDtl.setRegId(detailVo.getOldRegId());
			worksheetDtl.setDutyGroupId(detailVo.getDutyCode());
			worksheetDtl.setDutyGroupName(detailVo.getDutyName());

			worksheetDtl.setSumTaxAmtG1(NO_TAX_AMOUNT.equals(detailVo.getSumTaxAmtG1()) ? null : detailVo.getSumTaxAmtG1());
			worksheetDtl.setSumTaxAmtG2(NO_TAX_AMOUNT.equals(detailVo.getSumTaxAmtG2()) ? null : detailVo.getSumTaxAmtG2());
			worksheetDtl.setTaxAmtChnPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtChnPnt()) ? null : detailVo.getTaxAmtChnPnt());
			worksheetDtl.setTaxMonthNo(detailVo.getTaxMonthNo());

			worksheetDtl.setTaxAuditLast1(detailVo.getTaxAuditLast1());
			worksheetDtl.setTaxAuditLast2(detailVo.getTaxAuditLast2());
			worksheetDtl.setTaxAuditLast3(detailVo.getTaxAuditLast3());

			worksheetDtl.setTaxAmtG1M1(detailVo.getTaxAmtG1M1());
			worksheetDtl.setTaxAmtG1M2(detailVo.getTaxAmtG1M2());
			worksheetDtl.setTaxAmtG1M3(detailVo.getTaxAmtG1M3());
			worksheetDtl.setTaxAmtG1M4(detailVo.getTaxAmtG1M4());
			worksheetDtl.setTaxAmtG1M5(detailVo.getTaxAmtG1M5());
			worksheetDtl.setTaxAmtG1M6(detailVo.getTaxAmtG1M6());
			worksheetDtl.setTaxAmtG1M7(detailVo.getTaxAmtG1M7());
			worksheetDtl.setTaxAmtG1M8(detailVo.getTaxAmtG1M8());
			worksheetDtl.setTaxAmtG1M9(detailVo.getTaxAmtG1M9());
			worksheetDtl.setTaxAmtG1M10(detailVo.getTaxAmtG1M10());
			worksheetDtl.setTaxAmtG1M11(detailVo.getTaxAmtG1M11());
			worksheetDtl.setTaxAmtG1M12(detailVo.getTaxAmtG1M12());
			worksheetDtl.setTaxAmtG1M13(detailVo.getTaxAmtG1M13());
			worksheetDtl.setTaxAmtG1M14(detailVo.getTaxAmtG1M14());
			worksheetDtl.setTaxAmtG1M15(detailVo.getTaxAmtG1M15());
			worksheetDtl.setTaxAmtG1M16(detailVo.getTaxAmtG1M16());
			worksheetDtl.setTaxAmtG1M17(detailVo.getTaxAmtG1M17());
			worksheetDtl.setTaxAmtG1M18(detailVo.getTaxAmtG1M18());

			worksheetDtl.setTaxAmtG2M1(detailVo.getTaxAmtG2M1());
			worksheetDtl.setTaxAmtG2M2(detailVo.getTaxAmtG2M2());
			worksheetDtl.setTaxAmtG2M3(detailVo.getTaxAmtG2M3());
			worksheetDtl.setTaxAmtG2M4(detailVo.getTaxAmtG2M4());
			worksheetDtl.setTaxAmtG2M5(detailVo.getTaxAmtG2M5());
			worksheetDtl.setTaxAmtG2M6(detailVo.getTaxAmtG2M6());
			worksheetDtl.setTaxAmtG2M7(detailVo.getTaxAmtG2M7());
			worksheetDtl.setTaxAmtG2M8(detailVo.getTaxAmtG2M8());
			worksheetDtl.setTaxAmtG2M9(detailVo.getTaxAmtG2M9());
			worksheetDtl.setTaxAmtG2M10(detailVo.getTaxAmtG2M10());
			worksheetDtl.setTaxAmtG2M11(detailVo.getTaxAmtG2M11());
			worksheetDtl.setTaxAmtG2M12(detailVo.getTaxAmtG2M12());
			worksheetDtl.setTaxAmtG2M13(detailVo.getTaxAmtG2M13());
			worksheetDtl.setTaxAmtG2M14(detailVo.getTaxAmtG2M14());
			worksheetDtl.setTaxAmtG2M15(detailVo.getTaxAmtG2M15());
			worksheetDtl.setTaxAmtG2M16(detailVo.getTaxAmtG2M16());
			worksheetDtl.setTaxAmtG2M17(detailVo.getTaxAmtG2M17());
			worksheetDtl.setTaxAmtG2M18(detailVo.getTaxAmtG2M18());

			worksheetDtl.setTaxAmtSd(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtSd()) ? null : detailVo.getTaxAmtSd());
			worksheetDtl.setTaxAmtMean(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMean()) ? null : detailVo.getTaxAmtMean());
			worksheetDtl.setTaxAmtMaxPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMaxPnt()) ? null : detailVo.getTaxAmtMaxPnt());
			worksheetDtl.setTaxAmtMinPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMinPnt()) ? null : detailVo.getTaxAmtMinPnt());

			worksheetDtl.setCreatedBy(UserLoginUtils.getCurrentUsername());
			worksheetDtl.setCreatedDate(LocalDateTime.now());

			worksheetDtl.setLastAuditYear(detailVo.getLastAuditYear());
			worksheetDtl.setIncMultiDutyFlag(detailVo.getIncMultiDutyFlag());
			worksheetDtl.setBudgetYear(budgetYear);
			worksheetfDtlList.add(worksheetDtl);
		}

		taWorksheetDtlRepository.batchInsert(worksheetfDtlList);
	}

	public List<String> findAllAnalysisNumber(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		if (StringUtils.isEmpty(budgetYear)) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		logger.info("findAllDraftNumber officeCode={}, budgetYear={}", officeCode, budgetYear);

		return taWorksheetHdrRepository.findAllAnalysisNumberByOfficeCodeAndBudgetYear(officeCode, budgetYear);
	}

	public YearMonthVo getMonthStart(TaxOperatorFormVo formVo) {
		logger.info("getMonthStart draftNumber = {}", formVo.getDraftNumber());

		YearMonthVo ymVo = taWorksheetCondMainHdrRepository.findMonthStartByAnalysisNumber(formVo.getDraftNumber());

		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(ymVo.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(ymVo.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		ymVo.setCountGroup(taWorksheetCondMainDtlRepository.countByAnalysisNumber(formVo.getDraftNumber()));
		
		ymVo.setYearMonthStart(ymStart);
		ymVo.setYearMonthEnd(ymEnd);

		return ymVo;
	}

	public TaxOperatorVo getDraftWorksheet(TaxOperatorFormVo formVo) {
		formVo.setAnalysisNumber(formVo.getDraftNumber());
		logger.info("getDraftWorksheet analysisNumber = {}", formVo.getAnalysisNumber());

		TaxOperatorVo vo = new TaxOperatorVo();
		if (StringUtils.isNotEmpty(formVo.getAnalysisNumber())) {
			formVo.setWorksheetStatus(TA_WORKSHEET_STATUS.DRAFT);
			List<TaxOperatorDetailVo> draftDtlList = taWorksheetDtlRepository.findByCriteria(formVo);
			
			List<TaxOperatorDatatableVo> datatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(draftDtlList, formVo);
			prepareAdditionalData(formVo.getBudgetYear(), datatableVoList);
			vo.setDatas(datatableVoList);
//			vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(draftDtlList, formVo));
			vo.setCount(taWorksheetDtlRepository.countByCriteria(formVo));
		} else {
			vo.setDatas(new ArrayList<>());
			vo.setCount(0L);
		}

		return vo;
	}
	
	private void prepareAdditionalData(String budgetYear, List<TaxOperatorDatatableVo> taxOperatorVoList) {
		List<String> newRegIdList = taxOperatorVoList
			.stream()
			.map(e -> e.getNewRegId())
			.collect(Collectors.toList());
		
		List<String> budgetYearList = IntStream.rangeClosed(1, 3)
			.map(i -> -i).sorted().map(i -> -i)
			.map(i -> Integer.parseInt(budgetYear) - i)
			.boxed().map(i -> String.valueOf(i))
			.collect(Collectors.toList());
		
		Map<String, List<String>> dutyMap = null;
		Map<String, List<String>> auditPlanMap = null;
		if (newRegIdList != null && newRegIdList.size() > 0) {
			dutyMap = taWsReg4000Repository.findDutyByNewRegId(newRegIdList);
			auditPlanMap = taPlanWorksheetHisRepository.findAuditPlanCodeByNewRegId(newRegIdList, budgetYearList);
		}
		
		List<String> auditPlanList = null;
		for (TaxOperatorDatatableVo vo : taxOperatorVoList) {
			// Check Multiple Duty Group
			if (FLAG.Y_FLAG.equals(vo.getMultiDutyFlag())) {
				vo.setMultiDutyDesc(org.springframework.util.StringUtils.collectionToCommaDelimitedString(dutyMap.get(vo.getNewRegId())));
			}
			// Check Last Audit Plan
			int i = 1;
			for (String auditYear : budgetYearList) {
				auditPlanList = auditPlanMap.get(auditYear + vo.getNewRegId());
				if (auditPlanList != null && auditPlanList.size() > 1) {
					if (i == 1) {
						// Last 3 Year
						vo.setTaxAuditLast3MultiFlag(FLAG.Y_FLAG);
						vo.setTaxAuditLast3MultiDesc(org.springframework.util.StringUtils.collectionToCommaDelimitedString(auditPlanList));
					} else if (i == 2) {
						// Last 2 Year
						vo.setTaxAuditLast2MultiFlag(FLAG.Y_FLAG);
						vo.setTaxAuditLast2MultiDesc(org.springframework.util.StringUtils.collectionToCommaDelimitedString(auditPlanList));
					} else if (i == 3) {
						// Last 1 Year
						vo.setTaxAuditLast1MultiFlag(FLAG.Y_FLAG);
						vo.setTaxAuditLast1MultiDesc(org.springframework.util.StringUtils.collectionToCommaDelimitedString(auditPlanList));
					}
				}
				i++;
			}
		}
	}

}
