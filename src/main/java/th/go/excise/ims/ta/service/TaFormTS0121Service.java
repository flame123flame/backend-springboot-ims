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
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0121;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0121Repository;
import th.go.excise.ims.ta.vo.TaFormTS0121Vo;

@Service
public class TaFormTS0121Service extends AbstractTaFormTSService<TaFormTS0121Vo, TaFormTs0121> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0121Service.class);

	@Autowired
	private TaFormTs0121Repository taFormTs0121Repository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0121Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_21;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0121Vo formTS0121Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0121Vo);
		byte[] reportFile = generateReport(formTS0121Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0121Vo formTS0121Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0121Vo.getFormTsNumber());

		TaFormTs0121 formTS0121 = null;
		if (StringUtils.isNotBlank(formTS0121Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0121Vo.getFormTsNumber())) {
			formTS0121 = taFormTs0121Repository.findByFormTsNumber(formTS0121Vo.getFormTsNumber());
			toEntity(formTS0121, formTS0121Vo);
		} else {
			formTS0121 = new TaFormTs0121();
			toEntity(formTS0121, formTS0121Vo);
			formTS0121.setOfficeCode(officeCode);
			formTS0121.setBudgetYear(budgetYear);
			formTS0121.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0121Repository.save(formTS0121);
	}

	@Override
	public byte[] generateReport(TaFormTS0121Vo formTS0121Vo) throws Exception, IOException {
		logger.info("generateReport");
		
		// get data to report
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0121Vo.getFormTsNumber());
		params.put("factoryName", formTS0121Vo.getFactoryName());
		params.put("officerSendFullName1", formTS0121Vo.getOfficerSendFullName1());
		params.put("officerSendPosition1", formTS0121Vo.getOfficerSendPosition1());
		params.put("officerReceiveFullName1", formTS0121Vo.getOfficerReceiveFullName1());
		params.put("officerReceivePosition1", formTS0121Vo.getOfficerReceivePosition1());
		params.put("officeName", formTS0121Vo.getOfficeName());
		params.put("docDate", formTS0121Vo.getDocDate());
		params.put("comdDesc", formTS0121Vo.getComdDesc());
		params.put("comdDate", formTS0121Vo.getComdDate());
		params.put("officerSendFullName2", formTS0121Vo.getOfficerSendFullName2());
		params.put("factoryName2", formTS0121Vo.getFactoryName2());
		params.put("officerReceiveFullName2", formTS0121Vo.getOfficerReceiveFullName2());
		params.put("officerSendFullName3", formTS0121Vo.getOfficerSendFullName3());
		params.put("officerReceiveFullName3", formTS0121Vo.getOfficerReceiveFullName3());
		params.put("factoryName3", formTS0121Vo.getFactoryName3());
		params.put("doc1Num", formTS0121Vo.getDoc1Num());
		params.put("docAcct1Num", formTS0121Vo.getDocAcct1Num());
		params.put("docAcct1No", formTS0121Vo.getDocAcct1No());
		params.put("docAcct2Num", formTS0121Vo.getDocAcct2Num());
		params.put("docAcct2No", formTS0121Vo.getDocAcct2No());
		params.put("docOther", formTS0121Vo.getDocOther());
		params.put("signOfficerFullName1", formTS0121Vo.getSignOfficerFullName1());
		params.put("signOfficerFullName2", formTS0121Vo.getSignOfficerFullName2());
		params.put("signWitnessFullName1", formTS0121Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0121Vo.getSignWitnessFullName2());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_21 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0121Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTs0121 formTs0121 = taFormTs0121Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS0121Vo formTs0121Vo = new TaFormTS0121Vo();
		toVo(formTs0121Vo, formTs0121);
		
		return formTs0121Vo;
	}

}
