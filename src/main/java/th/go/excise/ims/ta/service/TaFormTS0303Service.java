package th.go.excise.ims.ta.service;

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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0303Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0303Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0303DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0303HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0303DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0303Vo;

@Service
public class TaFormTS0303Service extends AbstractTaFormTSService<TaFormTS0303Vo, TaFormTs0303Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0303Service.class);

	@Autowired
	private TaFormTs0303HdrRepository taFormTs0303HdrRepository;
	@Autowired
	private TaFormTs0303DtlRepository taFormTs0303DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0303HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS03_03;
	}

	@Override
	public byte[] processFormTS(TaFormTS0303Vo formTS0303Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0303Vo);
		byte[] reportFile = generateReport(formTS0303Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0303Vo formTS0303Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0303Vo.getFormTsNumber());

		TaFormTs0303Hdr formTs0303Hdr = null;
		TaFormTs0303Dtl formTs0303Dtl = null;
		List<TaFormTs0303Dtl> formTs0303DtlList = null;
		if (StringUtils.isNotBlank(formTS0303Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0303Vo.getFormTsNumber())) {
			// Case Update FormTS

			// ==> Set Hdr
			formTs0303Hdr = taFormTs0303HdrRepository.findByFormTsNumber(formTS0303Vo.getFormTsNumber());
			toEntity(formTs0303Hdr, formTS0303Vo);

			// ==> Set Dtl
			formTs0303DtlList = taFormTs0303DtlRepository.findByFormTsNumber(formTS0303Vo.getFormTsNumber());

			// Update isDeleted = 'Y' for Default
			formTs0303DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0303Vo.getTaFormTS0303DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0303DtlVo formTS0303DtlVo : formTS0303Vo.getTaFormTS0303DtlVoList()) {
					formTs0303Dtl = getEntityById(formTs0303DtlList, formTS0303DtlVo.getFormTs0303DtlId());
					if (formTs0303Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0303Dtl, formTS0303DtlVo);
						formTs0303Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0303Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTs0303Dtl = new TaFormTs0303Dtl();
						toEntityDtl(formTs0303Dtl, formTS0303DtlVo);
						formTs0303Dtl.setFormTsNumber(formTS0303Vo.getFormTsNumber());
						formTs0303Dtl.setRecNo(String.valueOf(i));
						formTs0303DtlList.add(formTs0303Dtl);
					}
					i++;
				}
				taFormTs0303DtlRepository.saveAll(formTs0303DtlList);
			}

		} else {
			// Case New FormTS
			String formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);

			// Set Header Record
			formTs0303Hdr = new TaFormTs0303Hdr();
			formTs0303Hdr.setBudgetYear(budgetYear);
			formTs0303Hdr.setOfficeCode(officeCode);
			formTs0303Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs0303DtlList = new ArrayList<>();
			if (formTS0303Vo.getTaFormTS0303DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0303DtlVo formTS0303DtlVo : formTS0303Vo.getTaFormTS0303DtlVoList()) {
					formTs0303Dtl = new TaFormTs0303Dtl();
					toEntityDtl(formTs0303Dtl, formTS0303DtlVo);
					formTs0303Dtl.setFormTsNumber(formTsNumber);
					formTs0303Dtl.setRecNo(String.valueOf(i));
					formTs0303DtlList.add(formTs0303Dtl);
					i++;
				}
				taFormTs0303DtlRepository.saveAll(formTs0303DtlList);
			}
			taFormTs0303HdrRepository.save(formTs0303Hdr);
		}
	}

	@Override
	public byte[] generateReport(TaFormTS0303Vo formTS0303Vo) throws Exception {
		logger.info("generateReport");

		Map<String, Object> params = new HashMap<>();
		params.put("formTsNumber", formTS0303Vo.getFormTsNumber());
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0303Vo.getTaFormTS0303DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS03_03 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	@Override
	public TaFormTS0303Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Set Header
		TaFormTS0303Vo formTS0303Vo = new TaFormTS0303Vo();
		TaFormTs0303Hdr formTs0303Hdr = taFormTs0303HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0303Vo, formTs0303Hdr);

		// Set Detail
		List<TaFormTs0303Dtl> formTs0303DtlList = taFormTs0303DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0303DtlVo> formTS0303DtlVoList = new ArrayList<>();
		TaFormTS0303DtlVo formTS0303DtlVo = null;

		for (TaFormTs0303Dtl formTs0303Dtl : formTs0303DtlList) {
			formTS0303DtlVo = new TaFormTS0303DtlVo();
			formTS0303DtlVo.setFormTs0303DtlId(StringUtils.defaultString(Long.toString(formTs0303Dtl.getFormTs0303DtlId())));
			formTS0303DtlVo.setRecNo(StringUtils.defaultString(formTs0303Dtl.getRecNo()));
			formTS0303DtlVo.setOwnerFullName(StringUtils.defaultString(formTs0303Dtl.getOwnerFullName()));
			formTS0303DtlVo.setNewRegId(StringUtils.defaultString(formTs0303Dtl.getNewRegId()));
			formTS0303DtlVo.setFactoryTypeText(StringUtils.defaultString(formTs0303Dtl.getFactoryTypeText()));
			formTS0303DtlVo.setReqDocNo(StringUtils.defaultString(formTs0303Dtl.getReqDocNo()));
			formTS0303DtlVo.setReqDocDate(formTs0303Dtl.getReqDocDate());
			formTS0303DtlVo.setInformDocNo(StringUtils.defaultString(formTs0303Dtl.getInformDocNo()));
			formTS0303DtlVo.setInformDocDate(formTs0303Dtl.getInformDocDate());
			formTS0303DtlVo.setCallDocNo(StringUtils.defaultString(formTs0303Dtl.getCallDocNo()));
			formTS0303DtlVo.setCallDocDate(formTs0303Dtl.getCallDocDate());
			formTS0303DtlVo.setAuditDateStart(formTs0303Dtl.getAuditDateStart());
			formTS0303DtlVo.setAuditDateEnd(formTs0303Dtl.getAuditDateEnd());
			formTS0303DtlVo.setResultDocNo(StringUtils.defaultString(formTs0303Dtl.getResultDocNo()));
			formTS0303DtlVo.setResultDocDate(formTs0303Dtl.getResultDocDate());
			formTS0303DtlVo.setResultTaxAmt(formTs0303Dtl.getResultTaxAmt());
			formTS0303DtlVo.setResultFineAmt(formTs0303Dtl.getResultFineAmt());
			formTS0303DtlVo.setResultExtraAmt(formTs0303Dtl.getResultExtraAmt());
			formTS0303DtlVo.setResultMoiAmt(formTs0303Dtl.getResultMoiAmt());
			formTS0303DtlVo.setResultNetTaxAmt(formTs0303Dtl.getResultNetTaxAmt());
			formTS0303DtlVo.setAssessmentAmt(formTs0303Dtl.getAssessmentAmt());
			formTS0303DtlVo.setOfficerFullName(StringUtils.defaultString(formTs0303Dtl.getOfficerFullName()));
			formTS0303DtlVo.setOfficerDate(formTs0303Dtl.getOfficerDate());
			formTS0303DtlVo.setOfficerComment(StringUtils.defaultString(formTs0303Dtl.getOfficerComment()));
			formTS0303DtlVoList.add(formTS0303DtlVo);
		}
		formTS0303Vo.setTaFormTS0303DtlVoList(formTS0303DtlVoList);

		return formTS0303Vo;
	}

	private void toEntityDtl(TaFormTs0303Dtl entity, TaFormTS0303DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0303Dtl getEntityById(List<TaFormTs0303Dtl> formTs0303DtlList, String id) {
		TaFormTs0303Dtl formTs0303Dtl = null;
		for (TaFormTs0303Dtl taFormTs0303Dtl : formTs0303DtlList) {
			if (id.equals(taFormTs0303Dtl.getFormTs0303DtlId().toString())) {
				formTs0303Dtl = taFormTs0303Dtl;
				break;
			}
		}
		return formTs0303Dtl;
	}

}
