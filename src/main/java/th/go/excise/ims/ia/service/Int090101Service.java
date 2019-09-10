package th.go.excise.ims.ia.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesD1JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;

@Service
public class Int090101Service {
	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;

	@Autowired
	private IaExpensesD1JdbcRepository iaExpensesD1JdbcRepository;

	public List<Int090101Vo> findCompare(Int090101CompareFormVo form) {

		List<Int090101Vo> data = new ArrayList<Int090101Vo>();
		form.setStartYear((Long.toString(Long.valueOf(form.getStartYear()) - 543)));
		form.setEndYear((Long.toString(Long.valueOf(form.getEndYear()) - 543)));
		if (StringUtils.isNotBlank(form.getYear())) {
			form.setYear((Long.toString(Long.valueOf(form.getYear()) - 543)));
		}

		// set budget Year
		if (Long.valueOf(form.getPeriodMonthStart()) <= 3) {
			form.setStartYear((Long.toString(Long.valueOf(form.getStartYear()) - 1)));
		}
		if (Long.valueOf(form.getPeriodMonthEnd()) <= 3) {
			form.setEndYear((Long.toString(Long.valueOf(form.getEndYear()) - 1)));
		}
		form.setPeriodMonthStart(this.monthMap(StringUtils.leftPad(form.getPeriodMonthStart(), 2, "0")));
		form.setPeriodMonthEnd(this.monthMap(StringUtils.leftPad(form.getPeriodMonthEnd(), 2, "0")));

		data = iaExpensesJdbcRepository.findCompare(form);

		List<Int090101Vo> dataRes = new ArrayList<>();
		List<IaExpensesD1> detail = null;

		for (Int090101Vo headder : data) {
			detail = iaExpensesD1JdbcRepository.findDetailTime(headder.getOfficeCode(), headder.getAccountId(),
					form.getPeriodMonthStart(), form.getStartYear(), form.getPeriodMonthEnd(), form.getEndYear());
//			detail = iaExpensesD1JdbcRepository.findDetail(headder.getOfficeCode(), headder.getBudgetYear(), headder.getExpenseMonth(), headder.getExpenseYear(), headder.getAccountId());
			int index = 0;
			for (IaExpensesD1 iaExpensesD1 : detail) {
				if (index == 0) {
					headder.setAverageCost(new BigDecimal(iaExpensesD1.getAverageCost()));
					headder.setAverageGive(iaExpensesD1.getAverageGive());
					headder.setAverageFrom(new BigDecimal(iaExpensesD1.getAverageFrom()));
					dataRes.add(headder);
				} else if (index > 0) {
					Int090101Vo dataBlank = new Int090101Vo();
					dataBlank.setAverageCost(new BigDecimal(iaExpensesD1.getAverageCost()));
					dataBlank.setAverageGive(iaExpensesD1.getAverageGive());
					dataBlank.setAverageFrom(new BigDecimal(iaExpensesD1.getAverageFrom()));
					dataRes.add(dataBlank);
				}
				index++;
			}
		}

		return dataRes;
	}

	private String monthMap(String inputStr) {
		String[][] monthMapping = { { "01", "10" }, { "02", "11" }, { "03", "12" }, { "04", "01" }, { "05", "02" },
				{ "06", "03" }, { "07", "04" }, { "08", "05" }, { "09", "06" }, { "10", "07" }, { "11", "08" },
				{ "12", "09" } };
		String outStr = new String();
		for (String[] monthtest : monthMapping) {
			if (monthtest[0].equals(inputStr)) {
				outStr = monthtest[1];
			}
		}
		return outStr;
	}

	public ByteArrayOutputStream export(String officeCode, String yearStart, String monthStart, String yearEnd,
			String monthEnd) throws IOException {
		Int090101CompareFormVo form = new Int090101CompareFormVo();
		form.setArea(officeCode);
		form.setStartYear(yearStart);
		form.setPeriodMonthStart(monthStart);
		form.setEndYear(yearEnd);
		form.setPeriodMonthEnd(monthEnd);
		List<Int090101Vo> dataExport = new ArrayList<Int090101Vo>();
		dataExport = findCompare(form);

		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle TopicCenterlite = ExcelUtils.createTopicCenterliteStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle TopicRight = ExcelUtils.createTopicRightStyle(workbook);
		CellStyle TopicCenter = ExcelUtils.createTopicCenterStyle(workbook);
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);

		CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		cellHeaderStyle.setFont(headerFont);

		tdRight.setFont(headerFont);
		tdLeft.setFont(headerFont);

		String[] header = { "ลำดับที่", "รหัสบัญชี", "ชื่อบัญชี", "กิจกรรม (รับโอนจากกรมฯ)", "", "", "รวมรับ",
				"การเบิกจ่ายจริงแยกตามกิจกรรมตามทะเบียนคุม", "", "", "รวมรับ", "งบทดลอง", "ผลต่างงบทดลอง",
				"บัญชีแยกประเภท", "ผลต่างบัญชีแยกประเภท", "คงเหลือ", "", "", "คงเหลือรวม", "ถัวจ่าย", "", "",
				"เหลือสุทธิ", "" };
		String[] header2 = { "", "", "", "การบริหาร", "ปราบปราม", "เงินนอกงปม.", "", "การบริหาร", "ปราบปราม",
				"เงินนอกงปม.", "", "", "", "", "", "การบริหาร", "ปราบปราม", "เงินนอกงปม.", "", "ถัวเป็นเงิน",
				"ถัวเป็นให้", "ถัวมาเงิน", "เงินงบ", "เงินนอก" };
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		rowNum = 0;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(header[i]);
			cell.setCellStyle(TopicCenter);
			cell.setCellStyle(cellHeaderStyle);
			cellNum++;
		}

		rowNum++;
		row = sheet.createRow(rowNum);
		cellNum = 0;
		for (int i = 0; i < header2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(header2[i]);
			cell.setCellStyle(TopicCenter);
			cell.setCellStyle(cellHeaderStyle);
			cellNum++;
		}

		// merge cell
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 10, 10));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 11, 11));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 12, 12));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 13, 13));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 14, 14));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 15, 17));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 18, 18));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 19, 21));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 23));

		// set detail
		double[] footerData = new double[24];

		rowNum++;
		int index = 1;
		for (Int090101Vo data : dataExport) {
			cellNum = 0;
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum);
			cell.setCellValue(index);
			cell.setCellStyle(tdLeft);

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAccountId());
			cell.setCellStyle(tdRight);

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAccountName());
			cell.setCellStyle(tdLeft);

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getServiceReceive()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getServiceReceive() != null ? data.getServiceReceive().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSuppressReceive()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSuppressReceive() != null ? data.getSuppressReceive().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getBudgetReceive()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getBudgetReceive() != null ? data.getBudgetReceive().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSumReceive()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSumReceive() != null ? data.getSumReceive().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getServiceWithdraw()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getServiceWithdraw() != null ? data.getServiceWithdraw().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSuppressWithdraw()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSuppressWithdraw() != null ? data.getSuppressWithdraw().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getBudgetWithdraw()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getBudgetWithdraw() != null ? data.getBudgetWithdraw().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSumWithdraw()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSumWithdraw() != null ? data.getSumWithdraw().doubleValue() : 0.0;

			if (StringUtils.isNotBlank(data.getAccountId())) {
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(changeFornat(new BigDecimal(data.getExperimentalBudget())));
				cell.setCellStyle(tdRight);
				footerData[cellNum] += data.getExperimentalBudget();

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(changeFornat(new BigDecimal(data.getDifferenceExperimentalBudget())));
				cell.setCellStyle(tdRight);
				footerData[cellNum] += data.getDifferenceExperimentalBudget();

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(changeFornat(new BigDecimal(data.getLedger())));
				cell.setCellStyle(tdRight);
				footerData[cellNum] += data.getLedger();

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(changeFornat(new BigDecimal(data.getDifferenceLedger())));
				cell.setCellStyle(tdRight);
				footerData[cellNum] += data.getDifferenceLedger();
			} else {
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("");
				cell.setCellStyle(tdRight);

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("");
				cell.setCellStyle(tdRight);

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("");
				cell.setCellStyle(tdRight);

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("");
				cell.setCellStyle(tdRight);
			}

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getServiceBalance()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getServiceBalance() != null ? data.getServiceBalance().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSuppressBalance()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSuppressBalance() != null ? data.getSuppressBalance().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getBudgetBalance()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getBudgetBalance() != null ? data.getBudgetBalance().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getSumBalance()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getSumBalance() != null ? data.getSumBalance().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getAverageCost()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getAverageCost() != null ? data.getAverageCost().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAverageGive());
			cell.setCellStyle(tdLeft);

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getAverageFrom()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getAverageFrom() != null ? data.getAverageFrom().doubleValue() : 0.0;

			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getMoneyBudget()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getMoneyBudget() != null ? data.getMoneyBudget().doubleValue() : 0.0;

			cellNum++;
			cell.setCellStyle(tdRight);
			cell = row.createCell(cellNum);
			cell.setCellValue(changeFornat(data.getMoneyOut()));
			cell.setCellStyle(tdRight);
			footerData[cellNum] += data.getMoneyOut() != null ? data.getMoneyOut().doubleValue() : 0.0;

			rowNum++;
			index++;
		}

		// set footer

		row = sheet.createRow(rowNum);
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue("รวมทั้งสิ้น");
		cell.setCellStyle(TopicCenter);
		cell.setCellStyle(cellHeaderStyle);

		cellNum++;
		cell = row.createCell(cellNum);
		cell.setCellStyle(TopicCenter);
		cell.setCellStyle(cellHeaderStyle);

		cellNum++;
		cell = row.createCell(cellNum);
		cell.setCellStyle(TopicCenter);
		cell.setCellStyle(cellHeaderStyle);

		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, cellNum));

		cellNum++;
		for (int i = cellNum; i < footerData.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellStyle(tdRight);
//			cell.setCellStyle(cellHeaderStyle);
			cellNum++;
			if (i == footerData.length - 3 || i == footerData.length - 4 || i == footerData.length - 5) {
				continue;
			}
			cell.setCellValue(changeFornat(new BigDecimal(footerData[i])));
		}

		// set width
		int width = 76;
		sheet.setColumnWidth(1, width * 40);
		sheet.setColumnWidth(2, width * 145);
		for (int i = 3; i < 24; i++) {
//			if (i == 21) {
//				continue;
//			}
			sheet.setColumnWidth(i, width * 50);
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

	public String changeFornat(BigDecimal num) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		String res = "";
		if (num != null) {
			res = decimalFormat.format(num).toString();
		}
		return res;
	}
}
