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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0120;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0120Repository;
import th.go.excise.ims.ta.vo.TaFormTS0120Vo;

@Service
public class TaFormTS0120Service extends AbstractTaFormTSService<TaFormTS0120Vo, TaFormTs0120> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0120Service.class);
	
	@Autowired
	private TaFormTs0120Repository taFormTs0120Repository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0120Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_20;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0120Vo formTS0120Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0120Vo);
		byte[] reportFile = generateReport(formTS0120Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0120Vo formTS0120Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0120Vo.getFormTsNumber());

		TaFormTs0120 formTS0120 = null;
		if (StringUtils.isNotBlank(formTS0120Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0120Vo.getFormTsNumber())) {
			formTS0120 = taFormTs0120Repository.findByFormTsNumber(formTS0120Vo.getFormTsNumber());
			toEntity(formTS0120, formTS0120Vo);
		} else {
			formTS0120 = new TaFormTs0120();
			toEntity(formTS0120, formTS0120Vo);
			formTS0120.setOfficeCode(officeCode);
			formTS0120.setBudgetYear(budgetYear);
			formTS0120.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0120Repository.save(formTS0120);
	}
	
	@Override
	public byte[] generateReport(TaFormTS0120Vo formTS0120Vo) throws Exception, IOException {
		logger.info("generateReport");
		
		// get data to report
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0120Vo.getFormTsNumber());
		params.put("factoryName", formTS0120Vo.getFactoryName());
		params.put("docDear", formTS0120Vo.getDocDear());
		params.put("bookNumber1", formTS0120Vo.getBookNumber1());
		params.put("bookDate", formTS0120Vo.getBookDate());
		params.put("factoryName2", formTS0120Vo.getFactoryName2());
		params.put("newRegId", formTS0120Vo.getNewRegId());
		params.put("auditDateStart", formTS0120Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS0120Vo.getAuditDateEnd());
		params.put("facAddrNo", formTS0120Vo.getFacAddrNo());
		params.put("facMooNo", formTS0120Vo.getFacMooNo());
		params.put("facSoiName", formTS0120Vo.getFacSoiName());
		params.put("facThnName", formTS0120Vo.getFacThnName());
		params.put("facTambolName", formTS0120Vo.getFacTambolName());
		params.put("facAmphurName", formTS0120Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0120Vo.getFacProvinceName());
		params.put("facZipCode", formTS0120Vo.getFacZipCode());
		params.put("expandReason", formTS0120Vo.getExpandReason());
		params.put("expandFlag", formTS0120Vo.getExpandFlag());
		params.put("expandNo", formTS0120Vo.getExpandNo());
		params.put("expandDateOld", formTS0120Vo.getExpandDateOld());
		params.put("expandDateNew", formTS0120Vo.getExpandDateNew());
		params.put("signOfficerFullName", formTS0120Vo.getSignOfficerFullName());
		params.put("signOfficerDate", formTS0120Vo.getSignOfficerDate());
		params.put("headOfficerComment", formTS0120Vo.getHeadOfficerComment());
		params.put("signHeadOfficerFullName", formTS0120Vo.getSignHeadOfficerFullName());
		params.put("signHeadOfficerDate", formTS0120Vo.getSignHeadOfficerDate());
		params.put("approverComment", formTS0120Vo.getApproverComment());
		params.put("approveFlag", formTS0120Vo.getApproveFlag());
		params.put("signApproverFullName", formTS0120Vo.getSignApproverFullName());
		params.put("signApproverDate", formTS0120Vo.getSignApproverDate());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_20 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0120Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTs0120 formTs0120 = taFormTs0120Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS0120Vo formTs0120Vo = new TaFormTS0120Vo();
		toVo(formTs0120Vo, formTs0120);
		
		return formTs0120Vo;
	}

}
