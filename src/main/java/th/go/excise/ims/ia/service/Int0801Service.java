package th.go.excise.ims.ia.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbD;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbH;
import th.go.excise.ims.ia.persistence.repository.IaAuditGftbDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditGftbHRepository;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0801SaveVo;
import th.go.excise.ims.ia.vo.Int0801Tabs;
import th.go.excise.ims.ia.vo.Int0801Vo;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgDisbRepository;

@Service
public class Int0801Service {

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaAuditGftbHRepository iaAuditGftbHRepository;

	@Autowired
	private IaAuditGftbDRepository iaAuditGftbDRepository;

	@Autowired
	private ExciseOrgDisbRepository exciseOrgDisbRepository;

	public List<Int0801Tabs> search(SearchVo request) {
		List<Int0801Tabs> tabs = new ArrayList<Int0801Tabs>();
		List<Int0801Vo> resTable = null;
		Int0801Tabs table = null;

		request.setPeriodYear(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
		for (int i = 1; i <= 5; i++) {
			resTable = new ArrayList<Int0801Vo>();
			table = new Int0801Tabs();
			request.setFlag(Integer.toString(i));
			resTable = iaGftrialBalanceRepository.findDataAccByRequest(request);

			/* set check flag */
			for (Int0801Vo int0801Vo : resTable) {
				if (IaConstants.CHART_OF_ACC_SYMBOLS.ZERO.equals(int0801Vo.getValueTrueType()) && IaConstants.CHART_OF_ACC_SYMBOLS.ZERO.equals(int0801Vo.getCarryForward().toString())) {
					int0801Vo.setCheckFlag("Y");
				} else if (IaConstants.CHART_OF_ACC_SYMBOLS.POSITIVE.equals(int0801Vo.getValueTrueType()) && int0801Vo.getCarryForward().intValue() >= 0) {
					int0801Vo.setCheckFlag("Y");
				} else if (IaConstants.CHART_OF_ACC_SYMBOLS.NEGATIVE.equals(int0801Vo.getValueTrueType()) && int0801Vo.getCarryForward().intValue() <= 0) {
					int0801Vo.setCheckFlag("Y");
				} else {
					int0801Vo.setCheckFlag("N");
				}
			}

			table.setTable(resTable);
			tabs.add(table);
		}
		return tabs;
	}

	public String save(Int0801SaveVo request) throws Exception {
		/* __________ deptDisb -> find office code !! __________ */
		ExciseOrgDisb exciseOrgDisb = exciseOrgDisbRepository.findExciseOrgGfmisByGfDisburseUnit(request.getDeptDisb());

		/* __________ generate paper No. __________ */
		String auditGftbNo = iaCommonService.autoGetRunAuditNoBySeqName("GFT", exciseOrgDisb.getOfficeCode(), "AUDIT_GFTB_NO_SEQ", 8);

		/* __________ header __________ */
		IaAuditGftbH header = new IaAuditGftbH();
		header.setDeptDisb(request.getDeptDisb());
		header.setPeriodMonth(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN)
				.concat(request.getPeriod()));
		header.setGftbConditionText(request.getGftbConditionText());
		header.setGftbCreteriaText(request.getGftbCreteriaText());
		header.setGftbFlag(request.getGftbFlag());
		header.setAuditGftbNo(auditGftbNo);
		iaAuditGftbHRepository.save(header);

		/* __________ details __________ */
		IaAuditGftbD detail = null;
		int i = 1;
		for (Int0801Tabs tab : request.getTabs()) {
			for (Int0801Vo table : tab.getTable()) {
				detail = new IaAuditGftbD();
				BeanUtils.copyProperties(detail, table);
				detail.setAuditGftbNo(auditGftbNo);
				detail.setGftbSeq(i);
				iaAuditGftbDRepository.save(detail);
			}
			i++;
		}

		return auditGftbNo;
	}

	public List<IaAuditGftbH> getauditGftbNoList() {
		return iaAuditGftbHRepository.findAllOrderByAuditGftbNo();
	}

	public Int0801SaveVo findByAuditGftbNo(String auditGftbNo) throws Exception {
		Int0801SaveVo response = new Int0801SaveVo();
		
		/* ________ header ________ */
		IaAuditGftbH resHeader = iaAuditGftbHRepository.findByAuditGftbNoAndIsDeletedOrderByAuditGftbNo(auditGftbNo, "N");
		
		/* __________ deptDisb -> find office code !! __________ */
		ExciseOrgDisb exciseOrgDisb = exciseOrgDisbRepository.findExciseOrgGfmisByGfDisburseUnit(resHeader.getDeptDisb());
		
		response.setDeptDisb(resHeader.getDeptDisb());
		response.setGfExciseName(exciseOrgDisb.getExciseName());
		response.setOfficeCode(exciseOrgDisb.getOfficeCode());
		response.setGftbConditionText(resHeader.getGftbConditionText());
		response.setGftbCreteriaText(resHeader.getGftbCreteriaText());
		response.setGftbFlag(resHeader.getGftbFlag());
		response.setPeriod(resHeader.getPeriodMonth().substring(4, 7));
		response.setPeriodYear(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(resHeader.getPeriodMonth().substring(0, 4), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN),
				ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));
		
		/* ________ details ________ */
		List<IaAuditGftbD> resDetails = iaAuditGftbDRepository.findByAuditGftbNoAndIsDeletedOrderByAccNoAscGftbSeqAsc(auditGftbNo, "N");
		List<Int0801Tabs> details = new ArrayList<Int0801Tabs>();
		Int0801Tabs tabs = null;
		List<Int0801Vo> tables = null;
		Int0801Vo table = null;
		for (int i = 1; i <= 5; i++) {
			tabs = new Int0801Tabs();
			tables = new ArrayList<Int0801Vo>();
			for (int j = 0; j < resDetails.size(); j++) {
				table = new Int0801Vo();
				if(i == resDetails.get(j).getGftbSeq()) {
					BeanUtils.copyProperties(table, resDetails.get(j));
					tables.add(table);
				}
			}
			tabs.setTable(tables);
			details.add(tabs);
		}
		response.setTabs(details);
		return response;
	}

	public ByteArrayOutputStream exportExcel(String auditGftbNo) throws Exception {
		String[] sheetName = {"หมวดทรัพย์สิน", "หมวดหนี้สิน", "หมวดส่วนทุน", "หมวดรายได้", "หมวดค่าใช้จ่าย"};
		
		/* __________ style __________ */
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle tdCenter = ExcelUtils.createCenterCellStyle(workbook);
		
		/* __________ find data to write excel __________ */
		Int0801SaveVo initData = findByAuditGftbNo(auditGftbNo);
		
		/* __________ start row tabs __________ */
		DecimalFormat DF = new DecimalFormat("#,##0.00");
		for (int i = 0; i < initData.getTabs().size(); i++) {
			Int0801Tabs tabs = initData.getTabs().get(i);
			
			/* __________ create sheet __________ */
			Sheet sheet = workbook.createSheet(sheetName[i]);
			
			/* __________ start row header __________ */
			int rowNum = 0;
			int cellNum = 2;
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			
			cell = row.createCell(cellNum);
			cell.setCellValue("หน่วยเบิกจ่าย:   ".concat(initData.getDeptDisb().concat(" - ").concat(initData.getGfExciseName())));
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, cellNum, 5));
			cellNum++;
			
			rowNum++;
			row = sheet.createRow(rowNum);
			cellNum = 2;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("ประจำงวด:   ".concat(initData.getPeriod()));
			cellNum++;
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("ประจำปี:   ".concat(initData.getPeriodYear()));
			
			/* __________ start row th table __________ */
			String[] th = { "รหัส", "ชื่อบัญชีแยกประเภท", "ยอดยกมา", "เดบิต", "เครดิต", "ยอดยกไป", "", "ผลการตรวจสอบ" };
			rowNum++;
			rowNum++;
			cellNum = 0;
			row = sheet.createRow(rowNum);
			row.setHeight((short) 500);
			for (int countH = 0; countH < th.length; countH++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(th[countH]);
//				cell.setCellStyle(TopicCenter);
				cell.setCellStyle(thStyle);
				cellNum++;
			}
			
			/* __________ start line td table __________ */
			for (int j = 0; j < tabs.getTable().size(); j++) {
				Int0801Vo table = tabs.getTable().get(j);
				
				rowNum++;
				cellNum = 0;
				row = sheet.createRow(rowNum);
				
				cell = row.createCell(cellNum);
				cell.setCellValue(table.getAccNo());
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdCenter);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(table.getAccName());
				cell.setCellStyle(tdStyle);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(DF.format(table.getBringForward()));
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(DF.format(table.getDebit()));
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(DF.format(table.getCredit()));
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(DF.format(table.getCarryForward()));
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue("Y".equals(table.getCheckFlag())? "X": "!");
				cell.setCellStyle(tdStyle);
				cell.setCellStyle(tdCenter);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(table.getGftbTestResult());
				cell.setCellStyle(tdStyle);
				cellNum++;
			}
			
			/* __________ start row conclude __________ */
			rowNum++;
			rowNum++;
			row = sheet.createRow(rowNum);
			cellNum = 0;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("สรุปผลการตรวจสอบข้อมูลทางบัญชีที่ถูกต้องตามดุลบัญชีปกติ");
			
			rowNum++;
			rowNum++;
			row = sheet.createRow(rowNum);
			cellNum = 2;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("ถูกต้อง:   ".concat("Y".equals(initData.getGftbFlag())? "X": ""));
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue("ไม่ถูกต้อง:   ".concat("N".equals(initData.getGftbFlag())? "X": ""));
			
			rowNum++;
			rowNum++;
			row = sheet.createRow(rowNum);
			cellNum = 1;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition):   ".concat(initData.getGftbConditionText()));
//			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, cellNum, 8));
			
			rowNum++;
			rowNum++;
			row = sheet.createRow(rowNum);
			cellNum = 1;
			
			cell = row.createCell(cellNum);
			cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria):   ".concat(initData.getGftbCreteriaText()));
//			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, cellNum, 8));

			/* __________  set width __________ */
			int width = 100;
			sheet.setColumnWidth(0, width * 60);
			sheet.setColumnWidth(1, width * 60);
			sheet.setColumnWidth(7, width * 70);
			for (int k = 2; k <= 5; k++) {
				sheet.setColumnWidth(k, width * 40);
			}
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		return outByteStream;
	}
}
