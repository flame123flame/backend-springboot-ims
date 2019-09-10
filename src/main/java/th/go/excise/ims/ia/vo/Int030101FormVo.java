package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;

public class Int030101FormVo {
	private BigDecimal idFactors;
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String riskFactorsMaster;
	private String infoUsedRiskDesc;
	private String dateFrom;
	private String dateTo;
	private String side;
	private String riskUnit;
	private String riskIndicators;
	private MultipartFile file;
	
	private String dataEvaluate;
	
	private List<IaRiskFactorsData> iaRiskFactorsDataList;

	public BigDecimal getIdFactors() {
		return idFactors;
	}

	public void setIdFactors(BigDecimal idFactors) {
		this.idFactors = idFactors;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getRiskFactorsMaster() {
		return riskFactorsMaster;
	}

	public void setRiskFactorsMaster(String riskFactorsMaster) {
		this.riskFactorsMaster = riskFactorsMaster;
	}

	public String getInfoUsedRiskDesc() {
		return infoUsedRiskDesc;
	}

	public void setInfoUsedRiskDesc(String dataName) {
		this.infoUsedRiskDesc = dataName;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getRiskUnit() {
		return riskUnit;
	}

	public void setRiskUnit(String riskUnit) {
		this.riskUnit = riskUnit;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<IaRiskFactorsData> getIaRiskFactorsDataList() {
		return iaRiskFactorsDataList;
	}

	public void setIaRiskFactorsDataList(List<IaRiskFactorsData> iaRiskFactorsDataList) {
		this.iaRiskFactorsDataList = iaRiskFactorsDataList;
	}

	public String getRiskIndicators() {
		return riskIndicators;
	}

	public void setRiskIndicators(String riskIndicators) {
		this.riskIndicators = riskIndicators;
	}

	public String getDataEvaluate() {
		return dataEvaluate;
	}

	public void setDataEvaluate(String dataEvaluate) {
		this.dataEvaluate = dataEvaluate;
	}

}
