package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr03H;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr08D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr08H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr08DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr08HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperUnitPriceReduceTaxVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;

@Service
public class ProductPaperUnitPriceReduceTaxService extends AbstractProductPaperService<ProductPaperUnitPriceReduceTaxVo, TaPaperPr08H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperUnitPriceReduceTaxService.class);

	private static final String PRODUCT_PAPER_UNIT_PRICE_REDUCE_TAX = "ราคาต่อหน่วยสินค้า";
	
	@Autowired
	private TaPaperPr08HRepository taPaperPr08HRepository;
	@Autowired
	private TaPaperPr08DRepository taPaperPr08DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "08";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr08HRepository;
	}
	
	@Override
	protected List<ProductPaperUnitPriceReduceTaxVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		String desc = "ราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี";
		List<ProductPaperUnitPriceReduceTaxVo> datalist = new ArrayList<ProductPaperUnitPriceReduceTaxVo>();
		ProductPaperUnitPriceReduceTaxVo data = null;
		for (int i = 0; i < 5; i++) {
			data = new ProductPaperUnitPriceReduceTaxVo();
			data.setGoodsDesc(desc + (i + 1));
			data.setTaxReduceAmt("1,000.00");
			data.setTaxReduceQty("100.00");
			data.setTaxReducePerUnitAmt("10.00");
			data.setBillNo("001-22-70" + (i + 1));
			data.setBillTaxAmt("1,000.00");
			data.setBillTaxQty("100.00");
			data.setBillTaxPerUnit("10.00");
			data.setDiffTaxReduceAmt("0.00");
			datalist.add(data);
		}
		return datalist;
	}

	@Override
	protected List<ProductPaperUnitPriceReduceTaxVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr08D> entityList = taPaperPr08DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperUnitPriceReduceTaxVo> voList = new ArrayList<>();
		ProductPaperUnitPriceReduceTaxVo vo = null;
		for (TaPaperPr08D entity : entityList) {
			vo = new ProductPaperUnitPriceReduceTaxVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxReduceAmt(entity.getTaxReduceAmt() != null ? entity.getTaxReduceAmt().toString() : NO_VALUE);
			vo.setTaxReduceQty(entity.getTaxReduceQty() != null ? entity.getTaxReduceQty().toString() : NO_VALUE);
			vo.setTaxReducePerUnitAmt(entity.getTaxReducePerUnitAmt() != null ? entity.getTaxReducePerUnitAmt().toString() : NO_VALUE);
			vo.setBillNo(entity.getBillNo() != null ? entity.getBillNo().toString() : NO_VALUE);
			vo.setBillTaxAmt(entity.getBillTaxAmt() != null ? entity.getBillTaxAmt().toString() : NO_VALUE);
			vo.setBillTaxQty(entity.getBillTaxQty() != null ? entity.getBillTaxQty().toString() : NO_VALUE);
			vo.setBillTaxPerUnit(entity.getBillTaxPerUnit() != null ? entity.getBillTaxPerUnit().toString() : NO_VALUE);
			vo.setDiffTaxReduceAmt(entity.getDiffTaxReduceAmt() != null ? entity.getDiffTaxReduceAmt().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperUnitPriceReduceTaxVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_UNIT_PRICE_REDUCE_TAX);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from Utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ขอลดหย่อนตามแบบ ภส. ๐๕-๐๓", "", "", "ใบเสร็จรับเงิน", "", "", "",
				"ผลต่าง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i >= 0 && i <= 4) {
				cell.setCellStyle(thStyle);
			} else if (i >= 5 && i <= 8) {
				cell.setCellStyle(bgKeyIn);
			} else {
				cell.setCellStyle(bgCal);
			}
		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "ภาษีรวม", "ปริมาณ", "ภาษีต่อหน่วย", "เลขที่ใบเสร็จ", "ภาษีรวม", "ปริมาณ",
				"ภาษีต่อหน่วย" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 1) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				if (i >= 0 && i <= 4) {
					cell.setCellStyle(thStyle);
				} else if (i >= 5 && i <= 8) {
					cell.setCellStyle(bgKeyIn);
				} else {
					cell.setCellStyle(bgCal);
				}
			}
		}

		/* width */
		for (int i = 0; i < 10; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

		for (int i = 0; i < 10; i++) {
			if (i < 2 || i > 8) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
			}
			
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (ProductPaperUnitPriceReduceTaxVo vo : voList) {
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
				if (StringUtils.isNotBlank(vo.getTaxReduceAmt()) && !NO_VALUE.equals(vo.getTaxReduceAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxReduceAmt())));
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
				if (StringUtils.isNotBlank(vo.getTaxReduceQty()) && !NO_VALUE.equals(vo.getTaxReduceQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxReduceQty())));
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
				if (StringUtils.isNotBlank(vo.getTaxReducePerUnitAmt()) && !NO_VALUE.equals(vo.getTaxReducePerUnitAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxReducePerUnitAmt())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(vo.getBillNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getBillTaxAmt()) && !NO_VALUE.equals(vo.getBillTaxAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getBillTaxAmt())));
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
				if (StringUtils.isNotBlank(vo.getBillTaxQty()) && !NO_VALUE.equals(vo.getBillTaxQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getBillTaxQty())));
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
				if (StringUtils.isNotBlank(vo.getBillTaxPerUnit()) && !NO_VALUE.equals(vo.getBillTaxPerUnit())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getBillTaxPerUnit())));
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
				if (StringUtils.isNotBlank(vo.getDiffTaxReduceAmt()) && !NO_VALUE.equals(vo.getDiffTaxReduceAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getDiffTaxReduceAmt())));
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
	public List<ProductPaperUnitPriceReduceTaxVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData readVo filename={}", formVo.getFile().getOriginalFilename());
		
		List<ProductPaperUnitPriceReduceTaxVo> voList = new ArrayList<>();
		ProductPaperUnitPriceReduceTaxVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			
			for (Row row : sheet) {
				vo = new ProductPaperUnitPriceReduceTaxVo();
				// Skip on first two row
				if (row.getRowNum() < 2) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						//GoodsDesc
						vo.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						//TaxReduceAmt
						vo.setTaxReduceAmt(ExcelUtils.getCellValueAsString(cell));
						//TaxReduceQty
					} else if (cell.getColumnIndex() == 3) {
						//TaxReduceQty
						vo.setTaxReduceQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						//TaxReducePerUnitAmt
						vo.setTaxReducePerUnitAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						//BillNo
						vo.setBillNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						//BillTaxAmt
						vo.setBillTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						//BillTaxQty
						vo.setBillTaxQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						//BillTaxPerUnit
						vo.setBillTaxPerUnit(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						//DiffTaxReduceAmt
						vo.setDiffTaxReduceAmt(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ProductPaperUnitPriceReduceTaxVo vo) {
		
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr08H entityH = new TaPaperPr08H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr03H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr08HRepository.save(entityH);
		
		List<ProductPaperUnitPriceReduceTaxVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr08D> entityDList = new ArrayList<>();
		TaPaperPr08D entityD = null;
		int i = 1;
		for (ProductPaperUnitPriceReduceTaxVo vo : voList) {
			entityD = new TaPaperPr08D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setTaxReduceAmt(!NO_VALUE.equals(vo.getTaxReduceAmt()) ? NumberUtils.toBigDecimal(vo.getTaxReduceAmt()) : null);
			entityD.setTaxReduceQty(!NO_VALUE.equals(vo.getTaxReduceQty()) ? NumberUtils.toBigDecimal(vo.getTaxReduceQty()) : null);
			entityD.setTaxReducePerUnitAmt(!NO_VALUE.equals(vo.getTaxReducePerUnitAmt()) ? NumberUtils.toBigDecimal(vo.getTaxReducePerUnitAmt()) : null);
			entityD.setBillNo(!NO_VALUE.equals(vo.getBillNo()) ? NumberUtils.toBigDecimal(vo.getBillNo()) : null);
			entityD.setBillTaxAmt(!NO_VALUE.equals(vo.getBillTaxAmt()) ? NumberUtils.toBigDecimal(vo.getBillTaxAmt()) : null);
			entityD.setBillTaxQty(!NO_VALUE.equals(vo.getBillTaxQty()) ? NumberUtils.toBigDecimal(vo.getBillTaxQty()) : null);
			entityD.setBillTaxPerUnit(!NO_VALUE.equals(vo.getBillTaxPerUnit()) ? NumberUtils.toBigDecimal(vo.getBillTaxPerUnit()) : null);
			entityD.setDiffTaxReduceAmt(!NO_VALUE.equals(vo.getDiffTaxReduceAmt()) ? NumberUtils.toBigDecimal(vo.getDiffTaxReduceAmt()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr08DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr08HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
}
