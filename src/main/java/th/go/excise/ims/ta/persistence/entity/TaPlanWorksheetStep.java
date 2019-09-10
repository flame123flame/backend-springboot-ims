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
@Table(name = "TA_PLAN_WORKSHEET_STEP")
public class TaPlanWorksheetStep extends BaseEntity {

	private static final long serialVersionUID = 1157973026607522432L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_STEP_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_STEP_GEN", sequenceName = "TA_PLAN_WORKSHEET_STEP_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_STEP_ID")
	private Long planWorksheetStepId;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "AUDIT_STEP_STATUS")
	private String auditStepStatus;
	@Column(name = "AUDIT_STEP_SUB_STATUS")
	private String auditStepSubStatus;
	@Column(name = "AUDIT_STEP_FLAG")
	private String auditStepFlag;
	@Column(name = "FORM_TS_CODE")
	private String formTsCode;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "PROCESS_DATE")
	private LocalDateTime processDate;

	public Long getPlanWorksheetStepId() {
		return planWorksheetStepId;
	}

	public void setPlanWorksheetStepId(Long planWorksheetStepId) {
		this.planWorksheetStepId = planWorksheetStepId;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getAuditStepStatus() {
		return auditStepStatus;
	}

	public void setAuditStepStatus(String auditStepStatus) {
		this.auditStepStatus = auditStepStatus;
	}

	public String getAuditStepSubStatus() {
		return auditStepSubStatus;
	}

	public void setAuditStepSubStatus(String auditStepSubStatus) {
		this.auditStepSubStatus = auditStepSubStatus;
	}

	public String getAuditStepFlag() {
		return auditStepFlag;
	}

	public void setAuditStepFlag(String auditStepFlag) {
		this.auditStepFlag = auditStepFlag;
	}

	public String getFormTsCode() {
		return formTsCode;
	}

	public void setFormTsCode(String formTsCode) {
		this.formTsCode = formTsCode;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public LocalDateTime getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDateTime processDate) {
		this.processDate = processDate;
	}

}
