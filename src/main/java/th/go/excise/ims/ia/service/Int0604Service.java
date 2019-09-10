package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpD;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicexpDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicexpHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0604JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.AuditLicexpDVo;
import th.go.excise.ims.ia.vo.AuditLicexpHVo;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0604SaveVo;

@Service
public class Int0604Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0604Service.class);

	@Autowired
	private Int0604JdbcRepository int0604JdbcRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditLicexpHRepository auditLicexpHRepository;

	@Autowired
	private IaAuditLicexpDRepository auditLicexpDRepository;

	public List<AuditLicexpDVo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriteria");
		
		List<AuditLicexpDVo> data = int0604JdbcRepository.findByCriteria(int0602FormVo);
		
		return data;
	}

	public List<AuditLicexpHVo> findAuditLicdupHList() {
		List<IaAuditLicexpH> iaAuditLicexpHList = auditLicexpHRepository.findIaAuditLicexpHAllDataActive();
		AuditLicexpHVo auditLicexpHVo = null;
		List<AuditLicexpHVo> auditLicexpHVoList = new ArrayList<>();
		for (IaAuditLicexpH iaAuditLicexpH : iaAuditLicexpHList) {
			auditLicexpHVo = new AuditLicexpHVo();
			try {
				auditLicexpHVo.setAuditLicexpSeq(iaAuditLicexpH.getAuditLicexpSeq());
				auditLicexpHVo.setOfficeCode(iaAuditLicexpH.getOfficeCode());
				auditLicexpHVo.setLicexpDateFrom(iaAuditLicexpH.getLicexpDateFrom() != null ? ConvertDateUtils.formatDateToString(iaAuditLicexpH.getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicexpHVo.setLicexpDateTo(iaAuditLicexpH.getLicexpDateTo() != null ? ConvertDateUtils.formatDateToString(iaAuditLicexpH.getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicexpHVo.setAuditLicexpNo(iaAuditLicexpH.getAuditLicexpNo());
				auditLicexpHVo.setAuditFlag(iaAuditLicexpH.getAuditFlag());
				auditLicexpHVo.setConditionText(iaAuditLicexpH.getConditionText());
				auditLicexpHVo.setCriteriaText(iaAuditLicexpH.getCriteriaText());
				auditLicexpHVoList.add(auditLicexpHVo);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		return auditLicexpHVoList;
	}

	public AuditLicexpHVo save(Int0604SaveVo vo) {
		logger.info("saveExpListService : {} ", vo.getAuditLicexpH().getAuditLicexpNo());
		IaAuditLicexpH licexpH = null;
		try {
			if (StringUtils.isNotBlank(vo.getAuditLicexpH().getAuditLicexpNo())) {
				licexpH = new IaAuditLicexpH();
				licexpH = auditLicexpHRepository.findByAuditLicexpNo(vo.getAuditLicexpH().getAuditLicexpNo());
				licexpH.setAuditFlag(vo.getAuditLicexpH().getAuditFlag());
				licexpH.setConditionText(vo.getAuditLicexpH().getConditionText());
				licexpH.setCriteriaText(vo.getAuditLicexpH().getCriteriaText());
				licexpH = auditLicexpHRepository.save(licexpH);
				vo.getAuditLicexpH().setAuditLicexpSeq(licexpH.getAuditLicexpSeq());
				vo.getAuditLicexpH().setAuditLicexpNo(licexpH.getAuditLicexpNo());
			} else {
				licexpH = new IaAuditLicexpH();
				licexpH.setOfficeCode(vo.getAuditLicexpH().getOfficeCode());
				licexpH.setLicexpDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditLicexpH().getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				licexpH.setLicexpDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditLicexpH().getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));				
				licexpH.setAuditLicexpNo(iaCommonService.autoGetRunAuditNoBySeqName("LIE", vo.getAuditLicexpH().getOfficeCode(), "AUDIT_LICEXP_SEQ", 8));	
				licexpH.setAuditFlag(vo.getAuditLicexpH().getAuditFlag());
				licexpH.setConditionText(vo.getAuditLicexpH().getConditionText());
				licexpH.setCriteriaText(vo.getAuditLicexpH().getCriteriaText());
				licexpH = auditLicexpHRepository.save(licexpH);
				vo.getAuditLicexpH().setAuditLicexpSeq(licexpH.getAuditLicexpSeq());
				vo.getAuditLicexpH().setAuditLicexpNo(licexpH.getAuditLicexpNo());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (vo.getAuditLicexpDList() != null && vo.getAuditLicexpDList().size() > 0) {
			IaAuditLicexpD val = null;
			List<IaAuditLicexpD> auditLicexpDList = new ArrayList<>();
			for (AuditLicexpDVo data : vo.getAuditLicexpDList()) {
				val = new IaAuditLicexpD();
				if (data.getAuditLicexpSeq() != null) {
					val = auditLicexpDRepository.findById(data.getAuditLicexpSeq()).get();
					try {
						val = auditLicexpDRepository.save(val);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				} else {
					try {
						val.setOfficeCode(vo.getAuditLicexpH().getOfficeCode());
						val.setAuditLicexpNo(vo.getAuditLicexpH().getAuditLicexpNo());
						val.setNewRegId(data.getNewRegId());
						val.setCusFullName(data.getCusFullName());
						val.setFacFullName(data.getFacFullName());
						val.setLicType(data.getLicType());
						val.setLicNo(data.getLicNo());
						val.setLicDate(ConvertDateUtils.parseStringToDate(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setExpDate(ConvertDateUtils.parseStringToDate(data.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setLicDateNew(ConvertDateUtils.parseStringToDate(data.getLicDateNew(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setLicNoNew(data.getLicNoNew());
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					auditLicexpDList.add(val);
				}

			}
			auditLicexpDRepository.saveAll(auditLicexpDList);
		}

		return vo.getAuditLicexpH();
	}

	public List<AuditLicexpDVo> findAuditLicexpDByAuditLicdupNo(String auditLicexpNo) throws Exception {
		List<AuditLicexpDVo> auditLicexpDVoList = new ArrayList<>();
		AuditLicexpDVo auditLicexpDVo = null;
		List<IaAuditLicexpD> auditLicdupDList = auditLicexpDRepository.findByAuditLicexpNoOrderByExpDate(auditLicexpNo);
		for (IaAuditLicexpD data : auditLicdupDList) {
			auditLicexpDVo = new AuditLicexpDVo();
			try {
				auditLicexpDVo.setAuditLicexpSeq(data.getAuditLicexpSeq());
				auditLicexpDVo.setAuditLicexpNo(data.getAuditLicexpNo());
				auditLicexpDVo.setOfficeCode(data.getOfficeCode());
				auditLicexpDVo.setNewRegId(data.getNewRegId());
				auditLicexpDVo.setCusFullName(data.getCusFullName());
				auditLicexpDVo.setFacFullName(data.getFacFullName());
				auditLicexpDVo.setLicType(data.getLicType());
				auditLicexpDVo.setLicNo(data.getLicNo());
				auditLicexpDVo.setLicDate(ConvertDateUtils.formatDateToString(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVo.setExpDate(ConvertDateUtils.formatDateToString(data.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVo.setLicNoNew(data.getLicNoNew());
				auditLicexpDVo.setLicDateNew(ConvertDateUtils.formatDateToString(data.getLicDateNew(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVoList.add(auditLicexpDVo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return auditLicexpDVoList;
	}

	public AuditLicexpHVo findByAuditLicexpNo(String auditLicexpNo) {
		AuditLicexpHVo auditLicexpHVo = null;
		IaAuditLicexpH data = null;
		ExciseDepartmentVo excise = null;
		data = auditLicexpHRepository.findByAuditLicexpNo(auditLicexpNo);
		try {
			auditLicexpHVo = new AuditLicexpHVo();
			auditLicexpHVo.setAuditLicexpSeq(data.getAuditLicexpSeq());
			auditLicexpHVo.setAuditFlag(data.getAuditFlag());
			auditLicexpHVo.setAuditLicexpNo(data.getAuditLicexpNo());
			auditLicexpHVo.setConditionText(data.getConditionText());
			auditLicexpHVo.setCriteriaText(data.getCriteriaText());
			auditLicexpHVo.setLicexpDateFrom(ConvertDateUtils.formatDateToString(data.getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicexpHVo.setLicexpDateTo(ConvertDateUtils.formatDateToString(data.getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicexpHVo.setOfficeCode(data.getOfficeCode());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getOfficeCode());
			auditLicexpHVo.setArea(excise.getArea());
			auditLicexpHVo.setSector(excise.getSector());
			auditLicexpHVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return auditLicexpHVo;
	}

	public byte[] export(String auditLicexpNo) {

		// hdr
		IaAuditLicexpH licH = null;
		licH = new IaAuditLicexpH();
		licH = auditLicexpHRepository.findByAuditLicexpNo(auditLicexpNo);

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ตรวจสอบการต่อใบอนุญาต");
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle wrapText = ExcelUtils.createWrapTextStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "เลขทะเบียนสรรพสามิต", "ชื่อผู้ประกอบการ", "ชื่อสถานประกอบการ", "ประเภทใบอนุญาต", "เลขที่ใบอนุญาต", "วันที่ออกใบอนุญาต ", "วันที่หมดอายุ", "วันที่ออกใบอนุญาตใหม่", "เลขที่ใบอนุญาตใหม่" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		List<IaAuditLicexpD> auditLicexpDList = auditLicexpDRepository.findByAuditLicexpNoOrderByExpDate(auditLicexpNo);
		for (IaAuditLicexpD data : auditLicexpDList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getNewRegId())) {
				cell.setCellValue(data.getNewRegId());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getCusFullName())) {
				cell.setCellValue(data.getCusFullName());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getFacFullName())) {
				cell.setCellValue(data.getFacFullName());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getLicType())) {
				cell.setCellValue(data.getLicType());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getLicNo())) {
				cell.setCellValue(data.getLicNo());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicDate() != null) {
				cell.setCellValue(ConvertDateUtils.formatDateToString(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getExpDate() != null) {
				cell.setCellValue(ConvertDateUtils.formatDateToString(data.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicDateNew() != null) {
				cell.setCellValue(ConvertDateUtils.formatDateToString(data.getLicDateNew(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getLicNoNew())) {
				cell.setCellValue(data.getLicNoNew());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		int rowNumD = 0;
		int cellNumD = 0;
		rowNumD = rowNum + 1;
		Row rowD1 = sheet.createRow(rowNumD);
		Cell cellD1 = rowD1.createCell(cellNumD);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));

		if ("1".equals(StringUtils.defaultString(licH.getAuditFlag()))) {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ถูกต้อง");
		} else if ("2".equals(StringUtils.defaultString(licH.getAuditFlag()))) {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ไม่ถูกต้อง");
		} else {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  -");
		}

		rowNumD += 2;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));
		cellD1.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");

		rowNumD += 1;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD + 3, 0, 3));
		cellD1.setCellValue(StringUtils.defaultString(licH.getConditionText()));
		cellD1.setCellStyle(wrapText);

		rowNumD += 5;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));
		cellD1.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");

		rowNumD += 1;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD + 3, 0, 3));
		cellD1.setCellValue(StringUtils.defaultString(licH.getCriteriaText()));
		cellD1.setCellStyle(wrapText);

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}

}
