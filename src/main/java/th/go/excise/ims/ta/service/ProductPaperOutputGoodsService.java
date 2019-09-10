package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr06D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr06H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr06DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr06HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputGoodsVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ProductPaperOutputGoodsService extends AbstractProductPaperService<ProductPaperOutputGoodsVo, TaPaperPr06H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperOutputGoodsService.class);

	private static final String PRODUCT_PAPER_OUTPUT_GOODS = "ตรวจสอบการจ่ายสินค้าสำเร็จรูป";

	@Autowired
	private TaPaperPr06HRepository taPaperPr06HRepository;
	@Autowired
	private TaPaperPr06DRepository taPaperPr06DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "06";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr06HRepository;
	}
	
	@Override
	protected List<ProductPaperOutputGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

//		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
//		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
//
//		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
//		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
//		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
//		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
//		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
//		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
//		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC05);
//
//		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		String[] splStartDate = formVo.getStartDate().split("/");
		String cvStartDate = splStartDate[1]+splStartDate[0]+"01";
		String[] splEndDate = formVo.getEndDate().split("/");
		String cvEndDate = splEndDate[1]+splEndDate[0]+"01";
		List<WsAnafri0001Vo> wsAnafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), cvStartDate, cvEndDate);
		List<ProductPaperOutputGoodsVo> voList = new ArrayList<>();
		ProductPaperOutputGoodsVo vo = null;
		for (WsAnafri0001Vo wsAnafri0001Vo : wsAnafri0001VoList) {
			vo = new ProductPaperOutputGoodsVo();
			vo.setGoodsDesc(wsAnafri0001Vo.getProductName());
			vo.setTaxGoodsQty(wsAnafri0001Vo.getProductQty().toString());
			vo.setOutputGoodsQty(NO_VALUE);
			vo.setOutputDailyAccountQty(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperOutputGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr06D> entityList = taPaperPr06DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperOutputGoodsVo> voList = new ArrayList<>();
		ProductPaperOutputGoodsVo vo = null;
		for (TaPaperPr06D entity : entityList) {
			vo = new ProductPaperOutputGoodsVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setOutputGoodsQty(entity.getOutputGoodsQty() != null ? entity.getOutputGoodsQty().toString() : NO_VALUE);
			vo.setOutputDailyAccountQty(entity.getOutputDailyAccountQty() != null ? entity.getOutputDailyAccountQty().toString() : NO_VALUE);
			vo.setOutputMonthStatementQty(entity.getOutputMonthStatementQty() != null ? entity.getOutputMonthStatementQty().toString() : NO_VALUE);
			vo.setAuditQty(entity.getAuditQty() != null ? entity.getAuditQty().toString() : NO_VALUE);
			vo.setTaxGoodsQty(entity.getTaxGoodsQty() != null ? entity.getTaxGoodsQty().toString() : NO_VALUE);
			vo.setDiffQty(entity.getDiffQty() != null ? entity.getDiffQty().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperOutputGoodsVo> voList, String exportType) {
		logger.info("exportData");
		
		// create spreadsheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_OUTPUT_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		// tbTH
		String[] tbTH = { "ลำดับ", "รายการ", "ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + "ในใบกำกับภาษีขาย",
				"ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + " (ภส. ๐๗-๐๒)",
				"ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "จากงบเดือน(ภส. ๐๗-๐๔)",
				"ปริมาณที่ได้จากการตรวจสอบ" + "\n" + "(1)", "ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "(ภส. ๐๓-๐๗ (2))",
				"ผลต่าง" + "\n" + "(1-2)"};
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 4) {
				cell.setCellStyle(bgKeyIn);				
			} else if (i == 7) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		// width
		for (int i = 0; i < 8; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		// set data
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (ProductPaperOutputGoodsVo vo : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(vo.getGoodsDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputGoodsQty()) && !NO_VALUE.equals(vo.getOutputGoodsQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputDailyAccountQty()) && !NO_VALUE.equals(vo.getOutputDailyAccountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputDailyAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputMonthStatementQty()) && !NO_VALUE.equals(vo.getOutputMonthStatementQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputMonthStatementQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getAuditQty()) && !NO_VALUE.equals(vo.getAuditQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getAuditQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getTaxGoodsQty()) && !NO_VALUE.equals(vo.getTaxGoodsQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getDiffQty()) && !NO_VALUE.equals(vo.getDiffQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getDiffQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
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
	public List<ProductPaperOutputGoodsVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperOutputGoodsVo> voList = new ArrayList<>();
		ProductPaperOutputGoodsVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				vo = new ProductPaperOutputGoodsVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
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
						// OutputGoodsQty
						vo.setOutputGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// OutputDailyAccountQty
						vo.setOutputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// OutputMonthStatementQty
						vo.setOutputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// AuditQty
						vo.setAuditQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxGoodsQty
						vo.setTaxGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffQty
						vo.setDiffQty(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ProductPaperOutputGoodsVo vo) {
		List<BigDecimal> bigDecimalList = new ArrayList<>();
		BigDecimal outputGoodsQty = NumberUtils.toBigDecimal(vo.getOutputGoodsQty());
		BigDecimal outputDailyAccountQty = NumberUtils.toBigDecimal(vo.getOutputDailyAccountQty());
		BigDecimal outputMonthStatementQty = NumberUtils.toBigDecimal(vo.getOutputMonthStatementQty());
		BigDecimal auditQty = null;
		BigDecimal taxGoodsQty = NumberUtils.toBigDecimal(vo.getTaxGoodsQty());
		
		if (outputGoodsQty != null) {
			bigDecimalList.add(outputGoodsQty);
		}
		if (outputDailyAccountQty != null) {
			bigDecimalList.add(outputDailyAccountQty);
		}
		if (outputMonthStatementQty != null) {
			bigDecimalList.add(outputMonthStatementQty);
		}
		if (!bigDecimalList.isEmpty()) {
			auditQty = NumberUtils.max(bigDecimalList);
			vo.setAuditQty(auditQty.toString());
		} else {
			vo.setAuditQty(NO_VALUE);
		}
		
		if (auditQty != null && taxGoodsQty != null) {
			BigDecimal diffQty = auditQty.subtract(taxGoodsQty);
			vo.setDiffQty(diffQty.toString());
		} else {
			vo.setDiffQty(NO_VALUE);
		}
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr06H entityH = new TaPaperPr06H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr06H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr06HRepository.save(entityH);
		
		List<ProductPaperOutputGoodsVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr06D> entityDList = new ArrayList<>();
		TaPaperPr06D entityD = null;
		int i = 1;
		for (ProductPaperOutputGoodsVo vo : voList) {
			entityD = new TaPaperPr06D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setOutputGoodsQty(!NO_VALUE.equals(vo.getOutputGoodsQty()) ? NumberUtils.toBigDecimal(vo.getOutputGoodsQty()) : null);
			entityD.setOutputDailyAccountQty(!NO_VALUE.equals(vo.getOutputDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getOutputDailyAccountQty()) : null);
			entityD.setOutputMonthStatementQty(!NO_VALUE.equals(vo.getOutputMonthStatementQty()) ? NumberUtils.toBigDecimal(vo.getOutputMonthStatementQty()) : null);
			entityD.setAuditQty(!NO_VALUE.equals(vo.getAuditQty()) ? NumberUtils.toBigDecimal(vo.getAuditQty()) : null);
			entityD.setTaxGoodsQty(!NO_VALUE.equals(vo.getTaxGoodsQty()) ? NumberUtils.toBigDecimal(vo.getTaxGoodsQty()) : null);
			entityD.setDiffQty(!NO_VALUE.equals(vo.getDiffQty()) ? NumberUtils.toBigDecimal(vo.getDiffQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr06DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr06HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
