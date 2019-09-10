package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Oa0106Vo {
	
	private BigDecimal customerId;
	private BigDecimal oaCusLicenseId;
	private String companyName;
	private String address;
	private String identityType;
	private String licenseType;
	private Date startDate;
	private Date endDate;
	private String approve;
	private String licenseNo;
	private String identifyNo;
	private String warehouseAddress;
	private String telephone;
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public BigDecimal getOaCusLicenseId() {
		return oaCusLicenseId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getAddress() {
		return address;
	}
	public String getIdentityType() {
		return identityType;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getApprove() {
		return approve;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public void setOaCusLicenseId(BigDecimal oaCusLicenseId) {
		this.oaCusLicenseId = oaCusLicenseId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public String getIdentifyNo() {
		return identifyNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}
	public String getWarehouseAddress() {
		return warehouseAddress;
	}
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	

}
