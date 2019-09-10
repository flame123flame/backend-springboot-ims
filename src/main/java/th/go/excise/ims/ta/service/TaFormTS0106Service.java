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
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0106;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0106Repository;
import th.go.excise.ims.ta.vo.TaFormTS0106Vo;

@Service
public class TaFormTS0106Service extends AbstractTaFormTSService<TaFormTS0106Vo, TaFormTs0106> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0106Service.class);

	@Autowired
	private TaFormTs0106Repository taFormTs0106Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0106Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_06;
	}

	@Override
	public byte[] processFormTS(TaFormTS0106Vo formTS0106Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0106Vo);
		byte[] reportFile = generateReport(formTS0106Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0106Vo formTS0106Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0106Vo.getFormTsNumber());

		// Set Data
		TaFormTs0106 formTs0106 = null;
		if (StringUtils.isNotBlank(formTS0106Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0106Vo.getFormTsNumber())) {
			formTs0106 = taFormTs0106Repository.findByFormTsNumber(formTS0106Vo.getFormTsNumber());
			toEntity(formTs0106, formTS0106Vo);
		} else {
			formTs0106 = new TaFormTs0106();
			toEntity(formTs0106, formTS0106Vo);
			formTs0106.setOfficeCode(officeCode);
			formTs0106.setBudgetYear(budgetYear);
			formTs0106.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		
		taFormTs0106Repository.save(formTs0106);
	}

	@Override
	public byte[] generateReport(TaFormTS0106Vo formTS0106Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0106Vo.getFormTsNumber());
		params.put("docPlace", formTS0106Vo.getDocPlace());
		params.put("docDate", formTS0106Vo.getDocDate());
		params.put("writerFullName", formTS0106Vo.getWriterFullName());
		params.put("writerPositionFlag", formTS0106Vo.getWriterPositionFlag());
		params.put("factoryName", formTS0106Vo.getFactoryName());
		params.put("newRegId", formTS0106Vo.getNewRegId());
		params.put("facAddrNo", formTS0106Vo.getFacAddrNo());
		params.put("facMooNo", formTS0106Vo.getFacMooNo());
		params.put("facSoiName", formTS0106Vo.getFacSoiName());
		params.put("facThnName", formTS0106Vo.getFacThnName());
		params.put("facTambolName", formTS0106Vo.getFacTambolName());
		params.put("facAmphurName", formTS0106Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0106Vo.getFacProvinceName());
		params.put("facZipCode", formTS0106Vo.getFacZipCode());
		params.put("facTelNo", formTS0106Vo.getFacTelNo());
		params.put("refBookNumber1", formTS0106Vo.getRefBookNumber1());
		params.put("refBookNumber2", formTS0106Vo.getRefBookNumber2());
		params.put("refDocDate", formTS0106Vo.getRefDocDate());
		params.put("authFullName", formTS0106Vo.getAuthFullName());
		params.put("authAge", formTS0106Vo.getAuthAge());
		params.put("authAddrNo", formTS0106Vo.getAuthAddrNo());
		params.put("authSoiName", formTS0106Vo.getAuthSoiName());
		params.put("authThnName", formTS0106Vo.getAuthThnName());
		params.put("authTambolName", formTS0106Vo.getAuthTambolName());
		params.put("authAmphurName", formTS0106Vo.getAuthAmphurName());
		params.put("authProvinceName", formTS0106Vo.getAuthProvinceName());
		params.put("authZipCode", formTS0106Vo.getAuthZipCode());
		params.put("authTelNo", formTS0106Vo.getAuthTelNo());
		params.put("authCardId", formTS0106Vo.getAuthCardId());
		params.put("authCardPlace", formTS0106Vo.getAuthCardPlace());
		params.put("docText", formTS0106Vo.getDocText());
		params.put("signAuthFullName1", formTS0106Vo.getSignAuthFullName1());
		params.put("signAuthFullName2", formTS0106Vo.getSignAuthFullName2());
		params.put("signAuthFullName3", formTS0106Vo.getSignAuthFullName3());
		params.put("signWitnessFullName1", formTS0106Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0106Vo.getSignWitnessFullName2());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_06 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0106Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0106 formTs0106 = taFormTs0106Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0106Vo formTs0106Vo = new TaFormTS0106Vo();
		toVo(formTs0106Vo, formTs0106);

		return formTs0106Vo;
	}

}
