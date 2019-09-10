package th.go.excise.ims.ta.service;

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
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv03D;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv03H;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv03DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv03HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperMemberVo;

@Service
public class ServicePaperMemberService extends AbstractServicePaperService<ServicePaperMemberVo, TaPaperSv03H> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperMemberService.class);
	
	@Autowired
	private TaPaperSv03HRepository taPaperSv03HRepository;
	@Autowired
	private TaPaperSv03DRepository taPaperSv03DRepository;

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
		return taPaperSv03HRepository;
	}
	
	@Override
	protected List<ServicePaperMemberVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperMemberVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperMemberVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv03D> entityList = taPaperSv03DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperMemberVo> voList = new ArrayList<>();
		ServicePaperMemberVo vo = null;
		for (TaPaperSv03D entity : entityList) {
			vo = new ServicePaperMemberVo();
			vo.setMemberCode(entity.getMemberCode());
			vo.setMemberFullName(entity.getMemberFullName() != null ? entity.getMemberFullName().toString() : NO_VALUE);
			vo.setMemberStartDate(entity.getMemberStartDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberStartDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberEndDate(entity.getMemberEndDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberEndDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberCoupon(entity.getMemberCoupon() != null ? entity.getMemberCoupon().toString() : NO_VALUE);
			vo.setMemberUsedDate(entity.getMemberUsedDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberUsedDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberStatus(entity.getMemberStatus() != null ? entity.getMemberStatus().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ServicePaperFormVo formVo, List<ServicePaperMemberVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบสถานะสมาชิก");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รหัสสมาชิก", "ชื่อ-สกุล", "วันเริ่มต้น", "วันหมดอายุ	", "คูปอง", "วันที่ใช้บริการ", "สถานะการเป็นสมาชิก" };
		row = sheet.createRow(rowNum);
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
//			if (cellNum > 1 && cellNum < 3) {
//				cell.setCellStyle(bgKeyIn);				
//			} else if (cellNum == 6) {
//				cell.setCellStyle(bgCal);
//			} else {
//				cell.setCellStyle(thStyle);
//			}
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperMemberVo detail : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode())) ? detail.getMemberCode() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberFullName())) ? detail.getMemberFullName() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStartDate())) ? detail.getMemberStartDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberEndDate())) ? detail.getMemberEndDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCoupon())) ? detail.getMemberCoupon() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberUsedDate())) ? detail.getMemberUsedDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStatus())) ? detail.getMemberStatus() : "");

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
	public List<ServicePaperMemberVo> uploadData(ServicePaperFormVo formVo) {
		logger.info("uploadData filename={}", formVo.getFile().getOriginalFilename());
		
		List<ServicePaperMemberVo> voList = new ArrayList<>();
		ServicePaperMemberVo vo = null;
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_DATA_INDEX);

			for (Row row : sheet) {
				vo = new ServicePaperMemberVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						vo.setMemberCode(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						vo.setMemberFullName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						vo.setMemberStartDate(ConvertDateUtils.formatDateToString(cell.getDateCellValue(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
					} else if (cell.getColumnIndex() == 4) {
						vo.setMemberEndDate(ConvertDateUtils.formatDateToString(cell.getDateCellValue(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
					} else if (cell.getColumnIndex() == 5) {
						vo.setMemberCoupon(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						vo.setMemberUsedDate(ConvertDateUtils.formatDateToString(cell.getDateCellValue(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
					} else if (cell.getColumnIndex() == 6) {
						vo.setMemberStatus(ExcelUtils.getCellValueAsString(cell));
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
	
	private void calculate(ServicePaperMemberVo vo) {
		
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ServicePaperFormVo formVo) {
		TaPaperSv03H entityH = new TaPaperSv03H();
		String paperSvNumber = prepareEntityH(formVo, entityH, TaPaperSv03H.class);
		logger.info("save paperSvNumber={}", paperSvNumber);
		taPaperSv03HRepository.save(entityH);
		
		List<ServicePaperMemberVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperSv03D> entityDList = new ArrayList<>();
		TaPaperSv03D entityD = null;
		int i = 1;
		for (ServicePaperMemberVo vo : voList) {
			entityD = new TaPaperSv03D();
			entityD.setPaperSvNumber(paperSvNumber);
			entityD.setSeqNo(i);
			entityD.setMemberCode(vo.getMemberCode());
			entityD.setMemberFullName(vo.getMemberFullName());
			entityD.setMemberStartDate(ConvertDateUtils.parseStringToLocalDate(vo.getMemberStartDate(), ConvertDateUtils.DD_MM_YYYY));
			entityD.setMemberEndDate(ConvertDateUtils.parseStringToLocalDate(vo.getMemberEndDate(), ConvertDateUtils.DD_MM_YYYY));
			entityD.setMemberCoupon(vo.getMemberCoupon());
			entityD.setMemberUsedDate(ConvertDateUtils.parseStringToLocalDate(vo.getMemberUsedDate(), ConvertDateUtils.DD_MM_YYYY));
			entityD.setMemberStatus(vo.getMemberStatus());
			entityDList.add(entityD);
			i++;
		}
		taPaperSv03DRepository.saveAll(entityDList);
		
		return paperSvNumber;
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv03HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
