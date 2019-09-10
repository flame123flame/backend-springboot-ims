package th.go.excise.ims.ia.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaInspectionPlanRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigAllRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;
import th.go.excise.ims.ia.vo.Int030407Vo;
import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401ListVo;
import th.go.excise.ims.ia.vo.Int0401Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int0401Service {

	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;

	@Autowired
	private Int0401JdbcRepository int0401JdbcRep;

	@Autowired
	private IaInspectionPlanRepository iaInspectionPlanRepository;
	
	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	
	@Autowired
	private IaRiskFactorsConfigAllRepository iaRiskFactorsConfigAllRepository;
	
	@Autowired
	private Int030401Service int030401Service;
	
	@Autowired
	private Int030405Service int030405Service;
	
	@Autowired
	private Int030403Service int030403Service;
	
	@Autowired
	private Int030406Service int030406Service;
	
	@Autowired
	private Int030407Service int030407Service;
	
	@Autowired
	private Int030404Service int030404Service;
	
	@Autowired
	private Int020301Service int020301Service;
	

	public List<Int0401Vo> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) throws IOException {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<Int0401Vo> lists = new ArrayList<>();
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		List<Int0401HeaderVo> factors = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		Int0401CalConfigVo config = int0401JdbcRep.findConfigAll(budgetYear, inspectionWork);
		
		
		if (config != null && config.getMedium() != null) {
			for (IaRiskSelectCase selectCase : selectCases) {
				Int0401Vo list = new Int0401Vo();
				List<Int0401ListVo> listVos = new ArrayList<>();
				
// ******************** CalculateCriteria **********************
				List<IntCalculateCriteriaVo> listsCal = new ArrayList<IntCalculateCriteriaVo>();
				
				BigDecimal riskRating = new BigDecimal(0);
				BigDecimal calRiskRatePercent = new BigDecimal(0);
				BigDecimal percentAll = new BigDecimal(0);
				String checkStatusScreen = "T";
				
				for (Int0401HeaderVo factor : factors) {
					Int0401ListVo listVo = new Int0401ListVo();
					
// ******************** CalculateCriteria **********************
					IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();
					if(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_3_CODE.equals(factor.getIaRiskFactors().getStatusScreen())) {
						
						// Risk Factors 1 NEW";
						if(IaConstants.IA_DATA_EVALUATE.NEW.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calNew(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getProjectCode(),selectCase.getExciseCode(),selectCase.getSystemCode());
						
						}
						
						// Risk Factors 2 questionnaire";
						else if(IaConstants.IA_DATA_EVALUATE.QUESTIONNAIRE.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calQuestionnaire(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getExciseCode());
							
						}
						
						// Risk Factors 3 budget_project";
						else if(IaConstants.IA_DATA_EVALUATE.BUDGET_PROJECT.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calBudgetProject(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getProjectCode());
							
						}
						
						// Risk Factors 4 project_efficiency";
						else if(IaConstants.IA_DATA_EVALUATE.PROJECT_EFFICIENCY.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calProjectEfficiency(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getProjectCode());
							
						}
						
						// Risk Factors 5 system_unworking";
						else if(IaConstants.IA_DATA_EVALUATE.SYSTEM_UNWORKING.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calSystemUnworking(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getSystemCode());
							
						}
						
						// Risk Factors 6 check_period";
						else if(IaConstants.IA_DATA_EVALUATE.CHECK_PERIOD.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calCheckPeriod(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getExciseCode());
							
						}
						
						// Risk Factors 7 income_perform";
						else if(IaConstants.IA_DATA_EVALUATE.INCOME_PERFORM.equals(factor.getIaRiskFactors().getDataEvaluate())) {
							
							calVo = calIncomePerform(factor.getIaRiskFactors().getId(),budgetYear,inspectionWork,selectCase.getExciseCode());
							
						}
						
						// Risk Factors 8 suppression";
						
					}else {
						checkStatusScreen = "F";
					}
					
					
					
					
//					calVo = IntCalculateCriteriaUtil.calculateCriteriaAndGetConfigByIdFactors(new BigDecimal(5), factor.getId());
				
					if (listVo.getRiskRate() != null) {
						riskRating.add(listVo.getRiskRate());
					}
					if(calVo.getRiskRate()!=null&&calVo.getPercent()!=null) {
						calRiskRatePercent = calRiskRatePercent.add(calVo.getRiskRate().multiply(calVo.getPercent()));
						percentAll = percentAll.add(calVo.getPercent());
						
					}
					
					
					listVos.add(listVo);
// ******************** CalculateCriteria **********************
					listsCal.add(calVo);
				}
				list.setId(selectCase.getId());
				list.setBudgetYear(selectCase.getBudgetYear());
				
				list.setProjectCode(selectCase.getProjectCode());
				list.setProjectName(selectCase.getProject());
				
				list.setExciseCode(selectCase.getExciseCode());
				list.setSectorName(selectCase.getSector());
				list.setAreaName(selectCase.getArea());
				
				list.setSystemCode(selectCase.getSystemCode());
				list.setSystemName(selectCase.getSystemName());
				
				list.setInspectionWork(selectCase.getInspectionWork());
				list.setStatus(selectCase.getStatus());
				
				list.setLists(listVos);
// ******************** CalculateCriteria **********************			
				list.setListsCal(listsCal);
				
				list.setRiskItem(new BigDecimal(listVos.size()));
				
// ******************** CalculateCriteria All **********************
				
				
				
				if("T".equals(checkStatusScreen)
						&& calRiskRatePercent!= null 
						&& percentAll != null 
						&& calRiskRatePercent.floatValue()!=0f 
						&& percentAll.floatValue()!=0f ) {
					
					BigDecimal riskRat = new BigDecimal(calRiskRatePercent.floatValue()/percentAll.floatValue());
					list.setRiskRate(riskRat.setScale(2, BigDecimal.ROUND_HALF_UP));
					IaRiskFactorsConfig calVo = matchConfigAllWithConfig(budgetYear, inspectionWork);
					IntCalculateCriteriaVo calVoRes = IntCalculateCriteriaUtil.calculateCriteria(list.getRiskRate(),calVo);
					
					list.setRiskText(calVoRes.getTranslatingRisk());
					list.setRiskColor(calVoRes.getCodeColor());
				}
				
				/* set ExciseDepartmentVo */
				if( list.getExciseCode() != null && list.getExciseCode().length() == 6 ) {
					list.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(list.getExciseCode()));
				}
				lists.add(list);
			}
		}
		
		Collections.sort(lists, new Comparator<Int0401Vo>() {
			@Override
			public int compare(final Int0401Vo object1, final Int0401Vo object2) {
				float obj1 = (object1.getRiskRate()!=null)?object1.getRiskRate().floatValue():0f;
				float obj2 = (object2.getRiskRate()!=null)?object2.getRiskRate().floatValue():0f;
				
//				Very --> Little
				return (obj1 > obj2)? -1 : (obj1 < obj2) ? 1 : 0;
				
//				Little --> Very
//				return (obj2 > obj1)? -1 : (obj2 < obj1) ? 1 : 0; 
			}
		});
		
		return lists;
	}
	
	public IaRiskFactorsConfig matchConfigAllWithConfig(String budgetYear, BigDecimal inspectionWork) {
		IaRiskFactorsConfigAll conAll = new IaRiskFactorsConfigAll();
		IaRiskFactorsConfig con = new IaRiskFactorsConfig();
		conAll = iaRiskFactorsConfigAllRepository.findByBudgetYearByInspectionWork(budgetYear,inspectionWork);
		
		con.setId(conAll.getId());

		con.setFactorsLevel(conAll.getFactorsLevel());
	
			con.setVerylow(conAll.getVerylow());
			con.setVerylowStart(conAll.getVerylowStart());
			con.setVerylowEnd(conAll.getVerylowEnd());
			con.setVerylowCondition(conAll.getVerylowCondition());
			con.setVerylowRating(conAll.getVerylowRating());
			con.setVerylowColor(conAll.getVerylowColor());
	
	
			con.setLow(conAll.getLow());
			con.setLowStart(conAll.getLowStart());
			con.setLowEnd(conAll.getLowEnd());
			con.setLowCondition(conAll.getLowCondition());
			con.setLowRating(conAll.getLowRating());
			con.setLowColor(conAll.getLowColor());
	
	
			con.setMedium(conAll.getMedium());
			con.setMediumStart(conAll.getMediumStart());
			con.setMediumEnd(conAll.getMediumEnd());
			con.setMediumCondition(conAll.getMediumCondition());
			con.setMediumRating(conAll.getMediumRating());
			con.setMediumColor(conAll.getMediumColor());
	
	
			con.setHigh(conAll.getHigh());
			con.setHighStart(conAll.getHighStart());
			con.setHighEnd(conAll.getHighEnd());
			con.setHighCondition(conAll.getHighCondition());
			con.setHighRating(conAll.getHighRating());
			con.setHighColor(conAll.getHighColor());
	
	
			con.setVeryhigh(conAll.getVeryhigh());
			con.setVeryhighStart(conAll.getVeryhighStart());
			con.setVeryhighEnd(conAll.getVeryhighEnd());
			con.setVeryhighCondition(conAll.getVeryhighCondition());
			con.setVeryhighRating(conAll.getVeryhighRating());
			con.setVeryhighColor(conAll.getVeryhighColor());

		
		return con;
	}
	
	
// *****************  Set IntCalculateCriteriaVo Data_Evaluate 1 = NEW  *****************  
	public IntCalculateCriteriaVo calNew(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String projectCode,String exciseCode,String systemCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

			Int030401FormVo form = new Int030401FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			form.setBudgetYear(budgetYear);
			form.setInspectionWork(inspectionWork);
			form.setIdFactors(idFactors);
			form.setIdConfig(config.getId());
			List<Int030401Vo> list = int030401Service.factorsDataList(form);
			for (Int030401Vo vo : list) {
				
				if(new BigDecimal(3).equals(inspectionWork)&&projectCode.equals(vo.getIaRiskFactorsData().getProjectCode())) {
					

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}else if(new BigDecimal(4).equals(inspectionWork)&&systemCode.equals(vo.getIaRiskFactorsData().getSystemCode())) {
					

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}else if(new BigDecimal(5).equals(inspectionWork)&&exciseCode.equals(vo.getIaRiskFactorsData().getExciseCode())) {
					

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}
	
			}
		
		return calVo;
		
	}
	
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 2 = questionnaire  *****************
	public IntCalculateCriteriaVo calQuestionnaire(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String exciseCode) {
			IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

				IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
				
				 List<Int020301InfoVo> list = int020301Service.findInfoByIdHdrRisk(config.getInfoUsedRisk(),budgetYear,config.getId().toString(), null);
				for (Int020301InfoVo vo : list) {
					
					if(vo.getOfficeCode().equals(exciseCode)) {

						calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
						calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
						calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
						calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
						calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
						calVo.setPercent(config.getPercent());
						
					}
		
				}
			
			return calVo;
			
		}	
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 3 = budget_project  *****************  
	public IntCalculateCriteriaVo calBudgetProject(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String projectCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

			Int030403FormVo form = new Int030403FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			form.setBudgetYear(budgetYear);
			form.setInspectionWork(inspectionWork);
			form.setIdConfig(config.getId());
			
// *************** Edit ***************
			form.setProjecttypecode("1");
			
			form.setProjectyear(budgetYear);
			List<Int030403Vo> list = int030403Service.list(form);
			for (Int030403Vo vo : list) {
				
				if(vo.getIaRiskBudgetProject().getProjectid().equals(projectCode)) {

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}
	
			}
		
		return calVo;
		
	}	
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 4 = project_efficiency  *****************  
	public IntCalculateCriteriaVo calProjectEfficiency(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String projectCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

			Int030404FormVo form = new Int030404FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			form.setBudgetYear(budgetYear);
			form.setInspectionWork(inspectionWork);
			form.setIdConfig(config.getId());
			List<Int030404Vo> list = int030404Service.projectEfficiencyList(form);
			for (Int030404Vo vo : list) {
				
				if(vo.getIaRiskProEfVo().getProjectId().equals(projectCode)) {

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}
	
			}
		
		return calVo;
		
	}	
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 5 = system_unworking  *****************  
	public IntCalculateCriteriaVo calSystemUnworking(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String systemCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

			Int030405FormVo form = new Int030405FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			form.setBudgetYear(budgetYear);
			form.setInspectionWork(inspectionWork);
			form.setStartDate(ConvertDateUtils.formatDateToString(config.getStartDate(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH));
			form.setEndDate(ConvertDateUtils.formatDateToString(config.getEndDate(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH));
			form.setIdConfig(config.getId());
			List<Int030405Vo> list = int030405Service.systemUnworkingList(form);
			for (Int030405Vo vo : list) {
				
				if(vo.getSystemCode().equals(systemCode)) {

					calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
					calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}
	
			}
		
		return calVo;
		
	}
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 6 = check_period  ***************** 	
	public IntCalculateCriteriaVo calCheckPeriod(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String exciseCode) {
	IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();


	Int030406FormVo form = new Int030406FormVo();
	IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
	form.setBudgetYear(budgetYear);
	form.setInspectionWork(inspectionWork);
	form.setIdConfig(config.getId());
	List<Int030406Vo> list = int030406Service.checkPeriodList(form);
	for (Int030406Vo vo : list) {
		
		if(vo.getIaRiskCheckPeriod().getExciseCode().equals(exciseCode)) {

			calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
			calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
			calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
			calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
			calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
			calVo.setPercent(config.getPercent());
			
		}

	}

return calVo;

} 
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 7 = income_perform  *****************  
	public IntCalculateCriteriaVo calIncomePerform(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String exciseCode) throws IOException {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

		IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
		List<Int030407Vo> list = int030407Service.findByBudgetYear(budgetYear,config.getId().toString());
		for (Int030407Vo vo : list) {
			
			if(vo.getOfficeCode().equals(exciseCode)) {

				calVo.setDataCal(vo.getIntCalculateCriteriaVo().getDataCal());
				calVo.setCodeColor(vo.getIntCalculateCriteriaVo().getCodeColor());
				calVo.setColor(vo.getIntCalculateCriteriaVo().getColor());
				calVo.setRiskRate(vo.getIntCalculateCriteriaVo().getRiskRate());
				calVo.setTranslatingRisk(vo.getIntCalculateCriteriaVo().getTranslatingRisk());
				calVo.setPercent(config.getPercent());
				
			}

		}
		return calVo;
	}
	// *****************  Set IntCalculateCriteriaVo Data_Evaluate 8 = suppression  *****************  
	

	public BigDecimal findStatusByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		return new BigDecimal(selectCases.size());
	}

	public List<Int0401HeaderVo> findHeadByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<Int0401HeaderVo> lists = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		return lists;
	}

	public List<Int0401Vo> updateRowByStatus(List<BigDecimal> ids, String status) throws IOException {
		List<IaRiskSelectCase> selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.findAllById(ids);
		for (IaRiskSelectCase selectCase : selectCases) {
			selectCase.setStatus(status);
		}
		selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		List<Int0401Vo> currentLists = new ArrayList<>();
		if (selectCases != null && selectCases.size() > 0) {
			String budgetYear = selectCases.get(0).getBudgetYear();
			BigDecimal inspectionWork = selectCases.get(0).getInspectionWork();
			String newStatus = "C".equalsIgnoreCase(status) ? "S" : "C";
			currentLists = findByBudgetYearAndInspectionWork(budgetYear, inspectionWork.toString(), newStatus);
		}
		return currentLists;
	}

	

	public void saveInspectionPlan(List<IaInspectionPlan> request) {
		iaInspectionPlanRepository.saveAll(request);
	}

}
