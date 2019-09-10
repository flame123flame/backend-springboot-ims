package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_INCOME_PERFORM")
public class IaRiskIncomePerform extends BaseEntity {

	private static final long serialVersionUID = -3710042369905350180L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_INCOME_PERFORM_GEN")
	@SequenceGenerator(name = "IA_RISK_INCOME_PERFORM_GEN", sequenceName = "IA_RISK_INCOME_PERFORM_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "ID_FACTORS")
	private BigDecimal idFactors;

	@Column(name = "BUDGET_YEAR")
	private String budgetYear;

	@Column(name = "OFFICE_CODE")
	private String officeCode;
	
	@Column(name = "SECTOR")
	private String sector;
	
	@Column(name = "AREA")
	private String area;

	@Column(name = "SUM_AMOUNT")
	private BigDecimal sumAmount;

	@Column(name = "FORECASE_AMOUNT")
	private BigDecimal forecaseAmount;

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

}
