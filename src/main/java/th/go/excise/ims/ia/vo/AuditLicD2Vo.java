package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuditLicD2Vo {

	private Long auditLicD2Seq;
	private String auditLicNo;
	private String taxCode;
	private String licName;
	private BigDecimal licPrice;
	private Long licCount;
	private String auditCheck;
	private String licT2Remark;

	

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}



	public Long getAuditLicD2Seq() {
		return auditLicD2Seq;
	}



	public void setAuditLicD2Seq(Long auditLicD2Seq) {
		this.auditLicD2Seq = auditLicD2Seq;
	}



	public String getAuditLicNo() {
		return auditLicNo;
	}



	public void setAuditLicNo(String auditLicNo) {
		this.auditLicNo = auditLicNo;
	}



	public String getTaxCode() {
		return taxCode;
	}



	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}



	public String getLicName() {
		return licName;
	}



	public void setLicName(String licName) {
		this.licName = licName;
	}



	public BigDecimal getLicPrice() {
		return licPrice;
	}



	public void setLicPrice(BigDecimal licPrice) {
		this.licPrice = licPrice;
	}



	public Long getLicCount() {
		return licCount;
	}



	public void setLicCount(Long licCount) {
		this.licCount = licCount;
	}



	public String getAuditCheck() {
		return auditCheck;
	}



	public void setAuditCheck(String auditCheck) {
		this.auditCheck = auditCheck;
	}



	public String getLicT2Remark() {
		return licT2Remark;
	}



	public void setLicT2Remark(String licT2Remark) {
		this.licT2Remark = licT2Remark;
	}

}
