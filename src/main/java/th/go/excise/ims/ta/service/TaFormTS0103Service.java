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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0103;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0103Repository;
import th.go.excise.ims.ta.vo.TaFormTS0103Vo;

@Service
public class TaFormTS0103Service extends AbstractTaFormTSService<TaFormTS0103Vo, TaFormTs0103> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0103Service.class);

	@Autowired
	private TaFormTs0103Repository taFormTs0103Repository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0103Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_03;
	}

	@Override
	public byte[] processFormTS(TaFormTS0103Vo formTS0103Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0103Vo);
		byte[] reportFile = generateReport(formTS0103Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0103Vo formTS0103Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0103Vo.getFormTsNumber());

		// Set Data
		TaFormTs0103 formTs0103 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0103Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0103Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0103Vo.getFormTsNumber();
						
			formTs0103 = taFormTs0103Repository.findByFormTsNumber(formTS0103Vo.getFormTsNumber());
			toEntity(formTs0103, formTS0103Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			formTs0103 = new TaFormTs0103();
			toEntity(formTs0103, formTS0103Vo);
			formTs0103.setOfficeCode(officeCode);
			formTs0103.setBudgetYear(budgetYear);
			formTs0103.setFormTsNumber(formTsNumber);
		}
		taFormTs0103Repository.save(formTs0103);
		
		saveAuditStep(formTS0103Vo, TaFormTS0103Vo.class, TA_FORM_TS_CODE.TS0103, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0103Vo formTS0103Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0103Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0103Vo.getBookNumber1());
		params.put("bookNumber2", formTS0103Vo.getBookNumber2());
		params.put("officeName1", formTS0103Vo.getOfficeName1());
		params.put("docDate", formTS0103Vo.getDocDate());
		params.put("docDear", formTS0103Vo.getDocDear());
		params.put("factoryType", formTS0103Vo.getFactoryType());
		params.put("factoryName", formTS0103Vo.getFactoryName());
		params.put("facAddrNo", formTS0103Vo.getFacAddrNo());
		params.put("facSoiName", formTS0103Vo.getFacSoiName());
		params.put("facThnName", formTS0103Vo.getFacThnName());
		params.put("facTambolName", formTS0103Vo.getFacTambolName());
		params.put("facAmphurName", formTS0103Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0103Vo.getFacProvinceName());
		params.put("facZipCode", formTS0103Vo.getFacZipCode());
		params.put("newRegId", formTS0103Vo.getNewRegId());
		params.put("compAddrNo", formTS0103Vo.getCompAddrNo());
		params.put("compSoiName", formTS0103Vo.getCompSoiName());
		params.put("compThnName", formTS0103Vo.getCompThnName());
		params.put("compTambolName", formTS0103Vo.getCompTambolName());
		params.put("compAmphurName", formTS0103Vo.getCompAmphurName());
		params.put("compProvinceName", formTS0103Vo.getCompProvinceName());
		params.put("compZipCode", formTS0103Vo.getCompZipCode());
		params.put("reasonText", formTS0103Vo.getReasonText());
		params.put("lawSection", formTS0103Vo.getLawSection());
		params.put("lawGroup", formTS0103Vo.getLawGroup());
		params.put("destText", formTS0103Vo.getDestText());
		params.put("destDate", formTS0103Vo.getDestDate());
		params.put("destTime", formTS0103Vo.getDestTime());
		params.put("destDocDesc", formTS0103Vo.getDestDocDesc());
		params.put("signOfficerFullName", formTS0103Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0103Vo.getSignOfficerPosition());
		params.put("officeName2", formTS0103Vo.getOfficeName2());
		params.put("officePhone", formTS0103Vo.getOfficePhone());
		params.put("headOfficerFullName", formTS0103Vo.getHeadOfficerFullName());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_03 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0103Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0103 formTs0103 = taFormTs0103Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0103Vo formTs0103Vo = new TaFormTS0103Vo();
		toVo(formTs0103Vo, formTs0103);

		return formTs0103Vo;
	}

}
