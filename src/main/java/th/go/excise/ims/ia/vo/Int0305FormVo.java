package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;

public class Int0305FormVo {
	private BigDecimal idHead;
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String status;
	private String factorsLevel;
	private String budgetYearTo;

//	Form Edit
	private BigDecimal id;
	private String riskFactorsMaster;
	private String side;

	private String startDate;
	private String endDate;
	private IaRiskFactorsConfig iaRiskFactorsConfig;

	private String riskUnit;
	private String infoUsedRiskDesc;
	private String riskIndicators;
	private String dataEvaluate;

	private List<Int0305FormVo> int0305FormVoList;

	private BigDecimal idMaster;

	private List<IaRiskFactorsConfig> iaRiskFactorsConfigList;
	private IaRiskFactorsConfigAll iaRiskFactorsConfigAll;
	private BigDecimal idConfig;

	public BigDecimal getIdHead() {
		return idHead;
	}

	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFactorsLevel() {
		return factorsLevel;
	}

	public void setFactorsLevel(String factorsLevel) {
		this.factorsLevel = factorsLevel;
	}

	public String getBudgetYearTo() {
		return budgetYearTo;
	}

	public void setBudgetYearTo(String budgetYearTo) {
		this.budgetYearTo = budgetYearTo;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getRiskFactorsMaster() {
		return riskFactorsMaster;
	}

	public void setRiskFactorsMaster(String riskFactorsMaster) {
		this.riskFactorsMaster = riskFactorsMaster;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public IaRiskFactorsConfig getIaRiskFactorsConfig() {
		return iaRiskFactorsConfig;
	}

	public void setIaRiskFactorsConfig(IaRiskFactorsConfig iaRiskFactorsConfig) {
		this.iaRiskFactorsConfig = iaRiskFactorsConfig;
	}

	public String getRiskUnit() {
		return riskUnit;
	}

	public void setRiskUnit(String riskUnit) {
		this.riskUnit = riskUnit;
	}

	public String getInfoUsedRiskDesc() {
		return infoUsedRiskDesc;
	}

	public void setInfoUsedRiskDesc(String infoUsedRiskDesc) {
		this.infoUsedRiskDesc = infoUsedRiskDesc;
	}

	public String getRiskIndicators() {
		return riskIndicators;
	}

	public void setRiskIndicators(String riskIndicators) {
		this.riskIndicators = riskIndicators;
	}

	public List<Int0305FormVo> getInt0305FormVoList() {
		return int0305FormVoList;
	}

	public void setInt0305FormVoList(List<Int0305FormVo> int0305FormVoList) {
		this.int0305FormVoList = int0305FormVoList;
	}

	public BigDecimal getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(BigDecimal idMaster) {
		this.idMaster = idMaster;
	}

	public List<IaRiskFactorsConfig> getIaRiskFactorsConfigList() {
		return iaRiskFactorsConfigList;
	}

	public void setIaRiskFactorsConfigList(List<IaRiskFactorsConfig> iaRiskFactorsConfigList) {
		this.iaRiskFactorsConfigList = iaRiskFactorsConfigList;
	}

	public IaRiskFactorsConfigAll getIaRiskFactorsConfigAll() {
		return iaRiskFactorsConfigAll;
	}

	public void setIaRiskFactorsConfigAll(IaRiskFactorsConfigAll iaRiskFactorsConfigAll) {
		this.iaRiskFactorsConfigAll = iaRiskFactorsConfigAll;
	}

	public BigDecimal getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(BigDecimal idConfig) {
		this.idConfig = idConfig;
	}

	public String getDataEvaluate() {
		return dataEvaluate;
	}

	public void setDataEvaluate(String dataEvaluate) {
		this.dataEvaluate = dataEvaluate;
	}

}
