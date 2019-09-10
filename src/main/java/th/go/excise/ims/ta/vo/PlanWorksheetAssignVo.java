package th.go.excise.ims.ta.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class PlanWorksheetAssignVo {

	private List<String> ids;
	private String budgetYear;
	private String analysisNumber;
	private String planNumber;
	private String planStatus;
	private String authComment;
	private String planComment;
	private String sendAllFlag;
	private String officeCode;
	private String subdeptCode;
	private String userLoginId;
	private String auditStatus;
	private String sector;
	private String area;
	private List<String> budgetYearList;
	private MultipartFile file;
	
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
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
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getAuthComment() {
		return authComment;
	}
	public void setAuthComment(String authComment) {
		this.authComment = authComment;
	}
	public String getPlanComment() {
		return planComment;
	}
	public void setPlanComment(String planComment) {
		this.planComment = planComment;
	}
	public String getSendAllFlag() {
		return sendAllFlag;
	}
	public void setSendAllFlag(String sendAllFlag) {
		this.sendAllFlag = sendAllFlag;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getSubdeptCode() {
		return subdeptCode;
	}
	public void setSubdeptCode(String subdeptCode) {
		this.subdeptCode = subdeptCode;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<String> getBudgetYearList() {
		return budgetYearList;
	}
	public void setBudgetYearList(List<String> budgetYearList) {
		this.budgetYearList = budgetYearList;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
