package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0117;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0117Repository;
import th.go.excise.ims.ta.vo.TaFormTS0117Vo;

@Service
public class TaFormTS0117Service extends AbstractTaFormTSService<TaFormTS0117Vo, TaFormTs0117> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0117Service.class);

	@Autowired
	private TaFormTs0117Repository taFormTs0117Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0117Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_17;
	}

	@Override
	public byte[] processFormTS(TaFormTS0117Vo formTS0117Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0117Vo);
		byte[] reportFile = generateReport(formTS0117Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0117Vo formTS0117Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0117Vo.getFormTsNumber());

		// Set Data
		TaFormTs0117 formTs0117 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0117Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0117Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0117Vo.getFormTsNumber();
			
			formTs0117 = taFormTs0117Repository.findByFormTsNumber(formTS0117Vo.getFormTsNumber());
			toEntity(formTs0117, formTS0117Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTs0117 = new TaFormTs0117();
			toEntity(formTs0117, formTS0117Vo);
			formTs0117.setOfficeCode(officeCode);
			formTs0117.setBudgetYear(budgetYear);
			formTs0117.setFormTsNumber(formTsNumber);
		}

		taFormTs0117Repository.save(formTs0117);
		saveAuditStep(formTS0117Vo, TaFormTS0117Vo.class, TA_FORM_TS_CODE.TS0117, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0117Vo formTS0117Vo) throws IOException, JRException {
		logger.info("generateReport");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0117Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0117Vo.getBookNumber1());
		params.put("bookNumber2", formTS0117Vo.getBookNumber2());
		params.put("docTopic", formTS0117Vo.getDocTopic());
		params.put("docDate", formTS0117Vo.getDocDate());
		params.put("docDear", formTS0117Vo.getDocDear());
		params.put("refBookNumber1", formTS0117Vo.getRefBookNumber1());
		params.put("refBookNumber2", formTS0117Vo.getRefBookNumber2());
		params.put("refDocDate", formTS0117Vo.getRefDocDate());
		params.put("auditDate", formTS0117Vo.getAuditDate());
		params.put("callBookNumber1", formTS0117Vo.getCallBookNumber1());
		params.put("callBookNumber2", formTS0117Vo.getCallBookNumber2());
		params.put("callBookDate", formTS0117Vo.getCallBookDate());
		params.put("factoryName", formTS0117Vo.getFactoryName());
		params.put("newRegId", formTS0117Vo.getNewRegId());
		params.put("facAddrNo", formTS0117Vo.getFacAddrNo());
		params.put("facMooNo", formTS0117Vo.getFacMooNo());
		params.put("facSoiName", formTS0117Vo.getFacSoiName());
		params.put("facThnName", formTS0117Vo.getFacThnName());
		params.put("facTambolName", formTS0117Vo.getFacTambolName());
		params.put("facAmphurName", formTS0117Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0117Vo.getFacProvinceName());
		params.put("facZipCode", formTS0117Vo.getFacZipCode());
		params.put("officerFullName", formTS0117Vo.getOfficerFullName());
		params.put("officerPosition", formTS0117Vo.getOfficerPosition());
		params.put("taxFormDateStart", formTS0117Vo.getTaxFormDateStart());
		params.put("taxFormDateEnd", formTS0117Vo.getTaxFormDateEnd());
		params.put("testimonyDate", formTS0117Vo.getTestimonyDate());
		params.put("factDesc", formTS0117Vo.getFactDesc());
		params.put("lawDesc", formTS0117Vo.getLawDesc());
		params.put("factoryName2", formTS0117Vo.getFactoryName2());
		params.put("taxAmt", formTS0117Vo.getTaxAmt());
		params.put("fineAmt", formTS0117Vo.getFineAmt());
		params.put("extraAmt", formTS0117Vo.getExtraAmt());
		params.put("exciseTaxAmt", formTS0117Vo.getExciseTaxAmt());
		params.put("moiAmt", formTS0117Vo.getMoiAmt());
		params.put("sumAllTaxAmt", formTS0117Vo.getSumAllTaxAmt());
		params.put("extraDate", formTS0117Vo.getExtraDate());
		params.put("paymentDest", formTS0117Vo.getPaymentDest());
		params.put("paymentExciseTaxAmt", formTS0117Vo.getPaymentExciseTaxAmt());
		params.put("paymentDate", formTS0117Vo.getPaymentDate());
		params.put("officeDest", formTS0117Vo.getOfficeDest());
		params.put("officeDate", formTS0117Vo.getOfficeDate());
		params.put("officeTime", formTS0117Vo.getOfficeTime());
		params.put("signOfficerFullName", formTS0117Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0117Vo.getSignOfficerPosition());
		params.put("officeName", formTS0117Vo.getOfficeName());
		params.put("officePhone", formTS0117Vo.getOfficePhone());
		params.put("headOfficerFullName", formTS0117Vo.getHeadOfficerFullName());

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_17 + "." + FILE_EXTENSION.JASPER, params);
		JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_17P2 + "." + FILE_EXTENSION.JASPER, params);

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0117Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0117 formTs0117 = taFormTs0117Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0117Vo formTs0117Vo = new TaFormTS0117Vo();
		toVo(formTs0117Vo, formTs0117);

		return formTs0117Vo;
	}

}
