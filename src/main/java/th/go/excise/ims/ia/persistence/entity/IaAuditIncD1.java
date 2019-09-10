package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_INC_D1")
public class IaAuditIncD1 extends BaseEntity {

	private static final long serialVersionUID = -2235042338832352491L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_D1_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_D1_GEN", sequenceName = "IA_AUDIT_INC_D1_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_INC_D_ID")
	private Long iaAuditIncDId;
	@Column(name = "AUDIT_INC_NO")
	private String auditIncNo;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "DOC_CTL_NO")
	private String docCtlNo;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "RUN_CHECK")
	private BigDecimal runCheck;
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;
	@Column(name = "TAX_NAME")
	private String taxName;
	@Column(name = "TAX_CODE")
	private String taxCode;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "CHECK_TAX0307")
	private String checkTax0307;
	@Column(name = "CHECK_STAMP")
	private String checkStamp;
	@Column(name = "CHECK_TAX0704")
	private String checkTax0704;
	@Column(name = "REMARK_TAX")
	private String remarkTax;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "WASTE_RECEIPT_NO_FLAG")
	private String wasteReceiptNoFlag;
	@Column(name = "ACTION_FLAG")
	private String actionFlag;

	public Long getIaAuditIncDId() {
		return iaAuditIncDId;
	}

	public void setIaAuditIncDId(Long iaAuditIncDId) {
		this.iaAuditIncDId = iaAuditIncDId;
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

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
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

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getWasteReceiptNoFlag() {
		return wasteReceiptNoFlag;
	}

	public void setWasteReceiptNoFlag(String wasteReceiptNoFlag) {
		this.wasteReceiptNoFlag = wasteReceiptNoFlag;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

}
