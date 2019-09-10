
package th.go.excise.ims.oa.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "OA_ALCOHOL_DTL")
public class OaAlcoholDtl
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_DTL_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_DTL_GEN", sequenceName = "OA_ALCOHOL_DTL_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_DTL_ID")
    private BigDecimal oaAlcoholDtlId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "LICENSE_MENUFAC")
    private String licenseMenufac;
    @Column(name = "LICENSE_MENUFAC_REMARK")
    private String licenseMenufacRemark;
    @Column(name = "LICENSE_TYPE2")
    private String licenseType2;
    @Column(name = "LICENSE_TYPE2_REMARK")
    private String licenseType2Remark;
    @Column(name = "ACH_DEGREE")
    private String achDegree;
    @Column(name = "ACH_DEGREE_REMARK")
    private String achDegreeRemark;
    @Column(name = "ACH_CAPACITY")
    private String achCapacity;
    @Column(name = "ACH_CAPACITY_REMARK")
    private String achCapacityRemark;
    @Column(name = "ACH_APPROVE")
    private String achApprove;
    @Column(name = "ACH_APPROVE_REMARK")
    private String achApproveRemark;
    @Column(name = "ACH_PRICE")
    private String achPrice;
    @Column(name = "ACH_PRICE_REMARK")
    private String achPriceRemark;
    @Column(name = "PLACE_STATUS")
    private String placeStatus;
    @Column(name = "PLACE_STATUS_REMARK")
    private String placeStatusRemark;
    @Column(name = "EQM_TANK")
    private String eqmTank;
    @Column(name = "EQM_TANK_NUM")
    private BigDecimal eqmTankNum;
    @Column(name = "EQM_DISTIL")
    private String eqmDistil;
    @Column(name = "EQM_DISTIL_NUM")
    private BigDecimal eqmDistilNum;
    @Column(name = "EQM_PACKING")
    private String eqmPacking;
    @Column(name = "EQM_PACKING_NUM")
    private BigDecimal eqmPackingNum;
    @Column(name = "AUDIT_07_01")
    private String audit0701;
    @Column(name = "AUDIT_07_01_REMARK")
    private String audit0701Remark;
    @Column(name = "AUDIT_07_02_1")
    private String audit07021;
    @Column(name = "AUDIT_07_02_1_REMARK")
    private String audit07021Remark;
    @Column(name = "AUDIT_07_02_2")
    private String audit07022;
    @Column(name = "AUDIT_07_02_2_REMARK")
    private String audit07022Remark;
    @Column(name = "EQUIPMENT_USED")
    private String equipmentUsed;
    @Column(name = "EQUIPMENT_USED_REMARK")
    private String equipmentUsedRemark;
    @Column(name = "AUDIT_OTHER")
    private String auditOther;
    @Column(name = "AUDIT_SUGGESTION")
    private String auditSuggestion;
    @Column(name = "ACH_STAMP")
    private String achStamp;
    @Column(name = "ACH_STAMP_REMARK")
    private String achStampRemark;
    @Column(name = "EQM_TANK_STATUS")
    private String eqmTankStatus;
    @Column(name = "EQM_DISTIL_STATUS")
    private String eqmDistilStatus;
    @Column(name = "EQM_PACKING_STATUS")
    private String eqmPackingStatus;

    public String getEqmTankStatus() {
		return eqmTankStatus;
	}

	public void setEqmTankStatus(String eqmTankStatus) {
		this.eqmTankStatus = eqmTankStatus;
	}

	public String getEqmDistilStatus() {
		return eqmDistilStatus;
	}

	public void setEqmDistilStatus(String eqmDistilStatus) {
		this.eqmDistilStatus = eqmDistilStatus;
	}

	public String getEqmPackingStatus() {
		return eqmPackingStatus;
	}

	public void setEqmPackingStatus(String eqmPackingStatus) {
		this.eqmPackingStatus = eqmPackingStatus;
	}

	public String getAchStamp() {
		return achStamp;
	}

	public void setAchStamp(String achStamp) {
		this.achStamp = achStamp;
	}

	public String getAchStampRemark() {
		return achStampRemark;
	}

	public void setAchStampRemark(String achStampRemark) {
		this.achStampRemark = achStampRemark;
	}

	public BigDecimal getOaAlcoholDtlId() {
        return oaAlcoholDtlId;
    }

    public void setOaAlcoholDtlId(BigDecimal oaAlcoholDtlId) {
        this.oaAlcoholDtlId = oaAlcoholDtlId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public String getLicenseMenufac() {
        return licenseMenufac;
    }

    public void setLicenseMenufac(String licenseMenufac) {
        this.licenseMenufac = licenseMenufac;
    }

    public String getLicenseMenufacRemark() {
        return licenseMenufacRemark;
    }

    public void setLicenseMenufacRemark(String licenseMenufacRemark) {
        this.licenseMenufacRemark = licenseMenufacRemark;
    }

    public String getLicenseType2() {
        return licenseType2;
    }

    public void setLicenseType2(String licenseType2) {
        this.licenseType2 = licenseType2;
    }

    public String getLicenseType2Remark() {
        return licenseType2Remark;
    }

    public void setLicenseType2Remark(String licenseType2Remark) {
        this.licenseType2Remark = licenseType2Remark;
    }

    public String getAchDegree() {
        return achDegree;
    }

    public void setAchDegree(String achDegree) {
        this.achDegree = achDegree;
    }

    public String getAchDegreeRemark() {
        return achDegreeRemark;
    }

    public void setAchDegreeRemark(String achDegreeRemark) {
        this.achDegreeRemark = achDegreeRemark;
    }

    public String getAchCapacity() {
        return achCapacity;
    }

    public void setAchCapacity(String achCapacity) {
        this.achCapacity = achCapacity;
    }

    public String getAchCapacityRemark() {
        return achCapacityRemark;
    }

    public void setAchCapacityRemark(String achCapacityRemark) {
        this.achCapacityRemark = achCapacityRemark;
    }

    public String getAchApprove() {
        return achApprove;
    }

    public void setAchApprove(String achApprove) {
        this.achApprove = achApprove;
    }

    public String getAchApproveRemark() {
        return achApproveRemark;
    }

    public void setAchApproveRemark(String achApproveRemark) {
        this.achApproveRemark = achApproveRemark;
    }

    public String getAchPrice() {
        return achPrice;
    }

    public void setAchPrice(String achPrice) {
        this.achPrice = achPrice;
    }

    public String getAchPriceRemark() {
        return achPriceRemark;
    }

    public void setAchPriceRemark(String achPriceRemark) {
        this.achPriceRemark = achPriceRemark;
    }

    public String getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(String placeStatus) {
        this.placeStatus = placeStatus;
    }

    public String getPlaceStatusRemark() {
        return placeStatusRemark;
    }

    public void setPlaceStatusRemark(String placeStatusRemark) {
        this.placeStatusRemark = placeStatusRemark;
    }

    public String getEqmTank() {
        return eqmTank;
    }

    public void setEqmTank(String eqmTank) {
        this.eqmTank = eqmTank;
    }

    public BigDecimal getEqmTankNum() {
        return eqmTankNum;
    }

    public void setEqmTankNum(BigDecimal eqmTankNum) {
        this.eqmTankNum = eqmTankNum;
    }

    public String getEqmDistil() {
        return eqmDistil;
    }

    public void setEqmDistil(String eqmDistil) {
        this.eqmDistil = eqmDistil;
    }

    public BigDecimal getEqmDistilNum() {
        return eqmDistilNum;
    }

    public void setEqmDistilNum(BigDecimal eqmDistilNum) {
        this.eqmDistilNum = eqmDistilNum;
    }

    public String getEqmPacking() {
        return eqmPacking;
    }

    public void setEqmPacking(String eqmPacking) {
        this.eqmPacking = eqmPacking;
    }

    public BigDecimal getEqmPackingNum() {
        return eqmPackingNum;
    }

    public void setEqmPackingNum(BigDecimal eqmPackingNum) {
        this.eqmPackingNum = eqmPackingNum;
    }

    public String getAudit0701() {
        return audit0701;
    }

    public void setAudit0701(String audit0701) {
        this.audit0701 = audit0701;
    }

    public String getAudit0701Remark() {
        return audit0701Remark;
    }

    public void setAudit0701Remark(String audit0701Remark) {
        this.audit0701Remark = audit0701Remark;
    }

    public String getAudit07021() {
        return audit07021;
    }

    public void setAudit07021(String audit07021) {
        this.audit07021 = audit07021;
    }

    public String getAudit07021Remark() {
        return audit07021Remark;
    }

    public void setAudit07021Remark(String audit07021Remark) {
        this.audit07021Remark = audit07021Remark;
    }

    public String getAudit07022() {
        return audit07022;
    }

    public void setAudit07022(String audit07022) {
        this.audit07022 = audit07022;
    }

    public String getAudit07022Remark() {
        return audit07022Remark;
    }

    public void setAudit07022Remark(String audit07022Remark) {
        this.audit07022Remark = audit07022Remark;
    }

    public String getEquipmentUsed() {
        return equipmentUsed;
    }

    public void setEquipmentUsed(String equipmentUsed) {
        this.equipmentUsed = equipmentUsed;
    }

    public String getEquipmentUsedRemark() {
        return equipmentUsedRemark;
    }

    public void setEquipmentUsedRemark(String equipmentUsedRemark) {
        this.equipmentUsedRemark = equipmentUsedRemark;
    }

    public String getAuditOther() {
        return auditOther;
    }

    public void setAuditOther(String auditOther) {
        this.auditOther = auditOther;
    }

    public String getAuditSuggestion() {
        return auditSuggestion;
    }

    public void setAuditSuggestion(String auditSuggestion) {
        this.auditSuggestion = auditSuggestion;
    }

}
