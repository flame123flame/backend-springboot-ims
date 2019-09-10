package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;
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
@Table(name = "TA_PLAN_WORKSHEET_DTL")
public class TaPlanWorksheetDtl extends BaseEntity {

	private static final long serialVersionUID = 196341662627564519L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_DTL_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_DTL_GEN", sequenceName = "TA_PLAN_WORKSHEET_DTL_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_DTL_ID")
	private Long planWorksheetDtlId;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "SYSTEM_TYPE")
	private String systemType;
	@Column(name = "PLAN_TYPE")
	private String planType;
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;
	@Column(name = "AUDIT_TYPE")
	private String auditType;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "AUDIT_START_DATE")
	private LocalDate auditStartDate;
	@Column(name = "AUDIT_END_DATE")
	private LocalDate auditEndDate;
	@Column(name = "AU_SUBDEPT_CODE")
	private String auSubdeptCode;
	@Column(name = "AU_JOB_RESP")
	private String auJobResp;
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	@Column(name = "APPROVED_DATE")
	private LocalDateTime approvedDate;
	@Column(name = "APPROVED_COMMENT")
	private String approvedComment;
	@Column(name = "APPROVED_NUMBER")
	private String approvedNumber;
	@Column(name = "RECEIVED_BY")
	private String receivedBy;
	@Column(name = "RECEIVED_DATE")
	private LocalDateTime receivedDate;
	@Column(name = "ASSIGNED_SUBDEPT_BY")
	private String assignedSubdeptBy;
	@Column(name = "ASSIGNED_SUBDEPT_DATE")
	private LocalDateTime assignedSubdeptDate;
	@Column(name = "ASSIGNED_OFFICER_BY")
	private String assignedOfficerBy;
	@Column(name = "ASSIGNED_OFFICER_DATE")
	private LocalDateTime assignedOfficerDate;
	@Column(name = "PLAN_REPLACE_ID")
	private Long planReplaceId;
	@Column(name = "REPLACE_REASON")
	private String replaceReason;
	@Column(name = "REPLACE_REG_ID")
	private String replaceRegId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;

	public Long getPlanWorksheetDtlId() {
		return planWorksheetDtlId;
	}

	public void setPlanWorksheetDtlId(Long planWorksheetDtlId) {
		this.planWorksheetDtlId = planWorksheetDtlId;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
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

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public LocalDate getAuditStartDate() {
		return auditStartDate;
	}

	public void setAuditStartDate(LocalDate auditStartDate) {
		this.auditStartDate = auditStartDate;
	}

	public LocalDate getAuditEndDate() {
		return auditEndDate;
	}

	public void setAuditEndDate(LocalDate auditEndDate) {
		this.auditEndDate = auditEndDate;
	}

	public String getAuSubdeptCode() {
		return auSubdeptCode;
	}

	public void setAuSubdeptCode(String auSubdeptCode) {
		this.auSubdeptCode = auSubdeptCode;
	}

	public String getAuJobResp() {
		return auJobResp;
	}

	public void setAuJobResp(String auJobResp) {
		this.auJobResp = auJobResp;
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

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public LocalDateTime getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(LocalDateTime receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getAssignedSubdeptBy() {
		return assignedSubdeptBy;
	}

	public void setAssignedSubdeptBy(String assignedSubdeptBy) {
		this.assignedSubdeptBy = assignedSubdeptBy;
	}

	public LocalDateTime getAssignedSubdeptDate() {
		return assignedSubdeptDate;
	}

	public void setAssignedSubdeptDate(LocalDateTime assignedSubdeptDate) {
		this.assignedSubdeptDate = assignedSubdeptDate;
	}

	public String getAssignedOfficerBy() {
		return assignedOfficerBy;
	}

	public void setAssignedOfficerBy(String assignedOfficerBy) {
		this.assignedOfficerBy = assignedOfficerBy;
	}

	public LocalDateTime getAssignedOfficerDate() {
		return assignedOfficerDate;
	}

	public void setAssignedOfficerDate(LocalDateTime assignedOfficerDate) {
		this.assignedOfficerDate = assignedOfficerDate;
	}

	public Long getPlanReplaceId() {
		return planReplaceId;
	}

	public void setPlanReplaceId(Long planReplaceId) {
		this.planReplaceId = planReplaceId;
	}

	public String getReplaceReason() {
		return replaceReason;
	}

	public void setReplaceReason(String replaceReason) {
		this.replaceReason = replaceReason;
	}

	public String getReplaceRegId() {
		return replaceRegId;
	}

	public void setReplaceRegId(String replaceRegId) {
		this.replaceRegId = replaceRegId;
	}
	
	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	

}
