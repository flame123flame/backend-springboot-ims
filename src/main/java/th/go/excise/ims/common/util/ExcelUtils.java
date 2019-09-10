package th.go.excise.ims.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public abstract class ExcelUtils {
	
	private static final String defaultDatePattern = "dd/MM/yyyy HH:mm:ss";
	
	public static final class FONT_NAME {
		public static final String TH_SARABUN_PSK = "TH SarabunPSK";
	}
	
	public static final int COLUMN_WIDTH_UNIT = 256;
	public static final int COLUMN_HEIGHT_UNIT = 20;
	
	/**
	 * This method for the type of data in the cell, extracts the data and returns
	 * it as a string.
	 */
	// http://www.java-connect.com/apache-poi-tutorials/read-all-type-of-excel-cell-value-as-string-using-poi/
	public static String getCellValueAsString(Cell cell) {
		return getCellValueAsString(cell, defaultDatePattern);
	}

	public static String getCellValueAsString(Cell cell, String datePattern) {
		String strCellValue = null;

		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case STRING:
				strCellValue = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					// Checking have decimal or not?
					if (value % 1 == 0) {
						// No Decimal
						Long longValue = value.longValue();
						strCellValue = longValue.toString();
					} else {
						// Decimal
						strCellValue = value.toString();
					}
				}
				break;
			case BOOLEAN:
				strCellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case BLANK:
				strCellValue = "";
				break;
			default:
				break;
			}
		}

		return strCellValue;
	}

	public static XSSFCellStyle createWrapTextStyle(XSSFWorkbook workbook) {
		XSSFCellStyle wrapText = workbook.createCellStyle();
		wrapText.setAlignment(HorizontalAlignment.LEFT);
		wrapText.setVerticalAlignment(VerticalAlignment.TOP);
		wrapText.setWrapText(true);
		return wrapText;
	}

	public static XSSFCellStyle createThCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thStyle.setWrapText(true);
		return thStyle;
	}

	public static XSSFCellStyle createTdCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle tdStyle = workbook.createCellStyle();
		tdStyle.setVerticalAlignment(VerticalAlignment.TOP);
		tdStyle.setBorderBottom(BorderStyle.THIN);
		tdStyle.setBorderLeft(BorderStyle.THIN);
		tdStyle.setBorderRight(BorderStyle.THIN);
		tdStyle.setBorderTop(BorderStyle.THIN);
		return tdStyle;
	}

	public static XSSFCellStyle createCenterCellStyle(Workbook workbook) {
		XSSFCellStyle cellCenter = (XSSFCellStyle) workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setVerticalAlignment(VerticalAlignment.TOP);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);
		cellCenter.setWrapText(true);
		return cellCenter;
	}

	public static XSSFCellStyle createRightCellStyle(Workbook workbook) {
		XSSFCellStyle cellRight = (XSSFCellStyle) workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setVerticalAlignment(VerticalAlignment.TOP);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);
		cellRight.setWrapText(true);
		return cellRight;
	}

	public static XSSFCellStyle createLeftCellStyle(Workbook workbook) {
		XSSFCellStyle cellLeft = (XSSFCellStyle) workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setVerticalAlignment(VerticalAlignment.TOP);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		cellLeft.setWrapText(true);
		return cellLeft;
	}

	public static XSSFCellStyle createThColorStyle(Workbook workbook, XSSFColor color) {
		XSSFCellStyle thColor = null;
		thColor = (XSSFCellStyle) workbook.createCellStyle();
		thColor.setFillForegroundColor(color);
		thColor.setAlignment(HorizontalAlignment.CENTER);
		thColor.setVerticalAlignment(VerticalAlignment.CENTER);
		thColor.setBorderBottom(BorderStyle.THIN);
		thColor.setBorderLeft(BorderStyle.THIN);
		thColor.setBorderRight(BorderStyle.THIN);
		thColor.setBorderTop(BorderStyle.THIN);
		thColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thColor.setWrapText(true);
		return thColor;
	}

	public static XSSFCellStyle createCellColorStyle(XSSFWorkbook workbook, XSSFColor color, HorizontalAlignment horAl, VerticalAlignment verAl) {
		XSSFCellStyle thColor = workbook.createCellStyle();
		thColor.setFillForegroundColor(color);
		thColor.setAlignment(horAl);
		thColor.setVerticalAlignment(verAl);
		thColor.setBorderBottom(BorderStyle.THIN);
		thColor.setBorderLeft(BorderStyle.THIN);
		thColor.setBorderRight(BorderStyle.THIN);
		thColor.setBorderTop(BorderStyle.THIN);
		thColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thColor.setWrapText(true);
		return thColor;
	}

	public static XSSFCellStyle createTopicCenterliteStyle(XSSFWorkbook workbook) {
		XSSFCellStyle topicCenterlite = workbook.createCellStyle();
		topicCenterlite.setAlignment(HorizontalAlignment.CENTER);
		return topicCenterlite;
	}
	
	public static XSSFCellStyle createTopicRightliteStyle(XSSFWorkbook workbook) {
		XSSFCellStyle topicRightlite = workbook.createCellStyle();
		topicRightlite.setAlignment(HorizontalAlignment.RIGHT);
		return topicRightlite;
	}

	public static XSSFCellStyle createTopicCenterStyle(XSSFWorkbook workbook) {
		XSSFCellStyle topicCenter = workbook.createCellStyle();
		topicCenter.setAlignment(HorizontalAlignment.CENTER);
		topicCenter.setFont(createTopicFontBold(workbook));
		return topicCenter;
	}

	public static XSSFCellStyle createTopicRightStyle(XSSFWorkbook workbook) {
		XSSFCellStyle topicRight = workbook.createCellStyle();
		topicRight.setAlignment(HorizontalAlignment.RIGHT);
		topicRight.setFont(createTopicFontBold(workbook));
		return topicRight;
	}

	public static XSSFCellStyle createTopicLeftStyle(XSSFWorkbook workbook) {
		XSSFCellStyle topicLeft = workbook.createCellStyle();
		topicLeft.setAlignment(HorizontalAlignment.LEFT);
		topicLeft.setFont(createTopicFontBold(workbook));
		return topicLeft;
	}

	public static XSSFFont createTopicFontBold(XSSFWorkbook workbook) {
		return createTopicFontBold(workbook, XSSFFont.DEFAULT_FONT_NAME);
	}

	public static XSSFFont createTopicFontBold(XSSFWorkbook workbook, String fontName) {
		XSSFFont fontBold = workbook.createFont();
		fontBold.setFontName(fontName);
		fontBold.setBold(true);
		return fontBold;
	}

	public static List<List<String>> readExcel(MultipartFile file) throws Exception {
		final List<List<String>> excelDataList = new ArrayList<>();
		
		try (Workbook workbook = WorkbookFactory.create(file.getInputStream());) {
			Sheet sheet = workbook.getSheetAt(0);
			sheet.forEach(row -> {
				List<String> lineDataList = new ArrayList<>();
				row.forEach(cell -> {
					String cellValue = ExcelUtils.getCellValueAsString(cell);
					lineDataList.add(cellValue);
				});
				excelDataList.add(lineDataList);
			});
		} catch (Exception e) {
			throw e;
		}
		
		return excelDataList;
	}

}
