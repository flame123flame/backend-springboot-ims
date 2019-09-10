package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;

public class Oa0201001Vo extends OaCustomerLicen{

	private String officeName1;
	private String officeName2;
	private BigDecimal licensePlanId;
	
	public BigDecimal getLicensePlanId() {
		return licensePlanId;
	}
	public void setLicensePlanId(BigDecimal licensePlanId) {
		this.licensePlanId = licensePlanId;
	}
	public String getOfficeName1() {
		return officeName1;
	}
	public String getOfficeName2() {
		return officeName2;
	}
	public void setOfficeName1(String officeName1) {
		this.officeName1 = officeName1;
	}
	public void setOfficeName2(String officeName2) {
		this.officeName2 = officeName2;
	}
	
	
}
