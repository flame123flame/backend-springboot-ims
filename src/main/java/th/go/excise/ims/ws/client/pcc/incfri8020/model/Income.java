package th.go.excise.ims.ws.client.pcc.incfri8020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Income {

	@SerializedName("ReceiptDate")
	@Expose
	private String receiptDate;

	@SerializedName("DepositDate")
	@Expose
	private String depositDate;

	@SerializedName("SendDate")
	@Expose
	private String sendDate;

	@SerializedName("IncomeName")
	@Expose
	private String incomeName;

	@SerializedName("ReceiptNo")
	@Expose
	private String receiptNo;

	@SerializedName("NettaxAmount")
	@Expose
	private String nettaxAmount;

	@SerializedName("NetLocAmount")
	@Expose
	private String netLocAmount;

	@SerializedName("LocOthAmount")
	@Expose
	private String locOthAmount;

	@SerializedName("LocExpAmount")
	@Expose
	private String locExpAmount;

	@SerializedName("SssFundAmount")
	@Expose
	private String sssFundAmount;

	@SerializedName("TpbsFundAmount")
	@Expose
	private String tpbsFundAmount;

	@SerializedName("SportFundAmount")
	@Expose
	private String sportFundAmount;

	@SerializedName("OlderFundAmount")
	@Expose
	private String olderFundAmount;

	@SerializedName("SendAmount")
	@Expose
	private String sendAmount;

	@SerializedName("StampAmount")
	@Expose
	private String stampAmount;

	@SerializedName("CustomAmount")
	@Expose
	private String customAmount;

	@SerializedName("TrnDate")
	@Expose
	private String trnDate;

	@SerializedName("OfficeReceive")
	@Expose
	private String officeReceive;

	@SerializedName("IncomeCode")
	@Expose
	private String incomeCode;

	@SerializedName("ReceiptNoSssFund")
	@Expose
	private String receiptNoSssFund;

	@SerializedName("ReceiptNoTpbsFund")
	@Expose
	private String receiptNoTpbsFund;

	@SerializedName("ReceiptNoSportFund")
	@Expose
	private String receiptNoSportFund;

	@SerializedName("ReceiptNoOlderFund")
	@Expose
	private String receiptNoOlderFund;

	@SerializedName("PinNidId")
	@Expose
	private String pinNidId;

	@SerializedName("NewRegId")
	@Expose
	private String newRegId;

	@SerializedName("CusName")
	@Expose
	private String cusName;

	@SerializedName("FacName")
	@Expose
	private String facName;
	
	@SerializedName("OfflineStatus")
	@Expose
	private String offlineStatus;
	
	@SerializedName("IncctlNo")
	@Expose
	private String IncCtlNo;

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
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

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getNettaxAmount() {
		return nettaxAmount;
	}

	public void setNettaxAmount(String nettaxAmount) {
		this.nettaxAmount = nettaxAmount;
	}

	public String getNetLocAmount() {
		return netLocAmount;
	}

	public void setNetLocAmount(String netLocAmount) {
		this.netLocAmount = netLocAmount;
	}

	public String getLocOthAmount() {
		return locOthAmount;
	}

	public void setLocOthAmount(String locOthAmount) {
		this.locOthAmount = locOthAmount;
	}

	public String getLocExpAmount() {
		return locExpAmount;
	}

	public void setLocExpAmount(String locExpAmount) {
		this.locExpAmount = locExpAmount;
	}

	public String getSssFundAmount() {
		return sssFundAmount;
	}

	public void setSssFundAmount(String sssFundAmount) {
		this.sssFundAmount = sssFundAmount;
	}

	public String getTpbsFundAmount() {
		return tpbsFundAmount;
	}

	public void setTpbsFundAmount(String tpbsFundAmount) {
		this.tpbsFundAmount = tpbsFundAmount;
	}

	public String getSportFundAmount() {
		return sportFundAmount;
	}

	public void setSportFundAmount(String sportFundAmount) {
		this.sportFundAmount = sportFundAmount;
	}

	public String getOlderFundAmount() {
		return olderFundAmount;
	}

	public void setOlderFundAmount(String olderFundAmount) {
		this.olderFundAmount = olderFundAmount;
	}

	public String getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(String sendAmount) {
		this.sendAmount = sendAmount;
	}

	public String getStampAmount() {
		return stampAmount;
	}

	public void setStampAmount(String stampAmount) {
		this.stampAmount = stampAmount;
	}

	public String getCustomAmount() {
		return customAmount;
	}

	public void setCustomAmount(String customAmount) {
		this.customAmount = customAmount;
	}

	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}

	public String getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(String officeReceive) {
		this.officeReceive = officeReceive;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getReceiptNoSssFund() {
		return receiptNoSssFund;
	}

	public void setReceiptNoSssFund(String receiptNoSssFund) {
		this.receiptNoSssFund = receiptNoSssFund;
	}

	public String getReceiptNoTpbsFund() {
		return receiptNoTpbsFund;
	}

	public void setReceiptNoTpbsFund(String receiptNoTpbsFund) {
		this.receiptNoTpbsFund = receiptNoTpbsFund;
	}

	public String getReceiptNoSportFund() {
		return receiptNoSportFund;
	}

	public void setReceiptNoSportFund(String receiptNoSportFund) {
		this.receiptNoSportFund = receiptNoSportFund;
	}

	public String getReceiptNoOlderFund() {
		return receiptNoOlderFund;
	}

	public void setReceiptNoOlderFund(String receiptNoOlderFund) {
		this.receiptNoOlderFund = receiptNoOlderFund;
	}

	public String getPinNidId() {
		return pinNidId;
	}

	public void setPinNidId(String pinNidId) {
		this.pinNidId = pinNidId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public String getOfflineStatus() {
		return offlineStatus;
	}

	public void setOfflineStatus(String offlineStatus) {
		this.offlineStatus = offlineStatus;
	}

	public String getIncCtlNo() {
		return IncCtlNo;
	}

	public void setIncCtlNo(String incCtlNo) {
		IncCtlNo = incCtlNo;
	}

}
