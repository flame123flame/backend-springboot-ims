package th.go.excise.ims.ta.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperVo;

public abstract class AbstractServicePaperService<VO, ENTITY_H> {
	
	protected static final String EXPORT_TYPE_CREATE = "001";
	protected static final String EXPORT_TYPE_SV_NUM = "002";
	protected static final String NO_VALUE = "-";
	protected static final int SHEET_DATA_INDEX = 0;
	protected static final int SHEET_CRITERIA_INDEX = 1;
	protected static final String SHEET_NAME_CRITERIA = "Criteria";
	
	protected Gson gson;
	protected TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;
	protected PaperSequenceService paperSequenceService;
	
	@Autowired
	public final void setGson(Gson gson) {
		this.gson = gson;
	}

	@Autowired
	public final void setTaPlanWorksheetDtlRepository(TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository) {
		this.taPlanWorksheetDtlRepository = taPlanWorksheetDtlRepository;
	}

	@Autowired
	public final void setPaperSequenceService(PaperSequenceService paperSequenceService) {
		this.paperSequenceService = paperSequenceService;
	}
	
	protected static final ThreadLocal<DecimalFormat> DECIMAL_FORMAT = new ThreadLocal<DecimalFormat>() {
		@Override
		protected DecimalFormat initialValue() {
			return new DecimalFormat("#,##0.00");
		}
	};
	
	protected abstract Logger getLogger();
	
	protected abstract String getPaperCode();
	
	protected abstract Object getRepository();
	
	public ServicePaperVo inquiry(ServicePaperFormVo formVo) {
		ServicePaperVo vo = new ServicePaperVo();
		vo.setAuditPlanCode(formVo.getAuditPlanCode());
		vo.setNewRegId(formVo.getNewRegId());
		vo.setDutyGroupId(formVo.getDutyGroupId());
		
		List<VO> voList = null;
		if (StringUtils.isEmpty(formVo.getPaperSvNumber())) {
			vo.setStartDate(formVo.getStartDate());
			vo.setEndDate(formVo.getEndDate());
			voList = inquiryByWs(formVo);
		} else {
			vo.setPaperSvNumber(formVo.getPaperSvNumber());
			prepareServicePaperVo(formVo.getPaperSvNumber(), vo);
			voList = inquiryByPaperSvNumber(formVo);
		}
		
		DataTableAjax dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(voList);
		vo.setDataTableAjax(dataTableAjax);
		
		return vo;
	};
	
	protected abstract List<VO> inquiryByWs(ServicePaperFormVo formVo);
	
	protected abstract List<VO> inquiryByPaperSvNumber(ServicePaperFormVo formVo);
	
	protected void prepareServicePaperVo(String paperSvNumber, ServicePaperVo vo) {
		getLogger().info("prepareServicePaperVo paperSvNumber={}", paperSvNumber);
		
		try {
			Class<?> repositoryClass = Class.forName(String.format("th.go.excise.ims.ta.persistence.repository.TaPaperSv%sHRepository", getPaperCode()));
			Class<?> entityClass = ((Class<ENTITY_H>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
			
			Method methodFindByPaperSvNumber = repositoryClass.getMethod("findByPaperSvNumber", String.class);
			ENTITY_H entityH = (ENTITY_H) methodFindByPaperSvNumber.invoke(getRepository(), paperSvNumber);
			
			Method methodGetStartDate = entityClass.getMethod("getStartDate");
			LocalDate startDate = (LocalDate) methodGetStartDate.invoke(entityH);
			vo.setStartDate(ThaiBuddhistDate.from(startDate).format(DateTimeFormatter.ofPattern("MM/yyyy")));
			
			Method methodGetEndDate = entityClass.getMethod("getEndDate");
			LocalDate endDate = (LocalDate) methodGetEndDate.invoke(entityH);
			vo.setEndDate(ThaiBuddhistDate.from(endDate).format(DateTimeFormatter.ofPattern("MM/yyyy")));
		} catch (Exception e) {
			getLogger().warn(e.getMessage(), e);
		}
	}
	
	public byte[] export(ServicePaperFormVo formVo) {
		List<VO> voList = null;
		String exportType = null;
		if (StringUtils.isEmpty(formVo.getPaperSvNumber())) {
			voList = inquiryByWs(formVo);
			exportType = EXPORT_TYPE_CREATE;
		} else {
			voList = inquiryByPaperSvNumber(formVo);
			exportType = EXPORT_TYPE_SV_NUM;
		}
		return exportData(formVo, voList, exportType);
	}
	
	protected abstract byte[] exportData(ServicePaperFormVo formVo, List<VO> voList, String exportType);
	
	protected void createSheetCriteria(XSSFWorkbook workbook, ServicePaperFormVo formVo) {
		Sheet sheet = workbook.createSheet(SHEET_NAME_CRITERIA);
		int rowNum = 0;
		Row row = null;
		Cell cell = null;
		
		// Data
		// auditPlanCode
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("auditPlanCode");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getAuditPlanCode());
		rowNum++;
		
		// newRegId
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("newRegId");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getNewRegId());
		rowNum++;
		
		// dutyGroupId
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("dutyGroupId");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getDutyGroupId());
		rowNum++;
		
		// startDate
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("startDate");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getStartDate());
		rowNum++;
		
		// endDate
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("endDate");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getEndDate());
		rowNum++;
		
		// paperPrNumber
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		cell.setCellValue("paperSvNumber");
		cell = row.createCell(1);
		cell.setCellValue(formVo.getPaperSvNumber());
		rowNum++;
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 16);
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20);
	}
	
	public String getExportFileName(ServicePaperFormVo formVo) {
		String exportFileName = "%s-sv%s-%s";
		String paperPrNumber = StringUtils.isNotEmpty(formVo.getPaperSvNumber()) ? formVo.getPaperSvNumber() : "temp";
		
		return String.format(exportFileName, formVo.getAuditPlanCode(), getPaperCode(), paperPrNumber);
	}
	
	public ServicePaperVo upload(ServicePaperFormVo formVo) {
		getLogger().info("upload readCriteria filename={}", formVo.getFile().getOriginalFilename());
		
		ServicePaperVo vo = new ServicePaperVo();
		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(SHEET_CRITERIA_INDEX);
			Cell cell = null;
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					// auditPlanCode
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setAuditPlanCode(cell.getStringCellValue());
						vo.setAuditPlanCode(cell.getStringCellValue());
					}
				} else if (row.getRowNum() == 1) {
					// newRegId
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setNewRegId(cell.getStringCellValue());
						vo.setNewRegId(cell.getStringCellValue());
					}
				} else if (row.getRowNum() == 2) {
					// dutyGroupId
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setDutyGroupId(cell.getStringCellValue());
						vo.setDutyGroupId(cell.getStringCellValue());
					}
				} else if (row.getRowNum() == 3) {
					// startDate
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setStartDate(cell.getStringCellValue());
						vo.setStartDate(cell.getStringCellValue());
					}
				} else if (row.getRowNum() == 4) {
					// endDate
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setEndDate(cell.getStringCellValue());
						vo.setEndDate(cell.getStringCellValue());
					}
				} else if (row.getRowNum() == 5) {
					// paperPrNumber
					cell = row.getCell(1);
					if (cell != null) {
						formVo.setPaperSvNumber(cell.getStringCellValue());
						vo.setPaperSvNumber(cell.getStringCellValue());
					}
				}
			}
		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
		}
		
		DataTableAjax dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(uploadData(formVo));
		vo.setDataTableAjax(dataTableAjax);
		
		return vo;
	}
	
	protected abstract List<VO> uploadData(ServicePaperFormVo formVo);
	
	public abstract String save(ServicePaperFormVo formVo);
	
	public abstract List<String> getPaperSvNumberList(ServicePaperFormVo formVo);
	
	protected Type getListVoType() {
		Type voType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return TypeToken.getParameterized(List.class, voType).getType();
	}
	
	protected LocalDate toLocalDate(String inputDate) {
		return LocalDateUtils.thaiMonthYear2LocalDate(inputDate);
	}
	
	protected String prepareEntityH(ServicePaperFormVo formVo, Object entityObj, Class<?> entityClass) {
		TaPlanWorksheetDtl planDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = planDtl.getBudgetYear();
		String paperSvNumber = paperSequenceService.getPaperServiceNumber(officeCode, budgetYear);
		
		try {
			Method methodSetOfficeCode = entityClass.getDeclaredMethod("setOfficeCode", String.class);
			methodSetOfficeCode.invoke(entityObj, officeCode);
			
			Method methodSetBudgetYear = entityClass.getDeclaredMethod("setBudgetYear", String.class);
			methodSetBudgetYear.invoke(entityObj, budgetYear);
			
			Method methodSetPlanNumber = entityClass.getDeclaredMethod("setPlanNumber", String.class);
			methodSetPlanNumber.invoke(entityObj, planDtl.getPlanNumber());
			
			Method methodSetAuditPlanCode = entityClass.getDeclaredMethod("setAuditPlanCode", String.class);
			methodSetAuditPlanCode.invoke(entityObj, formVo.getAuditPlanCode());
			
			Method methodSetPaperSvNumber = entityClass.getDeclaredMethod("setPaperSvNumber", String.class);
			methodSetPaperSvNumber.invoke(entityObj, paperSvNumber);
			
			Method methodSetNewRegId = entityClass.getDeclaredMethod("setNewRegId", String.class);
			methodSetNewRegId.invoke(entityObj, formVo.getNewRegId());
			
			Method methodSetDutyGroupId = entityClass.getDeclaredMethod("setDutyGroupId", String.class);
			methodSetDutyGroupId.invoke(entityObj, formVo.getDutyGroupId());
			
			Method methodSetStartDate = entityClass.getDeclaredMethod("setStartDate", LocalDate.class);
			methodSetStartDate.invoke(entityObj, toLocalDate(formVo.getStartDate()));
			
			Method methodSetEndDate = entityClass.getDeclaredMethod("setEndDate", LocalDate.class);
			methodSetEndDate.invoke(entityObj, toLocalDate(formVo.getEndDate()));
		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
		}
		
		return paperSvNumber;
	}
	
}
