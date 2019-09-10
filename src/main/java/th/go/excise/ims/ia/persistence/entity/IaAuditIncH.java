
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_AUDIT_INC_H")
public class IaAuditIncH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -212751796592128015L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_H_GEN", sequenceName = "IA_AUDIT_INC_H_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_INC_SEQ")
	private Long auditIncSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "RECEIPT_DATE_FROM")
	private Date receiptDateFrom;
	@Column(name = "RECEIPT_DATE_TO")
	private Date receiptDateTo;
	@Column(name = "AUDIT_INC_NO")
	private String auditIncNo;
	@Column(name = "D1_AUDIT_FLAG")
	private String d1AuditFlag;
	@Column(name = "D1_CONDITION_TEXT")
	private String d1ConditionText;
	@Column(name = "D1_CRITERIA_TEXT")
	private String d1CriteriaText;
	@Column(name = "D2_CONDITION_TEXT")
	private String d2ConditionText;
	@Column(name = "D2_CRITERIA_TEXT")
	private String d2CriteriaText;
	@Column(name = "D3_CONDITION_TEXT")
	private String d3ConditionText;
	@Column(name = "D3_CRITERIA_TEXT")
	private String d3CriteriaText;
	@Column(name = "D4_CONDITION_TEXT")
	private String d4ConditionText;
	@Column(name = "D4_CRITERIA_TEXT")
	private String d4CriteriaText;

	public Long getAuditIncSeq() {
		return auditIncSeq;
	}

	public void setAuditIncSeq(Long auditIncSeq) {
		this.auditIncSeq = auditIncSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Date getReceiptDateFrom() {
		return receiptDateFrom;
	}

	public void setReceiptDateFrom(Date receiptDateFrom) {
		this.receiptDateFrom = receiptDateFrom;
	}

	public Date getReceiptDateTo() {
		return receiptDateTo;
	}

	public void setReceiptDateTo(Date receiptDateTo) {
		this.receiptDateTo = receiptDateTo;
	}

	public String getAuditIncNo() {
		return auditIncNo;
	}

	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}

	public String getD1AuditFlag() {
		return d1AuditFlag;
	}

	public void setD1AuditFlag(String d1AuditFlag) {
		this.d1AuditFlag = d1AuditFlag;
	}

	public String getD1ConditionText() {
		return d1ConditionText;
	}

	public void setD1ConditionText(String d1ConditionText) {
		this.d1ConditionText = d1ConditionText;
	}

	public String getD1CriteriaText() {
		return d1CriteriaText;
	}

	public void setD1CriteriaText(String d1CriteriaText) {
		this.d1CriteriaText = d1CriteriaText;
	}

	public String getD2ConditionText() {
		return d2ConditionText;
	}

	public void setD2ConditionText(String d2ConditionText) {
		this.d2ConditionText = d2ConditionText;
	}

	public String getD2CriteriaText() {
		return d2CriteriaText;
	}

	public void setD2CriteriaText(String d2CriteriaText) {
		this.d2CriteriaText = d2CriteriaText;
	}

	public String getD3ConditionText() {
		return d3ConditionText;
	}

	public void setD3ConditionText(String d3ConditionText) {
		this.d3ConditionText = d3ConditionText;
	}

	public String getD3CriteriaText() {
		return d3CriteriaText;
	}

	public void setD3CriteriaText(String d3CriteriaText) {
		this.d3CriteriaText = d3CriteriaText;
	}

	public String getD4ConditionText() {
		return d4ConditionText;
	}

	public void setD4ConditionText(String d4ConditionText) {
		this.d4ConditionText = d4ConditionText;
	}

	public String getD4CriteriaText() {
		return d4CriteriaText;
	}

	public void setD4CriteriaText(String d4CriteriaText) {
		this.d4CriteriaText = d4CriteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
