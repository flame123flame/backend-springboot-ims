package th.go.excise.ims.ia.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfd;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfh;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncGfdRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncGfhRepository;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int0610HeaderVo;
import th.go.excise.ims.ia.vo.Int0610SaveVo;
import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610SumVo;
import th.go.excise.ims.ia.vo.Int0610TabVo;
import th.go.excise.ims.ia.vo.Int0610Vo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8020IncRepository;

@Service
public class Int0610Service {

	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;

	@Autowired
	private ExciseOrgGfmisService exciseOrgGfmisService;

	@Autowired
	private IaAuditIncGfhRepository iaAuditIncGfhRepository;

	@Autowired
	private IaAuditIncGfdRepository iaAuditIncGfdRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;

	public List<Int0610Vo> getTabs(Int0610SearchVo request) throws Exception {
		List<Int0610Vo> response = new ArrayList<Int0610Vo>();

		/* _________ set request _________ */
		String periodMonthFromStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodFrom().substring(0, 2)) - 1];
		String periodMonthToStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodTo().substring(0, 2)) - 1];
		String yearFromTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodFrom(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY,
				ConvertDateUtils.LOCAL_TH);
		String yearToTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodTo(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH);

		request.setPeriodFromStr(ExciseUtils.monthYearStrOfPeriod(request.getPeriodFrom().split("/")[0], request.getPeriodFrom().split("/")[1]));
		request.setPeriodToStr(ExciseUtils.monthYearStrOfPeriod(request.getPeriodTo().split("/")[0], request.getPeriodTo().split("/")[1]));
		request.setFromDateStr(ExciseUtils.firstDateOfPeriod(periodMonthFromStr, yearFromTH));
		request.setToDateStr(ExciseUtils.lastDateOfPeriod(periodMonthToStr, yearToTH, false));

		/* _________ find amount tabs _________ */
		List<WsIncfri8020Inc> tabsAmount = wsIncfri8020IncRepository.findTabs(request.getOfficeCode());

		List<Int0610TabVo> dataList = null;
		Int0610Vo tab = null;
		Int0610TabVo data = null;
		List<Int0610SumVo> summary = null;
		List<Int0610SumVo> dataFilter = null;
		for (WsIncfri8020Inc t : tabsAmount) {
			tab = new Int0610Vo();
			dataList = new ArrayList<Int0610TabVo>();
			summary = new ArrayList<Int0610SumVo>();

			request.setDeptDisb(t.getDeptDisb());
			summary = wsIncfri8020IncRepository.summaryByDisburseUnit(request);

			if (summary.size() > 0) {
				/* _______ loop for find key _______ */
				for (Int0610SumVo s : summary) {
					data = new Int0610TabVo();
					BigDecimal sumCarryForward = BigDecimal.ZERO;
					BigDecimal sumNetTaxAmt = BigDecimal.ZERO;

					/* _______ find data key == accNo. _______ */
					dataFilter = summary.stream().filter(f -> s.getIncomeCode().equals(f.getIncomeCode())).collect(Collectors.toList());

					if (dataFilter.size() > 0) {
						for (Int0610SumVo int0610SumVo : dataFilter) {
							/* _______ sum carry forward _______ */
							sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(int0610SumVo.getCarryForward()));
							sumNetTaxAmt = sumNetTaxAmt.add(NumberUtils.nullToZero(int0610SumVo.getNetTaxAmt()));
						}
						data.setSummary(dataFilter);
						data.setAccNo(s.getAccNo());
						data.setAccName(dataFilter.get(0).getAccName());
						data.setCarryForward(sumCarryForward);
						data.setDifference(sumNetTaxAmt.subtract(sumCarryForward));
					}
					dataList.add(data);
				}
				tab.setTab(dataList);
				tab.setExciseOrgDisb(exciseOrgGfmisService.findExciseOrgGfmisByGfDisburseUnit(t.getDeptDisb()));
				tab.setOfficeCode(request.getOfficeCode());
				tab.setIncMonthFrom(request.getPeriodFromStr().substring(4, 7));
				tab.setIncMonthTo(request.getPeriodToStr().substring(4, 7));
				tab.setIncYearFrom(request.getPeriodFromStr().substring(0, 4));
				tab.setIncYearTo(request.getPeriodToStr().substring(0, 4));
				response.add(tab);
			}
		}

		return response;
	}

	public void save(Int0610SaveVo request) {
		/* ________ generate auditIncGfNo ________ */
		String auditIncGfNo = iaCommonService.autoGetRunAuditNoBySeqName("AIG", request.getHeader().getOfficeCode(), "AUDIT_INC_GF_NO_SEQ", 8);
		request.getHeader().setAuditIncGfNo(auditIncGfNo);
		/* ________ save header ________ */
		iaAuditIncGfhRepository.save(request.getHeader());
		for (IaAuditIncGfd entity : request.getDetails()) {
			entity.setAuditIncGfNo(auditIncGfNo);
		}
		/* ________ save details ________ */
		iaAuditIncGfdRepository.saveAll(request.getDetails());
	}

	public List<IaAuditIncGfh> getAuditIncGfNoDropdown() {
		return iaAuditIncGfhRepository.findAuditIncGfNoOrderByAuditIncGfNo();
	}

	public Int0610HeaderVo findByAuditIncGfNo(String auditIncGfNo) {
		List<Int0610Vo> tabList = new ArrayList<Int0610Vo>();
		Int0610Vo tab = null;
		List<Int0610TabVo> dataList = null;
		Int0610TabVo data = null;
		List<Int0610SumVo> summaryList = null;
		Int0610SumVo summary = null;

		IaAuditIncGfh header = iaAuditIncGfhRepository.findByAuditIncGfNoAndIsDeleted(auditIncGfNo, "N");
		List<IaAuditIncGfd> details = iaAuditIncGfdRepository.findByAuditIncGfNoAndIsDeleted(auditIncGfNo, "N");

		for (IaAuditIncGfd d : details) {
			summaryList = new ArrayList<>();
			List<IaAuditIncGfd> dataFilter = details.stream().filter(f -> d.getDisburseUnit().equals(f.getDisburseUnit())).collect(Collectors.toList());
			for (IaAuditIncGfd iaAuditIncGfd : dataFilter) {
				summary = new Int0610SumVo();
				summary.setIncomeCode(iaAuditIncGfd.getIncomeCode());
				summary.setIncomeName(wsIncfri8020IncRepository.findIncomeName(iaAuditIncGfd.getIncomeCode()));
				summary.setNetTaxAmt(iaAuditIncGfd.getIncNetTaxAmt());
				summaryList.add(summary);
			}

			data = new Int0610TabVo();
			if (StringUtils.isNotBlank(d.getGlAccNo())) {
				data.setAccNo(d.getGlAccNo());
				data.setAccName(iaGftrialBalanceRepository.findAccName(d.getGlAccNo()));
			}
			data.setCarryForward(d.getGlNetTaxAmt());
			data.setDifference(summary.getNetTaxAmt().subtract(data.getCarryForward()));
			data.setSummary(summaryList);

			dataList = new ArrayList<>();
			dataList.add(data);

			tab = new Int0610Vo();
			tab.setTab(dataList);
			tab.setExciseOrgDisb(exciseOrgGfmisService.findExciseOrgGfmisByGfDisburseUnit(d.getDisburseUnit()));
			tab.setOfficeCode(header.getOfficeCode());
			tabList.add(tab);
		}

		Int0610HeaderVo response = new Int0610HeaderVo();
		response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(header.getOfficeCode()));
		response.setAuditIncGfNo(auditIncGfNo);
		response.setAuditFlag(header.getAuditFlag());
		response.setIncgfConditionText(header.getIncgfConditionText());
		response.setIncgfCreteriaText(header.getIncgfCreteriaText());
		response.setMonthPeriodFrom(strEnToStrTh(ExciseUtils.firstDateOfPeriod(header.getIncMonthFrom(), header.getIncYearTo())));
		response.setMonthPeriodTo(strEnToStrTh(ExciseUtils.lastDateOfPeriod(header.getIncMonthTo(), header.getIncYearTo(), true)));
		response.setDataList(tabList);

		return response;
	}
	
	private static String strEnToStrTh(String dateStrEN) {
		return ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(dateStrEN, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
	}

	public ByteArrayOutputStream export(String officeCode, String periodFrom, String periodTo) throws Exception {
		/* find data to write excel */
		Int0610SearchVo requestData = new Int0610SearchVo();
		requestData.setOfficeCode(officeCode);
		requestData.setPeriodFrom(periodFrom.replace("-", "/"));
		requestData.setPeriodTo(periodTo.replace("-", "/"));
		List<Int0610Vo> resData = getTabs(requestData);
		
		/* style */
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle TopicCenterlite = ExcelUtils.createTopicCenterliteStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle TopicRight = ExcelUtils.createTopicRightStyle(workbook);
		CellStyle TopicCenter = ExcelUtils.createTopicCenterStyle(workbook);
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);

		CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		cellHeaderStyle.setFont(headerFont);

		tdRight.setFont(headerFont);
		tdLeft.setFont(headerFont);

		String[] header = { "รหัสภาษี", "รายการภาษี", "จำนวนเงิน(ระบบรายได้)", "รหัส GL", "ชื่อรายได้", "จำนวนเงิน", "ผลต่าง" };
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		
		rowNum = 0;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(header[i]);
			cell.setCellStyle(TopicCenter);
			cell.setCellStyle(cellHeaderStyle);
			cellNum++;
		}
		
		for (Int0610Vo tabs : resData) {
			for (Int0610TabVo t : tabs.getTab()) {
				rowNum++;
				cellNum = 0;
				row = sheet.createRow(rowNum);
				for (Int0610SumVo s : t.getSummary()) {
					cell = row.createCell(cellNum);
					cell.setCellValue(s.getIncomeCode());
					cellNum++;
					
					cell = row.createCell(cellNum);
					cell.setCellValue(s.getAccName());
					cellNum++;
					
					cell = row.createCell(cellNum);
					cell.setCellValue(s.getNetTaxAmt().doubleValue());
					cellNum++;
					
					cell = row.createCell(cellNum);
					cell.setCellValue(t.getAccNo());
					cellNum++;
					
					cell = row.createCell(cellNum);
					cell.setCellValue(t.getAccName());
					cellNum++;
					
					cell = row.createCell(cellNum);
					cell.setCellValue(t.getCarryForward().doubleValue());
					cellNum++;
				}
			}
		}

		// set width
		int width = 76;
		sheet.setColumnWidth(1, width * 40);
		sheet.setColumnWidth(2, width * 145);
		for (int i = 3; i < 8; i++) {
			// if (i == 21) {
			// continue;
			// }
			sheet.setColumnWidth(i, width * 50);
		}
		
		Sheet sheet2 = workbook.createSheet();

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		return outByteStream;
	}
	
	public static void main(String[] args) {
		System.out.println(strEnToStrTh(ExciseUtils.firstDateOfPeriod("004", "2019")));
//		System.out.println(ExciseUtils.firstDateOfPeriod("012", "2562"));
	}

}
