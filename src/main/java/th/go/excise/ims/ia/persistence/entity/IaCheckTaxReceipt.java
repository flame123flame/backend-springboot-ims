
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
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CHECK_TAX_RECEIPT")
public class IaCheckTaxReceipt extends BaseEntity {

	private static final long serialVersionUID = -2607547194513954396L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_TAX_RECEIPT_GEN")
	@SequenceGenerator(name = "IA_CHECK_TAX_RECEIPT_GEN", sequenceName = "IA_CHECK_TAX_RECEIPT_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "TAX_RECEIPT_ID")
	private BigDecimal taxReceiptId;
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;
	@Column(name = "TRN_DATE")
	private Date trnDate;
	@Column(name = "OFFICE_RECEIVE")
	private String officeReceive;
	@Column(name = "DEPOSIT_DATE")
	private Date depositDate;
	@Column(name = "SEND_DATE")
	private Date sendDate;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "INCOME_NAME")
	private String incomeName;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "NETTAX_AMOUNT")
	private BigDecimal nettaxAmount;
	@Column(name = "NET_LOC_AMOUNT")
	private BigDecimal netLocAmount;
	@Column(name = "LOC_OTH_AMOUNT")
	private BigDecimal locOthAmount;
	@Column(name = "LOC_EXP_AMOUNT")
	private BigDecimal locExpAmount;
	@Column(name = "RECEIPT_NO_OLDER_FUND")
	private String receiptNoOlderFund;
	@Column(name = "OLDER_FUND_AMOUNT")
	private BigDecimal olderFundAmount;
	@Column(name = "RECEIPT_NO_TPBS_FUND")
	private String receiptNoTpbsFund;
	@Column(name = "TPBS_FUND_AMOUNT")
	private BigDecimal tpbsFundAmount;
	@Column(name = "RECEIPT_NO_SSS_FUND")
	private String receiptNoSssFund;
	@Column(name = "SSS_FUND_AMOUNT")
	private BigDecimal sssFundAmount;
	@Column(name = "RECEIPT_NO_SPORT_FUND")
	private String receiptNoSportFund;
	@Column(name = "SPORT_FUND_AMOUNT")
	private BigDecimal sportFundAmount;
	@Column(name = "SEND_AMOUNT")
	private BigDecimal sendAmount;
	@Column(name = "STAMP_AMOUNT")
	private BigDecimal stampAmount;
	@Column(name = "CUSTOM_AMOUNT")
	private BigDecimal customAmount;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "DATE_TYPE")
	private String dateType;
	@Column(name = "CHECKED_AMOUNT")
	private BigDecimal checkedAmount;
	@Column(name = "TAX_PRINT_NO")
	private String taxPrintNo;
	@Column(name = "PIN_NID_ID")
	private String pinNidId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_NAME")
	private String cusName;
	@Column(name = "FAC_NAME")
	private String facName;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getTaxReceiptId() {
		return taxReceiptId;
	}

	public void setTaxReceiptId(BigDecimal taxReceiptId) {
		this.taxReceiptId = taxReceiptId;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Date getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public String getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(String officeReceive) {
		this.officeReceive = officeReceive;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
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

	public BigDecimal getNettaxAmount() {
		return nettaxAmount;
	}

	public void setNettaxAmount(BigDecimal nettaxAmount) {
		this.nettaxAmount = nettaxAmount;
	}

	public BigDecimal getNetLocAmount() {
		return netLocAmount;
	}

	public void setNetLocAmount(BigDecimal netLocAmount) {
		this.netLocAmount = netLocAmount;
	}

	public BigDecimal getLocOthAmount() {
		return locOthAmount;
	}

	public void setLocOthAmount(BigDecimal locOthAmount) {
		this.locOthAmount = locOthAmount;
	}

	public BigDecimal getLocExpAmount() {
		return locExpAmount;
	}

	public void setLocExpAmount(BigDecimal locExpAmount) {
		this.locExpAmount = locExpAmount;
	}

	public String getReceiptNoOlderFund() {
		return receiptNoOlderFund;
	}

	public void setReceiptNoOlderFund(String receiptNoOlderFund) {
		this.receiptNoOlderFund = receiptNoOlderFund;
	}

	public BigDecimal getOlderFundAmount() {
		return olderFundAmount;
	}

	public void setOlderFundAmount(BigDecimal olderFundAmount) {
		this.olderFundAmount = olderFundAmount;
	}

	public String getReceiptNoTpbsFund() {
		return receiptNoTpbsFund;
	}

	public void setReceiptNoTpbsFund(String receiptNoTpbsFund) {
		this.receiptNoTpbsFund = receiptNoTpbsFund;
	}

	public BigDecimal getTpbsFundAmount() {
		return tpbsFundAmount;
	}

	public void setTpbsFundAmount(BigDecimal tpbsFundAmount) {
		this.tpbsFundAmount = tpbsFundAmount;
	}

	public String getReceiptNoSssFund() {
		return receiptNoSssFund;
	}

	public void setReceiptNoSssFund(String receiptNoSssFund) {
		this.receiptNoSssFund = receiptNoSssFund;
	}

	public BigDecimal getSssFundAmount() {
		return sssFundAmount;
	}

	public void setSssFundAmount(BigDecimal sssFundAmount) {
		this.sssFundAmount = sssFundAmount;
	}

	public String getReceiptNoSportFund() {
		return receiptNoSportFund;
	}

	public void setReceiptNoSportFund(String receiptNoSportFund) {
		this.receiptNoSportFund = receiptNoSportFund;
	}

	public BigDecimal getSportFundAmount() {
		return sportFundAmount;
	}

	public void setSportFundAmount(BigDecimal sportFundAmount) {
		this.sportFundAmount = sportFundAmount;
	}

	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}

	public BigDecimal getStampAmount() {
		return stampAmount;
	}

	public void setStampAmount(BigDecimal stampAmount) {
		this.stampAmount = stampAmount;
	}

	public BigDecimal getCustomAmount() {
		return customAmount;
	}

	public void setCustomAmount(BigDecimal customAmount) {
		this.customAmount = customAmount;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public BigDecimal getCheckedAmount() {
		return checkedAmount;
	}

	public void setCheckedAmount(BigDecimal checkedAmount) {
		this.checkedAmount = checkedAmount;
	}

	public String getTaxPrintNo() {
		return taxPrintNo;
	}

	public void setTaxPrintNo(String taxPrintNo) {
		this.taxPrintNo = taxPrintNo;
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

}
