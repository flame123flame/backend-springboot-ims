package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Int1104Vo {
	/* header */
	private BigDecimal id;
	private String projectCode;
	private String projectName;
	private String sector;
	private String area;
	private String systemCode;
	private String systemName;
	private String budgetYear;
	private String checkType;
	private Date reportDate;
	private Date approveDate;
	private String status;
	private Date dateClosedWork;
	private String noteClosedWork;
	private String inspectionWork;
	private String exciseCode;
	private BigDecimal idConcludeFollowHdr;
	private BigDecimal timeNotify;

	/* dtls */
	private Date followReportDate;
	private Date resultNotifyDate;
	private Date followNotifyDate;
	private String followReportDateStr;
	private String resultNotifyDateStr;
	private String followNotifyDateStr;

	/* deetails List */
	private List<Int110401DtlVo> details;

	/* ExciseDepartmentVo */
	private ExciseDepartmentVo exciseDepartmentVo;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateClosedWork() {
		return dateClosedWork;
	}

	public void setDateClosedWork(Date dateClosedWork) {
		this.dateClosedWork = dateClosedWork;
	}

	public String getNoteClosedWork() {
		return noteClosedWork;
	}

	public void setNoteClosedWork(String noteClosedWork) {
		this.noteClosedWork = noteClosedWork;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public BigDecimal getIdConcludeFollowHdr() {
		return idConcludeFollowHdr;
	}

	public void setIdConcludeFollowHdr(BigDecimal idConcludeFollowHdr) {
		this.idConcludeFollowHdr = idConcludeFollowHdr;
	}

	public Date getFollowReportDate() {
		return followReportDate;
	}

	public void setFollowReportDate(Date followReportDate) {
		this.followReportDate = followReportDate;
	}

	public Date getResultNotifyDate() {
		return resultNotifyDate;
	}

	public void setResultNotifyDate(Date resultNotifyDate) {
		this.resultNotifyDate = resultNotifyDate;
	}

	public Date getFollowNotifyDate() {
		return followNotifyDate;
	}

	public void setFollowNotifyDate(Date followNotifyDate) {
		this.followNotifyDate = followNotifyDate;
	}

	public List<Int110401DtlVo> getDetails() {
		return details;
	}

	public void setDetails(List<Int110401DtlVo> details) {
		this.details = details;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public BigDecimal getTimeNotify() {
		return timeNotify;
	}

	public void setTimeNotify(BigDecimal timeNotify) {
		this.timeNotify = timeNotify;
	}

	public String getFollowReportDateStr() {
		return followReportDateStr;
	}

	public void setFollowReportDateStr(String followReportDateStr) {
		this.followReportDateStr = followReportDateStr;
	}

	public String getResultNotifyDateStr() {
		return resultNotifyDateStr;
	}

	public void setResultNotifyDateStr(String resultNotifyDateStr) {
		this.resultNotifyDateStr = resultNotifyDateStr;
	}

	public String getFollowNotifyDateStr() {
		return followNotifyDateStr;
	}

	public void setFollowNotifyDateStr(String followNotifyDateStr) {
		this.followNotifyDateStr = followNotifyDateStr;
	}

}