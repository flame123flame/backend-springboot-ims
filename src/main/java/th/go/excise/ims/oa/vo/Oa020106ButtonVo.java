package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

public class Oa020106ButtonVo {
	private BigDecimal oaLubricantsId;
	private BigDecimal oaPlanId;
	private BigDecimal oaLubricantsDtlId;
	private BigDecimal oaCuslicenseId;
	private String licenseNo;
	private String licenseType;
	private String identifyType;
	private String oldCustomer;

	public String getOldCustomer() {
		return oldCustomer;
	}

	public void setOldCustomer(String oldCustomer) {
		this.oldCustomer = oldCustomer;
	}

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public BigDecimal getOaPlanId() {
		return oaPlanId;
	}

	public void setOaPlanId(BigDecimal oaPlanId) {
		this.oaPlanId = oaPlanId;
	}

	public BigDecimal getOaLubricantsId() {
		return oaLubricantsId;
	}

	public void setOaLubricantsId(BigDecimal oaLubricantsId) {
		this.oaLubricantsId = oaLubricantsId;
	}

	public BigDecimal getOaLubricantsDtlId() {
		return oaLubricantsDtlId;
	}

	public void setOaLubricantsDtlId(BigDecimal oaLubricantsDtlId) {
		this.oaLubricantsDtlId = oaLubricantsDtlId;
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
}
