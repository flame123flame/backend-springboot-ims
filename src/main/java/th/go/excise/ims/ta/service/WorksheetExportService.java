package th.go.excise.ims.ta.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;
import th.go.excise.ims.ta.vo.WorksheetExportFormVo;

@Service
public class WorksheetExportService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetExportService.class);
	
	private static final int FLUSH_ROWS = 2000;
	private static final String NO_TAX_AMOUNT = "-";
	private static final DateTimeFormatter dateFormatter_MMM_yyyy = DateTimeFormatter.ofPattern("MMM yyyy", ConvertDateUtils.LOCAL_TH);
	private static final DateTimeFormatter dateFormatter_MMM_yy = DateTimeFormatter.ofPattern("MMM yy", ConvertDateUtils.LOCAL_TH);
	
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;
	@Autowired
	private TaWorksheetCondSubNoAuditRepository taWorksheetCondSubNoAuditRepository;
	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;
	@Autowired
	private PlanWorksheetService planWorksheetService;
	
	public byte[] exportPreviewWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		LocalDate dateStart = LocalDateUtils.thaiMonthYear2LocalDate(formVo.getDateStart());
		LocalDate dateEnd = LocalDateUtils.thaiMonthYear2LocalDate(formVo.getDateEnd());
		int dateRange = LocalDateUtils.getLocalDateRange(dateStart, dateEnd).size() * 2;
		formVo.setDateRange(dateRange);
		logger.info("exportPreviewWorksheet officeCode={}, budgetYear={}, startDate={}, endDate={}, dateRange={}",
			formVo.getOfficeCode(), formVo.getBudgetYear(), formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());
		
		// Prepare Data for Export
		formVo.setOfficeCode(officeCode);
		String taxCompareType = TaxAuditUtils.getTaxCompareType(formVo.getDateRange());
		
		List<TaxOperatorDetailVo> previewVoList = draftWorksheetService.prepareTaxOperatorDetailVoList(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(previewVoList, formVo);
		WorksheetDateRangeVo worksheetDateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), taxCompareType);
		
		// Prepare WorksheetExportFormVo
		WorksheetExportFormVo exportFormVo = new WorksheetExportFormVo();
		exportFormVo.setTitleName("รายการข้อมูลผู้ประกอบการที่เสียภาษีสรรพสามิต");
		exportFormVo.setOfficeName(ApplicationCache.getExciseDepartment(officeCode).getDeptName());
		exportFormVo.setBudgetYear(formVo.getBudgetYear());
		exportFormVo.setDateRange(formVo.getDateRange());
		exportFormVo.setWorksheetDateRangeVo(worksheetDateRangeVo);
		exportFormVo.setTaxOperatorDatatableVoList(taxOperatorDatatableVoList);
		
		return generateDraftWorksheetXlsx(exportFormVo);
	}
	
	public byte[] exportDraftWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = StringUtils.isNotEmpty(formVo.getOfficeCode()) ? formVo.getOfficeCode() : UserLoginUtils.getCurrentUserBean().getOfficeCode();
		formVo.setAnalysisNumber(formVo.getDraftNumber());
		logger.info("exportDraftWorksheet officeCode={}, analysisNumber={}", officeCode, formVo.getDraftNumber());
		
		// Prepare Data for Export
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getDraftNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getDraftNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		formVo.setWorksheetStatus(TA_WORKSHEET_STATUS.DRAFT);
		formVo.setStart(0);
		formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
		//formVo.setLength(10);
		
		List<TaxOperatorDetailVo> worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
		WorksheetDateRangeVo worksheetDateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), worksheetCondMainHdr.getCompType());
		
		// Prepare WorksheetExportFormVo
		WorksheetExportFormVo exportFormVo = new WorksheetExportFormVo();
		exportFormVo.setTitleName("รายการข้อมูลผู้ประกอบการที่เสียภาษีสรรพสามิต");
		exportFormVo.setOfficeName(ApplicationCache.getExciseDepartment(officeCode).getDeptName());
		exportFormVo.setBudgetYear(formVo.getBudgetYear());
		exportFormVo.setDateRange(formVo.getDateRange());
		exportFormVo.setWorksheetDateRangeVo(worksheetDateRangeVo);
		exportFormVo.setTaxOperatorDatatableVoList(taxOperatorDatatableVoList);
		
		return generateDraftWorksheetXlsx(exportFormVo);
	}
	
	private byte[] generateDraftWorksheetXlsx(WorksheetExportFormVo formVo) {
		// Reverse Date
		List<LocalDate> subLocalDateG1List = new ArrayList<>(formVo.getWorksheetDateRangeVo().getSubLocalDateG1List());
		List<LocalDate> subLocalDateG2List = new ArrayList<>(formVo.getWorksheetDateRangeVo().getSubLocalDateG2List());
		Collections.reverse(subLocalDateG1List);
		Collections.reverse(subLocalDateG2List);
		
		// Label and Text
		String SHEET_NAME = "รายชื่อผู้ประกอบการ";
		
		// Create Workbook
		byte[] content = null;
		try (SXSSFWorkbook workbook = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			// Font
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			headerFont.setBold(true);
			
			Font detailFont = workbook.createFont();
			detailFont.setFontHeightInPoints((short) 14);
			detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			
			// Cell Style
			CellStyle cellTitleStyle = (XSSFCellStyle) workbook.createCellStyle();
			cellTitleStyle.setAlignment(HorizontalAlignment.CENTER);
			cellTitleStyle.setFont(headerFont);
			CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
			cellHeaderStyle.setFont(headerFont);
			CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			cellCenter.setFont(detailFont);
			CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			cellLeft.setFont(detailFont);
			CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			cellRight.setFont(detailFont);
			DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
			
			
			// Prepare Data for Export
			Sheet sheet = workbook.createSheet(SHEET_NAME);
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
			cell.setCellValue(formVo.getTitleName());
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			// Office Name
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
			cellNum = 0;
			cell = row.createCell(cellNum);
			cell.setCellValue("สำนักงาน " + formVo.getOfficeName());
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			// Budget Year
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
			cellNum = 0;
			cell = row.createCell(cellNum);
			cell.setCellValue("ประจำปีงบประมาณ " + formVo.getBudgetYear());
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			
			// Column Header
			// Header Line 1
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 2));
			List<String> headerText1List = new ArrayList<>();
			headerText1List.add("ลำดับ");
			headerText1List.add("เลขทะเบียนสรรพสามิต");
			headerText1List.add("ชื่อผู้ประกอบการ");
			headerText1List.add("เงื่อนไขที่ 1\nข้อมูลตรวจสอบภาษี");
			headerText1List.add("");
			headerText1List.add("");
			headerText1List.add("เงื่อนไขที่ 2\nข้อมูลการชำระภาษี");
			headerText1List.add("");
			headerText1List.add("");
			headerText1List.add("เงื่อนไขที่ 3");
			headerText1List.add("ชื่อโรงอุตสาหกรรม/สถานบริการ");
			headerText1List.add("ที่อยู่โรงอุตสาหกรรม/สถานบริการ");
			headerText1List.add("ภาค");
			headerText1List.add("พื้นที่");
			headerText1List.add("ทุนจดทะเบียน");
			headerText1List.add("วันที่จดทะเบียน");
			headerText1List.add("พิกัด");
			int monthNum = formVo.getDateRange() / 2;
			for (int i = 0; i < formVo.getDateRange(); i++) {
				if (i == 0) {
					headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนแรก");
				} else if (i == (monthNum)) {
					headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนหลัง");
				} else {
					headerText1List.add("");
				}
			}
			
			cellNum = 0;
			for (String headerText : headerText1List) {
				cell = row.createCell(cellNum);
				cell.setCellValue(headerText);
				cell.setCellStyle(cellHeaderStyle);
				cellNum++;
			}
			rowNum++;
			
			// Header Line 2
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 2));
			List<String> headerText2List = new ArrayList<>();
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 3));
			headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 2));
			headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 1));
			headerText2List.add("ปีนี้\n" + generateDateRangeString(subLocalDateG1List));
			headerText2List.add("ปีก่อน\n" + generateDateRangeString(subLocalDateG2List));
			headerText2List.add("เปลี่ยนแปลง\n(ร้อยละ)");
			headerText2List.add("จำนวนเดือน\nไม่ชำระภาษี");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			headerText2List.add("");
			int startTaxAmtIndex = headerText2List.size();
			// Date G1
			headerText2List.addAll(generateDateString(formVo.getWorksheetDateRangeVo().getSubLocalDateG1List()));
			// Date G2
			headerText2List.addAll(generateDateString(formVo.getWorksheetDateRangeVo().getSubLocalDateG2List()));
			
			cellNum = 0;
			for (String headerText : headerText2List) {
				cell = row.createCell(cellNum);
				cell.setCellValue(headerText);
				cell.setCellStyle(cellHeaderStyle);
				cellNum++;
			}
			rowNum++;
			
			// Details
			int no = 1;
			for (TaxOperatorDatatableVo taxVo : formVo.getTaxOperatorDatatableVoList()) {
				row = sheet.createRow(rowNum);
				cellNum = 0;
				// ลำดับ
				cell = row.createCell(cellNum++);
				cell.setCellValue(no);
				cell.setCellStyle(cellLeft);
				// เลขทะเบียนสรรพสามิต
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getNewRegId());
				cell.setCellStyle(cellLeft);
				// ชื่อผู้ประกอบการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getCusFullname());
				cell.setCellStyle(cellLeft);
				// การตรวจสอบภาษีย้อนหลัง 3 ปี
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getTaxAuditLast3());
				cell.setCellStyle(cellLeft);
				// การตรวจสอบภาษีย้อนหลัง 2 ปี
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getTaxAuditLast2());
				cell.setCellStyle(cellLeft);
				// การตรวจสอบภาษีย้อนหลัง 1 ปี
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getTaxAuditLast1());
				cell.setCellStyle(cellLeft);
				// ยอดชำระภาษี วิเคราะห์
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG1(), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
				// ยอดชำระภาษี เปรียบเทียบ
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG2(), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
				// เปลี่ยนแปลง (ร้อยละ)
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtChnPnt(), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
				// จำนวนเดือนไม่ชำระภาษี
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getNotPayTaxMonthNo());
				cell.setCellStyle(cellRight);
				// ชื่อโรงอุตสาหกรรม/สถานบริการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getFacFullname());
				cell.setCellStyle(cellLeft);
				// ที่อยู่โรงอุตสาหกรรม/สถานบริการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getFacAddress());
				cell.setCellStyle(cellLeft);
				// ภาค
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getSecDesc());
				cell.setCellStyle(cellLeft);
				// พื้นที่
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getAreaDesc());
				cell.setCellStyle(cellLeft);
				// ทุนจดทะเบียน
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getRegCapital(), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
				// วันที่จดทะเบียน
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getRegDate());
				cell.setCellStyle(cellLeft);
				// พิกัด
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxVo.getDutyName());
				cell.setCellStyle(cellLeft);
				// การชำระภาษี X เดือนแรก และ X เดือนหลัง
				for (String taxAmt : taxVo.getTaxAmtList()) {
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxAmt, decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
				}
				
				if (rowNum % FLUSH_ROWS == 0) {
					((SXSSFSheet) sheet).flushRows(FLUSH_ROWS); // retain ${flushRows} last rows and flush all others
					logger.debug("Writed {} row(s)", rowNum);
				}
				
				no++;
				rowNum++;
			}
			
			// Column Width
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 7);  // ลำดับ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 28); // เลขทะเบียนสรรพสามิต
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อผู้ประกอบการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 3 ปี
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 2 ปี
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 1 ปี
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ยอดชำระภาษี วิเคราะห์
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ยอดชำระภาษี เปรียบเทียบ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // เปลี่ยนแปลง (ร้อยละ)
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // จำนวนเดือนไม่ชำระภาษี
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อโรงอุตสาหกรรม/สถานบริการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 10); // ภาค
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // พื้นที่
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ทุนจดทะเบียน
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันที่จดทะเบียน
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // พิกัด
			for (int i = 0; i < formVo.getDateRange(); i++) {
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การชำระภาษี X เดือนแรก และ X เดือนหลัง
			}
			
			// Merge Column
			int titleRow = 0;
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 16));
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 16));
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 16));
			int mergeCellIndex = 0;
			for (String headerText : headerText2List) {
				if (StringUtils.isEmpty(headerText)) {
					sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 1, mergeCellIndex, mergeCellIndex));
				}
				mergeCellIndex++;
			}
			sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, 3, 5)); // การตรวจสอบภาษีย้อนหลัง 3 ปี
			sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, 6, 8)); // การชำระภาษีในสภาวะปกติ
			int halfDataRange = formVo.getDateRange() / 2;
			if (halfDataRange > 1) {
				sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, startTaxAmtIndex, startTaxAmtIndex + halfDataRange - 1));
				sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, startTaxAmtIndex + halfDataRange, startTaxAmtIndex + formVo.getDateRange() - 1));
			}
			
			workbook.write(out);
			content = out.toByteArray();
			
			// dispose of temporary files backing this workbook on disk
			workbook.dispose();
			//logger.debug("Writed {} row(s)", rowNum);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return content;
	}
	
	public byte[] exportWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = null;
		if (StringUtils.isNotBlank(formVo.getArea()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getArea())) {
			officeCode = formVo.getArea();
		} else if (StringUtils.isNotBlank(formVo.getSector()) && !CommonConstants.JsonStatus.SUCCESS.equals(formVo.getSector())) {
			officeCode = formVo.getSector();
		} else {
			officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		}
		logger.info("exportWorksheet officeCode={}, analysisNumber={}", officeCode, formVo.getAnalysisNumber());
		
		// Prepare for Export
		final String SHEET_NAME_1 = "ข้อมูลตามเงื่อนไขคัดเลือก";
		final String SHEET_NAME_2 = "จดทะเบียนรายใหม่";
		final int DEFAULT_NO_TAX_AUDIT_YEAR = 3;
		
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		
		List<TaxOperatorDetailVo> worksheetVoList = null;
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = null;
		TaWorksheetCondSubNoAudit worksheetCondSubNoAudit = null;
		WorksheetDateRangeVo worksheetDateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), worksheetCondMainHdr.getCompType());
		List<LocalDate> subLocalDateG1List = new ArrayList<>(worksheetDateRangeVo.getSubLocalDateG1List());
		List<LocalDate> subLocalDateG2List = new ArrayList<>(worksheetDateRangeVo.getSubLocalDateG2List());
		Collections.reverse(subLocalDateG1List);
		Collections.reverse(subLocalDateG2List);
		
		// Label and Text
		List<String> sheetNameList = new ArrayList<>();
		sheetNameList.add(SHEET_NAME_1);
		sheetNameList.add(SHEET_NAME_2);
		String titleName = "รายการข้อมูลผู้ประกอบการที่เสียภาษีสรรพสามิตตามเงื่อนไข";
		String officeName = ApplicationCache.getExciseDepartment(officeCode).getDeptName();
		
		// Create Workbook
		byte[] content = null;
		try (SXSSFWorkbook workbook = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			// Font
		    Font headerFont = workbook.createFont();
		    headerFont.setFontHeightInPoints((short) 14);
		    headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
		    headerFont.setBold(true);
		    
		    Font detailFont = workbook.createFont();
		    detailFont.setFontHeightInPoints((short) 14);
		    detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			
			// Cell Style
		    CellStyle cellTitleStyle = (XSSFCellStyle) workbook.createCellStyle();
			cellTitleStyle.setAlignment(HorizontalAlignment.CENTER);
			cellTitleStyle.setFont(headerFont);
			CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
			cellHeaderStyle.setFont(headerFont);
			CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			cellCenter.setFont(detailFont);
			CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			cellLeft.setFont(detailFont);
			CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			cellRight.setFont(detailFont);
			DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
			
			// Prepare Data for Export
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			
			int sheetNameCount = 1;
			for (String sheetName : sheetNameList) {
				// Initial Data
				int rowNum = 0;
				int cellNum = 0;
				int condGroupCount = 0;
				int noTaxAuditYearNum = 0;
				
				if (sheetNameCount == 1) {
					formVo.setNewRegFlag(FLAG.N_FLAG);
					condGroupCount = 1 + taWorksheetCondMainDtlRepository.countByAnalysisNumber(formVo.getAnalysisNumber());
					worksheetCondSubNoAudit = taWorksheetCondSubNoAuditRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
					noTaxAuditYearNum = worksheetCondSubNoAudit.getNoTaxAuditYearNum() != null ? worksheetCondSubNoAudit.getNoTaxAuditYearNum() : DEFAULT_NO_TAX_AUDIT_YEAR;
				} else if (sheetNameCount == 2) {
					noTaxAuditYearNum = DEFAULT_NO_TAX_AUDIT_YEAR;
					formVo.setNewRegFlag(FLAG.Y_FLAG);
				}
				formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
				//formVo.setLength(10);
				
				// Prepare Data for Export
				worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
				taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
				
				// Sheet Name
				sheet = workbook.createSheet(sheetName);
				
				// Title
				// Report Name
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
				cellNum = 0;
				cell = row.createCell(cellNum);
				cell.setCellValue(titleName);
				cell.setCellStyle(cellTitleStyle);
				rowNum++;
				// Office Name
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
				cellNum = 0;
				cell = row.createCell(cellNum);
				cell.setCellValue("สำนักงาน " + officeName);
				cell.setCellStyle(cellTitleStyle);
				rowNum++;
				// Budget Year
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
				cellNum = 0;
				cell = row.createCell(cellNum);
				cell.setCellValue("ประจำปีงบประมาณ " + formVo.getBudgetYear());
				cell.setCellStyle(cellTitleStyle);
				rowNum++;
				
				// Column Header
				// Header Line 1
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 2));
				List<String> headerText1List = new ArrayList<>();
				headerText1List.add("ลำดับ");
				headerText1List.add("เลขทะเบียนสรรพสามิต");
				headerText1List.add("ชื่อผู้ประกอบการ");
				for (int i = 1; i <= condGroupCount; i++) {
					if (i == 1) {
						headerText1List.add("เงื่อนไขคัดเลือก");
					} else {
						headerText1List.add("");
					}
				}
				for (int i = 1; i <= noTaxAuditYearNum; i++) {
					if (i == 1) {
						headerText1List.add("เงื่อนไขที่ 1\nข้อมูลตรวจสอบภาษี");
					} else {
						headerText1List.add("");
					}
				}
				headerText1List.add("เงื่อนไขที่ 2\nข้อมูลการชำระภาษี");
				headerText1List.add("");
				headerText1List.add("");
				headerText1List.add("เงื่อนไขที่ 3");
				headerText1List.add("ชื่อโรงอุตสาหกรรม/สถานบริการ");
				headerText1List.add("ที่อยู่โรงอุตสาหกรรม/สถานบริการ");
				headerText1List.add("ภาค");
				headerText1List.add("พื้นที่");
				headerText1List.add("ทุนจดทะเบียน");
				headerText1List.add("วันที่จดทะเบียน");
				headerText1List.add("พิกัด");
				int monthNum = formVo.getDateRange() / 2;
				for (int i = 0; i < formVo.getDateRange(); i++) {
					if (i == 0) {
						headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนแรก");
					} else if (i == (monthNum)) {
						headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนหลัง");
					} else {
						headerText1List.add("");
					}
				}
				
				cellNum = 0;
				for (String headerText : headerText1List) {
					cell = row.createCell(cellNum);
					cell.setCellValue(headerText);
					cell.setCellStyle(cellHeaderStyle);
					cellNum++;
				}
				rowNum++;
				
				// Header Line 2
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 2));
				
				List<String> headerText2List = new ArrayList<>();
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				for (int i = 1; i <= condGroupCount; i++) {
					headerText2List.add("เงื่อนไขที่ " + i);
				}
				for (int i = noTaxAuditYearNum; i >= 1; i--) {
					headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - i));
				}
				headerText2List.add("ปีนี้\n" + generateDateRangeString(subLocalDateG1List));
				headerText2List.add("ปีก่อน\n" + generateDateRangeString(subLocalDateG2List));
				headerText2List.add("เปลี่ยนแปลง\n(ร้อยละ)");
				headerText2List.add("จำนวนเดือน\nไม่ชำระภาษี");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				int startTaxAmtIndex = headerText2List.size();
				// Date G1
				headerText2List.addAll(generateDateString(worksheetDateRangeVo.getSubLocalDateG1List()));
				// Date G2
				headerText2List.addAll(generateDateString(worksheetDateRangeVo.getSubLocalDateG2List()));
				
				cellNum = 0;
				for (String headerText : headerText2List) {
					cell = row.createCell(cellNum);
					cell.setCellValue(headerText);
					cell.setCellStyle(cellHeaderStyle);
					cellNum++;
				}
				rowNum++;
				
				// Details
				int no = 1;
				Method method = null;
				String condGroupFlag = null;
				for (TaxOperatorDatatableVo taxVo : taxOperatorDatatableVoList) {
					row = sheet.createRow(rowNum);
					cellNum = 0;
					// ลำดับ
					cell = row.createCell(cellNum++);
					cell.setCellValue(no);
					cell.setCellStyle(cellLeft);
					// เลขทะเบียนสรรพสามิต
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getNewRegId());
					cell.setCellStyle(cellLeft);
					// ชื่อผู้ประกอบการ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getCusFullname());
					cell.setCellStyle(cellLeft);
					// กลุ่มเงื่อนไข
					for (int i = 0; i < condGroupCount; i++) {
						cell = row.createCell(cellNum++);
						if (i == 0) {
							if (FLAG.Y_FLAG.equals(taxVo.getCondSubNoAudit())) {
								cell.setCellValue("X");
							} else {
								cell.setCellValue("");
							}
						} else {
							try {
								method = TaxOperatorDatatableVo.class.getDeclaredMethod("getCondG" + i);
								condGroupFlag = (String) method.invoke(taxVo);
								if (FLAG.Y_FLAG.equals(condGroupFlag)) {
									cell.setCellValue("X");
								} else {
									cell.setCellValue("");
								}
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
							}
						}
						cell.setCellStyle(cellCenter);
					}
					// การตรวจสอบภาษีย้อนหลัง 3 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast3());
					cell.setCellStyle(cellLeft);
					// การตรวจสอบภาษีย้อนหลัง 2 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast2());
					cell.setCellStyle(cellLeft);
					// การตรวจสอบภาษีย้อนหลัง 1 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast1());
					cell.setCellStyle(cellLeft);
					// ยอดชำระภาษี วิเคราะห์
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG1(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// ยอดชำระภาษี เปรียบเทียบ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG2(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// เปลี่ยนแปลง (ร้อยละ)
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtChnPnt(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// จำนวนเดือนไม่ชำระภาษี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getNotPayTaxMonthNo());
					cell.setCellStyle(cellRight);
					// ชื่อโรงอุตสาหกรรม/สถานบริการ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getFacFullname());
					cell.setCellStyle(cellLeft);
					// ที่อยู่โรงอุตสาหกรรม/สถานบริการ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getFacAddress());
					cell.setCellStyle(cellLeft);
					// ภาค
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getSecDesc());
					cell.setCellStyle(cellLeft);
					// พื้นที่
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getAreaDesc());
					cell.setCellStyle(cellLeft);
					// ทุนจดทะเบียน
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getRegCapital(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// วันที่จดทะเบียน
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getRegDate());
					cell.setCellStyle(cellLeft);
					// พิกัด
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getDutyName());
					cell.setCellStyle(cellLeft);
					// การชำระภาษี X เดือนแรก และ X เดือนหลัง
					for (String taxAmt : taxVo.getTaxAmtList()) {
						cell = row.createCell(cellNum++);
						cell.setCellValue(taxAmtFormat(taxAmt, decimalFormatTwoDigits));
						cell.setCellStyle(cellRight);
					}
					
					if (rowNum % FLUSH_ROWS == 0) {
						((SXSSFSheet) sheet).flushRows(FLUSH_ROWS); // retain ${flushRows} last rows and flush all others
						logger.debug("Writed {} row(s)", rowNum);
					}
					
					no++;
					rowNum++;
				}
				
				// Column Width
				int colIndex = 0;
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 7);  // ลำดับ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 28); // เลขทะเบียนสรรพสามิต
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อผู้ประกอบการ
				for (int i = 1; i <= condGroupCount; i++) {
					sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // กลุ่มเงื่อนไข
				}
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 3 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 2 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 1 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ยอดชำระภาษี วิเคราะห์
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ยอดชำระภาษี เปรียบเทียบ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // เปลี่ยนแปลง (ร้อยละ)
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // จำนวนเดือนไม่ชำระภาษี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อโรงอุตสาหกรรม/สถานบริการ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 10); // ภาค
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // พื้นที่
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ทุนจดทะเบียน
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันที่จดทะเบียน
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // พิกัด
				for (int i = 0; i < formVo.getDateRange(); i++) {
					sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การชำระภาษี X เดือนแรก และ X เดือนหลัง
				}
				
				// Merge Column
				int titleRow = 0;
				sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 19));
				sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 19));
				sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 19));
				int mergeCellIndex = 0;
				for (String headerText : headerText2List) {
					if (StringUtils.isEmpty(headerText)) {
						sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 1, mergeCellIndex, mergeCellIndex));
					}
					mergeCellIndex++;
				}
				int afterCondGroupIndex = 3;
				if (sheetNameCount == 1) {
					afterCondGroupIndex += condGroupCount - 1;
					sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, 3, afterCondGroupIndex)); // กลุ่มเงื่อนไข
				} else {
					afterCondGroupIndex--;
				}
				sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, afterCondGroupIndex + 1, afterCondGroupIndex + 3)); // การตรวจสอบภาษีย้อนหลัง 3 ปี
				sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, afterCondGroupIndex + 4, afterCondGroupIndex + 6)); // การชำระภาษีในสภาวะปกติ
				int halfDataRange = formVo.getDateRange() / 2;
				if (halfDataRange > 1) {
					sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, startTaxAmtIndex, startTaxAmtIndex + halfDataRange - 1));
					sheet.addMergedRegion(new CellRangeAddress(titleRow + 0, titleRow + 0, startTaxAmtIndex + halfDataRange, startTaxAmtIndex + formVo.getDateRange() - 1));
				}
				
				sheetNameCount++;
			}
			
			workbook.write(out);
			content = out.toByteArray();
			
			// dispose of temporary files backing this workbook on disk
			workbook.dispose();
			//logger.debug("Writed {} row(s)", rowNum);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return content;
	}
	
	/*public byte[] exportCondSubWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportCondSubWorksheet officeCode={}, budgetYear={}, draftNumber={}", officeCode, formVo.getBudgetYear(), formVo.getDraftNumber());
		
		// Prepare Data for Export
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
		
		List<TaxOperatorDetailVo> worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
		
		// Label and Text
		String SHEET_NAME = "รายชื่อผู้ประกอบการ";
		
		// Create Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// Cell Style
		CellStyle cellHeader = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		
		Sheet sheet = workbook.createSheet(SHEET_NAME);
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		// Column Header
		// Header Line 1
		row = sheet.createRow(rowNum);
		generateWorksheetHeader1(row, cell, cellHeader, formVo);
		rowNum++;
		// Header Line 2
		row = sheet.createRow(rowNum);
		generateWorksheetHeader2(row, cell, cellHeader, formVo);
		rowNum++;
		
		// Details
		rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, taxOperatorDatatableVoList);
		
		// Column Width
		setColumnWidth(sheet, 0, formVo.getDateRange());
		setMergeCell(sheet, formVo.getDateRange());
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}*/
	
	public byte[] exportPlanWorksheetSelected(PlanWorksheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportWorksheet officeCode={}, planNumber={}", officeCode, formVo.getPlanNumber());
		
		// Prepare Data
		TaPlanWorksheetHdr planHdr = planWorksheetService.getPlanWorksheetHdr(formVo);
		formVo.setBudgetYear(planHdr.getBudgetYear());
		
		// PlanWorksheet Data for Export
		List<PlanWorksheetDatatableVo> planVoList = planWorksheetService.planDtlDatatableAll(formVo);
		
		// Label and Text
		String SHEET_NAME = "รายชื่อผู้ประกอบการที่คัดเลือก";
		String titleName = "รายการคัดเลือกราย";
		String officeName = ApplicationCache.getExciseDepartment(officeCode).getDeptName();
		
		// Create Workbook
		byte[] content = null;
		try (SXSSFWorkbook workbook = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			// Font
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			headerFont.setBold(true);
			
			Font detailFont = workbook.createFont();
			detailFont.setFontHeightInPoints((short) 14);
			detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			
			// Cell Style
			CellStyle cellTitleStyle = (XSSFCellStyle) workbook.createCellStyle();
			cellTitleStyle.setAlignment(HorizontalAlignment.CENTER);
			cellTitleStyle.setFont(headerFont);
			CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
			cellHeaderStyle.setFont(headerFont);
			CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			cellCenter.setFont(detailFont);
			CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			cellLeft.setFont(detailFont);
			CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			cellRight.setFont(detailFont);
			
			// Prepare Data for Export
			Sheet sheet = workbook.createSheet(SHEET_NAME);
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
			cell.setCellValue(titleName);
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			// Office Name
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
			cellNum = 0;
			cell = row.createCell(cellNum);
			cell.setCellValue("สำนักงาน " + officeName);
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			// Budget Year
			row = sheet.createRow(rowNum);
			row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
			cellNum = 0;
			cell = row.createCell(cellNum);
			cell.setCellValue("ประจำปีงบประมาณ " + formVo.getBudgetYear());
			cell.setCellStyle(cellTitleStyle);
			rowNum++;
			
			// Column Header
			row = sheet.createRow(rowNum);
			List<String> headerTextList = new ArrayList<>();
			headerTextList.add("ในแผน");
			headerTextList.add("ลำดับ");
			headerTextList.add("เลขทะเบียนสรรพสามิต");
			headerTextList.add("ชื่อผู้ประกอบการ");
			headerTextList.add("ชื่อโรงอุตสาหกรรม/สถานบริการ");
			headerTextList.add("ที่อยู่โรงอุตสาหกรรม/สถานบริการ");
			headerTextList.add("ภาค");
			headerTextList.add("พื้นที่");
			headerTextList.add("พิกัด");
			
			cellNum = 0;
			for (String headerText : headerTextList) {
				cell = row.createCell(cellNum);
				cell.setCellValue(headerText);
				cell.setCellStyle(cellHeaderStyle);
				cellNum++;
			}
			rowNum++;
			
			// Details
			int no = 1;
			for (PlanWorksheetDatatableVo planVo : planVoList) {
				row = sheet.createRow(rowNum);
				cellNum = 0;
				// ในแผน
				cell = row.createCell(cellNum++);
				cell.setCellValue("");
				cell.setCellStyle(cellCenter);
				// ลำดับ
				cell = row.createCell(cellNum++);
				cell.setCellValue(no);
				cell.setCellStyle(cellCenter);
				// ทะเบียนสรรพสามิต
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getNewRegId());
				cell.setCellStyle(cellCenter);
				// ชื่อผู้ประกอบการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getCusFullname());
				cell.setCellStyle(cellLeft);
				// ชื่อโรงอุตสาหกรรม/สถานบริการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getFacFullname());
				cell.setCellStyle(cellLeft);
				// ที่อยู่โรงอุตสาหกรรม/สถานบริการ
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getFacAddress());
				cell.setCellStyle(cellLeft);
				// ภาค
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getSecDesc());
				cell.setCellStyle(cellLeft);
				// พื้นที่
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getAreaDesc());
				cell.setCellStyle(cellLeft);
				// พิกัด
				cell = row.createCell(cellNum++);
				cell.setCellValue(planVo.getDutyDesc());
				cell.setCellStyle(cellLeft);
				
				if (rowNum % FLUSH_ROWS == 0) {
					((SXSSFSheet) sheet).flushRows(FLUSH_ROWS); // retain ${flushRows} last rows and flush all others
					logger.debug("Writed {} row(s)", rowNum);
				}
				
				no++;
				rowNum++;
			}
			
			// Column Width
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 7); // ในแผน
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 7); // ลำดับ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 28); // ทะเบียนสรรพสามิต
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อผู้ประกอบการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อโรงอุตสาหกรรม/สถานบริการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 10); // ภาค
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // พื้นที่
			sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 25); // พิกัด
			
			// Merge Column
			int titleRow = 0;
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(titleRow, titleRow++, 0, 8));
			
			workbook.write(out);
			content = out.toByteArray();
			
			// dispose of temporary files backing this workbook on disk
			workbook.dispose();
			//logger.debug("Writed {} row(s)", rowNum);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return content;
	}
	
	private String convertToThaiDate(String date) {
		return ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(date, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
	}
	
	private List<String> generateDateString(List<LocalDate> localDateList) {
		List<String> thaiDateList = new ArrayList<>();
		for (LocalDate localDate : localDateList) {
			thaiDateList.add(ThaiBuddhistDate.from(localDate).format(dateFormatter_MMM_yyyy));
		}
		return thaiDateList;
	}
	
	private String generateDateRangeString(List<LocalDate> localDateList) {
		String text = null;
		if (localDateList != null && localDateList.size() > 0) {
			text = "(";
			text += ThaiBuddhistDate.from(localDateList.get(0)).format(dateFormatter_MMM_yy);
			text += " - ";
			text += ThaiBuddhistDate.from(localDateList.get(localDateList.size() - 1)).format(dateFormatter_MMM_yy);
			text += ")";
		} else {
			text = "";
		}
		return text;
	}
	
	private String taxAmtFormat(String taxAmt, DecimalFormat decimalFormat) {
		return !NO_TAX_AMOUNT.equals(taxAmt) ? decimalFormat.format(NumberUtils.nullToZero(NumberUtils.toBigDecimal(taxAmt))) : taxAmt;
	}
	
}
