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
import th.go.excise.ims.ta.persistence.entity.TaPaperSv01D;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv01H;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv01DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv01HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ServicePaperQtyService extends AbstractServicePaperService<ServicePaperQtyVo, TaPaperSv01H> {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperQtyService.class);
	
	@Autowired
	private TaPaperSv01HRepository taPaperSv01HRepository;
	@Autowired
	private TaPaperSv01DRepository taPaperSv01DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	
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
		return taPaperSv01HRepository;
	}
	
	@Override
	protected List<ServicePaperQtyVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<ServicePaperQtyVo> voList = new ArrayList<>();
		ServicePaperQtyVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new ServicePaperQtyVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setServiceDocNoQty(NO_VALUE);
			vo.setIncomeDailyAccountQty(NO_VALUE);
			vo.setPaymentDocNoQty(NO_VALUE);
			vo.setAuditQty("");
			vo.setGoodsQty(anafri0001Vo.getProductQty().toString());
			vo.setDiffQty("");
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<ServicePaperQtyVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv01D> entityList = taPaperSv01DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperQtyVo> voList = new ArrayList<>();
		ServicePaperQtyVo vo = null;
		for (TaPaperSv01D entity : entityList) {
			vo = new ServicePaperQtyVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setServiceDocNoQty(entity.getServiceDocNoQty() != null ? entity.getServiceDocNoQty().toString() : NO_VALUE);
			vo.setIncomeDailyAccountQty(entity.getIncomeDailyAccountQty() != null ? entity.getIncomeDailyAccountQty().toString() : NO_VALUE);
			vo.setPaymentDocNoQty(entity.getPaymentDocNoQty() != null ? entity.getPaymentDocNoQty().toString() : NO_VALUE);
			vo.setAuditQty(entity.getAuditQty() != null ? entity.getAuditQty().toString() : NO_VALUE);
			vo.setGoodsQty(entity.getGoodsQty() != null ? entity.getGoodsQty().toString() : NO_VALUE);
			vo.setDiffQty(entity.getDiffQty() != null ? entity.getDiffQty().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ServicePaperFormVo formVo, List<ServicePaperQtyVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("กระดาษทำการตรวจสอบด้านปริมาณ");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรอบบริการ", "บัญชีประจำวัน " + "\n" + "แสดงรายรับของสถานบริการ " + "\n" + "(ภส.๐๗-๐๕)", "ใบนำส่งเงิน", "จากการตรวจสอบ", "แบบรายการภาษี (ภส.๐๓-๐๘)", "ผลต่าง" };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			if (cellNum > 1 && cellNum < 5) {
				cell.setCellStyle(bgKeyIn);				
			} else if (cellNum == 7) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		};

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);

		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperQtyVo detail : voList) {
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDocNoQty())) ? detail.getServiceDocNoQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getIncomeDailyAccountQty())) ? detail.getIncomeDailyAccountQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getPaymentDocNoQty())) ? detail.getPaymentDocNoQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditQty())) ? detail.getAuditQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsQty())) ? detail.getGoodsQty() : "");
			
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getDiffQty())) ? detail.getDiffQty() : "");

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
	public List<ServicePaperQtyVo> uploadData(ServicePaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());
		
		List<ServicePaperQtyVo> voList = new ArrayList<>();
		ServicePaperQtyVo vo = new ServicePaperQtyVo();
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			
			// ###### find data for calcualte in excel
			LocalDate localDateStart = toLocalDate(formVo.getStartDate());
			LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
			String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
			String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
			
			List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
			for (Row row : sheet) {
				vo = new ServicePaperQtyVo();
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
						vo.setServiceDocNoQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						vo.setIncomeDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						vo.setPaymentDocNoQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
					} else if (cell.getColumnIndex() == 6) {
						vo.setGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
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
	
	private void calculate(ServicePaperQtyVo vo) {
		List<BigDecimal> bigDecimalList = new ArrayList<>();
		
		if (StringUtils.isNotBlank(vo.getServiceDocNoQty()) && !NO_VALUE.equals(vo.getServiceDocNoQty())) {
			BigDecimal serviceDocNoQty = NumberUtils.toBigDecimal(vo.getServiceDocNoQty());
			bigDecimalList.add(serviceDocNoQty);
		}
		
		if (StringUtils.isNotBlank(vo.getIncomeDailyAccountQty()) && !NO_VALUE.equals(vo.getIncomeDailyAccountQty())) {
			BigDecimal incomeDailyAccountQty = NumberUtils.toBigDecimal(vo.getIncomeDailyAccountQty());
			bigDecimalList.add(incomeDailyAccountQty);
		}
		
		if (StringUtils.isNotBlank(vo.getPaymentDocNoQty()) && !NO_VALUE.equals(vo.getPaymentDocNoQty())) {
			BigDecimal paymentDocNoQty = NumberUtils.toBigDecimal(vo.getPaymentDocNoQty());
			bigDecimalList.add(paymentDocNoQty);
		}
		
		BigDecimal auditQty = null;
		if (!bigDecimalList.isEmpty()) {
			auditQty = NumberUtils.max(bigDecimalList);
			vo.setAuditQty(auditQty.toString());
		} else {
			vo.setAuditQty(NO_VALUE);
		}
		
		BigDecimal goodsQty = NumberUtils.toBigDecimal(vo.getGoodsQty());
		if (goodsQty != null && auditQty != null) {
			BigDecimal diffQty = goodsQty.subtract(auditQty);
			vo.setDiffQty(diffQty.toString());
		}
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ServicePaperFormVo formVo) {
		TaPaperSv01H entityH = new TaPaperSv01H();
		String paperSvNumber = prepareEntityH(formVo, entityH, TaPaperSv01H.class);
		logger.info("save paperSvNumber={}", paperSvNumber);
		taPaperSv01HRepository.save(entityH);
		
		List<ServicePaperQtyVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperSv01D> entityDList = new ArrayList<>();
		TaPaperSv01D entityD = null;
		int i = 1;
		for (ServicePaperQtyVo vo : voList) {
			entityD = new TaPaperSv01D();
			entityD.setPaperSvNumber(paperSvNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setServiceDocNoQty(!NO_VALUE.equals(vo.getServiceDocNoQty()) ? NumberUtils.toBigDecimal(vo.getServiceDocNoQty()) : null);
			entityD.setIncomeDailyAccountQty(!NO_VALUE.equals(vo.getIncomeDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getIncomeDailyAccountQty()) : null);
			entityD.setPaymentDocNoQty(!NO_VALUE.equals(vo.getPaymentDocNoQty()) ? NumberUtils.toBigDecimal(vo.getPaymentDocNoQty()) : null);
			entityD.setAuditQty(!NO_VALUE.equals(vo.getAuditQty()) ? NumberUtils.toBigDecimal(vo.getAuditQty()) : null);
			entityD.setGoodsQty(!NO_VALUE.equals(vo.getGoodsQty()) ? NumberUtils.toBigDecimal(vo.getGoodsQty()) : null);
			entityD.setDiffQty(!NO_VALUE.equals(vo.getDiffQty()) ? NumberUtils.toBigDecimal(vo.getDiffQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperSv01DRepository.saveAll(entityDList);
		
		return paperSvNumber;
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv01HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}