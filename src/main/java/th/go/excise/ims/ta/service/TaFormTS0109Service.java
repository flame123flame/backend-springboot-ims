package th.go.excise.ims.ta.service;

import java.io.IOException;
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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0109;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0109Repository;
import th.go.excise.ims.ta.vo.TaFormTS0109Vo;

@Service
public class TaFormTS0109Service extends AbstractTaFormTSService<TaFormTS0109Vo, TaFormTs0109> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0109Service.class);

	@Autowired
	private TaFormTs0109Repository taFormTs0109Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0109Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_09;
	}

	@Override
	public byte[] processFormTS(TaFormTS0109Vo taFormTS0109Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(taFormTS0109Vo);
		byte[] reportFile = generateReport(taFormTS0109Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0109Vo formTS0109Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0109Vo.getFormTsNumber());

		TaFormTs0109 formTS0109 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0109Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0109Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0109Vo.getFormTsNumber();

			formTS0109 = taFormTs0109Repository.findByFormTsNumber(formTS0109Vo.getFormTsNumber());
			toEntity(formTS0109, formTS0109Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			formTS0109 = new TaFormTs0109();
			toEntity(formTS0109, formTS0109Vo);
			formTS0109.setOfficeCode(officeCode);
			formTS0109.setBudgetYear(budgetYear);
			formTS0109.setFormTsNumber(formTsNumber);
		}
		taFormTs0109Repository.save(formTS0109);

		saveAuditStep(formTS0109Vo, TaFormTS0109Vo.class, TA_FORM_TS_CODE.TS0109, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0109Vo formTS0109Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0109Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0109Vo.getBookNumber1());
		params.put("bookNumber2", formTS0109Vo.getBookNumber2());
		params.put("comdPlace", formTS0109Vo.getComdPlace());
		params.put("docDate", formTS0109Vo.getDocDate());
		params.put("evidenceReason", formTS0109Vo.getEvidenceReason());
		params.put("newRegId", formTS0109Vo.getNewRegId());
		params.put("docText1", formTS0109Vo.getDocText1());
		params.put("docText2", formTS0109Vo.getDocText2());
		params.put("docText3", formTS0109Vo.getDocText3());
		params.put("headOfficerFullName", formTS0109Vo.getHeadOfficerFullName());
		params.put("headOfficerPosition", formTS0109Vo.getHeadOfficerPosition());
		params.put("officerText", formTS0109Vo.getOfficerText());
		params.put("searchPlace", formTS0109Vo.getSearchPlace());
		params.put("searchDate", formTS0109Vo.getSearchDate());
		params.put("signOfficerFullName", formTS0109Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0109Vo.getSignOfficerPosition());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_09 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0109Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0109 formTs0109 = taFormTs0109Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0109Vo formTs0109Vo = new TaFormTS0109Vo();
		toVo(formTs0109Vo, formTs0109);

		return formTs0109Vo;
	}

}
