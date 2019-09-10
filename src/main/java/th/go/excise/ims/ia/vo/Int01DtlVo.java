package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Int01DtlVo {

	/* IA_PLAN_DTL */
	private BigDecimal planDtlId;
	private BigDecimal planHdrId;
	private String inspectionWork;
	private String budgetYear;
	private String activity;
	private BigDecimal frequency;
	private String unit;
	private String activityStatus;
	private String responsiblePerson;
	private String inspector;
	private String officer;
	private String position;
	
	private Int01MonthVo monthVo = null;
	private Boolean checkBtnDtl;

	public BigDecimal getPlanDtlId() {
		return planDtlId;
	}

	public void setPlanDtlId(BigDecimal planDtlId) {
		this.planDtlId = planDtlId;
	}

	public BigDecimal getPlanHdrId() {
		return planHdrId;
	}

	public void setPlanHdrId(BigDecimal planHdrId) {
		this.planHdrId = planHdrId;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public BigDecimal getFrequency() {
		return frequency;
	}

	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Int01MonthVo getMonthVo() {
		return monthVo;
	}

	public void setMonthVo(Int01MonthVo monthVo) {
		this.monthVo = monthVo;
	}

	public Boolean getCheckBtnDtl() {
		return checkBtnDtl;
	}

	public void setCheckBtnDtl(Boolean checkBtnDtl) {
		this.checkBtnDtl = checkBtnDtl;
	}

}