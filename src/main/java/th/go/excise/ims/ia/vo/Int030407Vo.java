package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int030407Vo {
	private BigDecimal id;
	private BigDecimal idFactors;
	private String budgetYear;
	private String officeCode;
	private String sector;
	private String area;
	private BigDecimal sumAmount;
	private BigDecimal forecaseAmount;
	private BigDecimal diffAmount;
	private BigDecimal rateAmount;
	private BigDecimal rateRisk;
	private String textRisk;
	private String colorRisk;

	private IntCalculateCriteriaVo intCalculateCriteriaVo;

	/* ExciseDepartmentVo */
	private ExciseDepartmentVo exciseDepartmentVo;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public BigDecimal getForecaseAmount() {
		return forecaseAmount;
	}

	public void setForecaseAmount(BigDecimal forecaseAmount) {
		this.forecaseAmount = forecaseAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}

	public BigDecimal getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(BigDecimal rateAmount) {
		this.rateAmount = rateAmount;
	}

	public BigDecimal getRateRisk() {
		return rateRisk;
	}

	public void setRateRisk(BigDecimal rateRisk) {
		this.rateRisk = rateRisk;
	}

	public String getTextRisk() {
		return textRisk;
	}

	public void setTextRisk(String textRisk) {
		this.textRisk = textRisk;
	}

	public String getColorRisk() {
		return colorRisk;
	}

	public void setColorRisk(String colorRisk) {
		this.colorRisk = colorRisk;
	}

	public IntCalculateCriteriaVo getIntCalculateCriteriaVo() {
		return intCalculateCriteriaVo;
	}

	public void setIntCalculateCriteriaVo(IntCalculateCriteriaVo intCalculateCriteriaVo) {
		this.intCalculateCriteriaVo = intCalculateCriteriaVo;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
