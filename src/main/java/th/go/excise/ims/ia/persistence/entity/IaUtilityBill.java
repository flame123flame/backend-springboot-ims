
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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_UTILITY_BILL")
public class IaUtilityBill extends BaseEntity {
	private static final long serialVersionUID = -8247875257088877045L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_UTILITY_BILL_GEN")
	@SequenceGenerator(name = "IA_UTILITY_BILL_GEN", sequenceName = "IA_UTILITY_BILL_SEQ", allocationSize = 1)
	@Column(name = "UTILITY_BILL_SEQ")
	private Long utilityBillSeq;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "UBILL_TYPE")
	private String ubillType;
	@Column(name = "MONTH_WD_PAY")
	private String monthWdPay;
	@Column(name = "INVOICE_SEQ")
	private BigDecimal invoiceSeq;
	@Column(name = "INVOICE_MONTH")
	private String invoiceMonth;
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	@Column(name = "TEL_INV_NUMBER")
	private String telInvNumber;
	@Column(name = "INVOICE_DATE")
	private Date invoiceDate;
	@Column(name = "RECEIVE_INV_DATE")
	private Date receiveInvDate;
	@Column(name = "INVOICE_AMT")
	private BigDecimal invoiceAmt;
	@Column(name = "REQ_WD_DATE")
	private Date reqWdDate;
	@Column(name = "REQ_WD_NO")
	private String reqWdNo;
	@Column(name = "REQ_WD_AMT")
	private BigDecimal reqWdAmt;
	@Column(name = "REQ_TAX_AMT")
	private BigDecimal reqTaxAmt;
	@Column(name = "REQ_NET_AMT")
	private BigDecimal reqNetAmt;
	@Column(name = "REQ_PAY_NO")
	private String reqPayNo;
	@Column(name = "REQ_RECEIPT_DATE")
	private Date reqReceiptDate;
	@Column(name = "LATE_PAY_CAUSE")
	private String latePayCause;
	@Column(name = "UBILL_REMARK")
	private String ubillRemark;


	public Long getUtilityBillSeq() {
		return utilityBillSeq;
	}

	public void setUtilityBillSeq(Long utilityBillSeq) {
		this.utilityBillSeq = utilityBillSeq;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getUbillType() {
		return ubillType;
	}

	public void setUbillType(String ubillType) {
		this.ubillType = ubillType;
	}

	public String getMonthWdPay() {
		return monthWdPay;
	}

	public void setMonthWdPay(String monthWdPay) {
		this.monthWdPay = monthWdPay;
	}

	public BigDecimal getInvoiceSeq() {
		return invoiceSeq;
	}

	public void setInvoiceSeq(BigDecimal invoiceSeq) {
		this.invoiceSeq = invoiceSeq;
	}

	public String getInvoiceMonth() {
		return invoiceMonth;
	}

	public void setInvoiceMonth(String invoiceMonth) {
		this.invoiceMonth = invoiceMonth;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getTelInvNumber() {
		return telInvNumber;
	}

	public void setTelInvNumber(String telInvNumber) {
		this.telInvNumber = telInvNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getReceiveInvDate() {
		return receiveInvDate;
	}

	public void setReceiveInvDate(Date receiveInvDate) {
		this.receiveInvDate = receiveInvDate;
	}

	public BigDecimal getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(BigDecimal invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public Date getReqWdDate() {
		return reqWdDate;
	}

	public void setReqWdDate(Date reqWdDate) {
		this.reqWdDate = reqWdDate;
	}

	public String getReqWdNo() {
		return reqWdNo;
	}

	public void setReqWdNo(String reqWdNo) {
		this.reqWdNo = reqWdNo;
	}

	public BigDecimal getReqWdAmt() {
		return reqWdAmt;
	}

	public void setReqWdAmt(BigDecimal reqWdAmt) {
		this.reqWdAmt = reqWdAmt;
	}

	public BigDecimal getReqTaxAmt() {
		return reqTaxAmt;
	}

	public void setReqTaxAmt(BigDecimal reqTaxAmt) {
		this.reqTaxAmt = reqTaxAmt;
	}

	public BigDecimal getReqNetAmt() {
		return reqNetAmt;
	}

	public void setReqNetAmt(BigDecimal reqNetAmt) {
		this.reqNetAmt = reqNetAmt;
	}

	public String getReqPayNo() {
		return reqPayNo;
	}

	public void setReqPayNo(String reqPayNo) {
		this.reqPayNo = reqPayNo;
	}

	public Date getReqReceiptDate() {
		return reqReceiptDate;
	}

	public void setReqReceiptDate(Date reqReceiptDate) {
		this.reqReceiptDate = reqReceiptDate;
	}

	public String getLatePayCause() {
		return latePayCause;
	}

	public void setLatePayCause(String latePayCause) {
		this.latePayCause = latePayCause;
	}

	public String getUbillRemark() {
		return ubillRemark;
	}

	public void setUbillRemark(String ubillRemark) {
		this.ubillRemark = ubillRemark;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
