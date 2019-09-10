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
import th.go.excise.ims.ta.persistence.entity.TaPaperSv04D;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv04H;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv04DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv04HRepository;
import th.go.excise.ims.ta.vo.ServicePaperBalanceGoodsVo;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;

@Service
public class ServicePaperBalanceGoodsService extends AbstractServicePaperService<ServicePaperBalanceGoodsVo, TaPaperSv04H> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperBalanceGoodsService.class);
	
	@Autowired
	private TaPaperSv04HRepository taPaperSv04HRepository;
	@Autowired
	private TaPaperSv04DRepository taPaperSv04DRepository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "04";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperSv04HRepository;
	}
	
	@Override
	protected List<ServicePaperBalanceGoodsVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperBalanceGoodsVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperBalanceGoodsVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv04D> entityList = taPaperSv04DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperBalanceGoodsVo> voList = new ArrayList<>();
		ServicePaperBalanceGoodsVo vo = null;
		for (TaPaperSv04D entity : entityList) {
			vo = new ServicePaperBalanceGoodsVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setBalanceGoodsQty(entity.getBalanceGoodsQty() != null ? entity.getBalanceGoodsQty().toString() : NO_VALUE);
			vo.setAuditBalanceGoodsQty(entity.getAuditBalanceGoodsQty() != null ? entity.getAuditBalanceGoodsQty().toString() : NO_VALUE);
			vo.setDiffBalanceGoodsQty(entity.getDiffBalanceGoodsQty() != null ? entity.getDiffBalanceGoodsQty().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ServicePaperFormVo formVo, List<ServicePaperBalanceGoodsVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจนับสินค้าคงเหลือ");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ยอดคงเหลือตามบัญชี", "ยอดสินค้าคงเหลือจากการตรวจนับ","ผลต่าง" };
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 35 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 35 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		row = sheet.createRow(rowNum);
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			if (cellNum > 0 && cellNum < 4) {
				cell.setCellStyle(bgKeyIn);				
			} else if (cellNum == 4) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperBalanceGoodsVo detail : voList) {
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getBalanceGoodsQty())) ? detail.getBalanceGoodsQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditBalanceGoodsQty())) ? detail.getAuditBalanceGoodsQty() : "");
			
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getDiffBalanceGoodsQty())) ? detail.getDiffBalanceGoodsQty() : "");

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
	public List<ServicePaperBalanceGoodsVo> uploadData(ServicePaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());

		List<ServicePaperBalanceGoodsVo> voList = new ArrayList<>();
		ServicePaperBalanceGoodsVo vo = new ServicePaperBalanceGoodsVo();
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);
			
			for (Row row : sheet) {
				vo = new ServicePaperBalanceGoodsVo();
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
						vo.setBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						vo.setAuditBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						vo.setDiffBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ServicePaperBalanceGoodsVo vo) {
		BigDecimal auditBalanceGoodsQty = NumberUtils.toBigDecimal(vo.getAuditBalanceGoodsQty());
		BigDecimal balanceGoodsQty = NumberUtils.toBigDecimal(vo.getBalanceGoodsQty());
		if (auditBalanceGoodsQty != null && balanceGoodsQty != null) {
			BigDecimal diffBalanceGoodsQty = auditBalanceGoodsQty.subtract(balanceGoodsQty);
			vo.setDiffBalanceGoodsQty(diffBalanceGoodsQty.toString());
		}
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ServicePaperFormVo formVo) {
		TaPaperSv04H entityH = new TaPaperSv04H();
		String paperSvNumber = prepareEntityH(formVo, entityH, TaPaperSv04H.class);
		logger.info("save paperSvNumber={}", paperSvNumber);
		taPaperSv04HRepository.save(entityH);
		
		List<ServicePaperBalanceGoodsVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperSv04D> entityDList = new ArrayList<>();
		TaPaperSv04D entityD = null;
		int i = 1;
		for (ServicePaperBalanceGoodsVo vo : voList) {
			entityD = new TaPaperSv04D();
			entityD.setPaperSvNumber(paperSvNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setBalanceGoodsQty(!NO_VALUE.equals(vo.getBalanceGoodsQty()) ? NumberUtils.toBigDecimal(vo.getBalanceGoodsQty()) : null);
			entityD.setAuditBalanceGoodsQty(!NO_VALUE.equals(vo.getAuditBalanceGoodsQty()) ? NumberUtils.toBigDecimal(vo.getAuditBalanceGoodsQty()) : null);
			entityD.setDiffBalanceGoodsQty(!NO_VALUE.equals(vo.getDiffBalanceGoodsQty()) ? NumberUtils.toBigDecimal(vo.getDiffBalanceGoodsQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperSv04DRepository.saveAll(entityDList);
		
		return paperSvNumber;
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv04HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
