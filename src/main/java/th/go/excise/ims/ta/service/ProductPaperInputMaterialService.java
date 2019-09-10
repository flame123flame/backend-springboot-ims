package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr01D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr01H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr01DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr01HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperInputMaterialService extends AbstractProductPaperService<ProductPaperInputMaterialVo, TaPaperPr01H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInputMaterialService.class);

	private static final String PRODUCT_PAPER_INPUT_MATERIAL = "ตรวจสอบการรับวัตถุดิบ";

	@Autowired
	private TaPaperPr01HRepository taPaperPr01HRepository;
	@Autowired
	private TaPaperPr01DRepository taPaperPr01DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "01";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr01HRepository;
	}

	@Override
	protected List<ProductPaperInputMaterialVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC05);

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperInputMaterialVo> voList = new ArrayList<>();
		ProductPaperInputMaterialVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperInputMaterialVo();
			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
			vo.setMonthStatementQty(wsOasfri0100Vo.getInQty().toString());
			vo.setInputMaterialQty(NO_VALUE);
			vo.setDailyAccountQty(NO_VALUE);
			vo.setExternalDataQty(NO_VALUE);
			vo.setMaxDiffQty(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperInputMaterialVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());

		List<TaPaperPr01D> entityList = taPaperPr01DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperInputMaterialVo> voList = new ArrayList<>();
		ProductPaperInputMaterialVo vo = null;
		for (TaPaperPr01D entity : entityList) {
			vo = new ProductPaperInputMaterialVo();
			vo.setMaterialDesc(entity.getMaterialDesc());
			vo.setInputMaterialQty(entity.getInputMaterialQty() != null ? entity.getInputMaterialQty().toString() : NO_VALUE);
			vo.setDailyAccountQty(entity.getDailyAccountQty() != null ? entity.getDailyAccountQty().toString() : NO_VALUE);
			vo.setMonthStatementQty(entity.getMonthStatementQty() != null ? entity.getMonthStatementQty().toString() : NO_VALUE);
			vo.setExternalDataQty(entity.getExternalDataQty() != null ? entity.getExternalDataQty().toString() : NO_VALUE);
			vo.setMaxDiffQty(entity.getMaxDiffQty() != null ? entity.getMaxDiffQty().toString() : NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperInputMaterialVo> voList, String exportType) {
		logger.info("exportData");

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_INPUT_MATERIAL);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบกำกับภาษีซื้อ", "บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)", "จำนวนรับวัตถุดิบ", "ผลต่างสูงสุด", };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i == 2 || i == 3 || i == 5) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* set sheet */
		for (int i = 0; i <= 6; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 76);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (ProductPaperInputMaterialVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getInputMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getInputMaterialQty()) && !NO_VALUE.equals(data.getInputMaterialQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getInputMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getDailyAccountQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getDailyAccountQty()) && !NO_VALUE.equals(data.getDailyAccountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getDailyAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getMonthStatementQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
				cell.setCellStyle(thStyle);
			} else {
				if (StringUtils.isNotBlank(data.getMonthStatementQty()) && !NO_VALUE.equals(data.getMonthStatementQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getMonthStatementQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
				cell.setCellStyle(cellRight);
			}
			cellNum++;

			// getExternalDataQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getExternalDataQty()) && !NO_VALUE.equals(data.getExternalDataQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getExternalDataQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			/// getMaxDiffQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaxDiffQty()) && !NO_VALUE.equals(data.getMaxDiffQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getMaxDiffQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}
		
		// Create 'Criteria' Sheet
		createSheetCriteria(workbook, formVo);

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

	@Override
	public List<ProductPaperInputMaterialVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());
		
		List<ProductPaperInputMaterialVo> voList = new ArrayList<>();
		ProductPaperInputMaterialVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			// ###### find data for calcualte in excel
			LocalDate localDateStart = toLocalDate(formVo.getStartDate());
			LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

			WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
			wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
			wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
			wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
			wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
			wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
			wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC05);

			for (Row row : sheet) {
				vo = new ProductPaperInputMaterialVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// MaterialDesc
						vo.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// InputMaterialQty
						vo.setInputMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// DailyAccountQty
						vo.setDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// MonthStatementQty
						vo.setMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// ExternalDataQty
						vo.setExternalDataQty(ExcelUtils.getCellValueAsString(cell));
					}
				}
				calculate(vo);
				voList.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return voList;
	}
	
	private void calculate(ProductPaperInputMaterialVo vo) {
		List<BigDecimal> bigDecimalList = new ArrayList<>();
		BigDecimal monthStatementQty = NumberUtils.toBigDecimal(vo.getMonthStatementQty());
		
		if (StringUtils.isNotBlank(vo.getInputMaterialQty()) && !NO_VALUE.equals(vo.getInputMaterialQty())) {
			BigDecimal inputMaterialQty = NumberUtils.toBigDecimal(vo.getInputMaterialQty());
			BigDecimal diffInputMaterialQty = monthStatementQty.subtract(inputMaterialQty).abs();
			bigDecimalList.add(diffInputMaterialQty);
		}
		
		if (StringUtils.isNotBlank(vo.getDailyAccountQty()) && !NO_VALUE.equals(vo.getDailyAccountQty())) {
			BigDecimal dailyAccountQty = NumberUtils.toBigDecimal(vo.getDailyAccountQty());
			BigDecimal diffDailyAccountQty = monthStatementQty.subtract(dailyAccountQty).abs();
			bigDecimalList.add(diffDailyAccountQty);
		}
		
		if (StringUtils.isNotBlank(vo.getExternalDataQty()) && !NO_VALUE.equals(vo.getExternalDataQty())) {
			BigDecimal externalDataQty = NumberUtils.toBigDecimal(vo.getExternalDataQty());
			BigDecimal diffExternalDataQty = monthStatementQty.subtract(externalDataQty).abs();
			bigDecimalList.add(diffExternalDataQty);
		}
		
		if (!bigDecimalList.isEmpty()) {
			BigDecimal maxDiffQty = NumberUtils.max(bigDecimalList);
			vo.setMaxDiffQty(maxDiffQty.toString());
		} else {
			vo.setMaxDiffQty(NO_VALUE);
		}
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr01H entityH = new TaPaperPr01H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr01H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr01HRepository.save(entityH);
		
		List<ProductPaperInputMaterialVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr01D> entityDList = new ArrayList<>();
		TaPaperPr01D entityD = null;
		int i = 1;
		for (ProductPaperInputMaterialVo vo : voList) {
			entityD = new TaPaperPr01D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setMaterialDesc(vo.getMaterialDesc());
			entityD.setInputMaterialQty(!NO_VALUE.equals(vo.getInputMaterialQty()) ? NumberUtils.toBigDecimal(vo.getInputMaterialQty()) : null);
			entityD.setDailyAccountQty(!NO_VALUE.equals(vo.getDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getDailyAccountQty()) : null);
			entityD.setMonthStatementQty(!NO_VALUE.equals(vo.getMonthStatementQty()) ? NumberUtils.toBigDecimal(vo.getMonthStatementQty()) : null);
			entityD.setExternalDataQty(!NO_VALUE.equals(vo.getExternalDataQty()) ? NumberUtils.toBigDecimal(vo.getExternalDataQty()) : null);
			entityD.setMaxDiffQty(!NO_VALUE.equals(vo.getMaxDiffQty()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr01DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr01HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
