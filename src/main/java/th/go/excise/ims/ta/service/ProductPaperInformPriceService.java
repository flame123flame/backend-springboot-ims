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
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr09D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr09H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr09DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr09HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInformPriceVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ProductPaperInformPriceService extends AbstractProductPaperService<ProductPaperInformPriceVo, TaPaperPr09H> {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInformPriceService.class);

	private static final String PRODUCT_PAPER_IN_FORM_PRICE = "ตรวจสอบด้านราคา";
	
	@Autowired
	private TaPaperPr09HRepository taPaperPr09HRepository;
	@Autowired
	private TaPaperPr09DRepository taPaperPr09DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "09";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr09HRepository;
	}
	
	@Override
	protected List<ProductPaperInformPriceVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		String[] splStartDate = formVo.getStartDate().split("/");
		String cvStartDate = splStartDate[1]+splStartDate[0]+"01";
		String[] splEndDate = formVo.getEndDate().split("/");
		String cvEndDate = splEndDate[1]+splEndDate[0]+"01";
		List<WsAnafri0001Vo> wsAnafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), cvStartDate, cvEndDate);
		List<ProductPaperInformPriceVo> voList = new ArrayList<>();
		ProductPaperInformPriceVo vo = null;
		for (WsAnafri0001Vo wsAnafri0001Vo : wsAnafri0001VoList) {
			vo = new ProductPaperInformPriceVo();
			vo.setGoodsDesc(wsAnafri0001Vo.getProductName());
			vo.setTaxPrice(wsAnafri0001Vo.getProductPrice().toString());
			vo.setExternalPrice(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperInformPriceVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr09D> entityList = taPaperPr09DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperInformPriceVo> voList = new ArrayList<>();
		ProductPaperInformPriceVo vo = null;
		for (TaPaperPr09D entity : entityList) {
			vo = new ProductPaperInformPriceVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setExternalPrice(entity.getExternalPrice() != null ? entity.getExternalPrice().toString() : NO_VALUE);
			vo.setDeclarePrice(entity.getDeclarePrice() != null ? entity.getDeclarePrice().toString() : NO_VALUE);
			vo.setRetailPrice(entity.getRetailPrice() != null ? entity.getRetailPrice().toString() : NO_VALUE);
			vo.setTaxPrice(entity.getTaxPrice() != null ? entity.getTaxPrice().toString() : NO_VALUE);
			vo.setDiffPrice(entity.getDiffPrice() != null ? entity.getDiffPrice().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperInformPriceVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_IN_FORM_PRICE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "ชื่อสินค้า (ภส.๐๓-๐๗)", "ราคาตามแบบแจ้ง ภส. ๐๒-๐๑", "ราคาจากข้อมูลภายนอก",
				"ราคาต่อหน่วยตามประกาศกรม", "ราคาขายปลีกแนะนำจาก ภส. ๐๓-๐๗", "ผลต่างราคา" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 2 && i < 4) {
				cell.setCellStyle(bgKeyIn);				
			} else if (i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
//		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (ProductPaperInformPriceVo vo : voList) {
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
				if (StringUtils.isNotBlank(vo.getExternalPrice()) && !NO_VALUE.equals(vo.getExternalPrice())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getExternalPrice())));
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
				if (StringUtils.isNotBlank(vo.getDeclarePrice()) && !NO_VALUE.equals(vo.getDeclarePrice())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getDeclarePrice())));
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
				if (StringUtils.isNotBlank(vo.getRetailPrice()) && !NO_VALUE.equals(vo.getRetailPrice())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getRetailPrice())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

//			cell = row.createCell(cellNum);
//			cell.setCellValue(data.getTaxPrice());
//			cell.setCellStyle(cellRight);
//			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getDiffPrice()) && !NO_VALUE.equals(vo.getDiffPrice())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getDiffPrice())));
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
	public List<ProductPaperInformPriceVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperInformPriceVo> voList = new ArrayList<>();
		ProductPaperInformPriceVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			
			// ###### find data for calcualte in excel
			String[] splStartDate = formVo.getStartDate().split("/");
			String cvStartDate = splStartDate[1]+splStartDate[0]+"01";
			String[] splEndDate = formVo.getEndDate().split("/");
			String cvEndDate = splEndDate[1]+splEndDate[0]+"01";
			List<WsAnafri0001Vo> wsAnafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), cvStartDate, cvEndDate);
			for (Row row : sheet) {
				vo = new ProductPaperInformPriceVo();
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
						// InformPrice
						vo.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						vo.setExternalPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						vo.setDeclarePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						vo.setRetailPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxPrice
						vo.setTaxPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffPrice
						vo.setDiffPrice(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ProductPaperInformPriceVo vo) {
		BigDecimal externalPrice = NumberUtils.toBigDecimal(vo.getExternalPrice());
		BigDecimal taxPrice = NumberUtils.toBigDecimal(vo.getTaxPrice());
		
		if (taxPrice != null && externalPrice != null) {
			BigDecimal diffPrice = taxPrice.subtract(externalPrice);
			vo.setDiffPrice(diffPrice.toString());
		} else {
			vo.setDiffPrice(NO_VALUE);
		}
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr09H entityH = new TaPaperPr09H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr09H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr09HRepository.save(entityH);
		
		List<ProductPaperInformPriceVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr09D> entityDList = new ArrayList<>();
		TaPaperPr09D entityD = null;
		int i = 1;
		for (ProductPaperInformPriceVo vo : voList) {
			entityD = new TaPaperPr09D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setInformPrice(!NO_VALUE.equals(vo.getInformPrice()) ? NumberUtils.toBigDecimal(vo.getInformPrice()) : null);
			entityD.setExternalPrice(!NO_VALUE.equals(vo.getExternalPrice()) ? NumberUtils.toBigDecimal(vo.getExternalPrice()) : null);
			entityD.setDeclarePrice(!NO_VALUE.equals(vo.getDeclarePrice()) ? NumberUtils.toBigDecimal(vo.getDeclarePrice()) : null);
			entityD.setRetailPrice(!NO_VALUE.equals(vo.getRetailPrice()) ? NumberUtils.toBigDecimal(vo.getRetailPrice()) : null);
			entityD.setTaxPrice(!NO_VALUE.equals(vo.getTaxPrice()) ? NumberUtils.toBigDecimal(vo.getTaxPrice()) : null);
			entityD.setDiffPrice(!NO_VALUE.equals(vo.getDiffPrice()) ? NumberUtils.toBigDecimal(vo.getDiffPrice()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr09DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr09HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
