package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsDataRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030101JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030101Vo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int030101Service {
	private Logger logger = LoggerFactory.getLogger(Int030101Service.class);

	@Autowired
	private Int030101JdbcRepository int030101JdbcRepository;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	@Autowired
	private IaRiskFactorsDataRepository iaRiskFactorsDataRepository;

	@Autowired
	private Int0301Service int0301Service;

	@Autowired
	private Int0401JdbcRepository int0401JdbcRep;

	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	@Transactional
	public Int030101Vo saveFactors(Int030101FormVo form) {
		// Save IaRiskFactorsMaster
		IaRiskFactorsMaster dataFactorsMaster = new IaRiskFactorsMaster();
		dataFactorsMaster.setBudgetYear(form.getBudgetYear());
		dataFactorsMaster.setRiskFactorsMaster(form.getRiskFactorsMaster());
		dataFactorsMaster.setInspectionWork(form.getInspectionWork());
		dataFactorsMaster.setNotDelete("N");
		dataFactorsMaster.setDataEvaluate(IaConstants.IA_DATA_EVALUATE.NEW);
		dataFactorsMaster.setSide(form.getSide());
		IaRiskFactorsMaster dataFactorsMasterRes = iaRiskFactorsMasterRepository.save(dataFactorsMaster);

		// Save IaRiskFactorsStatus
		IaRiskFactorsStatus dataFactorsStatus = new IaRiskFactorsStatus();
		dataFactorsStatus.setIdMaster(dataFactorsMasterRes.getId());
		dataFactorsStatus.setBudgetYear(form.getBudgetYear());
		dataFactorsStatus.setStatus("Y");
		dataFactorsStatus.setInspectionWork(form.getInspectionWork());
		iaRiskFactorsStatusRepository.save(dataFactorsStatus);

		// Save IaRiskFactors
		IaRiskFactors dataFactors = new IaRiskFactors();
		dataFactors.setIdMaster(dataFactorsMasterRes.getId());
		dataFactors.setRiskFactors(form.getRiskFactorsMaster());
		dataFactors.setBudgetYear(form.getBudgetYear());
		dataFactors.setInspectionWork(form.getInspectionWork());
		dataFactors.setSide(form.getSide());
		dataFactors.setStatusScreen(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_1_CODE);
		dataFactors.setDataEvaluate(IaConstants.IA_DATA_EVALUATE.NEW);
		IaRiskFactors dataFactorsRes = iaRiskFactorsRepository.save(dataFactors);

		// Save IaRiskFactorsConfig
		IaRiskFactorsConfig dataFactorsConfig = new IaRiskFactorsConfig();
		dataFactorsConfig.setIdFactors(dataFactorsRes.getId());
		dataFactorsConfig.setRiskUnit(form.getRiskUnit());
		dataFactorsConfig
				.setStartDate(ConvertDateUtils.parseStringToDate(form.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));// ,
																													// ConvertDateUtils.LOCAL_TH
		dataFactorsConfig.setEndDate(ConvertDateUtils.parseStringToDate(form.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
		dataFactorsConfig.setInfoUsedRiskDesc(form.getInfoUsedRiskDesc());
		dataFactorsConfig.setRiskIndicators(form.getRiskIndicators());

		Int0301FormVo form0301 = new Int0301FormVo();
		form0301.setBudgetYear(form.getBudgetYear());
		form0301.setInspectionWork(form.getInspectionWork());
		BigDecimal factorsLevels = new BigDecimal(3);
		List<Int0301Vo> int0301VoList = int0301Service.listdynamic(form0301);
		for (Int0301Vo int0301Vo : int0301VoList) {
			if (int0301Vo.getIaRiskFactorsConfig().getFactorsLevel() != null) {
				factorsLevels = int0301Vo.getIaRiskFactorsConfig().getFactorsLevel();
				break;
			}
		}
		dataFactorsConfig.setFactorsLevel(factorsLevels);
		IaRiskFactorsConfig dataFactorsConRes = iaRiskFactorsConfigRepository.save(dataFactorsConfig);

		// Set Response
		Int030101Vo res = new Int030101Vo();
		Int030101FormVo formVo = new Int030101FormVo();
		res.setIdFactors(dataFactorsRes.getId());
		formVo.setRiskFactorsMaster(form.getRiskFactorsMaster());
		formVo.setSide(form.getSide());
		formVo.setDateFrom(form.getDateFrom());
		formVo.setDateTo(form.getDateTo());
		formVo.setRiskUnit(form.getRiskUnit());
		formVo.setInfoUsedRiskDesc(form.getInfoUsedRiskDesc());
		formVo.setRiskIndicators(form.getRiskIndicators());

		res.setInt030101FormVo(formVo);

		return res;
	}

	public ByteArrayOutputStream exportInt030101() throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = excelUtil.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับ", "รหัส", "โครงการ", "ค่าความเสี่ยง " };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(excelUtil.thStyle);
		}

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 3; i++) {
			if (i == 2) {
				sheet.setColumnWidth(i, 76 * 220);
			} else if (i == 3) {
				sheet.setColumnWidth(i, 76 * 120);
			} else {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<IaRiskSelectCase> dataList = getDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(3));
		if (dataList.size() == 0) {
			dataList = saveDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(3));
		}
		for (IaRiskSelectCase item : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cellNum++;

			// Id.
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getId().toString() + "-" + item.getProjectCode());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(item.getProject());
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

	public ByteArrayOutputStream exportInt0301012() throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = excelUtil.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับที่	", "รหัสระบบสารสนเทศฯ", "ระบบสารสนเทศฯ ของกรมสรรพสามิต", "ค่าความเสี่ยง" };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(excelUtil.thStyle);
		}

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 5; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 200);
			} else if (i == 3) {
				sheet.setColumnWidth(i, 76 * 80);
			}
		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		List<IaRiskSelectCase> dataList = getDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(4));
		if (dataList.size() == 0) {
			dataList = saveDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(4));
		}

		for (IaRiskSelectCase data : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getId().toString() + "-" + data.getSystemCode());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getSystemName());
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

	public ByteArrayOutputStream exportInt0301013() throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = excelUtil.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับที่	", "รหัส", "รหัสสรรพสามิต", "ภาค", "พื้นที่", "ค่าความเสี่ยง" };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(excelUtil.thStyle);
		}

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 4; i++) {
			if (i == 1) {
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 120);
			} else {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		List<IaRiskSelectCase> dataList = getDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(5));
		if (dataList.size() == 0) {
			dataList = saveDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(5));
		}
		for (IaRiskSelectCase data : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getId().toString());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExciseCode());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getSector());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getArea());
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

	private List<IaRiskSelectCase> saveDataList(String budgetYear, BigDecimal inspectionWork) {
		// WEB SERVICE QUERY
		// NOW MOCKING DATA

//		Data mock inspectionWork 5

		List<String> dataList1 = new ArrayList<String>();
		dataList1.add("30100");
		dataList1.add("40100");
		dataList1.add("50100");

		List<String> dataList2 = new ArrayList<String>();
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 3");
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 4");
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 5");

		List<String> dataList3 = new ArrayList<String>();
		dataList3.add("สำนักงานสรรพสามิตพื้นที่เชียงใหม่");
		dataList3.add("สำนักงานสรรพสามิตพื้นที่นครราชสีมา");
		dataList3.add("สำนักงานสรรพสามิตพื้นที่อุดรธานี");

//		Data mock inspectionWork 4 

		List<String> dataList6 = new ArrayList<String>();
		dataList6.add("2");
		dataList6.add("3");
		dataList6.add("4");

		List<String> dataList7 = new ArrayList<String>();
		dataList7.add("ระบบงานสารสนเทศหลัก http://Web.excise.go.th/EDINTRAWeb");
		dataList7.add("ระบบความปลอดภัยกลาง (SSO) http://authen.excise.go.th/oiddas");
		dataList7.add("ระบบงานอีเมล์กรมสรรพสามิต http://mail.excise.go.th");

//		Data mock inspectionWork 3

		List<String> dataList4 = new ArrayList<String>();
		dataList4.add("10");
		dataList4.add("20");
		dataList4.add("30");

		List<String> dataList5 = new ArrayList<String>();
		dataList5.add("แผนหลักเกณฑ์การประเมินผลการปฏิบัติราชการ");
		dataList5.add("โครงการตามยุทธศาตร์");
		dataList5.add("แผนบริหารความเสี่ยง");

		List<IaRiskSelectCase> selectCases = new ArrayList<>();
		if (inspectionWork.compareTo(new BigDecimal(3)) == 0) {
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (int i = 0; i < dataList4.size(); i++) {
				selectCase = new IaRiskSelectCase();
				selectCase.setProjectCode(dataList4.get(i));
				selectCase.setProject(dataList5.get(i));
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(4)) == 0) {
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (int i = 0; i < dataList6.size(); i++) {
				selectCase = new IaRiskSelectCase();
				selectCase.setSystemCode(dataList6.get(i));
				selectCase.setSystemName(dataList7.get(i));
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(5)) == 0) {
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (int i = 0; i < dataList1.size(); i++) {
				selectCase = new IaRiskSelectCase();
				selectCase.setExciseCode(dataList1.get(i));
				selectCase.setSector(dataList2.get(i));
				selectCase.setArea(dataList3.get(i));
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		}
		return selectCases;
	}

	private List<IaRiskSelectCase> getDataList(String budgetYear, BigDecimal inspectionWork) {
		return int0401JdbcRep.findRowWithoutStatus(budgetYear, inspectionWork);
	}

	public List<IaRiskFactorsData> readFileExcel(Int030101FormVo form) throws Exception {
		List<IaRiskFactorsData> dataUploadList = new ArrayList<IaRiskFactorsData>();
		MultipartFile file = form.getFile();
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		DataFormatter dataFormatter = new DataFormatter();

		BigDecimal inspectionWork = form.getInspectionWork();
		if (new BigDecimal(3).equals(inspectionWork)) {
			for (Sheet sheet : workbook) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						continue; // just skip the rows if row number is 0 (Header)
					}
					IaRiskFactorsData dataUpload = new IaRiskFactorsData();
					int columns = 1;
					// dataUpload.setIdFactors(new
					// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));
					// dataUpload.setBudgetYear(dataFormatter.formatCellValue(row.getCell(columns++)));
					String idSelectAndprojectCode = dataFormatter.formatCellValue(row.getCell(columns++));
					BigDecimal idSelect = null;
					String idSelectString = StringUtils.trim(idSelectAndprojectCode).split("-")[0];
					idSelect = (StringUtils.isNotBlank(idSelectString)) ? new BigDecimal(idSelectString) : idSelect;
					dataUpload.setIdSelect(idSelect);

					String projectCode = StringUtils.trim(idSelectAndprojectCode).split("-")[1];
					projectCode = (StringUtils.isNotBlank(projectCode)) ? projectCode : "";
					dataUpload.setProjectCode(projectCode);
					dataUpload.setProject(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));

					dataUpload.setRiskCost(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					// dataUpload.setInspectionWork(new
					// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));

					dataUploadList.add(dataUpload);
				}
			}

		} else if (new BigDecimal(4).equals(inspectionWork)) {
			for (Sheet sheet : workbook) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						continue; // just skip the rows if row number is 0 (Header)
					}
					IaRiskFactorsData dataUpload = new IaRiskFactorsData();
					int columns = 1;
					String idSelectAndSystemCode = dataFormatter.formatCellValue(row.getCell(columns++));
					BigDecimal idSelect = null;
					String idSelectString = StringUtils.trim(idSelectAndSystemCode).split("-")[0];
					idSelect = (StringUtils.isNotBlank(idSelectString)) ? new BigDecimal(idSelectString) : idSelect;
					dataUpload.setIdSelect(idSelect);

					String systemCode = StringUtils.trim(idSelectAndSystemCode).split("-")[1];
					systemCode = (StringUtils.isNotBlank(systemCode)) ? systemCode : "";
					dataUpload.setSystemCode(systemCode);

					dataUpload.setSystemName(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					dataUpload.setRiskCost(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					// dataUpload.setInspectionWork(new
					// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));

					dataUploadList.add(dataUpload);
				}
			}
		} else if (new BigDecimal(5).equals(inspectionWork)) {
			for (Sheet sheet : workbook) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						continue; // just skip the rows if row number is 0 (Header)
					}
					IaRiskFactorsData dataUpload = new IaRiskFactorsData();
					int columns = 1;
					// dataUpload.setIdFactors(new
					// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));
					// dataUpload.setBudgetYear(dataFormatter.formatCellValue(row.getCell(columns++)));
					dataUpload.setIdSelect(
							new BigDecimal(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++)))));
					dataUpload.setExciseCode(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					dataUpload.setSector(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					dataUpload.setArea(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					dataUpload.setRiskCost(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
					// dataUpload.setInspectionWork(new
					// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));

					dataUploadList.add(dataUpload);
				}
			}
		} else {
			logger.info("Int030101Service : Upload No match inspectionWork");
		}

		return dataUploadList;
	}

//	@Transactional
	public void saveFactorsData(Int030101FormVo form) {
//		BigDecimal idFactor = form.getIdFactors();
//
//		IaRiskFactorsConfig dataSetConfig = iaRiskFactorsConfigRepository.findByIdFactors(idFactor);
//		dataSetConfig.setInfoUsedRiskDesc(form.getInfoUsedRiskDesc());
//		logger.info("dataSetConfig", dataSetConfig);
//		iaRiskFactorsConfigRepository.save(dataSetConfig);

		int030101JdbcRepository.deleteFactosData(form.getIdFactors().toString());
		IaRiskFactorsConfig configData = iaRiskFactorsConfigRepository.findByIdFactors(form.getIdFactors());
		if (!StringUtils.isEmpty(form.getDateFrom()) || !StringUtils.isEmpty(form.getDateTo())) {
			configData.setStartDate(ConvertDateUtils.parseStringToDate(form.getDateFrom(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH));
			configData.setEndDate(ConvertDateUtils.parseStringToDate(form.getDateTo(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH));
		}
		configData.setInfoUsedRiskDesc(form.getInfoUsedRiskDesc());
		configData.setRiskIndicators(form.getRiskIndicators());
		configData.setRiskUnit(form.getRiskUnit());
		iaRiskFactorsConfigRepository.save(configData);
		
		saveRiskFactor(form.getIdFactors(),form.getDataEvaluate());

		List<IaRiskFactorsData> dataList = form.getIaRiskFactorsDataList();
		IaRiskFactorsData dataSet = null;
		if (dataList != null) {
			for (IaRiskFactorsData iaRiskFactorsData : dataList) {
				dataSet = new IaRiskFactorsData();
				dataSet.setBudgetYear(form.getBudgetYear());
				dataSet.setInspectionWork(form.getInspectionWork());
				dataSet.setIdFactors(form.getIdFactors());
				dataSet.setIdSelect(iaRiskFactorsData.getIdSelect());

				dataSet.setProjectCode(iaRiskFactorsData.getProjectCode());
				dataSet.setProject(iaRiskFactorsData.getProject());

				dataSet.setExciseCode(iaRiskFactorsData.getExciseCode());
				dataSet.setSector(iaRiskFactorsData.getSector());
				dataSet.setArea(iaRiskFactorsData.getArea());

				dataSet.setSystemCode(iaRiskFactorsData.getSystemCode());
				dataSet.setSystemName(iaRiskFactorsData.getSystemName());

				dataSet.setRiskCost(iaRiskFactorsData.getRiskCost());
				iaRiskFactorsDataRepository.save(dataSet);
			}
		}

	}
	
	public void saveRiskFactor(BigDecimal idFactor,String dataEvaluate) {
		IaRiskFactors data = iaRiskFactorsRepository.findById(idFactor).get();
		data.setDataEvaluate(dataEvaluate);
		iaRiskFactorsRepository.save(data);
	}

	@Transactional
	public Int030101Vo configFactorsDataList(Int030101FormVo form) {
		Int030101Vo res = new Int030101Vo();
		List<Int030101Vo> resList = int030101JdbcRepository.list(form);
		for (Int030101Vo int030101Vo : resList) {
			Int030101FormVo formVo = new Int030101FormVo();
			formVo.setSide(int030101Vo.getInt030101FormVo().getSide());
			formVo.setRiskFactorsMaster(int030101Vo.getInt030101FormVo().getRiskFactorsMaster());
			formVo.setInfoUsedRiskDesc(int030101Vo.getInt030101FormVo().getInfoUsedRiskDesc());
			formVo.setRiskIndicators(int030101Vo.getInt030101FormVo().getRiskIndicators());
			formVo.setRiskUnit(int030101Vo.getInt030101FormVo().getRiskUnit());
			formVo.setDateFrom(int030101Vo.getInt030101FormVo().getDateFrom());
			formVo.setDateTo(int030101Vo.getInt030101FormVo().getDateTo());
			res.setInt030101FormVo(formVo);
		}

		return res;
	}
}
