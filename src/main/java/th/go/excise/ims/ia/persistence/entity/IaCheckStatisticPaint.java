
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_CHECK_STATISTIC_PAINT")
public class IaCheckStatisticPaint
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7827992498492949563L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_STATISTIC_PAINT_GEN")
    @SequenceGenerator(name = "IA_CHECK_STATISTIC_PAINT_GEN", sequenceName = "IA_CHECK_STATISTIC_PAINT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "OFFCODE")
    private String offcode;
    @Column(name = "LIC_TYPE")
    private String licType;
    @Column(name = "LIC_CODE")
    private String licCode;
    @Column(name = "LIC_NO")
    private String licNo;
    @Column(name = "LIC_NAME")
    private String licName;
    @Column(name = "LIC_FEE")
    private String licFee;
    @Column(name = "LIC_INTERIOR")
    private String licInterior;
    @Column(name = "LIC_PRICE")
    private String licPrice;
    @Column(name = "LIC_DATE")
    private Date licDate;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "EXP_DATE")
    private Date expDate;
    @Column(name = "SEND_DATE")
    private Date sendDate;
    @Column(name = "PRINT_COUNT")
    private String printCount;
    @Column(name = "NID")
    private String nid;
    @Column(name = "NEWREG_ID")
    private String newregId;
    @Column(name = "CUS_FULL_NAME")
    private String cusFullName;
    @Column(name = "FAC_FULL_NAME")
    private String facFullName;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOffcode() {
        return offcode;
    }

    public void setOffcode(String offcode) {
        this.offcode = offcode;
    }

    public String getLicType() {
        return licType;
    }

    public void setLicType(String licType) {
        this.licType = licType;
    }

    public String getLicCode() {
        return licCode;
    }

    public void setLicCode(String licCode) {
        this.licCode = licCode;
    }

    public String getLicNo() {
        return licNo;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    public String getLicName() {
        return licName;
    }

    public void setLicName(String licName) {
        this.licName = licName;
    }

    public String getLicFee() {
        return licFee;
    }

    public void setLicFee(String licFee) {
        this.licFee = licFee;
    }

    public String getLicInterior() {
        return licInterior;
    }

    public void setLicInterior(String licInterior) {
        this.licInterior = licInterior;
    }

    public String getLicPrice() {
        return licPrice;
    }

    public void setLicPrice(String licPrice) {
        this.licPrice = licPrice;
    }

    public Date getLicDate() {
        return licDate;
    }

    public void setLicDate(Date licDate) {
        this.licDate = licDate;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getPrintCount() {
        return printCount;
    }

    public void setPrintCount(String printCount) {
        this.printCount = printCount;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNewregId() {
        return newregId;
    }

    public void setNewregId(String newregId) {
        this.newregId = newregId;
    }

    public String getCusFullName() {
        return cusFullName;
    }

    public void setCusFullName(String cusFullName) {
        this.cusFullName = cusFullName;
    }

    public String getFacFullName() {
        return facFullName;
    }

    public void setFacFullName(String facFullName) {
        this.facFullName = facFullName;
    }

}
