package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
import th.go.excise.ims.ta.persistence.entity.TaPaperSv02D;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv02H;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv02DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv02HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperPricePerUnitVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ServicePaperPricePerUnitService extends AbstractServicePaperService<ServicePaperPricePerUnitVo, TaPaperSv02H> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperPricePerUnitService.class);
	
	@Autowired
	private TaPaperSv02HRepository taPaperSv02HRepository;
	@Autowired
	private TaPaperSv02DRepository taPaperSv02DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "02";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperSv02HRepository;
	}
	
	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		ServicePaperPricePerUnitVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new ServicePaperPricePerUnitVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setInvoicePrice(NO_VALUE);
			vo.setInformPrice("");
			vo.setAuditPrice("");
			vo.setGoodsPrice(anafri0001Vo.getProductPrice().toString());
			vo.setDiffPrice("");
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv02D> entityList = taPaperSv02DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		ServicePaperPricePerUnitVo vo = null;
		for (TaPaperSv02D entity : entityList) {
			vo = new ServicePaperPricePerUnitVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setInvoicePrice(entity.getInvoicePrice() != null ? entity.getInvoicePrice().toString() : NO_VALUE);
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setAuditPrice(entity.getAuditPrice() != null ? entity.getAuditPrice().toString() : NO_VALUE);
			vo.setGoodsPrice(entity.getGoodsPrice() != null ? entity.getGoodsPrice().toString() : NO_VALUE);
			vo.setDiffPrice(entity.getDiffPrice() != null ? entity.getDiffPrice().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ServicePaperFormVo formVo, List<ServicePaperPricePerUnitVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ราคาตามใบกำกับภาษี", "ราคาบริการตามแบบแจ้ง", "จากการตรวจสอบ", "ราคาที่ยื่นชำระภาษี", "ผลต่าง" };
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		row = sheet.createRow(rowNum);
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			if (cellNum > 1 && cellNum < 3) {
				cell.setCellStyle(bgKeyIn);				
			} else if (cellNum == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperPricePerUnitVo detail : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInvoicePrice())) ? detail.getInvoicePrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInformPrice())) ? detail.getInformPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditPrice())) ? detail.getAuditPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsPrice())) ? detail.getGoodsPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getDiffPrice())) ? detail.getDiffPrice() : "");
			
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
	public List<ServicePaperPricePerUnitVo> uploadData(ServicePaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		ServicePaperPricePerUnitVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				vo = new ServicePaperPricePerUnitVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						vo.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						vo.setInvoicePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						vo.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						vo.setAuditPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						vo.setGoodsPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
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
	
	private void calculate(ServicePaperPricePerUnitVo vo) {
		List<BigDecimal> bigDecimalList = new ArrayList<>();
		
		if (StringUtils.isNotBlank(vo.getInvoicePrice()) && !NO_VALUE.equals(vo.getInvoicePrice())) {
			BigDecimal invoicePrice = NumberUtils.toBigDecimal(vo.getInvoicePrice());
			bigDecimalList.add(invoicePrice);
		}
		
		if (StringUtils.isNotBlank(vo.getInformPrice()) && !NO_VALUE.equals(vo.getInformPrice())) {
			BigDecimal informPrice = NumberUtils.toBigDecimal(vo.getInformPrice());
			bigDecimalList.add(informPrice);
		}
		
		BigDecimal auditPrice = null;
		if (!bigDecimalList.isEmpty()) {
			auditPrice = NumberUtils.max(bigDecimalList);
			vo.setAuditPrice(auditPrice.toString());
		} else {
			vo.setAuditPrice(NO_VALUE);
		}
		
		BigDecimal goodsPrice = NumberUtils.toBigDecimal(vo.getGoodsPrice());
		if (goodsPrice != null && auditPrice != null) {
			BigDecimal diffPrice = goodsPrice.subtract(auditPrice);
			vo.setDiffPrice(diffPrice.toString());
		}
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ServicePaperFormVo formVo) {
		TaPaperSv02H entityH = new TaPaperSv02H();
		String paperSvNumber = prepareEntityH(formVo, entityH, TaPaperSv02H.class);
		logger.info("save paperSvNumber={}", paperSvNumber);
		taPaperSv02HRepository.save(entityH);
		
		List<ServicePaperPricePerUnitVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperSv02D> entityDList = new ArrayList<>();
		TaPaperSv02D entityD = null;
		int i = 1;
		for (ServicePaperPricePerUnitVo vo : voList) {
			entityD = new TaPaperSv02D();
			entityD.setPaperSvNumber(paperSvNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setInvoicePrice(!NO_VALUE.equals(vo.getInvoicePrice()) ? NumberUtils.toBigDecimal(vo.getInvoicePrice()) : null);
			entityD.setInformPrice(!NO_VALUE.equals(vo.getInformPrice()) ? NumberUtils.toBigDecimal(vo.getInformPrice()) : null);
			entityD.setAuditPrice(!NO_VALUE.equals(vo.getAuditPrice()) ? NumberUtils.toBigDecimal(vo.getAuditPrice()) : null);
			entityD.setGoodsPrice(!NO_VALUE.equals(vo.getGoodsPrice()) ? NumberUtils.toBigDecimal(vo.getGoodsPrice()) : null);
			entityD.setDiffPrice(!NO_VALUE.equals(vo.getDiffPrice()) ? NumberUtils.toBigDecimal(vo.getDiffPrice()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperSv02DRepository.saveAll(entityDList);
		
		return paperSvNumber;
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv02HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
}
