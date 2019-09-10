package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaRiskProEfKpiVo {
	private BigDecimal id;
	private BigDecimal peId;
	private String projectId;
	private BigDecimal tsId;
	private String taskId;
	private String kpiId;
	private String kpiCode;
	private String kpiName;
	private String kpiActivityCode;
	private String kpiActivityName;
	private BigDecimal kpiTargetAmount;
	private BigDecimal kpiExpenseActualAmount;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getPeId() {
		return peId;
	}

	public void setPeId(BigDecimal peId) {
		this.peId = peId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getTsId() {
		return tsId;
	}

	public void setTsId(BigDecimal tsId) {
		this.tsId = tsId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getKpiCode() {
		return kpiCode;
	}

	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getKpiActivityCode() {
		return kpiActivityCode;
	}

	public void setKpiActivityCode(String kpiActivityCode) {
		this.kpiActivityCode = kpiActivityCode;
	}

	public String getKpiActivityName() {
		return kpiActivityName;
	}

	public void setKpiActivityName(String kpiActivityName) {
		this.kpiActivityName = kpiActivityName;
	}

	public BigDecimal getKpiTargetAmount() {
		return kpiTargetAmount;
	}

	public void setKpiTargetAmount(BigDecimal kpiTargetAmount) {
		this.kpiTargetAmount = kpiTargetAmount;
	}

	public BigDecimal getKpiExpenseActualAmount() {
		return kpiExpenseActualAmount;
	}

	public void setKpiExpenseActualAmount(BigDecimal kpiExpenseActualAmount) {
		this.kpiExpenseActualAmount = kpiExpenseActualAmount;
	}

}
