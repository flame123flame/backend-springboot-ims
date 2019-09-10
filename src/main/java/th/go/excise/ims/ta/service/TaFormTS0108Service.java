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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0108Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0108Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0108DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0108HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

@Service
public class TaFormTS0108Service extends AbstractTaFormTSService<TaFormTS0108Vo, TaFormTs0108Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	@Autowired
	private TaFormTs0108HdrRepository taFormTs0108HdrRepository;
	@Autowired
	private TaFormTs0108DtlRepository taFormTs0108DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0108HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_08;
	}

	@Override
	public byte[] processFormTS(TaFormTS0108Vo formTS0108Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0108Vo);
		byte[] reportFile = generateReport(formTS0108Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0108Vo formTS0108Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0108Vo.getFormTsNumber());

		TaFormTs0108Hdr formTs0108Hdr = null;
		TaFormTs0108Dtl formTs0108Dtl = null;
		List<TaFormTs0108Dtl> formTs0108DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0108Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0108Vo.getFormTsNumber())) {
			formTsNumber = formTS0108Vo.getFormTsNumber();
			
			// Case Update FormTS
			formTs0108DtlList = taFormTs0108DtlRepository.findByFormTsNumber(formTS0108Vo.getFormTsNumber());

			// Update isDeleted = 'Y' for Default
			formTs0108DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0108Vo.getTaFormTS0108DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0108DtlVo formTS0108DtlVo : formTS0108Vo.getTaFormTS0108DtlVoList()) {
					formTs0108Dtl = getEntityById(formTs0108DtlList, formTS0108DtlVo.getFormTs0108DtlId());
					if (formTs0108Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0108Dtl, formTS0108DtlVo);
						formTs0108Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0108Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTs0108Dtl = new TaFormTs0108Dtl();
						toEntityDtl(formTs0108Dtl, formTS0108DtlVo);
						formTs0108Dtl.setFormTsNumber(formTS0108Vo.getFormTsNumber());
						formTs0108Dtl.setRecNo(String.valueOf(i));
						formTs0108DtlList.add(formTs0108Dtl);
					}
					i++;
				}
				taFormTs0108DtlRepository.saveAll(formTs0108DtlList);
			}

		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);

			// Set Header Record
			formTs0108Hdr = new TaFormTs0108Hdr();
			formTs0108Hdr.setBudgetYear(budgetYear);
			formTs0108Hdr.setOfficeCode(officeCode);
			formTs0108Hdr.setFormTsNumber(formTsNumber);
			formTs0108Hdr.setAuditPlanCode(formTS0108Vo.getAuditPlanCode());
			taFormTs0108HdrRepository.save(formTs0108Hdr);

			// Set Detail Record
			formTs0108DtlList = new ArrayList<>();
			if (formTS0108Vo.getTaFormTS0108DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0108DtlVo formTS0108DtlVo : formTS0108Vo.getTaFormTS0108DtlVoList()) {
					formTs0108Dtl = new TaFormTs0108Dtl();
					toEntityDtl(formTs0108Dtl, formTS0108DtlVo);
					formTs0108Dtl.setFormTsNumber(formTsNumber);
					formTs0108Dtl.setRecNo(String.valueOf(i));
					formTs0108DtlList.add(formTs0108Dtl);
					i++;
				}
				taFormTs0108DtlRepository.saveAll(formTs0108DtlList);
			}
		}
		
		saveAuditStep(formTS0108Vo, TaFormTS0108Vo.class, TA_FORM_TS_CODE.TS0108, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0108Vo formTS0108Vo) throws JRException, IOException {
		logger.info("generateReport");

		Map<String, Object> params = new HashMap<>();
		params.put("formTsNumber", formTS0108Vo.getFormTsNumber());
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0108Vo.getTaFormTS0108DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_08 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	@Override
	public TaFormTS0108Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Set Header
		TaFormTS0108Vo formTS0108Vo = new TaFormTS0108Vo();
		TaFormTs0108Hdr formTs0108Hdr = taFormTs0108HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0108Vo, formTs0108Hdr);

		// Set Detail
		TaFormTS0108DtlVo formTS0108DtlVo = null;
		List<TaFormTS0108DtlVo> formTS0108DtlVoList = new ArrayList<>();
		List<TaFormTs0108Dtl> formTs0108DtlList = taFormTs0108DtlRepository.findByFormTsNumber(formTsNumber);

		for (TaFormTs0108Dtl formTs0108Dtl : formTs0108DtlList) {
			formTS0108DtlVo = new TaFormTS0108DtlVo();
			formTS0108DtlVo.setFormTs0108DtlId(StringUtils.defaultString(Long.toString(formTs0108Dtl.getFormTs0108DtlId())));
			formTS0108DtlVo.setRecNo(StringUtils.defaultString(formTs0108Dtl.getRecNo()));
			formTS0108DtlVo.setAuditDate(formTs0108Dtl.getAuditDate());
			formTS0108DtlVo.setOfficerFullName(StringUtils.defaultString(formTs0108Dtl.getOfficerFullName()));
			formTS0108DtlVo.setOfficerPosition(StringUtils.defaultString(formTs0108Dtl.getOfficerPosition()));
			formTS0108DtlVo.setAuditTime(StringUtils.defaultString(formTs0108Dtl.getAuditTime()));
			formTS0108DtlVo.setAuditDest(StringUtils.defaultString(formTs0108Dtl.getAuditDest()));
			formTS0108DtlVo.setAuditTopic(StringUtils.defaultString(formTs0108Dtl.getAuditTopic()));
			formTS0108DtlVo.setApprovedAck(StringUtils.defaultString(formTs0108Dtl.getApprovedAck()));
			formTS0108DtlVo.setOfficerAck(StringUtils.defaultString(formTs0108Dtl.getOfficerAck()));
			formTS0108DtlVo.setAuditResultDocNo(StringUtils.defaultString(formTs0108Dtl.getAuditResultDocNo()));
			formTS0108DtlVo.setAuditResultDate(formTs0108Dtl.getAuditResultDate());
			formTS0108DtlVo.setAuditComment(StringUtils.defaultString(formTs0108Dtl.getAuditComment()));
			formTS0108DtlVoList.add(formTS0108DtlVo);
		}

		// Sorting
		formTS0108DtlVoList.sort((p1, p2) -> Integer.parseInt(p1.getRecNo()) - Integer.parseInt(p2.getRecNo()));

		formTS0108Vo.setTaFormTS0108DtlVoList(formTS0108DtlVoList);

		return formTS0108Vo;
	}

	private void toEntityDtl(TaFormTs0108Dtl entity, TaFormTS0108DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0108Dtl getEntityById(List<TaFormTs0108Dtl> formTs0108DtlList, String id) {
		TaFormTs0108Dtl formTs0108Dtl = null;
		for (TaFormTs0108Dtl taFormTs0108Dtl : formTs0108DtlList) {
			if (id.equals(taFormTs0108Dtl.getFormTs0108DtlId().toString())) {
				formTs0108Dtl = taFormTs0108Dtl;
				break;
			}
		}
		return formTs0108Dtl;
	}

}
