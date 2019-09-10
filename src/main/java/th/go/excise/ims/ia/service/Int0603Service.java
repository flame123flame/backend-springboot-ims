package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
import th.go.excise.ims.ia.persistence.entity.IaAuditLicdupD;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicdupH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicdupDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicdupHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.AuditLicdupDVo;
import th.go.excise.ims.ia.vo.AuditLicdupHVo;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0603SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0603Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0603Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaAuditLicdupHRepository iaAuditLicdupHRepository;

	@Autowired
	private IaAuditLicdupDRepository iaAuditLicdupDRepository;

	DecimalFormat df1 = new DecimalFormat("###,###,###");
	DecimalFormat df2 = new DecimalFormat("###,###,###.00");

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "PRINT_COUNT");
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					if (wsLicfri6010.getSendDate() != null) {
						intiData.setSendDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getExpDate() != null) {
						intiData.setExpDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getStartDate() != null) {
						intiData.setStartDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getLicDate() != null) {
						intiData.setLicDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					intiData.setOffcode(wsLicfri6010.getOffcode());
					intiData.setLicType(wsLicfri6010.getLicType());
					intiData.setLicNo(wsLicfri6010.getLicNo());
					intiData.setLicName(wsLicfri6010.getLicName());
					intiData.setLicFee(wsLicfri6010.getLicFee());
					intiData.setLicInterior(wsLicfri6010.getLicInterior());
					intiData.setLicPrice(wsLicfri6010.getLicPrice());
					intiData.setPrintCount(wsLicfri6010.getPrintCount());
					intiData.setNid(wsLicfri6010.getNid());
					intiData.setNewRegId(wsLicfri6010.getNewRegId());
					intiData.setCusFullname(wsLicfri6010.getCusFullname());
					intiData.setFacFullname(wsLicfri6010.getFacFullname());
					intiData.setIncCode(wsLicfri6010.getIncCode());

					int0602ResultTab1Vo.add(intiData);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}

	public List<AuditLicdupHVo> findAuditLicdupHList() {
		List<IaAuditLicdupH> iaAuditLicHList = iaAuditLicdupHRepository.findIaAuditLicdupHAllDataActive();
		AuditLicdupHVo auditLicdupHVo = null;
		List<AuditLicdupHVo> auditLicdupHVosList = new ArrayList<>();
		for (IaAuditLicdupH iaAuditLicdupH : iaAuditLicHList) {
			auditLicdupHVo = new AuditLicdupHVo();
			try {
				auditLicdupHVo.setAuditLicdupSeq(iaAuditLicdupH.getAuditLicdupSeq());
				auditLicdupHVo.setOfficeCode(iaAuditLicdupH.getOfficeCode());
				auditLicdupHVo.setLicDateFrom(iaAuditLicdupH.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(iaAuditLicdupH.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicdupHVo.setLicDateTo(iaAuditLicdupH.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(iaAuditLicdupH.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicdupHVo.setAuditLicdupNo(iaAuditLicdupH.getAuditLicdupNo());
				auditLicdupHVo.setAuditFlag(iaAuditLicdupH.getAuditFlag());
				auditLicdupHVo.setConditionText(iaAuditLicdupH.getConditionText());
				auditLicdupHVo.setCriteriaText(iaAuditLicdupH.getCriteriaText());
				auditLicdupHVosList.add(auditLicdupHVo);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}

		}
		return auditLicdupHVosList;
	}

	public AuditLicdupHVo saveLicdupH(Int0603SaveVo vo) {
		logger.info("saveLicListService : {} ", vo.getAuditLicdupH().getAuditLicdupNo());
		IaAuditLicdupH licH = null;
		try {
			if (StringUtils.isNotBlank(vo.getAuditLicdupH().getAuditLicdupNo())) {
				licH = new IaAuditLicdupH();
				licH = iaAuditLicdupHRepository.findByAuditLicdupNo(vo.getAuditLicdupH().getAuditLicdupNo());
				licH.setAuditFlag(vo.getAuditLicdupH().getAuditFlag());
				licH.setConditionText(vo.getAuditLicdupH().getConditionText());
				licH.setCriteriaText(vo.getAuditLicdupH().getCriteriaText());
				licH = iaAuditLicdupHRepository.save(licH);
				vo.getAuditLicdupH().setAuditLicdupSeq(licH.getAuditLicdupSeq());
				vo.getAuditLicdupH().setAuditLicdupNo(licH.getAuditLicdupNo());
			} else {
				licH = new IaAuditLicdupH();
				licH.setOfficeCode(vo.getAuditLicdupH().getOfficeCode());
				licH.setLicDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditLicdupH().getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				licH.setLicDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditLicdupH().getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				licH.setAuditLicdupNo(iaCommonService.autoGetRunAuditNoBySeqName("LID", vo.getAuditLicdupH().getOfficeCode(), "AUDIT_LICDUP_NO_SEQ", 8));
				licH.setAuditFlag(vo.getAuditLicdupH().getAuditFlag());
				licH.setConditionText(vo.getAuditLicdupH().getConditionText());
				licH.setCriteriaText(vo.getAuditLicdupH().getCriteriaText());
				licH = iaAuditLicdupHRepository.save(licH);
				vo.getAuditLicdupH().setAuditLicdupSeq(licH.getAuditLicdupSeq());
				vo.getAuditLicdupH().setAuditLicdupNo(licH.getAuditLicdupNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (vo.getAuditLicdupDList() != null && vo.getAuditLicdupDList().size() > 0) {
			IaAuditLicdupD val = null;
			List<IaAuditLicdupD> iaAuditLicdupDList = new ArrayList<>();
			for (AuditLicdupDVo auditLicdupDVo : vo.getAuditLicdupDList()) {
				val = new IaAuditLicdupD();
				if (auditLicdupDVo.getAuditLicdupDSeq() != null) {
					val = iaAuditLicdupDRepository.findById(auditLicdupDVo.getAuditLicdupDSeq()).get();
					try {
						val.setRunCheck(auditLicdupDVo.getRunCheck());
						val = iaAuditLicdupDRepository.save(val);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				} else {
					try {
						val.setNewRegId(auditLicdupDVo.getNewRegId());
						val.setCusFullname(auditLicdupDVo.getCusFullname());
						val.setLicType(auditLicdupDVo.getLicType());
						val.setRunCheck(auditLicdupDVo.getRunCheck());
						val.setLicNo(auditLicdupDVo.getLicNo());
						val.setPrintCount(auditLicdupDVo.getPrintCount());
						val.setLicDate(ConvertDateUtils.parseStringToDate(auditLicdupDVo.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setAuditLicdupNo(licH.getAuditLicdupNo());
					} catch (Exception e) {
						e.printStackTrace();
					}
					iaAuditLicdupDList.add(val);
				}

			}
			iaAuditLicdupDRepository.saveAll(iaAuditLicdupDList);
		}

		return vo.getAuditLicdupH();
	}

	public List<AuditLicdupDVo> findAuditLicdupDByAuditLicdupNo(String auditLicdupNo) throws Exception {
		List<AuditLicdupDVo> iaAuditLicdupDVoList = new ArrayList<>();
		AuditLicdupDVo auditLicdupDVo = null;
		List<IaAuditLicdupD> auditLicdupDList = iaAuditLicdupDRepository.findByAuditLicdupNoOrderByPrintCount(auditLicdupNo);
		for (IaAuditLicdupD auditLicdupD : auditLicdupDList) {
			auditLicdupDVo = new AuditLicdupDVo();
			auditLicdupDVo.setAuditLicdupDSeq(auditLicdupD.getAuditLicdupDSeq());
			auditLicdupDVo.setAuditLicdupNo(auditLicdupD.getAuditLicdupNo());
			auditLicdupDVo.setCusFullname(auditLicdupD.getCusFullname());
			auditLicdupDVo.setLicDate(ConvertDateUtils.formatDateToString(auditLicdupD.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicdupDVo.setLicNo(auditLicdupD.getLicNo());
			auditLicdupDVo.setLicType(auditLicdupD.getLicType());
			auditLicdupDVo.setNewRegId(auditLicdupD.getNewRegId());
			auditLicdupDVo.setPrintCount(auditLicdupD.getPrintCount());
			auditLicdupDVo.setRunCheck(auditLicdupD.getRunCheck());
			iaAuditLicdupDVoList.add(auditLicdupDVo);
		}
		return iaAuditLicdupDVoList;
	}

	public AuditLicdupHVo findByAuditLicdupNo(String auditLicdupNo) {
		AuditLicdupHVo auditLicdupHVo = null;
		IaAuditLicdupH licH = null;
		ExciseDepartmentVo excise = null;
		licH = iaAuditLicdupHRepository.findByAuditLicdupNo(auditLicdupNo);

		try {
			auditLicdupHVo = new AuditLicdupHVo();
			auditLicdupHVo.setAuditLicdupSeq(licH.getAuditLicdupSeq());
			auditLicdupHVo.setAuditFlag(licH.getAuditFlag());
			auditLicdupHVo.setAuditLicdupNo(licH.getAuditLicdupNo());
			auditLicdupHVo.setConditionText(licH.getConditionText());
			auditLicdupHVo.setCriteriaText(licH.getCriteriaText());
			auditLicdupHVo.setLicDateFrom(ConvertDateUtils.formatDateToString(licH.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicdupHVo.setLicDateTo(ConvertDateUtils.formatDateToString(licH.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicdupHVo.setOfficeCode(licH.getOfficeCode());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(licH.getOfficeCode());
			auditLicdupHVo.setArea(excise.getArea());
			auditLicdupHVo.setSector(excise.getSector());
			auditLicdupHVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return auditLicdupHVo;
	}

	public byte[] export(String auditLicdupNo) {

		// hdr
		IaAuditLicdupH licH = null;
		licH = new IaAuditLicdupH();
		licH = iaAuditLicdupHRepository.findByAuditLicdupNo(auditLicdupNo);

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ตรวจสอบการพิมพ์ใบอนุญาตซ้ำ");
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle wrapText = ExcelUtils.createWrapTextStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "เลขทะเบียนสรรพสามิต", "ชื่อผู้ประกอบการ", "ประเภทใบอนุญาต", "ตรวจสอบเลขที่แบบพิมพ์", "เลขที่ใบอนุญาต ", "วันที่ออกใบอนุญาต", "จำนวนครั้งที่พิมพ์ซ้ำ" };
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
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		sheet.setColumnWidth(colIndex++, 20 * 256);
		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		List<IaAuditLicdupD> auditLicdupDList = iaAuditLicdupDRepository.findByAuditLicdupNoOrderByPrintCount(auditLicdupNo);
		for (IaAuditLicdupD data : auditLicdupDList) {
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
			if (StringUtils.isNotBlank(data.getCusFullname())) {
				cell.setCellValue(data.getCusFullname());
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
			if (data.getRunCheck() != null) {
				cell.setCellValue(data.getRunCheck());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellRight);
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
			if (data.getPrintCount() != null) {
				cell.setCellValue(df1.format(data.getPrintCount()));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellRight);
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
