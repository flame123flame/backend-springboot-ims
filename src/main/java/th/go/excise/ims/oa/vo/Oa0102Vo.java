package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

public class Oa0102Vo {
	private BigDecimal oaPlanId;
	private BigDecimal oaLicensePlanId;
	private BigDecimal oaCuslicenseId;
	private String companyName;
	private String address;

	public BigDecimal getOaPlanId() {
		return oaPlanId;
	}

	public void setOaPlanId(BigDecimal oaPlanId) {
		this.oaPlanId = oaPlanId;
	}

	public BigDecimal getOaCuslicenseId() {
		return oaCuslicenseId;
	}

	public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
		this.oaCuslicenseId = oaCuslicenseId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getOaLicensePlanId() {
		return oaLicensePlanId;
	}

	public void setOaLicensePlanId(BigDecimal oaLicensePlanId) {
		this.oaLicensePlanId = oaLicensePlanId;
	}

}
