package th.go.excise.ims.ta.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.constant.ProjectConstants.TAX_COMPARE_TYPE;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

public class TaxAuditUtils {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditUtils.class);

	public static List<TaxOperatorDatatableVo> prepareTaxOperatorDatatable(List<TaxOperatorDetailVo> taxOperatorDetailVoList, TaxOperatorFormVo formVo) {
		logger.info("prepareTaxOperatorDatatable");
		
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = new ArrayList<>();
		TaxOperatorDatatableVo taxOperatorDatatableVo = null;
		List<String> taxAmtList = null;
		for (TaxOperatorDetailVo taxOperatorDetailVo : taxOperatorDetailVoList) {
			taxOperatorDatatableVo = new TaxOperatorDatatableVo();
			taxOperatorDatatableVo.setCusFullname(taxOperatorDetailVo.getCusFullname());
			taxOperatorDatatableVo.setFacFullname(taxOperatorDetailVo.getFacFullname());
			taxOperatorDatatableVo.setFacAddress(taxOperatorDetailVo.getFacAddress());
			taxOperatorDatatableVo.setOfficeCode(taxOperatorDetailVo.getOfficeCode());
			taxOperatorDatatableVo.setSecCode(taxOperatorDetailVo.getSecCode());
			taxOperatorDatatableVo.setSecDesc(taxOperatorDetailVo.getSecDesc());
			taxOperatorDatatableVo.setAreaCode(taxOperatorDetailVo.getAreaCode());
			taxOperatorDatatableVo.setAreaDesc(taxOperatorDetailVo.getAreaDesc());
			taxOperatorDatatableVo.setWorksheetHdrId(taxOperatorDetailVo.getWorksheetHdrId());
			taxOperatorDatatableVo.setDraftNumber(taxOperatorDetailVo.getDraftNumber());
			taxOperatorDatatableVo.setNewRegId(taxOperatorDetailVo.getNewRegId());
			taxOperatorDatatableVo.setSumTaxAmtG1(taxOperatorDetailVo.getSumTaxAmtG1());
			taxOperatorDatatableVo.setSumTaxAmtG2(taxOperatorDetailVo.getSumTaxAmtG2());
			taxOperatorDatatableVo.setSumTotalTaxAmt(taxOperatorDetailVo.getSumTotalTaxAmt());
			taxOperatorDatatableVo.setTaxAmtChnPnt(taxOperatorDetailVo.getTaxAmtChnPnt());
			taxOperatorDatatableVo.setTaxAmtSd(taxOperatorDetailVo.getTaxAmtSd());
			taxOperatorDatatableVo.setTaxMonthNo(taxOperatorDetailVo.getTaxMonthNo());
			taxOperatorDatatableVo.setTaxAuditLast3(taxOperatorDetailVo.getTaxAuditLast3());
			taxOperatorDatatableVo.setTaxAuditLast2(taxOperatorDetailVo.getTaxAuditLast2());
			taxOperatorDatatableVo.setTaxAuditLast1(taxOperatorDetailVo.getTaxAuditLast1());
			taxOperatorDatatableVo.setOldRegId(taxOperatorDetailVo.getOldRegId());
			taxOperatorDatatableVo.setLastedStatus(taxOperatorDetailVo.getLastedStatus());
			taxOperatorDatatableVo.setRegStatus(taxOperatorDetailVo.getRegStatus());
			taxOperatorDatatableVo.setTaxAmtMean(taxOperatorDetailVo.getTaxAmtMean());
			taxOperatorDatatableVo.setTaxAmtMaxPnt(taxOperatorDetailVo.getTaxAmtMaxPnt());
			taxOperatorDatatableVo.setTaxAmtMinPnt(taxOperatorDetailVo.getTaxAmtMinPnt());
			taxOperatorDatatableVo.setDutyName(taxOperatorDetailVo.getDutyName());
			taxOperatorDatatableVo.setOtherDutyName(taxOperatorDetailVo.getOtherDutyName());
			taxOperatorDatatableVo.setSelectBy(taxOperatorDetailVo.getSelectBy());
			taxOperatorDatatableVo.setSelectByOfCode(taxOperatorDetailVo.getSelectByOfCode());
			
			taxOperatorDatatableVo.setCondTaxGrp(taxOperatorDetailVo.getCondTaxGrp());
			taxOperatorDatatableVo.setRegCapital(taxOperatorDetailVo.getRegCapital());
			taxOperatorDatatableVo.setCondSubCapital(taxOperatorDetailVo.getCondSubCapital());
			taxOperatorDatatableVo.setCondSubRisk(taxOperatorDetailVo.getCondSubRisk());
			taxOperatorDatatableVo.setCondSubNoAudit(taxOperatorDetailVo.getCondSubNoAudit());
			taxOperatorDatatableVo.setCondSubCapitalDesc(taxOperatorDetailVo.getCondSubCapitalDesc());
			taxOperatorDatatableVo.setCondSubRiskDesc(taxOperatorDetailVo.getCondSubRiskDesc());
			taxOperatorDatatableVo.setCondSubNoAuditDesc(taxOperatorDetailVo.getCondSubNoAuditDesc());
			taxOperatorDatatableVo.setLastAuditYear(taxOperatorDetailVo.getLastAuditYear());
			taxOperatorDatatableVo.setRiskLevel(taxOperatorDetailVo.getRiskLevel());
			taxOperatorDatatableVo.setRiskLevelDesc(taxOperatorDetailVo.getRiskLevelDesc());
			taxOperatorDatatableVo.setRegDate(taxOperatorDetailVo.getRegDate());
			taxOperatorDatatableVo.setCondG1(taxOperatorDetailVo.getCondG1());
			taxOperatorDatatableVo.setCondG2(taxOperatorDetailVo.getCondG2());
			taxOperatorDatatableVo.setCondG3(taxOperatorDetailVo.getCondG3());
			taxOperatorDatatableVo.setCondG4(taxOperatorDetailVo.getCondG4());
			taxOperatorDatatableVo.setCondG5(taxOperatorDetailVo.getCondG5());
			taxOperatorDatatableVo.setCondG6(taxOperatorDetailVo.getCondG6());
			taxOperatorDatatableVo.setCondRegDate(taxOperatorDetailVo.getCondRegDate());
			taxOperatorDatatableVo.setNotPayTaxMonthNo(taxOperatorDetailVo.getNotPayTaxMonthNo());
			taxOperatorDatatableVo.setMultiDutyFlag(taxOperatorDetailVo.getMultiDutyFlag());
			taxOperatorDatatableVo.setIncMultiDutyFlag(taxOperatorDetailVo.getIncMultiDutyFlag());
			taxOperatorDatatableVo.setSyncDate(taxOperatorDetailVo.getSyncDate());
			
			taxAmtList = new ArrayList<>();
			for (int i = 0; i < formVo.getDateRange(); i++) {
				taxAmtList.add(getTaxAmtByField(taxOperatorDetailVo, i, formVo.getDateRange()));
			}
			taxOperatorDatatableVo.setTaxAmtList(taxAmtList);

			taxOperatorDatatableVoList.add(taxOperatorDatatableVo);
		}

		return taxOperatorDatatableVoList;
	}

	private static String getTaxAmtByField(TaxOperatorDetailVo taxOperatorDetailVo, int i, int dataRange) {
		String taxAmt = "0.00";
		if (i < dataRange / 2) {
			if (i + 1 == 1) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M1();
			} else if (i + 1 == 2) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M2();
			} else if (i + 1 == 3) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M3();
			} else if (i + 1 == 4) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M4();
			} else if (i + 1 == 5) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M5();
			} else if (i + 1 == 6) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M6();
			} else if (i + 1 == 7) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M7();
			} else if (i + 1 == 8) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M8();
			} else if (i + 1 == 9) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M9();
			} else if (i + 1 == 10) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M10();
			} else if (i + 1 == 11) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M11();
			} else if (i + 1 == 12) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M12();
			} else if (i + 1 == 13) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M13();
			} else if (i + 1 == 14) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M14();
			} else if (i + 1 == 15) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M15();
			} else if (i + 1 == 16) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M16();
			} else if (i + 1 == 17) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M17();
			} else if (i + 1 == 18) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M18();
			}
		} else {
			if (i + 1 - (dataRange / 2) == 1) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M1();
			} else if (i + 1 - (dataRange / 2) == 2) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M2();
			} else if (i + 1 - (dataRange / 2) == 3) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M3();
			} else if (i + 1 - (dataRange / 2) == 4) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M4();
			} else if (i + 1 - (dataRange / 2) == 5) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M5();
			} else if (i + 1 - (dataRange / 2) == 6) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M6();
			} else if (i + 1 - (dataRange / 2) == 7) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M7();
			} else if (i + 1 - (dataRange / 2) == 8) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M8();
			} else if (i + 1 - (dataRange / 2) == 9) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M9();
			} else if (i + 1 - (dataRange / 2) == 10) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M10();
			} else if (i + 1 - (dataRange / 2) == 11) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M11();
			} else if (i + 1 - (dataRange / 2) == 12) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M12();
			} else if (i + 1 - (dataRange / 2) == 13) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M13();
			} else if (i + 1 - (dataRange / 2) == 14) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M14();
			} else if (i + 1 - (dataRange / 2) == 15) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M15();
			} else if (i + 1 - (dataRange / 2) == 16) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M16();
			} else if (i + 1 - (dataRange / 2) == 17) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M17();
			} else if (i + 1 - (dataRange / 2) == 18) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M18();
			}
		}

		return taxAmt;
	}

	public static void commonSelectionWorksheetRowMapper(TaxOperatorDetailVo vo, ResultSet rs) throws SQLException {
		vo.setNewRegId(rs.getString("NEW_REG_ID"));
		vo.setCusFullname(rs.getString("CUS_FULLNAME"));
		vo.setFacFullname(rs.getString("FAC_FULLNAME"));
		vo.setFacAddress(rs.getString("FAC_ADDRESS"));
		vo.setOfficeCode(rs.getString("OFFICE_CODE_R4000"));
		vo.setSecCode(rs.getString("SEC_CODE"));
		vo.setSecDesc(rs.getString("SEC_DESC"));
		vo.setAreaCode(rs.getString("AREA_CODE"));
		vo.setAreaDesc(rs.getString("AREA_DESC"));

		vo.setTaxAuditLast1(rs.getString("TAX_AUDIT_LAST1"));
		vo.setTaxAuditLast2(rs.getString("TAX_AUDIT_LAST2"));
		vo.setTaxAuditLast3(rs.getString("TAX_AUDIT_LAST3"));

		vo.setSumTaxAmtG1(rs.getString("SUM_TAX_AMT_G1"));
		vo.setSumTaxAmtG2(rs.getString("SUM_TAX_AMT_G2"));
		vo.setTaxAmtChnPnt(rs.getString("TAX_AMT_CHN_PNT"));
		vo.setTaxMonthNo(rs.getString("TAX_MONTH_NO"));
		vo.setTaxAmtG1M1(rs.getString("TAX_AMT_G1_M1"));
		vo.setTaxAmtG1M2(rs.getString("TAX_AMT_G1_M2"));
		vo.setTaxAmtG1M3(rs.getString("TAX_AMT_G1_M3"));
		vo.setTaxAmtG1M4(rs.getString("TAX_AMT_G1_M4"));
		vo.setTaxAmtG1M5(rs.getString("TAX_AMT_G1_M5"));
		vo.setTaxAmtG1M6(rs.getString("TAX_AMT_G1_M6"));
		vo.setTaxAmtG1M7(rs.getString("TAX_AMT_G1_M7"));
		vo.setTaxAmtG1M8(rs.getString("TAX_AMT_G1_M8"));
		vo.setTaxAmtG1M9(rs.getString("TAX_AMT_G1_M9"));
		vo.setTaxAmtG1M10(rs.getString("TAX_AMT_G1_M10"));
		vo.setTaxAmtG1M11(rs.getString("TAX_AMT_G1_M11"));
		vo.setTaxAmtG1M12(rs.getString("TAX_AMT_G1_M12"));
		vo.setTaxAmtG1M13(rs.getString("TAX_AMT_G1_M13"));
		vo.setTaxAmtG1M14(rs.getString("TAX_AMT_G1_M14"));
		vo.setTaxAmtG1M15(rs.getString("TAX_AMT_G1_M15"));
		vo.setTaxAmtG1M16(rs.getString("TAX_AMT_G1_M16"));
		vo.setTaxAmtG1M17(rs.getString("TAX_AMT_G1_M17"));
		vo.setTaxAmtG1M18(rs.getString("TAX_AMT_G1_M18"));
		vo.setTaxAmtG2M1(rs.getString("TAX_AMT_G2_M1"));
		vo.setTaxAmtG2M2(rs.getString("TAX_AMT_G2_M2"));
		vo.setTaxAmtG2M3(rs.getString("TAX_AMT_G2_M3"));
		vo.setTaxAmtG2M4(rs.getString("TAX_AMT_G2_M4"));
		vo.setTaxAmtG2M5(rs.getString("TAX_AMT_G2_M5"));
		vo.setTaxAmtG2M6(rs.getString("TAX_AMT_G2_M6"));
		vo.setTaxAmtG2M7(rs.getString("TAX_AMT_G2_M7"));
		vo.setTaxAmtG2M8(rs.getString("TAX_AMT_G2_M8"));
		vo.setTaxAmtG2M9(rs.getString("TAX_AMT_G2_M9"));
		vo.setTaxAmtG2M10(rs.getString("TAX_AMT_G2_M10"));
		vo.setTaxAmtG2M11(rs.getString("TAX_AMT_G2_M11"));
		vo.setTaxAmtG2M12(rs.getString("TAX_AMT_G2_M12"));
		vo.setTaxAmtG2M13(rs.getString("TAX_AMT_G2_M13"));
		vo.setTaxAmtG2M14(rs.getString("TAX_AMT_G2_M14"));
		vo.setTaxAmtG2M15(rs.getString("TAX_AMT_G2_M15"));
		vo.setTaxAmtG2M16(rs.getString("TAX_AMT_G2_M16"));
		vo.setTaxAmtG2M17(rs.getString("TAX_AMT_G2_M17"));
		vo.setTaxAmtG2M18(rs.getString("TAX_AMT_G2_M18"));
		vo.setTaxAmtSd(rs.getString("TAX_AMT_SD"));
		vo.setTaxAmtMean(rs.getString("TAX_AMT_MEAN"));
		vo.setTaxAmtMaxPnt(rs.getString("TAX_AMT_MAX_PNT"));
		vo.setTaxAmtMinPnt(rs.getString("TAX_AMT_MIN_PNT"));
		vo.setDutyCode(rs.getString("DUTY_GROUP_ID"));
		vo.setDutyName(rs.getString("DUTY_GROUP_NAME"));
	}

	public static List<ExciseDepartment> getExciseSectorList() {
		List<ExciseDepartment> taSectorList = new ArrayList<>();
		taSectorList.add(ApplicationCache.getExciseDepartment(EXCISE_OFFICE_CODE.TA_CENTRAL));
		List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
		for (ExciseDepartment exciseDept : sectorList) {
			if (EXCISE_OFFICE_CODE.CENTRAL.equals(exciseDept.getOfficeCode())) {
				continue;
			}
			taSectorList.add(exciseDept);
		}

		return taSectorList;
	}
	
	/**
	 * 
	 * @param dateStart format MM/yyyy in ThaiBuddhist
	 * @param dateEnd format MM/yyyy in ThaiBuddhist
	 * @param dateRange
	 * @param compType
	 * @return
	 */
	public static WorksheetDateRangeVo getWorksheetDateRangeVo(String dateStart, String dateEnd, int dateRange, String compType) {
		logger.debug("getWorksheetDateRangeVo dateStart={}, dateEnd={}, dateRange={}, compType={}", dateStart, dateEnd, dateRange, compType);
		
		WorksheetDateRangeVo vo = new WorksheetDateRangeVo();
		LocalDate localDateG1Start = null;
		LocalDate localDateG1End = null;
		LocalDate localDateG2Start = null;
		LocalDate localDateG2End = null;
		if (TAX_COMPARE_TYPE.HALF.equals(compType)) {
			int range = (dateRange / 2) - 1;
			localDateG1Start = LocalDateUtils.thaiMonthYear2LocalDate(dateEnd);
			localDateG1End = LocalDateUtils.thaiMonthYear2LocalDate(dateStart);
			localDateG2Start = localDateG1End.minus(1, ChronoUnit.MONTHS);
			localDateG2End = localDateG2Start.minus(range, ChronoUnit.MONTHS);
		} else {
			localDateG1Start = LocalDateUtils.thaiMonthYear2LocalDate(dateEnd);
			localDateG1End = LocalDateUtils.thaiMonthYear2LocalDate(dateStart);
			localDateG2Start = localDateG1Start.minus(1, ChronoUnit.YEARS);
			localDateG2End = localDateG1End.minus(1, ChronoUnit.YEARS);
		}
		vo.setYmG1StartInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		vo.setYmG1EndInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		vo.setYmG2StartInc8000M(localDateG2End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		vo.setYmG2EndInc8000M(localDateG2Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		List<LocalDate> subLocalDateG1List = LocalDateUtils.getLocalDateRange(localDateG1End, localDateG1Start);
		List<LocalDate> subLocalDateG2List = LocalDateUtils.getLocalDateRange(localDateG2End, localDateG2Start);
		Collections.reverse(subLocalDateG1List);
		Collections.reverse(subLocalDateG2List);
		vo.setSubLocalDateG1List(subLocalDateG1List);
		vo.setSubLocalDateG2List(subLocalDateG2List);
		logger.debug("localDateG1Start={}", localDateG1Start);
		logger.debug("localDateG1End  ={}", localDateG1End);
		logger.debug("localDateG2Start={}", localDateG2Start);
		logger.debug("localDateG2End  ={}", localDateG2End);
		logger.debug("taxCompareType={}, ymG1StartInc8000M={}, ymG1EndInc8000M={}, ymG2StartInc8000M={}, ymG2EndInc8000M={}", compType, vo.getYmG1StartInc8000M(), vo.getYmG1EndInc8000M(), vo.getYmG2StartInc8000M(), vo.getYmG2EndInc8000M());
		logger.debug("subLocalDateList1.size()={}, subLocalDateList1={}", vo.getSubLocalDateG1List().size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(vo.getSubLocalDateG1List()));
		logger.debug("subLocalDateList2.size()={}, subLocalDateList2={}", vo.getSubLocalDateG2List().size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(vo.getSubLocalDateG2List()));
		
		return vo;
	}
	
	public static String getTaxCompareType(int monthNum) {
    	final int MONTH_NUM = 24;
    	if (monthNum >= MONTH_NUM) {
    		return TAX_COMPARE_TYPE.HALF;
    	} else {
    		return TAX_COMPARE_TYPE.MONTH;
    	}
    }

}
