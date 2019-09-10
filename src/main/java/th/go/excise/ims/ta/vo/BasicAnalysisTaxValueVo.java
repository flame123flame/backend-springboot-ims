package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisTaxValueVo extends DataTableRequest {

	private static final long serialVersionUID = 4030578141548018358L;

	private String goodsDescText;
	private BigDecimal taxQty;
	private BigDecimal informPrice;
	private BigDecimal goodsValueAmt;

	public String getGoodsDescText() {
		return goodsDescText;
	}

	public void setGoodsDescText(String goodsDescText) {
		this.goodsDescText = goodsDescText;
	}

	public BigDecimal getTaxQty() {
		return taxQty;
	}

	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getGoodsValueAmt() {
		return goodsValueAmt;
	}

	public void setGoodsValueAmt(BigDecimal goodsValueAmt) {
		this.goodsValueAmt = goodsValueAmt;
	}

}
