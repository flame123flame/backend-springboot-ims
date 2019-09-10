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
@Table(name = "TA_PAPER_PR03_D")
public class TaPaperPr03D extends BaseEntity {

	private static final long serialVersionUID = -3818160727510573738L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR03_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR03_D_GEN", sequenceName = "TA_PAPER_PR03_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR03_D_SEQ")
	private Long paperPr03DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "BALANCE_BY_ACCOUNT_QTY")
	private BigDecimal balanceByAccountQty;
	@Column(name = "BALANCE_BY_STOCK_QTY")
	private BigDecimal balanceByStockQty;
	@Column(name = "BALANCE_BY_COUNT_QTY")
	private BigDecimal balanceByCountQty;
	@Column(name = "MAX_DIFF_QTY1")
	private BigDecimal maxDiffQty1;
	@Column(name = "MAX_DIFF_QTY2")
	private BigDecimal maxDiffQty2;

	public Long getPaperPr03DSeq() {
		return paperPr03DSeq;
	}

	public void setPaperPr03DSeq(Long paperPr03DSeq) {
		this.paperPr03DSeq = paperPr03DSeq;
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

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getBalanceByAccountQty() {
		return balanceByAccountQty;
	}

	public void setBalanceByAccountQty(BigDecimal balanceByAccountQty) {
		this.balanceByAccountQty = balanceByAccountQty;
	}

	public BigDecimal getBalanceByStockQty() {
		return balanceByStockQty;
	}

	public void setBalanceByStockQty(BigDecimal balanceByStockQty) {
		this.balanceByStockQty = balanceByStockQty;
	}

	public BigDecimal getBalanceByCountQty() {
		return balanceByCountQty;
	}

	public void setBalanceByCountQty(BigDecimal balanceByCountQty) {
		this.balanceByCountQty = balanceByCountQty;
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
