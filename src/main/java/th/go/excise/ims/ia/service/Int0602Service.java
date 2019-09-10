package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.AuditLicD1Vo;
import th.go.excise.ims.ia.vo.AuditLicD2Vo;
import th.go.excise.ims.ia.vo.AuditLicHVo;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0602ResultTab2Vo;
import th.go.excise.ims.ia.vo.Int0602SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0602Service {
	private static final Logger logger = LoggerFactory.getLogger(Int0602Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;

	@Autowired
	private IaAuditLicHRepository iaAuditLicHRepository;

	@Autowired
	private IaAuditLicD1Repository iaAuditLicD1Repository;

	@Autowired
	private IaAuditLicD2Repository iaAuditLicD2Repository;

	@Autowired
	private IaCommonService iaCommonService;

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");

		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "LIC_NO");

		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				intiData.setLicType(wsLicfri6010.getLicType());
				intiData.setLicNo(wsLicfri6010.getLicNo());
				intiData.setLicName(wsLicfri6010.getLicName());
				intiData.setIncCode(wsLicfri6010.getIncCode());
				intiData.setLicPrice(wsLicfri6010.getLicPrice());
				intiData.setLicFee(wsLicfri6010.getLicFee());
				intiData.setLicInterior(wsLicfri6010.getLicInterior());

				if (wsLicfri6010.getLicDate() != null) {
					intiData.setLicDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				}
				if (wsLicfri6010.getSendDate() != null) {
					intiData.setSendDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				}
				int0602ResultTab1Vo.add(intiData);
			}
		}
		return int0602ResultTab1Vo;
	}

	public List<Int0602ResultTab2Vo> findTab2Criteria(Int0602FormVo int0602FormVo) {
		return int0602JdbcRepository.findTab2Criteria(int0602FormVo);
	}

	public AuditLicHVo saveLicListService(Int0602SaveVo vo) {
		logger.info("saveLicListService : {} ", vo.getAuditLicH().getAuditLicNo());

		// Header
		IaAuditLicH licH = null;
		if (StringUtils.isNotBlank(vo.getAuditLicH().getAuditLicNo())) {
			licH = iaAuditLicHRepository.findByAuditLicNo(vo.getAuditLicH().getAuditLicNo());
		} else {
			licH = new IaAuditLicH();
			licH.setOfficeCode(vo.getAuditLicH().getOfficeCode());
			licH.setLicDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditLicH().getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			licH.setLicDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditLicH().getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			licH.setAuditLicNo(iaCommonService.autoGetRunAuditNoBySeqName("AL", vo.getAuditLicH().getOfficeCode(), "AUDIT_LIC_NO_SEQ", 8));
		}

		licH.setD1AuditFlag(vo.getAuditLicH().getD1AuditFlag());
		licH.setD1ConditionText(vo.getAuditLicH().getD1ConditionText());
		licH.setD1CriteriaText(vo.getAuditLicH().getD1CriteriaText());
		licH.setD2AuditFlag(vo.getAuditLicH().getD2AuditFlag());
		licH.setD2ConditionText(vo.getAuditLicH().getD2ConditionText());
		licH.setD2CriteriaText(vo.getAuditLicH().getD2CriteriaText());

		licH = iaAuditLicHRepository.save(licH);
		vo.getAuditLicH().setAuditLicSeq(licH.getAuditLicSeq());
		vo.getAuditLicH().setAuditLicNo(licH.getAuditLicNo());

		// D1
		if (vo.getAuditLicD1List() != null && vo.getAuditLicD1List().size() > 0) {
			IaAuditLicD1 val1 = null;
			List<IaAuditLicD1> iaAuditLicD1List = new ArrayList<>();
			for (AuditLicD1Vo auditLicD1Vo : vo.getAuditLicD1List()) {
				val1 = new IaAuditLicD1();
				if (auditLicD1Vo.getAuditLicD1Seq() != null) {
					val1 = iaAuditLicD1Repository.findById(auditLicD1Vo.getAuditLicD1Seq()).get();
					val1.setSeqNo(auditLicD1Vo.getSeqNo());
					val1.setRunCheck(auditLicD1Vo.getRunCheck());
					val1.setLicRemark(auditLicD1Vo.getLicRemark());
					val1.setActionFlag(auditLicD1Vo.getActionFlag());
					val1 = iaAuditLicD1Repository.save(val1);
				} else {
					val1.setSeqNo(auditLicD1Vo.getSeqNo());
					val1.setAuditLicNo(licH.getAuditLicNo());
					val1.setLicType(auditLicD1Vo.getLicType());
					val1.setLicNo(auditLicD1Vo.getLicNo());
					val1.setRunCheck(auditLicD1Vo.getRunCheck());
					val1.setLicDate(ConvertDateUtils.parseStringToDate(auditLicD1Vo.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setSendDate(ConvertDateUtils.parseStringToDate(auditLicD1Vo.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setLicName(auditLicD1Vo.getLicName());
					val1.setIncCode(auditLicD1Vo.getIncCode());
					val1.setLicPrice(auditLicD1Vo.getLicPrice());
					val1.setLicFee(auditLicD1Vo.getLicFee());
					val1.setLicInterior(auditLicD1Vo.getLicInterior());
					val1.setLicRemark(auditLicD1Vo.getLicRemark());
					val1.setActionFlag(auditLicD1Vo.getActionFlag());
					iaAuditLicD1List.add(val1);
				}
			}
			iaAuditLicD1Repository.saveAll(iaAuditLicD1List);
		}
		// D2
		if (vo.getAuditLicD2List() != null && vo.getAuditLicD2List().size() > 0) {
			IaAuditLicD2 val2 = null;
			List<IaAuditLicD2> iaAuditLicD2List = new ArrayList<>();
			for (AuditLicD2Vo auditLicD2Vo : vo.getAuditLicD2List()) {
				val2 = new IaAuditLicD2();
				if (auditLicD2Vo.getAuditLicD2Seq() != null) {
					val2 = iaAuditLicD2Repository.findById(auditLicD2Vo.getAuditLicD2Seq()).get();
					val2.setAuditCheck(auditLicD2Vo.getAuditCheck());
					val2.setLicT2Remark(auditLicD2Vo.getLicT2Remark());
					val2 = iaAuditLicD2Repository.save(val2);
				} else {
					val2.setAuditLicNo(licH.getAuditLicNo());
					val2.setTaxCode(auditLicD2Vo.getTaxCode());
					val2.setLicName(auditLicD2Vo.getLicName());
					val2.setLicPrice(auditLicD2Vo.getLicPrice());
					val2.setLicCount(auditLicD2Vo.getLicCount());
					val2.setAuditCheck(auditLicD2Vo.getAuditCheck());
					val2.setLicT2Remark(auditLicD2Vo.getLicT2Remark());
					iaAuditLicD2List.add(val2);
				}

			}
			iaAuditLicD2Repository.saveAll(iaAuditLicD2List);
		}
		return vo.getAuditLicH();
	}

	public List<AuditLicHVo> findAuditLicHVoList() {
		List<IaAuditLicH> iaAuditLicHList = iaAuditLicHRepository.findIaAuditLicHAllDataActive();
		AuditLicHVo licH = null;
		List<AuditLicHVo> auditLicHVoList = new ArrayList<>();

		for (IaAuditLicH data : iaAuditLicHList) {
			licH = new AuditLicHVo();
			try {
				licH.setAuditLicSeq(data.getAuditLicSeq());
				licH.setOfficeCode(data.getOfficeCode());
				licH.setLicDateFrom(data.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				licH.setLicDateTo(data.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(data.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				licH.setAuditLicNo(data.getAuditLicNo());
				licH.setD1AuditFlag(data.getD1AuditFlag());
				licH.setD1ConditionText(data.getD1ConditionText());
				licH.setD1CriteriaText(data.getD1CriteriaText());
				licH.setD2AuditFlag(data.getD2AuditFlag());
				licH.setD2ConditionText(data.getD2ConditionText());
				licH.setD2CriteriaText(data.getD2CriteriaText());
				auditLicHVoList.add(licH);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		return auditLicHVoList;
	}

	public List<AuditLicD1Vo> findAuditLicD1ByAuditLicNo(String auditLicNo) throws Exception {
		List<AuditLicD1Vo> auditLicD1VoList = new ArrayList<>();
		AuditLicD1Vo val1 = new AuditLicD1Vo();
		List<IaAuditLicD1> iaAuditLicD1List = iaAuditLicD1Repository.findByAuditLicNoOrderBySeqNo(auditLicNo);
		for (IaAuditLicD1 data : iaAuditLicD1List) {
			val1 = new AuditLicD1Vo();
			val1.setAuditLicD1Seq(data.getAuditLicD1Seq());
			val1.setSeqNo(data.getSeqNo());
			val1.setAuditLicNo(data.getAuditLicNo());
			val1.setLicType(data.getLicType());
			val1.setLicNo(data.getLicNo());
			val1.setRunCheck(data.getRunCheck());
			val1.setLicDate(ConvertDateUtils.formatDateToString(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			val1.setSendDate(ConvertDateUtils.formatDateToString(data.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			val1.setLicName(data.getLicName());
			val1.setIncCode(data.getIncCode());
			val1.setLicPrice(data.getLicPrice());
			val1.setLicFee(data.getLicFee());
			val1.setLicInterior(data.getLicInterior());
			val1.setLicRemark(data.getLicRemark());
			val1.setActionFlag(data.getActionFlag());
			auditLicD1VoList.add(val1);
		}
		return auditLicD1VoList;
	}

	public List<AuditLicD2Vo> findAuditLicD2ByAuditLicNo(String auditLicNo) throws Exception {
		List<AuditLicD2Vo> auditLicD2VoList = new ArrayList<>();
		AuditLicD2Vo val2 = new AuditLicD2Vo();
		List<IaAuditLicD2> iaAuditLicD2List = iaAuditLicD2Repository.findByAuditLicNo(auditLicNo);
		for (IaAuditLicD2 data : iaAuditLicD2List) {
			val2 = new AuditLicD2Vo();
			val2.setAuditLicD2Seq(data.getAuditLicD2Seq());
			val2.setAuditLicNo(data.getAuditLicNo());
			val2.setTaxCode(data.getTaxCode());
			val2.setLicName(data.getLicName());
			val2.setLicPrice(data.getLicPrice());
			val2.setLicCount(data.getLicCount());
			val2.setAuditCheck(data.getAuditCheck());
			val2.setLicT2Remark(data.getLicT2Remark());

			auditLicD2VoList.add(val2);
		}
		return auditLicD2VoList;
	}

	public AuditLicHVo findIaAuditLicHByAuditLicNo(String auditLicNo) {
		logger.info("findIaAuditLicHByAuditLicNo auditLicNo={}", auditLicNo);

		AuditLicHVo LicHVo = null;
		IaAuditLicH data = null;
		ExciseDepartmentVo excise = null;
		data = iaAuditLicHRepository.findByAuditLicNo(auditLicNo);
		try {
			LicHVo = new AuditLicHVo();
			LicHVo.setOfficeCode(data.getOfficeCode());
			LicHVo.setLicDateFrom(data.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			LicHVo.setLicDateTo(data.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(data.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			LicHVo.setAuditLicNo(data.getAuditLicNo());
			LicHVo.setD1AuditFlag(data.getD1AuditFlag());
			LicHVo.setD1ConditionText(data.getD1ConditionText());
			LicHVo.setD1CriteriaText(data.getD1CriteriaText());
			LicHVo.setD2AuditFlag(data.getD2AuditFlag());
			LicHVo.setD2ConditionText(data.getD2ConditionText());
			LicHVo.setD2CriteriaText(data.getD2CriteriaText());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getOfficeCode());
			LicHVo.setArea(excise.getArea());
			LicHVo.setSector(excise.getSector());
			LicHVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return LicHVo;
	}

	public byte[] export(String auditLicNo) throws Exception {
		logger.info("export auditIncNo={}", auditLicNo);

		AuditLicHVo auditLicHVo = findIaAuditLicHByAuditLicNo(auditLicNo);
		auditLicHVo.setTitleName("ตรวจสอบการใช้ใบอนุญาต");
		auditLicHVo.setOfficeName("สำนักงาน " + ApplicationCache.getExciseDepartment(auditLicHVo.getOfficeCode()).getDeptName());
		auditLicHVo.setReceiptDateRangeText("ช่วงในเวลา " + auditLicHVo.getLicDateFrom() + " - " + auditLicHVo.getLicDateTo());

		// Create SpreadSheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		WorkbookCellStyle workbookCellStyle = new WorkbookCellStyle(workbook);

		// Sheet 1
		generateSheetD1(auditLicNo, auditLicHVo, workbook, workbookCellStyle);

		// Sheet 2
		generateSheetD2(auditLicNo, auditLicHVo, workbook, workbookCellStyle);

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			throw e;
		}

		return content;
	}

	private class WorkbookCellStyle {

		private CellStyle cellTitle;
		private CellStyle thStyle;
		private CellStyle cellCenter;
		private CellStyle cellLeft;
		private CellStyle cellRight;
		private CellStyle wrapText;
		private CellStyle cellLeftBold;

		public WorkbookCellStyle(Workbook workbook) {
			// Font
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			headerFont.setBold(true);

			Font detailFont = workbook.createFont();
			detailFont.setFontHeightInPoints((short) 14);
			detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);

			// Cell Style
			cellTitle = (XSSFCellStyle) workbook.createCellStyle();
			cellTitle.setAlignment(HorizontalAlignment.CENTER);
			cellTitle.setFont(headerFont);

			this.thStyle = ExcelUtils.createThCellStyle((XSSFWorkbook) workbook);
			this.thStyle.setFont(headerFont);
			this.cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			this.cellCenter.setFont(detailFont);
			this.cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			this.cellLeft.setFont(detailFont);
			this.cellRight = ExcelUtils.createRightCellStyle(workbook);
			this.cellRight.setFont(detailFont);
			this.wrapText = ExcelUtils.createWrapTextStyle((XSSFWorkbook) workbook);
			this.wrapText.setFont(detailFont);
			this.cellLeftBold = workbook.createCellStyle();
			this.cellLeftBold.setAlignment(HorizontalAlignment.LEFT);
			this.cellLeftBold.setFont(headerFont);
		}

		public CellStyle getCellTitle() {
			return cellTitle;
		}

		public CellStyle getThStyle() {
			return thStyle;
		}

		public CellStyle getCellCenter() {
			return cellCenter;
		}

		public CellStyle getCellLeft() {
			return cellLeft;
		}

		public CellStyle getCellRight() {
			return cellRight;
		}

		public CellStyle getWrapText() {
			return wrapText;
		}

		public CellStyle getCellLeftBold() {
			return cellLeftBold;
		}

	}

	private void generateSheetD1(String auditLicNo, AuditLicHVo auditLicHVo, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<AuditLicD1Vo> dataList = findAuditLicD1ByAuditLicNo(auditLicNo);
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");

		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("ตรวจสอบการใช้ใบอนุญาต");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;

		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 11));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 11));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 11));
		rowNum++;

		// Column Header
		String[] tbTH = { "ลำดับ", "ประเภทใบอนุญาต", "เลขที่ใบอนุญาต", "ตรวจสอบเลขที่พิมพ์", "วันที่ออกใบอนุญาต", "วันที่นำส่งเงิน( ระบบรายได้ ) ", "ใบอนุญาต ป.1-ป.2", "รหัสภาษี", "จำนวนเงิน", "ค่าธรรมเนียมใบอนุญาต", "ค่าธรรมเนียมมหาดไทย", "หมายเหตุผลการตรวจ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;

		// Column Details
		int no = 1;
		for (AuditLicD1Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicType());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicNo());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getRunCheck() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getRunCheck().toString());
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isEmpty(data.getLicDate())) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getLicDate());
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			if (StringUtils.isEmpty(data.getSendDate())) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getSendDate());
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicName());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIncCode());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicPrice() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getLicPrice()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicFee() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getLicFee()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicInterior() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getLicInterior()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicRemark());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;

			no++;
			rowNum++;
		}

		// Summary
		rowNum++;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		if ("1".equals(StringUtils.defaultString(auditLicHVo.getD1AuditFlag()))) {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  ถูกต้อง");
		} else if ("2".equals(StringUtils.defaultString(auditLicHVo.getD1AuditFlag()))) {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  ไม่ถูกต้อง");
		} else {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  -");
		}
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 2;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(auditLicHVo.getD1ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(auditLicHVo.getD1CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // ประเภทใบอนุญาต
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เลขที่ใบอนุญาต
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // ตรวจสอบเลขที่พิมพ์
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันที่ออกใบอนุญาต
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // วันที่นำส่งเงิน( ระบบรายได้ )
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // ใบอนุญาต ป.1-ป.2
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // รหัสภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // ค่าธรรมเนียมใบอนุญาต
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // ค่าธรรมเนียมมหาดไทย
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // หมายเหตุผลการตรวจ
	}

	private void generateSheetD2(String auditLicNo, AuditLicHVo auditLicHVo, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<AuditLicD2Vo> dataList = findAuditLicD2ByAuditLicNo(auditLicNo);
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0");

		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("ใบอนุญาตที่ใช้จริง");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;

		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(auditLicHVo.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;

		// Column Header
		String[] tbTH = { "ลำดับ", "รหัสภาษี", "ใบอนุญาต ป.1-ป.2", "จำนวนเงิน", "จำนวนราย", "ผลการตรวจกับงบสรุปยอดที่นำส่ง", "หมายเหตุ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;

		// Column Details
		int no = 1;
		for (AuditLicD2Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicName());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicPrice() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getLicPrice()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getLicCount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormat.format(data.getLicCount()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if ("1".equals(data.getAuditCheck())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getAuditCheck())) {
				cell.setCellValue("ไม่ถูกต้อง");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getLicT2Remark());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;

			no++;
			rowNum++;
		}

		// Summary
		rowNum++;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		if ("1".equals(StringUtils.defaultString(auditLicHVo.getD1AuditFlag()))) {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  ถูกต้อง");
		} else if ("2".equals(StringUtils.defaultString(auditLicHVo.getD1AuditFlag()))) {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  ไม่ถูกต้อง");
		} else {
			cell.setCellValue("สรุปผลการตรวจกับทะเบียนคุมใบอนุญาต :  -");
		}
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 2;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(auditLicHVo.getD1ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(auditLicHVo.getD1CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // รหัสภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // ใบอนุญาต ป.1-ป.2
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนราย
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // ผลการตรวจกับงบสรุปยอดที่นำส่ง
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // หมายเหตุผลการตรวจ

	}

}
