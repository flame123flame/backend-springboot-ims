
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
@Table(name = "IA_FOLLOW_RECOMMEND_HDR")
public class IaFollowRecommendHdr extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2422533997557380617L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_FOLLOW_RECOMMEND_HDR_GEN")
	@SequenceGenerator(name = "IA_FOLLOW_RECOMMEND_HDR_GEN", sequenceName = "IA_FOLLOW_RECOMMEND_HDR_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "PROJECT_CODE")
	private String projectCode;
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Column(name = "SECTOR")
	private String sector;
	@Column(name = "AREA")
	private String area;
	@Column(name = "SYSTEM_CODE")
	private String systemCode;
	@Column(name = "SYSTEM_NAME")
	private String systemName;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "CHECK_TYPE")
	private String checkType;
	@Column(name = "REPORT_DATE")
	private Date reportDate;
	@Column(name = "APPROVE_DATE")
	private Date approveDate;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "DATE_CLOSED_WORK")
	private Date dateClosedWork;
	@Column(name = "NOTE_CLOSED_WORK")
	private String noteClosedWork;
	@Column(name = "INSPECTION_WORK")
	private String inspectionWork;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "ID_CONCLUDE_FOLLOW_HDR")
	private BigDecimal idConcludeFollowHdr;

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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
