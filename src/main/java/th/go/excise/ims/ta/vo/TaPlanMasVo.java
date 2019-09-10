package th.go.excise.ims.ta.vo;

import th.go.excise.ims.ta.persistence.entity.TaPlanMas;

public class TaPlanMasVo extends TaPlanMas {
	
	private String auditCount;
	private String auditMonth;
	private String sumCount;
	private String sumMonth;
	
	
	public String getAuditCount() {
		return auditCount;
	}
	public void setAuditCount(String auditCount) {
		this.auditCount = auditCount;
	}
	public String getAuditMonth() {
		return auditMonth;
	}
	public void setAuditMonth(String auditMonth) {
		this.auditMonth = auditMonth;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getSumMonth() {
		return sumMonth;
	}
	public void setSumMonth(String sumMonth) {
		this.sumMonth = sumMonth;
	}
	
	

}
