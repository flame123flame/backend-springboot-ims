package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisTaxRetailPriceVo extends DataTableRequest {

	private static final long serialVersionUID = -2918790368443565580L;

	private String goodsDesc;
	private BigDecimal taxInformPrice;
	private BigDecimal informPrice;
	private BigDecimal diffTaxInformPrice;

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public BigDecimal getTaxInformPrice() {
		return taxInformPrice;
	}

	public void setTaxInformPrice(BigDecimal taxInformPrice) {
		this.taxInformPrice = taxInformPrice;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getDiffTaxInformPrice() {
		return diffTaxInformPrice;
	}

	public void setDiffTaxInformPrice(BigDecimal diffTaxInformPrice) {
		this.diffTaxInformPrice = diffTaxInformPrice;
	}

}
