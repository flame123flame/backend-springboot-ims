package th.go.excise.ims.ta.vo;

import java.time.LocalDate;

public class AuditStepVo {

	private String auditPlanCode;
	private String auditStepStatus;
	private String auditStepSubStatus;
	private String auditStepFlag;
	private String formTsCode;
	private String formTsNumber;
	private LocalDate processDate;

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

	public LocalDate getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDate processDate) {
		this.processDate = processDate;
	}

}
