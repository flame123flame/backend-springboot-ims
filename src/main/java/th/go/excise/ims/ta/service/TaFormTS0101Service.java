package th.go.excise.ims.ta.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0101;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0101Repository;
import th.go.excise.ims.ta.vo.TaFormTS0101Vo;

@Service
public class TaFormTS0101Service extends AbstractTaFormTSService<TaFormTS0101Vo, TaFormTs0101> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0101Service.class);

	@Autowired
	private TaFormTs0101Repository taFormTs0101Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0101Repository;
	}
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_01;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0101Vo formTS0101Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0101Vo);
		byte[] reportFile = generateReport(formTS0101Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0101Vo formTS0101Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0101Vo.getFormTsNumber());

		// Set Data
		TaFormTs0101 formTs0101 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0101Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0101Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0101Vo.getFormTsNumber();
			
			formTs0101 = taFormTs0101Repository.findByFormTsNumber(formTS0101Vo.getFormTsNumber());
			toEntity(formTs0101, formTS0101Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTs0101 = new TaFormTs0101();
			toEntity(formTs0101, formTS0101Vo);
			formTs0101.setOfficeCode(officeCode);
			formTs0101.setBudgetYear(budgetYear);
			formTs0101.setFormTsNumber(formTsNumber);
		}
		taFormTs0101Repository.save(formTs0101);
		
		saveAuditStep(formTS0101Vo, TaFormTS0101Vo.class, TA_FORM_TS_CODE.TS0101, formTsNumber);		
	}

	@Override
	public byte[] generateReport(TaFormTS0101Vo formTS0101Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0101Vo.getFormTsNumber());
		params.put("newRegId", formTS0101Vo.getNewRegId());
		params.put("factoryName", formTS0101Vo.getFactoryName());
		params.put("factoryTypeText", formTS0101Vo.getFactoryTypeText());
		params.put("factoryAddress", formTS0101Vo.getFactoryAddress());
		params.put("analysisDateStart", formTS0101Vo.getAnalysisDateStart());
		params.put("analysisDateEnd", formTS0101Vo.getAnalysisDateEnd());
		params.put("analysisData1", formTS0101Vo.getAnalysisData1());
		params.put("analysisData2", formTS0101Vo.getAnalysisData2());
		params.put("analysisData3", formTS0101Vo.getAnalysisData3());
		params.put("analysisData4", formTS0101Vo.getAnalysisData4());
		params.put("analysisData5", formTS0101Vo.getAnalysisData5());
		params.put("analysisResultDear", formTS0101Vo.getAnalysisResultDear());
		params.put("analysisResultText", formTS0101Vo.getAnalysisResultText());
		params.put("callAuditFlag", formTS0101Vo.getCallAuditFlag());
		params.put("otherText", formTS0101Vo.getOtherText());
		params.put("signOfficerFullName", formTS0101Vo.getSignOfficerFullName());
		params.put("signSupOfficerFullName", formTS0101Vo.getSignSupOfficerFullName());
		params.put("signOfficerDate", formTS0101Vo.getSignOfficerDate());
		params.put("approvedFlag", formTS0101Vo.getApprovedFlag());
		params.put("signApprOfficerFullName", formTS0101Vo.getSignApprOfficerFullName());
		params.put("signApprOfficerPosition", formTS0101Vo.getSignApprOfficerPosition());
		params.put("signApprDate", formTS0101Vo.getSignApprDate());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_01 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
	
	@Override
	public TaFormTS0101Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTs0101 formTs0101 = taFormTs0101Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS0101Vo formTs0101Vo = new TaFormTS0101Vo();
		toVo(formTs0101Vo, formTs0101);
		
		return formTs0101Vo;
	}

}
