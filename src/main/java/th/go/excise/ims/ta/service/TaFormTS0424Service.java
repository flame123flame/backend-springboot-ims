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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0424Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0424Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0424DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0424HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0424DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0424Vo;

@Service
public class TaFormTS0424Service extends AbstractTaFormTSService<TaFormTS0424Vo, TaFormTs0424Hdr>  {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0424Service.class);
	
	@Autowired
	private TaFormTs0424HdrRepository taFormTs0424HdrRepository;
	@Autowired
	private TaFormTs0424DtlRepository taFormTs0424DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0424HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS04_24;
	}

	@Override
	public byte[] processFormTS(TaFormTS0424Vo formTS0424Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0424Vo);
		byte[] reportFile = generateReport(formTS0424Vo);

		return reportFile;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0424Vo formTS0424Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0424Vo.getFormTsNumber());

		TaFormTs0424Hdr formTS0424Hdr = null;
		TaFormTs0424Dtl formTS0424Dtl = null;
		List<TaFormTs0424Dtl> formTs0424DtlList = null;
		if (StringUtils.isNotBlank(formTS0424Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0424Vo.getFormTsNumber())) {
			// Case Update FormTS

			// Update Header
			formTS0424Hdr = taFormTs0424HdrRepository.findByFormTsNumber(formTS0424Vo.getFormTsNumber());
			toEntity(formTS0424Hdr, formTS0424Vo);

			// Update Detail
			formTs0424DtlList = taFormTs0424DtlRepository.findByFormTsNumber(formTS0424Vo.getFormTsNumber());

			// Update isDeleted = 'Y' for Default
			formTs0424DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0424Vo.getTaFormTS0424DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0424DtlVo formTS0424DtlVo : formTS0424Vo.getTaFormTS0424DtlVoList()) {
					formTS0424Dtl = getEntityById(formTs0424DtlList, formTS0424DtlVo.getFormTs0424DtlId());
					if (formTS0424Dtl != null) {
						// Exist Page
						toEntityDtl(formTS0424Dtl, formTS0424DtlVo);
						formTS0424Dtl.setIsDeleted(FLAG.N_FLAG);
						formTS0424Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTS0424Dtl = new TaFormTs0424Dtl();
						toEntityDtl(formTS0424Dtl, formTS0424DtlVo);
						formTS0424Dtl.setFormTsNumber(formTS0424Vo.getFormTsNumber());
						formTS0424Dtl.setRecNo(String.valueOf(i));
						formTs0424DtlList.add(formTS0424Dtl);
					}
					i++;
				}
				taFormTs0424DtlRepository.saveAll(formTs0424DtlList);
			}

		} else {
			// Case New FormTS

			// Set Header Record
			formTS0424Hdr = new TaFormTs0424Hdr();
			toEntity(formTS0424Hdr, formTS0424Vo);
			formTS0424Hdr.setOfficeCode(officeCode);
			formTS0424Hdr.setBudgetYear(budgetYear);
			formTS0424Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));

			// Set Detail Record
			formTs0424DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0424DtlVo formTS0424DtlVo : formTS0424Vo.getTaFormTS0424DtlVoList()) {
				formTS0424Dtl = new TaFormTs0424Dtl();
				toEntityDtl(formTS0424Dtl, formTS0424DtlVo);
				formTS0424Dtl.setFormTsNumber(formTS0424Hdr.getFormTsNumber());
				formTS0424Dtl.setRecNo(String.valueOf(i));
				formTs0424DtlList.add(formTS0424Dtl);
				i++;
			}
			taFormTs0424DtlRepository.saveAll(formTs0424DtlList);
		}

		taFormTs0424HdrRepository.save(formTS0424Hdr);
	}

	private void toEntityDtl(TaFormTs0424Dtl entity, TaFormTS0424DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0424Dtl getEntityById(List<TaFormTs0424Dtl> taFormTs0424DtlList, String id) {
		TaFormTs0424Dtl formTs0424Dtl = null;
		for (TaFormTs0424Dtl taFormTs0424Dtl : taFormTs0424DtlList) {
			if (id.equals(taFormTs0424Dtl.getFormTs0424DtlId().toString())) {
				formTs0424Dtl = taFormTs0424Dtl;
				break;
			}
		}
		return formTs0424Dtl;
	}
	
	@Override
	public byte[] generateReport(TaFormTS0424Vo taFormTS0424Vo) throws Exception, IOException {
		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("formTsNumber", taFormTS0424Vo.getFormTsNumber());
		params.put("factoryName",taFormTS0424Vo.getFactoryName());
		params.put("auditMonthStart", StringUtils.isNotEmpty(taFormTS0424Vo.getAuditMonthStart()) ? ApplicationCache.getParamInfoByCode("MONTH_LIST", taFormTS0424Vo.getAuditMonthStart()).getValue1() : null);
		params.put("auditMonthEnd",StringUtils.isNoneEmpty(taFormTS0424Vo.getAuditMonthEnd()) ? ApplicationCache.getParamInfoByCode("MONTH_LIST", taFormTS0424Vo.getAuditMonthEnd()).getValue1() : null);
		params.put("auditYear",taFormTS0424Vo.getAuditYear());
		
        JRDataSource dataSource = new JRBeanCollectionDataSource(taFormTS0424Vo.getTaFormTS0424DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS04_24 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		
		return reportFile;
	}
	
	@Override
	public TaFormTS0424Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0424Vo formTS0424Vo = new TaFormTS0424Vo();
		TaFormTs0424Hdr formTs01142Hdr = taFormTs0424HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0424Vo, formTs01142Hdr);
		// Prepare Detail
		List<TaFormTs0424Dtl> formTs0424DtlList = taFormTs0424DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0424DtlVo> formTS0424DtlVoList = new ArrayList<>();
		TaFormTS0424DtlVo formTS0424DtlVo = null;
		for (TaFormTs0424Dtl formTs0424Dtl : formTs0424DtlList) {
			formTS0424DtlVo = new TaFormTS0424DtlVo();
			formTS0424DtlVo.setRecNo(formTs0424Dtl.getRecNo());
			formTS0424DtlVo.setOperatorOfficeName(StringUtils.defaultString(formTs0424Dtl.getOperatorOfficeName()));
			formTS0424DtlVo.setOperatorFullName(StringUtils.defaultString(formTs0424Dtl.getOperatorFullName()));
			formTS0424DtlVo.setOwnerFullName(StringUtils.defaultString(formTs0424Dtl.getOwnerFullName()));
			formTS0424DtlVo.setNewRegId(StringUtils.defaultString(formTs0424Dtl.getNewRegId()));
			formTS0424DtlVo.setFactoryTypeText(StringUtils.defaultString(formTs0424Dtl.getFactoryTypeText()));
			formTS0424DtlVo.setCallDocNo(StringUtils.defaultString(formTs0424Dtl.getCallDocNo()));
			formTS0424DtlVo.setAuditDateStart(formTs0424Dtl.getAuditDateStart());
			formTS0424DtlVo.setAuditDateEnd(formTs0424Dtl.getAuditDateEnd());
			formTS0424DtlVo.setTaxAmt(formTs0424Dtl.getTaxAmt());
			formTS0424DtlVo.setFineAmt(formTs0424Dtl.getFineAmt());
			formTS0424DtlVo.setExtraAmt(formTs0424Dtl.getExtraAmt());
			formTS0424DtlVo.setMoiAmt(formTs0424Dtl.getMoiAmt());
			formTS0424DtlVo.setNettaxAmt(formTs0424Dtl.getNettaxAmt());
			formTS0424DtlVo.setResidueNum(formTs0424Dtl.getResidueNum());
			formTS0424DtlVo.setOfficerComment(StringUtils.defaultString(formTs0424Dtl.getOfficerComment()));
			formTS0424DtlVoList.add(formTS0424DtlVo);
		}
		formTS0424Vo.setTaFormTS0424DtlVoList(formTS0424DtlVoList);

		return formTS0424Vo;
	}
	
}
