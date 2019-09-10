package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsDataRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.ExportRiskVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030401Service {

	@Autowired
	private IaRiskFactorsDataRepository iaRiskFactorsDataRepository;

	@Autowired
	private Int030405Service int030405Service;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private Int0301Service int0301Service;

	public List<Int030401Vo> factorsDataList(Int030401FormVo form) {
		List<Int030401Vo> Int030401VoList = new ArrayList<>();
		List<IaRiskFactorsData> iaRiskFactorsDataList = new ArrayList<IaRiskFactorsData>();
		iaRiskFactorsDataList = iaRiskFactorsDataRepository.findByIdFactorsByInspectionWorkByBudgetYear(
				form.getIdFactors(), form.getInspectionWork(), form.getBudgetYear());

		List<IaRiskFactorsData> iaRiskFactorsDataListRes = new ArrayList<IaRiskFactorsData>();
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataList) {
			IaRiskFactorsData datanew = new IaRiskFactorsData();
			datanew.setIdFactors(iaRiskFactorsData.getIdFactors());

			datanew.setProjectCode(iaRiskFactorsData.getProjectCode());
			datanew.setProject(iaRiskFactorsData.getProject());

			datanew.setExciseCode(iaRiskFactorsData.getExciseCode());
			datanew.setSector(iaRiskFactorsData.getSector());
			datanew.setArea(iaRiskFactorsData.getArea());

			datanew.setSystemCode(iaRiskFactorsData.getSystemCode());
			datanew.setSystemName(iaRiskFactorsData.getSystemName());

			datanew.setRiskCost(iaRiskFactorsData.getRiskCost());
			datanew.setCreatedDate(iaRiskFactorsData.getCreatedDate());
			iaRiskFactorsDataListRes.add(datanew);
		}

		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		int index = 0;
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataListRes) {

			Int030401Vo resDataCalSet = new Int030401Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			if (StringUtils.isNotBlank(iaRiskFactorsData.getRiskCost())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(iaRiskFactorsData.getRiskCost()),
						getForm0304.getIaRiskFactorsConfig());
			}
			resDataCalSet.setIaRiskFactorsData(iaRiskFactorsData);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			Int030401VoList.add(index, resDataCalSet);
			index++;
		}

		return Int030401VoList;
	}

	public ByteArrayOutputStream chooseExport(BigDecimal idFactors, BigDecimal idConfig, String budgetYear,
			BigDecimal inspectionWork, String riskHrdPaperName, String createUserName, String createLastName,
			String createPosition, String checkUserName, String checkLastName, String checkPosition)
			throws IOException {
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

		if ("3".equals(inspectionWork.toString())) {
			outByteStream = exportInspecWork3(idFactors, idConfig, budgetYear, inspectionWork, riskHrdPaperName,
					createUserName, createLastName, createPosition, checkUserName, checkLastName, checkPosition);
		} else if ("4".equals(inspectionWork.toString())) {
			outByteStream = exportInspecWork4(idFactors, idConfig, budgetYear, inspectionWork, riskHrdPaperName,
					createUserName, createLastName, createPosition, checkUserName, checkLastName, checkPosition);
		} else if ("5".equals(inspectionWork.toString())) {
			outByteStream = exportInspecWork5(idFactors, idConfig, budgetYear, inspectionWork, riskHrdPaperName,
					createUserName, createLastName, createPosition, checkUserName, checkLastName, checkPosition);
		}

		return outByteStream;
	}

	public ByteArrayOutputStream exportInspecWork5(BigDecimal idFactors, BigDecimal idConfig, String budgetYear,
			BigDecimal inspectionWork, String riskHrdPaperName, String createUserName, String createLastName,
			String createPosition, String checkUserName, String checkLastName, String checkPosition)
			throws IOException {

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
		String[] tbTH1 = { "ลำดับ", "รหัสสรรพสามิต", "ภาค", "พื้นที่", "ค่าความเสี่ยง", "ประเมินความเสี่ยง", "" };
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
		for (int i = 0	; i < test ; i++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (i + 10) , rowNum - (i + 10), 0 , 6));
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
		for (int i = 1; i <= 6; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, width * 100);
			} else if (i == 2) {
				sheet.setColumnWidth(i, width * 150);
			} else if (i == 3) {
				sheet.setColumnWidth(i, width * 200);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 9 + test;
		cellNum = 0;

		Int030401FormVo form = new Int030401FormVo();
		form.setBudgetYear(budgetYear);
		form.setIdFactors(idFactors);
		form.setIdConfig(idConfig);
		form.setInspectionWork(inspectionWork);

		List<Int030401Vo> iaRiskFactorsMasterList = factorsDataList(form);

		for (Int030401Vo data : iaRiskFactorsMasterList) {
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
			cell.setCellValue(data.getIaRiskFactorsData().getExciseCode());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getSector());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getArea());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getRiskCost());
			cell.setCellStyle(styleCustom);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
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

	public ByteArrayOutputStream exportInspecWork4(BigDecimal idFactors, BigDecimal idConfig, String budgetYear,
			BigDecimal inspectionWork, String riskHrdPaperName, String createUserName, String createLastName,
			String createPosition, String checkUserName, String checkLastName, String checkPosition)
			throws IOException {

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
		String[] tbTH1 = { "ลำดับ", "รหัสระบบสารสนเทศฯ", "ระบบสารสนเทศฯ ของกรมสรรพสามิต", "ค่าความเสี่ยง",
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

		String[] tbTH2 = { "", "", "", "", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		for (int i = 0	; i < test ; i++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (i + 10) , rowNum - (i + 10), 0 , 5));
		}
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 9, rowNum - 9, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 8, rowNum - 8, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 7, rowNum - 7, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 4, 5));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 5; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, width * 100);
			} else if (i == 2) {
				sheet.setColumnWidth(i, width * 300);
			} else if (i == 3) {
				sheet.setColumnWidth(i, width * 200);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 9 + test;
		cellNum = 0;

		Int030401FormVo form = new Int030401FormVo();
		form.setBudgetYear(budgetYear);
		form.setIdFactors(idFactors);
		form.setIdConfig(idConfig);
		form.setInspectionWork(inspectionWork);

		List<Int030401Vo> iaRiskFactorsMasterList = factorsDataList(form);

		for (Int030401Vo data : iaRiskFactorsMasterList) {
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
			cell.setCellValue(data.getIaRiskFactorsData().getSystemCode());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getSystemName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getRiskCost());
			cell.setCellStyle(styleCustom);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
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

	public ByteArrayOutputStream exportInspecWork3(BigDecimal idFactors, BigDecimal idConfig, String budgetYear,
			BigDecimal inspectionWork, String riskHrdPaperName, String createUserName, String createLastName,
			String createPosition, String checkUserName, String checkLastName, String checkPosition)
			throws IOException {

//		ExportRiskVo exportRiskData = excelUtil.exportConfig(idFactors);
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
		String[] tbTH1 = { "ลำดับ", "โครงการตามยุทธศาสตร์", "ค่าความเสี่ยง", "ประเมินความเสี่ยง", "" };
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

		String[] tbTH2 = { "", "", "", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		for (int i = 0	; i < test ; i++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (i + 10) , rowNum - (i + 10), 0 , 4));
		}
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 9, rowNum - 9, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 8, rowNum - 8, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 7, rowNum - 7, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 3, 4));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 4; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, width * 150);
			} else if (i == 2) {
				sheet.setColumnWidth(i, width * 100);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 9 + test;
		cellNum = 0;

		Int030401FormVo form = new Int030401FormVo();
		form.setBudgetYear(budgetYear);
		form.setIdFactors(idFactors);
		form.setIdConfig(idConfig);
		form.setInspectionWork(inspectionWork);

		List<Int030401Vo> iaRiskFactorsMasterList = factorsDataList(form);

		for (Int030401Vo data : iaRiskFactorsMasterList) {
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
			cell.setCellValue(data.getIaRiskFactorsData().getProject());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getRiskCost());
			cell.setCellStyle(styleCustom);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
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
