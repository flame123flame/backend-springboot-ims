package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;

public class Oa040106FormVo {
	private BigDecimal oaCuslicenseId;
	private BigDecimal oaCustomerId;
	private String licenseNo;
	private Date licenseDate;
	private String operateName;
	private String operateRemark;
	private String approveName;
	private Date startDate;
	private Date endDate;
	private String offCode;
	private Date receiveDate;
	private String receiveNo;
	private String approve;
	private String licenseTypeFor;
	private String licenseTypeDesp;
	private String licenseAddress;
	private String createdFactTime;
	private Date usedDate;
	private BigDecimal money;
	private String name;
	private String companyName;
	private String identifyNo;
	private String identifyType;
	private String warehouseAddress;
	private String address;
	private String mobile;
	private List<OaAchCustomerLicenDtl> details;
	private List<OaAchCustomerLicenDtl> deletes;

	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateRemark() {
		return operateRemark;
	}

	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
	}

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveNo() {
		return receiveNo;
	}

	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getLicenseTypeFor() {
		return licenseTypeFor;
	}

	public void setLicenseTypeFor(String licenseTypeFor) {
		this.licenseTypeFor = licenseTypeFor;
	}

	public String getLicenseTypeDesp() {
		return licenseTypeDesp;
	}

	public void setLicenseTypeDesp(String licenseTypeDesp) {
		this.licenseTypeDesp = licenseTypeDesp;
	}

	public String getLicenseAddress() {
		return licenseAddress;
	}

	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress;
	}

	public String getCreatedFactTime() {
		return createdFactTime;
	}

	public void setCreatedFactTime(String createdFactTime) {
		this.createdFactTime = createdFactTime;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public List<OaAchCustomerLicenDtl> getDetails() {
		return details;
	}

	public void setDetails(List<OaAchCustomerLicenDtl> details) {
		this.details = details;
	}

	public List<OaAchCustomerLicenDtl> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<OaAchCustomerLicenDtl> deletes) {
		this.deletes = deletes;
	}
}
