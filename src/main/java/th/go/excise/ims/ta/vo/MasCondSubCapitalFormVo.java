package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class MasCondSubCapitalFormVo {
	
	private String budgetYear;
	private String dutyCode;
	private BigDecimal hugeCapitalAmount;
	private BigDecimal largeCapitalAmount;
	private BigDecimal mediumCapitalAmount;
	private BigDecimal smallCapitalAmount;
	
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public BigDecimal getHugeCapitalAmount() {
		return hugeCapitalAmount;
	}
	public void setHugeCapitalAmount(BigDecimal hugeCapitalAmount) {
		this.hugeCapitalAmount = hugeCapitalAmount;
	}
	public BigDecimal getLargeCapitalAmount() {
		return largeCapitalAmount;
	}
	public void setLargeCapitalAmount(BigDecimal largeCapitalAmount) {
		this.largeCapitalAmount = largeCapitalAmount;
	}
	public BigDecimal getMediumCapitalAmount() {
		return mediumCapitalAmount;
	}
	public void setMediumCapitalAmount(BigDecimal mediumCapitalAmount) {
		this.mediumCapitalAmount = mediumCapitalAmount;
	}
	public BigDecimal getSmallCapitalAmount() {
		return smallCapitalAmount;
	}
	public void setSmallCapitalAmount(BigDecimal smallCapitalAmount) {
		this.smallCapitalAmount = smallCapitalAmount;
	}
	
}
