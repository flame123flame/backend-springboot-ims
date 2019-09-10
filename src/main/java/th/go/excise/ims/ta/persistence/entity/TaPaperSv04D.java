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
@Table(name = "TA_PAPER_SV04_D")
public class TaPaperSv04D extends BaseEntity {

	private static final long serialVersionUID = -6064782550028244093L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV04_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV04_D_GEN", sequenceName = "TA_PAPER_SV04_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV04_D_SEQ")
	private Long paperSv04DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "BALANCE_GOODS_QTY")
	private BigDecimal balanceGoodsQty;
	@Column(name = "AUDIT_BALANCE_GOODS_QTY")
	private BigDecimal auditBalanceGoodsQty;
	@Column(name = "DIFF_BALANCE_GOODS_QTY")
	private BigDecimal diffBalanceGoodsQty;

	public Long getPaperSv04DSeq() {
		return paperSv04DSeq;
	}

	public void setPaperSv04DSeq(Long paperSv04DSeq) {
		this.paperSv04DSeq = paperSv04DSeq;
	}

	public String getPaperSvNumber() {
		return paperSvNumber;
	}

	public void setPaperSvNumber(String paperSvNumber) {
		this.paperSvNumber = paperSvNumber;
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

	public BigDecimal getBalanceGoodsQty() {
		return balanceGoodsQty;
	}

	public void setBalanceGoodsQty(BigDecimal balanceGoodsQty) {
		this.balanceGoodsQty = balanceGoodsQty;
	}

	public BigDecimal getAuditBalanceGoodsQty() {
		return auditBalanceGoodsQty;
	}

	public void setAuditBalanceGoodsQty(BigDecimal auditBalanceGoodsQty) {
		this.auditBalanceGoodsQty = auditBalanceGoodsQty;
	}

	public BigDecimal getDiffBalanceGoodsQty() {
		return diffBalanceGoodsQty;
	}

	public void setDiffBalanceGoodsQty(BigDecimal diffBalanceGoodsQty) {
		this.diffBalanceGoodsQty = diffBalanceGoodsQty;
	}

}
