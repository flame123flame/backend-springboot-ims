package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int061402FilterVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1281712285277755352L;
	private String officeCode;
	private String regDateStart;
	private String regDateEnd;
	private String auditTxinsurNo;
	private String flagSearch;

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

	public String getFlagSearch() {
		return flagSearch;
	}

	public void setFlagSearch(String flagSearch) {
		this.flagSearch = flagSearch;
	}

}