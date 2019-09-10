package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskIncomePerform;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskIncomePerformRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.ExportRiskVo;
import th.go.excise.ims.ia.vo.Int030407Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;
import th.go.excise.ims.ws.client.pcc.incfri8040.service.IncFri8040Service;

@Service
public class Int030407Service {
	private Logger logger = LoggerFactory.getLogger(Int030407Service.class);

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRep;

	@Autowired
	private IaRiskIncomePerformRepository iaRiskIncomePerformRep;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private Int0301Service int0301Service;

	@Autowired
	private IncFri8040Service incFri8040Service;

	public List<Int030407Vo> findByBudgetYear(String budgetYear, String idConfigStr) throws IOException {
		/* web service */
//		IncFri8040Request requestWs = new IncFri8040Request();
//		requestWs.setBudgetYear("2553");
//		requestWs.setDataPerPage("10");
//		requestWs.setDateType("Income");
//		requestWs.setPageNo("1");
//		List<IncomeList> incomeList = incFri8040Service.postRestFul(requestWs);
		/* ---------------------------------------- */

		BigDecimal idConfig = new BigDecimal(idConfigStr);
		List<IaRiskIncomePerform> incomes = iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
		Optional<IaRiskFactorsConfig> config = iaRiskFactorsConfigRep.findById(idConfig);
		List<Int030407Vo> lists = new ArrayList<>();
		Int030407Vo list = null;
		for (IaRiskIncomePerform income : incomes) {
			BigDecimal diffAmount = income.getSumAmount().subtract(income.getForecaseAmount());
			BigDecimal rateAmount = new BigDecimal(
					(diffAmount.doubleValue() * 100) / income.getForecaseAmount().doubleValue());
			list = new Int030407Vo();
			list.setArea(income.getArea());
			list.setBudgetYear(income.getBudgetYear());
			list.setDiffAmount(diffAmount);
			list.setForecaseAmount(income.getForecaseAmount());
			list.setId(income.getId());
			list.setIdFactors(income.getIdFactors());
			list.setOfficeCode(income.getOfficeCode());
			list.setRateAmount(rateAmount);
			list.setSector(income.getSector());
			list.setSumAmount(income.getSumAmount());
			// CALCULATE
			if (config.isPresent()) {
				IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(rateAmount, config.get());
				list.setColorRisk(risk.getColor());
				list.setRateRisk(risk.getRiskRate());
				list.setTextRisk(risk.getTranslatingRisk());
				list.setIntCalculateCriteriaVo(risk);
			}
			/* set ExciseDepartmentVo */
//			logger.info("office-code: {}", list.getOfficeCode());
			if (list.getOfficeCode() != null && list.getOfficeCode().length() == 6) {
				list.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(list.getOfficeCode()));
			}
			lists.add(list);

		}
		
		Collections.sort(lists, new Comparator<Int030407Vo>() {
			@Override
			public int compare(final Int030407Vo object1, final Int030407Vo object2) {
				float  obj1 = Float.parseFloat(object1.getRateAmount().toString());
				float  obj2 = Float.parseFloat(object2.getRateAmount().toString());
				
//				Very --> Little
				return (obj1 > obj2) ? -1 : (obj1 < obj2) ? 1 : 0;
				
//				Little --> Very
//				return (obj2 > obj1)? -1 : (obj2 < obj1) ? 1 : 0; 
			}
			
		});

		return lists;
	}

	public ByteArrayOutputStream exportInt030407(String budgetYear, String idConfigStr, String riskHrdPaperName,
			String createUserName, String createLastName, String createPosition, String checkUserName,
			String checkLastName, String checkPosition) throws IOException {
		/* create spreadsheet */
		BigDecimal idConfig = new BigDecimal(idConfigStr);
		ExportRiskVo exportRiskData = excelUtil.exportConfig(idConfig);
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle TopicCenterlite = ExcelUtils.createTopicCenterliteStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle TopicRight = ExcelUtils.createTopicRightStyle(workbook);
		CellStyle TopicCenter = ExcelUtils.createTopicCenterStyle(workbook);
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row1 = sheet.createRow(rowNum);
		Cell cell1 = row1.createCell(cellNum);
		cell1 = row1.createCell(cellNum);
		cell1.setCellValue(riskHrdPaperName);
		cell1.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row2 = sheet.createRow(rowNum);
		Cell cell2 = row2.createCell(cellNum);
		cell2 = row2.createCell(cellNum);
		cell2.setCellValue("เพื่อพิจารณาคัดเลือกหน่วยงานรับตรวจสำนักงานสรรพสามิตภาค พื้นที่ และสาขา");
		cell2.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row3 = sheet.createRow(rowNum);
		Cell cell3 = row3.createCell(cellNum);
		cell3 = row3.createCell(cellNum);
		cell3.setCellValue("กลุ่มตรวจสอบภายใน  กรมสรรพสามิต");
		cell3.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row4 = sheet.createRow(rowNum);
		Cell cell4 = row4.createCell(cellNum);
		cell4 = row4.createCell(cellNum);
		if (StringUtils.isNotBlank(exportRiskData.getIaRiskFactorsConfig().getRiskIndicators())) {
			cell4.setCellValue("เกณฑ์ความเสี่ยง : " + exportRiskData.getIaRiskFactorsConfig().getRiskIndicators());
		}
		cell4.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row5 = sheet.createRow(rowNum);
		Cell cell5 = row5.createCell(cellNum);
		cell5 = row5.createCell(cellNum);
		if (StringUtils.isNotBlank(exportRiskData.getIaRiskFactorsConfig().getRiskIndicators())) {
			cell5.setCellValue(exportRiskData.getIaRiskFactorsConfig().getRiskIndicators() + "("
					+ exportRiskData.getIaRiskFactorsConfig().getRiskUnit() + ")");
		}
		cell5.setCellStyle(TopicCenterlite);
		rowNum++;

		int test = exportRiskData.getIaRiskFactorsConfig().getFactorsLevel().intValue();

		String veryhigh = int0301Service.convertCondition(
				exportRiskData.getIaRiskFactorsConfig().getVeryhighCondition(),
				exportRiskData.getIaRiskFactorsConfig().getVeryhighStart(),
				exportRiskData.getIaRiskFactorsConfig().getVeryhighEnd(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit());

		String high = int0301Service.convertCondition(exportRiskData.getIaRiskFactorsConfig().getHighCondition(),
				exportRiskData.getIaRiskFactorsConfig().getHighStart(),
				exportRiskData.getIaRiskFactorsConfig().getHighEnd(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit());

		String medium = int0301Service.convertCondition(exportRiskData.getIaRiskFactorsConfig().getMediumCondition(),
				exportRiskData.getIaRiskFactorsConfig().getMediumStart(),
				exportRiskData.getIaRiskFactorsConfig().getMediumEnd(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit());

		String low = int0301Service.convertCondition(exportRiskData.getIaRiskFactorsConfig().getLowCondition(),
				exportRiskData.getIaRiskFactorsConfig().getLowStart(),
				exportRiskData.getIaRiskFactorsConfig().getLowEnd(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit());

		String verylow = int0301Service.convertCondition(exportRiskData.getIaRiskFactorsConfig().getVerylowCondition(),
				exportRiskData.getIaRiskFactorsConfig().getVerylowStart(),
				exportRiskData.getIaRiskFactorsConfig().getVerylowStart(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit(),
				exportRiskData.getIaRiskFactorsConfig().getRiskUnit());

		String[] tbTHCondition3 = { exportRiskData.getIaRiskFactorsConfig().getHigh(),
				exportRiskData.getIaRiskFactorsConfig().getMedium(), exportRiskData.getIaRiskFactorsConfig().getLow() };
		String[] tbTHCondition5 = { exportRiskData.getIaRiskFactorsConfig().getVeryhigh(),
				exportRiskData.getIaRiskFactorsConfig().getHigh(), exportRiskData.getIaRiskFactorsConfig().getMedium(),
				exportRiskData.getIaRiskFactorsConfig().getLow(),
				exportRiskData.getIaRiskFactorsConfig().getVerylow() };
		String[] tbTHConvert3 = { high, medium, low };
		String[] tbTHConvert5 = { veryhigh, high, medium, low, verylow };
		for (int j = 0; j < test; j++) {
			// Row [0]
			Row row6 = sheet.createRow(rowNum);
			Cell cell6 = row6.createCell(cellNum);
			cell6 = row6.createCell(cellNum);
			if (test == 3) {
				cell6.setCellValue(tbTHCondition3[j] + " : "
						+ exportRiskData.getIaRiskFactorsConfig().getRiskIndicators() + " " + tbTHConvert3[j]);
			}
			if (test == 5) {
				cell6.setCellValue(tbTHCondition5[j] + " : "
						+ exportRiskData.getIaRiskFactorsConfig().getRiskIndicators() + " " + tbTHConvert5[j]);
			}
			cell6.setCellStyle(TopicCenterlite);
			rowNum++;
		}

		// Row [0]
		Row row9 = sheet.createRow(rowNum);
		Cell cell9 = row9.createCell(cellNum);
		cell9 = row9.createCell(cellNum);
		String dateStart = ConvertDateUtils.formatDateToString(exportRiskData.getIaRiskFactorsConfig().getStartDate(),
				ConvertDateUtils.DD_MMMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH);
		String dateEnd = ConvertDateUtils.formatDateToString(exportRiskData.getIaRiskFactorsConfig().getEndDate(),
				ConvertDateUtils.DD_MMMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH);
		cell9.setCellValue("แหล่งข้อมูล : " + exportRiskData.getIaRiskFactorsConfig().getInfoUsedRiskDesc() + " "
				+ "ปีงบประมาณ " + "" + budgetYear + " ( " + dateStart + " - " + dateEnd + " )");
		cell9.setCellStyle(TopicCenterlite);
		rowNum++;

		// Row [0]
		Row row11 = sheet.createRow(rowNum);
		Cell cell11 = row11.createCell(cellNum);
		cell11 = row11.createCell(cellNum);
		cell11.setCellValue("หน่วย : " + exportRiskData.getIaRiskFactorsConfig().getRiskUnit());
		cell11.setCellStyle(TopicRight);
		rowNum++;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "หน่วยงาน", "หน่วยงาน", "ผลการจัดเก็บรายได้", "ประมาณรายได้", "ผลต่าง",
				"อัตราสูงต่ำ", "ประเมินความเสี่ยง", "ประเมินความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		String[] tbTH2 = { "สรรพสามิตภาค", "สรรพสามิตพื้นที่" };
		// Empty Cell Row [1]
		for (int i = 0; i < 1; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 0; i < 4; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		String[] tbTH3 = { "อัตราความเสี่ยง", "แปลงค่าความเสี่ยง" };
		for (int i = 0; i < tbTH3.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cell.setCellValue(tbTH3[i]);
			cellNumtbTH2++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		for (int i = 0; i < test; i++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (i + 10), rowNum - (i + 10), 0, 8));
		}
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 9, rowNum - 9, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 8, rowNum - 8, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 7, rowNum - 7, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 8));
		for (int i = 0; i < 1; i++) {
			sheet.addMergedRegion(new CellRangeAddress(test + 7, test + 8, i, i));
		}
		for (int i = 3; i < 7; i++) {
			sheet.addMergedRegion(new CellRangeAddress(test + 7, test + 8, i, i));
		}
		sheet.addMergedRegion(new CellRangeAddress(test + 7, test + 7, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(test + 8, test + 8, 7, 8));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 9; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 9 + test;
		cellNum = 0;
		List<Int030407Vo> datas = findByBudgetYear(budgetYear, idConfigStr);
		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030407Vo data : datas) {
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
			cell.setCellValue(data.getSector());
			cell.setCellStyle(tdLeft);
			cellNum++;
			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getArea());
			cell.setCellStyle(tdLeft);
			cellNum++;
			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAmount().doubleValue()));
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getForecaseAmount().doubleValue()));
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getDiffAmount().doubleValue()));
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getRateAmount().doubleValue()));
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRateRisk().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTextRisk());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Next Row
			rowNum++;
		}
		/* end details */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

}
