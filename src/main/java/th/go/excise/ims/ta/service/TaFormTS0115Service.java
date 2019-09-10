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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0115Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0115Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0115DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0115Vo;

@Service
public class TaFormTS0115Service extends AbstractTaFormTSService<TaFormTS0115Vo, TaFormTs0115Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0115Service.class);

	@Autowired
	private TaFormTs0115HdrRepository taFormTs0115HdrRepository;
	@Autowired
	private TaFormTs0115DtlRepository taFormTs0115DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0115HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_15;
	}

	@Override
	public byte[] processFormTS(TaFormTS0115Vo formTS0115Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0115Vo);
		byte[] reportFile = generateReport(formTS0115Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0115Vo formTS0115Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0115Vo.getFormTsNumber());

		TaFormTs0115Hdr formTs0115Hdr = null;
		TaFormTs0115Dtl formTs0115Dtl = null;
		List<TaFormTs0115Dtl> formTs0115DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0115Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0115Vo.getFormTsNumber())) {
			formTsNumber = formTS0115Vo.getFormTsNumber();
			
			// Case Update FormTS

			// ==> Set Hdr
			formTs0115Hdr = taFormTs0115HdrRepository.findByFormTsNumber(formTS0115Vo.getFormTsNumber());
			toEntity(formTs0115Hdr, formTS0115Vo);

			// ==> Set Dtl
			formTs0115DtlList = taFormTs0115DtlRepository.findByFormTsNumber(formTS0115Vo.getFormTsNumber());

			// Set flag Y
			formTs0115DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			if (formTS0115Vo.getTaFormTS0115DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0115DtlVo formTS0115DtlVo : formTS0115Vo.getTaFormTS0115DtlVoList()) {
					formTs0115Dtl = getEntityById(formTs0115DtlList, formTS0115DtlVo.getFormTs0115DtlId());
					if (formTs0115Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0115Dtl, formTS0115DtlVo);
						formTs0115Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0115Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTs0115Dtl = new TaFormTs0115Dtl();
						toEntityDtl(formTs0115Dtl, formTS0115DtlVo);
						formTs0115Dtl.setFormTsNumber(formTS0115Vo.getFormTsNumber());
						formTs0115Dtl.setRecNo(String.valueOf(i));
						formTs0115DtlList.add(formTs0115Dtl);
					}
					i++;
				}
				taFormTs0115DtlRepository.saveAll(formTs0115DtlList);
			}

		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);

			// Set Header Record
			formTs0115Hdr = new TaFormTs0115Hdr();
			toEntity(formTs0115Hdr, formTS0115Vo);
			formTs0115Hdr.setOfficeCode(officeCode);
			formTs0115Hdr.setBudgetYear(budgetYear);
			formTs0115Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs0115DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0115DtlVo formDtl : formTS0115Vo.getTaFormTS0115DtlVoList()) {
				formTs0115Dtl = new TaFormTs0115Dtl();
				toEntityDtl(formTs0115Dtl, formDtl);
				formTs0115Dtl.setFormTsNumber(formTsNumber);
				formTs0115Dtl.setRecNo(String.valueOf(i));
				formTs0115DtlList.add(formTs0115Dtl);
				i++;
			}
			taFormTs0115DtlRepository.saveAll(formTs0115DtlList);
		}
		taFormTs0115HdrRepository.save(formTs0115Hdr);
		
		saveAuditStep(formTS0115Vo, TaFormTS0115Vo.class, TA_FORM_TS_CODE.TS0115, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0115Vo formTS0115Vo) throws Exception {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0115Vo.getFormTsNumber());
		params.put("officeName", formTS0115Vo.getOfficeName());
		params.put("docDate", formTS0115Vo.getDocDate());
		params.put("ownerFullName", formTS0115Vo.getOwnerFullName());
		params.put("factoryType", formTS0115Vo.getFactoryType());
		params.put("factoryName", formTS0115Vo.getFactoryName());
		params.put("newRegId", formTS0115Vo.getNewRegId());
		params.put("facAddrNo", formTS0115Vo.getFacAddrNo());
		params.put("facSoiName", formTS0115Vo.getFacSoiName());
		params.put("facThnName", formTS0115Vo.getFacThnName());
		params.put("facTambolName", formTS0115Vo.getFacTambolName());
		params.put("facAmphurName", formTS0115Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0115Vo.getFacProvinceName());
		params.put("facZipCode", formTS0115Vo.getFacZipCode());
		params.put("signOwnerFullName", formTS0115Vo.getSignOwnerFullName());
		params.put("signOfficerFullName", formTS0115Vo.getSignOfficerFullName());
		params.put("signWitnessFullName1", formTS0115Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0115Vo.getSignWitnessFullName2());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0115Vo.getTaFormTS0115DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_15 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0115Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0115Vo formTS0115Vo = new TaFormTS0115Vo();
		TaFormTs0115Hdr formTs0115Hdr = taFormTs0115HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0115Vo, formTs0115Hdr);

		// Prepare Detail
		List<TaFormTs0115Dtl> formTs0115DtlList = taFormTs0115DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0115DtlVo> formTS0115DtlVoList = new ArrayList<>();
		TaFormTS0115DtlVo formTS0115DtlVo = null;
		for (TaFormTs0115Dtl formTs0115Dtl : formTs0115DtlList) {
			formTS0115DtlVo = new TaFormTS0115DtlVo();
			formTS0115DtlVo.setFormTs0115DtlId(StringUtils.defaultString(Long.toString(formTs0115Dtl.getFormTs0115DtlId())));
			formTS0115DtlVo.setRecNo(StringUtils.defaultString(formTs0115Dtl.getRecNo()));
			formTS0115DtlVo.setDutyTypeText(StringUtils.defaultString(formTs0115Dtl.getDutyTypeText()));
			formTS0115DtlVo.setRecDate(formTs0115Dtl.getRecDate());
			formTS0115DtlVo.setTaxAmt(formTs0115Dtl.getTaxAmt());
			formTS0115DtlVo.setFineAmt(formTs0115Dtl.getFineAmt());
			formTS0115DtlVo.setExtraAmt(formTs0115Dtl.getExtraAmt());
			formTS0115DtlVo.setMoiAmt(formTs0115Dtl.getMoiAmt());
			formTS0115DtlVo.setSumTaxAmt(formTs0115Dtl.getSumTaxAmt());

			formTS0115DtlVoList.add(formTS0115DtlVo);
		}
		formTS0115Vo.setTaFormTS0115DtlVoList(formTS0115DtlVoList);

		return formTS0115Vo;
	}

	private void toEntityDtl(TaFormTs0115Dtl entity, TaFormTS0115DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0115Dtl getEntityById(List<TaFormTs0115Dtl> formTs0115DtlList, String id) {
		TaFormTs0115Dtl formTs0115Dtl = null;

		for (TaFormTs0115Dtl taFormTs0115Dtl : formTs0115DtlList) {
			if (id.equals(taFormTs0115Dtl.getFormTs0115DtlId().toString())) {
				formTs0115Dtl = taFormTs0115Dtl;
				break;
			}
		}

		return formTs0115Dtl;
	}

}
