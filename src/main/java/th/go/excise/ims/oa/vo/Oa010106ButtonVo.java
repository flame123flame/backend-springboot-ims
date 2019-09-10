package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

public class Oa010106ButtonVo {
	private BigDecimal oaHydrocarbId;
	private BigDecimal oaPlanId;
	private BigDecimal oaHydrocarbDtlId;
	private BigDecimal oaCuslicenseId;
	private BigDecimal oaCustomerId;
	private String licenseNo;
	private String licenseType;
	private String oldCustomer;

	public String getOldCustomer() {
		return oldCustomer;
	}

	public void setOldCustomer(String oldCustomer) {
		this.oldCustomer = oldCustomer;
	}

	public BigDecimal getOaHydrocarbId() {
		return oaHydrocarbId;
	}

	public void setOaHydrocarbId(BigDecimal oaHydrocarbId) {
		this.oaHydrocarbId = oaHydrocarbId;
	}

	public BigDecimal getOaPlanId() {
		return oaPlanId;
	}

	public void setOaPlanId(BigDecimal oaPlanId) {
		this.oaPlanId = oaPlanId;
	}

	public BigDecimal getOaHydrocarbDtlId() {
		return oaHydrocarbDtlId;
	}

	public void setOaHydrocarbDtlId(BigDecimal oaHydrocarbDtlId) {
		this.oaHydrocarbDtlId = oaHydrocarbDtlId;
	}

	public BigDecimal getOaCuslicenseId() {
		return oaCuslicenseId;
	}

	public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
		this.oaCuslicenseId = oaCuslicenseId;
	}

	public BigDecimal getOaCustomerId() {
		return oaCustomerId;
	}

	public void setOaCustomerId(BigDecimal oaCustomerId) {
		this.oaCustomerId = oaCustomerId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
}