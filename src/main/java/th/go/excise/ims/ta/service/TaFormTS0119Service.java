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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0119;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0119Repository;
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;

@Service
public class TaFormTS0119Service extends AbstractTaFormTSService<TaFormTS0119Vo, TaFormTs0119> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0119Service.class);

	@Autowired
	private TaFormTs0119Repository taFormTs0119Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0119Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_19;
	}

	@Override
	public byte[] processFormTS(TaFormTS0119Vo formTS0119Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0119Vo);
		byte[] reportFile = generateReport(formTS0119Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0119Vo formTS0119Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0119Vo.getFormTsNumber());

		TaFormTs0119 formTS0119 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0119Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0119Vo.getFormTsNumber())) {
			// Case Update FormTS
        	formTsNumber = formTS0119Vo.getFormTsNumber();
        	
			formTS0119 = taFormTs0119Repository.findByFormTsNumber(formTS0119Vo.getFormTsNumber());
			toEntity(formTS0119, formTS0119Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTS0119 = new TaFormTs0119();
			toEntity(formTS0119, formTS0119Vo);
			formTS0119.setOfficeCode(officeCode);
			formTS0119.setBudgetYear(budgetYear);
			formTS0119.setFormTsNumber(formTsNumber);
		}
		
		taFormTs0119Repository.save(formTS0119);
		
		saveAuditStep(formTS0119Vo, TaFormTS0119Vo.class, TA_FORM_TS_CODE.TS0119, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0119Vo formTS0119Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0119Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0119Vo.getBookNumber1());
		params.put("bookNumber2", formTS0119Vo.getBookNumber2());
		params.put("docText1", formTS0119Vo.getDocText1());
		params.put("docText2", formTS0119Vo.getDocText2());
		params.put("docText3", formTS0119Vo.getDocText3());
		params.put("docDear", formTS0119Vo.getDocDear());
		params.put("companyName", formTS0119Vo.getCompanyName());
		params.put("factoryType", formTS0119Vo.getFactoryType());
		params.put("factoryName", formTS0119Vo.getFactoryName());
		params.put("newRegId", formTS0119Vo.getNewRegId());
		params.put("facAddrNo", formTS0119Vo.getFacAddrNo());
		params.put("facMooNo", formTS0119Vo.getFacMooNo());
		params.put("facSoiName", formTS0119Vo.getFacSoiName());
		params.put("facThnName", formTS0119Vo.getFacThnName());
		params.put("facTambolName", formTS0119Vo.getFacTambolName());
		params.put("facAmphurName", formTS0119Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0119Vo.getFacProvinceName());
		params.put("facZipCode", formTS0119Vo.getFacZipCode());
		params.put("signOfficerFullName", formTS0119Vo.getSignOfficerFullName());
		params.put("followTypeFlag1", formTS0119Vo.getFollowTypeFlag1());
		params.put("followTypeFlag2", formTS0119Vo.getFollowTypeFlag2());
		params.put("signOfficerFullName", formTS0119Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0119Vo.getSignOfficerPosition());
		params.put("officeName2", formTS0119Vo.getOfficeName2());
		params.put("officePhone", formTS0119Vo.getOfficePhone());
		params.put("docDate", formTS0119Vo.getDocDate());
		params.put("refBookDate", formTS0119Vo.getRefBookDate());
		params.put("officeName1", formTS0119Vo.getOfficeName1());
		params.put("refBookNumber", formTS0119Vo.getRefBookNumber());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_19 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0119Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0119 formTs0119 = taFormTs0119Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0119Vo formTs0119Vo = new TaFormTS0119Vo();
		toVo(formTs0119Vo, formTs0119);

		return formTs0119Vo;
	}

}
