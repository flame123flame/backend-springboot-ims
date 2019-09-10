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
@Table(name = "TA_PAPER_PR05_D")
public class TaPaperPr05D extends BaseEntity {

	private static final long serialVersionUID = -1960303646266794651L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR05_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR05_D_GEN", sequenceName = "TA_PAPER_PR05_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR05_D_SEQ")
	private Long paperPr05DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "INPUT_GOODS_QTY")
	private BigDecimal inputGoodsQty;
	@Column(name = "INPUT_MONTH_STATEMENT_QTY")
	private BigDecimal inputMonthStatementQty;
	@Column(name = "INPUT_DAILY_ACCOUNT_QTY")
	private BigDecimal inputDailyAccountQty;
	@Column(name = "MAX_DIFF_QTY1")
	private BigDecimal maxDiffQty1;
	@Column(name = "MAX_DIFF_QTY2")
	private BigDecimal maxDiffQty2;

	public Long getPaperPr05DSeq() {
		return paperPr05DSeq;
	}

	public void setPaperPr05DSeq(Long paperPr05DSeq) {
		this.paperPr05DSeq = paperPr05DSeq;
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

	public BigDecimal getInputGoodsQty() {
		return inputGoodsQty;
	}

	public void setInputGoodsQty(BigDecimal inputGoodsQty) {
		this.inputGoodsQty = inputGoodsQty;
	}

	public BigDecimal getInputMonthStatementQty() {
		return inputMonthStatementQty;
	}

	public void setInputMonthStatementQty(BigDecimal inputMonthStatementQty) {
		this.inputMonthStatementQty = inputMonthStatementQty;
	}

	public BigDecimal getInputDailyAccountQty() {
		return inputDailyAccountQty;
	}

	public void setInputDailyAccountQty(BigDecimal inputDailyAccountQty) {
		this.inputDailyAccountQty = inputDailyAccountQty;
	}

	public BigDecimal getMaxDiffQty1() {
		return maxDiffQty1;
	}

	public void setMaxDiffQty1(BigDecimal maxDiffQty1) {
		this.maxDiffQty1 = maxDiffQty1;
	}

	public BigDecimal getMaxDiffQty2() {
		return maxDiffQty2;
	}

	public void setMaxDiffQty2(BigDecimal maxDiffQty2) {
		this.maxDiffQty2 = maxDiffQty2;
	}

}
