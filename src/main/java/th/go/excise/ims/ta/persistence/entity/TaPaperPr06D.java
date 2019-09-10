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
@Table(name = "TA_PAPER_PR06_D")
public class TaPaperPr06D extends BaseEntity {

	private static final long serialVersionUID = 4022735541556742561L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR06_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR06_D_GEN", sequenceName = "TA_PAPER_PR06_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR06_D_SEQ")
	private Long paperPr06DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "OUTPUT_GOODS_QTY")
	private BigDecimal outputGoodsQty;
	@Column(name = "OUTPUT_DAILY_ACCOUNT_QTY")
	private BigDecimal outputDailyAccountQty;
	@Column(name = "OUTPUT_MONTH_STATEMENT_QTY")
	private BigDecimal outputMonthStatementQty;
	@Column(name = "AUDIT_QTY")
	private BigDecimal auditQty;
	@Column(name = "TAX_GOODS_QTY")
	private BigDecimal taxGoodsQty;
	@Column(name = "DIFF_QTY")
	private BigDecimal diffQty;

	public Long getPaperPr06DSeq() {
		return paperPr06DSeq;
	}

	public void setPaperPr06DSeq(Long paperPr06DSeq) {
		this.paperPr06DSeq = paperPr06DSeq;
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

	public BigDecimal getOutputGoodsQty() {
		return outputGoodsQty;
	}

	public void setOutputGoodsQty(BigDecimal outputGoodsQty) {
		this.outputGoodsQty = outputGoodsQty;
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

	public BigDecimal getAuditQty() {
		return auditQty;
	}

	public void setAuditQty(BigDecimal auditQty) {
		this.auditQty = auditQty;
	}

	public BigDecimal getTaxGoodsQty() {
		return taxGoodsQty;
	}

	public void setTaxGoodsQty(BigDecimal taxGoodsQty) {
		this.taxGoodsQty = taxGoodsQty;
	}

	public BigDecimal getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(BigDecimal diffQty) {
		this.diffQty = diffQty;
	}

}
