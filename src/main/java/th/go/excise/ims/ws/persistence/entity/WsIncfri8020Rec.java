package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_INCFRI8020_REC")
public class WsIncfri8020Rec extends BaseEntity {

	private static final long serialVersionUID = 80426290746382580L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_INCFRI8020_REC_GEN")
	@SequenceGenerator(name = "WS_INCFRI8020_REC_GEN", sequenceName = "WS_INCFRI8020_REC_SEQ", allocationSize = 1)
	@Column(name = "WS_INCFRI8020_REC_ID")
	private Long wsIncfri8020RecId;
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;
	@Column(name = "DEPOSIT_DATE")
	private Date depositDate;
	@Column(name = "SEND_DATE")
	private Date sendDate;
	@Column(name = "INCOME_NAME")
	private String incomeName;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;
	@Column(name = "NET_LOC_AMT")
	private BigDecimal netLocAmt;
	@Column(name = "LOC_OTH_AMT")
	private BigDecimal locOthAmt;
	@Column(name = "LOC_EXP_AMT")
	private BigDecimal locExpAmt;
	@Column(name = "SSS_FUND_AMT")
	private BigDecimal sssFundAmt;
	@Column(name = "TPBS_FUND_AMT")
	private BigDecimal tpbsFundAmt;
	@Column(name = "SPORT_FUND_AMT")
	private BigDecimal sportFundAmt;
	@Column(name = "OLDER_FUND_AMT")
	private BigDecimal olderFundAmt;
	@Column(name = "SEND_AMT")
	private BigDecimal sendAmt;
	@Column(name = "STAMP_AMT")
	private BigDecimal stampAmt;
	@Column(name = "CUSTOM_AMT")
	private BigDecimal customAmt;
	@Column(name = "TRN_DATE")
	private Date trnDate;
	@Column(name = "OFFICE_RECEIVE")
	private String officeReceive;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "RECEIPT_NO_SSS_FUND")
	private String receiptNoSssFund;
	@Column(name = "RECEIPT_NO_TPBS_FUND")
	private String receiptNoTpbsFund;
	@Column(name = "RECEIPT_NO_SPORT_FUND")
	private String receiptNoSportFund;
	@Column(name = "RECEIPT_NO_OLDER_FUND")
	private String receiptNoOlderFund;
	@Column(name = "PIN_NID_ID")
	private String pinNidId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_NAME")
	private String cusName;
	@Column(name = "FAC_NAME")
	private String facName;
	@Column(name = "IS_DELETED")
	private String isDeleted;
	@Column(name = "VERSION")
	private BigDecimal version;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public Long getWsIncfri8020RecId() {
		return wsIncfri8020RecId;
	}

	public void setWsIncfri8020RecId(Long wsIncfri8020RecId) {
		this.wsIncfri8020RecId = wsIncfri8020RecId;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
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

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

	public BigDecimal getNetLocAmt() {
		return netLocAmt;
	}

	public void setNetLocAmt(BigDecimal netLocAmt) {
		this.netLocAmt = netLocAmt;
	}

	public BigDecimal getLocOthAmt() {
		return locOthAmt;
	}

	public void setLocOthAmt(BigDecimal locOthAmt) {
		this.locOthAmt = locOthAmt;
	}

	public BigDecimal getLocExpAmt() {
		return locExpAmt;
	}

	public void setLocExpAmt(BigDecimal locExpAmt) {
		this.locExpAmt = locExpAmt;
	}

	public BigDecimal getSssFundAmt() {
		return sssFundAmt;
	}

	public void setSssFundAmt(BigDecimal sssFundAmt) {
		this.sssFundAmt = sssFundAmt;
	}

	public BigDecimal getTpbsFundAmt() {
		return tpbsFundAmt;
	}

	public void setTpbsFundAmt(BigDecimal tpbsFundAmt) {
		this.tpbsFundAmt = tpbsFundAmt;
	}

	public BigDecimal getSportFundAmt() {
		return sportFundAmt;
	}

	public void setSportFundAmt(BigDecimal sportFundAmt) {
		this.sportFundAmt = sportFundAmt;
	}

	public BigDecimal getOlderFundAmt() {
		return olderFundAmt;
	}

	public void setOlderFundAmt(BigDecimal olderFundAmt) {
		this.olderFundAmt = olderFundAmt;
	}

	public BigDecimal getSendAmt() {
		return sendAmt;
	}

	public void setSendAmt(BigDecimal sendAmt) {
		this.sendAmt = sendAmt;
	}

	public BigDecimal getStampAmt() {
		return stampAmt;
	}

	public void setStampAmt(BigDecimal stampAmt) {
		this.stampAmt = stampAmt;
	}

	public BigDecimal getCustomAmt() {
		return customAmt;
	}

	public void setCustomAmt(BigDecimal customAmt) {
		this.customAmt = customAmt;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
