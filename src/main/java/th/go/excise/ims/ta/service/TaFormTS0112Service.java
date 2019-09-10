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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0112Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0112Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0112DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0112HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0112DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;

@Service
public class TaFormTS0112Service extends AbstractTaFormTSService<TaFormTS0112Vo, TaFormTs0112Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0112Service.class);

	@Autowired
	private TaFormTs0112HdrRepository taFormTs0112HdrRepository;
	@Autowired
	private TaFormTs0112DtlRepository taFormTs0112DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0112HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_12;
	}

	@Override
	public byte[] processFormTS(TaFormTS0112Vo formTS0112Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0112Vo);
		byte[] reportFile = generateReport(formTS0112Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0112Vo formTS0112Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0112Vo.getFormTsNumber());

		TaFormTs0112Hdr formTs0112Hdr = null;
		TaFormTs0112Dtl formTs0112Dtl = null;
		List<TaFormTs0112Dtl> formTs0112DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0112Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0112Vo.getFormTsNumber())) {
			formTsNumber = formTS0112Vo.getFormTsNumber();

			// Case Update FormTS

			// ==> Set Hdr
			formTs0112Hdr = taFormTs0112HdrRepository.findByFormTsNumber(formTS0112Vo.getFormTsNumber());
			toEntity(formTs0112Hdr, formTS0112Vo);

			// ==> Set Dtl
			formTs0112DtlList = taFormTs0112DtlRepository.findByFormTsNumber(formTS0112Vo.getFormTsNumber());

			// Set flag Y
			formTs0112DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			if (formTS0112Vo.getTaFormTS0112DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0112DtlVo formTS0112DtlVo : formTS0112Vo.getTaFormTS0112DtlVoList()) {
					formTs0112Dtl = getEntityById(formTs0112DtlList, formTS0112DtlVo.getFormTs0112DtlId());
					if (formTs0112Dtl != null) {
						// Exist Page
						toEntityDtl(formTs0112Dtl, formTS0112DtlVo);
						formTs0112Dtl.setIsDeleted(FLAG.N_FLAG);
						formTs0112Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTs0112Dtl = new TaFormTs0112Dtl();
						toEntityDtl(formTs0112Dtl, formTS0112DtlVo);
						formTs0112Dtl.setFormTsNumber(formTS0112Vo.getFormTsNumber());
						formTs0112Dtl.setRecNo(String.valueOf(i));
						formTs0112DtlList.add(formTs0112Dtl);
					}
					i++;
				}
				taFormTs0112DtlRepository.saveAll(formTs0112DtlList);
			}

		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);

			// Set Header Record
			formTs0112Hdr = new TaFormTs0112Hdr();
			toEntity(formTs0112Hdr, formTS0112Vo);
			formTs0112Hdr.setOfficeCode(officeCode);
			formTs0112Hdr.setBudgetYear(budgetYear);
			formTs0112Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs0112DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0112DtlVo formDtl : formTS0112Vo.getTaFormTS0112DtlVoList()) {
				formTs0112Dtl = new TaFormTs0112Dtl();
				toEntityDtl(formTs0112Dtl, formDtl);
				formTs0112Dtl.setFormTsNumber(formTsNumber);
				formTs0112Dtl.setRecNo(String.valueOf(i));
				formTs0112DtlList.add(formTs0112Dtl);
				i++;
			}
			taFormTs0112DtlRepository.saveAll(formTs0112DtlList);
		}
		taFormTs0112HdrRepository.save(formTs0112Hdr);

		saveAuditStep(formTS0112Vo, TaFormTS0112Vo.class, TA_FORM_TS_CODE.TS0112, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0112Vo formTS0112Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0112Vo.getFormTsNumber());
		params.put("docPlace", formTS0112Vo.getDocPlace());
		params.put("docDate", formTS0112Vo.getDocDate());
		params.put("headOfficerFullName", formTS0112Vo.getHeadOfficerFullName());
		params.put("headOfficerPosition", formTS0112Vo.getHeadOfficerPosition());
		params.put("headOfficerOfficeName", formTS0112Vo.getHeadOfficerOfficeName());
		params.put("factoryName", formTS0112Vo.getFactoryName());
		params.put("newRegId", formTS0112Vo.getNewRegId());
		params.put("facAddrNo", formTS0112Vo.getFacAddrNo());
		params.put("facSoiName", formTS0112Vo.getFacSoiName());
		params.put("facThnName", formTS0112Vo.getFacThnName());
		params.put("facTambolName", formTS0112Vo.getFacTambolName());
		params.put("facAmphurName", formTS0112Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0112Vo.getFacProvinceName());
		params.put("facZipCode", formTS0112Vo.getFacZipCode());
		params.put("ownerFullName1", formTS0112Vo.getOwnerFullName1());
		params.put("ownerPosition", formTS0112Vo.getOwnerPosition());
		params.put("ownerOther", formTS0112Vo.getOwnerOther());
		params.put("lawGroup", formTS0112Vo.getLawGroup());
		params.put("seizeDesc", formTS0112Vo.getSeizeDesc());
		params.put("contactDesc", formTS0112Vo.getContactDesc());
		params.put("ownerFullName2", formTS0112Vo.getOwnerFullName2());
		params.put("ownerPosition2", formTS0112Vo.getOwnerPosition2());
		params.put("ownerOther2", formTS0112Vo.getOwnerOther2());
		params.put("signAuthFullName", formTS0112Vo.getSignAuthFullName());
		params.put("signInspectorFullName", formTS0112Vo.getSignInspectorFullName());
		params.put("signWitnessFullName1", formTS0112Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0112Vo.getSignWitnessFullName2());

		// Prepare Thai Number
		formTS0112Vo.getTaFormTS0112DtlVoList().forEach(e -> {
			e.setRecNoTh(ThaiNumberUtils.toThaiNumber(e.getRecNo()));
		});
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0112Vo.getTaFormTS0112DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_12 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		
		return content;
	}

	@Override
	public TaFormTS0112Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0112Vo formTS0112Vo = new TaFormTS0112Vo();
		TaFormTs0112Hdr formTs0112Hdr = taFormTs0112HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0112Vo, formTs0112Hdr);

		// Prepare Detail
		List<TaFormTs0112Dtl> formTs0112DtlList = taFormTs0112DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0112DtlVo> formTS0112DtlVoList = new ArrayList<>();
		TaFormTS0112DtlVo formTS0112DtlVo = null;
		for (TaFormTs0112Dtl formTs0112Dtl : formTs0112DtlList) {
			formTS0112DtlVo = new TaFormTS0112DtlVo();
			formTS0112DtlVo.setFormTs0112DtlId(StringUtils.defaultString(Long.toString(formTs0112Dtl.getFormTs0112DtlId())));
			formTS0112DtlVo.setRecNo(StringUtils.defaultString(formTs0112Dtl.getRecNo()));
			formTS0112DtlVo.setOfficerFullName(StringUtils.defaultString(formTs0112Dtl.getOfficerFullName()));
			formTS0112DtlVo.setOfficerPosition(StringUtils.defaultString(formTs0112Dtl.getOfficerPosition()));
			formTS0112DtlVoList.add(formTS0112DtlVo);
		}
		formTS0112Vo.setTaFormTS0112DtlVoList(formTS0112DtlVoList);

		return formTS0112Vo;
	}
	
	private void toEntityDtl(TaFormTs0112Dtl entity, TaFormTS0112DtlVo vo) {
		entity.setOfficerFullName(NULL.equalsIgnoreCase(vo.getOfficerFullName()) ? "" : vo.getOfficerFullName());
		entity.setOfficerPosition(NULL.equalsIgnoreCase(vo.getOfficerPosition()) ? "" : vo.getOfficerPosition());
	}
	
	private TaFormTs0112Dtl getEntityById(List<TaFormTs0112Dtl> formTs0112DtlList, String id) {
		TaFormTs0112Dtl formTs0112Dtl = null;

		for (TaFormTs0112Dtl taFormTs0112Dtl : formTs0112DtlList) {
			if (id.equals(taFormTs0112Dtl.getFormTs0112DtlId().toString())) {
				formTs0112Dtl = taFormTs0112Dtl;
				break;
			}
		}

		return formTs0112Dtl;
	}
	
}
