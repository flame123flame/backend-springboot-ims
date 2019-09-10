package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskCheckPeriodJdbcRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.ExportRiskVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030406Service {
	
	private Logger logger = LoggerFactory.getLogger(Int030406Service.class);

	@Autowired
	private Int030405Service int030405Service;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private Int0301Service int0301Service;

	@Autowired
	private IaRiskCheckPeriodJdbcRepository iaRiskCheckPeriodJdbcRepository;

	public ByteArrayOutputStream exportInt030405(String budgetYear, BigDecimal inspectionWork, BigDecimal idConfig,
			String riskHrdPaperName, String createUserName, String createLastName, String createPosition,
			String checkUserName, String checkLastName, String checkPosition) throws IOException {
		/* create spreadsheet */
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
		long budgetYearCon = Long.parseLong(budgetYear);
		budgetYearCon = budgetYearCon - 1;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับที่", "หน่วยงาน", "วัมที่เริ่มตรวจ", "วันที่ปิดตรวจ", "ระยะเวลาการเข้าตรวจสอบ ( ปี )",
				"ประเมินความเสี่ยง", "" };
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

		String[] tbTH2 = { "", "", "", "", "", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		for (int i = 0; i < test; i++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (i + 10), rowNum - (i + 10), 0, 6));
		}
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 9, rowNum - 9, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 8, rowNum - 8, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 7, rowNum - 7, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 5, 6));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		sheet.setColumnWidth(1, width * 280);
		for (int i = 2; i <= 6; i++) {
			if (i >= 2 && i <= 3) {
				sheet.setColumnWidth(i, width * 60);
			}
			if (i == 4) {
				sheet.setColumnWidth(i, width * 150);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 9 + test;
		cellNum = 0;

		List<Int030406Vo> datas = new ArrayList<Int030406Vo>();
		Int030406FormVo form = new Int030406FormVo();
		form.setBudgetYear(budgetYear);
		form.setInspectionWork(inspectionWork);
		form.setIdConfig(idConfig);
		datas = checkPeriodList(form);

		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030406Vo data : datas) {
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
			cell.setCellValue(data.getIaRiskCheckPeriod().getAreaName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(ConvertDateUtils.formatDateToString(data.getIaRiskCheckPeriod().getDateStart(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(ConvertDateUtils.formatDateToString(data.getIaRiskCheckPeriod().getDateEnd(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskCheckPeriod().getLongTime().toString());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			if (data.getIntCalculateCriteriaVo().getRiskRate() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			if (data.getIntCalculateCriteriaVo().getTranslatingRisk() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
			} else {
				cell.setCellValue("-");
			}
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

	public List<Int030406Vo> checkPeriodList(Int030406FormVo form) {
		List<Int030406Vo> resDataCal = new ArrayList<Int030406Vo>();
		List<IaRiskCheckPeriod> systemUnworkingList = new ArrayList<IaRiskCheckPeriod>();
//		systemUnworkingList = iaRiskCheckPeriodRepository.findAllOderLongTimeDesc();
		systemUnworkingList = iaRiskCheckPeriodJdbcRepository.getDataFilter();
		List<IaRiskCheckPeriod> res = new ArrayList<IaRiskCheckPeriod>();
		for (IaRiskCheckPeriod systemList : systemUnworkingList) {
			IaRiskCheckPeriod dataSet = new IaRiskCheckPeriod();

			int year = calYearCheckPeriod(systemList.getDateEnd());

			dataSet.setDateStart(systemList.getDateStart());
			dataSet.setDateEnd(systemList.getDateEnd());
			
			dataSet.setExciseCode(systemList.getExciseCode());
			dataSet.setSectorName(systemList.getSectorName());
			dataSet.setAreaName(systemList.getAreaName());
			dataSet.setLongTime(new BigDecimal(year));
//			systemList.getLongTime()
			res.add(dataSet);
		}

		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		int index = 0;
		for (IaRiskCheckPeriod list : res) {
			Int030406Vo resDataCalSet = new Int030406Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
//			if(StringUtils.isNotBlank(list.getKpiExpenseActualAmount())) {
			risk = IntCalculateCriteriaUtil.calculateCriteria(list.getLongTime(), getForm0304.getIaRiskFactorsConfig());
//			}
			resDataCalSet.setIaRiskCheckPeriod(list);

			resDataCalSet
					.setDateFrom(ConvertDateUtils.formatDateToString(list.getDateStart(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet
					.setDateTo(ConvertDateUtils.formatDateToString(list.getDateEnd(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
			
			/* set ExciseDepartmentVo */
//			logger.info(list.getExciseCode());
			if(list.getExciseCode() != null) {
				resDataCalSet.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(list.getExciseCode()));
			}

		}
		
		Collections.sort(resDataCal, new Comparator<Int030406Vo>() {
			@Override
			public int compare(final Int030406Vo object1, final Int030406Vo object2) {
				int obj1 = Integer.valueOf(object1.getIaRiskCheckPeriod().getLongTime().toString());
				int obj2 = Integer.valueOf(object2.getIaRiskCheckPeriod().getLongTime().toString());
				
//				Very --> Little
				return (obj1 > obj2)? -1 : (obj1 < obj2) ? 1 : 0;
				
//				Little --> Very
//				return (obj2 > obj1)? -1 : (obj2 < obj1) ? 1 : 0; 
			}
		});

		return resDataCal;
	}

	public int calYearCheckPeriod( Date endDate) {
		String dateStringEnd = ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY);
		LocalDate dateLocalCerent = LocalDate.now();
		LocalDate dateLocalEnd = ConvertDateUtils.parseStringToLocalDate(dateStringEnd, ConvertDateUtils.DD_MM_YYYY);

		return Period.between(dateLocalEnd, dateLocalCerent).getYears();
	}
}
