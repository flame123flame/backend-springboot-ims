package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDateTime;
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
@Table(name = "TA_PLAN_WORKSHEET_HDR")
public class TaPlanWorksheetHdr extends BaseEntity {

	private static final long serialVersionUID = 6378937104289474037L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_HDR_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_HDR_GEN", sequenceName = "TA_PLAN_WORKSHEET_HDR_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_HDR_ID")
	private Long planWorksheetHdrId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "SEND_ALL_FLAG")
	private String sendAllFlag;
	@Column(name = "PLAN_STATUS")
	private String planStatus;
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
	@Column(name = "DOC_APPROVE_NO")
	private String docApproveNo;
	@Column(name = "DOC_APPROVE_DATE")
	private Date docApproveDate;

	public Long getPlanWorksheetHdrId() {
		return planWorksheetHdrId;
	}

	public void setPlanWorksheetHdrId(Long planWorksheetHdrId) {
		this.planWorksheetHdrId = planWorksheetHdrId;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getSendAllFlag() {
		return sendAllFlag;
	}

	public void setSendAllFlag(String sendAllFlag) {
		this.sendAllFlag = sendAllFlag;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
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

	public String getDocApproveNo() {
		return docApproveNo;
	}

	public void setDocApproveNo(String docApproveNo) {
		this.docApproveNo = docApproveNo;
	}

	public Date getDocApproveDate() {
		return docApproveDate;
	}

	public void setDocApproveDate(Date docApproveDate) {
		this.docApproveDate = docApproveDate;
	}
	

}
