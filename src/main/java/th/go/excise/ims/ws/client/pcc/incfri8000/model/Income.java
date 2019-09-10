package th.go.excise.ims.ws.client.pcc.incfri8000.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Income {

	@SerializedName("NewregId")
	@Expose
	private String newregId;

	@SerializedName("RegId")
	@Expose
	private String regId;

	@SerializedName("ReceiptNo")
	@Expose
	private String receiptNo;

	@SerializedName("ReceiptDate")
	@Expose
	private String receiptDate;

	@SerializedName("TaxAmount")
	@Expose
	private String taxAmount;

	@SerializedName("PenAmount")
	@Expose
	private String penAmount;

	@SerializedName("AddAmount")
	@Expose
	private String addAmount;

	@SerializedName("ReduceAmount")
	@Expose
	private String reduceAmount;

	@SerializedName("CreditAmount")
	@Expose
	private String creditAmount;

	@SerializedName("OfficeReceiveCode")
	@Expose
	private String officeReceiveCode;

	@SerializedName("TrnDate")
	@Expose
	private String trnDate;

	@SerializedName("DepositDate")
	@Expose
	private String depositDate;

	@SerializedName("SendDate")
	@Expose
	private String sendDate;

	@SerializedName("IncomeCode")
	@Expose
	private String incomeCode;

	@SerializedName("IncomeType")
	@Expose
	private String incomeType;

	@SerializedName("CreditList")
	@Expose
	private List<Credit> creditList;

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
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

	public String getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(String reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getOfficeReceiveCode() {
		return officeReceiveCode;
	}

	public void setOfficeReceiveCode(String officeReceiveCode) {
		this.officeReceiveCode = officeReceiveCode;
	}

	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public List<Credit> getCreditList() {
		return creditList;
	}

	public void setCreditList(List<Credit> creditList) {
		this.creditList = creditList;
	}

}
