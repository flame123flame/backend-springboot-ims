package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskBudgetProject;

public class Int030403FormVo {

	private BigDecimal id;
	private String projectyear;
	private String projecttypecode;
	private BigDecimal inspectionWork;
	private BigDecimal idConfig;
	private String budgetYear;


	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProjectyear() {
		return projectyear;
	}

	public void setProjectyear(String projectyear) {
		this.projectyear = projectyear;
	}

	public String getProjecttypecode() {
		return projecttypecode;
	}

	public void setProjecttypecode(String projecttypecode) {
		this.projecttypecode = projecttypecode;
	}

	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public BigDecimal getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(BigDecimal idConfig) {
		this.idConfig = idConfig;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	
	
	
	
	

}
