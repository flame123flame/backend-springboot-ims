package th.go.excise.ims.ia.util;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.vo.ExportRiskVo;

@Service
public class ExcelUtil {

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	public CellStyle thStyle;
	public CellStyle cellCenter;
	public CellStyle cellRight;
	public CellStyle cellLeft;
	public CellStyle bgRed;
	public CellStyle bgYellow;
	public CellStyle bgGreen;
	public CellStyle topCenter;
	public CellStyle topRight;
	public CellStyle topLeft;
	public CellStyle bgLightBule;
	public CellStyle bgBule;
	public Font fontHeader;

	public XSSFWorkbook setUpExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();

		fontHeader = workbook.createFont();
		fontHeader.setBold(true);

		thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thStyle.setFont(fontHeader);

		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);

		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);

		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);

		bgRed = workbook.createCellStyle();
		bgRed.setAlignment(HorizontalAlignment.CENTER);
		bgRed.setBorderBottom(BorderStyle.THIN);
		bgRed.setBorderLeft(BorderStyle.THIN);
		bgRed.setBorderRight(BorderStyle.THIN);
		bgRed.setBorderTop(BorderStyle.THIN);
		bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgYellow = workbook.createCellStyle();
		bgYellow.setAlignment(HorizontalAlignment.CENTER);
		bgYellow.setBorderBottom(BorderStyle.THIN);
		bgYellow.setBorderLeft(BorderStyle.THIN);
		bgYellow.setBorderRight(BorderStyle.THIN);
		bgYellow.setBorderTop(BorderStyle.THIN);
		bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgGreen = workbook.createCellStyle();
		bgGreen.setAlignment(HorizontalAlignment.CENTER);
		bgGreen.setBorderBottom(BorderStyle.THIN);
		bgGreen.setBorderLeft(BorderStyle.THIN);
		bgGreen.setBorderRight(BorderStyle.THIN);
		bgGreen.setBorderTop(BorderStyle.THIN);
		bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgLightBule = workbook.createCellStyle();
		bgLightBule.setAlignment(HorizontalAlignment.CENTER);
		bgLightBule.setBorderBottom(BorderStyle.THIN);
		bgLightBule.setBorderLeft(BorderStyle.THIN);
		bgLightBule.setBorderRight(BorderStyle.THIN);
		bgLightBule.setBorderTop(BorderStyle.THIN);
		bgLightBule.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		bgLightBule.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		bgLightBule.setWrapText(true);

		bgBule = workbook.createCellStyle();
		bgBule.setAlignment(HorizontalAlignment.CENTER);
		bgBule.setBorderBottom(BorderStyle.THIN);
		bgBule.setBorderLeft(BorderStyle.THIN);
		bgBule.setBorderRight(BorderStyle.THIN);
		bgBule.setBorderTop(BorderStyle.THIN);
		bgBule.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.WHITE1.getIndex());
		font.setBold(true);
		bgBule.setFont(font);
		bgBule.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
		bgBule.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		bgBule.setWrapText(true);

		topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);

		topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);

		topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		return workbook;
	}

	public ExportRiskVo exportConfig(BigDecimal idConfig) throws IOException {
		ExportRiskVo exportRiskData = new ExportRiskVo();
		IaRiskFactorsConfig configData = iaRiskFactorsConfigRepository.findById(idConfig).get();
		exportRiskData.setIaRiskFactorsConfig(configData);
		return exportRiskData;
	}
}
