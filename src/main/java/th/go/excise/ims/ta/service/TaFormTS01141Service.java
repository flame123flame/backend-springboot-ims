package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
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

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01141;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01141Repository;
import th.go.excise.ims.ta.vo.TaFormTS01141Vo;

@Service
public class TaFormTS01141Service extends AbstractTaFormTSService<TaFormTS01141Vo, TaFormTs01141> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS01141Service.class);

	@Autowired
	private TaFormTs01141Repository taFormTs01141Repository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs01141Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_14_1;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("processFormTS");
		
		saveFormTS(formTS01141Vo);
		byte[] reportFile = generateReport(formTS01141Vo);
		
		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS01141Vo formTS01141Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01141Vo.getFormTsNumber());
		
		// Set Data
		TaFormTs01141 formTs01141 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS01141Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS01141Vo.getFormTsNumber())) {
			formTsNumber = formTS01141Vo.getFormTsNumber();
			// Case Update FormTS
			List<TaFormTs01141> taFormTs01141List = taFormTs01141Repository.findByFormTsNumber(formTS01141Vo.getFormTsNumber());
			
			// Update isDeleted = 'Y' for Default
			for (TaFormTs01141 taFormTs01141 : taFormTs01141List) {
				if (Integer.parseInt(taFormTs01141.getPageNo()) >= 1) {
					taFormTs01141.setIsDeleted(FLAG.Y_FLAG);
				}
			}
			
			// Set Main Record
			formTs01141 = getEntityByPageNo(taFormTs01141List, "0");
			toEntity(formTs01141, formTS01141Vo);
			
			// Set Sub Record
			if (formTS01141Vo.getTaFormTS01141VoList() != null) {
				for (TaFormTS01141Vo subFormTS01141Vo : formTS01141Vo.getTaFormTS01141VoList()) {
					formTs01141 = getEntityByPageNo(taFormTs01141List, subFormTS01141Vo.getPageNo());
					if (formTs01141 != null) {
						// Exist Page
						formTs01141.setAuditDesc(subFormTS01141Vo.getAuditDesc());
						formTs01141.setIsDeleted(FLAG.N_FLAG);
					} else {
						// New Page
						formTs01141 = new TaFormTs01141();
						toEntity(formTs01141, subFormTS01141Vo);
						formTs01141.setOfficeCode(officeCode);
						formTs01141.setBudgetYear(budgetYear);
						formTs01141.setFormTsNumber(formTS01141Vo.getFormTsNumber());
						taFormTs01141List.add(formTs01141);
					}
				}
			}
			
			taFormTs01141Repository.saveAll(taFormTs01141List);
			
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			List<TaFormTs01141> formTs01141List = new ArrayList<>();
			
			// Set Main Record
			formTs01141 = new TaFormTs01141();
			toEntity(formTs01141, formTS01141Vo);
			formTs01141.setOfficeCode(officeCode);
			formTs01141.setBudgetYear(budgetYear);
			formTs01141.setFormTsNumber(formTsNumber);
			formTs01141List.add(formTs01141);
			
			// Set Sub Record
			if (formTS01141Vo.getTaFormTS01141VoList() != null) {
				for (TaFormTS01141Vo subFormTS01141Vo : formTS01141Vo.getTaFormTS01141VoList()) {
					formTs01141 = new TaFormTs01141();
					toEntity(formTs01141, subFormTS01141Vo);
					formTs01141.setOfficeCode(officeCode);
					formTs01141.setBudgetYear(budgetYear);
					formTs01141.setFormTsNumber(formTsNumber);
					formTs01141List.add(formTs01141);
				}
			}
			
			taFormTs01141Repository.saveAll(formTs01141List);
		}
		
		saveAuditStep(formTS01141Vo, TaFormTS01141Vo.class, TA_FORM_TS_CODE.TS01141, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("generateReport");
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		JasperPrint jasperPrint = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS01141Vo.getFormTsNumber());
		params.put("docDate", formTS01141Vo.getDocDate());
		params.put("docDear", formTS01141Vo.getDocDear());
		params.put("factoryName", formTS01141Vo.getFactoryName());
		params.put("factoryTypeText", formTS01141Vo.getFactoryTypeText());
		params.put("auditDateStart", formTS01141Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS01141Vo.getAuditDateEnd());
		params.put("auditDesc", formTS01141Vo.getAuditDesc());
		
		jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1 + "." + FILE_EXTENSION.JASPER, params);
		items.add(new SimpleExporterInputItem(jasperPrint));
		
		if (formTS01141Vo.getTaFormTS01141VoList() != null) {
			Map<String, Object> subParams = null;
			for (TaFormTS01141Vo subFormTS01141Vo : formTS01141Vo.getTaFormTS01141VoList()) {
				subParams = new HashMap<>();
				subParams.put("formTsNumber", formTS01141Vo.getFormTsNumber());
				subParams.put("pageNo", subFormTS01141Vo.getPageNo());
				subParams.put("auditDesc", subFormTS01141Vo.getAuditDesc());
				jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1P2 + "." + FILE_EXTENSION.JASPER, subParams);
				items.add(new SimpleExporterInputItem(jasperPrint));
			}
		}

		// set output
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
	public TaFormTS01141Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTS01141Vo formTS01141Vo = new TaFormTS01141Vo();
		TaFormTS01141Vo subFormTS01141Vo = null;
		List<TaFormTS01141Vo> formTS01141VoList = new ArrayList<>();
		
		List<TaFormTs01141> taFormTs01141List = taFormTs01141Repository.findByFormTsNumber(formTsNumber);
		for (TaFormTs01141 taFormTs01141 : taFormTs01141List) {
			if ("0".equals(taFormTs01141.getPageNo())) {
				// Main Page
				toVo(formTS01141Vo, taFormTs01141);
			} else {
				// Sub Page
				subFormTS01141Vo = new TaFormTS01141Vo();
				toVo(subFormTS01141Vo, taFormTs01141);
				formTS01141VoList.add(subFormTS01141Vo);
			}
		}
		
		// Sorting
		formTS01141VoList.sort((p1, p2) -> Integer.parseInt(p1.getPageNo()) - Integer.parseInt(p2.getPageNo()));
		
		formTS01141Vo.setTaFormTS01141VoList(formTS01141VoList);
		
		return formTS01141Vo;
	}
	
	private TaFormTs01141 getEntityByPageNo(List<TaFormTs01141> formTs01141List, String pageNo) {
		TaFormTs01141 formTs01141 = null;
		for (TaFormTs01141 taFormTs01141 : formTs01141List) {
			if (pageNo.equals(taFormTs01141.getPageNo())) {
				formTs01141 = taFormTs01141;
				break;
			}
		}
		return formTs01141;
	}

}
