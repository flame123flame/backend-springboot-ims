package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int090304Vo {
	private String paymentDate;
	private String refPayment;
	private BigDecimal amount;
	private String budgetType;
	private String itemDesc;
	private String payee;
	private String bankName;

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getRefPayment() {
		return refPayment;
	}

	public void setRefPayment(String refPayment) {
		this.refPayment = refPayment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
