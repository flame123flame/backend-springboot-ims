package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int090304JdbcRepository;
import th.go.excise.ims.ia.vo.Int090304Vo;
import th.go.excise.ims.ia.vo.Int0903FormVo;

@Service
public class Int090304Service {

	private Logger logger = LoggerFactory.getLogger(Int090304Service.class);

	@Autowired
	Int090304JdbcRepository int090304JdbcRepository;

	public DataTableAjax<Int090304Vo> list(Int0903FormVo request) {
		List<Int090304Vo> data = int090304JdbcRepository.findByCriteria(request);
		Integer total = int090304JdbcRepository.countByCriteria(request);

		DataTableAjax<Int090304Vo> dataTableAjax = new DataTableAjax<Int090304Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<Int0903FormVo> budgetTypeDropdown() {
		List<Int0903FormVo> response = int090304JdbcRepository.budgetTypeDropdown();
		return response;
	}

	public byte[] exportData(Int0903FormVo formVo) {
		logger.info("exportData");

		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		List<Int090304Vo> dataList = int090304JdbcRepository.getDataExport(formVo);

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;

		/* call style from utils */

		// Font
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
		headerFont.setBold(true);

		Font detailFont = workbook.createFont();
		detailFont.setFontHeightInPoints((short) 14);
		detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);

		// Cell Style
		CellStyle cellTitle = (XSSFCellStyle) workbook.createCellStyle();
		cellTitle.setAlignment(HorizontalAlignment.CENTER);
		cellTitle.setFont(headerFont);
		CellStyle thStyle = ExcelUtils.createThCellStyle((XSSFWorkbook) workbook);
		thStyle.setFont(headerFont);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		cellCenter.setFont(detailFont);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		cellLeft.setFont(detailFont);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		cellRight.setFont(detailFont);
		CellStyle cellLeftBold = workbook.createCellStyle();
		cellLeftBold.setAlignment(HorizontalAlignment.LEFT);
		cellLeftBold.setFont(headerFont);

		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue("ทะเบียนคุม KTB-Corporate");
		cell.setCellStyle(cellTitle);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue("สำนักงาน " + ApplicationCache.getExciseDepartment(formVo.getOffcode()).getDeptName());
		cell.setCellStyle(cellTitle);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;

		// Type , Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue("ประเภทงบประมาณ" + (StringUtils.isNotBlank(formVo.getBudgetType()) ? " " + formVo.getBudgetType() + " " : "	      ทั้งหมด      ") + "ช่วงในเวลา " + formVo.getStartDate() + " - " + formVo.getEndDate());
		cell.setCellStyle(cellTitle);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;

		/* Header */
		String[] tbTH1 = { "ลำดับ", "ผู้รับเงิน", "เลขที่บัญชี", "ประเภทค่าใช้จ่าย", "รายการ", "จำนวนเงิน", "วันที่โอน" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
		}
		;

		/* set sheet */
		// setColumnWidth
		for (int i = 1; i <= 6; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 60);
			} else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum++;
		cellNum = 0;
		int no = 1;
		for (Int090304Vo item : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPayee(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getRefPayment(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getBudgetType(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getItemDesc(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(formatter.format(item.getAmount()), ""));
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPaymentDate(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

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
