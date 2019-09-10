package th.go.excise.ims.ta.vo;

public class PlanWorksheetStatus {

	private String planStatus;
	private String planStatusDesc;
	private String approvalComment;
	private String approvedComment;
	
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getPlanStatusDesc() {
		return planStatusDesc;
	}
	public void setPlanStatusDesc(String planStatusDesc) {
		this.planStatusDesc = planStatusDesc;
	}
	public String getApprovalComment() {
		return approvalComment;
	}
	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}
	public String getApprovedComment() {
		return approvedComment;
	}
	public void setApprovedComment(String approvedComment) {
		this.approvedComment = approvedComment;
	}
	
}
