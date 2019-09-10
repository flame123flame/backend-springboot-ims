
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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_TXINSUR_D1")
public class IaAuditTxinsurD1 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176015466467732369L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_TXINSUR_D1_GEN")
	@SequenceGenerator(name = "IA_AUDIT_TXINSUR_D1_GEN", sequenceName = "IA_AUDIT_TXINSUR_D1_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_TXINSUR_D1_ID")
	private Long iaAuditTxinsurD1Id;
	@Column(name = "AUDIT_TXINSUR_NO")
	private String auditTxinsurNo;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "RESULT_SEQ")
	private BigDecimal resultSeq;
	@Column(name = "BANK_GUARANTEE_DATE")
	private Date bankGuaranteeDate;
	@Column(name = "BANK_GUARANTEE_NO")
	private String bankGuaranteeNo;
	@Column(name = "BANK_GUARANTEE_AMT")
	private BigDecimal bankGuaranteeAmt;
	@Column(name = "BANK_GUARANTEE_RESULT")
	private String bankGuaranteeResult;
	@Column(name = "CASH_GUARANTEE_DATE")
	private Date cashGuaranteeDate;
	@Column(name = "CASH_RECEIPT_NO")
	private String cashReceiptNo;
	@Column(name = "CASH_GUARANTEE_AMT")
	private BigDecimal cashGuaranteeAmt;
	@Column(name = "CASH_GUARANTEE_RESULT")
	private String cashGuaranteeResult;

	public Long getIaAuditTxinsurD1Id() {
		return iaAuditTxinsurD1Id;
	}

	public void setIaAuditTxinsurD1Id(Long iaAuditTxinsurD1Id) {
		this.iaAuditTxinsurD1Id = iaAuditTxinsurD1Id;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public BigDecimal getResultSeq() {
		return resultSeq;
	}

	public void setResultSeq(BigDecimal resultSeq) {
		this.resultSeq = resultSeq;
	}

	public Date getBankGuaranteeDate() {
		return bankGuaranteeDate;
	}

	public void setBankGuaranteeDate(Date bankGuaranteeDate) {
		this.bankGuaranteeDate = bankGuaranteeDate;
	}

	public String getBankGuaranteeNo() {
		return bankGuaranteeNo;
	}

	public void setBankGuaranteeNo(String bankGuaranteeNo) {
		this.bankGuaranteeNo = bankGuaranteeNo;
	}

	public BigDecimal getBankGuaranteeAmt() {
		return bankGuaranteeAmt;
	}

	public void setBankGuaranteeAmt(BigDecimal bankGuaranteeAmt) {
		this.bankGuaranteeAmt = bankGuaranteeAmt;
	}

	public String getBankGuaranteeResult() {
		return bankGuaranteeResult;
	}

	public void setBankGuaranteeResult(String bankGuaranteeResult) {
		this.bankGuaranteeResult = bankGuaranteeResult;
	}

	public Date getCashGuaranteeDate() {
		return cashGuaranteeDate;
	}

	public void setCashGuaranteeDate(Date cashGuaranteeDate) {
		this.cashGuaranteeDate = cashGuaranteeDate;
	}

	public String getCashReceiptNo() {
		return cashReceiptNo;
	}

	public void setCashReceiptNo(String cashReceiptNo) {
		this.cashReceiptNo = cashReceiptNo;
	}

	public BigDecimal getCashGuaranteeAmt() {
		return cashGuaranteeAmt;
	}

	public void setCashGuaranteeAmt(BigDecimal cashGuaranteeAmt) {
		this.cashGuaranteeAmt = cashGuaranteeAmt;
	}

	public String getCashGuaranteeResult() {
		return cashGuaranteeResult;
	}

	public void setCashGuaranteeResult(String cashGuaranteeResult) {
		this.cashGuaranteeResult = cashGuaranteeResult;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getAuditTxinsurNo() {
		return auditTxinsurNo;
	}

	public void setAuditTxinsurNo(String auditTxinsurNo) {
		this.auditTxinsurNo = auditTxinsurNo;
	}

}
