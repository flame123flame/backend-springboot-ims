package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaEstimateD1VoType;
import th.go.excise.ims.ia.vo.IaEstimateExpHVo;

@Service
public class Int0502Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0502Service.class);

	@Autowired
	private Int0501Service int0501Service;;

	public ByteArrayOutputStream exportInt0502(String estExpNo ,BigDecimal allSumTranCost, BigDecimal allSumOtherExpenses, BigDecimal allSumAmt , BigDecimal allSumAllowances,BigDecimal allSumAccom) throws IOException {
		/* create spreadsheet */
		String[] estExpNo1 = estExpNo.split("!");
		String estExpNos = estExpNo1[0] + "/" + estExpNo1[1];
		String textMoney = ThaiNumberUtils.toThaiBaht(allSumAmt.toString());

		IaEstimateExpHVo iaEstimateExpHVo = null;
		List<IaEstimateD1VoType> datalist1 = new ArrayList<IaEstimateD1VoType>();
		try {
			iaEstimateExpHVo = int0501Service.findIaEstimateHByestExpNo(estExpNos);
			datalist1 = int0501Service.findData1ExpNo(estExpNos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		ExciseDepartmentVo excise = ExciseDepartmentUtil
				.getExciseDepartmentFull(iaEstimateExpHVo.getDestinationPlace());
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle tdCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle TopicCenter = ExcelUtils.createTopicCenterStyle(workbook);
		CellStyle TopicCenterlite = ExcelUtils.createTopicCenterliteStyle(workbook);
		Sheet sheet = workbook.createSheet();

		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row1 = sheet.createRow(rowNum);
		Cell cell1 = row1.createCell(cellNum);
		cell1 = row1.createCell(cellNum);
		cell1.setCellValue("หลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ");
		cell1.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row2 = sheet.createRow(rowNum);
		Cell cell2 = row2.createCell(cellNum);
		cell2 = row2.createCell(cellNum);
		cell2.setCellValue("ชื่อส่วนราชการ  กลุ่มตรวจสอบภายใน กรมสรรพสามิต  " + excise.getArea());
		cell2.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row3 = sheet.createRow(rowNum);
		Cell cell3 = row3.createCell(cellNum);
		cell3 = row3.createCell(cellNum);
		cell3.setCellValue(
				"ใบประกอบใบเบิกค่าใช้จ่ายการเดินทางของ  " + UserLoginUtils.getCurrentUserBean().getUserThaiName() + "  "
						+ UserLoginUtils.getCurrentUserBean().getUserThaiSurname() + "  ลงวันที่....................................");
		cell3.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
//		Row row4 = sheet.createRow(rowNum);
//		Cell cell4 = row4.createCell(cellNum);
//		cell4 = row4.createCell(cellNum);
//		cell4.setCellValue("ตั้งแต่วันที่ " + iaEstimateExpHVo.getWorkStDate() + " ถึง " + iaEstimateExpHVo.getWorkFhDate());
//		cell4.setCellStyle(TopicCenter);
//		rowNum++;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับที่", "ชื่อ", "ตำแหน่ง", "ค่าใช้จ่าย", "", "", "", "รวม", "รายมือชือผู้รับเงิน",
				"วัน เดือน ปี ที่รับเงิน", "หมายเหตุ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		cellNum = 0;
		cell = row.createCell(cellNum);
		String[] tbTH2 = { "", "", "", "ค่าเบี้ยเลี้ยง", "ค่าเช้าที่พัก", "ค่าพาหนะ", "ค่าใช้จ่ายอื่น", "", "", "",
				"" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
//		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 10));

		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 7, 7));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 8, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 9, 9));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 10, 10));

		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 3, 6));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 10; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, width * 150);
			} else if (i == 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 5;
		cellNum = 0;

		DecimalFormat df2 = new DecimalFormat("###,###.00");

		for (IaEstimateD1VoType data : datalist1) {
			// Re Initial
			cellNum = 0;
			row = sheet.createRow(rowNum);
			// Column 1
			CellStyle styleCustom = tdStyle;
			styleCustom.setAlignment(HorizontalAlignment.CENTER);
			cell = row.createCell(cellNum);
			cell.setCellValue(count++);
			cell.setCellStyle(styleCustom);
			cellNum++;
			// Column 2
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPersonTeamCode());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPosition());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAllowances().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAccom().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getTranCost().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getOtherExpenses().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAmt().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue("");
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 10
			cell = row.createCell(cellNum);
			cell.setCellValue("");
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 11
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Next Row
			rowNum++;

		}
		
		cellNum = 0;
		row = sheet.createRow(rowNum);
		// Column 1
		CellStyle styleCustom = tdStyle;
		styleCustom.setAlignment(HorizontalAlignment.CENTER);
		cell = row.createCell(cellNum);
		cell.setCellValue("");
		cell.setCellStyle(styleCustom);
		cellNum++;
		// Column 2
		cell = row.createCell(cellNum);
		cell.setCellValue("");
		cell.setCellStyle(tdLeft);
		cellNum++;

		// Column 3
		cell = row.createCell(cellNum);
		cell.setCellValue("รวมเงิน");
		cell.setCellStyle(tdCenter);
		cellNum++;

		// Column 4
		cell = row.createCell(cellNum);
		cell.setCellValue(df2.format(allSumAllowances.doubleValue()));
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 5
		cell = row.createCell(cellNum);
		cell.setCellValue(df2.format(allSumAccom.doubleValue()));
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 6
		cell = row.createCell(cellNum);
		cell.setCellValue(df2.format(allSumTranCost.doubleValue()));
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 7
		cell = row.createCell(cellNum);
		cell.setCellValue(df2.format(allSumOtherExpenses.doubleValue()));
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 8
		cell = row.createCell(cellNum);
		cell.setCellValue(df2.format(allSumAmt.doubleValue()));
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 9
		cell = row.createCell(cellNum);
		cell.setCellValue("");
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 10
		cell = row.createCell(cellNum);
		cell.setCellValue("");
		cell.setCellStyle(tdRight);
		cellNum++;

		// Column 11
		cell = row.createCell(cellNum);
		cell.setCellValue("");
		cell.setCellStyle(tdLeft);
		cellNum++;

		// Next Row
		rowNum++;

		/* end details */
		rowNum++;
		cellNum = 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("จำนวนเงินรวมทั้งสิ้น (ตัวอักษร)" + "      " + textMoney );
		
		cellNum = 8;
		cell = row.createCell(cellNum);
		cell.setCellValue("ลงชื่อ..............................................");
		cell.setCellStyle(TopicCenterlite);
		
		

		rowNum++;
		cellNum = 8;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("(" +UserLoginUtils.getCurrentUserBean().getUserThaiName() + "  " + UserLoginUtils.getCurrentUserBean().getUserThaiSurname() + ")");
		cell.setCellStyle(TopicCenterlite);
		
		rowNum++;
		cellNum = 0;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("คำชี้แจง");
		
		cellNum = 1;
		cell = row.createCell(cellNum);
		cell.setCellValue("1. ค่าเบี้ยเลี้ยงและค่าเช่าที่พักให้ระบุอัตตราวันละและจำนวนวันที่ขอเบิกของแต่ล่ะบุคคลในช่องหมายเหตุ");
		
		cellNum = 8;
		cell = row.createCell(cellNum);
		cell.setCellValue("ตำแหน่ง   " + UserLoginUtils.getCurrentUserBean().getTitle());
		cell.setCellStyle(TopicCenterlite);
		
		
		rowNum++;
		cellNum = 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("1. ให้ผู้มีสิทธิแต่ละคนเป็นผู้ลงลายมือชื่อผู้รับเงินและวันเดือนปีที่ได้รับเงิน กรณีเป็นทางการรับจากเงินยืม ให้ระยุวันที่ที่ได้รับจากเงินยืม");
		
		cellNum = 8;
		cell = row.createCell(cellNum);
		cell.setCellValue("ลงวันที่..................................");
		cell.setCellStyle(TopicCenterlite);
		
		rowNum++;
		cellNum = 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("3. ผู้จ่ายเงินหมายถึงผู้ที่ขอยืมเงินจากทางราชการ และจ่ายเงินยืมนั้นใหเแก่ผู้เดินทางแต่ล่ะคน เป็นผู้ลงลายมือชื่อผู้จ่ายเงิน");
		
		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

}
