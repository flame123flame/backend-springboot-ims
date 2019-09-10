package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_PR10_D")
public class TaPaperPr10D extends BaseEntity {

	private static final long serialVersionUID = -4156854620147849518L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR10_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR10_D_GEN", sequenceName = "TA_PAPER_PR10_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR10_D_SEQ")
	private Long paperPr10DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "CARGO_DOC_NO")
	private BigDecimal cargoDocNo;
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	@Column(name = "OUTPUT_DAILY_ACCOUNT_QTY")
	private BigDecimal outputDailyAccountQty;
	@Column(name = "OUTPUT_MONTH_STATEMENT_QTY")
	private BigDecimal outputMonthStatementQty;
	@Column(name = "OUTPUT_AUDIT_QTY")
	private BigDecimal outputAuditQty;
	@Column(name = "TAX_REDUCE_QTY")
	private BigDecimal taxReduceQty;
	@Column(name = "DIFF_OUTPUT_QTY")
	private BigDecimal diffOutputQty;

	public Long getPaperPr10DSeq() {
		return paperPr10DSeq;
	}

	public void setPaperPr10DSeq(Long paperPr10DSeq) {
		this.paperPr10DSeq = paperPr10DSeq;
	}

	public String getPaperPrNumber() {
		return paperPrNumber;
	}

	public void setPaperPrNumber(String paperPrNumber) {
		this.paperPrNumber = paperPrNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public BigDecimal getCargoDocNo() {
		return cargoDocNo;
	}

	public void setCargoDocNo(BigDecimal cargoDocNo) {
		this.cargoDocNo = cargoDocNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getOutputDailyAccountQty() {
		return outputDailyAccountQty;
	}

	public void setOutputDailyAccountQty(BigDecimal outputDailyAccountQty) {
		this.outputDailyAccountQty = outputDailyAccountQty;
	}

	public BigDecimal getOutputMonthStatementQty() {
		return outputMonthStatementQty;
	}

	public void setOutputMonthStatementQty(BigDecimal outputMonthStatementQty) {
		this.outputMonthStatementQty = outputMonthStatementQty;
	}

	public BigDecimal getOutputAuditQty() {
		return outputAuditQty;
	}

	public void setOutputAuditQty(BigDecimal outputAuditQty) {
		this.outputAuditQty = outputAuditQty;
	}

	public BigDecimal getTaxReduceQty() {
		return taxReduceQty;
	}

	public void setTaxReduceQty(BigDecimal taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}

	public BigDecimal getDiffOutputQty() {
		return diffOutputQty;
	}

	public void setDiffOutputQty(BigDecimal diffOutputQty) {
		this.diffOutputQty = diffOutputQty;
	}

}
