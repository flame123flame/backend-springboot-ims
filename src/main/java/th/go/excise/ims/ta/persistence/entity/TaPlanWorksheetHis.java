package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PLAN_WORKSHEET_HIS")
public class TaPlanWorksheetHis extends BaseEntity {

	private static final long serialVersionUID = -7077546493758474990L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_HIS_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_HIS_GEN", sequenceName = "TA_PLAN_WORKSHEET_HIS_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_HIS_ID")
	private Long planWorksheetHisId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "SYSTEM_TYPE")
	private String systemType;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;
	@Column(name = "AUDIT_TYPE")
	private String auditType;
	@Column(name = "AUDIT_START_DATE")
	private LocalDate auditStartDate;
	@Column(name = "AUDIT_END_DATE")
	private LocalDate auditEndDate;
	@Column(name = "AUDIT_RESULT_DESC")
	private String auditResultDesc;

	public Long getPlanWorksheetHisId() {
		return planWorksheetHisId;
	}

	public void setPlanWorksheetHisId(Long planWorksheetHisId) {
		this.planWorksheetHisId = planWorksheetHisId;
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

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
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

	public String getAuditResultDesc() {
		return auditResultDesc;
	}

	public void setAuditResultDesc(String auditResultDesc) {
		this.auditResultDesc = auditResultDesc;
	}

}
