
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_CONCLUDE_FOLLOW_HDR")
public class IaConcludeFollowHdr extends BaseEntity {

	private static final long serialVersionUID = 7214732600189130473L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CONCLUDE_FOLLOW_HDR_GEN")
	@SequenceGenerator(name = "IA_CONCLUDE_FOLLOW_HDR_GEN", sequenceName = "IA_CONCLUDE_FOLLOW_HDR_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "CHECK_TYPE")
	private String checkType;
	@Column(name = "DATE_FROM")
	private Date dateFrom;
	@Column(name = "DATE_TO")
	private Date dateTo;
	@Column(name = "APPROVERS")
	private String approvers;
	@Column(name = "CHECK_STATUS")
	private String checkStatus;
	@Column(name = "APPROVE_DATE")
	private Date approveDate;
	@Column(name = "NOTATION")
	private String notation;
	@Column(name = "SEND_STATUS")
	private String sendStatus;
	@Column(name = "INSPECTION_WORK")
	private String inspectionWork;
	@Column(name = "PROJECT_CODE")
	private String projectCode;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "SECTOR")
	private String sector;
	@Column(name = "AREA")
	private String area;
	@Column(name = "SYSTEM_NAME")
	private String systemName;
	@Column(name = "SYSTEM_CODE")
	private String systemCode;
	@Column(name = "INSPECTION_UNIT")
	private String inspectionUnit;
	@Column(name = "REPORT_NUMBER")
	private String reportNumber;
	@Column(name = "DATE_RECEIVING")
	private Date dateReceiving;
	@Column(name = "REPORTING_RESULTS")
	private String reportingResults;
	@Column(name = "HEAD_INSPECTION_UNIT")
	private String headInspectionUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getApprovers() {
		return approvers;
	}

	public void setApprovers(String approvers) {
		this.approvers = approvers;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
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

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getInspectionUnit() {
		return inspectionUnit;
	}

	public void setInspectionUnit(String inspectionUnit) {
		this.inspectionUnit = inspectionUnit;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public Date getDateReceiving() {
		return dateReceiving;
	}

	public void setDateReceiving(Date dateReceiving) {
		this.dateReceiving = dateReceiving;
	}

	public String getReportingResults() {
		return reportingResults;
	}

	public void setReportingResults(String reportingResults) {
		this.reportingResults = reportingResults;
	}

	public String getHeadInspectionUnit() {
		return headInspectionUnit;
	}

	public void setHeadInspectionUnit(String headInspectionUnit) {
		this.headInspectionUnit = headInspectionUnit;
	}
	
	

}
