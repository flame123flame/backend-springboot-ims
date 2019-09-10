package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_ANAFRI0001_D")
public class WsAnafri0001D extends BaseEntity {

	private static final long serialVersionUID = 4468815188892769078L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_ANAFRI0001_D_GEN")
	@SequenceGenerator(name = "WS_ANAFRI0001_D_GEN", sequenceName = "WS_ANAFRI0001_D_SEQ", allocationSize = 1)
	@Column(name = "ANAFRI0001_D_SEQ")
	private Long anafri0001DSeq;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "REG_IN_NO")
	private String regInNo;
	@Column(name = "GOODS_SEQ")
	private String goodsSeq;
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "BRAND_MAIN_CODE")
	private String brandMainCode;
	@Column(name = "BRAND_MAIN_NAME")
	private String brandMainName;
	@Column(name = "BRAND_SECOND_CODE")
	private String brandSecondCode;
	@Column(name = "BRAND_SECOND_NAME")
	private String brandSecondName;
	@Column(name = "MODEL_CODE")
	private String modelCode;
	@Column(name = "MODEL_NAME")
	private String modelName;
	@Column(name = "SIZE_CODE")
	private String sizeCode;
	@Column(name = "SIZE_NAME")
	private String sizeName;
	@Column(name = "UNIT_CODE")
	private String unitCode;
	@Column(name = "UNIT_NAME")
	private String unitName;
	@Column(name = "PRODUCT_QTY")
	private BigDecimal productQty;
	@Column(name = "PRODUCT_PRICE")
	private BigDecimal productPrice;
	@Column(name = "VALUE_RATE")
	private BigDecimal valueRate;
	@Column(name = "QTY_RATE")
	private BigDecimal qtyRate;
	@Column(name = "TAX_VALUE_AMT")
	private BigDecimal taxValueAmt;
	@Column(name = "TAX_QUANTITY_AMT")
	private BigDecimal taxQuantityAmt;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "LOC_AMT")
	private BigDecimal locAmt;

	public Long getAnafri0001DSeq() {
		return anafri0001DSeq;
	}

	public void setAnafri0001DSeq(Long anafri0001DSeq) {
		this.anafri0001DSeq = anafri0001DSeq;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getRegInNo() {
		return regInNo;
	}

	public void setRegInNo(String regInNo) {
		this.regInNo = regInNo;
	}

	public String getGoodsSeq() {
		return goodsSeq;
	}

	public void setGoodsSeq(String goodsSeq) {
		this.goodsSeq = goodsSeq;
	}

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

	public BigDecimal getProductQty() {
		return productQty;
	}

	public void setProductQty(BigDecimal productQty) {
		this.productQty = productQty;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getValueRate() {
		return valueRate;
	}

	public void setValueRate(BigDecimal valueRate) {
		this.valueRate = valueRate;
	}

	public BigDecimal getQtyRate() {
		return qtyRate;
	}

	public void setQtyRate(BigDecimal qtyRate) {
		this.qtyRate = qtyRate;
	}

	public BigDecimal getTaxValueAmt() {
		return taxValueAmt;
	}

	public void setTaxValueAmt(BigDecimal taxValueAmt) {
		this.taxValueAmt = taxValueAmt;
	}

	public BigDecimal getTaxQuantityAmt() {
		return taxQuantityAmt;
	}

	public void setTaxQuantityAmt(BigDecimal taxQuantityAmt) {
		this.taxQuantityAmt = taxQuantityAmt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getLocAmt() {
		return locAmt;
	}

	public void setLocAmt(BigDecimal locAmt) {
		this.locAmt = locAmt;
	}

}
