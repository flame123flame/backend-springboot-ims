package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0802Vo {
	/* IA_GFTRIAL_BALANCE */
	private String accNo;
	private String accName;
	private BigDecimal carryForward;
	private BigDecimal bringForward;
	private BigDecimal debit;
	private BigDecimal credit;

	/* IA_GFLEDGER_ACCOUNT */
	private String pkCode;
	private BigDecimal currAmt;

	/* summary */
	private BigDecimal sumCarryForward;
	private BigDecimal sumBringForward;
	private BigDecimal sumDebit;
	private BigDecimal sumCredit;
	private BigDecimal sumDebit2;
	private BigDecimal sumCredit2;
	private BigDecimal sumDebit3;
	private BigDecimal sumCredit3;
	private BigDecimal sumDiffDebit;
	private BigDecimal sumDiffCredit;

	/* custom */
	private BigDecimal debit2;
	private BigDecimal credit2;
	private BigDecimal debit3;
	private BigDecimal credit3;
	private BigDecimal diffDebit;
	private BigDecimal diffCredit;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
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

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public BigDecimal getCurrAmt() {
		return currAmt;
	}

	public void setCurrAmt(BigDecimal currAmt) {
		this.currAmt = currAmt;
	}

	public BigDecimal getSumCarryForward() {
		return sumCarryForward;
	}

	public void setSumCarryForward(BigDecimal sumCarryForward) {
		this.sumCarryForward = sumCarryForward;
	}

	public BigDecimal getSumBringForward() {
		return sumBringForward;
	}

	public void setSumBringForward(BigDecimal sumBringForward) {
		this.sumBringForward = sumBringForward;
	}

	public BigDecimal getSumDebit() {
		return sumDebit;
	}

	public void setSumDebit(BigDecimal sumDebit) {
		this.sumDebit = sumDebit;
	}

	public BigDecimal getSumCredit() {
		return sumCredit;
	}

	public void setSumCredit(BigDecimal sumCredit) {
		this.sumCredit = sumCredit;
	}

	public BigDecimal getSumDebit2() {
		return sumDebit2;
	}

	public void setSumDebit2(BigDecimal sumDebit2) {
		this.sumDebit2 = sumDebit2;
	}

	public BigDecimal getSumCredit2() {
		return sumCredit2;
	}

	public void setSumCredit2(BigDecimal sumCredit2) {
		this.sumCredit2 = sumCredit2;
	}

	public BigDecimal getSumDebit3() {
		return sumDebit3;
	}

	public void setSumDebit3(BigDecimal sumDebit3) {
		this.sumDebit3 = sumDebit3;
	}

	public BigDecimal getSumCredit3() {
		return sumCredit3;
	}

	public void setSumCredit3(BigDecimal sumCredit3) {
		this.sumCredit3 = sumCredit3;
	}

	public BigDecimal getSumDiffDebit() {
		return sumDiffDebit;
	}

	public void setSumDiffDebit(BigDecimal sumDiffDebit) {
		this.sumDiffDebit = sumDiffDebit;
	}

	public BigDecimal getSumDiffCredit() {
		return sumDiffCredit;
	}

	public void setSumDiffCredit(BigDecimal sumDiffCredit) {
		this.sumDiffCredit = sumDiffCredit;
	}

	public BigDecimal getDebit2() {
		return debit2;
	}

	public void setDebit2(BigDecimal debit2) {
		this.debit2 = debit2;
	}

	public BigDecimal getCredit2() {
		return credit2;
	}

	public void setCredit2(BigDecimal credit2) {
		this.credit2 = credit2;
	}

	public BigDecimal getDebit3() {
		return debit3;
	}

	public void setDebit3(BigDecimal debit3) {
		this.debit3 = debit3;
	}

	public BigDecimal getCredit3() {
		return credit3;
	}

	public void setCredit3(BigDecimal credit3) {
		this.credit3 = credit3;
	}

	public BigDecimal getDiffDebit() {
		return diffDebit;
	}

	public void setDiffDebit(BigDecimal diffDebit) {
		this.diffDebit = diffDebit;
	}

	public BigDecimal getDiffCredit() {
		return diffCredit;
	}

	public void setDiffCredit(BigDecimal diffCredit) {
		this.diffCredit = diffCredit;
	}

}