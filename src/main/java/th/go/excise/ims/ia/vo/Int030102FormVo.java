package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;

public class Int030102FormVo {
	private BigDecimal id;
	private BigDecimal idHead;
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String status;
	private String factorsLevel;
	
	private String budgetYearTo;
	private IaRiskFactorsConfigAll iaRiskFactorsConfigAll; 
	

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

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

	public String getBudgetYearTo() {
		return budgetYearTo;
	}

	public void setBudgetYearTo(String budgetYearTo) {
		this.budgetYearTo = budgetYearTo;
	}

	public String getFactorsLevel() {
		return factorsLevel;
	}

	public void setFactorsLevel(String factorsLevel) {
		this.factorsLevel = factorsLevel;
	}

	public IaRiskFactorsConfigAll getIaRiskFactorsConfigAll() {
		return iaRiskFactorsConfigAll;
	}

	public void setIaRiskFactorsConfigAll(IaRiskFactorsConfigAll iaRiskFactorsConfigAll) {
		this.iaRiskFactorsConfigAll = iaRiskFactorsConfigAll;
	}
	
	

	
}
