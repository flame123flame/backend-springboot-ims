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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0102;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0102Repository;
import th.go.excise.ims.ta.vo.TaFormTS0102Vo;

@Service
public class TaFormTS0102Service extends AbstractTaFormTSService<TaFormTS0102Vo, TaFormTs0102> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0102Service.class);

	@Autowired
	private TaFormTs0102Repository taFormTs0102Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0102Repository;
	}
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_02;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0102Vo formTS0102Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0102Vo);
		byte[] reportFile = generateReport(formTS0102Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0102Vo formTS0102Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0102Vo.getFormTsNumber());

		// Set Data
		TaFormTs0102 formTs0102 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0102Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0102Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0102Vo.getFormTsNumber();
			
			formTs0102 = taFormTs0102Repository.findByFormTsNumber(formTS0102Vo.getFormTsNumber());
			toEntity(formTs0102, formTS0102Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTs0102 = new TaFormTs0102();
			toEntity(formTs0102, formTS0102Vo);
			formTs0102.setOfficeCode(officeCode);
			formTs0102.setBudgetYear(budgetYear);
			formTs0102.setFormTsNumber(formTsNumber);
		}
		taFormTs0102Repository.save(formTs0102);
		
		saveAuditStep(formTS0102Vo, TaFormTS0102Vo.class, TA_FORM_TS_CODE.TS0102, formTsNumber);	
	}

	@Override
	public byte[] generateReport(TaFormTS0102Vo formTS0102Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0102Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0102Vo.getBookNumber1());
		params.put("bookNumber2", formTS0102Vo.getBookNumber2());
		params.put("docDear", formTS0102Vo.getDocDear());
		params.put("docFrom", formTS0102Vo.getDocFrom());
		params.put("docText1", formTS0102Vo.getDocText1());
		params.put("companyType", formTS0102Vo.getCompanyType());
		params.put("factoryName", formTS0102Vo.getFactoryName());
		params.put("newRegId", formTS0102Vo.getNewRegId());
		params.put("factoryAddress", formTS0102Vo.getFactoryAddress());
		params.put("factoryTypeText", formTS0102Vo.getFactoryTypeText());
		params.put("auditDateStart", formTS0102Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS0102Vo.getAuditDateEnd());
		params.put("auditCase", formTS0102Vo.getAuditCase());
		params.put("signOfficerFullName", formTS0102Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0102Vo.getSignOfficerPosition());
		params.put("signOfficerDate", formTS0102Vo.getSignOfficerDate());
		params.put("regDear", formTS0102Vo.getRegDear());
		params.put("regText", formTS0102Vo.getRegText());
		params.put("signRegFullName", formTS0102Vo.getSignRegFullName());
		params.put("signRegPosition", formTS0102Vo.getSignRegPosition());
		params.put("signRegDate", formTS0102Vo.getSignRegDate());
		params.put("comdTypeFlag", formTS0102Vo.getComdTypeFlag());
		params.put("signComdFullName", formTS0102Vo.getSignComdFullName());
		params.put("signComdPosition", formTS0102Vo.getSignComdPosition());
		params.put("signComdDate", formTS0102Vo.getSignComdDate());
		params.put("financeBookNumber1", formTS0102Vo.getFinanceBookNumber1());
		params.put("financeBookNumber2", formTS0102Vo.getFinanceBookNumber2());
		params.put("financeDear", formTS0102Vo.getFinanceDear());
		params.put("signFinanceFullName", formTS0102Vo.getSignFinanceFullName());
		params.put("signFinancePosition", formTS0102Vo.getSignFinancePosition());
		params.put("signFinanceDate", formTS0102Vo.getSignFinanceDate());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_02 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0102Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0102 formTs0102 = taFormTs0102Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0102Vo formTs0102Vo = new TaFormTS0102Vo();
		toVo(formTs0102Vo, formTs0102);

		return formTs0102Vo;
	}

}
