package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaAuditIncD1WasteReceiptVo {

	private String trnDate;
	private String incctlNo;
	private String receiptNo;
	private String receiptNoNew;
	private BigDecimal paidAmt;
	private String reasonCode;
	private String reasonDesc;

	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}

	public String getIncctlNo() {
		return incctlNo;
	}

	public void setIncctlNo(String incctlNo) {
		this.incctlNo = incctlNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptNoNew() {
		return receiptNoNew;
	}

	public void setReceiptNoNew(String receiptNoNew) {
		this.receiptNoNew = receiptNoNew;
	}

	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

}
