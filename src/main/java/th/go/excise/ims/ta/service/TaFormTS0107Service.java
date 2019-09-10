package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0107Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0107Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0107DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0107HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0107DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

@Service
public class TaFormTS0107Service extends AbstractTaFormTSService<TaFormTS0107Vo, TaFormTs0107Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	@Autowired
	private TaFormTs0107HdrRepository taFormTs0107HdrRepository;
	@Autowired
	private TaFormTs0107DtlRepository taFormTs0107DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0107HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_07;
	}

	@Override
	public byte[] processFormTS(TaFormTS0107Vo formTS0107Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0107Vo);
		byte[] reportFile = generateReport(formTS0107Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0107Vo formTS0107Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0107Vo.getFormTsNumber());
		
		TaFormTs0107Hdr formTs0107Hdr = null;
		TaFormTs0107Dtl formTs0107Dtl = null;
		List<TaFormTs0107Dtl> formTs0107DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0107Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0107Vo.getFormTsNumber())) {
			formTsNumber = formTS0107Vo.getFormTsNumber();
			
			// Case Update FormTS

			// ==> Set Hdr
			formTs0107Hdr = taFormTs0107HdrRepository.findByFormTsNumber(formTS0107Vo.getFormTsNumber());
			toEntity(formTs0107Hdr, formTS0107Vo);

			// ==> Set Dtl
			formTs0107DtlList = taFormTs0107DtlRepository.findByFormTsNumber(formTS0107Vo.getFormTsNumber());

			// Set flag Y
			formTs0107DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			if (formTS0107Vo.getTaFormTS0107DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0107DtlVo formTS0107DtlVo : formTS0107Vo.getTaFormTS0107DtlVoList()) {
					formTs0107Dtl = getEntityById(formTs0107DtlList, formTS0107DtlVo.getFormTs0107DtlId());
					if (formTs0107Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0107Dtl, formTS0107DtlVo);
						formTs0107Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0107Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTs0107Dtl = new TaFormTs0107Dtl();
						toEntityDtl(formTs0107Dtl, formTS0107DtlVo);
						formTs0107Dtl.setFormTsNumber(formTS0107Vo.getFormTsNumber());
						formTs0107Dtl.setRecNo(String.valueOf(i));
						formTs0107DtlList.add(formTs0107Dtl);
					}
					i++;
				}
				taFormTs0107DtlRepository.saveAll(formTs0107DtlList);
			}

		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);

			// Set Header Record
			formTs0107Hdr = new TaFormTs0107Hdr();
			toEntity(formTs0107Hdr, formTS0107Vo);
			formTs0107Hdr.setOfficeCode(officeCode);
			formTs0107Hdr.setBudgetYear(budgetYear);
			formTs0107Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs0107DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0107DtlVo formDtl : formTS0107Vo.getTaFormTS0107DtlVoList()) {
				formTs0107Dtl = new TaFormTs0107Dtl();
				toEntityDtl(formTs0107Dtl, formDtl);
				formTs0107Dtl.setFormTsNumber(formTsNumber);
				formTs0107Dtl.setRecNo(String.valueOf(i));
				formTs0107DtlList.add(formTs0107Dtl);
				i++;
			}
			taFormTs0107DtlRepository.saveAll(formTs0107DtlList);
		}
		taFormTs0107HdrRepository.save(formTs0107Hdr);
		
		saveAuditStep(formTS0107Vo, TaFormTS0107Vo.class, TA_FORM_TS_CODE.TS0107, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0107Vo formTS0107Vo) throws Exception, IOException {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0107Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0107Vo.getBookNumber1());
		params.put("bookNumber2", formTS0107Vo.getBookNumber2());
		params.put("officeName1", formTS0107Vo.getOfficeName1());
		params.put("docDate", formTS0107Vo.getDocDate());
		params.put("officeName2", formTS0107Vo.getOfficeName2());
		params.put("headOfficerFullName", formTS0107Vo.getHeadOfficerFullName());
		params.put("headOfficerPosition", formTS0107Vo.getHeadOfficerPosition());
		params.put("companyName", formTS0107Vo.getCompanyName());
		params.put("factoryType", formTS0107Vo.getFactoryType());
		params.put("factoryName", formTS0107Vo.getFactoryName());
		params.put("newRegId", formTS0107Vo.getNewRegId());
		params.put("facAddrNo", formTS0107Vo.getFacAddrNo());
		params.put("facMooNo", formTS0107Vo.getFacMooNo());
		params.put("facSoiName", formTS0107Vo.getFacSoiName());
		params.put("facThnName", formTS0107Vo.getFacThnName());
		params.put("facTambolName", formTS0107Vo.getFacTambolName());
		params.put("facAmphurName", formTS0107Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0107Vo.getFacProvinceName());
		params.put("facZipCode", formTS0107Vo.getFacZipCode());
		params.put("auditDate", formTS0107Vo.getAuditDate());
		params.put("lawSection", formTS0107Vo.getLawSection());
		params.put("headOfficerPhone", formTS0107Vo.getHeadOfficerPhone());
		params.put("signOfficerFullName", formTS0107Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0107Vo.getSignOfficerPosition());
		params.put("otherText", formTS0107Vo.getOtherText());
		params.put("otherPhone", formTS0107Vo.getOtherPhone());
		
		// Prepare Thai Number
		formTS0107Vo.getTaFormTS0107DtlVoList().forEach(e -> {
			e.setRecNoTh(ThaiNumberUtils.toThaiNumber(e.getRecNo()));
		});
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0107Vo.getTaFormTS0107DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0107Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0107Vo formTS0107Vo = new TaFormTS0107Vo();
		TaFormTs0107Hdr formTs0107Hdr = taFormTs0107HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0107Vo, formTs0107Hdr);

		// Prepare Detail
		List<TaFormTs0107Dtl> formTs0107DtlList = taFormTs0107DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0107DtlVo> formTS0107DtlVoList = new ArrayList<>();
		TaFormTS0107DtlVo formTS0107DtlVo = null;
		for (TaFormTs0107Dtl formTs0107Dtl : formTs0107DtlList) {
			formTS0107DtlVo = new TaFormTS0107DtlVo();
			formTS0107DtlVo.setFormTs0107DtlId(StringUtils.defaultString(Long.toString(formTs0107Dtl.getFormTs0107DtlId())));
			formTS0107DtlVo.setRecNo(StringUtils.defaultString(formTs0107Dtl.getRecNo()));
			formTS0107DtlVo.setOfficerFullName(StringUtils.defaultString(formTs0107Dtl.getOfficerFullName()));
			formTS0107DtlVo.setOfficerPosition(StringUtils.defaultString(formTs0107Dtl.getOfficerPosition()));
			formTS0107DtlVoList.add(formTS0107DtlVo);
		}
		formTS0107Vo.setTaFormTS0107DtlVoList(formTS0107DtlVoList);

		return formTS0107Vo;
	}
	
	private void toEntityDtl(TaFormTs0107Dtl entity, TaFormTS0107DtlVo vo) {
		entity.setOfficerFullName(NULL.equalsIgnoreCase(vo.getOfficerFullName()) ? "" : vo.getOfficerFullName());
		entity.setOfficerPosition(NULL.equalsIgnoreCase(vo.getOfficerPosition()) ? "" : vo.getOfficerPosition());
	}
	
	private TaFormTs0107Dtl getEntityById(List<TaFormTs0107Dtl> formTs0107DtlList, String id) {
		TaFormTs0107Dtl formTs0107Dtl = null;

		for (TaFormTs0107Dtl taFormTs0107Dtl : formTs0107DtlList) {
			if (id.equals(taFormTs0107Dtl.getFormTs0107DtlId().toString())) {
				formTs0107Dtl = taFormTs0107Dtl;
				break;
			}
		}

		return formTs0107Dtl;
	}

}
