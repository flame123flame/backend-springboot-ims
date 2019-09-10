package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaMedicalReceipt;
import th.go.excise.ims.ia.persistence.entity.IaMedicalWelfare;
import th.go.excise.ims.ia.persistence.repository.IaMedicalReceiptRepository;
import th.go.excise.ims.ia.persistence.repository.IaMedicalWelfareRepository;
import th.go.excise.ims.ia.vo.HospitalVo;
import th.go.excise.ims.ia.vo.Int1200702HdrVo;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;
import th.go.excise.ims.ia.vo.Int120702DtlVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHospital;
import th.go.excise.ims.preferences.persistence.repository.ExciseHospitalRepository;

@Service
public class Int12070102Service {
	private static final Logger logger = LoggerFactory.getLogger(Int12070102Service.class);

	@Autowired
	private ExciseHospitalRepository exciseHospitalRepository;

	@Autowired
	private IaMedicalWelfareRepository iaMedicalWelfareRepository;

	@Autowired
	private IaMedicalReceiptRepository iaMedicalReceiptRepository;

	public List<HospitalVo> getHospital() {
		List<HospitalVo> res = new ArrayList<HospitalVo>();
		List<ExciseHospital> data = exciseHospitalRepository.findAll();
		HospitalVo dataSet = null;
		for (ExciseHospital hospital : data) {
			dataSet = new HospitalVo();
			dataSet.setHospCode(hospital.getHospCode());
			dataSet.setHospName(hospital.getHospName());
			res.add(dataSet);
		}
		return res;
	}

	@Transactional
	public void save(Int1200702HdrVo form) {
		IaMedicalWelfare dataHdrSave = new IaMedicalWelfare();

		if (form.isSelf()) {
			dataHdrSave.setSelfCheck("Y");
		}
		if (form.isCouple()) {
			dataHdrSave.setMateName(form.getMateName());
			dataHdrSave.setMateCitizenId(form.getMateCitizenId());
			dataHdrSave.setCoupleCheck("Y");
		}
		if (form.isFather()) {
			dataHdrSave.setFatherName(form.getFatherName());
			dataHdrSave.setFatherCitizenId(form.getFatherCitizenId());
			dataHdrSave.setFatherCheck("Y");
		}
		if (form.isMother()) {
			dataHdrSave.setMotherName(form.getMotherName());
			dataHdrSave.setMotherCitizenId(form.getMotherCitizenId());
			dataHdrSave.setFatherCheck("Y");
		}
		if (form.isChild1()) {
			dataHdrSave.setBirthdate(ConvertDateUtils.parseStringToDate(form.getBirthdate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName(form.getChildName());
			dataHdrSave.setChildCitizenId(form.getChildCitizenId());
			dataHdrSave.setStatus(form.getStatus());
			dataHdrSave.setChildCheck("Y");
			dataHdrSave.setSiblingsOrder(new BigDecimal(form.getSiblingsOrder()));
		}
		if (form.isChild2()) {
			dataHdrSave.setBirthdate2(ConvertDateUtils.parseStringToDate(form.getBirthdate2(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName2(form.getChildName2());
			dataHdrSave.setChildCitizenId2(form.getChildCitizenId2());
			dataHdrSave.setStatus2(form.getStatus2());
			dataHdrSave.setChild2Check("Y");
			dataHdrSave.setSiblingsOrder2(new BigDecimal(form.getSiblingsOrder2()));
		}
		if (form.isChild3()) {
			dataHdrSave.setBirthdate3(ConvertDateUtils.parseStringToDate(form.getBirthdate3(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName3(form.getChildName3());
			dataHdrSave.setChildCitizenId3(form.getChildCitizenId3());
			dataHdrSave.setStatus3(form.getStatus3());
			dataHdrSave.setChild3Check("Y");
			dataHdrSave.setSiblingsOrder3(new BigDecimal(form.getSiblingsOrder3()));
		}

		dataHdrSave.setFullName(form.getFullName());
		dataHdrSave.setGender(form.getGender());
		dataHdrSave.setPhoneNo(form.getPhoneNumber());

		dataHdrSave.setPosition(form.getPosition());
		dataHdrSave.setAffiliation(form.getAffiliation());
		dataHdrSave.setPhoneNo(form.getPhoneNo());
		dataHdrSave.setDisease(form.getDisease());
		dataHdrSave.setHospitalName(form.getHospitalName());
		dataHdrSave.setHospitalOwner(form.getHospitalOwner());
		dataHdrSave.setTreatedDateFrom(ConvertDateUtils.parseStringToDate(form.getTreatedDateFrom(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataHdrSave.setTreatedDateTo(ConvertDateUtils.parseStringToDate(form.getTreatedDateTo(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataHdrSave.setTotalMoney(new BigDecimal(form.getTotalMoney()));
		dataHdrSave.setReceiptQt(new BigDecimal(form.getReceiptQt()));
		dataHdrSave.setClaimStatus(form.getClaimStatus());
		dataHdrSave.setClaimMoney(new BigDecimal(form.getClaimMoney()));

//		dataHdrSave.setFileId(new BigDecimal(form.getFileId()));
		dataHdrSave.setStatusCheck(form.getStatusCheck());
//		dataHdrSave.setIaDisReqId(new BigDecimal(form.getIaDisReqId()));

		dataHdrSave.setOwnerClaim1(form.getOwnerClaim1());
		dataHdrSave.setOwnerClaim2(form.getOwnerClaim2());
		dataHdrSave.setOwnerClaim3(form.getOwnerClaim3());
		dataHdrSave.setOwnerClaim4(form.getOwnerClaim4());
		dataHdrSave.setOtherClaim1(form.getOtherClaim1());
		dataHdrSave.setOtherClaim2(form.getOtherClaim2());
		dataHdrSave.setOtherClaim3(form.getOtherClaim3());
		dataHdrSave.setOtherClaim4(form.getOtherClaim4());

		iaMedicalWelfareRepository.save(dataHdrSave);
		IaMedicalReceipt dataDtlSave = null;
		for (Int120702DtlVo dataDtl : form.getReceipts()) {
			dataDtlSave = new IaMedicalReceipt();
			dataDtlSave.setId(dataHdrSave.getId());
			dataDtlSave.setReceiptNo(dataDtl.getReceiptNo());
			dataDtlSave.setReceiptAmount(new BigDecimal(dataDtl.getReceiptAmount()));
			dataDtlSave.setReceiptDate(ConvertDateUtils.parseStringToDate(dataDtl.getReceiptDate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataDtlSave.setReceiptType(dataDtl.getReceiptType());
			iaMedicalReceiptRepository.save(dataDtlSave);
		}
	}

	public Int1200702HdrVo findById(Long id) {
		Int1200702HdrVo dataRes = new Int1200702HdrVo();
		IaMedicalWelfare dataHdr = iaMedicalWelfareRepository.findById(id).get();
		dataRes.setFullName(dataHdr.getFullName());
		dataRes.setGender(dataHdr.getGender());
		dataRes.setPhoneNumber(dataHdr.getPhoneNo());

		if ("Y".equals(dataHdr.getSelfCheck())) {
			dataRes.setSelf(true);
		}
		if ("Y".equals(dataHdr.getCoupleCheck())) {
			dataRes.setCouple(true);
			dataRes.setMateName(dataHdr.getMateName());
			dataRes.setMateCitizenId(dataHdr.getMateCitizenId());
		}
		if ("Y".equals(dataHdr.getFatherCheck())) {
			dataRes.setFather(true);
			dataRes.setFatherName(dataHdr.getFatherName());
			dataRes.setFatherCitizenId(dataHdr.getFatherCitizenId());
		}
		if ("Y".equals(dataHdr.getMotherCheck())) {
			dataRes.setMother(true);
			dataRes.setMotherName(dataHdr.getMotherName());
			dataRes.setMotherCitizenId(dataHdr.getMotherCitizenId());
		}
		if ("Y".equals(dataHdr.getChildCheck())) {
			dataRes.setChild1(true);
			dataRes.setChildName(dataHdr.getChildName());
			dataRes.setChildCitizenId(dataHdr.getChildCitizenId());
			dataRes.setStatusCheck(dataHdr.getStatusCheck());
			dataRes.setSiblingsOrder(dataHdr.getSiblingsOrder().toString());
			dataRes.setBirthdate(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
		if ("Y".equals(dataHdr.getChild2Check())) {
			dataRes.setChild2(true);
			dataRes.setChildName2(dataHdr.getChildName2());
			dataRes.setChildCitizenId2(dataHdr.getChildCitizenId2());
			dataRes.setStatus2(dataHdr.getStatus2());
			dataRes.setSiblingsOrder2(dataHdr.getSiblingsOrder2().toString());
			dataRes.setBirthdate2(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate2(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
		if ("Y".equals(dataHdr.getChild3Check())) {
			dataRes.setChild3(true);
			dataRes.setChildName3(dataHdr.getChildName3());
			dataRes.setChildCitizenId3(dataHdr.getChildCitizenId3());
			dataRes.setStatus3(dataHdr.getStatus3());
			dataRes.setSiblingsOrder3(dataHdr.getSiblingsOrder3().toString());
			dataRes.setBirthdate3(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate3(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}

//		dataRes.setBirthdate(dataHdr.getBirthdate().toString());
//		dataRes.setSiblingsOrder(dataHdr.getSiblingsOrder().toString());
		dataRes.setPosition(dataHdr.getPosition());
		dataRes.setAffiliation(dataHdr.getAffiliation());
//		dataRes.setPhoneNo(dataHdr.getPhoneNo());
//		dataRes.setStatus(dataHdr.getStatus());
		dataRes.setDisease(dataHdr.getDisease());
		dataRes.setHospitalName(dataHdr.getHospitalName());
		dataRes.setHospitalOwner(dataHdr.getHospitalOwner());
		dataRes.setTreatedDateFrom(ConvertDateUtils.formatDateToString(dataHdr.getTreatedDateFrom(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataRes.setTreatedDateTo(ConvertDateUtils.formatDateToString(dataHdr.getTreatedDateTo(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataRes.setTotalMoney(dataHdr.getTotalMoney().toString());
		dataRes.setReceiptQt(dataHdr.getReceiptQt().toString());
		dataRes.setClaimStatus(dataHdr.getClaimStatus());
		dataRes.setClaimMoney(dataHdr.getClaimMoney().toString());

		dataRes.setOwnerClaim1(dataHdr.getOwnerClaim1());
		dataRes.setOwnerClaim2(dataHdr.getOwnerClaim2());
		dataRes.setOwnerClaim3(dataHdr.getOwnerClaim3());
		dataRes.setOwnerClaim4(dataHdr.getOwnerClaim4());
		dataRes.setOtherClaim1(dataHdr.getOtherClaim1());
		dataRes.setOtherClaim2(dataHdr.getOtherClaim2());
		dataRes.setOtherClaim3(dataHdr.getOtherClaim3());
		dataRes.setOtherClaim4(dataHdr.getOtherClaim4());

		return dataRes;
	}

	public byte[] exportReport(long id) throws Exception {
		Int1200702HdrVo dataFind = findById(id);

		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		Map<String, Object> params = new HashMap<>();
		params.put("name", dataFind.getFullName());
		params.put("position", dataFind.getPosition());
		params.put("affiliation", dataFind.getAffiliation());
		params.put("self", dataFind.isSelf());
		// set couple data
		params.put("couple", dataFind.isCouple());
		params.put("coupleName", dataFind.getMateName());
		params.put("coupleCitizenId", dataFind.getMateCitizenId());
		// set father data
		params.put("father", dataFind.isFather());
		params.put("fatherName", dataFind.getFatherName());
		params.put("fatherCitizenId", dataFind.getFatherCitizenId());
		// set mother data
		params.put("mother", dataFind.isMother());
		params.put("motherName", dataFind.getMotherName());
		params.put("motherCitizenId", dataFind.getMotherCitizenId());
		// set child1 data
		params.put("child1", dataFind.isChild1());
		params.put("child1Name", dataFind.getChildName());
		params.put("child1CitizenId", dataFind.getChildCitizenId());
		params.put("birthdate1", dataFind.getBirthdate());
		params.put("child1Order", dataFind.getSiblingsOrder());
		params.put("child1Status", dataFind.getStatus());
		// set child2 data
		params.put("child2", dataFind.isChild2());
		params.put("child2Name", dataFind.getChildName2());
		params.put("child2CitizenId", dataFind.getChildCitizenId2());
		params.put("birthdate2", dataFind.getBirthdate2());
		params.put("child2Order", dataFind.getSiblingsOrder2());
		params.put("child2Status", dataFind.getStatus2());
		// set child3 data
		params.put("child3", dataFind.isChild3());
		params.put("child3Name", dataFind.getChildName3());
		params.put("child3CitizenId", dataFind.getChildCitizenId3());
		params.put("birthdate3", dataFind.getBirthdate3());
		params.put("child3Order", dataFind.getSiblingsOrder3());
		params.put("child3Status", dataFind.getStatus3());
		// set hospital data
		params.put("disease", dataFind.getDisease());
		params.put("hospitalName", dataFind.getHospitalName());
		params.put("hospitalOwner", dataFind.getHospitalOwner());
		params.put("treatedDateFrom", dataFind.getTreatedDateFrom());
		params.put("treatedDateTo", dataFind.getTreatedDateTo());
		params.put("totalMoney", decimalFormat.format(new BigDecimal(dataFind.getTotalMoney())));
		params.put("totalMoneyText", ThaiNumberUtils.toThaiBaht(dataFind.getTotalMoney()));
		params.put("ReceiptQt", dataFind.getReceiptQt());
		params.put("claimStatus", dataFind.getClaimStatus());
		params.put("claimMoney", decimalFormat.format(new BigDecimal(dataFind.getClaimMoney())));
		params.put("claimMoneyText", ThaiNumberUtils.toThaiBaht(dataFind.getClaimMoney()));
		
		params.put("ownerClaim1", dataFind.getOwnerClaim1());
		params.put("ownerClaim2", dataFind.getOwnerClaim2());
		params.put("ownerClaim3", dataFind.getOwnerClaim3());
		params.put("ownerClaim4", dataFind.getOwnerClaim4());
		params.put("getOtherClaim1", dataFind.getOtherClaim1());
		params.put("getOtherClaim2", dataFind.getOtherClaim2());
		params.put("getOtherClaim3", dataFind.getOtherClaim3());
		params.put("getOtherClaim4", dataFind.getOtherClaim4());
		
		
//		params.put("billAmountText", ThaiNumberUtils.toThaiBaht(dataFind.getBillAmount().toString()));
//		params.put("salaryText",ThaiNumberUtils.toThaiBaht(dataFind.getSalary().toString()));
//		params.put("notOverText", ThaiNumberUtils.toThaiBaht(dataFind.getNotOver().toString()));
//		params.put("totalWithdrawText", ThaiNumberUtils.toThaiBaht(dataFind.getTotalWithdraw().toString()));
//		params.put("totalMonthThaiNumber", ThaiNumberUtils.toThaiNumber(dataFind.getTotalMonth().toString()));
//		params.put("receiptsNumber", ThaiNumberUtils.toThaiNumber(dataFind.getReceipts().toString()));

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.IA_FORM_7131_NO + "." + FILE_EXTENSION.JASPER,
				params);
		JasperPrint jasperPrint2 = ReportUtils
				.getJasperPrint(REPORT_NAME.IA_FORM_7131_NO_2 + "." + FILE_EXTENSION.JASPER, params);

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

}
