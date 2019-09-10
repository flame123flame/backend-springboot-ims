package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisTaxAmtVo extends DataTableRequest {

	private static final long serialVersionUID = -6837417994944245251L;

	private String goodsDesc;
	private String taxSubmissionDate;
	private BigDecimal netTaxByValue;
	private BigDecimal netTaxByQty;
	private BigDecimal netTaxAmt;
	private BigDecimal anaNetTaxByValue;
	private BigDecimal anaNetTaxByQty;
	private BigDecimal anaNetTaxAmt;
	private BigDecimal diffNetTaxByValue;
	private BigDecimal diffNetTaxByQty;
	private BigDecimal diffNetTaxAmt;

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getTaxSubmissionDate() {
		return taxSubmissionDate;
	}

	public void setTaxSubmissionDate(String taxSubmissionDate) {
		this.taxSubmissionDate = taxSubmissionDate;
	}

	public BigDecimal getNetTaxByValue() {
		return netTaxByValue;
	}

	public void setNetTaxByValue(BigDecimal netTaxByValue) {
		this.netTaxByValue = netTaxByValue;
	}

	public BigDecimal getNetTaxByQty() {
		return netTaxByQty;
	}

	public void setNetTaxByQty(BigDecimal netTaxByQty) {
		this.netTaxByQty = netTaxByQty;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

	public BigDecimal getAnaNetTaxByValue() {
		return anaNetTaxByValue;
	}

	public void setAnaNetTaxByValue(BigDecimal anaNetTaxByValue) {
		this.anaNetTaxByValue = anaNetTaxByValue;
	}

	public BigDecimal getAnaNetTaxByQty() {
		return anaNetTaxByQty;
	}

	public void setAnaNetTaxByQty(BigDecimal anaNetTaxByQty) {
		this.anaNetTaxByQty = anaNetTaxByQty;
	}

	public BigDecimal getAnaNetTaxAmt() {
		return anaNetTaxAmt;
	}

	public void setAnaNetTaxAmt(BigDecimal anaNetTaxAmt) {
		this.anaNetTaxAmt = anaNetTaxAmt;
	}

	public BigDecimal getDiffNetTaxByValue() {
		return diffNetTaxByValue;
	}

	public void setDiffNetTaxByValue(BigDecimal diffNetTaxByValue) {
		this.diffNetTaxByValue = diffNetTaxByValue;
	}

	public BigDecimal getDiffNetTaxByQty() {
		return diffNetTaxByQty;
	}

	public void setDiffNetTaxByQty(BigDecimal diffNetTaxByQty) {
		this.diffNetTaxByQty = diffNetTaxByQty;
	}

	public BigDecimal getDiffNetTaxAmt() {
		return diffNetTaxAmt;
	}

	public void setDiffNetTaxAmt(BigDecimal diffNetTaxAmt) {
		this.diffNetTaxAmt = diffNetTaxAmt;
	}

}
