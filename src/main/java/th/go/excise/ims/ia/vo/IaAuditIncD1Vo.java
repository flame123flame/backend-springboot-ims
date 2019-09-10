package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaAuditIncD1Vo {

	private Long iaAuditIncDId;
	private Integer seqNo;
	private String auditIncNo;
	private String officeCode;
	private String docCtlNo;
	private String receiptNo;
	private BigDecimal runCheck;
	private String receiptDate;
	private String taxName;
	private String taxCode;
	private BigDecimal amount;
	private String remark;
	private String checkTax0307;
	private String checkStamp;
	private String checkTax0704;
	private String remarkTax;
	private String wasteReceiptFlag;
	private String actionFlag;

	public Long getIaAuditIncDId() {
		return iaAuditIncDId;
	}

	public void setIaAuditIncDId(Long iaAuditIncDId) {
		this.iaAuditIncDId = iaAuditIncDId;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getAuditIncNo() {
		return auditIncNo;
	}

	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDocCtlNo() {
		return docCtlNo;
	}

	public void setDocCtlNo(String docCtlNo) {
		this.docCtlNo = docCtlNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public BigDecimal getRunCheck() {
		return runCheck;
	}

	public void setRunCheck(BigDecimal runCheck) {
		this.runCheck = runCheck;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckTax0307() {
		return checkTax0307;
	}

	public void setCheckTax0307(String checkTax0307) {
		this.checkTax0307 = checkTax0307;
	}

	public String getCheckStamp() {
		return checkStamp;
	}

	public void setCheckStamp(String checkStamp) {
		this.checkStamp = checkStamp;
	}

	public String getCheckTax0704() {
		return checkTax0704;
	}

	public void setCheckTax0704(String checkTax0704) {
		this.checkTax0704 = checkTax0704;
	}

	public String getRemarkTax() {
		return remarkTax;
	}

	public void setRemarkTax(String remarkTax) {
		this.remarkTax = remarkTax;
	}

	public String getWasteReceiptFlag() {
		return wasteReceiptFlag;
	}

	public void setWasteReceiptFlag(String wasteReceiptFlag) {
		this.wasteReceiptFlag = wasteReceiptFlag;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

}
