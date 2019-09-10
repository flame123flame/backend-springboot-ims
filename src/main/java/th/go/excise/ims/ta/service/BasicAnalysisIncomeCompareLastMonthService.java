package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD7;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD7Repository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

@Service
public class BasicAnalysisIncomeCompareLastMonthService extends AbstractBasicAnalysisService<BasicAnalysisIncomeCompareLastMonthVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisIncomeCompareLastMonthService.class);

	@Autowired
	private TaPaperBaD7Repository taPaperBaD7Repository;
	@Autowired
	private TaWsInc8000MRepository wsInc8000MRepository;

	@Override
	protected List<BasicAnalysisIncomeCompareLastMonthVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateG1Start = toLocalDate(formVo.getStartDate());
		LocalDate localDateG1End = toLocalDate(formVo.getEndDate());
		LocalDate localDateG2Start = LocalDate.from(localDateG1Start);
		LocalDate localDateG2End = LocalDate.from(localDateG1End);
		localDateG1End = localDateG1End.plusMonths(1);
		List<LocalDate> subLocalDateG1List = LocalDateUtils.getLocalDateRange(localDateG1Start, localDateG1End);
		List<LocalDate> subLocalDateG2List = LocalDateUtils.getLocalDateRange(localDateG2Start, localDateG2End);
		subLocalDateG2List.add(0, null);

		WorksheetDateRangeVo dateRangeVo = new WorksheetDateRangeVo();
		dateRangeVo.setYmG1StartInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG1EndInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG2StartInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG2EndInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));

		logger.debug("localDateG1Start={}", localDateG1Start);
		logger.debug("localDateG1End  ={}", localDateG1End);
		logger.debug("localDateG2Start={}", localDateG2Start);
		logger.debug("localDateG2End  ={}", localDateG2End);
		logger.debug("ymG1StartInc8000M={}, ymG1EndInc8000M={}, ymG2StartInc8000M={}, ymG2EndInc8000M={}", dateRangeVo.getYmG1StartInc8000M(), dateRangeVo.getYmG1EndInc8000M(), dateRangeVo.getYmG2StartInc8000M(), dateRangeVo.getYmG2EndInc8000M());
		logger.debug("subLocalDateList1.size()={}, subLocalDateList1={}", subLocalDateG1List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG1List));
		logger.debug("subLocalDateList2.size()={}, subLocalDateList2={}", subLocalDateG2List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG2List));

		Map<String, BigDecimal> incomeMap = wsInc8000MRepository.findByMonthRangeDuty(formVo.getNewRegId(), formVo.getDutyGroupId(), dateRangeVo, formVo.getMonthIncomeType());

		List<BasicAnalysisIncomeCompareLastMonthVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastMonthVo vo = null;
		BigDecimal incomeCurrentMonthAmt = null;
		BigDecimal incomePreviousMonthAmt = null;
		BigDecimal diffIncomeAmt = null;
		BigDecimal diffIncomePnt = null;
		int dateLength = subLocalDateG1List.size();
		for (int i = 0; i < dateLength; i++) {
			vo = new BasicAnalysisIncomeCompareLastMonthVo();
			incomeCurrentMonthAmt = incomeMap.get(subLocalDateG1List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			if (incomeCurrentMonthAmt == null) {
				incomeCurrentMonthAmt = BigDecimal.ZERO;
				vo.setIncomeAmt(NO_VALUE);
			} else {
				vo.setIncomeAmt(incomeCurrentMonthAmt.toString());
			}
			if (i > 0) {
				incomePreviousMonthAmt = incomeMap.get(subLocalDateG2List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
				if (incomePreviousMonthAmt == null) {
					incomePreviousMonthAmt = BigDecimal.ZERO;
				}
			} else {
				incomePreviousMonthAmt = BigDecimal.ZERO;
			}
			diffIncomeAmt = incomeCurrentMonthAmt.subtract(incomePreviousMonthAmt);
			diffIncomePnt = NumberUtils.calculatePercent(incomeCurrentMonthAmt, incomePreviousMonthAmt);

			vo.setTaxMonth(ThaiBuddhistDate.from(subLocalDateG1List.get(i)).format(DateTimeFormatter.ofPattern(DATEFORMAT_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			vo.setDiffIncomeAmt(i == 0 ? NO_VALUE : diffIncomeAmt.toString());
			vo.setDiffIncomePnt(i == 0 ? NO_VALUE : diffIncomePnt.toString());
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<BasicAnalysisIncomeCompareLastMonthVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD7> entityList = taPaperBaD7Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisIncomeCompareLastMonthVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastMonthVo vo = null;
		for (TaPaperBaD7 entity : entityList) {
			vo = new BasicAnalysisIncomeCompareLastMonthVo();
			vo.setTaxMonth(entity.getTaxMonth());
			vo.setIncomeAmt(entity.getIncomeAmt() != null ? entity.getIncomeAmt().toString() : NO_VALUE);
			vo.setDiffIncomeAmt(entity.getDiffIncomeAmt() != null ? entity.getDiffIncomeAmt().toString() : NO_VALUE);
			vo.setDiffIncomePnt(entity.getDiffIncomePnt() != null ? entity.getDiffIncomePnt().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisIncomeCompareLastMonthVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD7> entityList = new ArrayList<>();
		TaPaperBaD7 entity = null;
		int i = 1;
		for (BasicAnalysisIncomeCompareLastMonthVo vo : voList) {
			entity = new TaPaperBaD7();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxMonth(vo.getTaxMonth());
			entity.setIncomeAmt(NO_VALUE.equals(vo.getIncomeAmt()) ? null : NumberUtils.toBigDecimal(vo.getIncomeAmt()));
			entity.setDiffIncomeAmt(NO_VALUE.equals(vo.getDiffIncomeAmt()) ? null : NumberUtils.toBigDecimal(vo.getDiffIncomeAmt()));
			entity.setDiffIncomePnt(NO_VALUE.equals(vo.getDiffIncomePnt()) ? null : NumberUtils.toBigDecimal(vo.getDiffIncomePnt()));
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD7Repository.saveAll(entityList);
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
		params.put("monthIncType", formVo.getMonthIncomeType());
		params.put("commentText7", formVo.getCommentText7());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisIncomeCompareLastMonthVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D7 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}
	
}
