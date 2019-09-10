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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0114Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0114Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0114DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0114HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0114DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;

@Service
public class TaFormTS0114Service extends AbstractTaFormTSService<TaFormTS0114Vo, TaFormTs0114Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0114Service.class);

	@Autowired
	private TaFormTs0114HdrRepository taFormTs0114HdrRepository;
	@Autowired
	private TaFormTs0114DtlRepository taFormTs0114DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0114HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_14;
	}

	@Override
	public byte[] processFormTS(TaFormTS0114Vo taFormTS0114Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(taFormTS0114Vo);
		byte[] reportFile = generateReport(taFormTS0114Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0114Vo formTS0114Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0114Vo.getFormTsNumber());

		TaFormTs0114Hdr formTs0114Hdr = null;
		TaFormTs0114Dtl formTs0114Dtl = null;
		List<TaFormTs0114Dtl> taFormTs0114DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0114Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0114Vo.getFormTsNumber())) {
			formTsNumber = formTS0114Vo.getFormTsNumber();
			
			// Case Update FormTS

			// ==> Set Hdr
			formTs0114Hdr = taFormTs0114HdrRepository.findByFormTsNumber(formTS0114Vo.getFormTsNumber());
			toEntity(formTs0114Hdr, formTS0114Vo);

			// ==> Set Dtl
			taFormTs0114DtlList = taFormTs0114DtlRepository.findByFormTsNumber(formTS0114Vo.getFormTsNumber());

			// Set flag Y
			taFormTs0114DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0114Vo.getTaFormTS0114DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0114DtlVo formDtlVo : formTS0114Vo.getTaFormTS0114DtlVoList()) {
					formTs0114Dtl = getEntityById(taFormTs0114DtlList, formDtlVo.getFormTs0114DtlId());
					if (formTs0114Dtl != null) {
						// Exist Record
						toEntityDtl(formTs0114Dtl, formDtlVo);
						formTs0114Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0114Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Record
						formTs0114Dtl = new TaFormTs0114Dtl();
						toEntityDtl(formTs0114Dtl, formDtlVo);
						formTs0114Dtl.setFormTsNumber(formTS0114Vo.getFormTsNumber());
						formTs0114Dtl.setRecNo(String.valueOf(i));
						taFormTs0114DtlList.add(formTs0114Dtl);
					}
					i++;
				}
				taFormTs0114DtlRepository.saveAll(taFormTs0114DtlList);
			}
			
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			// Set Header Record
			formTs0114Hdr = new TaFormTs0114Hdr();
			toEntity(formTs0114Hdr, formTS0114Vo);
			formTs0114Hdr.setOfficeCode(officeCode);
			formTs0114Hdr.setBudgetYear(budgetYear);
			formTs0114Hdr.setFormTsNumber(formTsNumber);
			
			// Set Detail Record
			taFormTs0114DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0114DtlVo formDtl : formTS0114Vo.getTaFormTS0114DtlVoList()) {
				formTs0114Dtl = new TaFormTs0114Dtl();
				toEntityDtl(formTs0114Dtl, formDtl);
				formTs0114Dtl.setFormTsNumber(formTsNumber);
				formTs0114Dtl.setRecNo(String.valueOf(i));
				taFormTs0114DtlList.add(formTs0114Dtl);
				i++;
			}
			taFormTs0114DtlRepository.saveAll(taFormTs0114DtlList);
		}
		taFormTs0114HdrRepository.save(formTs0114Hdr);
		
		saveAuditStep(formTS0114Vo, TaFormTS0114Vo.class, TA_FORM_TS_CODE.TS0114, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0114Vo formTS0114Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0114Vo.getFormTsNumber());
		params.put("factoryName", formTS0114Vo.getFactoryName());
		params.put("newRegId", formTS0114Vo.getNewRegId());
		params.put("facAddrNo", formTS0114Vo.getFacAddrNo());
		params.put("facSoiName", formTS0114Vo.getFacSoiName());
		params.put("facThnName", formTS0114Vo.getFacThnName());
		params.put("facTambolName", formTS0114Vo.getFacTambolName());
		params.put("facAmphurName", formTS0114Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0114Vo.getFacProvinceName());
		params.put("facZipCode", formTS0114Vo.getFacZipCode());
		params.put("facTelNo", formTS0114Vo.getFacTelNo());
		params.put("facFaxNumber", formTS0114Vo.getFacFaxNumber());
		params.put("factoryTypeText", formTS0114Vo.getFactoryTypeText());
		params.put("officerFullName", formTS0114Vo.getOfficerFullName());
		params.put("officerDept", formTS0114Vo.getOfficerDept());
		params.put("auditDate", formTS0114Vo.getAuditDate());
		params.put("bookNumber1", formTS0114Vo.getBookNumber1());
		params.put("bookDate", formTS0114Vo.getBookDate());
		params.put("auditDateStart", formTS0114Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS0114Vo.getAuditDateEnd());
		params.put("auditSumMonth", formTS0114Vo.getAuditSumMonth());
		params.put("auditSumDay", formTS0114Vo.getAuditSumDay());
		params.put("auditBookType", formTS0114Vo.getAuditBookType());
		params.put("auditBookTypeOther", formTS0114Vo.getAuditBookTypeOther());
		params.put("auditBookNumber", formTS0114Vo.getAuditBookNumber());
		params.put("auditBookDate", formTS0114Vo.getAuditBookDate());
		params.put("docNum", formTS0114Vo.getDocNum());
		params.put("doc1Num", formTS0114Vo.getDoc1Num());
		params.put("doc1Date", formTS0114Vo.getDoc1Date());
		params.put("doc2Num", formTS0114Vo.getDoc2Num());
		params.put("doc2Date", formTS0114Vo.getDoc2Date());
		params.put("doc3Num", formTS0114Vo.getDoc3Num());
		params.put("doc3Date", formTS0114Vo.getDoc3Date());
		params.put("doc4Num", formTS0114Vo.getDoc4Num());
		params.put("doc5Num", formTS0114Vo.getDoc5Num());
		params.put("doc6Num", formTS0114Vo.getDoc6Num());
		params.put("doc7Num", formTS0114Vo.getDoc7Num());
		params.put("doc8Num", formTS0114Vo.getDoc8Num());
		params.put("doc9Num", formTS0114Vo.getDoc9Num());
		params.put("assReason", formTS0114Vo.getAssReason());
		params.put("signOfficerFullName", formTS0114Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0114Vo.getSignOfficerPosition());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0114Vo.getTaFormTS0114DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0114Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		
		// Prepare Header
		TaFormTS0114Vo formTS0114Vo = new TaFormTS0114Vo();
		TaFormTs0114Hdr formTs0114Hdr = taFormTs0114HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0114Vo, formTs0114Hdr);
		
		// Prepare Detail
		List<TaFormTs0114Dtl> formTs0114DtlList = taFormTs0114DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0114DtlVo> formTS0114DtlVoList = new ArrayList<>();
		TaFormTS0114DtlVo formTS0114DtlVo = null;
		for (TaFormTs0114Dtl formTs0114Dtl : formTs0114DtlList) {
			formTS0114DtlVo = new TaFormTS0114DtlVo();
			toVoDtl(formTS0114DtlVo, formTs0114Dtl);
			formTS0114DtlVoList.add(formTS0114DtlVo);
		}
		formTS0114Vo.setTaFormTS0114DtlVoList(formTS0114DtlVoList);
		
		return formTS0114Vo;
	}

	private void toEntityDtl(TaFormTs0114Dtl entity, TaFormTS0114DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private void toVoDtl(TaFormTS0114DtlVo vo, TaFormTs0114Dtl entity) {
		try {
			BeanUtils.copyProperties(vo, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0114Dtl getEntityById(List<TaFormTs0114Dtl> formTs0114DtlList, String id) {
		TaFormTs0114Dtl formTs0114Dtl = null;
		
		for (TaFormTs0114Dtl taFormTs0114Dtl : formTs0114DtlList) {
			if (id.equals(taFormTs0114Dtl.getFormTs0114DtlId().toString())) {
				formTs0114Dtl = taFormTs0114Dtl;
				break;
			}
		}
		
		return formTs0114Dtl;
	}

}
