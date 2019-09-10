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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0104;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0104Repository;
import th.go.excise.ims.ta.vo.TaFormTS0104Vo;

@Service
public class TaFormTS0104Service extends AbstractTaFormTSService<TaFormTS0104Vo, TaFormTs0104> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0104Service.class);

	@Autowired
	private TaFormTs0104Repository taFormTs0104Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0104Repository;
	}
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_04;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0104Vo formTS0104Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0104Vo);
		byte[] reportFile = generateReport(formTS0104Vo);

		return reportFile;

	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0104Vo formTS0104Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0104Vo.getFormTsNumber());

		// Set Data
		TaFormTs0104 formTs0104 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0104Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0104Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0104Vo.getFormTsNumber();
						
			formTs0104 = taFormTs0104Repository.findByFormTsNumber(formTS0104Vo.getFormTsNumber());
			toEntity(formTs0104, formTS0104Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			formTs0104 = new TaFormTs0104();
			toEntity(formTs0104, formTS0104Vo);
			formTs0104.setOfficeCode(officeCode);
			formTs0104.setBudgetYear(budgetYear);
			formTs0104.setFormTsNumber(formTsNumber);
		}
		taFormTs0104Repository.save(formTs0104);
		
		saveAuditStep(formTS0104Vo, TaFormTS0104Vo.class, TA_FORM_TS_CODE.TS0104, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0104Vo formTS0104Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0104Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0104Vo.getBookNumber1());
		params.put("bookNumber2", formTS0104Vo.getBookNumber2());
		params.put("subject1", formTS0104Vo.getSubject1());
		params.put("subject2", formTS0104Vo.getSubject2());
		params.put("docDate", formTS0104Vo.getDocDate());
		params.put("docTopic", formTS0104Vo.getDocTopic());
		params.put("docDear", formTS0104Vo.getDocDear());
		params.put("docReference", formTS0104Vo.getDocReference());
		params.put("docRequire", formTS0104Vo.getDocRequire());
		params.put("destText", formTS0104Vo.getDestText());
		params.put("destDate", formTS0104Vo.getDestDate());
		params.put("destTime", formTS0104Vo.getDestTime());
		params.put("docPaper", formTS0104Vo.getDocPaper());
		params.put("signOfficerFullName", formTS0104Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0104Vo.getSignOfficerPosition());
		params.put("otherText", formTS0104Vo.getOtherText());
		params.put("otherPhone", formTS0104Vo.getOtherPhone());
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_04 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0104Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0104 formTs0104 = taFormTs0104Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0104Vo formTs0104Vo = new TaFormTS0104Vo();
		toVo(formTs0104Vo, formTs0104);

		return formTs0104Vo;
	}

}
