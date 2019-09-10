
package th.go.excise.ims.oa.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "OA_ACH_CUSTOMER_LICEN")
public class OaAchCustomerLicen
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ACH_CUSTOMER_LICEN_GEN")
    @SequenceGenerator(name = "OA_ACH_CUSTOMER_LICEN_GEN", sequenceName = "OA_ACH_CUSTOMER_LICEN_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSLICENSE_ID")
    private BigDecimal oaCuslicenseId;
    @Column(name = "LICENSE_NO")
    private String licenseNo;
    @Column(name = "LICENSE_DATE")
    private Date licenseDate;
    @Column(name = "OPERATE_NAME")
    private String operateName;
    @Column(name = "OPERATE_REMARK")
    private String operateRemark;
    @Column(name = "APPROVE_NAME")
    private String approveName;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;
    @Column(name = "RECEIVE_NO")
    private String receiveNo;
    @Column(name = "APPROVE")
    private String approve;
    @Column(name = "LICENSE_TYPE_FOR")
    private String licenseTypeFor;
    @Column(name = "LICENSE_TYPE_DESP")
    private String licenseTypeDesp;
    @Column(name = "LICENSE_ADDRESS")
    private String licenseAddress;
    @Column(name = "CREATED_FACT_TIME")
    private String createdFactTime;
    @Column(name = "USED_DATE")
    private Date usedDate;
    @Column(name = "MONEY")
	private BigDecimal money;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "IDENTIFY_NO")
    private String identifyNo;
    @Column(name = "IDENTIFY_TYPE")
    private String identifyType;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE")
    private String mobile;
    
    
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

}
