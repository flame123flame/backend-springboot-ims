package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr05D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr05H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr05DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr05HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperInputGoodsService extends AbstractProductPaperService<ProductPaperInputGoodsVo, TaPaperPr05H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInputGoodsService.class);

	private static final String PRODUCT_PAPER_INPUT_GOODS = "รับสินค้าสำเร็จรูป";

	@Autowired
	private TaPaperPr05HRepository taPaperPr05HRepository;
	@Autowired
	private TaPaperPr05DRepository taPaperPr05DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "05";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr05HRepository;
	}

	@Override
	protected List<ProductPaperInputGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
//		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC14);

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperInputGoodsVo> voList = new ArrayList<>();
		ProductPaperInputGoodsVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperInputGoodsVo();
			vo.setGoodsDesc(wsOasfri0100Vo.getDataName());
			vo.setInputGoodsQty(NO_VALUE);
			vo.setInputMonthStatementQty(wsOasfri0100Vo.getInQty()!=null?wsOasfri0100Vo.getInQty().toString():NO_VALUE);
			vo.setInputDailyAccountQty(NO_VALUE);
			vo.setMaxDiffQty1(NO_VALUE);
			vo.setMaxDiffQty2(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperInputGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());

		List<TaPaperPr05D> entityList = taPaperPr05DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperInputGoodsVo> voList = new ArrayList<>();
		ProductPaperInputGoodsVo vo = null;
		for (TaPaperPr05D entity : entityList) {
			vo = new ProductPaperInputGoodsVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setInputGoodsQty(entity.getInputGoodsQty() != null ? entity.getInputGoodsQty().toString() : NO_VALUE);
			vo.setInputMonthStatementQty(entity.getInputMonthStatementQty() != null ? entity.getInputMonthStatementQty().toString() : NO_VALUE);
			vo.setInputDailyAccountQty(entity.getInputDailyAccountQty() != null ? entity.getInputDailyAccountQty().toString() : NO_VALUE);
			vo.setMaxDiffQty1(entity.getMaxDiffQty1() != null ? entity.getMaxDiffQty1().toString() : NO_VALUE);
			vo.setMaxDiffQty2(entity.getMaxDiffQty2() != null ? entity.getMaxDiffQty2().toString() : NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperInputGoodsVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_INPUT_GOODS);
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

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรับสินค้าสำเร็จรูป (1)", "ปริมาณรับจากการผลิต", " ", "ผลต่างข้อมูลคู่ที่ 1 (2) - (1)", "ผลต่างข้อมูลคู่ที่ 2 (2) - (3)" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i == 2) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 5 || i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		/* tbTH2 */
		rowNum++;
		row = sheet.createRow(rowNum);
		cell = row.createCell(3);
		cell.setCellValue("งบเดือน (ภส. ๐๗-๐๔) (2)");
		cell.setCellStyle(thStyle);
		cell = row.createCell(4);
		cell.setCellValue("บัญชีประจำวัน (ภส. ๐๗-๐๒) (3)");
		cell.setCellStyle(bgKeyIn);

		/* width */
		int column = 1;
		sheet.setColumnWidth(column, 76 * 150);
		column++;
		sheet.setColumnWidth(column, 76 * 80);
		column++;
		sheet.setColumnWidth(column, 76 * 90);
		column++;
		sheet.setColumnWidth(column, 76 * 90);
		column++;
		sheet.setColumnWidth(column, 76 * 100);
		column++;
		sheet.setColumnWidth(column, 76 * 100);

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
		for (int i = 0; i < 7; i++) {
			if (i != 3 && i != 4) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				if (i < 2) {
					cell.setCellStyle(thStyle);
				} else if (i > 2) {
					cell.setCellStyle(bgCal);
				} else if (i == 2) {
					cell.setCellStyle(bgKeyIn);
				}
			}
		}
		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (ProductPaperInputGoodsVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getInputGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getInputGoodsQty()) && !NO_VALUE.equals(data.getInputGoodsQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getInputGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getInputMonthStatementQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getInputMonthStatementQty()) && !NO_VALUE.equals(data.getInputMonthStatementQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getInputMonthStatementQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getInputDailyAccountQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getInputDailyAccountQty()) && !NO_VALUE.equals(data.getInputDailyAccountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getInputDailyAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getMaxDiffQty1
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaxDiffQty1()) && !NO_VALUE.equals(data.getMaxDiffQty1())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getMaxDiffQty1())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getMaxDiffQty2
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaxDiffQty2()) && !NO_VALUE.equals(data.getMaxDiffQty2())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getMaxDiffQty2())));
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
	public List<ProductPaperInputGoodsVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperInputGoodsVo> voList = new ArrayList<>();
		ProductPaperInputGoodsVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			// ###### find data for calcualte in excel
			LocalDate localDateStart = toLocalDate(formVo.getStartDate());
			LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

			WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
			wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
			// wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
			wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT);
			wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
			wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
			wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC14);

			List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
			for (Row row : sheet) {
				vo = new ProductPaperInputGoodsVo();
				// Skip on second row
				if (row.getRowNum() < 2) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						vo.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// nputGoodsQty
						vo.setInputGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InputMonthStatementQty
						vo.setInputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// InputDailyAccountQty
						vo.setInputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// MaxDiffQty
					} else if (cell.getColumnIndex() == 6) {
						// MaxDiffQty
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
	
	private void calculate(ProductPaperInputGoodsVo vo) {
		BigDecimal inputGoodsQty = NumberUtils.toBigDecimal(vo.getInputGoodsQty());
		BigDecimal inputMonthStatementQty = NumberUtils.toBigDecimal(vo.getInputMonthStatementQty());
		BigDecimal inputDailyAccountQty = NumberUtils.toBigDecimal(vo.getInputDailyAccountQty());
		
		if (inputGoodsQty != null && inputMonthStatementQty != null) {
			BigDecimal maxDiffQty1 = inputGoodsQty.subtract(inputMonthStatementQty);
			vo.setMaxDiffQty1(maxDiffQty1.toString());
		} else {
			vo.setMaxDiffQty1(NO_VALUE);
		}
		
		if (inputGoodsQty != null && inputDailyAccountQty != null) {
			BigDecimal maxDiffQty2 = inputGoodsQty.subtract(inputDailyAccountQty);
			vo.setMaxDiffQty2(maxDiffQty2.toString());
		} else {
			vo.setMaxDiffQty2(NO_VALUE);
		}
	}

	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr05H entityH = new TaPaperPr05H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr05H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr05HRepository.save(entityH);

		List<ProductPaperInputGoodsVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr05D> entityDList = new ArrayList<>();
		TaPaperPr05D entityD = null;
		int i = 1;
		for (ProductPaperInputGoodsVo vo : voList) {
			entityD = new TaPaperPr05D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setInputGoodsQty(!NO_VALUE.equals(vo.getInputGoodsQty()) ? NumberUtils.toBigDecimal(vo.getInputGoodsQty()) : null);
			entityD.setInputMonthStatementQty(!NO_VALUE.equals(vo.getInputMonthStatementQty()) ? NumberUtils.toBigDecimal(vo.getInputMonthStatementQty()) : null);
			entityD.setInputDailyAccountQty(!NO_VALUE.equals(vo.getInputDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getInputDailyAccountQty()) : null);
			entityD.setMaxDiffQty1(!NO_VALUE.equals(vo.getMaxDiffQty1()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty1()) : null);
			entityD.setMaxDiffQty2(!NO_VALUE.equals(vo.getMaxDiffQty2()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty2()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr05DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr05HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
