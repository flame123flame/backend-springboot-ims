package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_FORM_DOWNLOAD_H")
public class TaFormDownloadH extends BaseEntity {

	private static final long serialVersionUID = -4505913343569058062L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_DOWNLOAD_H_GEN")
	@SequenceGenerator(name = "TA_FORM_DOWNLOAD_H_GEN", sequenceName = "TA_FORM_DOWNLOAD_H_SEQ", allocationSize = 1)
	@Column(name = "TA_FORM_DOWNLOAD_H_SEQ")
	private Long taFormDownloadHSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "FORM_DL_NUMBER")
	private String formDlNumber;
	@Column(name = "FORM_DL_STATUS")
	private String formDlStatus;
	@Column(name = "APPROVAL_BY")
	private String approvalBy;
	@Column(name = "APPROVAL_DATE")
	private LocalDateTime approvalDate;
	@Column(name = "APPROVAL_COMMENT")
	private String approvalComment;
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	@Column(name = "APPROVED_DATE")
	private LocalDateTime approvedDate;
	@Column(name = "APPROVED_COMMENT")
	private String approvedComment;
	@Column(name = "APPROVED_NUMBER")
	private String approvedNumber;
	@Column(name = "REJECTED_BY")
	private String rejectedBy;
	@Column(name = "REJECTED_DATE")
	private LocalDateTime rejectedDate;
	@Column(name = "REJECTED_COMMENT")
	private String rejectedComment;

	public Long getTaFormDownloadHSeq() {
		return taFormDownloadHSeq;
	}

	public void setTaFormDownloadHSeq(Long taFormDownloadHSeq) {
		this.taFormDownloadHSeq = taFormDownloadHSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getFormDlNumber() {
		return formDlNumber;
	}

	public void setFormDlNumber(String formDlNumber) {
		this.formDlNumber = formDlNumber;
	}

	public String getFormDlStatus() {
		return formDlStatus;
	}

	public void setFormDlStatus(String formDlStatus) {
		this.formDlStatus = formDlStatus;
	}

	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}

	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(String approvedComment) {
		this.approvedComment = approvedComment;
	}

	public String getApprovedNumber() {
		return approvedNumber;
	}

	public void setApprovedNumber(String approvedNumber) {
		this.approvedNumber = approvedNumber;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public LocalDateTime getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(LocalDateTime rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public String getRejectedComment() {
		return rejectedComment;
	}

	public void setRejectedComment(String rejectedComment) {
		this.rejectedComment = rejectedComment;
	}

}
