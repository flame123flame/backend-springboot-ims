package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_WS_INC8000")
public class TaWsInc8000 extends BaseEntity {

	private static final long serialVersionUID = 6475180011633542723L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WS_INC8000_GEN")
	@SequenceGenerator(name = "TA_WS_INC8000_GEN", sequenceName = "TA_WS_INC8000_SEQ", allocationSize = 1)
	@Column(name = "WS_INC8000_ID")
	private BigDecimal wsInc8000Id;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "RECEIPT_DATE")
	private String receiptDate;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;
	@Column(name = "PEN_AMOUNT")
	private BigDecimal penAmount;
	@Column(name = "ADD_AMOUNT")
	private BigDecimal addAmount;
	@Column(name = "REDUCE_AMOUNT")
	private BigDecimal reduceAmount;
	@Column(name = "CREDIT_AMOUNT")
	private BigDecimal creditAmount;
	@Column(name = "OFFICE_RECEIVE_CODE")
	private String officeReceiveCode;
	@Column(name = "TRN_DATE")
	private String trnDate;
	@Column(name = "DEPOSIT_DATE")
	private String depositDate;
	@Column(name = "SEND_DATE")
	private String sendDate;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "INCOME_TYPE")
	private String incomeType;

	public BigDecimal getWsInc8000Id() {
		return wsInc8000Id;
	}

	public void setWsInc8000Id(BigDecimal wsInc8000Id) {
		this.wsInc8000Id = wsInc8000Id;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getPenAmount() {
		return penAmount;
	}

	public void setPenAmount(BigDecimal penAmount) {
		this.penAmount = penAmount;
	}

	public BigDecimal getAddAmount() {
		return addAmount;
	}

	public void setAddAmount(BigDecimal addAmount) {
		this.addAmount = addAmount;
	}

	public BigDecimal getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(BigDecimal reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
