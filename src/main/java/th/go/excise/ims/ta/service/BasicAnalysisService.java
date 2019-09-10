package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaHRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;

@Service
public class BasicAnalysisService {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisService.class);
	
	private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;
	private PaperSequenceService paperSequenceService;
	private TaPaperBaHRepository taPaperBaHRepository;
	private TaxAuditService taxAuditService;
	
	private Map<String, AbstractBasicAnalysisService> basicAnalysisServiceMap = new LinkedHashMap<>();
	
	@Autowired
	public BasicAnalysisService(
			BasicAnalysisTaxQtyService basicAnalysisTaxQtyService,
			BasicAnalysisTaxRetailPriceService basicAnalysisTaxRetailPriceService,
			BasicAnalysisTaxValueService basicAnalysisTaxValueService,
			BasicAnalysisTaxRateService basicAnalysisTaxRateService,
			BasicAnalysisTaxAmtService basicAnalysisTaxAmtService,
			BasicAnalysisTaxFilingService basicAnalysisTaxFilingService,
			BasicAnalysisIncomeCompareLastMonthService basicAnalysisIncomeCompareLastMonthService,
			BasicAnalysisIncomeCompareLastYearService basicAnalysisIncomeCompareLastYearService,
			TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository,
			PaperSequenceService paperSequenceService,
			TaPaperBaHRepository taPaperBaHRepository,
			TaxAuditService taxAuditService) {
		basicAnalysisServiceMap.put("tax-qty", basicAnalysisTaxQtyService);
		basicAnalysisServiceMap.put("tax-retail-price", basicAnalysisTaxRetailPriceService);
		basicAnalysisServiceMap.put("tax-value", basicAnalysisTaxValueService);
		basicAnalysisServiceMap.put("tax-rate", basicAnalysisTaxRateService);
		basicAnalysisServiceMap.put("tax-amt", basicAnalysisTaxAmtService);
		basicAnalysisServiceMap.put("tax-filing", basicAnalysisTaxFilingService);
		basicAnalysisServiceMap.put("income-compare-last-month", basicAnalysisIncomeCompareLastMonthService);
		basicAnalysisServiceMap.put("income-compare-last-year", basicAnalysisIncomeCompareLastYearService);
		this.taPlanWorksheetDtlRepository = taPlanWorksheetDtlRepository;
		this.paperSequenceService = paperSequenceService;
		this.taPaperBaHRepository = taPaperBaHRepository;
		this.taxAuditService = taxAuditService;
	}
	
	public DataTableAjax<Object> inquiry(String analysisType, BasicAnalysisFormVo formVo) {
		AbstractBasicAnalysisService<Object> service = basicAnalysisServiceMap.get(analysisType);
		List<Object> voList = service.inquiry(formVo);
		
		DataTableAjax<Object> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(voList);

		return dataTableAjax;
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void save(BasicAnalysisFormVo formVo) {
		logger.info("save");
		
		TaPaperBaH paperBaH = taPaperBaHRepository.findByPaperBaNumber(formVo.getPaperBaNumber());
		if (paperBaH == null) {
			TaPlanWorksheetDtl planWorksheeetDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
			
			String paperBaNumber = paperSequenceService.getBasicAnalysisNumber(planWorksheeetDtl.getOfficeCode(), planWorksheeetDtl.getBudgetYear());
			LocalDate localDateStart = LocalDateUtils.thaiMonthYear2LocalDate(formVo.getStartDate());
			LocalDate localDateEnd = LocalDateUtils.thaiMonthYear2LocalDate(formVo.getEndDate());
			
			paperBaH = new TaPaperBaH();
			paperBaH.setOfficeCode(planWorksheeetDtl.getOfficeCode());
			paperBaH.setBudgetYear(planWorksheeetDtl.getBudgetYear());
			paperBaH.setPlanNumber(planWorksheeetDtl.getPlanNumber());
			paperBaH.setAuditPlanCode(formVo.getAuditPlanCode());
			paperBaH.setPaperBaNumber(paperBaNumber);
			paperBaH.setNewRegId(formVo.getNewRegId());
			paperBaH.setDutyGroupId(formVo.getDutyGroupId());
			paperBaH.setStartDate(localDateStart);
			paperBaH.setEndDate(localDateEnd);
			paperBaH.setMonthIncType(formVo.getMonthIncomeType());
			paperBaH.setYearIncType(formVo.getYearIncomeType());
			paperBaH.setYearNum(Integer.parseInt(formVo.getYearNum()));
			paperBaH.setAnaResultText1(formVo.getCommentText1());
			paperBaH.setAnaResultText2(formVo.getCommentText2());
			paperBaH.setAnaResultText3(formVo.getCommentText3());
			paperBaH.setAnaResultText4(formVo.getCommentText4());
			paperBaH.setAnaResultText5(formVo.getCommentText5());
			paperBaH.setAnaResultText6(formVo.getCommentText6());
			paperBaH.setAnaResultText7(formVo.getCommentText7());
			paperBaH.setAnaResultText8(formVo.getCommentText8());
			
			formVo.setPaperBaNumber(paperBaNumber);
			for (AbstractBasicAnalysisService service : basicAnalysisServiceMap.values()) {
				service.save(formVo);
			}
		} else {
			paperBaH.setAnaResultText1(formVo.getCommentText1());
			paperBaH.setAnaResultText2(formVo.getCommentText2());
			paperBaH.setAnaResultText3(formVo.getCommentText3());
			paperBaH.setAnaResultText4(formVo.getCommentText4());
			paperBaH.setAnaResultText5(formVo.getCommentText5());
			paperBaH.setAnaResultText6(formVo.getCommentText6());
			paperBaH.setAnaResultText7(formVo.getCommentText7());
			paperBaH.setAnaResultText8(formVo.getCommentText8());
		}
		
		taPaperBaHRepository.save(paperBaH);
	}

	public List<String> getPaperBaNumberList(BasicAnalysisFormVo formVo) {
		return taPaperBaHRepository.findPaperBaNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
	public BasicAnalysisFormVo getPaperBaHeader(BasicAnalysisFormVo formVo) {
		logger.info("getPaperBaHeader paperBaNumber={}", formVo.getPaperBaNumber());
		
		TaPaperBaH paperBaH = taPaperBaHRepository.findByPaperBaNumber(formVo.getPaperBaNumber());
		if (paperBaH != null) {
			formVo.setAuditPlanCode(paperBaH.getAuditPlanCode());
			formVo.setNewRegId(paperBaH.getNewRegId());
			formVo.setDutyGroupId(paperBaH.getDutyGroupId());
			formVo.setStartDate(ThaiBuddhistDate.from(paperBaH.getStartDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH)));
			formVo.setEndDate(ThaiBuddhistDate.from(paperBaH.getEndDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH)));
			formVo.setMonthIncomeType(paperBaH.getMonthIncType());
			formVo.setYearIncomeType(paperBaH.getYearIncType());
			formVo.setYearNum(String.valueOf(paperBaH.getYearNum()));
			formVo.setCommentText1(paperBaH.getAnaResultText1());
			formVo.setCommentText2(paperBaH.getAnaResultText2());
			formVo.setCommentText3(paperBaH.getAnaResultText3());
			formVo.setCommentText4(paperBaH.getAnaResultText4());
			formVo.setCommentText5(paperBaH.getAnaResultText5());
			formVo.setCommentText6(paperBaH.getAnaResultText6());
			formVo.setCommentText7(paperBaH.getAnaResultText7());
			formVo.setCommentText8(paperBaH.getAnaResultText8());
		}
		
		return formVo;
	}
	
	public byte[] generateReport(String paperBaNumber) throws Exception {
		logger.info("generateReport paperBaNumber={}", paperBaNumber);
		
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setPaperBaNumber(paperBaNumber);
		formVo = getPaperBaHeader(formVo);
		
		TaxAuditDetailFormVo taxAuditDetailFormVo = new TaxAuditDetailFormVo();
		taxAuditDetailFormVo.setAuditPlanCode(formVo.getAuditPlanCode());
		TaxAuditDetailVo taxAuditDetailVo = taxAuditService.getOperatorDetailsByAuditPlanCode(taxAuditDetailFormVo);
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		JasperPrint jasperPrint = null;
		byte[] content = null;
		for (AbstractBasicAnalysisService service : basicAnalysisServiceMap.values()) {
			jasperPrint = service.getJasperPrint(formVo, taxAuditDetailVo);
			items.add(new SimpleExporterInputItem(jasperPrint));
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.exportReport();
			
			content = outputStream.toByteArray();
		}
		
		return content;
	}
	
}
