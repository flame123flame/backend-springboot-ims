package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
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
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0111Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0111Hdr;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0111DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0111HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0111DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;

@Service
public class TaFormTS0111Service extends AbstractTaFormTSService<TaFormTS0111Vo, TaFormTs0111Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	@Autowired
	private TaFormTs0111HdrRepository taFormTs0111HdrRepository;
	@Autowired
	private TaFormTs0111DtlRepository taFormTs0111DtlRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0111HdrRepository;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_11;
	}

	@Override
	public byte[] processFormTS(TaFormTS0111Vo formTS0111Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0111Vo);
		byte[] reportFile = generateReport(formTS0111Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0111Vo formTS0111Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0111Vo.getFormTsNumber());

		TaFormTs0111Hdr formTS0111Hdr = null;
		TaFormTs0111Dtl formTS0111Dtl = null;
		List<TaFormTs0111Dtl> formTs0111DtlList = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0111Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0111Vo.getFormTsNumber())) {
			formTsNumber = formTS0111Vo.getFormTsNumber();
			// Case Update FormTS

			// Update Header
			formTS0111Hdr = taFormTs0111HdrRepository.findByFormTsNumber(formTS0111Vo.getFormTsNumber());
			toEntity(formTS0111Hdr, formTS0111Vo);

			// Update Detail
			formTs0111DtlList = taFormTs0111DtlRepository.findByFormTsNumber(formTS0111Vo.getFormTsNumber());

			// Update isDeleted = 'Y' for Default
			formTs0111DtlList.forEach(e -> {
				e.setIsDeleted(FLAG.Y_FLAG);
				e.setRecNo(null);
			});

			// Set Detail Record
			if (formTS0111Vo.getTaFormTS0111DtlVoList() != null) {
				int i = 1;
				for (TaFormTS0111DtlVo formTS0111DtlVo : formTS0111Vo.getTaFormTS0111DtlVoList()) {
					formTS0111Dtl = getEntityById(formTs0111DtlList, formTS0111DtlVo.getFormTs0111DtlId());
					if (formTS0111Dtl != null) {
						// Exist Page
						toEntityDtl(formTS0111Dtl, formTS0111DtlVo);
						formTS0111Dtl.setIsDeleted(FLAG.N_FLAG);
						formTS0111Dtl.setRecNo(String.valueOf(i));
					} else {
						// New Page
						formTS0111Dtl = new TaFormTs0111Dtl();
						toEntityDtl(formTS0111Dtl, formTS0111DtlVo);
						formTS0111Dtl.setFormTsNumber(formTS0111Vo.getFormTsNumber());
						formTS0111Dtl.setRecNo(String.valueOf(i));
						formTs0111DtlList.add(formTS0111Dtl);
					}
					i++;
				}
				taFormTs0111DtlRepository.saveAll(formTs0111DtlList);
			}

		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
			
			// Set Header Record
			formTS0111Hdr = new TaFormTs0111Hdr();
			toEntity(formTS0111Hdr, formTS0111Vo);
			formTS0111Hdr.setOfficeCode(officeCode);
			formTS0111Hdr.setBudgetYear(budgetYear);
			formTS0111Hdr.setFormTsNumber(formTsNumber);

			// Set Detail Record
			formTs0111DtlList = new ArrayList<>();
			int i = 1;
			for (TaFormTS0111DtlVo formTS0111DtlVo : formTS0111Vo.getTaFormTS0111DtlVoList()) {
				formTS0111Dtl = new TaFormTs0111Dtl();
				toEntityDtl(formTS0111Dtl, formTS0111DtlVo);
				formTS0111Dtl.setFormTsNumber(formTS0111Hdr.getFormTsNumber());
				formTS0111Dtl.setRecNo(String.valueOf(i));
				formTs0111DtlList.add(formTS0111Dtl);
				i++;
			}
			taFormTs0111DtlRepository.saveAll(formTs0111DtlList);
		}

		taFormTs0111HdrRepository.save(formTS0111Hdr);
		
		saveAuditStep(formTS0111Vo, TaFormTS0111Vo.class, TA_FORM_TS_CODE.TS0111, formTsNumber);
	}

	@Override
	public byte[] generateReport(TaFormTS0111Vo formTS0111Vo) throws IOException, JRException {
		logger.info("generateReport");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0111Vo.getFormTsNumber());
		params.put("docPlace", formTS0111Vo.getDocPlace());
		params.put("docDate", formTS0111Vo.getDocDate());
		params.put("docTime", formTS0111Vo.getDocTime());
		params.put("officerFullName", formTS0111Vo.getOfficerFullName());
		params.put("officerPosition", formTS0111Vo.getOfficerPosition());
		params.put("factoryName", formTS0111Vo.getFactoryName());
		params.put("newRegId", formTS0111Vo.getNewRegId());
		params.put("factoryType", formTS0111Vo.getFactoryType());
		params.put("facAddrNo", formTS0111Vo.getFacAddrNo());
		params.put("facMooNo", formTS0111Vo.getFacMooNo());
		params.put("facSoiName", formTS0111Vo.getFacSoiName());
		params.put("facThnName", formTS0111Vo.getFacThnName());
		params.put("facTambolName", formTS0111Vo.getFacTambolName());
		params.put("facAmphurName", formTS0111Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0111Vo.getFacProvinceName());
		params.put("facZipCode", formTS0111Vo.getFacZipCode());
		params.put("deliverFullName", formTS0111Vo.getDeliverFullName());
		params.put("deliverPosition", formTS0111Vo.getDeliverPosition());
		params.put("deliverOther", formTS0111Vo.getDeliverOther());
		params.put("refBookNumber1", formTS0111Vo.getRefBookNumber1());
		params.put("refBookNumber2", formTS0111Vo.getRefBookNumber2());
		params.put("refDocDate", formTS0111Vo.getRefDocDate());
		params.put("taFormTS0111DtlVoList", formTS0111Vo.getTaFormTS0111DtlVoList());
		params.put("signAuthFullName1", formTS0111Vo.getSignAuthFullName1());
		params.put("signWitnessFullName1", formTS0111Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0111Vo.getSignWitnessFullName2());

		logger.info("export TA_FORM_TS01_11P2");
		params.put("authFullName1", formTS0111Vo.getAuthFullName1());
		params.put("signAuthFullName2", formTS0111Vo.getSignAuthFullName2());
		params.put("signWitnessFullName3", formTS0111Vo.getSignWitnessFullName3());
		params.put("signWitnessFullName4", formTS0111Vo.getSignWitnessFullName4());
		params.put("authFullName2", formTS0111Vo.getAuthFullName2());
		params.put("authPosition", formTS0111Vo.getAuthPosition());
		params.put("authPositionOther", formTS0111Vo.getAuthPositionOther());
		params.put("authFrom", formTS0111Vo.getAuthFrom());
		params.put("authDate", formTS0111Vo.getAuthDate());
		params.put("signAuthFullName3", formTS0111Vo.getSignAuthFullName3());
		params.put("signAuthFullName4", formTS0111Vo.getSignAuthFullName4());
		params.put("signWitnessFullName5", formTS0111Vo.getSignWitnessFullName5());
		params.put("signWitnessFullName6", formTS0111Vo.getSignWitnessFullName6());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0111Vo.getTaFormTS0111DtlVoList());

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_11 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_11P2 + "." + FILE_EXTENSION.JASPER, params);

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public TaFormTS0111Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		// Prepare Header
		TaFormTS0111Vo formTS0111Vo = new TaFormTS0111Vo();
		TaFormTs0111Hdr formTs0111Hdr = taFormTs0111HdrRepository.findByFormTsNumber(formTsNumber);
		toVo(formTS0111Vo, formTs0111Hdr);

		// Prepare Detail
		List<TaFormTs0111Dtl> formTs0111DtlList = taFormTs0111DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0111DtlVo> formTS0111DtlVoList = new ArrayList<>();
		TaFormTS0111DtlVo formTS0111DtlVo = null;
		for (TaFormTs0111Dtl formTs0111Dtl : formTs0111DtlList) {
			formTS0111DtlVo = new TaFormTS0111DtlVo();
			toVoDtl(formTS0111DtlVo, formTs0111Dtl);
			formTS0111DtlVo.setDocName(StringUtils.defaultString(formTS0111DtlVo.getDocName()));
			formTS0111DtlVo.setDocQty(StringUtils.defaultString(formTS0111DtlVo.getDocQty()));
			formTS0111DtlVo.setDocComment(StringUtils.defaultString(formTS0111DtlVo.getDocComment()));
			formTS0111DtlVoList.add(formTS0111DtlVo);
		}
		formTS0111Vo.setTaFormTS0111DtlVoList(formTS0111DtlVoList);

		return formTS0111Vo;
	}

	private void toEntityDtl(TaFormTs0111Dtl entity, TaFormTS0111DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private void toVoDtl(TaFormTS0111DtlVo vo, TaFormTs0111Dtl entity) {
		try {
			BeanUtils.copyProperties(vo, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0111Dtl getEntityById(List<TaFormTs0111Dtl> taFormTs0111DtlList, String id) {
		TaFormTs0111Dtl formTs0111Dtl = null;

		for (TaFormTs0111Dtl taFormTs0111Dtl : taFormTs0111DtlList) {
			if (id.equals(taFormTs0111Dtl.getFormTs0111DtlId().toString())) {
				formTs0111Dtl = taFormTs0111Dtl;
				break;
			}
		}

		return formTs0111Dtl;
	}

}
