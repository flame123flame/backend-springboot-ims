package th.go.excise.ims.ws.client.pcc.anafri0001.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goods {

	@SerializedName("ProductCode")
	@Expose
	private String productCode;

	@SerializedName("ProductName")
	@Expose
	private String productName;

	@SerializedName("BrandMainCode")
	@Expose
	private String brandMainCode;

	@SerializedName("BrandMainName")
	@Expose
	private String brandMainName;

	@SerializedName("BrandSecondCode")
	@Expose
	private String brandSecondCode;

	@SerializedName("BrandSecondName")
	@Expose
	private String brandSecondName;

	@SerializedName("ModelCode")
	@Expose
	private String modelCode;

	@SerializedName("ModelName")
	@Expose
	private String modelName;

	@SerializedName("SizeCode")
	@Expose
	private String sizeCode;

	@SerializedName("SizeName")
	@Expose
	private String sizeName;

	@SerializedName("UnitCode")
	@Expose
	private String unitCode;

	@SerializedName("UnitName")
	@Expose
	private String unitName;

	@SerializedName("ProductQuantity")
	@Expose
	private String productQuantity;

	@SerializedName("ProductPrice")
	@Expose
	private String productPrice;

	@SerializedName("ValueRate")
	@Expose
	private String valueRate;

	@SerializedName("QuantityRate")
	@Expose
	private String quantityRate;

	@SerializedName("TaxValueAmount")
	@Expose
	private String taxValueAmount;

	@SerializedName("TaxQuantityAmount")
	@Expose
	private String taxQuantityAmount;

	@SerializedName("TaxAmount")
	@Expose
	private String taxAmount;

	@SerializedName("LocAmount")
	@Expose
	private String locAmount;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandMainCode() {
		return brandMainCode;
	}

	public void setBrandMainCode(String brandMainCode) {
		this.brandMainCode = brandMainCode;
	}

	public String getBrandMainName() {
		return brandMainName;
	}

	public void setBrandMainName(String brandMainName) {
		this.brandMainName = brandMainName;
	}

	public String getBrandSecondCode() {
		return brandSecondCode;
	}

	public void setBrandSecondCode(String brandSecondCode) {
		this.brandSecondCode = brandSecondCode;
	}

	public String getBrandSecondName() {
		return brandSecondName;
	}

	public void setBrandSecondName(String brandSecondName) {
		this.brandSecondName = brandSecondName;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getValueRate() {
		return valueRate;
	}

	public void setValueRate(String valueRate) {
		this.valueRate = valueRate;
	}

	public String getQuantityRate() {
		return quantityRate;
	}

	public void setQuantityRate(String quantityRate) {
		this.quantityRate = quantityRate;
	}

	public String getTaxValueAmount() {
		return taxValueAmount;
	}

	public void setTaxValueAmount(String taxValueAmount) {
		this.taxValueAmount = taxValueAmount;
	}

	public String getTaxQuantityAmount() {
		return taxQuantityAmount;
	}

	public void setTaxQuantityAmount(String taxQuantityAmount) {
		this.taxQuantityAmount = taxQuantityAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getLocAmount() {
		return locAmount;
	}

	public void setLocAmount(String locAmount) {
		this.locAmount = locAmount;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
