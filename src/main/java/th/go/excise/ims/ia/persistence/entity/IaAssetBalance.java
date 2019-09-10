
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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_ASSET_BALANCE")
public class IaAssetBalance extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2091508691111850321L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ASSET_BALANCE_GEN")
	@SequenceGenerator(name = "IA_ASSET_BALANCE_GEN", sequenceName = "IA_ASSET_BALANCE_SEQ", allocationSize = 1)
	@Column(name = "ASSET_BALANCE_ID")
	private BigDecimal assetBalanceId;
	@Column(name = "EXCISE_DEPARTMENT")
	private String exciseDepartment;
	@Column(name = "EXCISE_REGION")
	private String exciseRegion;
	@Column(name = "EXCISE_DISTRICT")
	private String exciseDistrict;
	@Column(name = "GOVERNMENT_SECTOR")
	private String governmentSector;
	@Column(name = "INSTITUTE")
	private String institute;
	@Column(name = "ASSET_TYPE")
	private String assetType;
	@Column(name = "ASSET_CODE")
	private String assetCode;
	@Column(name = "ASSET_FEATURE")
	private String assetFeature;
	@Column(name = "ASSET_MODEL")
	private String assetModel;
	@Column(name = "PIC_ADDRESS")
	private String picAddress;
	@Column(name = "VENDORS_NAME")
	private String vendorsName;
	@Column(name = "VENDORS_ADDRESS")
	private String vendorsAddress;
	@Column(name = "VENDORS_PHONE")
	private String vendorsPhone;
	@Column(name = "TYPE_COST")
	private String typeCost;
	@Column(name = "ACQUISITION")
	private String acquisition;
	@Column(name = "DOCUMENT_DATE")
	private Date documentDate;
	@Column(name = "DOCUMENT_NO")
	private String documentNo;
	@Column(name = "ASSET_DESCRIPTION")
	private String assetDescription;
	@Column(name = "ASSET_AMOUNT")
	private BigDecimal assetAmount;
	@Column(name = "UNIT_PRICE_ASSET")
	private BigDecimal unitPriceAsset;
	@Column(name = "TOTLE_PRICE_ASSET")
	private BigDecimal totlePriceAsset;
	@Column(name = "LIFETIME_ASSET")
	private BigDecimal lifetimeAsset;
	@Column(name = "DATE_OF_MANUFACTURE")
	private Date dateOfManufacture;
	@Column(name = "DEPRECIATION_RATE")
	private BigDecimal depreciationRate;
	@Column(name = "ANNUAL_DEPRECIATION")
	private BigDecimal annualDepreciation;
	@Column(name = "ACCUMULATED_DEPRECIATION")
	private BigDecimal accumulatedDepreciation;
	@Column(name = "NET_VALUE")
	private BigDecimal netValue;
	@Column(name = "ASSET_NOTE")
	private String assetNote;

	public BigDecimal getAssetBalanceId() {
		return assetBalanceId;
	}

	public void setAssetBalanceId(BigDecimal assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

	public String getExciseDepartment() {
		return exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseRegion() {
		return exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public String getExciseDistrict() {
		return exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getGovernmentSector() {
		return governmentSector;
	}

	public void setGovernmentSector(String governmentSector) {
		this.governmentSector = governmentSector;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetFeature() {
		return assetFeature;
	}

	public void setAssetFeature(String assetFeature) {
		this.assetFeature = assetFeature;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public String getVendorsName() {
		return vendorsName;
	}

	public void setVendorsName(String vendorsName) {
		this.vendorsName = vendorsName;
	}

	public String getVendorsAddress() {
		return vendorsAddress;
	}

	public void setVendorsAddress(String vendorsAddress) {
		this.vendorsAddress = vendorsAddress;
	}

	public String getVendorsPhone() {
		return vendorsPhone;
	}

	public void setVendorsPhone(String vendorsPhone) {
		this.vendorsPhone = vendorsPhone;
	}

	public String getTypeCost() {
		return typeCost;
	}

	public void setTypeCost(String typeCost) {
		this.typeCost = typeCost;
	}

	public String getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public BigDecimal getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(BigDecimal assetAmount) {
		this.assetAmount = assetAmount;
	}

	public BigDecimal getUnitPriceAsset() {
		return unitPriceAsset;
	}

	public void setUnitPriceAsset(BigDecimal unitPriceAsset) {
		this.unitPriceAsset = unitPriceAsset;
	}

	public BigDecimal getTotlePriceAsset() {
		return totlePriceAsset;
	}

	public void setTotlePriceAsset(BigDecimal totlePriceAsset) {
		this.totlePriceAsset = totlePriceAsset;
	}

	public BigDecimal getLifetimeAsset() {
		return lifetimeAsset;
	}

	public void setLifetimeAsset(BigDecimal lifetimeAsset) {
		this.lifetimeAsset = lifetimeAsset;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public BigDecimal getDepreciationRate() {
		return depreciationRate;
	}

	public void setDepreciationRate(BigDecimal depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public BigDecimal getAnnualDepreciation() {
		return annualDepreciation;
	}

	public void setAnnualDepreciation(BigDecimal annualDepreciation) {
		this.annualDepreciation = annualDepreciation;
	}

	public BigDecimal getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}

	public void setAccumulatedDepreciation(BigDecimal accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public String getAssetNote() {
		return assetNote;
	}

	public void setAssetNote(String assetNote) {
		this.assetNote = assetNote;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
