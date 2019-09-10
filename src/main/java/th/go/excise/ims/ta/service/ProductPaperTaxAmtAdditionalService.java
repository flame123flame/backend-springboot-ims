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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr11D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr11H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr11DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr11HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperTaxAmtAdditionalVo;

@Service
public class ProductPaperTaxAmtAdditionalService extends AbstractProductPaperService<ProductPaperTaxAmtAdditionalVo, TaPaperPr11H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperTaxAmtAdditionalService.class);

	private static final String PRODUCT_PAPER_TAX_AMT_ADDITIONAL = "คำนวณภาษีที่ต้องชำระเพิ่ม";
	
	@Autowired
	private TaPaperPr11HRepository taPaperPr11HRepository;
	@Autowired
	private TaPaperPr11DRepository taPaperPr11DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "11";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr11HRepository;
	}
	
	@Override
	protected List<ProductPaperTaxAmtAdditionalVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ProductPaperTaxAmtAdditionalVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ProductPaperTaxAmtAdditionalVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr11D> entityList = taPaperPr11DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperTaxAmtAdditionalVo> voList = new ArrayList<>();
		ProductPaperTaxAmtAdditionalVo vo = null;
		for (TaPaperPr11D entity : entityList) {
			vo = new ProductPaperTaxAmtAdditionalVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxQty(entity.getTaxQty() != null ? entity.getTaxQty().toString() : NO_VALUE);
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setTaxValue(entity.getTaxValue() != null ? entity.getTaxValue().toString() : NO_VALUE);
			vo.setTaxRateByValue(entity.getTaxRateByValue() != null ? entity.getTaxRateByValue().toString() : NO_VALUE);
			vo.setTaxRateByQty(entity.getTaxRateByQty() != null ? entity.getTaxRateByQty().toString() : NO_VALUE);
			vo.setTaxAdditional(entity.getTaxAdditional() != null ? entity.getTaxAdditional().toString() : NO_VALUE);
			vo.setPenaltyAmt(entity.getPenaltyAmt() != null ? entity.getPenaltyAmt().toString() : NO_VALUE);
			vo.setSurchargeAmt(entity.getSurchargeAmt() != null ? entity.getSurchargeAmt().toString() : NO_VALUE);
			vo.setMoiTaxAmt(entity.getMoiTaxAmt() != null ? entity.getMoiTaxAmt().toString() : NO_VALUE);
			vo.setNetTaxAmt(entity.getNetTaxAmt() != null ? entity.getNetTaxAmt().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperTaxAmtAdditionalVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_TAX_AMT_ADDITIONAL);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		// CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new
		// java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH 1*/
		String[] tbTH = {"ลำดับ", "รายการ", "ปริมาณ", "ราคาขายปลีก", "มูลค่า", "อัตราภาษี", "ภาษีที่ต้องชำระเพิ่มเติม" , "ภาษีที่ต้องชำระเพิ่มเติม", "เบี้ยปรับ", "เงินเพิ่ม", "ภาษีเพื่อราชการส่วนท้องถิ่น", "รวม"};
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);
		}
		
		/* tbTH2 */
		String[] tbTH2 = {"ตามมูลค่า", "ตามปริมาณ", };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(i+5);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		
		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 6));
		
		for (int i = 0; i < 12; i++) {
			if (i < 5 || i > 6) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (ProductPaperTaxAmtAdditionalVo vo : voList) {
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
				if (StringUtils.isNotBlank(vo.getTaxQty()) && !NO_VALUE.equals(vo.getTaxQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxQty())));
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
				if (StringUtils.isNotBlank(vo.getInformPrice()) && !NO_VALUE.equals(vo.getInformPrice())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getInformPrice())));
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
				if (StringUtils.isNotBlank(vo.getTaxValue()) && !NO_VALUE.equals(vo.getTaxValue())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxValue())));
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
				if (StringUtils.isNotBlank(vo.getTaxRateByValue()) && !NO_VALUE.equals(vo.getTaxRateByValue())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxRateByValue())));
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
				if (StringUtils.isNotBlank(vo.getTaxRateByQty()) && !NO_VALUE.equals(vo.getTaxRateByQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxRateByQty())));
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
				if (StringUtils.isNotBlank(vo.getTaxAdditional()) && !NO_VALUE.equals(vo.getTaxAdditional())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxAdditional())));
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
				if (StringUtils.isNotBlank(vo.getPenaltyAmt()) && !NO_VALUE.equals(vo.getPenaltyAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getPenaltyAmt())));
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
				if (StringUtils.isNotBlank(vo.getSurchargeAmt()) && !NO_VALUE.equals(vo.getSurchargeAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getSurchargeAmt())));
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
				if (StringUtils.isNotBlank(vo.getMoiTaxAmt()) && !NO_VALUE.equals(vo.getMoiTaxAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getMoiTaxAmt())));
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
				if (StringUtils.isNotBlank(vo.getNetTaxAmt()) && !NO_VALUE.equals(vo.getNetTaxAmt())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getNetTaxAmt())));
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
	public List<ProductPaperTaxAmtAdditionalVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData readVo filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperTaxAmtAdditionalVo> dataList = new ArrayList<>();
		ProductPaperTaxAmtAdditionalVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				data = new ProductPaperTaxAmtAdditionalVo();
				// Skip on first two row
				if (row.getRowNum() < 2) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						data.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// TaxQty
						data.setTaxQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InformPrice
						data.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// TaxValue
						data.setTaxValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// TaxRateByValue
						data.setTaxRateByValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxRateByQty
						data.setTaxRateByQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// TaxAdditional
						data.setTaxAdditional(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// PenaltyAmt
						data.setPenaltyAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// SurchargeAmt
						data.setSurchargeAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 10) {
						// MoiTaxAmt
						data.setMoiTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 11) {
						// NetTaxAmt
						data.setNetTaxAmt(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr11H entityH = new TaPaperPr11H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr11H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr11HRepository.save(entityH);
		
		List<ProductPaperTaxAmtAdditionalVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr11D> entityDList = new ArrayList<>();
		TaPaperPr11D entityD = null;
		int i = 1;
		for (ProductPaperTaxAmtAdditionalVo vo : voList) {
			entityD = new TaPaperPr11D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setTaxQty(!NO_VALUE.equals(vo.getTaxQty()) ? NumberUtils.toBigDecimal(vo.getTaxQty()) : null);
			entityD.setInformPrice(!NO_VALUE.equals(vo.getInformPrice()) ? NumberUtils.toBigDecimal(vo.getInformPrice()) : null);
			entityD.setTaxValue(!NO_VALUE.equals(vo.getTaxValue()) ? NumberUtils.toBigDecimal(vo.getTaxValue()) : null);
			entityD.setTaxRateByValue(!NO_VALUE.equals(vo.getTaxRateByValue()) ? NumberUtils.toBigDecimal(vo.getTaxRateByValue()) : null);
			entityD.setTaxRateByQty(!NO_VALUE.equals(vo.getTaxRateByQty()) ? NumberUtils.toBigDecimal(vo.getTaxRateByQty()) : null);
			entityD.setTaxAdditional(!NO_VALUE.equals(vo.getTaxAdditional()) ? NumberUtils.toBigDecimal(vo.getTaxAdditional()) : null);
			entityD.setPenaltyAmt(!NO_VALUE.equals(vo.getPenaltyAmt()) ? NumberUtils.toBigDecimal(vo.getPenaltyAmt()) : null);
			entityD.setSurchargeAmt(!NO_VALUE.equals(vo.getSurchargeAmt()) ? NumberUtils.toBigDecimal(vo.getSurchargeAmt()) : null);
			entityD.setMoiTaxAmt(!NO_VALUE.equals(vo.getMoiTaxAmt()) ? NumberUtils.toBigDecimal(vo.getMoiTaxAmt()) : null);
			entityD.setNetTaxAmt(!NO_VALUE.equals(vo.getNetTaxAmt()) ? NumberUtils.toBigDecimal(vo.getNetTaxAmt()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr11DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr11HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
}
