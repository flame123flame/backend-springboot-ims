package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.persistence.entity.IaPlanHdr;
import th.go.excise.ims.ia.persistence.repository.IaPlanDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaPlanHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDayActivityJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDtlJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int0101PlanDayVo;
import th.go.excise.ims.ia.vo.Int01DtlVo;
import th.go.excise.ims.ia.vo.Int01HdrVo;
import th.go.excise.ims.ia.vo.Int01MonthVo;
import th.go.excise.ims.ia.vo.Int01TableVo;
import th.go.excise.ims.ia.vo.Int01Vo;

@Service
public class Int01Service {
	@Autowired
	private IaPlanHdrRepository iaPlanHdrRepository;

	@Autowired
	private IaPlanHdrJdbcRepository iaPlanHdrJdbcRepository;

	@Autowired
	private IaPlanDtlJdbcRepository iaPlanDtlJdbcRepository;

	@Autowired
	private IaPlanDtlRepository iaPlanDtlRepository;
	
	@Autowired
	private IaPlanDayActivityJdbcRepository iaPlanDayActivityJdbcRepository;

	public Int01Vo findDataByBudgetYear(String budgetYear) throws Exception {
		Int01Vo response = new Int01Vo();
		List<Int01TableVo> detailList = new ArrayList<Int01TableVo>();

		/* header */
		Int01HdrVo header = iaPlanHdrJdbcRepository.getDataFilter(budgetYear);
		header.setStatusStr(ApplicationCache.getParamInfoByCode("IA_PLAN_HDR_STATUS", header.getStatus()).getValue1());
		response.setHeader(header);

		/* group by inspectionWork */
		List<IaPlanDtl> inspectionWorkList = iaPlanDtlJdbcRepository.findPlanDtlGroupByInspectionWork();
		if (inspectionWorkList.size() > 0) {
			IaPlanDtl iaPlanDtl = null;
			Int01TableVo detail = null;
			for (IaPlanDtl inspectionWork : inspectionWorkList) {
				/* initial data */
				iaPlanDtl = new IaPlanDtl();
				iaPlanDtl.setBudgetYear(budgetYear);
				iaPlanDtl.setInspectionWork(inspectionWork.getInspectionWork());
				List<Int01DtlVo> dataFilter = iaPlanDtlJdbcRepository.findByIaPlanDtl(iaPlanDtl);
				
				/* detail */
				detail = new Int01TableVo();
				if (dataFilter.size() > 0) {
					detail.setInspectionWorkStr(ApplicationCache.getParamInfoByCode("IA_INSPECTION_WORK", inspectionWork.getInspectionWork()).getValue1());
					detail.setInspectionWork(inspectionWork.getInspectionWork());
					detail.setFrequency(new BigDecimal(iaPlanDtlJdbcRepository.countSumFrequencyByInspectionWorkAndBudgetYear(iaPlanDtl)));
					detail.setUnit(dataFilter.get(0).getUnit());

					/* set detail activity */
					for (Int01DtlVo objFilter : dataFilter) {
						List<IaPlanDayActivity> planDayActList = iaPlanDayActivityJdbcRepository.findplanDayActByidDtl(objFilter.getPlanDtlId());	
						
						BigDecimal budgetYearFull = new BigDecimal(budgetYear).subtract(new BigDecimal(1));
						String afterSub = budgetYear.toString().substring(2, 4);
						String beforeSub = budgetYearFull.toString().substring(2, 4);
						
						/* default List */
						Int01MonthVo monthVo = new Int01MonthVo();
						List<Int0101PlanDayVo> month10List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader10("ต.ค. " + beforeSub);
						monthVo.setMonth10(month10List);
						List<Int0101PlanDayVo> month11List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader11("พ.ย. " + beforeSub);
						monthVo.setMonth11(month11List);
						List<Int0101PlanDayVo> month12List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader12("ธ.ค. " + beforeSub);
						monthVo.setMonth12(month12List);
						List<Int0101PlanDayVo> month01List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader01("ม.ค. " + afterSub);
						monthVo.setMonth01(month01List);
						List<Int0101PlanDayVo> month02List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader02("ก.พ. " + afterSub);
						monthVo.setMonth02(month02List);
						List<Int0101PlanDayVo> month03List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader03("มี.ค. " + afterSub);
						monthVo.setMonth03(month03List);
						List<Int0101PlanDayVo> month04List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader04("เม.ย. " + afterSub);
						monthVo.setMonth04(month04List);
						List<Int0101PlanDayVo> month05List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader05("พ.ค. " + afterSub);
						monthVo.setMonth05(month05List);
						List<Int0101PlanDayVo> month06List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader06("มิ.ย. " + afterSub);
						monthVo.setMonth06(month06List);
						List<Int0101PlanDayVo> month07List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader07("ก.ค. " + afterSub);
						monthVo.setMonth07(month07List);
						List<Int0101PlanDayVo> month08List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader08("ส.ค. " + afterSub);
						monthVo.setMonth08(month08List);
						List<Int0101PlanDayVo> month09List = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader09("ก.ย. " + afterSub);
						monthVo.setMonth09(month09List);
						List<Int0101PlanDayVo> month10YList = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader10Y("ต.ค. " + (new BigDecimal(afterSub).add(new BigDecimal(1))).toString() );
						monthVo.setMonth10Y(month10YList);
						List<Int0101PlanDayVo> month11YList = new ArrayList<Int0101PlanDayVo>();
						monthVo.setHeader11Y("พ.ย. " + (new BigDecimal(afterSub).add(new BigDecimal(1))).toString() );
						monthVo.setMonth11Y(month11YList);
						
						objFilter.setCheckBtnDtl(false);
						if (planDayActList.size() > 0) {
							objFilter.setCheckBtnDtl(true);
							for (IaPlanDayActivity planDayAct : planDayActList) {
								int monthStart = planDayAct.getDateStartActivity().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
								int yearStart = planDayAct.getDateStartActivity().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
								int monthEnd = planDayAct.getDateEndActivity().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
								int yearEnd = planDayAct.getDateEndActivity().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
								int budgetYearEN = Integer.parseInt(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(budgetYear, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
								
								if (budgetYearEN == yearStart) {
									if ( (yearStart == yearEnd) && (monthEnd < 10) ) {
									
										if( monthStart == 1 || (monthEnd >= 1 && monthStart <= 1 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month01 = new Int0101PlanDayVo();
											month01.setActivity(planDayAct.getActivity());
											month01.setActivityShort(planDayAct.getActivityShort());
											month01.setActivityStatus(planDayAct.getActivityStatus());
											month01.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month01.setPlanDtlId(planDayAct.getPlanDtlId());
											month01.setPlanHdrId(planDayAct.getPlanHdrId());
											month01.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month01List.add(month01);
										}
										
										if(monthStart == 2 || (monthEnd >= 2 && monthStart <= 2 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month02 = new Int0101PlanDayVo();
											month02.setActivity(planDayAct.getActivity());
											month02.setActivityShort(planDayAct.getActivityShort());
											month02.setActivityStatus(planDayAct.getActivityStatus());
											month02.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month02.setPlanDtlId(planDayAct.getPlanDtlId());
											month02.setPlanHdrId(planDayAct.getPlanHdrId());
											month02.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month02List.add(month02);
										}
										
										if(monthStart == 3 || (monthEnd >= 3 && monthStart <= 3 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month03 = new Int0101PlanDayVo();
											month03.setActivity(planDayAct.getActivity());
											month03.setActivityShort(planDayAct.getActivityShort());
											month03.setActivityStatus(planDayAct.getActivityStatus());
											month03.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month03.setPlanDtlId(planDayAct.getPlanDtlId());
											month03.setPlanHdrId(planDayAct.getPlanHdrId());
											month03.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month03List.add(month03);
										}
										
										if(monthStart == 4 || (monthEnd >= 4 && monthStart <= 4 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month04 = new Int0101PlanDayVo();
											month04.setActivity(planDayAct.getActivity());
											month04.setActivityShort(planDayAct.getActivityShort());
											month04.setActivityStatus(planDayAct.getActivityStatus());
											month04.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month04.setPlanDtlId(planDayAct.getPlanDtlId());
											month04.setPlanHdrId(planDayAct.getPlanHdrId());
											month04.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month04List.add(month04);
										}
										
										if(monthStart == 5 || (monthEnd >= 5 && monthStart <= 5 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month05 = new Int0101PlanDayVo();
											month05.setActivity(planDayAct.getActivity());
											month05.setActivityShort(planDayAct.getActivityShort());
											month05.setActivityStatus(planDayAct.getActivityStatus());
											month05.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month05.setPlanDtlId(planDayAct.getPlanDtlId());
											month05.setPlanHdrId(planDayAct.getPlanHdrId());
											month05.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month05List.add(month05);
										}
										
										if(monthStart == 6 || ( monthEnd >= 6 && monthStart <= 6 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month06 = new Int0101PlanDayVo();
											month06.setActivity(planDayAct.getActivity());
											month06.setActivityShort(planDayAct.getActivityShort());
											month06.setActivityStatus(planDayAct.getActivityStatus());
											month06.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month06.setPlanDtlId(planDayAct.getPlanDtlId());
											month06.setPlanHdrId(planDayAct.getPlanHdrId());
											month06.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month06List.add(month06);
										}
										
										if(monthStart == 7 || (monthEnd >= 7 && monthStart <= 7 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month07 = new Int0101PlanDayVo();
											month07.setActivity(planDayAct.getActivity());
											month07.setActivityShort(planDayAct.getActivityShort());
											month07.setActivityStatus(planDayAct.getActivityStatus());
											month07.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month07.setPlanDtlId(planDayAct.getPlanDtlId());
											month07.setPlanHdrId(planDayAct.getPlanHdrId());
											month07.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month07List.add(month07);
										}
										
										if(monthStart == 8 || (monthEnd >= 8 && monthStart <= 8 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month08 = new Int0101PlanDayVo();
											month08.setActivity(planDayAct.getActivity());
											month08.setActivityShort(planDayAct.getActivityShort());
											month08.setActivityStatus(planDayAct.getActivityStatus());
											month08.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month08.setPlanDtlId(planDayAct.getPlanDtlId());
											month08.setPlanHdrId(planDayAct.getPlanHdrId());
											month08.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month08List.add(month08);
										}
										
										if(monthStart == 9 || (monthEnd >= 9 && monthStart <= 9 && budgetYearEN == yearStart ) ) {
											Int0101PlanDayVo month09 = new Int0101PlanDayVo();
											month09.setActivity(planDayAct.getActivity());
											month09.setActivityShort(planDayAct.getActivityShort());
											month09.setActivityStatus(planDayAct.getActivityStatus());
											month09.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month09.setPlanDtlId(planDayAct.getPlanDtlId());
											month09.setPlanHdrId(planDayAct.getPlanHdrId());
											month09.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month09List.add(month09);
										}
										
									} else {
										
										if( monthStart == 10 ||  (monthEnd >= 10 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month10Y = new Int0101PlanDayVo();
											
											month10Y.setActivity(planDayAct.getActivity());
											month10Y.setActivityShort(planDayAct.getActivityShort());
											month10Y.setActivityStatus(planDayAct.getActivityStatus());
											month10Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month10Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month10Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month10Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month10List.add(month10Y);
										}
										
										if( monthStart == 11 || (monthEnd >= 11 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month11Y = new Int0101PlanDayVo();
											
											month11Y.setActivity(planDayAct.getActivity());
											month11Y.setActivityShort(planDayAct.getActivityShort());
											month11Y.setActivityStatus(planDayAct.getActivityStatus());
											month11Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month11Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month11Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month11Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month11List.add(month11Y);
										}
										
										if ( monthStart == 12 || monthEnd == 12 ) {
											Int0101PlanDayVo month12Y = new Int0101PlanDayVo();
											
											month12Y.setActivity(planDayAct.getActivity());
											month12Y.setActivityShort(planDayAct.getActivityShort());
											month12Y.setActivityStatus(planDayAct.getActivityStatus());
											month12Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month12Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month12Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month12Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month12List.add(month12Y);
										}
									}
									
								} else if (budgetYearEN > yearStart) {
									
									if(yearStart == yearEnd) {
										if( monthStart == 10 ||  (monthEnd >= 10 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month10 = new Int0101PlanDayVo();
											
											month10.setActivity(planDayAct.getActivity());
											month10.setActivityShort(planDayAct.getActivityShort());
											month10.setActivityStatus(planDayAct.getActivityStatus());
											month10.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month10.setPlanDtlId(planDayAct.getPlanDtlId());
											month10.setPlanHdrId(planDayAct.getPlanHdrId());
											month10.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month10List.add(month10);
										}
										
										if( monthStart == 11 || (monthEnd >= 11 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month11 = new Int0101PlanDayVo();
											
											month11.setActivity(planDayAct.getActivity());
											month11.setActivityShort(planDayAct.getActivityShort());
											month11.setActivityStatus(planDayAct.getActivityStatus());
											month11.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month11.setPlanDtlId(planDayAct.getPlanDtlId());
											month11.setPlanHdrId(planDayAct.getPlanHdrId());
											month11.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month11List.add(month11);
										}
										
										if ( monthStart == 12 || monthEnd == 12 ) {
											Int0101PlanDayVo month12 = new Int0101PlanDayVo();
											
											month12.setActivity(planDayAct.getActivity());
											month12.setActivityShort(planDayAct.getActivityShort());
											month12.setActivityStatus(planDayAct.getActivityStatus());
											month12.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month12.setPlanDtlId(planDayAct.getPlanDtlId());
											month12.setPlanHdrId(planDayAct.getPlanHdrId());
											month12.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month12List.add(month12);
										}
									} else {
										if(monthStart == 1 || monthEnd >= 1) {
											Int0101PlanDayVo month01 = new Int0101PlanDayVo();
											month01.setActivity(planDayAct.getActivity());
											month01.setActivityShort(planDayAct.getActivityShort());
											month01.setActivityStatus(planDayAct.getActivityStatus());
											month01.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month01.setPlanDtlId(planDayAct.getPlanDtlId());
											month01.setPlanHdrId(planDayAct.getPlanHdrId());
											month01.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month01List.add(month01);
										}
										
										if(monthStart == 2 || monthEnd >= 2) {
											Int0101PlanDayVo month02 = new Int0101PlanDayVo();
											month02.setActivity(planDayAct.getActivity());
											month02.setActivityShort(planDayAct.getActivityShort());
											month02.setActivityStatus(planDayAct.getActivityStatus());
											month02.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month02.setPlanDtlId(planDayAct.getPlanDtlId());
											month02.setPlanHdrId(planDayAct.getPlanHdrId());
											month02.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month02List.add(month02);
										}
										
										if(monthStart == 3 || monthEnd >= 3) {
											Int0101PlanDayVo month03 = new Int0101PlanDayVo();
											month03.setActivity(planDayAct.getActivity());
											month03.setActivityShort(planDayAct.getActivityShort());
											month03.setActivityStatus(planDayAct.getActivityStatus());
											month03.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month03.setPlanDtlId(planDayAct.getPlanDtlId());
											month03.setPlanHdrId(planDayAct.getPlanHdrId());
											month03.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month03List.add(month03);
										}
										
										if(monthStart == 4 || monthEnd >= 4) {
											Int0101PlanDayVo month04 = new Int0101PlanDayVo();
											month04.setActivity(planDayAct.getActivity());
											month04.setActivityShort(planDayAct.getActivityShort());
											month04.setActivityStatus(planDayAct.getActivityStatus());
											month04.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month04.setPlanDtlId(planDayAct.getPlanDtlId());
											month04.setPlanHdrId(planDayAct.getPlanHdrId());
											month04.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month04List.add(month04);
										}
										
										if(monthStart == 5 || monthEnd >= 5) {
											Int0101PlanDayVo month05 = new Int0101PlanDayVo();
											month05.setActivity(planDayAct.getActivity());
											month05.setActivityShort(planDayAct.getActivityShort());
											month05.setActivityStatus(planDayAct.getActivityStatus());
											month05.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month05.setPlanDtlId(planDayAct.getPlanDtlId());
											month05.setPlanHdrId(planDayAct.getPlanHdrId());
											month05.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month05List.add(month05);
										}
										
										if(monthStart == 6 || monthEnd >= 6) {
											Int0101PlanDayVo month06 = new Int0101PlanDayVo();
											month06.setActivity(planDayAct.getActivity());
											month06.setActivityShort(planDayAct.getActivityShort());
											month06.setActivityStatus(planDayAct.getActivityStatus());
											month06.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month06.setPlanDtlId(planDayAct.getPlanDtlId());
											month06.setPlanHdrId(planDayAct.getPlanHdrId());
											month06.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month06List.add(month06);
										}
										
										if(monthStart == 7 || monthEnd >= 7) {
											Int0101PlanDayVo month07 = new Int0101PlanDayVo();
											month07.setActivity(planDayAct.getActivity());
											month07.setActivityShort(planDayAct.getActivityShort());
											month07.setActivityStatus(planDayAct.getActivityStatus());
											month07.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month07.setPlanDtlId(planDayAct.getPlanDtlId());
											month07.setPlanHdrId(planDayAct.getPlanHdrId());
											month07.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month07List.add(month07);
										}
										
										if(monthStart == 8 || monthEnd >= 8) {
											Int0101PlanDayVo month08 = new Int0101PlanDayVo();
											month08.setActivity(planDayAct.getActivity());
											month08.setActivityShort(planDayAct.getActivityShort());
											month08.setActivityStatus(planDayAct.getActivityStatus());
											month08.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month08.setPlanDtlId(planDayAct.getPlanDtlId());
											month08.setPlanHdrId(planDayAct.getPlanHdrId());
											month08.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month08List.add(month08);
										}
										
										if(monthStart == 9 || monthEnd >= 9) {
											Int0101PlanDayVo month09 = new Int0101PlanDayVo();
											month09.setActivity(planDayAct.getActivity());
											month09.setActivityShort(planDayAct.getActivityShort());
											month09.setActivityStatus(planDayAct.getActivityStatus());
											month09.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month09.setPlanDtlId(planDayAct.getPlanDtlId());
											month09.setPlanHdrId(planDayAct.getPlanHdrId());
											month09.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month09List.add(month09);
										}
										if( monthStart == 10 ||  (monthEnd >= 10 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month10Y = new Int0101PlanDayVo();
											
											month10Y.setActivity(planDayAct.getActivity());
											month10Y.setActivityShort(planDayAct.getActivityShort());
											month10Y.setActivityStatus(planDayAct.getActivityStatus());
											month10Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month10Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month10Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month10Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month10List.add(month10Y);
										}
										
										if( monthStart == 11 || (monthEnd >= 11 && monthStart < monthEnd) ) {
											Int0101PlanDayVo month11Y = new Int0101PlanDayVo();
											
											month11Y.setActivity(planDayAct.getActivity());
											month11Y.setActivityShort(planDayAct.getActivityShort());
											month11Y.setActivityStatus(planDayAct.getActivityStatus());
											month11Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month11Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month11Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month11Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month11List.add(month11Y);
										}
										
										if ( monthStart == 12 || monthEnd == 12 ) {
											Int0101PlanDayVo month12Y = new Int0101PlanDayVo();
											
											month12Y.setActivity(planDayAct.getActivity());
											month12Y.setActivityShort(planDayAct.getActivityShort());
											month12Y.setActivityStatus(planDayAct.getActivityStatus());
											month12Y.setPlanDayActivityId(planDayAct.getPlanDayActivityId());
											month12Y.setPlanDtlId(planDayAct.getPlanDtlId());
											month12Y.setPlanHdrId(planDayAct.getPlanHdrId());
											month12Y.setColorCode(setColorCode(planDayAct.getActivityStatus()));
											
											month12List.add(month12Y);
										}
									}
								} else if (budgetYearEN < yearStart) {
									
								}
								
								
							}
							/* set month10List */
							monthVo.setMonth10(month10List);
							monthVo.setMonth11(month11List);
							monthVo.setMonth12(month12List);
							monthVo.setMonth01(month01List);
							monthVo.setMonth02(month02List);
							monthVo.setMonth03(month03List);
							monthVo.setMonth04(month04List);
							monthVo.setMonth05(month05List);
							monthVo.setMonth06(month06List);
							monthVo.setMonth07(month07List);
							monthVo.setMonth08(month08List);
							monthVo.setMonth09(month09List);
							monthVo.setMonth10Y(month10YList);
							monthVo.setMonth11Y(month11YList);

							/* set monthVo */
							objFilter.setMonthVo(monthVo);
						}
					}
					/* set detail */
					detail.setDetail(dataFilter);
				}
				detailList.add(detail);
			}
			response.setTableVo(detailList);
		}
		return response;
	}
	
	private String setColorCode(String status) {
		if ( IaConstants.PLAN_DAY_ACTIVITY_STATUS.PARAM_CODE_I.equals(status) ) {
			return IaConstants.PLAN_DAY_ACTIVITY_STATUS.VALUE_2_COLOR_I;
		} else if ( IaConstants.PLAN_DAY_ACTIVITY_STATUS.PARAM_CODE_II.equals(status) ) {
			return IaConstants.PLAN_DAY_ACTIVITY_STATUS.VALUE_2_COLOR_II;
		} else if ( IaConstants.PLAN_DAY_ACTIVITY_STATUS.PARAM_CODE_III.equals(status) ) {
			return IaConstants.PLAN_DAY_ACTIVITY_STATUS.VALUE_2_COLOR_III;
		} else {
			return "";
		}
	}

	public IaPlanHdr updateChoice(BigDecimal planHdrId, String flag) {
		IaPlanHdr dataHdr = null;
		IaPlanHdr response = null;
		
		if (planHdrId != null) {
			dataHdr = iaPlanHdrRepository.findById(planHdrId).get();
			if ("APPROVE".equals(flag)) {
				dataHdr.setStatus(IaConstants.PLAN_HDR_STATUS.STATUS_1_CODE);
			} else {
				dataHdr.setStatus(IaConstants.PLAN_HDR_STATUS.STATUS_2_CODE);
			}
			response = iaPlanHdrRepository.save(dataHdr);
		}
		return response;
	}
	
}
