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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0110;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0110Repository;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Service
public class TaFormTS0110Service extends AbstractTaFormTSService<TaFormTS0110Vo, TaFormTs0110> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0110Service.class);

	@Autowired
	private TaFormTs0110Repository taFormTs0110Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0110Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_10;
	}

	@Override
	public byte[] processFormTS(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0110Vo);
		byte[] reportFile = generateReport(formTS0110Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0110Vo formTS0110Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0110Vo.getFormTsNumber());
		
		TaFormTs0110 formTs0110 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0110Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0110Vo.getFormTsNumber())) {
			formTsNumber = formTS0110Vo.getFormTsNumber();
			
			// Case Update FormTS
			List<TaFormTs0110> taFormTs0110List = taFormTs0110Repository.findByFormTsNumber(formTS0110Vo.getFormTsNumber());
			
			// Update isDeleted = 'Y' for Default
			for (TaFormTs0110 taFormTs0110 : taFormTs0110List) {
				if (Integer.parseInt(taFormTs0110.getTestimonyPageNo()) >= 1) {
					taFormTs0110.setIsDeleted(FLAG.Y_FLAG);
				}
			}
			
			// Set Main Record
			formTs0110 = getEntityByPageNo(taFormTs0110List, "0");
			toEntity(formTs0110, formTS0110Vo);
			
			// Set Sub Record
			if (formTS0110Vo.getTaFormTS0110VoList() != null) {
				for (TaFormTS0110Vo subFormTS0110Vo : formTS0110Vo.getTaFormTS0110VoList()) {
					formTs0110 = getEntityByPageNo(taFormTs0110List, subFormTS0110Vo.getTestimonyPageNo());
					if (formTs0110 != null) {
						// Exist Page
						formTs0110.setTestimonyOf(formTS0110Vo.getTestimonyOf());
						formTs0110.setTestimonyText(subFormTS0110Vo.getTestimonyText());
						formTs0110.setAuditPlanCode(formTS0110Vo.getAuditPlanCode());
						formTs0110.setIsDeleted(FLAG.N_FLAG);
					} else {
						// New Page
						formTs0110 = new TaFormTs0110();
						toEntity(formTs0110, subFormTS0110Vo);
						formTs0110.setOfficeCode(officeCode);
						formTs0110.setBudgetYear(budgetYear);
						formTs0110.setFormTsNumber(formTS0110Vo.getFormTsNumber());
						formTs0110.setAuditPlanCode(formTS0110Vo.getAuditPlanCode());
						taFormTs0110List.add(formTs0110);
					}
				}
			}
			
			taFormTs0110Repository.saveAll(taFormTs0110List);
			
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			List<TaFormTs0110> formTs0110List = new ArrayList<>();
			
			// Set Main Record
			formTs0110 = new TaFormTs0110();
			toEntity(formTs0110, formTS0110Vo);
			formTs0110.setOfficeCode(officeCode);
			formTs0110.setBudgetYear(budgetYear);
			formTs0110.setFormTsNumber(formTsNumber);
			formTs0110List.add(formTs0110);
			
			// Set Sub Record
			if (formTS0110Vo.getTaFormTS0110VoList() != null) {
				for (TaFormTS0110Vo subFormTS0110Vo : formTS0110Vo.getTaFormTS0110VoList()) {
					formTs0110 = new TaFormTs0110();
					toEntity(formTs0110, subFormTS0110Vo);
					formTs0110.setTestimonyOf(formTS0110Vo.getTestimonyOf());
					formTs0110.setOfficeCode(officeCode);
					formTs0110.setBudgetYear(budgetYear);
					formTs0110.setFormTsNumber(formTsNumber);
					formTs0110.setAuditPlanCode(formTS0110Vo.getAuditPlanCode());
					formTs0110List.add(formTs0110);
				}
			}
			
			taFormTs0110Repository.saveAll(formTs0110List);
		}
		
		saveAuditStep(formTS0110Vo, TaFormTS0110Vo.class, TA_FORM_TS_CODE.TS0110, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("export exportTaFormTS0110");

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		JasperPrint jasperPrint = null;

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0110Vo.getFormTsNumber());
		params.put("testimonyOf", formTS0110Vo.getTestimonyOf());
		params.put("testimonyTopic", formTS0110Vo.getTestimonyTopic());
		params.put("docDate", formTS0110Vo.getDocDate());
		params.put("officerFullName", formTS0110Vo.getOfficerFullName());
		params.put("officerPosition", formTS0110Vo.getOfficerPosition());
		params.put("testimonyFullName", formTS0110Vo.getTestimonyFullName());
		params.put("testimonyAge", formTS0110Vo.getTestimonyAge());
		params.put("testimonyNationality", formTS0110Vo.getTestimonyNationality());
		params.put("testimonyRace", formTS0110Vo.getTestimonyRace());
		params.put("testimonyAddrNo", formTS0110Vo.getTestimonyAddrNo());
		params.put("testimonyBuildNameVillage", formTS0110Vo.getTestimonyBuildNameVillage());
		params.put("testimonyFloorNo", formTS0110Vo.getTestimonyFloorNo());
		params.put("testimonyRoomNo", formTS0110Vo.getTestimonyRoomNo());
		params.put("testimonySoiName", formTS0110Vo.getTestimonySoiName());
		params.put("testimonyThnName", formTS0110Vo.getTestimonyThnName());
		params.put("testimonyTambolName", formTS0110Vo.getTestimonyTambolName());
		params.put("testimonyAmphurName", formTS0110Vo.getTestimonyAmphurName());
		params.put("testimonyProvinceName", formTS0110Vo.getTestimonyProvinceName());
		params.put("testimonyZipCode", formTS0110Vo.getTestimonyZipCode());
		params.put("testimonyTelNo", formTS0110Vo.getTestimonyTelNo());
		params.put("testimonyCardType", formTS0110Vo.getTestimonyCardType());
		params.put("testimonyCardOtherDesc", formTS0110Vo.getTestimonyCardOtherDesc());
		params.put("testimonyCardNo", formTS0110Vo.getTestimonyCardNo());
		params.put("testimonyCardSource", formTS0110Vo.getTestimonyCardSource());
		params.put("testimonyCardCountry", formTS0110Vo.getTestimonyCardCountry());
		params.put("testimonyPosition", formTS0110Vo.getTestimonyPosition());
		params.put("testimonyFactoryFullName", formTS0110Vo.getTestimonyFactoryFullName());
		params.put("newRegId", formTS0110Vo.getNewRegId());
		params.put("testimonyText", formTS0110Vo.getTestimonyText());

		// set output
		jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10 + "." + FILE_EXTENSION.JASPER, params);
		items.add(new SimpleExporterInputItem(jasperPrint));

		// add report TA_FORM_TS01_10_1
		if (formTS0110Vo.getTaFormTS0110VoList() != null) {
			Map<String, Object> subParams = null;
			for (TaFormTS0110Vo subFormTS0110Vo : formTS0110Vo.getTaFormTS0110VoList()) {
				subParams = new HashMap<>();
				subParams.put("formTsNumber", formTS0110Vo.getFormTsNumber());
				subParams.put("testimonyPageNo", subFormTS0110Vo.getTestimonyPageNo());
				subParams.put("testimonyOf", formTS0110Vo.getTestimonyOf());
				subParams.put("testimonyText", subFormTS0110Vo.getTestimonyText());
				jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10_1 + "." + FILE_EXTENSION.JASPER,
						subParams);
				items.add(new SimpleExporterInputItem(jasperPrint));
			}
		}

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
	public TaFormTS0110Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTS0110Vo formTS0110Vo = new TaFormTS0110Vo();
		TaFormTS0110Vo subFormTS0110Vo = null;
		List<TaFormTS0110Vo> formTS0110VoList = new ArrayList<>();

		List<TaFormTs0110> taFormTs0110List = taFormTs0110Repository.findByFormTsNumber(formTsNumber);
		for (TaFormTs0110 taFormTs0110 : taFormTs0110List) {
			if ("0".equals(taFormTs0110.getTestimonyPageNo())) {
				// Main Page
				toVo(formTS0110Vo, taFormTs0110);
			} else {
				// Sub Page
				subFormTS0110Vo = new TaFormTS0110Vo();
				toVo(subFormTS0110Vo, taFormTs0110);
				formTS0110VoList.add(subFormTS0110Vo);
			}
		}
		
		// Sorting
		formTS0110VoList.sort((p1, p2) -> Integer.parseInt(p1.getTestimonyPageNo()) - Integer.parseInt(p2.getTestimonyPageNo()));
		
		formTS0110Vo.setTaFormTS0110VoList(formTS0110VoList);
		
		return formTS0110Vo;
	}
	
	private TaFormTs0110 getEntityByPageNo(List<TaFormTs0110> formTs0110List, String pageNo) {
		TaFormTs0110 formTs0110 = null;
		for (TaFormTs0110 taFormTs0110 : formTs0110List) {
			if (pageNo.equals(taFormTs0110.getTestimonyPageNo())) {
				formTs0110 = taFormTs0110;
				break;
			}
		}
		return formTs0110;
	}

}