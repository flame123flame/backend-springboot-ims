package th.go.excise.ims.ta.vo;

public class YearMonthVo {

	private String yearMonthStart;
	private String yearMonthEnd;
	private Integer monthTotal;
	private Integer monthStart;
	private String condSubCapitalFlag;
	private String condSubRiskFlag;
	private String condSubNoAuditFlag;
	private String worksheetStatus;
	private String yearCondSubNoAudit;
	private Integer countGroup;
	private Boolean isDisabled = true;

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Integer getCountGroup() {
		return countGroup;
	}

	public void setCountGroup(Integer countGroup) {
		this.countGroup = countGroup;
	}

	public String getYearCondSubNoAudit() {
		return yearCondSubNoAudit;
	}

	public void setYearCondSubNoAudit(String yearCondSubNoAudit) {
		this.yearCondSubNoAudit = yearCondSubNoAudit;
	}

	public String getWorksheetStatus() {
		return worksheetStatus;
	}

	public void setWorksheetStatus(String worksheetStatus) {
		this.worksheetStatus = worksheetStatus;
	}

	public String getCondSubCapitalFlag() {
		return condSubCapitalFlag;
	}

	public void setCondSubCapitalFlag(String condSubCapitalFlag) {
		this.condSubCapitalFlag = condSubCapitalFlag;
	}

	public String getCondSubRiskFlag() {
		return condSubRiskFlag;
	}

	public void setCondSubRiskFlag(String condSubRiskFlag) {
		this.condSubRiskFlag = condSubRiskFlag;
	}

	public String getCondSubNoAuditFlag() {
		return condSubNoAuditFlag;
	}

	public void setCondSubNoAuditFlag(String condSubNoAuditFlag) {
		this.condSubNoAuditFlag = condSubNoAuditFlag;
	}

	public String getYearMonthStart() {
		return yearMonthStart;
	}

	public void setYearMonthStart(String yearMonthStart) {
		this.yearMonthStart = yearMonthStart;
	}

	public String getYearMonthEnd() {
		return yearMonthEnd;
	}

	public void setYearMonthEnd(String yearMonthEnd) {
		this.yearMonthEnd = yearMonthEnd;
	}

	public Integer getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(Integer monthTotal) {
		this.monthTotal = monthTotal;
	}

	public Integer getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(Integer monthStart) {
		this.monthStart = monthStart;
	}

}
