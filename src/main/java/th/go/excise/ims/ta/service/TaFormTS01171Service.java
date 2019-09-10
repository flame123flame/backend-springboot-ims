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
import th.go.excise.ims.ta.persistence.entity.TaFormTs01171;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01171Repository;
import th.go.excise.ims.ta.vo.TaFormTS01171Vo;

@Service
public class TaFormTS01171Service extends AbstractTaFormTSService<TaFormTS01171Vo, TaFormTs01171> {

private static final Logger logger = LoggerFactory.getLogger(TaFormTS01171Service.class);
	
	@Autowired
	private TaFormTs01171Repository taFormTs01171Repository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs01171Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_17_1;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS01171Vo formTS01171Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS01171Vo);
		byte[] reportFile = generateReport(formTS01171Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS01171Vo formTS01171Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01171Vo.getFormTsNumber());

		TaFormTs01171 formTS01171 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS01171Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS01171Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS01171Vo.getFormTsNumber();
			
			formTS01171 = taFormTs01171Repository.findByFormTsNumber(formTS01171Vo.getFormTsNumber());
			toEntity(formTS01171, formTS01171Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTS01171 = new TaFormTs01171();
			toEntity(formTS01171, formTS01171Vo);
			formTS01171.setOfficeCode(officeCode);
			formTS01171.setBudgetYear(budgetYear);
			formTS01171.setFormTsNumber(formTsNumber);
		}
		
		taFormTs01171Repository.save(formTS01171);
		saveAuditStep(formTS01171Vo, TaFormTS01171Vo.class, TA_FORM_TS_CODE.TS01171, formTsNumber);
	}
	
	@Override
	public byte[] generateReport(TaFormTS01171Vo formTS01171Vo) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS01171Vo.getFormTsNumber());
		params.put("bookNumber1", formTS01171Vo.getBookNumber1());
		params.put("bookNumber2", formTS01171Vo.getBookNumber2());
		params.put("docTopic", formTS01171Vo.getDocTopic());
		params.put("docDate", formTS01171Vo.getDocDate());
		params.put("docDear", formTS01171Vo.getDocDear());
		params.put("factoryName", formTS01171Vo.getFactoryName());
		params.put("newRegId", formTS01171Vo.getNewRegId());
		params.put("factoryType", formTS01171Vo.getFactoryType());
		params.put("facAddrNo", formTS01171Vo.getFacAddrNo());
		params.put("facMooNo", formTS01171Vo.getFacMooNo());
		params.put("facSoiName", formTS01171Vo.getFacSoiName());
		params.put("facThnName", formTS01171Vo.getFacThnName());
		params.put("facTambolName", formTS01171Vo.getFacTambolName());
		params.put("facAmphurName", formTS01171Vo.getFacAmphurName());
		params.put("facProvinceName", formTS01171Vo.getFacProvinceName());
		params.put("facZipCode", formTS01171Vo.getFacZipCode());
		params.put("bookType", formTS01171Vo.getBookType());
		params.put("refBookNumber1", formTS01171Vo.getRefBookNumber1());
		params.put("refBookNumber2", formTS01171Vo.getRefBookNumber2());
		params.put("refDocDate", formTS01171Vo.getRefDocDate());
		params.put("auditDateStart", formTS01171Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS01171Vo.getAuditDateEnd());
		params.put("factDesc", formTS01171Vo.getFactDesc());
		params.put("lawDesc", formTS01171Vo.getLawDesc());
		params.put("factoryName2", formTS01171Vo.getFactoryName2());
		params.put("taxAmt", formTS01171Vo.getTaxAmt());
		params.put("fineAmt", formTS01171Vo.getFineAmt());
		params.put("extraAmt", formTS01171Vo.getExtraAmt());
		params.put("exciseTaxAmt", formTS01171Vo.getExciseTaxAmt());
		params.put("moiAmt", formTS01171Vo.getMoiAmt());
		params.put("sumAllTaxAmt", formTS01171Vo.getSumAllTaxAmt());
		params.put("extraDate", formTS01171Vo.getExtraDate());
		params.put("officeDest", formTS01171Vo.getOfficeDest());
		params.put("officeDate", formTS01171Vo.getOfficeDate());
		params.put("officeTime", formTS01171Vo.getOfficeTime());
		params.put("signOfficerFullName", formTS01171Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS01171Vo.getSignOfficerPosition());
		params.put("officeName", formTS01171Vo.getOfficeName());
		params.put("offficePhone", formTS01171Vo.getOffficePhone());
		params.put("headOfficerFullName", formTS01171Vo.getHeadOfficerFullName());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_17_1 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS01171Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTs01171 formTs01171 = taFormTs01171Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS01171Vo formTs01171Vo = new TaFormTS01171Vo();
		toVo(formTs01171Vo, formTs01171);
		
		return formTs01171Vo;
	}

}
