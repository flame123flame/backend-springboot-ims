package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int091303SaveVo {
	private Long utilityBudgetSeq;
	private String exciseCode;
	private String ubudgetQ;
	private BigDecimal budgetAmt;
	private BigDecimal nonBudgetAmt;
	
	
	public Long getUtilityBudgetSeq() {
		return utilityBudgetSeq;
	}
	public void setUtilityBudgetSeq(Long utilityBudgetSeq) {
		this.utilityBudgetSeq = utilityBudgetSeq;
	}
	public String getExciseCode() {
		return exciseCode;
	}
	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}
	public String getUbudgetQ() {
		return ubudgetQ;
	}
	public void setUbudgetQ(String ubudgetQ) {
		this.ubudgetQ = ubudgetQ;
	}
	public BigDecimal getBudgetAmt() {
		return budgetAmt;
	}
	public void setBudgetAmt(BigDecimal budgetAmt) {
		this.budgetAmt = budgetAmt;
	}
	public BigDecimal getNonBudgetAmt() {
		return nonBudgetAmt;
	}
	public void setNonBudgetAmt(BigDecimal nonBudgetAmt) {
		this.nonBudgetAmt = nonBudgetAmt;
	}
	
	
}
