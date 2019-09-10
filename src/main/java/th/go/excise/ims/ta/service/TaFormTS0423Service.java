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
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0423Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0423Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0423DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0423HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0423DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0423Vo;

@Service
public class TaFormTS0423Service extends AbstractTaFormTSService<TaFormTS0423Vo, TaFormTs0423Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0423Service.class);

	@Autowired
	private TaFormTs0423HdrRepository taFormTs0423HdrRepository;
	@Autowired
	private TaFormTs0423DtlRepository taFormTs0423DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0423HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS04_23;
	}

	@Override
	public byte[] processFormTS(TaFormTS0423Vo formTS0423Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0423Vo);
		byte[] reportFile = generateReport(formTS0423Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0423Vo formTS0423Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0423Vo.getFormTsNumber());

		TaFormTs0423Hdr formTS0423Hdr = null;
		TaFormTs0423Dtl formTS0423Dtl = null;
		List<TaFormTs0423Dtl> formTs0423DtlList = null;
		if (StringUtils.isNotBlank(formTS0423Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0423Vo.getFormTsNumber())) {
			// Case Update FormTS

			// Update Header
			formTS0423Hdr = taFormTs0423HdrRepository.findByFormTsNumber(formTS0423Vo.getFormTsNumber());
			toEntity(formTS0423Hdr, formTS0423Vo);

			// Update Detail
			formTs0423DtlList = taFormTs0423DtlRepository.findByFormTsNumber(formTS0423Vo.getFormTsNumber());

			// Update isDeleted = 'Y' for Default
			formTs0423DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0423Vo.getTaFormTS0423DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0423DtlVo formTS0423DtlVo : formTS0423Vo.getTaFormTS0423DtlVoList()) {
					formTS0423Dtl = getEntityById(formTs0423DtlList, formTS0423DtlVo.getFormTs0423DtlId());
					if (formTS0423Dtl != null) {
						// Exist Page
						toEntityDtl(formTS0423Dtl, formTS0423DtlVo);
						formTS0423Dtl.setIsDeleted(FLAG.N_FLAG);
						formTS0423Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTS0423Dtl = new TaFormTs0423Dtl();
						toEntityDtl(formTS0423Dtl, formTS0423DtlVo);
						formTS0423Dtl.setFormTsNumber(formTS0423Vo.getFormTsNumber());
						formTS0423Dtl.setRecNo(String.valueOf(i));
						formTs0423DtlList.add(formTS0423Dtl);
					}
					i++;
				}
				taFormTs0423DtlRepository.saveAll(formTs0423DtlList);
			}

		} else {
			// Case New FormTS

			// Set Header Record
			formTS0423Hdr = new TaFormTs0423Hdr();
			toEntity(formTS0423Hdr, formTS0423Vo);
			formTS0423Hdr.setOfficeCode(officeCode);
			formTS0423Hdr.setBudgetYear(budgetYear);
			formTS0423Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));

			// Set Detail Record
			formTs0423DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0423DtlVo formTS0423DtlVo : formTS0423Vo.getTaFormTS0423DtlVoList()) {
				formTS0423Dtl = new TaFormTs0423Dtl();
				toEntityDtl(formTS0423Dtl, formTS0423DtlVo);
				formTS0423Dtl.setFormTsNumber(formTS0423Hdr.getFormTsNumber());
				formTS0423Dtl.setRecNo(String.valueOf(i));
				formTs0423DtlList.add(formTS0423Dtl);
				i++;
			}
			taFormTs0423DtlRepository.saveAll(formTs0423DtlList);
		}

		taFormTs0423HdrRepository.save(formTS0423Hdr);
	}

	private void toEntityDtl(TaFormTs0423Dtl entity, TaFormTS0423DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0423Dtl getEntityById(List<TaFormTs0423Dtl> taFormTs0423DtlList, String id) {
		TaFormTs0423Dtl formTs0423Dtl = null;
		for (TaFormTs0423Dtl taFormTs0423Dtl : taFormTs0423DtlList) {
			if (id.equals(taFormTs0423Dtl.getFormTs0423DtlId().toString())) {
				formTs0423Dtl = taFormTs0423Dtl;
				break;
			}
		}
		return formTs0423Dtl;
	}

	@Override
	public byte[] generateReport(TaFormTS0423Vo taFormTS0423Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();

		params.put("formTsNumber", taFormTS0423Vo.getFormTsNumber());
		params.put("alphabet", taFormTS0423Vo.getAlphabet());
		params.put("factoryTypeText", taFormTS0423Vo.getFactoryTypeText());
		params.put("ownerName", taFormTS0423Vo.getOwnerName());
		params.put("newRegId", taFormTS0423Vo.getNewRegId());
		params.put("facAddrNo", taFormTS0423Vo.getFacAddrNo());
		params.put("facSoiName", taFormTS0423Vo.getFacSoiName());
		params.put("facThnName", taFormTS0423Vo.getFacThnName());
		params.put("facTambolName", taFormTS0423Vo.getFacTambolName());
		params.put("facAmphurName", taFormTS0423Vo.getFacAmphurName());
		params.put("facProvinceName", taFormTS0423Vo.getFacProvinceName());

		JRDataSource dataSource = new JRBeanCollectionDataSource(taFormTS0423Vo.getTaFormTS0423DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS04_23 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	@Override
	public TaFormTS0423Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0423Vo formTS0423Vo = new TaFormTS0423Vo();
		TaFormTs0423Hdr formTs01142Hdr = taFormTs0423HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0423Vo, formTs01142Hdr);
		// Prepare Detail
		List<TaFormTs0423Dtl> formTs0423DtlList = taFormTs0423DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0423DtlVo> formTS0423DtlVoList = new ArrayList<>();
		TaFormTS0423DtlVo formTS0423DtlVo = null;
		for (TaFormTs0423Dtl formTs0423Dtl : formTs0423DtlList) {
			formTS0423DtlVo = new TaFormTS0423DtlVo();
			formTS0423DtlVo.setFormTs0423DtlId(StringUtils.defaultString(Long.toString(formTs0423Dtl.getFormTs0423DtlId())));
			formTS0423DtlVo.setRecNo(formTs0423Dtl.getRecNo());
			formTS0423DtlVo.setOperatorOfficeName(StringUtils.defaultString(formTs0423Dtl.getOperatorOfficeName()));
			formTS0423DtlVo.setAuditDateStart(formTS0423DtlVo.getAuditDateStart());
			formTS0423DtlVo.setAuditDateEnd(formTS0423DtlVo.getAuditDateEnd());
			formTS0423DtlVo.setReqDocDate(formTS0423DtlVo.getReqDocDate());
			formTS0423DtlVo.setInformDocDate(formTS0423DtlVo.getInformDocDate());
			formTS0423DtlVo.setAuditReason(StringUtils.defaultString(formTS0423DtlVo.getAuditReason()));
			formTS0423DtlVo.setAuditResult(StringUtils.defaultString(formTS0423DtlVo.getAuditResult()));
			formTS0423DtlVo.setOfficerFullName(StringUtils.defaultString(formTS0423DtlVo.getOfficerFullName()));
			formTS0423DtlVoList.add(formTS0423DtlVo);
		}
		formTS0423Vo.setTaFormTS0423DtlVoList(formTS0423DtlVoList);

		return formTS0423Vo;
	}

}
