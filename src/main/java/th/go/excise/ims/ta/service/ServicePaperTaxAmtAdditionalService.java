package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import th.go.excise.ims.ta.persistence.entity.TaPaperSv05D;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv05H;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv05DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv05HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperTaxAmtAdditionalVo;

@Service
public class ServicePaperTaxAmtAdditionalService extends AbstractServicePaperService<ServicePaperTaxAmtAdditionalVo, TaPaperSv05H> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperTaxAmtAdditionalService.class);
	
	@Autowired
	private TaPaperSv05HRepository taPaperSv05HRepository;
	@Autowired
	private TaPaperSv05DRepository taPaperSv05DRepository;
	
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
		return taPaperSv05HRepository;
	}
	
	@Override
	protected List<ServicePaperTaxAmtAdditionalVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperTaxAmtAdditionalVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperTaxAmtAdditionalVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv05D> entityList = taPaperSv05DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperTaxAmtAdditionalVo> voList = new ArrayList<>();
		ServicePaperTaxAmtAdditionalVo vo = null;
		for (TaPaperSv05D entity : entityList) {
			vo = new ServicePaperTaxAmtAdditionalVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setGoodsQty(entity.getGoodsQty() != null ? entity.getGoodsQty().toString() : NO_VALUE);
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setGoodsValue(entity.getGoodsValue() != null ? entity.getGoodsValue().toString() : NO_VALUE);
			vo.setTaxRateByValue(entity.getTaxRateByValue() != null ? entity.getTaxRateByValue().toString() : NO_VALUE);
			vo.setTaxRateByQty(entity.getTaxRateByQty() != null ? entity.getTaxRateByQty().toString() : NO_VALUE);
			vo.setTaxAdditionalAmt(entity.getTaxAdditionalAmt() != null ? entity.getTaxAdditionalAmt().toString() : NO_VALUE);
			vo.setPenaltyAmt(entity.getPenaltyAmt() != null ? entity.getPenaltyAmt().toString() : NO_VALUE);
			vo.setSurchargeAmt(entity.getSurchargeAmt() != null ? entity.getSurchargeAmt().toString() : NO_VALUE);
			vo.setMoiTaxAmt(entity.getMoiTaxAmt() != null ? entity.getMoiTaxAmt().toString() : NO_VALUE);
			vo.setNetTaxAmt(entity.getNetTaxAmt() != null ? entity.getNetTaxAmt().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ServicePaperFormVo formVo, List<ServicePaperTaxAmtAdditionalVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ตารางการคำนวณภาษีที่ต้องชำระเพิ่ม");
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		;

		// tbTH1
		String[] tbTH1 = { "ลำดับ", "รายการ", "ปริมาณ", "ราคาขายปลีก", "มูลค่า", "อัตราภาษี", "", "ภาษีที่ต้องชำระเพิ่มเติม", "เบี้ยปรับ", "เงินเพิ่ม", "ภาษีเพื่อราชการส่วนท้องถิ่น", "รวม" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
		}

		// tbTH2
		String[] tbTH2 = { "", "", "", "", "", "ตามมูลค่า", "ตามปริมาณ" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 2) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thStyle);
			}
		}

		// width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 32 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 6));
		//
		for (int i = 0; i < 12; i++) {
			if (i != 5 && i != 6) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		// set data
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (ServicePaperTaxAmtAdditionalVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInformPrice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsValue());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxRateByValue());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxRateByQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxAdditionalAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPenaltyAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getSurchargeAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMoiTaxAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getNetTaxAmt());
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
	public List<ServicePaperTaxAmtAdditionalVo> uploadData(ServicePaperFormVo formVo) {
		List<ServicePaperTaxAmtAdditionalVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				ServicePaperTaxAmtAdditionalVo pushdata = new ServicePaperTaxAmtAdditionalVo();
				// Skip on first row
				if (row.getRowNum() < 2) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						pushdata.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						pushdata.setGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setGoodsValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						pushdata.setTaxRateByValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setTaxRateByQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						pushdata.setTaxAdditionalAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						pushdata.setPenaltyAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						pushdata.setSurchargeAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 10) {
						pushdata.setMoiTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 11) {
						pushdata.setNetTaxAmt(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(pushdata);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ServicePaperFormVo formVo) {
		TaPaperSv05H entityH = new TaPaperSv05H();
		String paperSvNumber = prepareEntityH(formVo, entityH, TaPaperSv05H.class);
		logger.info("save paperSvNumber={}", paperSvNumber);
		taPaperSv05HRepository.save(entityH);
		
		List<ServicePaperTaxAmtAdditionalVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperSv05D> entityDList = new ArrayList<>();
		TaPaperSv05D entityD = null;
		int i = 1;
		for (ServicePaperTaxAmtAdditionalVo vo : voList) {
			entityD = new TaPaperSv05D();
			entityD.setPaperSvNumber(paperSvNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setGoodsQty(!NO_VALUE.equals(vo.getGoodsQty()) ? NumberUtils.toBigDecimal(vo.getGoodsQty()) : null);
			entityD.setInformPrice(!NO_VALUE.equals(vo.getInformPrice()) ? NumberUtils.toBigDecimal(vo.getInformPrice()) : null);
			entityD.setGoodsValue(!NO_VALUE.equals(vo.getGoodsValue()) ? NumberUtils.toBigDecimal(vo.getGoodsValue()) : null);
			entityD.setTaxRateByValue(!NO_VALUE.equals(vo.getTaxRateByValue()) ? NumberUtils.toBigDecimal(vo.getTaxRateByValue()) : null);
			entityD.setTaxRateByQty(!NO_VALUE.equals(vo.getTaxRateByQty()) ? NumberUtils.toBigDecimal(vo.getTaxRateByQty()) : null);
			entityD.setTaxAdditionalAmt(!NO_VALUE.equals(vo.getTaxAdditionalAmt()) ? NumberUtils.toBigDecimal(vo.getTaxAdditionalAmt()) : null);
			entityD.setPenaltyAmt(!NO_VALUE.equals(vo.getPenaltyAmt()) ? NumberUtils.toBigDecimal(vo.getPenaltyAmt()) : null);
			entityD.setSurchargeAmt(!NO_VALUE.equals(vo.getSurchargeAmt()) ? NumberUtils.toBigDecimal(vo.getSurchargeAmt()) : null);
			entityD.setMoiTaxAmt(!NO_VALUE.equals(vo.getMoiTaxAmt()) ? NumberUtils.toBigDecimal(vo.getMoiTaxAmt()) : null);
			entityD.setNetTaxAmt(!NO_VALUE.equals(vo.getNetTaxAmt()) ? NumberUtils.toBigDecimal(vo.getNetTaxAmt()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperSv05DRepository.saveAll(entityDList);
		
		return paperSvNumber;
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv05HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
}
