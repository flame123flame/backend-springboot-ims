package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskQtnConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int020301JdbcRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.ExportRiskVo;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;
import th.go.excise.ims.preferences.vo.ExcelHeaderNameVo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Int020301Service {

	@Autowired
	private Int020301JdbcRepository int020301JdbcRepository;

	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRep;

	@Autowired
	private IaRiskQtnConfigRepository iaRiskQtnConfigRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRep;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private Int0301Service int0301Service;

	public List<Int020301HeaderVo> findHeaderByIdSide(String idSideStr, String budgetYear) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		return int020301JdbcRepository.findHeaderByIdSide(idSide, budgetYear);
	}

	private String conditionConfigs(BigDecimal value, BigDecimal size, IaRiskQtnConfig configs) {
		double highStart = configs.getHighStart().doubleValue();
		double mediumStart = configs.getMediumStart().doubleValue();
		double mediumEnd = configs.getMediumEnd().doubleValue();
		double lowStart = configs.getHighStart().doubleValue();
		double compareValue = (value.doubleValue() / size.doubleValue()) * 100;
		if (compareValue > highStart) {
			return configs.getHighCondition();
		} else if (compareValue >= mediumStart && compareValue <= mediumEnd) {
			return configs.getMediumCondition();
		} else if (compareValue < lowStart) {
			return configs.getLowCondition();
		} else {
			return configs.getLowCondition();
		}
	}

	IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();

	public List<Int020301InfoVo> findInfoByIdHdr(String idHdrStr, String budgetYear, String secter) {
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		IaRiskQtnConfig configs = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idHdr, "N");
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idHdr, budgetYear, secter);
		for (Int020301InfoVo data : datas) {
			// Sides Data
			List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdHdr(idHdr, budgetYear,
					data.getAreaName());

			IaRiskFactorsConfig configFactor = matchConfigQtnWithConfig(configs, new BigDecimal(idHdrStr));
			IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
			String condition = "";
			double all = 0;
			double declineValue = 0;
			for (Int020301DataVo sideDtl : sideDtls) {
//				vo.setAcceptValue(rs.getBigDecimal("ACCEPT"));
//				vo.setDeclineValue(rs.getBigDecimal("DECLINE"));
				// Calculate RiskName
				cal = new IntCalculateCriteriaVo();
				double sum = 0;
				if (sideDtl.getDeclineValue() == null) {
					sideDtl.setDeclineValue(new BigDecimal(0));
				}
				if (sideDtl.getAcceptValue() == null) {
					sideDtl.setAcceptValue(new BigDecimal(0));
				}
				if (sideDtl.getDeclineValue() != null) {
					sum = sideDtl.getDeclineValue().doubleValue();
					if (sideDtl.getAcceptValue() != null) {
						sum = sum + sideDtl.getAcceptValue().doubleValue();
					}
					all = all + sum;
					declineValue = declineValue + sideDtl.getDeclineValue().doubleValue();
					condition = conditionConfigs(sideDtl.getDeclineValue(), new BigDecimal(sum), configs);
					double sumPercent = 0;
					if(((sideDtl.getDeclineValue().doubleValue() + sideDtl.getAcceptValue().doubleValue()) * 100) > 0) {
						sumPercent = sideDtl.getDeclineValue().doubleValue()
								/ (sideDtl.getDeclineValue().doubleValue() + sideDtl.getAcceptValue().doubleValue()) * 100;
					}
					
					cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(sumPercent), configFactor);
				} else {
					if (sideDtl.getAcceptValue() != null) {

						condition = conditionConfigs(new BigDecimal(0), sideDtl.getAcceptValue(), configs);
						cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(0), configFactor);

					} else {

						condition = conditionConfigs(new BigDecimal(0), new BigDecimal(0), configs);
						cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(0), configFactor);

					}
				}

				if (">".equals(condition)) { // High
					sideDtl.setRiskName(configs.getHigh());
					sideDtl.setRiskColor(configs.getHighColor());
				} else if ("<>".equals(condition)) { // Medium
					sideDtl.setRiskName(configs.getMedium());
					sideDtl.setRiskColor(configs.getMediumColor());
				} else if ("<".equals(condition)) { // Low
					sideDtl.setRiskName(configs.getLow());
					sideDtl.setRiskColor(configs.getLowColor());
				}

				sideDtl.setIntCalculateCriteriaVo(cal);
			}
			data.setSideDtls(sideDtls);
			// RiskQuantity
			data.setRiskQuantity(new BigDecimal(sideDtls.size()));
			// Sum Data
			condition = conditionConfigs(new BigDecimal(declineValue), new BigDecimal(all), configs);
			double avg = 0;
			if(all > 0) {
				avg = (declineValue / all) * 100;
			}
			cal = new IntCalculateCriteriaVo();
			cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(avg), configFactor);
			data.setIntCalculateCriteriaVo(cal);

			// calculate Risk
			if (">".equals(condition)) { // High
				data.setRiskText(configs.getHigh());
				data.setRiskColor(configs.getHighColor());
			} else if ("<>".equals(condition)) { // Medium
				data.setRiskText(configs.getMedium());
				data.setRiskColor(configs.getMediumColor());
			} else if ("<".equals(condition)) { // Low
				data.setRiskText(configs.getLow());
				data.setRiskColor(configs.getLowColor());
			}
			data.setAvgRisk(new BigDecimal(Math.round(avg)));
			// Finding Sector and Area Name
			List<ExciseDepartment> exciseDepts = ApplicationCache.getExciseSectorList();
			data.setStatusText(ApplicationCache
					.getParamInfoByCode(IaConstants.IA_STATUS_REPLY_QTN.PARAM_GROUP_CODE, data.getStatusText())
					.getValue1());
			for (ExciseDepartment exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
					data.setSectorName(exciseDept.getDeptName());
				}
			}
			ExciseDepartment area = ApplicationCache.getExciseDepartment(data.getAreaName());
			if (!"0000".equals(area.getOfficeCode().substring(2, 6))) {
				data.setAreaName(area.getDeptName());
			} else {
				data.setAreaName("");
			}
			
			if(data.getOfficeCode() != null) {
				ExciseDepartmentVo exciseDepartmentVo = ExciseDepartmentUtil.getExciseDepartment(data.getOfficeCode());
				data.setExciseDepartmentVo(exciseDepartmentVo);
			}
		}

		return datas;
	}

	public List<Int020301InfoVo> findInfoByIdHdrRisk(String idHdrStr, String budgetYear, String idConfigStr, String officeCode) {
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		BigDecimal idConfig = new BigDecimal(idConfigStr);
		IaRiskQtnConfig configs = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idHdr, "N");
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idHdr, budgetYear, officeCode );
		Optional<IaRiskFactorsConfig> config = iaRiskFactorsConfigRep.findById(idConfig);
		IaRiskFactorsConfig configFactor = iaRiskFactorsConfigRepository.findById(idConfig).get();
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		if (config.isPresent()) {
			for (Int020301InfoVo data : datas) {
				// Sides Data
				List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdHdr(idHdr, budgetYear,
						data.getAreaName());
				String condition = "";
				double all = 0;
				double declineValue = 0;
				for (Int020301DataVo sideDtl : sideDtls) {
					// Calculate RiskName
					double sum = 0;
					cal = new IntCalculateCriteriaVo();
					if (sideDtl.getDeclineValue() == null) {
						sideDtl.setDeclineValue(new BigDecimal(0));
					}
					if (sideDtl.getAcceptValue() == null) {
						sideDtl.setAcceptValue(new BigDecimal(0));
					}
					if (sideDtl.getDeclineValue() != null) {
						sum = sideDtl.getDeclineValue().doubleValue();
						if (sideDtl.getAcceptValue() != null) {
							sum = sum + sideDtl.getAcceptValue().doubleValue();
						}
						all = all + sum;
						declineValue = declineValue + sideDtl.getDeclineValue().doubleValue();
						condition = conditionConfigs(sideDtl.getDeclineValue(), new BigDecimal(sum), configs);
						
						double sumPercent = 0;
						if(((sideDtl.getDeclineValue().doubleValue() + sideDtl.getAcceptValue().doubleValue())* 100) > 0) {
							sumPercent = sideDtl.getDeclineValue().doubleValue()
									/ (sideDtl.getDeclineValue().doubleValue() + sideDtl.getAcceptValue().doubleValue()) * 100;
						}
						
						cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(sumPercent), configFactor);
					} else {
						if (sideDtl.getAcceptValue() != null) {
							condition = conditionConfigs(new BigDecimal(0), sideDtl.getAcceptValue(), configs);
							cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(0), configFactor);
						} else {
							condition = conditionConfigs(new BigDecimal(0), new BigDecimal(0), configs);
							cal = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(0), configFactor);
						}
					}
					if (">".equals(condition)) { // High
						sideDtl.setRiskName(configs.getHigh());
						sideDtl.setRiskColor(configs.getHighColor());
					} else if ("<>".equals(condition)) { // Medium
						sideDtl.setRiskName(configs.getMedium());
						sideDtl.setRiskColor(configs.getMediumColor());
					} else if ("<".equals(condition)) { // Low
						sideDtl.setRiskName(configs.getLow());
						sideDtl.setRiskColor(configs.getLowColor());
					}
					sideDtl.setIntCalculateCriteriaVo(cal);
				}
				data.setSideDtls(sideDtls);
				// RiskQuantity
				data.setRiskQuantity(new BigDecimal(sideDtls.size()));
				// Sum Data
				double avg =  0;
				if(all > 0) {
					avg = (declineValue / all) * 100;
				}

				if (avg >= 0) {
					data.setAvgRisk(new BigDecimal(avg));
				} else {
					data.setAvgRisk(new BigDecimal(0));
				}
				IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(data.getAvgRisk(),
						config.get());

				cal = new IntCalculateCriteriaVo();
				cal = IntCalculateCriteriaUtil.calculateCriteria(data.getAvgRisk(), configFactor);
				data.setIntCalculateCriteriaVo(cal);

				data.setRiskColor(risk.getColor());
				data.setRiskText(risk.getTranslatingRisk());
				data.setRiskNum(risk.getRiskRate());
				// Finding Sector and Area Name
				List<ExciseDepartment> exciseDepts = ApplicationCache.getExciseSectorList();
				data.setStatusText(ApplicationCache
						.getParamInfoByCode(IaConstants.IA_STATUS.PARAM_GROUP_CODE, data.getStatusText()).getValue1());
				for (ExciseDepartment exciseDept : exciseDepts) {
					if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
						data.setSectorName(exciseDept.getDeptName());
					}
				}
				ExciseDepartment area = ApplicationCache.getExciseDepartment(data.getAreaName());
				if (!"0000".equals(area.getOfficeCode().substring(2, 6))) {
					data.setAreaName(area.getDeptName());
				} else {
					data.setAreaName("");
				}
				
				/* set ExciseDepartmentVo */
				if(data.getOfficeCode() != null) {
					data.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(data.getOfficeCode()));
				}
			}
		}
		return datas;
	}

	public IaRiskFactorsConfig matchConfigQtnWithConfig(IaRiskQtnConfig configQtn, BigDecimal IdHdr) {
		IaRiskFactorsConfig con = new IaRiskFactorsConfig();
		IaQuestionnaireHdr hdrList = iaQuestionnaireHdrRepository.findById(IdHdr).get();
		
		if(hdrList.getFactorLevel() != null) {
			con.setFactorsLevel(new BigDecimal(hdrList.getFactorLevel()));
		}
		con.setVerylow(configQtn.getVerylow());
		con.setVerylowStart((configQtn.getVerylowStart() != null) ? configQtn.getVerylowStart().toString() : "");
		con.setVerylowEnd((configQtn.getVerylowEnd() != null) ? configQtn.getVerylowEnd().toString() : "");
		con.setVerylowCondition(configQtn.getVerylowCondition());
		con.setVerylowRating(configQtn.getVerylowRating());
		con.setVerylowColor(configQtn.getVerylowColor());

		con.setLow(configQtn.getLow());
		con.setLowStart((configQtn.getLowStart() != null) ? configQtn.getLowStart().toString() : "");
		con.setLowEnd((configQtn.getLowEnd() != null) ? configQtn.getLowEnd().toString() : "");
		con.setLowCondition(configQtn.getLowCondition());
		con.setLowRating(configQtn.getLowRating());

//		    String colorLow = colorConfigQtnToColorConfig(configQtn.getLowColor());
//			con.setLowColor(colorLow);
		con.setLowColor(configQtn.getLowColor());

		con.setMedium(configQtn.getMedium());
		con.setMediumStart((configQtn.getMediumStart() != null) ? configQtn.getMediumStart().toString() : "");
		con.setMediumEnd((configQtn.getMediumEnd() != null) ? configQtn.getMediumEnd().toString() : "");
		con.setMediumCondition(configQtn.getMediumCondition());
		con.setMediumRating(configQtn.getMediumRating());

//			String colorMedium = colorConfigQtnToColorConfig(configQtn.getMediumColor());
//			con.setMediumColor(colorMedium);
		con.setMediumColor(configQtn.getMediumColor());

		con.setHigh(configQtn.getHigh());
		con.setHighStart((configQtn.getHighStart() != null) ? configQtn.getHighStart().toString() : "");
		con.setHighEnd((configQtn.getHighEnd() != null) ? configQtn.getHighEnd().toString() : "");
		con.setHighCondition(configQtn.getHighCondition());
		con.setHighRating(configQtn.getHighRating());

//			String colorHigh = colorConfigQtnToColorConfig(configQtn.getHighColor());
//			con.setHighColor(colorHigh);
		con.setHighColor(configQtn.getHighColor());

		con.setVeryhigh(configQtn.getVeryhigh());
		con.setVeryhighStart((configQtn.getVeryhighStart() != null) ? configQtn.getVeryhighStart().toString() : "");
		con.setVeryhighEnd((configQtn.getVeryhighEnd() != null) ? configQtn.getVeryhighEnd().toString() : "");
		con.setVeryhighCondition(configQtn.getVeryhighCondition());
		con.setVeryhighRating(configQtn.getVeryhighRating());
		con.setVeryhighColor(configQtn.getVeryhighColor());

		return con;
	}

	public static String colorConfigQtnToColorConfig(String color) {
		String colorConfig = "";
		if ("red".equals(color)) {
			colorConfig = IaConstants.IA_RISK_COLOR.COLOR5;

		} else if ("yellow".equals(color)) {
			colorConfig = IaConstants.IA_RISK_COLOR.COLOR3;

		} else if ("green".equals(color)) {
			colorConfig = IaConstants.IA_RISK_COLOR.COLOR2;

		}
		return colorConfig;

	}

	DecimalFormat formatter = new DecimalFormat("#,##0.00");

	public ByteArrayOutputStream exportInt020301(String idHdrStr, String budgetYear) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		List<ExcelHeaderNameVo> headerNames = new ArrayList<>();
		String[] tbTH1 = { "ลำดับ", "หน่ายงาน", "จำนวนด้านความเสี่ยง", "ไม่มี/ไม่ใช่ (%)", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		headerNames = iaQuestionnaireSideJdbcRep.findNameByIdHdr(idHdr);
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue("ด้าน");
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		String[] tbTH2 = { "ส่งเมื่อ" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		// Empty Cell Row [1]
		for (int i = 0; i < 5; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(headerNames.get(i / 3).getName());
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 5 + headerNames.size() * 3; i < 5 + headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		rowNum++;

		// Row [2]
		row = sheet.createRow(rowNum);
		int cellNumtbTH3 = 0;
		// Empty Cell Row [2]
		for (int i = 0; i < 5; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}
		// Text Cell Row [2]
		String[] tbTH3 = { "มี/ใช่", "ไม่มี/ไม่ใช่", "ระดับความเสี่ยง" };
		for (int i = 0; i < headerNames.size(); i++) {
			for (int j = 0; j < tbTH3.length; j++) {
				cell = row.createCell(cellNumtbTH3);
				cell.setCellValue(tbTH3[j]);
				cell.setCellStyle(thStyle);
				cellNumtbTH3++;
			}
		}
		// Empty Cell Row [2]
		for (int i = 5 + headerNames.size() * 3; i < 5 + headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 5 + headerNames.size() * 3 - 1));
		for (int i = 1; i <= headerNames.size(); i++) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 5 + ((i - 1) * 3), 5 + (i * 3) - 1));
		}

		for (int i = 0; i < 5; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		for (int i = headerNames.size() * 3 + 5; i <= headerNames.size() * 3 + 5; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 5; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}
		for (int i = 5; i <= headerNames.size() * 3 + 5; i++) {
			if (i % 3 == 2) {
				sheet.setColumnWidth(i, width * 50);
			} else {
				sheet.setColumnWidth(i, width * 40);
			}
		}
		for (int i = headerNames.size() * 3 + 5; i < headerNames.size() * 3 + 5; i++) {
			sheet.setColumnWidth(i, width * 76);
		}

		/* start details */
		int count = 1;
		rowNum = 3;
		cellNum = 0;
		List<Int020301InfoVo> details = findInfoByIdHdr(idHdrStr, budgetYear, null);
		for (Int020301InfoVo detail : details) {
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
			// Column 2 - 3
			CellStyle styleCustom2 = tdStyle;
			styleCustom2.setAlignment(HorizontalAlignment.LEFT);
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getSectorName() + "  " + detail.getAreaName());
			cell.setCellStyle(styleCustom2);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getRiskQuantity().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 5
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
				cell.setCellValue(detail.getAvgRisk().doubleValue());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 6
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
				cell.setCellValue(detail.getRiskText());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			for (Int020301DataVo sideDtl : detail.getSideDtls()) {
				// Column cellNum+1+1
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getAcceptValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+2
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getDeclineValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+3
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getRiskName());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
			}

			// Column detail.getSideDtls().size()+1
			cell = row.createCell(cellNum);
			if (detail.getSentDate() != null) {
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(
							ConvertDateUtils.formatDateToString(detail.getSentDate(), ConvertDateUtils.DD_MM_YYYY));
				} else {
					cell.setCellValue("-");
				}
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

	public ByteArrayOutputStream exportInt020301On030402(String idHdrStr, String budgetYear, BigDecimal idConfig,
			String riskHrdPaperName, String createUserName, String createLastName, String createPosition,
			String checkUserName, String checkLastName, String checkPosition) throws IOException {
		/* create spreadsheet */
		ExportRiskVo exportRiskData = excelUtil.exportConfig(idConfig);
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle tdStyleCenter = ExcelUtils.createCenterCellStyle(workbook);
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
		List<ExcelHeaderNameVo> headerNames = new ArrayList<>();
		String[] tbTH1 = { "ลำดับ", "หน่วยงาน", "จำนวนด้านความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		headerNames = iaQuestionnaireSideJdbcRep.findNameByIdHdr(idHdr);
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue("ด้าน");
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		String[] tbTH2 = { "ส่งเมื่อ", "ไม่มี/ไม่ใช่ (%)", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		// Empty Cell Row [1]
		for (int i = 0; i < 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(headerNames.get(i / 3).getName());
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 3 + headerNames.size() * 3; i < 3 + headerNames.size() * 3 + 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		rowNum++;

		// Row [2]
		row = sheet.createRow(rowNum);
		int cellNumtbTH3 = 0;
		// Empty Cell Row [2]
		for (int i = 0; i < 3; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}
		// Text Cell Row [2]
		String[] tbTH3 = { "มี/ใช่", "ไม่มี/ไม่ใช่", "ระดับความเสี่ยง" };
		for (int i = 0; i < headerNames.size(); i++) {
			for (int j = 0; j < tbTH3.length; j++) {
				cell = row.createCell(cellNumtbTH3);
				cell.setCellValue(tbTH3[j]);
				cell.setCellStyle(thStyle);
				cellNumtbTH3++;
			}
		}
		// Empty Cell Row [2]
		for (int i = 3 + headerNames.size() * 3; i < 3 + headerNames.size() * 3 + 3; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		int colcount = (headerNames.size() * 3) + 6;
		for (int j = 0; j < test; j++) {
			sheet.addMergedRegion(new CellRangeAddress(rowNum - (j + 10), rowNum - (j + 10), 0, colcount));
		}
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 9, rowNum - 9, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 8, rowNum - 8, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 7, rowNum - 7, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, colcount));
		sheet.addMergedRegion(new CellRangeAddress(7 + test, 7 + test, 3, 3 + headerNames.size() * 3 - 1));
		for (int i = 1; i <= headerNames.size(); i++) {
			sheet.addMergedRegion(new CellRangeAddress(8 + test, 8 + test, 3 + ((i - 1) * 3), 3 + (i * 3) - 1));
		}
		for (int i = 0; i < 3; i++) {
			sheet.addMergedRegion(new CellRangeAddress(7 + test, 9 + test, i, i));
		}
		for (int i = headerNames.size() * 3 + 3; i <= headerNames.size() * 3 + 3 + 3; i++) {
			sheet.addMergedRegion(new CellRangeAddress(7 + test, 9 + test, i, i));
		}
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 3; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}
		for (int i = 3; i <= headerNames.size() * 3 + 3; i++) {
			if (i % 3 == 2) {
				sheet.setColumnWidth(i, width * 50);
			} else {
				sheet.setColumnWidth(i, width * 40);
			}
		}
		for (int i = headerNames.size() * 3 + 3; i < headerNames.size() * 3 + 3 + 4; i++) {
			sheet.setColumnWidth(i, width * 76);
		}

		/* start details */
		int count = 1;
		rowNum = 10 + test;
		cellNum = 0;
		String idConfigStr = idConfig.toString();
		List<Int020301InfoVo> details = findInfoByIdHdrRisk(idHdrStr, budgetYear, idConfigStr, null);
		for (Int020301InfoVo detail : details) {
			// Re Initial
			cellNum = 0;
			row = sheet.createRow(rowNum);
			// Column 1
			CellStyle styleCustom = tdStyle;
			styleCustom.setAlignment(HorizontalAlignment.CENTER);
			cell = row.createCell(cellNum);
			cell.setCellValue(count++);
			cell.setCellStyle(tdStyleCenter);
			cellNum++;
			// Column 2 - 3
			CellStyle styleCustom2 = tdStyle;
			styleCustom2.setAlignment(HorizontalAlignment.LEFT);
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getSectorName() + " " + detail.getAreaName());
			cell.setCellStyle(styleCustom2);
			cellNum++;
			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getRiskQuantity().doubleValue());
			cell.setCellStyle(tdStyleCenter);
			cellNum++;

			for (Int020301DataVo sideDtl : detail.getSideDtls()) {
				// Column cellNum+1+1
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getAcceptValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyleCenter);
				cellNum++;
				// Column cellNum+1+2
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getDeclineValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyleCenter);
				cellNum++;
				// Column cellNum+1+3
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(sideDtl.getRiskName());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyleCenter);
				cellNum++;
			}

			// Column detail.getSideDtls().size()+1
			cell = row.createCell(cellNum);
			if (detail.getSentDate() != null) {
				if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
					cell.setCellValue(
							ConvertDateUtils.formatDateToString(detail.getSentDate(), ConvertDateUtils.DD_MM_YYYY));
				} else {
					cell.setCellValue("-");
				}
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyleCenter);
			cellNum++;

			// Column detail.getSideDtls().size()+3
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
				cell.setCellValue(detail.getAvgRisk().doubleValue());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyleCenter);
			cellNum++;

			// Column detail.getSideDtls().size()+4
			DecimalFormat df2 = new DecimalFormat(".##");
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
				cell.setCellValue(df2.format(detail.getRiskNum().doubleValue()));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyleCenter);
			cellNum++;

			// Column detail.getSideDtls().size()+5
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE)) {
				cell.setCellValue(detail.getRiskText());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyleCenter);
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

	public void saveConclude(BigDecimal id, IaQuestionnaireHdr form) {
		IaQuestionnaireHdr res = iaQuestionnaireHdrRepository.findById(id).get();
		res.setConclude(form.getConclude());
		iaQuestionnaireHdrRepository.save(res);
		questionnaireService.updateStatusIaQuestionnaire(id, IaConstants.IA_STATUS.STATUS_6_CODE);
	}

}
