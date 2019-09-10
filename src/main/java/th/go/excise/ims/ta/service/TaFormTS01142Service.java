package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01142Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01142Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01142DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01142HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS01142DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS01142Vo;

@Service
public class TaFormTS01142Service  extends AbstractTaFormTSService<TaFormTS01142Vo, TaFormTs01142Hdr>{
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0114Service.class);
	
	@Autowired
	private TaFormTs01142HdrRepository taFormTs01142HdrRepository;
	@Autowired
	private TaFormTs01142DtlRepository taFormTs01142DtlRepository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs01142HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_14_2;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS01142Vo taFormTS01142Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(taFormTS01142Vo);
		byte[] reportFile = generateReport(taFormTS01142Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS01142Vo formTS01142Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01142Vo.getFormTsNumber());
		
		TaFormTs01142Hdr formTS01142Hdr = null;
		TaFormTs01142Dtl formTS01142Dtl = null;
		List<TaFormTs01142Dtl> formTs01142DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS01142Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS01142Vo.getFormTsNumber())) {
			formTsNumber = formTS01142Vo.getFormTsNumber();
			
			// Case Update FormTS

			// Update Header
			formTS01142Hdr = taFormTs01142HdrRepository.findByFormTsNumber(formTS01142Vo.getFormTsNumber());
			toEntity(formTS01142Hdr, formTS01142Vo);

			// Update Detail
			formTs01142DtlList = taFormTs01142DtlRepository.findByFormTsNumber(formTS01142Vo.getFormTsNumber());
			
			// Update isDeleted = 'Y' for Default
			formTs01142DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});
			
			// Set Detail Record
			if (formTS01142Vo.getTaFormTS01142DtlVoList() != null) {
				int i = 1;
				for (TaFormTS01142DtlVo formTS01142DtlVo : formTS01142Vo.getTaFormTS01142DtlVoList()) {
					formTS01142Dtl = getEntityById(formTs01142DtlList, formTS01142DtlVo.getFormTs01142DtlId());
					if (formTS01142Dtl != null) {
                        // Exist Page
                        toEntityDtl(formTS01142Dtl, formTS01142DtlVo);
                        formTS01142Dtl.setIsDeleted(FLAG.N_FLAG);
                        formTS01142Dtl.setRecNo(String.valueOf(i));
                    } else {
                        // New Page
                    	formTS01142Dtl = new TaFormTs01142Dtl();
                        toEntityDtl(formTS01142Dtl, formTS01142DtlVo);
                        formTS01142Dtl.setFormTsNumber(formTS01142Vo.getFormTsNumber());
                        formTS01142Dtl.setRecNo(String.valueOf(i));
                        formTs01142DtlList.add(formTS01142Dtl);
                    }
					i++;
				}
				taFormTs01142DtlRepository.saveAll(formTs01142DtlList);
			}
			
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			// Set Header Record
			formTS01142Hdr = new TaFormTs01142Hdr();
			toEntity(formTS01142Hdr, formTS01142Vo);
			formTS01142Hdr.setOfficeCode(officeCode);
			formTS01142Hdr.setBudgetYear(budgetYear);
			formTS01142Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs01142DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS01142DtlVo formTS01142DtlVo : formTS01142Vo.getTaFormTS01142DtlVoList()) {
				formTS01142Dtl = new TaFormTs01142Dtl();
				toEntityDtl(formTS01142Dtl, formTS01142DtlVo);
				formTS01142Dtl.setFormTsNumber(formTsNumber);
				formTS01142Dtl.setRecNo(String.valueOf(i));
				formTs01142DtlList.add(formTS01142Dtl);
				i++;
			}
			taFormTs01142DtlRepository.saveAll(formTs01142DtlList);
		}
		taFormTs01142HdrRepository.save(formTS01142Hdr);
		
		saveAuditStep(formTS01142Vo, TaFormTS01142Vo.class, TA_FORM_TS_CODE.TS01142, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS01142Vo formTS01142Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS01142Vo.getFormTsNumber());
		params.put("ownerFullName",formTS01142Vo.getOwnerFullName());
		params.put("factoryType",formTS01142Vo.getFactoryType());
		params.put("factoryName",formTS01142Vo.getFactoryName());
		params.put("auditDateStart",formTS01142Vo.getAuditDateStart());
		params.put("auditDateEnd",formTS01142Vo.getAuditDateEnd());
		params.put("dutyTypeText",formTS01142Vo.getDutyTypeText());
		params.put("newRegId",formTS01142Vo.getNewRegId());
		params.put("extraAmtDate",formTS01142Vo.getExtraAmtDate());
		params.put("signOwnerFullName",formTS01142Vo.getSignOwnerFullName());
		params.put("signOfficerFullName",formTS01142Vo.getSignOfficerFullName());

        JRDataSource dataSource = new JRBeanCollectionDataSource(formTS01142Vo.getTaFormTS01142DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_2 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		
		return reportFile;
	}

	@Override
	public TaFormTS01142Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		// Prepare Header
		TaFormTS01142Vo formTS01142Vo = new TaFormTS01142Vo();
		TaFormTs01142Hdr formTs01142Hdr = taFormTs01142HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS01142Vo, formTs01142Hdr);
		// Prepare Detail
		List<TaFormTs01142Dtl> formTs01142DtlList = taFormTs01142DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS01142DtlVo> formTS01142DtlVoList = new ArrayList<>();
		TaFormTS01142DtlVo formTS01142DtlVo = null;
		for (TaFormTs01142Dtl formTs01142Dtl : formTs01142DtlList) {
			formTS01142DtlVo = new TaFormTS01142DtlVo();
			formTS01142DtlVo.setFormTs01142DtlId(StringUtils.defaultString(Long.toString(formTs01142Dtl.getFormTs01142DtlId())));
			formTS01142DtlVo.setRecDate(formTs01142Dtl.getRecDate());
			formTS01142DtlVo.setDutyTypeText(StringUtils.defaultString(formTs01142Dtl.getDutyTypeText()));
			formTS01142DtlVo.setValueFromAudit(formTs01142Dtl.getValueFromAudit());
			formTS01142DtlVo.setTaxRate(formTs01142Dtl.getTaxRate());
			formTS01142DtlVo.setAuditTaxAmt(formTs01142Dtl.getAuditTaxAmt());
			formTS01142DtlVo.setPaidTaxAmt(formTs01142Dtl.getPaidTaxAmt());
			formTS01142DtlVo.setAddTaxAmt(formTs01142Dtl.getAddTaxAmt());
			formTS01142DtlVo.setAddFineAmt(formTs01142Dtl.getAddFineAmt());
			formTS01142DtlVo.setAddExtraAmt(formTs01142Dtl.getAddExtraAmt());
			formTS01142DtlVo.setAddSumTaxAmt(formTs01142Dtl.getAddSumTaxAmt());
			formTS01142DtlVo.setAddMoiAmt(formTs01142Dtl.getAddMoiAmt());
			formTS01142DtlVo.setAddSumAllTaxAmt(formTs01142Dtl.getAddSumAllTaxAmt());
			formTS01142DtlVo.setAddMonthNum(formTs01142Dtl.getAddMonthNum());
			formTS01142DtlVoList.add(formTS01142DtlVo);
		}
		formTS01142Vo.setTaFormTS01142DtlVoList(formTS01142DtlVoList);

		return formTS01142Vo;
	}
	
	private void toEntityDtl(TaFormTs01142Dtl entity, TaFormTS01142DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	private TaFormTs01142Dtl getEntityById(List<TaFormTs01142Dtl> taFormTs01142DtlList, String id) {
        TaFormTs01142Dtl formTs01142Dtl = null;
        
        for (TaFormTs01142Dtl taFormTs01142Dtl : taFormTs01142DtlList) {
            if (id.equals(taFormTs01142Dtl.getFormTs01142DtlId().toString())) {
                formTs01142Dtl = taFormTs01142Dtl;
                break;
            }
        }
        
        return formTs01142Dtl;
    }
	
}
