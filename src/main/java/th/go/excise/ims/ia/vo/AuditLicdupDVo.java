package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class AuditLicdupDVo {

	private Long auditLicdupDSeq;
	private String auditLicdupNo;
	private String newRegId;
	private String cusFullname;
	private String licType;
	private Integer runCheck;
	private String licNo;
	private String licDate;
	private BigDecimal printCount;

	public Long getAuditLicdupDSeq() {
		return auditLicdupDSeq;
	}

	public void setAuditLicdupDSeq(Long auditLicdupDSeq) {
		this.auditLicdupDSeq = auditLicdupDSeq;
	}

	public String getAuditLicdupNo() {
		return auditLicdupNo;
	}

	public void setAuditLicdupNo(String auditLicdupNo) {
		this.auditLicdupNo = auditLicdupNo;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public Integer getRunCheck() {
		return runCheck;
	}

	public void setRunCheck(Integer runCheck) {
		this.runCheck = runCheck;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getLicDate() {
		return licDate;
	}

	public void setLicDate(String licDate) {
		this.licDate = licDate;
	}

	public BigDecimal getPrintCount() {
		return printCount;
	}

	public void setPrintCount(BigDecimal printCount) {
		this.printCount = printCount;
	}

}
