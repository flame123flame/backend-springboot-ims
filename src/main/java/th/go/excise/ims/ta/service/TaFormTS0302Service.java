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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0302Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0302Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0302DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0302HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0302DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0302Vo;

@Service
public class TaFormTS0302Service extends AbstractTaFormTSService<TaFormTS0302Vo, TaFormTs0302Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0302Service.class);

	@Autowired
	private TaFormTs0302HdrRepository taFormTs0302HdrRepository;
	@Autowired
	private TaFormTs0302DtlRepository taFormTs0302DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0302HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS03_02;
	}

	@Override
	public byte[] processFormTS(TaFormTS0302Vo formTS0302Vo) throws Exception {
		logger.info("processFormTS");
		
		saveFormTS(formTS0302Vo);
		byte[] reportFile = generateReport(formTS0302Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0302Vo formTS0302Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0302Vo.getFormTsNumber());

		TaFormTs0302Hdr formTs0302Hdr = null;
		TaFormTs0302Dtl formTs0302Dtl = null;
		List<TaFormTs0302Dtl> formTs0302DtlList = null;
		if (StringUtils.isNotBlank(formTS0302Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0302Vo.getFormTsNumber())) {
			// Case Update FormTS

			// ==> Set Hdr
			formTs0302Hdr = taFormTs0302HdrRepository.findByFormTsNumber(formTS0302Vo.getFormTsNumber());
			toEntity(formTs0302Hdr, formTS0302Vo);

			// ==> Set Dtl
			formTs0302DtlList = taFormTs0302DtlRepository.findByFormTsNumber(formTS0302Vo.getFormTsNumber());

			// Set flag Y
			formTs0302DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setAuditNo(null);
			});

			if (formTS0302Vo.getTaFormTS0302DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0302DtlVo formTS0302DtlVo : formTS0302Vo.getTaFormTS0302DtlVoList()) {
					formTs0302Dtl = getEntityById(formTs0302DtlList, formTS0302DtlVo.getFormTs0302DtlId());
					if (formTs0302Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0302Dtl, formTS0302DtlVo);
						formTs0302Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0302Dtl.setAuditNo(String.valueOf(i));
					} else {
						// New Page
						formTs0302Dtl = new TaFormTs0302Dtl();
						toEntityDtl(formTs0302Dtl, formTS0302DtlVo);
						formTs0302Dtl.setFormTsNumber(formTS0302Vo.getFormTsNumber());
						formTs0302Dtl.setAuditNo(String.valueOf(i));
						formTs0302DtlList.add(formTs0302Dtl);
					}
					i++;
				}
				taFormTs0302DtlRepository.saveAll(formTs0302DtlList);
			}

		} else {
			// Case New FormTS

			// Set Header Record
			formTs0302Hdr = new TaFormTs0302Hdr();
			toEntity(formTs0302Hdr, formTS0302Vo);
			formTs0302Hdr.setOfficeCode(officeCode);
			formTs0302Hdr.setBudgetYear(budgetYear);
			formTs0302Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));

			// Set Detail Record
			formTs0302DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0302DtlVo formDtl : formTS0302Vo.getTaFormTS0302DtlVoList()) {
				formTs0302Dtl = new TaFormTs0302Dtl();
				toEntityDtl(formTs0302Dtl, formDtl);
				formTs0302Dtl.setFormTsNumber(formTs0302Hdr.getFormTsNumber());
				formTs0302Dtl.setAuditNo(String.valueOf(i));
				formTs0302DtlList.add(formTs0302Dtl);
				i++;
			}
			taFormTs0302DtlRepository.saveAll(formTs0302DtlList);
		}
		taFormTs0302HdrRepository.save(formTs0302Hdr);
	}

	@Override
	public byte[] generateReport(TaFormTS0302Vo formTS0302Vo) throws Exception {
		logger.info("generateReport");
		// get data to report
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("formTsNumber", formTS0302Vo.getFormTsNumber());
		params.put("factoryName", formTS0302Vo.getFactoryName());
		params.put("factoryTypeText", formTS0302Vo.getFactoryTypeText());
		params.put("ownerName", formTS0302Vo.getOwnerName());
		params.put("newRegId", formTS0302Vo.getNewRegId());
		params.put("facAddrNo", formTS0302Vo.getFacAddrNo());
		params.put("facSoiName", formTS0302Vo.getFacSoiName());
		params.put("facThnName", formTS0302Vo.getFacThnName());
		params.put("facTambolName", formTS0302Vo.getFacTambolName());
		params.put("facAmphurName", formTS0302Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0302Vo.getFacProvinceName());
		params.put("assessmentText", formTS0302Vo.getAssessmentText());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0302Vo.getTaFormTS0302DtlVoList());
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS03_02 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0302Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);
		// Prepare Header
		TaFormTS0302Vo formTS0302Vo = new TaFormTS0302Vo();
		TaFormTs0302Hdr formTs0302Hdr = taFormTs0302HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0302Vo, formTs0302Hdr);

		// Prepare Detail
		List<TaFormTs0302Dtl> formTs0302DtlList = taFormTs0302DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0302DtlVo> formTS0302DtlVoList = new ArrayList<>();
		TaFormTS0302DtlVo formTS0302DtlVo = null;
		for (TaFormTs0302Dtl formTs0302Dtl : formTs0302DtlList) {
			formTS0302DtlVo = new TaFormTS0302DtlVo();
			formTS0302DtlVo.setFormTs0302DtlId(StringUtils.defaultString(Long.toString(formTs0302Dtl.getFormTs0302DtlId())));
			formTS0302DtlVo.setAuditNo(StringUtils.defaultString(formTs0302Dtl.getAuditNo()));
			formTS0302DtlVo.setOperatorOfficeName(StringUtils.defaultString(formTs0302Dtl.getOperatorOfficeName()));
			formTS0302DtlVo.setOperatorFullName(StringUtils.defaultString(formTs0302Dtl.getOperatorFullName()));
			formTS0302DtlVo.setRefDocNo(StringUtils.defaultString(formTs0302Dtl.getRefDocNo()));
			formTS0302DtlVo.setRefDocDate(formTs0302Dtl.getRefDocDate());
			formTS0302DtlVo.setAuditDateStart(formTs0302Dtl.getAuditDateStart());
			formTS0302DtlVo.setAuditDateEnd(formTs0302Dtl.getAuditDateEnd());
			formTS0302DtlVo.setAuditStatus(StringUtils.defaultString(formTs0302Dtl.getAuditStatus()));
			formTS0302DtlVo.setAuditStatusDate(formTs0302Dtl.getAuditStatusDate());
			formTS0302DtlVo.setResultDocNo(StringUtils.defaultString(formTs0302Dtl.getResultDocNo()));
			formTS0302DtlVo.setResultDocDate(formTs0302Dtl.getResultDocDate());
			formTS0302DtlVo.setResultTaxAmt(formTs0302Dtl.getResultTaxAmt());
			formTS0302DtlVo.setResultFineAmt(formTs0302Dtl.getResultFineAmt());
			formTS0302DtlVo.setResultExtraAmt(formTs0302Dtl.getResultExtraAmt());
			formTS0302DtlVo.setResultMoiAmt(formTs0302Dtl.getResultMoiAmt());
			formTS0302DtlVo.setResultNetTaxAmt(formTs0302Dtl.getResultNetTaxAmt());
			formTS0302DtlVo.setAssessmentAmt(formTs0302Dtl.getAssessmentAmt());
			formTS0302DtlVo.setOfficerFullName(StringUtils.defaultString(formTs0302Dtl.getOfficerFullName()));
			formTS0302DtlVo.setOfficerDate(formTs0302Dtl.getOfficerDate());
			formTS0302DtlVo.setOfficerComment(StringUtils.defaultString(formTs0302Dtl.getOfficerComment()));
			formTS0302DtlVoList.add(formTS0302DtlVo);
		}
		formTS0302Vo.setTaFormTS0302DtlVoList(formTS0302DtlVoList);

		return formTS0302Vo;
	}

	private void toEntityDtl(TaFormTs0302Dtl entity, TaFormTS0302DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0302Dtl getEntityById(List<TaFormTs0302Dtl> formTs0302DtlList, String id) {
		TaFormTs0302Dtl formTs0302Dtl = null;

		for (TaFormTs0302Dtl taFormTs0302Dtl : formTs0302DtlList) {
			if (id.equals(taFormTs0302Dtl.getFormTs0302DtlId().toString())) {
				formTs0302Dtl = taFormTs0302Dtl;
				break;
			}
		}

		return formTs0302Dtl;
	}

}
