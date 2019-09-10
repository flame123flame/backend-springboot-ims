package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

public class Oa040106ButtonVo {
	private BigDecimal oaAlcoholId;
	private BigDecimal oaPlanId;
	private BigDecimal oaAlcoholDtlId;
	private BigDecimal oaCuslicenseId;
	private String licenseNo;
	private String licenseType;
	private String identifyType;
	private String oldCustomer;

	public BigDecimal getOaAlcoholId() {
		return oaAlcoholId;
	}

	public void setOaAlcoholId(BigDecimal oaAlcoholId) {
		this.oaAlcoholId = oaAlcoholId;
	}

	public BigDecimal getOaPlanId() {
		return oaPlanId;
	}

	public void setOaPlanId(BigDecimal oaPlanId) {
		this.oaPlanId = oaPlanId;
	}

	public BigDecimal getOaAlcoholDtlId() {
		return oaAlcoholDtlId;
	}

	public void setOaAlcoholDtlId(BigDecimal oaAlcoholDtlId) {
		this.oaAlcoholDtlId = oaAlcoholDtlId;
	}

	public BigDecimal getOaCuslicenseId() {
		return oaCuslicenseId;
	}

	public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
		this.oaCuslicenseId = oaCuslicenseId;
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

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getOldCustomer() {
		return oldCustomer;
	}

	public void setOldCustomer(String oldCustomer) {
		this.oldCustomer = oldCustomer;
	}
}
