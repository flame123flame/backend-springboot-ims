package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpD1;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;
import th.go.excise.ims.ia.persistence.repository.IaEstimateExpD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaEstimateExpHRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaEstimateD1VoType;
import th.go.excise.ims.ia.vo.IaEstimateExpD1Vo;
import th.go.excise.ims.ia.vo.IaEstimateExpHVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501SaveVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;

@Service
public class Int0501Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0501Service.class);

	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;

	@Autowired
	private IaEstimateExpHRepository iaEstimateExpHRepository;

	@Autowired
	private IaEstimateExpD1Repository iaEstimateExpD1Repository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private ExcelUtil excelUtil;

	public List<Int0501Vo> listPerson(Int0501FormVo form) {
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		personList = excisePersonJdbcRepository.listPerson(form);
		return personList;
	}

	public IaEstimateExpHVo saveIaEstimateExp(Int0501SaveVo vo) {
		IaEstimateExpH estimateExpH = null;
		try {
			if (StringUtils.isNotBlank(vo.getIaEstimateExpHVo().getEstExpNo())) {
				estimateExpH = new IaEstimateExpH();
				estimateExpH = iaEstimateExpHRepository.findByEstExpNo(vo.getIaEstimateExpHVo().getEstExpNo());
				estimateExpH.setPersonResp(vo.getIaEstimateExpHVo().getPersonResp());
				estimateExpH.setRespDeptCode(vo.getIaEstimateExpHVo().getRespDeptCode());
				Date expReqDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getExpReqDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setExpReqDate(expReqDate);
				estimateExpH.setDestinationPlace(vo.getIaEstimateExpHVo().getDestinationPlace());
				Date workStDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkStDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkStDate(workStDate);
				Date workFhDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkFhDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkFhDate(workFhDate);
				estimateExpH = iaEstimateExpHRepository.save(estimateExpH);
				vo.getIaEstimateExpHVo().setEstExpNo(estimateExpH.getEstExpNo());
			} else {
				estimateExpH = new IaEstimateExpH();
				estimateExpH.setEstExpNo(iaCommonService.autoGetRunAuditNoBySeqName("EST",
						vo.getIaEstimateExpHVo().getDestinationPlace(), "ESTIMATE_EXP_NO_SEQ", 8));
				estimateExpH.setPersonResp(vo.getIaEstimateExpHVo().getPersonResp());
				estimateExpH.setRespDeptCode(vo.getIaEstimateExpHVo().getRespDeptCode());
				Date expReqDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getExpReqDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setExpReqDate(expReqDate);
				estimateExpH.setDestinationPlace(vo.getIaEstimateExpHVo().getDestinationPlace());
				Date workStDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkStDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkStDate(workStDate);
				Date workFhDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkFhDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkFhDate(workFhDate);
				estimateExpH = iaEstimateExpHRepository.save(estimateExpH);
				vo.getIaEstimateExpHVo().setEstExpNo(estimateExpH.getEstExpNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// D1
		if (vo.getIaEstimateExpD1Vo() != null && vo.getIaEstimateExpD1Vo().size() > 0) {
			IaEstimateExpD1 val1 = null;
			List<IaEstimateExpD1> iaEstimateExpD1List = new ArrayList<>();
			for (IaEstimateExpD1Vo data1 : vo.getIaEstimateExpD1Vo()) {
				val1 = new IaEstimateExpD1();
				if (data1.getEstimateExpD1Id() != null) {
					val1 = iaEstimateExpD1Repository.findById(data1.getEstimateExpD1Id()).get();
					try {
						val1.setSeqNo(data1.getSeqNo());
						val1.setPersonTeamCode(data1.getPersonTeamCode());
						val1.setPosition(data1.getPosition());
						BigDecimal allowancesDay = new BigDecimal(data1.getAllowancesDay());
						val1.setAllowancesDay(allowancesDay);
						BigDecimal allowancesHalfDay = new BigDecimal(data1.getAllowancesHalfDay());
						val1.setAllowancesHalfDay(allowancesHalfDay);
						BigDecimal accomFeePackages = new BigDecimal(data1.getAccomFeePackages());
						val1.setAccomFeePackages(accomFeePackages);
						BigDecimal accomFeePackagesDat = new BigDecimal(data1.getAccomFeePackagesDat());
						val1.setAccomFeePackagesDat(accomFeePackagesDat);
						BigDecimal tranCost = new BigDecimal(data1.getTranCost());
						val1.setTranCost(tranCost);
						BigDecimal otherExpenses = new BigDecimal(data1.getOtherExpenses());
						val1.setOtherExpenses(otherExpenses);
						BigDecimal sumAmt = new BigDecimal(data1.getSumAmt());
						val1.setSumAmt(sumAmt);
						BigDecimal sumAllowances = allowancesDay.multiply(allowancesHalfDay);
						val1.setSumAllowances(sumAllowances);
						BigDecimal sumAccom = accomFeePackages.multiply(accomFeePackagesDat);
						val1.setSumAccom(sumAccom);
						val1.setRemark(data1.getRemark());
						val1.setFlagNotWithdrawing(data1.getFlagNotWithdrawing());
						val1 = iaEstimateExpD1Repository.save(val1);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				} else {
					try {
						val1.setEstExpNo(estimateExpH.getEstExpNo());
						val1.setSeqNo(data1.getSeqNo());
						val1.setPersonTeamCode(data1.getPersonTeamCode());
						val1.setPosition(data1.getPosition());
						BigDecimal allowancesDay = new BigDecimal(data1.getAllowancesDay());
						val1.setAllowancesDay(allowancesDay);
						BigDecimal allowancesHalfDay = new BigDecimal(data1.getAllowancesHalfDay());
						val1.setAllowancesHalfDay(allowancesHalfDay);
						BigDecimal accomFeePackages = new BigDecimal(data1.getAccomFeePackages());
						val1.setAccomFeePackages(accomFeePackages);
						BigDecimal accomFeePackagesDat = new BigDecimal(data1.getAccomFeePackagesDat());
						val1.setAccomFeePackagesDat(accomFeePackagesDat);
						BigDecimal tranCost = new BigDecimal(data1.getTranCost());
						val1.setTranCost(tranCost);
						BigDecimal otherExpenses = new BigDecimal(data1.getOtherExpenses());
						val1.setOtherExpenses(otherExpenses);
						BigDecimal sumAmt = new BigDecimal(data1.getSumAmt());
						val1.setSumAmt(sumAmt);
						BigDecimal sumAllowances = allowancesDay.multiply(allowancesHalfDay);
						val1.setSumAllowances(sumAllowances);
						BigDecimal sumAccom = accomFeePackages.multiply(accomFeePackagesDat);
						val1.setSumAccom(sumAccom);
						val1.setRemark(data1.getRemark());
						val1.setFlagNotWithdrawing(data1.getFlagNotWithdrawing());
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}

					iaEstimateExpD1List.add(val1);
				}
			}
			iaEstimateExpD1Repository.saveAll(iaEstimateExpD1List);
		}
		return vo.getIaEstimateExpHVo();
	}

	public List<IaEstimateExpH> getDropdownEstimateNo() {
		return iaEstimateExpHRepository.getEstimateNoList();
	}

	public List<IaEstimateD1VoType> findIaEstimateD1ByestExpNo(String estExpNo) throws Exception {
		List<IaEstimateD1VoType> iaEstimateExpD1List = new ArrayList<>();
		IaEstimateD1VoType iaEstimateExpD1Vo = null;
		List<IaEstimateExpD1> iaEstimateExpDList = iaEstimateExpD1Repository.findIaEstimateD1ByestExpNo(estExpNo);
		for (IaEstimateExpD1 iaEstimateExpD1 : iaEstimateExpDList) {
			iaEstimateExpD1Vo = new IaEstimateD1VoType();
			iaEstimateExpD1Vo.setEstimateExpD1Id(iaEstimateExpD1.getEstimateExpD1Id());
			iaEstimateExpD1Vo.setEstExpNo(iaEstimateExpD1.getEstExpNo());
			iaEstimateExpD1Vo.setSeqNo(iaEstimateExpD1.getSeqNo());
			iaEstimateExpD1Vo.setPersonTeamCode(iaEstimateExpD1.getPersonTeamCode());
			iaEstimateExpD1Vo.setPosition(iaEstimateExpD1.getPosition());
			iaEstimateExpD1Vo.setAllowancesDay(iaEstimateExpD1.getAllowancesDay());
			iaEstimateExpD1Vo.setAllowancesHalfDay(iaEstimateExpD1.getAllowancesHalfDay());
			iaEstimateExpD1Vo.setAccomFeePackages(iaEstimateExpD1.getAccomFeePackages());
			iaEstimateExpD1Vo.setAccomFeePackagesDat(iaEstimateExpD1.getAccomFeePackagesDat());
			iaEstimateExpD1Vo.setTranCost(iaEstimateExpD1.getTranCost());
			iaEstimateExpD1Vo.setOtherExpenses(iaEstimateExpD1.getOtherExpenses());
			iaEstimateExpD1Vo.setSumAmt(iaEstimateExpD1.getSumAmt());
			iaEstimateExpD1Vo.setSumAllowances(iaEstimateExpD1.getSumAllowances());
			iaEstimateExpD1Vo.setSumAccom(iaEstimateExpD1.getSumAccom());
			iaEstimateExpD1Vo.setRemark(iaEstimateExpD1.getRemark());
			iaEstimateExpD1Vo.setFlagNotWithdrawing(iaEstimateExpD1.getFlagNotWithdrawing());
			iaEstimateExpD1List.add(iaEstimateExpD1Vo);
		}
		return iaEstimateExpD1List;
	}
	
	public List<IaEstimateD1VoType> findData1ExpNo(String estExpNo) throws Exception {
		List<IaEstimateD1VoType> iaEstimateExpD1List = new ArrayList<>();
		IaEstimateD1VoType iaEstimateExpD1Vo = null;
		List<IaEstimateExpD1> iaEstimateExpDList = iaEstimateExpD1Repository.findDataExpNo(estExpNo);
		for (IaEstimateExpD1 iaEstimateExpD1 : iaEstimateExpDList) {
			iaEstimateExpD1Vo = new IaEstimateD1VoType();
			iaEstimateExpD1Vo.setEstimateExpD1Id(iaEstimateExpD1.getEstimateExpD1Id());
			iaEstimateExpD1Vo.setEstExpNo(iaEstimateExpD1.getEstExpNo());
			iaEstimateExpD1Vo.setSeqNo(iaEstimateExpD1.getSeqNo());
			iaEstimateExpD1Vo.setPersonTeamCode(iaEstimateExpD1.getPersonTeamCode());
			iaEstimateExpD1Vo.setPosition(iaEstimateExpD1.getPosition());
			iaEstimateExpD1Vo.setAllowancesDay(iaEstimateExpD1.getAllowancesDay());
			iaEstimateExpD1Vo.setAllowancesHalfDay(iaEstimateExpD1.getAllowancesHalfDay());
			iaEstimateExpD1Vo.setAccomFeePackages(iaEstimateExpD1.getAccomFeePackages());
			iaEstimateExpD1Vo.setAccomFeePackagesDat(iaEstimateExpD1.getAccomFeePackagesDat());
			iaEstimateExpD1Vo.setTranCost(iaEstimateExpD1.getTranCost());
			iaEstimateExpD1Vo.setOtherExpenses(iaEstimateExpD1.getOtherExpenses());
			iaEstimateExpD1Vo.setSumAmt(iaEstimateExpD1.getSumAmt());
			iaEstimateExpD1Vo.setSumAllowances(iaEstimateExpD1.getSumAllowances());
			iaEstimateExpD1Vo.setSumAccom(iaEstimateExpD1.getSumAccom());
			iaEstimateExpD1Vo.setRemark(iaEstimateExpD1.getRemark());
			iaEstimateExpD1Vo.setFlagNotWithdrawing(iaEstimateExpD1.getFlagNotWithdrawing());
			iaEstimateExpD1Vo.setSumAllowances(iaEstimateExpD1.getSumAllowances());
			iaEstimateExpD1Vo.setSumAccom(iaEstimateExpD1.getSumAccom());
			iaEstimateExpD1List.add(iaEstimateExpD1Vo);
		}
		return iaEstimateExpD1List;
	}

	public IaEstimateExpHVo findIaEstimateHByestExpNo(String estExpNo) {
		IaEstimateExpHVo EstExpVo = null;
		IaEstimateExpH data = null;
		ExciseDepartmentVo excise = null;
		data = iaEstimateExpHRepository.findByEstExpNo(estExpNo);
		try {
			EstExpVo = new IaEstimateExpHVo();
			EstExpVo.setEstExpNo(data.getEstExpNo());
			EstExpVo.setPersonResp(data.getPersonResp());
			EstExpVo.setRespDeptCode(data.getRespDeptCode());
			EstExpVo.setExpReqDate(data.getExpReqDate().toString());
			EstExpVo.setDestinationPlace(data.getDestinationPlace());
			String workStDate = ConvertDateUtils.formatDateToString(data.getWorkStDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
			EstExpVo.setWorkStDate(workStDate);
			String workFhDate = ConvertDateUtils.formatDateToString(data.getWorkFhDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
			EstExpVo.setWorkFhDate(workFhDate);

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getDestinationPlace());
			EstExpVo.setArea(excise.getArea());
			EstExpVo.setSector(excise.getSector());
			EstExpVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return EstExpVo;
	}

	public ByteArrayOutputStream exportInt0501(String estExpNo) throws IOException {
		/* create spreadsheet */
		String[] estExpNo1 = estExpNo.split("!");
		String estExpNos = estExpNo1[0] + "/" + estExpNo1[1];
		
		IaEstimateExpHVo iaEstimateExpHVo = null;
		List<IaEstimateD1VoType> datalist1 = new ArrayList<IaEstimateD1VoType>(); 
		try {
			iaEstimateExpHVo = findIaEstimateHByestExpNo(estExpNos);
			datalist1 = findIaEstimateD1ByestExpNo(estExpNos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		ExciseDepartmentVo excise = ExciseDepartmentUtil.getExciseDepartmentFull(iaEstimateExpHVo.getDestinationPlace());
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle TopicCenter = ExcelUtils.createTopicCenterStyle(workbook);
		CellStyle topicRightlite = ExcelUtils.createTopicRightliteStyle(workbook);
		Sheet sheet = workbook.createSheet();

		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row1 = sheet.createRow(rowNum);
		Cell cell1 = row1.createCell(cellNum);
		cell1 = row1.createCell(cellNum);
		cell1.setCellValue("ประมาณการค่าใช้จ่ายในการเดินทางไปราชการ");
		cell1.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row2 = sheet.createRow(rowNum);
		Cell cell2 = row2.createCell(cellNum);
		cell2 = row2.createCell(cellNum);
		cell2.setCellValue("หน่วยงาน กลุ่มตรวจสอบภายใน");
		cell2.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row3 = sheet.createRow(rowNum);
		Cell cell3 = row3.createCell(cellNum);
		cell3 = row3.createCell(cellNum);
		cell3.setCellValue("เดินทางไปราชการที่ " + excise.getArea());
		cell3.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row4 = sheet.createRow(rowNum);
		Cell cell4 = row4.createCell(cellNum);
		cell4 = row4.createCell(cellNum);
		cell4.setCellValue("ตั้งแต่วันที่ " + iaEstimateExpHVo.getWorkStDate() + " ถึง " + iaEstimateExpHVo.getWorkFhDate());
		cell4.setCellStyle(TopicCenter);
		rowNum++;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ที่", "ชื่อ", "ตำแหน่ง", "ค่าเบี้ยเลี้ยง", "", "ค่าที่พัก", "", "ค่าพาหนะ",
				"ค่าใช่จายอื่นๆ", "รวมเป็นเงิน", "หมายเหตุ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		cellNum = 0;
		cell = row.createCell(cellNum);

		String[] tbTH2 = { "", "", "", "วัน", "จำนวนเงิน", "วัน", "จำนวนเงิน", "", "", "", "" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 6, rowNum - 6, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 5, rowNum - 5, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 4, rowNum - 4, 0, 10));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 0, 10));

		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 7, 7));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 8, 8));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 9, 9));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 10, 10));

		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 3, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 5, 6));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 10; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, width * 150);
			} else if (i == 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 6;
		cellNum = 0;

		DecimalFormat df2 = new DecimalFormat("###,###.00");

		for (IaEstimateD1VoType data : datalist1) {
			// Re Initial
			cellNum = 0;
			row = sheet.createRow(rowNum);
			// Column 1
			CellStyle styleCustom = tdStyle;
			styleCustom.setAlignment(HorizontalAlignment.CENTER);
			cell = row.createCell(cellNum);
			cell.setCellValue(count++);
			cell.setCellStyle(styleCustom);
			cellNum++;
			// Column 2
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPersonTeamCode());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPosition());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAllowancesHalfDay().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getAllowancesDay().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAccomFeePackagesDat().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getAccomFeePackages()));
			cell.setCellStyle(tdRight);
			cellNum++;

			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getTranCost().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			
			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getOtherExpenses().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			
			// Column 10
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAmt()));
			cell.setCellStyle(tdRight);
			cellNum++;
			
			// Column 11
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Next Row
			rowNum++;
		}
		/* end details */
		rowNum++;
		cellNum = 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("หมายเหตุ             ค่าพาหนะ หมายถึง -ค่าเดินทางพร้อมสัมภาระจากที่พัก ถึงกรมสรรพสามิต และกรมสรรพสามิตถึงที่พัก");
		
		rowNum++;
		cellNum = 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue("-ค่าน้ำมัน     ");
		cell.setCellStyle(topicRightlite);
		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

}
