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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0116;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0116Repository;
import th.go.excise.ims.ta.vo.TaFormTS0116Vo;

@Service
public class TaFormTS0116Service extends AbstractTaFormTSService<TaFormTS0116Vo, TaFormTs0116> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0116Service.class);
	
	@Autowired
	private TaFormTs0116Repository taFormTs0116Repository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0116Repository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_16;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0116Vo formTS0116Vo) throws Exception {
		logger.info("processFormTS");
		
		saveFormTS(formTS0116Vo);
		byte[] reportFile = generateReport(formTS0116Vo);
		
		return reportFile;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0116Vo formTS0116Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0116Vo.getFormTsNumber());

		// Set Data
		TaFormTs0116 formTs0116 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0116Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0116Vo.getFormTsNumber())) {
			formTsNumber = formTS0116Vo.getFormTsNumber();
			
			// Case Update FormTS
			
			formTs0116 = taFormTs0116Repository.findByFormTsNumber(formTS0116Vo.getFormTsNumber());
			toEntity(formTs0116, formTS0116Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			formTs0116 = new TaFormTs0116();
			toEntity(formTs0116, formTS0116Vo);
			formTs0116.setOfficeCode(officeCode);
			formTs0116.setBudgetYear(budgetYear);
			formTs0116.setFormTsNumber(formTsNumber);
		}
		taFormTs0116Repository.save(formTs0116);
		
		saveAuditStep(formTS0116Vo, TaFormTS0116Vo.class, TA_FORM_TS_CODE.TS0116, formTsNumber);
	}
	
	@Override
	public byte[] generateReport(TaFormTS0116Vo formTS0116Vo) throws Exception, IOException {
		logger.info("export generateReport");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0116Vo.getFormTsNumber());
		params.put("docText",formTS0116Vo.getDocText());
		params.put("docDear",formTS0116Vo.getDocDear());
		params.put("factoryName1",formTS0116Vo.getFactoryName1());
		params.put("factoryName2",formTS0116Vo.getFactoryName2());
		params.put("factoryType",formTS0116Vo.getFactoryType());
		params.put("newRegId",formTS0116Vo.getNewRegId());
		params.put("requestDate",formTS0116Vo.getRequestDate());
		params.put("requestTypeExcept",formTS0116Vo.getRequestTypeExcept());
		params.put("requestTypeReduce",formTS0116Vo.getRequestTypeReduce());
		params.put("requestTypeFineAmt",formTS0116Vo.getRequestTypeFineAmt());
		params.put("requestTypeExtraAmt",formTS0116Vo.getRequestTypeExtraAmt());
		params.put("requestReason",formTS0116Vo.getRequestReason());
		params.put("requestDesc",formTS0116Vo.getRequestDesc());
		params.put("fineNoFlag",formTS0116Vo.getFineNoFlag());
		params.put("fineExceptAmtFlag",formTS0116Vo.getFineExceptAmtFlag());
		params.put("fineReduceAmtFlag",formTS0116Vo.getFineReduceAmtFlag());
		params.put("finePercent",formTS0116Vo.getFinePercent());
		params.put("extraNoFlag",formTS0116Vo.getExtraNoFlag());
		params.put("extraReduceAmtFlag",formTS0116Vo.getExtraReduceAmtFlag());
		params.put("extraPercent",formTS0116Vo.getExtraPercent());
		params.put("beforeTaxAmt",formTS0116Vo.getBeforeTaxAmt());
		params.put("beforeFinePercent",formTS0116Vo.getBeforeFinePercent());
		params.put("beforeFineAmt",formTS0116Vo.getBeforeFineAmt());
		params.put("beforeExtraAmt",formTS0116Vo.getBeforeExtraAmt());
		params.put("beforeMoiAmt",formTS0116Vo.getBeforeMoiAmt());
		params.put("beforeSumAmt",formTS0116Vo.getBeforeSumAmt());
		params.put("afterTaxAmt",formTS0116Vo.getAfterTaxAmt());
		params.put("afterFinePercent",formTS0116Vo.getAfterFinePercent());
		params.put("afterFineAmt",formTS0116Vo.getAfterFineAmt());
		params.put("afterExtraAmt",formTS0116Vo.getAfterExtraAmt());
	 	params.put("afterMoiAmt",formTS0116Vo.getAfterMoiAmt());
		params.put("afterSumAmt",formTS0116Vo.getAfterSumAmt());
		params.put("signOfficerFullName",formTS0116Vo.getSignOfficerFullName());
		params.put("signOfficerPosition",formTS0116Vo.getSignOfficerPosition());
		params.put("headOfficerComment",formTS0116Vo.getHeadOfficerComment());
		params.put("signHeadOfficerFullName",formTS0116Vo.getSignHeadOfficerFullName());
		params.put("signHeadOfficerPosition",formTS0116Vo.getSignHeadOfficerPosition());
		params.put("signHeadOfficerDate",formTS0116Vo.getSignHeadOfficerDate());
		params.put("approverComment",formTS0116Vo.getApproverComment());
		params.put("signApproverFullName",formTS0116Vo.getSignApproverFullName());
		params.put("signApproverPosition",formTS0116Vo.getSignApproverPosition());
		params.put("signApproverDate",formTS0116Vo.getSignApproverDate());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_16 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
	
	@Override
	public TaFormTS0116Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		TaFormTs0116 formTs0116 = taFormTs0116Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS0116Vo formTs0116Vo = new TaFormTS0116Vo();
		toVo(formTs0116Vo, formTs0116);
		
		return formTs0116Vo;
	}
	
}
