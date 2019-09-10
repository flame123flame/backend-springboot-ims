package th.go.excise.ims.ta.service;

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
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr03D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr03H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr03DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr03HRepository;
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperBalanceMaterialService extends AbstractProductPaperService<ProductPaperBalanceMaterialVo, TaPaperPr03H> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperBalanceMaterialService.class);

	private static final String PRODUCT_PAPER_BALANCE_MATERIAL = "ตรวจนับวัตถุดิบคงเหลือ";;

	@Autowired
	private TaPaperPr03HRepository taPaperPr03HRepository;
	@Autowired
	private TaPaperPr03DRepository taPaperPr03DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "03";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr03HRepository;
	}

	@Override
	protected List<ProductPaperBalanceMaterialVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperBalanceMaterialVo> voList = new ArrayList<>();
		ProductPaperBalanceMaterialVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperBalanceMaterialVo();
			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
			vo.setBalanceByAccountQty(NO_VALUE);
			vo.setBalanceByStockQty(NO_VALUE);
			vo.setBalanceByCountQty(NO_VALUE);
			vo.setMaxDiffQty1(NO_VALUE);
			vo.setMaxDiffQty2(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperBalanceMaterialVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());

		List<TaPaperPr03D> entityList = taPaperPr03DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperBalanceMaterialVo> voList = new ArrayList<>();
		ProductPaperBalanceMaterialVo vo = null;
		for (TaPaperPr03D entity : entityList) {
			vo = new ProductPaperBalanceMaterialVo();
			vo.setMaterialDesc(entity.getMaterialDesc());
			vo.setBalanceByAccountQty(entity.getBalanceByAccountQty() != null ? entity.getBalanceByAccountQty().toString() : NO_VALUE);
			vo.setBalanceByStockQty(entity.getBalanceByStockQty() != null ? entity.getBalanceByStockQty().toString() : NO_VALUE);
			vo.setBalanceByCountQty(entity.getBalanceByCountQty() != null ? entity.getBalanceByCountQty().toString() : NO_VALUE);
			vo.setMaxDiffQty1(entity.getMaxDiffQty1() != null ? entity.getMaxDiffQty1().toString() : NO_VALUE);
			vo.setMaxDiffQty2(entity.getMaxDiffQty2() != null ? entity.getMaxDiffQty2().toString() : NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperBalanceMaterialVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_BALANCE_MATERIAL);
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
		String[] tbTH = { "ลำดับ", "รายการ", "ยอดคงเหลือ (ภส.๐๗-๐๑)", "คลังสินค้า", "ยอดคงเหลือจากการตรวจนับ", "ผลต่างยอดคงเหลือ", "ผลต่างคลังสินค้า" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 5) {
				cell.setCellStyle(bgKeyIn);
			} else if (i > 4) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

			cellNum++;
		}

		/* width */
		for (int i = 0; i < 7; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 100);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		for (ProductPaperBalanceMaterialVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getBalanceByAccountQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getBalanceByAccountQty()) && !NO_VALUE.equals(data.getBalanceByAccountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getBalanceByAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getBalanceByStockQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getBalanceByStockQty()) && !NO_VALUE.equals(data.getBalanceByStockQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getBalanceByStockQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getBalanceByCountQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getBalanceByCountQty()) && !NO_VALUE.equals(data.getBalanceByCountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(data.getBalanceByCountQty())));
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
	public List<ProductPaperBalanceMaterialVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperBalanceMaterialVo> voList = new ArrayList<>();
		ProductPaperBalanceMaterialVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				vo = new ProductPaperBalanceMaterialVo();
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
						// BalanceByAccountQty
						vo.setBalanceByAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// BalanceByStockQty
						vo.setBalanceByStockQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// BalanceByCountQty
						vo.setBalanceByCountQty(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ProductPaperBalanceMaterialVo vo) {
		BigDecimal balanceByAccountQty = NumberUtils.toBigDecimal(vo.getBalanceByAccountQty());
		BigDecimal balanceByStockQty = NumberUtils.toBigDecimal(vo.getBalanceByStockQty());
		BigDecimal balanceByCountQty = NumberUtils.toBigDecimal(vo.getBalanceByCountQty());
		
		if (balanceByCountQty != null && balanceByStockQty != null) {
			BigDecimal maxDiffQty1 = balanceByCountQty.subtract(balanceByStockQty);
			vo.setMaxDiffQty1(maxDiffQty1.toString());
		} else {
			vo.setMaxDiffQty1(NO_VALUE);
		}
		
		if (balanceByCountQty != null && balanceByAccountQty != null) {
			BigDecimal maxDiffQty2 = balanceByCountQty.subtract(balanceByAccountQty);
			vo.setMaxDiffQty2(maxDiffQty2.toString());
		} else {
			vo.setMaxDiffQty2(NO_VALUE);
		}
	}

	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr03H entityH = new TaPaperPr03H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr03H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr03HRepository.save(entityH);

		List<ProductPaperBalanceMaterialVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr03D> entityDList = new ArrayList<>();
		TaPaperPr03D entityD = null;
		int i = 1;
		for (ProductPaperBalanceMaterialVo vo : voList) {
			entityD = new TaPaperPr03D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setMaterialDesc(vo.getMaterialDesc());
			entityD.setBalanceByAccountQty(!NO_VALUE.equals(vo.getBalanceByAccountQty()) ? NumberUtils.toBigDecimal(vo.getBalanceByAccountQty()) : null);
			entityD.setBalanceByStockQty(!NO_VALUE.equals(vo.getBalanceByStockQty()) ? NumberUtils.toBigDecimal(vo.getBalanceByStockQty()) : null);
			entityD.setBalanceByCountQty(!NO_VALUE.equals(vo.getBalanceByCountQty()) ? NumberUtils.toBigDecimal(vo.getBalanceByCountQty()) : null);
			entityD.setMaxDiffQty1(!NO_VALUE.equals(vo.getMaxDiffQty1()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty1()) : null);
			entityD.setMaxDiffQty2(!NO_VALUE.equals(vo.getMaxDiffQty2()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty2()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr03DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr03HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
