package th.go.excise.ims.ta.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_MAIN_COND_RANGE;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_SUB_COND_CAPITAL;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubRisk;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHisRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubRiskRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.MasCondSubCapitalFormVo;
import th.go.excise.ims.ta.vo.TaxDraftVo;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.WorksheetCondDetailVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Service
public class WorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(WorksheetService.class);

	private static final String MAX_DATE = "999912";

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

	@Autowired
	private TaPlanWorksheetSendRepository taPlanWorksheetSendRepository;

	@Autowired
	private TaPlanWorksheetHisRepository taPlanWorksheetHisRepository;
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Transactional(rollbackOn = Exception.class)
	public void saveWorksheet(String draftNumber, String budgetYear) throws Exception {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String analysisNumber = draftNumber;
		logger.info("saveWorksheet budgetYear={}, analysisNumber={}", budgetYear, analysisNumber);

		// ==> Update WorksheetHdr
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(analysisNumber);
		worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.CONDITION);
		taWorksheetHdrRepository.save(worksheetHdr);

		// Prepare Data for Assessment
		List<TaxDraftVo> taxDraftVoList = taWorksheetDtlRepository.findByAnalysisNumber(analysisNumber);
		// Main Condition
		TaWorksheetCondMainHdr condMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(analysisNumber);
		List<TaWorksheetCondMainDtl> condMainDtlList = taWorksheetCondMainDtlRepository.findByAnalysisNumber(analysisNumber);
		String indexLastCondition = null;
		for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
			if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(condMainDtl.getCondType())) {
				indexLastCondition = condMainDtl.getCondGroup();
				break;
			}
		}
		// Sub Condition - Capital
		BigDecimal ONE_MILLION = new BigDecimal(1000000);
		Map<String, MasCondSubCapitalFormVo> condSubCapitalMap = taWorksheetCondSubCapitalRepository.findByAnalysisNumber(analysisNumber)
			.stream()
			.collect(Collectors.toMap(e -> StringUtils.defaultIfBlank(e.getDutyCode(), StringUtils.EMPTY), e -> {
				MasCondSubCapitalFormVo condSubCapitalVo = new MasCondSubCapitalFormVo();
				condSubCapitalVo.setHugeCapitalAmount(e.getHugeCapitalAmount().multiply(ONE_MILLION));
				condSubCapitalVo.setLargeCapitalAmount(e.getLargeCapitalAmount().multiply(ONE_MILLION));
				condSubCapitalVo.setMediumCapitalAmount(e.getMediumCapitalAmount().multiply(ONE_MILLION));
				condSubCapitalVo.setSmallCapitalAmount(e.getSmallCapitalAmount().multiply(ONE_MILLION));
				return condSubCapitalVo;
			}));
		// Sub Condition - Risk
		Map<String, TaWorksheetCondSubRisk> condSubRiskMap = taWorksheetCondSubRiskRepository.findByAnalysisNumber(analysisNumber)
			.stream()
			.collect(Collectors.toMap(e -> e.getDutyCode(), Function.identity()));
		// Sub Condition - No Audit
		TaWorksheetCondSubNoAudit condSubNoAudit = taWorksheetCondSubNoAuditRepository.findByAnalysisNumber(analysisNumber);
		List<String> budgetYearList = new ArrayList<>();
		Map<String, String> condSubNoAuditMap = new HashMap<>();
		if (condSubNoAudit != null) {
			for (int i = 0; i < condSubNoAudit.getNoTaxAuditYearNum(); i++) {
				budgetYearList.add(String.valueOf(Integer.parseInt(budgetYear) - (i + 1)));
			}
			condSubNoAuditMap = taPlanWorksheetHisRepository.findAuditPlanCodeByOfficeCodeAndBudgetYearList(officeCode, budgetYearList);
		}

		// ==> Assessment Condition
		List<TaWorksheetDtl> worksheetDtlList = new ArrayList<>();
		TaWorksheetDtl worksheetDtl = null;
		String regDate = null;

		for (TaxDraftVo taxDraftVo : taxDraftVoList) {
			worksheetDtl = new TaWorksheetDtl();
			worksheetDtl.setWorksheetDtlId(taxDraftVo.getWorksheetDtlId());
			worksheetDtl.setNewRegId(taxDraftVo.getNewRegId());
			worksheetDtl.setAnalysisNumber(analysisNumber);
			worksheetDtl.setCondMainGrp("0"); // Default Main Group
			if (FLAG.Y_FLAG.equals(condMainHdr.getNewFacFlag())) {
				for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
					if (TA_MAS_COND_MAIN_TYPE.TAX.equals(condMainDtl.getCondType())) {
						if (isConditionGroup(condMainDtl, taxDraftVo)) {
							worksheetDtl.setCondMainGrp(condMainDtl.getCondGroup());
							break;
						}
					}
				}
			} else {
				if (taxDraftVo.getRegDate() != null) {
					regDate = taxDraftVo.getRegDate().format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM));
				} else {
					regDate = MAX_DATE;
				}
				if (regDate.compareTo(condMainHdr.getYearMonthStart()) >= 0 && regDate.compareTo(condMainHdr.getYearMonthEnd()) <= 0) {
					worksheetDtl.setCondMainGrp(indexLastCondition);
				} else {
					// Check Case T
					for (TaWorksheetCondMainDtl worksheetCondMainDtl : condMainDtlList) {
						if (TA_MAS_COND_MAIN_TYPE.TAX.equals(worksheetCondMainDtl.getCondType())) {
							if (isConditionGroup(worksheetCondMainDtl, taxDraftVo)) {
								worksheetDtl.setCondMainGrp(worksheetCondMainDtl.getCondGroup());
								break;
							}
						}
					}
				}
			}
			//logger.debug("newRegId=" + taxDraftVo.getNewRegId() + "\ttaxAmtChnPnt=" + taxDraftVo.getTaxAmtChnPnt() + "\tcondMainGrp=" + worksheetDtl.getCondMainGrp());

			// ==> Assessment Sub Condition - Capital
			if (FLAG.Y_FLAG.equals(worksheetHdr.getCondSubCapitalFlag())) {
				worksheetDtl.setCondSubCapital(isConditionSubCapital(condSubCapitalMap, taxDraftVo));
				System.out.println(taxDraftVo.getRegCapital() + " " + worksheetDtl.getCondSubCapital());
			}

			// ==> Assessment Sub Condition - Risk
			if (FLAG.Y_FLAG.equals(worksheetHdr.getCondSubRiskFlag())) {
				worksheetDtl.setCondSubRisk(isConditionSubRisk(condSubRiskMap, taxDraftVo));
			}

			// ==> Assessment Sub Condition - No Audit
			if (FLAG.Y_FLAG.equals(worksheetHdr.getCondSubNoAuditFlag())) {
				worksheetDtl.setCondSubNoAudit(isConditionSubNoAudit(condSubNoAuditMap, budgetYearList, taxDraftVo));
			}

			worksheetDtl.setUpdatedBy(UserLoginUtils.getCurrentUsername());
			worksheetDtl.setUpdatedDate(LocalDateTime.now());
			
			// Start check new Condition
			int condSorting = 0;
			if (LocalDateUtils.isRange(condMainHdr.getRegDateStart(), condMainHdr.getRegDateEnd(), taxDraftVo.getRegDate())) {
				worksheetDtl.setCondRegDate(FLAG.Y_FLAG);
			}
			
			if (!FLAG.Y_FLAG.equals(worksheetDtl.getCondRegDate())) {
				if (condSubNoAudit != null) {
					int lastAuditYear = StringUtils.isNotBlank(taxDraftVo.getLastAuditYear()) ? Integer.valueOf(taxDraftVo.getLastAuditYear()) : 0;
					int year = Integer.valueOf(worksheetHdr.getBudgetYear()) - lastAuditYear - 1;
					logger.debug("Check condSubNoAudit newRegId={}, lastAuditYear={}, condNoTaxAuditYear={}, year={}, year >= condNoTaxAuditYear = {}",
							taxDraftVo.getNewRegId(), lastAuditYear, condSubNoAudit.getNoTaxAuditYearNum(), year, (year >= Integer.valueOf(condSubNoAudit.getNoTaxAuditYearNum())));
					if (year >= Integer.valueOf(condSubNoAudit.getNoTaxAuditYearNum())) {
						worksheetDtl.setCondSubNoAudit(FLAG.Y_FLAG);
						condSorting++;
					}
				}
				
				// Condition G1, G2
				for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
					if (TA_MAS_COND_MAIN_TYPE.TAX.equals(condMainDtl.getCondType())) {
						logger.debug("Check condG{} newRegId={}, taxMonthNo={}, changePnt={}",
								condMainDtl.getCondGroup(), taxDraftVo.getNewRegId(), taxDraftVo.getTaxMonthNo(), taxDraftVo.getTaxAmtChnPnt());
						if (isConditionGroup(condMainDtl, taxDraftVo)) {
							logger.debug("condGroup={}, flag=Y");
							setConnGroup(worksheetDtl, condMainDtl.getCondGroup());
							condSorting++;
						}
					}
				}
			}
			worksheetDtl.setCondSorting(condSorting);
			
			worksheetDtlList.add(worksheetDtl);
		}
		taWorksheetDtlRepository.batchUpdate(worksheetDtlList);
	}

	private boolean isConditionGroup(TaWorksheetCondMainDtl worksheetCondMainDtl, TaxDraftVo taxDratfVo) {
		boolean condGroup = false;
		BigDecimal rangeStart = worksheetCondMainDtl.getRangeStart();
		BigDecimal rangeEnd = worksheetCondMainDtl.getRangeEnd();
		BigDecimal value = floorAndCeilPercentage(taxDratfVo.getTaxAmtChnPnt());
		if (TA_MAIN_COND_RANGE.GREATER_THAN.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			if (rangeEnd != null) {
				if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = NumberUtils.isGreaterThan(rangeStart, value) && NumberUtils.isLessThan(rangeEnd, value);
				} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = NumberUtils.isGreaterThan(rangeStart, value) && NumberUtils.isLessThanOrEquals(rangeEnd, value);
				} else {
					condGroup = NumberUtils.isGreaterThan(rangeStart, value);
				}
			} else {
				condGroup = NumberUtils.isGreaterThan(rangeStart, value);
			}
		} else if (TA_MAIN_COND_RANGE.GREATER_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			if (rangeEnd != null) {
				if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = NumberUtils.isGreaterThanOrEquals(rangeStart, value) && NumberUtils.isLessThan(rangeEnd, value);
				} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = NumberUtils.isGreaterThanOrEquals(rangeStart, value) && NumberUtils.isLessThanOrEquals(rangeEnd, value);
				} else {
					condGroup = NumberUtils.isGreaterThanOrEquals(rangeStart, value);
				}
			} else {
				condGroup = NumberUtils.isGreaterThanOrEquals(rangeStart, value);
			}
		} else if (TA_MAIN_COND_RANGE.EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = NumberUtils.isEquals(rangeStart, value);
		} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = NumberUtils.isLessThanOrEquals(rangeStart, value);
		} else if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = NumberUtils.isLessThan(rangeStart, value);
		}
		return taxDratfVo.getTaxMonthNo().intValue() >= worksheetCondMainDtl.getTaxMonthStart().intValue()
			&& taxDratfVo.getTaxMonthNo().intValue() <= worksheetCondMainDtl.getTaxMonthEnd().intValue()
			&& condGroup;
	}

	private BigDecimal floorAndCeilPercentage(BigDecimal amount) {
		amount = NumberUtils.nullToZero(amount);
		BigDecimal amountPlus100 = new BigDecimal("100");
		BigDecimal amountMinus100 = new BigDecimal("-100");
		if (amount.compareTo(amountPlus100) == 1) {
			return amountPlus100;
		} else if (amount.compareTo(amountMinus100) == -1) {
			return amountMinus100;
		} else {
			return amount;
		}
	}

	private String isConditionSubCapital(Map<String, MasCondSubCapitalFormVo> condSubCapitalMap, TaxDraftVo taxDraftVo) {
		String condSubCapitalCode = null;

		MasCondSubCapitalFormVo condSubCapital = null;
		if (condSubCapitalMap.containsKey(taxDraftVo.getDutyCode())) {
			condSubCapital = condSubCapitalMap.get(taxDraftVo.getDutyCode());
		} else {
			condSubCapital = condSubCapitalMap.get(StringUtils.EMPTY);
		}

		BigDecimal regCapital = NumberUtils.nullToZero(NumberUtils.toBigDecimal(taxDraftVo.getRegCapital()));
		if (condSubCapital != null) {
			if (NumberUtils.isGreaterThan(condSubCapital.getHugeCapitalAmount(), regCapital)) {
				condSubCapitalCode = TA_SUB_COND_CAPITAL.HUGE_CAPITAL;
			} else if (NumberUtils.isGreaterThan(condSubCapital.getLargeCapitalAmount(), regCapital)) {
				condSubCapitalCode = TA_SUB_COND_CAPITAL.LARGE_CAPITAL;
			} else if (NumberUtils.isGreaterThan(condSubCapital.getMediumCapitalAmount(), regCapital)) {
				condSubCapitalCode = TA_SUB_COND_CAPITAL.MEDIUM_CAPITAL;
			} else if (NumberUtils.isLessThanOrEquals(condSubCapital.getSmallCapitalAmount(), regCapital)) {
				condSubCapitalCode = TA_SUB_COND_CAPITAL.SMALL_CAPITAL;
			} else {
				condSubCapitalCode = TA_SUB_COND_CAPITAL.OTHER;
			}
		} else {
			condSubCapitalCode = TA_SUB_COND_CAPITAL.OTHER;
		}

		return condSubCapitalCode;
	}

	private String isConditionSubRisk(Map<String, TaWorksheetCondSubRisk> condSubRiskMap, TaxDraftVo taxDraftVo) {
		String condSubRiskCode = null;

		if (condSubRiskMap.containsKey(taxDraftVo.getDutyCode())) {
			condSubRiskCode = condSubRiskMap.get(taxDraftVo.getDutyCode()).getRiskLevel();
		}

		return condSubRiskCode;
	}

	private String isConditionSubNoAudit(Map<String, String> condSubNoAuditMap, List<String> budgetYearList, TaxDraftVo taxDraftVo) {
		String condSubNoAuditCode = FLAG.Y_FLAG;

		String key = null;
		for (String budgetYear : budgetYearList) {
			key = budgetYear + taxDraftVo.getNewRegId();
			if (condSubNoAuditMap.containsKey(key)) {
				condSubNoAuditCode = FLAG.N_FLAG;
				break;
			}
		}

		return condSubNoAuditCode;
	}
	
	private void setConnGroup(TaWorksheetDtl worksheetDtl, String condGroup) {
		try {
			Method method = TaWorksheetDtl.class.getDeclaredMethod("setCondG" + condGroup, String.class);
			method.invoke(worksheetDtl, FLAG.Y_FLAG);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<TaWorksheetHdr> findAllAnalysisNumber(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		if (StringUtils.isEmpty(budgetYear)) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		logger.info("findAllAnalysisNumber officeCode={}, budgetYear={}", officeCode, budgetYear);
		return taWorksheetHdrRepository.findByOfficeCodeAndBudgetYearOrderByCreatedDateDesc(officeCode, budgetYear);
	}

	public YearMonthVo getMonthStart(TaxOperatorFormVo formVo) {
		logger.info("getMonthStart analysisNumber = {}", formVo.getAnalysisNumber());

		YearMonthVo obj = taWorksheetCondMainHdrRepository.findMonthStartByAnalysisNumber(formVo.getAnalysisNumber());

		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(obj.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(obj.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

		TaWorksheetCondSubNoAudit subNoAudit = taWorksheetCondSubNoAuditRepository.findByAnalysisNumber(formVo.getAnalysisNumber());

		if (subNoAudit != null) {
			obj.setYearCondSubNoAudit(subNoAudit.getNoTaxAuditYearNum().toString());
		} else {
			obj.setYearCondSubNoAudit("0");
		}
		obj.setCountGroup(taWorksheetCondMainDtlRepository.countByAnalysisNumber(formVo.getAnalysisNumber()));
		obj.setYearMonthStart(ymStart);
		obj.setYearMonthEnd(ymEnd);

		return obj;
	}

	public List<CondGroupVo> findCondGroupDtl(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("findCondGroupDtl officeCode={}, budgetYear={}", officeCode, budgetYear);
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (StringUtils.isNotBlank(formVo.getSeeDataSelect()) && planSend == null) {
			return new ArrayList<>();
		}
		return taWorksheetCondMainDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber()).stream().map(t -> {
			CondGroupVo vo = new CondGroupVo();
			vo.setAnalysisNumber(t.getAnalysisNumber());
			vo.setCondGroup(t.getCondGroup());
			vo.setTaxMonthStart(t.getTaxMonthStart());
			vo.setTaxMonthEnd(t.getTaxMonthEnd());
			vo.setRangeStart(t.getRangeStart());
			vo.setRangeEnd(t.getRangeEnd());
			vo.setRiskLevel(t.getRiskLevel() == null ? null : Integer.valueOf(t.getRiskLevel()));
			String riskLevelDesc = null;
			try {
				riskLevelDesc = ApplicationCache.getParamInfoByCode("TA_RISK_LEVEL", t.getRiskLevel().toString()).getValue1();
			} catch (Exception e) {
				logger.warn("Not found risLevel={}", t.getRiskLevel());
				riskLevelDesc = "";
			}
			vo.setRiskLevelDesc(riskLevelDesc);
			return vo;
		}).collect(Collectors.toList());
	}

	public TaxOperatorVo getWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		if(StringUtils.isNotBlank(formVo.getBudgetYear())) {
			budgetYear = formVo.getBudgetYear();
		}else {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}

		logger.info("getWorksheet officeCode={}, budgetYear={}, analysisNumber={}", officeCode, budgetYear, formVo.getAnalysisNumber());

		if (StringUtils.isBlank(formVo.getAnalysisNumber())) {
			formVo.setAnalysisNumber("");
		}

		TaxOperatorVo vo = new TaxOperatorVo();
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (StringUtils.isNotBlank(formVo.getSeeDataSelect()) && planSend == null) {
			vo.setDatas(new ArrayList<>());
			vo.setCount(0L);
		} else {
			if (StringUtils.isNotBlank(formVo.getArea()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getArea())) {
				formVo.setOfficeCode(formVo.getArea());
			} else if (StringUtils.isNotBlank(formVo.getSector()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getSector())) {
				formVo.setOfficeCode(formVo.getSector());
			} else {
				formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			}
			
			List<TaxOperatorDetailVo> list = taWorksheetDtlRepository.findByCriteria(formVo);
			List<TaxOperatorDatatableVo> datatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo);
			prepareAdditionalData(budgetYear, datatableVoList);
			vo.setDatas(datatableVoList);
			vo.setCount(taWorksheetDtlRepository.countByCriteria(formVo));
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

	public List<LabelValueBean> groupCondSubCapital(String analysisNumber) {
		return taWorksheetDtlRepository.groupCondSubCapital(analysisNumber);
	}

	public List<LabelValueBean> groupCondSubRisk(String analysisNumber) {
		return taWorksheetDtlRepository.groupCondSubRisk(analysisNumber);
	}

	public List<WorksheetCondDetailVo> getCondDetails(TaxOperatorFormVo formVo) {
		String analysisNumber = null;
		if (StringUtils.isNotBlank(formVo.getAnalysisNumber())) {
			analysisNumber = formVo.getAnalysisNumber();
		} else {
			analysisNumber = formVo.getDraftNumber();
		}
		logger.info("getCondDetails analysisNumber={}", analysisNumber);
		
		List<WorksheetCondDetailVo> voList = new ArrayList<>();
		
		// Condition 1
		TaWorksheetCondSubNoAudit condSubNoAudit = taWorksheetCondSubNoAuditRepository.findByAnalysisNumber(analysisNumber);
		String noTaxAuditYearNum = null;
		if (condSubNoAudit != null) {
			noTaxAuditYearNum = String.valueOf(condSubNoAudit.getNoTaxAuditYearNum());
		} else {
			noTaxAuditYearNum = "3";
		}
		String condDesc1 = String.format(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "NO_AUDIT").getValue1(), noTaxAuditYearNum);
		WorksheetCondDetailVo condVo1 = new WorksheetCondDetailVo();
		condVo1.setCondGroup("1");
		condVo1.setCondDtlDesc(condDesc1);
		voList.add(condVo1);
		
		List<TaWorksheetCondMainDtl> condMainDtlList = taWorksheetCondMainDtlRepository.findByAnalysisNumber(analysisNumber);
		int i = 2;
		WorksheetCondDetailVo condVo = null;
		for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
			condVo = new WorksheetCondDetailVo();
			condVo.setCondGroup(String.valueOf(i));
			condVo.setCondDtlDesc(condMainDtl.getCondDtlDesc());
			voList.add(condVo);
			i++;
		}
		
		return voList;
	}

	public TaWorksheetHdr checkEvaluateCondition(TaxOperatorFormVo formVo) {
		return taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
	}

	public List<TaWorksheetHdr> findAllAnalysisNumberHead(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		if (StringUtils.isEmpty(budgetYear)) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		logger.info("findAllAnalysisNumberHead officeCode={}, budgetYear={}", officeCode, budgetYear);
		return taWorksheetHdrRepository.findAllAnalysisNumberHead(officeCode, budgetYear);
	}

	public TaxOperatorVo getWorksheetForAssign(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		formVo.setBudgetYear(budgetYear);
		logger.info("getWorksheet officeCode={}, budgetYear={}, analysisNumber={}", officeCode, budgetYear, formVo.getAnalysisNumber());

		if (StringUtils.isBlank(formVo.getAnalysisNumber())) {
			formVo.setAnalysisNumber("");
		}

		TaxOperatorVo vo = new TaxOperatorVo();
		if (StringUtils.isNotBlank(formVo.getArea()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getArea())) {
			formVo.setOfficeCode(formVo.getArea());
		} else if (StringUtils.isNotBlank(formVo.getSector()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getSector())) {
			formVo.setOfficeCode(formVo.getSector());
		} else {
			formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		}
		
		List<TaxOperatorDetailVo> list = taWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> datatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo);
		prepareAdditionalData(budgetYear, datatableVoList);
		vo.setDatas(datatableVoList);
//		vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo));
		vo.setCount(taWorksheetDtlRepository.countByCriteria(formVo));

		return vo;
	}

	public List<String> getBudgetYearList() {
		List<String> entities = taWorksheetHdrRepository.findBudgetYearDistinctOrderByBudgetYearDesc();
		return entities;
	}

}
