package th.go.excise.ims.ta.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD8;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD8Repository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastYearVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;

@Service
public class BasicAnalysisIncomeCompareLastYearService extends AbstractBasicAnalysisService<BasicAnalysisIncomeCompareLastYearVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisIncomeCompareLastYearService.class);

	@Autowired
	private TaPaperBaD8Repository taPaperBaD8Repository;
	@Autowired
	private TaWsInc8000MRepository wsInc8000MRepository;

	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		// Initial
		int yearNum = Integer.parseInt(formVo.getYearNum());
		BasicAnalysisIncomeCompareLastYearVo vo = null;
		BigDecimal incomeCurrentYearAmt = null;
		BigDecimal incomeLastYearAmt = null;
		BigDecimal diffIncomeLastYearAmt = null;
		BigDecimal diffIncomeLastYearPnt = null;
		
		LocalDate localDateCurrentStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateCurrentEnd = toLocalDate(formVo.getEndDate());
		List<LocalDate> localDateCurrentList = LocalDateUtils.getLocalDateRange(localDateCurrentStart, localDateCurrentEnd);
		List<String> yyyyMMList = LocalDateUtils.getLocalDateRange(localDateCurrentStart.minus(yearNum, ChronoUnit.YEARS), localDateCurrentEnd)
			.stream()
			.map(e -> e.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)))
			.collect(Collectors.toList());
		Map<String, BigDecimal> incomeMap = wsInc8000MRepository.findByMonthRangePivot(formVo.getNewRegId(), formVo.getDutyGroupId(), yyyyMMList, formVo.getYearIncomeType());
		LocalDate tmpLocalDate = null;
		
		List<BasicAnalysisIncomeCompareLastYearVo> voList = new ArrayList<>();
		for (LocalDate localDate : localDateCurrentList) {
			vo = new BasicAnalysisIncomeCompareLastYearVo();
			vo.setTaxMonth(ThaiBuddhistDate.from(localDate).format(DateTimeFormatter.ofPattern(DATEFORMAT_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			
			// Current Year
			incomeCurrentYearAmt = incomeMap.get(localDate.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			if (incomeCurrentYearAmt != null) {
				vo.setIncomeCurrentYearAmt(incomeCurrentYearAmt.toString());
			} else {
				vo.setIncomeCurrentYearAmt(NO_VALUE);
				incomeCurrentYearAmt = BigDecimal.ZERO;
			}
			
			// Last Year
			for (int i = 1; i <= yearNum; i++) {
				tmpLocalDate = localDate.minusYears(i);
				
				// IncomeLastYearAmt
				incomeLastYearAmt = incomeMap.get(tmpLocalDate.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
				if (incomeLastYearAmt != null) {
					setLastYearValue(vo, "setIncomeLastYear" + i + "Amt", incomeLastYearAmt.toString());
				} else {
					setLastYearValue(vo, "setIncomeLastYear" + i + "Amt", NO_VALUE);
					incomeLastYearAmt = BigDecimal.ZERO;
				}
				
				// DiffIncomeLastYearAmt
				diffIncomeLastYearAmt = incomeCurrentYearAmt.subtract(incomeLastYearAmt);
				setLastYearValue(vo, "setDiffIncomeLastYear" + i + "Amt", diffIncomeLastYearAmt.toString());
				
				// DiffIncomeLastYearPnt
				diffIncomeLastYearPnt = NumberUtils.calculatePercent(incomeCurrentYearAmt, incomeLastYearAmt);
				setLastYearValue(vo, "setDiffIncomeLastYear" + i + "Pnt", diffIncomeLastYearPnt.toString());
			}
			
			voList.add(vo);
		}
		
		return voList;
	}
	
	private void setLastYearValue(BasicAnalysisIncomeCompareLastYearVo vo, String methodName, String value) {
		try {
			Method methodSetLastYearValue = BasicAnalysisIncomeCompareLastYearVo.class.getMethod(methodName, String.class);
			methodSetLastYearValue.invoke(vo, value);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD8> entityList = taPaperBaD8Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisIncomeCompareLastYearVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastYearVo vo = null;
		for (TaPaperBaD8 entity : entityList) {
			vo = new BasicAnalysisIncomeCompareLastYearVo();
			vo.setTaxMonth(entity.getTaxMonth());
			vo.setIncomeCurrentYearAmt(entity.getIncomeCurrentYearAmt() != null ? entity.getIncomeCurrentYearAmt().toString() : NO_VALUE);
			vo.setIncomeLastYear1Amt(entity.getIncomeLastYear1Amt() != null ? entity.getIncomeLastYear1Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear1Amt(entity.getDiffIncomeLastYear1Amt() != null ? entity.getDiffIncomeLastYear1Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear1Pnt(entity.getDiffIncomeLastYear1Pnt() != null ? entity.getDiffIncomeLastYear1Pnt().toString() : NO_VALUE);
			vo.setIncomeLastYear2Amt(entity.getIncomeLastYear2Amt() != null ? entity.getIncomeLastYear2Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear2Amt(entity.getDiffIncomeLastYear2Amt() != null ? entity.getDiffIncomeLastYear2Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear2Pnt(entity.getDiffIncomeLastYear2Pnt() != null ? entity.getDiffIncomeLastYear2Pnt().toString() : NO_VALUE);
			vo.setIncomeLastYear3Amt(entity.getIncomeLastYear3Amt() != null ? entity.getIncomeLastYear3Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear3Amt(entity.getDiffIncomeLastYear3Amt() != null ? entity.getDiffIncomeLastYear3Amt().toString() : NO_VALUE);
			vo.setDiffIncomeLastYear3Pnt(entity.getDiffIncomeLastYear3Pnt() != null ? entity.getDiffIncomeLastYear3Pnt().toString() : NO_VALUE);
			voList.add(vo);
		}
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisIncomeCompareLastYearVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD8> entityList = new ArrayList<>();
		TaPaperBaD8 entity = null;
		int i = 1;
		for (BasicAnalysisIncomeCompareLastYearVo vo : voList) {
			entity = new TaPaperBaD8();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxMonth(vo.getTaxMonth());
			entity.setIncomeCurrentYearAmt(!NO_VALUE.equals(vo.getIncomeCurrentYearAmt()) ? new BigDecimal((vo.getIncomeCurrentYearAmt()).replaceAll(",", "")) : null);
			entity.setIncomeLastYear1Amt(!NO_VALUE.equals(vo.getIncomeLastYear1Amt()) ? new BigDecimal((vo.getIncomeLastYear1Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear1Amt(!NO_VALUE.equals(vo.getDiffIncomeLastYear1Amt()) ? new BigDecimal((vo.getDiffIncomeLastYear1Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear1Pnt(!NO_VALUE.equals(vo.getDiffIncomeLastYear1Pnt()) ? new BigDecimal((vo.getDiffIncomeLastYear1Pnt()).replaceAll(",", "")) : null);
			entity.setIncomeLastYear2Amt(!NO_VALUE.equals(vo.getIncomeLastYear2Amt()) && vo.getIncomeLastYear2Amt() != null ? new BigDecimal((vo.getIncomeLastYear2Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear2Amt(!NO_VALUE.equals(vo.getDiffIncomeLastYear2Amt()) && vo.getDiffIncomeLastYear2Amt() != null ? new BigDecimal((vo.getDiffIncomeLastYear2Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear2Pnt(!NO_VALUE.equals(vo.getDiffIncomeLastYear2Pnt()) && vo.getDiffIncomeLastYear2Pnt() != null ? new BigDecimal((vo.getDiffIncomeLastYear2Pnt()).replaceAll(",", "")) : null);
			entity.setIncomeLastYear3Amt(!NO_VALUE.equals(vo.getIncomeLastYear3Amt()) && vo.getIncomeLastYear3Amt() != null ? new BigDecimal((vo.getIncomeLastYear3Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear3Amt(!NO_VALUE.equals(vo.getDiffIncomeLastYear3Amt()) && vo.getDiffIncomeLastYear3Amt() != null ? new BigDecimal((vo.getDiffIncomeLastYear3Amt()).replaceAll(",", "")) : null);
			entity.setDiffIncomeLastYear3Pnt(!NO_VALUE.equals(vo.getDiffIncomeLastYear3Pnt()) && vo.getDiffIncomeLastYear3Pnt() != null ? new BigDecimal((vo.getDiffIncomeLastYear3Pnt()).replaceAll(",", "")) : null);
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD8Repository.saveAll(entityList);
	}

	@Override
	protected JasperPrint getJasperPrint(BasicAnalysisFormVo formVo, TaxAuditDetailVo taxAuditDetailVo) throws Exception {
		logger.info("generateReport paperBaNumber={}", formVo.getPaperBaNumber());
		
		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("newRegId", formVo.getNewRegId());
		params.put("dutyGroupId", taxAuditDetailVo.getWsRegfri4000Vo().getRegDutyList().get(0).getGroupName());
		params.put("facFullname", taxAuditDetailVo.getWsRegfri4000Vo().getFacFullname());
		params.put("auJobResp", taxAuditDetailVo.getAuJobResp());
		params.put("paperBaNumber", formVo.getPaperBaNumber());
		params.put("startDate", formVo.getStartDate());
		params.put("endDate", formVo.getEndDate());
		params.put("yearIncType", formVo.getYearIncomeType());
		params.put("yearNum", formVo.getYearNum());
		params.put("commentText8", formVo.getCommentText8());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisIncomeCompareLastYearVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = null;
		if (StringUtils.equals("1", formVo.getYearNum())) {
			jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D8_Y1 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		} else if (StringUtils.equals("2", formVo.getYearNum())) {
			jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D8_Y2 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		} else {
			jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D8_Y3 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		}
		
		return jasperPrint;
	}

}
