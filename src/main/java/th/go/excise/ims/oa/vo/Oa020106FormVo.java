package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;

public class Oa020106FormVo {
	private BigDecimal oaCuslicenseId;
	private String name;
	private String companyName;
	private String identifyNo;
	private String identifyType;
	private String licenseType;
	private String licenseNo;
	private Date licenseDate;
	private String licenseTypeFor;
	private String licenseTypeDesp;
	private String bankGuarantee;
	private String bankGuaranteeNo;
	private Date bankGuaranteeDate;
	private String oldLicenseYear;
	private String operateName;
	private String operateRemark;
	private Date startDate;
	private Date endDate;
	private String offCode;
	private Date receiveDate;
	private String receiveNo;
	private String approveName;
	private String approve;
	private String oldCustomer;
	private String mobile;
	private String address;
	private String warehouseAddress;
	private String sectorName;
	private String areaName;
	private List<OaCustomerLicenDetail> details;
	private List<OaCustomerLicenDetail> deletes;

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public BigDecimal getOaCuslicenseId() {
		return oaCuslicenseId;
	}

	public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
		this.oaCuslicenseId = oaCuslicenseId;
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

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
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

	public String getBankGuarantee() {
		return bankGuarantee;
	}

	public void setBankGuarantee(String bankGuarantee) {
		this.bankGuarantee = bankGuarantee;
	}

	public String getBankGuaranteeNo() {
		return bankGuaranteeNo;
	}

	public void setBankGuaranteeNo(String bankGuaranteeNo) {
		this.bankGuaranteeNo = bankGuaranteeNo;
	}

	public Date getBankGuaranteeDate() {
		return bankGuaranteeDate;
	}

	public void setBankGuaranteeDate(Date bankGuaranteeDate) {
		this.bankGuaranteeDate = bankGuaranteeDate;
	}

	public String getOldLicenseYear() {
		return oldLicenseYear;
	}

	public void setOldLicenseYear(String oldLicenseYear) {
		this.oldLicenseYear = oldLicenseYear;
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

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getOldCustomer() {
		return oldCustomer;
	}

	public void setOldCustomer(String oldCustomer) {
		this.oldCustomer = oldCustomer;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public List<OaCustomerLicenDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OaCustomerLicenDetail> details) {
		this.details = details;
	}

	public List<OaCustomerLicenDetail> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<OaCustomerLicenDetail> deletes) {
		this.deletes = deletes;
	}
}
