package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

public class ServicePaperVo {

	private String auditPlanCode;
	private String newRegId;
	private String dutyGroupId;
	private String startDate;
	private String endDate;
	private String paperSvNumber;
	private DataTableAjax<Object> dataTableAjax;

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPaperSvNumber() {
		return paperSvNumber;
	}

	public void setPaperSvNumber(String paperSvNumber) {
		this.paperSvNumber = paperSvNumber;
	}

	public DataTableAjax<Object> getDataTableAjax() {
		return dataTableAjax;
	}

	public void setDataTableAjax(DataTableAjax<Object> dataTableAjax) {
		this.dataTableAjax = dataTableAjax;
	}

}
