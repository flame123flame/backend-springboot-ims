package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0803TableVo {
	private String accNo;
	private BigDecimal carryForward;
	private BigDecimal bringForward;
	private BigDecimal debit;
	private BigDecimal credit;
	private String officeCode;
	private String officeCodeStr;
	private String disburseUnit;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getBringForward() {
		return bringForward;
	}

	public void setBringForward(BigDecimal bringForward) {
		this.bringForward = bringForward;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeCodeStr() {
		return officeCodeStr;
	}

	public void setOfficeCodeStr(String officeCodeStr) {
		this.officeCodeStr = officeCodeStr;
	}

	public String getDisburseUnit() {
		return disburseUnit;
	}

	public void setDisburseUnit(String disburseUnit) {
		this.disburseUnit = disburseUnit;
	}

}