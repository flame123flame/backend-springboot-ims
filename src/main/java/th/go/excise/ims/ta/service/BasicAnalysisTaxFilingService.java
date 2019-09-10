package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.preferences.persistence.repository.ExciseDuedatePs0112Repository;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD6;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD6Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxFilingVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxFilingService extends AbstractBasicAnalysisService<BasicAnalysisTaxFilingVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxFilingService.class);
	
	@Autowired
	private TaPaperBaD6Repository taPaperBaD6Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	@Autowired
	private ExciseDuedatePs0112Repository exciseDuedatePs0112Repository;

	@Override
	protected List<BasicAnalysisTaxFilingVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		ThaiBuddhistDate localDateStart = ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1);
		ThaiBuddhistDate localDateEnd = ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1);
		String monthStart = localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM", ConvertDateUtils.LOCAL_TH));
		String monthEnd = localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM", ConvertDateUtils.LOCAL_TH));
		String dateStart = LocalDate.from(localDateStart).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = LocalDate.from(localDateEnd).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		Map<String, LocalDate> duedateMap = exciseDuedatePs0112Repository.findByMonthRange(monthStart, monthEnd);
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		Map<String, LocalDate> filingMap = convertToFilingMap(anafri0001VoList);
		
		List<LocalDate> localDateList = LocalDateUtils.getLocalDateRange(LocalDate.from(localDateStart), LocalDate.from(localDateEnd));
		
		List<BasicAnalysisTaxFilingVo> voList = new ArrayList<>();
		BasicAnalysisTaxFilingVo vo = null;
		String yearMonth = null;
		LocalDate duedateLocalDate = null;
		LocalDate anaLocalDate = null;
		for (LocalDate localDate : localDateList) {
			vo = new BasicAnalysisTaxFilingVo();
			vo.setTaxMonth(ThaiBuddhistDate.from(localDate).format(DateTimeFormatter.ofPattern(DATEFORMAT_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			vo.setTaxMonthLocalDate(localDate);
			yearMonth = ThaiBuddhistDate.from(localDate).format(DateTimeFormatter.ofPattern("yyyyMM", ConvertDateUtils.LOCAL_TH));
			duedateLocalDate = duedateMap.get(yearMonth);
			if (duedateLocalDate != null) {
				vo.setTaxSubmissionDate(ThaiBuddhistDate.from(duedateLocalDate).format(DateTimeFormatter.ofPattern(DATEFORMAT_DD_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
				vo.setTaxSubmissionLocalDate(duedateLocalDate);
			} else {
				vo.setTaxSubmissionDate(NO_VALUE);
			}
			anaLocalDate = filingMap.get(yearMonth);
			if (anaLocalDate != null) {
				vo.setAnaTaxSubmissionDate(ThaiBuddhistDate.from(anaLocalDate).format(DateTimeFormatter.ofPattern(DATEFORMAT_DD_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
				vo.setAnaTaxSubmissionLocalDate(anaLocalDate);
			} else {
				vo.setAnaTaxSubmissionDate(NO_VALUE);
			}
			if (duedateLocalDate != null && anaLocalDate != null) {
				if (anaLocalDate.isEqual(duedateLocalDate) || anaLocalDate.isBefore(duedateLocalDate)) {
					vo.setResultTaxSubmission(FLAG.Y_FLAG);
				} else {
					vo.setResultTaxSubmission(FLAG.N_FLAG);
				}
			} else {
				vo.setResultTaxSubmission(NO_VALUE);
			}
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxFilingVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD6> entityList = taPaperBaD6Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxFilingVo> voList = new ArrayList<>();
		BasicAnalysisTaxFilingVo vo = null;
		for (TaPaperBaD6 entity : entityList) {
			vo = new BasicAnalysisTaxFilingVo();
			vo.setTaxMonth(ThaiBuddhistDate.from(LocalDate.parse(entity.getTaxMonth() + "01", DateTimeFormatter.BASIC_ISO_DATE)).format(DateTimeFormatter.ofPattern(DATEFORMAT_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			if (entity.getTaxSubmissionDate() != null) {
				vo.setTaxSubmissionDate(ThaiBuddhistDate.from(entity.getTaxSubmissionDate()).format(DateTimeFormatter.ofPattern(DATEFORMAT_DD_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			} else {
				vo.setTaxSubmissionDate(NO_VALUE);
			}
			if (entity.getAnaTaxSubmissionDate() != null) {
				vo.setAnaTaxSubmissionDate(ThaiBuddhistDate.from(entity.getAnaTaxSubmissionDate()).format(DateTimeFormatter.ofPattern(DATEFORMAT_DD_MMMM_YYYY, ConvertDateUtils.LOCAL_TH)));
			} else {
				vo.setAnaTaxSubmissionDate(NO_VALUE);
			}
			vo.setResultTaxSubmission(entity.getResultTaxSubmission());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxFilingVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD6> entityList = new ArrayList<>();
		TaPaperBaD6 entity = null;
		int i = 1;
		for (BasicAnalysisTaxFilingVo vo : voList) {
			entity = new TaPaperBaD6();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxMonth(vo.getTaxMonthLocalDate().format(DateTimeFormatter.ofPattern("yyyyMM", Locale.US)));
			entity.setTaxSubmissionDate(vo.getTaxSubmissionLocalDate());
			entity.setAnaTaxSubmissionDate(vo.getAnaTaxSubmissionLocalDate());
			entity.setResultTaxSubmission(vo.getResultTaxSubmission());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD6Repository.saveAll(entityList);
	}
	
	private Map<String, LocalDate> convertToFilingMap(List<WsAnafri0001Vo> anafri0001VoList) {
		Map<String, LocalDate> filingMap = new HashMap<>();
		for (WsAnafri0001Vo wsAnafri0001Vo : anafri0001VoList) {
			filingMap.put(wsAnafri0001Vo.getRegInDate().format(DateTimeFormatter.ofPattern("yyyyMM")), wsAnafri0001Vo.getRegInDate());
		}
		
		return filingMap;
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
		params.put("commentText6", formVo.getCommentText6());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxFilingVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D6 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
