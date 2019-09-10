package th.go.excise.ims.ia.vo;

public class Int061401FilterVo {
	private String officeCode;
	private String regDateStart;
	private String regDateEnd;
	private String auditTxinsurNo;
	private Boolean flagSearch;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getRegDateStart() {
		return regDateStart;
	}

	public void setRegDateStart(String regDateStart) {
		this.regDateStart = regDateStart;
	}

	public String getRegDateEnd() {
		return regDateEnd;
	}

	public void setRegDateEnd(String regDateEnd) {
		this.regDateEnd = regDateEnd;
	}

	public String getAuditTxinsurNo() {
		return auditTxinsurNo;
	}

	public void setAuditTxinsurNo(String auditTxinsurNo) {
		this.auditTxinsurNo = auditTxinsurNo;
	}

	public Boolean getFlagSearch() {
		return flagSearch;
	}

	public void setFlagSearch(Boolean flagSearch) {
		this.flagSearch = flagSearch;
	}
}
