package th.go.excise.ims.ws.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_ANAFRI0001_H")
public class WsAnafri0001H extends BaseEntity {

	private static final long serialVersionUID = 1729629415490516457L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_ANAFRI0001_H_GEN")
	@SequenceGenerator(name = "WS_ANAFRI0001_H_GEN", sequenceName = "WS_ANAFRI0001_H_SEQ", allocationSize = 1)
	@Column(name = "ANAFRI0001_H_SEQ")
	private Long anafri0001HSeq;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "REG_IN_NO")
	private String regInNo;
	@Column(name = "REG_IN_DATE")
	private LocalDate regInDate;
	@Column(name = "PAY_TYPE12")
	private String payType12;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "RECEIPT_DATE")
	private LocalDate receiptDate;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "REDUCE_AMT")
	private BigDecimal reduceAmt;
	@Column(name = "DIF_AMT")
	private BigDecimal difAmt;
	@Column(name = "PEN_AMT")
	private BigDecimal penAmt;
	@Column(name = "ADD_AMT")
	private BigDecimal addAmt;
	@Column(name = "CREDIT_AMT")
	private BigDecimal creditAmt;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;

	public Long getAnafri0001HSeq() {
		return anafri0001HSeq;
	}

	public void setAnafri0001HSeq(Long anafri0001HSeq) {
		this.anafri0001HSeq = anafri0001HSeq;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getRegInNo() {
		return regInNo;
	}

	public void setRegInNo(String regInNo) {
		this.regInNo = regInNo;
	}

	public LocalDate getRegInDate() {
		return regInDate;
	}

	public void setRegInDate(LocalDate regInDate) {
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

	public LocalDate getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(LocalDate receiptDate) {
		this.receiptDate = receiptDate;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getReduceAmt() {
		return reduceAmt;
	}

	public void setReduceAmt(BigDecimal reduceAmt) {
		this.reduceAmt = reduceAmt;
	}

	public BigDecimal getDifAmt() {
		return difAmt;
	}

	public void setDifAmt(BigDecimal difAmt) {
		this.difAmt = difAmt;
	}

	public BigDecimal getPenAmt() {
		return penAmt;
	}

	public void setPenAmt(BigDecimal penAmt) {
		this.penAmt = penAmt;
	}

	public BigDecimal getAddAmt() {
		return addAmt;
	}

	public void setAddAmt(BigDecimal addAmt) {
		this.addAmt = addAmt;
	}

	public BigDecimal getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

}
