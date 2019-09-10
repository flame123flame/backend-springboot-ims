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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0113;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0113Repository;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;

@Service
public class TaFormTS0113Service extends AbstractTaFormTSService<TaFormTS0113Vo, TaFormTs0113> {

    private static final Logger logger = LoggerFactory.getLogger(TaFormTS0113Service.class);

    @Autowired
    private TaFormTs0113Repository taFormTs0113Repository;

    @Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0113Repository;
	}

	@Override
    public String getReportName() {
        return REPORT_NAME.TA_FORM_TS01_13;
    }

    @Override
    public byte[] processFormTS(TaFormTS0113Vo formTS0113Vo) throws Exception {
        logger.info("processFormTS");

        saveFormTS(formTS0113Vo);
        byte[] reportFile = generateReport(formTS0113Vo);

        return reportFile;
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void saveFormTS(TaFormTS0113Vo formTS0113Vo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String budgetYear = ExciseUtils.getCurrentBudgetYear();
        logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0113Vo.getFormTsNumber());

        TaFormTs0113 formTS0113 = null;
        String formTsNumber = null;
        if (StringUtils.isNotBlank(formTS0113Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0113Vo.getFormTsNumber())) {
        	// Case Update FormTS
        	formTsNumber = formTS0113Vo.getFormTsNumber();
        	
            formTS0113 = taFormTs0113Repository.findByFormTsNumber(formTS0113Vo.getFormTsNumber());
            toEntity(formTS0113, formTS0113Vo);
        } else {
        	// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
        				
            formTS0113 = new TaFormTs0113();
            toEntity(formTS0113, formTS0113Vo);
            formTS0113.setOfficeCode(officeCode);
            formTS0113.setBudgetYear(budgetYear);
            formTS0113.setFormTsNumber(formTsNumber);
        }
        taFormTs0113Repository.save(formTS0113);
        
        saveAuditStep(formTS0113Vo, TaFormTS0113Vo.class, TA_FORM_TS_CODE.TS0113, formTsNumber);
    }

    @Override
    public byte[] generateReport(TaFormTS0113Vo formTS0113Vo) throws Exception, IOException {
        logger.info("generateReport");

        // get data to report
        Map<String, Object> params = new HashMap<>();
        params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
        params.put("formTsNumber", formTS0113Vo.getFormTsNumber());
        params.put("docPlace", formTS0113Vo.getDocPlace());
        params.put("docDate", formTS0113Vo.getDocDate());
        params.put("docTime", formTS0113Vo.getDocTime());
        params.put("headOfficerFullName", formTS0113Vo.getHeadOfficerFullName());
        params.put("headOfficerPosition", formTS0113Vo.getHeadOfficerPosition());
        params.put("refBookNumber1", formTS0113Vo.getRefBookNumber1());
        params.put("refBookDate", formTS0113Vo.getRefBookDate());
        params.put("factoryType", formTS0113Vo.getFactoryType());
        params.put("newRegId", formTS0113Vo.getNewRegId());
        params.put("factoryName", formTS0113Vo.getFactoryName());
        params.put("facAddrNo", formTS0113Vo.getFacAddrNo());
        params.put("facSoiName", formTS0113Vo.getFacSoiName());
        params.put("facThnName", formTS0113Vo.getFacThnName());
        params.put("facTambolName", formTS0113Vo.getFacTambolName());
        params.put("facAmphurName", formTS0113Vo.getFacAmphurName());
        params.put("facProvinceName", formTS0113Vo.getFacProvinceName());
        params.put("facZipCode", formTS0113Vo.getFacZipCode());
        params.put("auditDate", formTS0113Vo.getAuditDate());
        params.put("ownerFullName", formTS0113Vo.getOwnerFullName());
        params.put("ownerPosition", formTS0113Vo.getOwnerPosition());
        params.put("factoryName2", formTS0113Vo.getFactoryName2());
        params.put("auditFinishTime", formTS0113Vo.getAuditFinishTime());
        params.put("signOwnerFullName", formTS0113Vo.getSignOwnerFullName());
        params.put("signOfficerFullName", formTS0113Vo.getSignOfficerFullName());
        params.put("signWitnessFullName1", formTS0113Vo.getSignWitnessFullName1());
        params.put("signWitnessFullName2", formTS0113Vo.getSignWitnessFullName2());

        // set output
        JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_13 + "." + FILE_EXTENSION.JASPER, params);
        byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
        ReportUtils.closeResourceFileInputStream(params);

        return content;
    }

    @Override
    public TaFormTS0113Vo getFormTS(String formTsNumber) {
    	logger.info("getFormTS formTsNumber={}", formTsNumber);
        
    	TaFormTs0113 taFormTs0113 = taFormTs0113Repository.findByFormTsNumber(formTsNumber);
        
    	// Set Data
        TaFormTS0113Vo formTS0113Vo = new TaFormTS0113Vo();
        toVo(formTS0113Vo, taFormTs0113);
        
        return formTS0113Vo;
    }
    
}
