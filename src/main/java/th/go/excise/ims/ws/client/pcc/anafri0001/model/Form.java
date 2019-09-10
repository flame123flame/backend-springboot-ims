package th.go.excise.ims.ws.client.pcc.anafri0001.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form {

	@SerializedName("RegInNo")
	@Expose
	private String regInNo;

	@SerializedName("RegInDate")
	@Expose
	private String regInDate;

	@SerializedName("PayType12")
	@Expose
	private String payType12;

	@SerializedName("ReceiptNo")
	@Expose
	private String receiptNo;

	@SerializedName("ReceiptDate")
	@Expose
	private String receiptDate;

	@SerializedName("TaxAmount")
	@Expose
	private String taxAmount;

	@SerializedName("ReduceAmount")
	@Expose
	private String reduceAmount;

	@SerializedName("DifAmount")
	@Expose
	private String difAmount;

	@SerializedName("PenAmount")
	@Expose
	private String penAmount;

	@SerializedName("AddAmount")
	@Expose
	private String addAmount;

	@SerializedName("CreditAmount")
	@Expose
	private String creditAmount;

	@SerializedName("NetTaxAmount")
	@Expose
	private String netTaxAmount;

	@SerializedName("GoodsList")
	@Expose
	private List<Goods> goodsList;

	public String getRegInNo() {
		return regInNo;
	}

	public void setRegInNo(String regInNo) {
		this.regInNo = regInNo;
	}

	public String getRegInDate() {
		return regInDate;
	}

	public void setRegInDate(String regInDate) {
		this.regInDate = regInDate;
	}

	public String getPayType12() {
		return payType12;
	}

	public void setPayType12(String payType12) {
		this.payType12 = payType12;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(String reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public String getDifAmount() {
		return difAmount;
	}

	public void setDifAmount(String difAmount) {
		this.difAmount = difAmount;
	}

	public String getPenAmount() {
		return penAmount;
	}

	public void setPenAmount(String penAmount) {
		this.penAmount = penAmount;
	}

	public String getAddAmount() {
		return addAmount;
	}

	public void setAddAmount(String addAmount) {
		this.addAmount = addAmount;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getNetTaxAmount() {
		return netTaxAmount;
	}

	public void setNetTaxAmount(String netTaxAmount) {
		this.netTaxAmount = netTaxAmount;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
