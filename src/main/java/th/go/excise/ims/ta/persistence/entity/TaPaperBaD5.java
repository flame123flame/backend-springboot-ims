package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PAPER_BA_D5")
public class TaPaperBaD5 extends BaseEntity {
	
	private static final long serialVersionUID = -3304427831822549201L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D5_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D5_GEN", sequenceName = "TA_PAPER_BA_D5_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D5_SEQ")
	private Long paperBaD5Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "TAX_SUBMISSION_DATE")
	private LocalDate taxSubmissionDate;
	@Column(name = "NET_TAX_BY_VALUE")
	private BigDecimal netTaxByValue;
	@Column(name = "NET_TAX_BY_QTY")
	private BigDecimal netTaxByQty;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;
	@Column(name = "ANA_NET_TAX_BY_VALUE")
	private BigDecimal anaNetTaxByValue;
	@Column(name = "ANA_NET_TAX_BY_QTY")
	private BigDecimal anaNetTaxByQty;
	@Column(name = "ANA_NET_TAX_AMT")
	private BigDecimal anaNetTaxAmt;
	@Column(name = "DIFF_NET_TAX_BY_VALUE")
	private BigDecimal diffNetTaxByValue;
	@Column(name = "DIFF_NET_TAX_BY_QTY")
	private BigDecimal diffNetTaxByQty;
	@Column(name = "DIFF_NET_TAX_AMT")
	private BigDecimal diffNetTaxAmt;

	public Long getPaperBaD5Seq() {
		return paperBaD5Seq;
	}

	public void setPaperBaD5Seq(Long paperBaD5Seq) {
		this.paperBaD5Seq = paperBaD5Seq;
	}

	public String getPaperBaNumber() {
		return paperBaNumber;
	}

	public void setPaperBaNumber(String paperBaNumber) {
		this.paperBaNumber = paperBaNumber;
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

	public LocalDate getTaxSubmissionDate() {
		return taxSubmissionDate;
	}

	public void setTaxSubmissionDate(LocalDate taxSubmissionDate) {
		this.taxSubmissionDate = taxSubmissionDate;
	}

	public BigDecimal getNetTaxByValue() {
		return netTaxByValue;
	}

	public void setNetTaxByValue(BigDecimal netTaxByValue) {
		this.netTaxByValue = netTaxByValue;
	}

	public BigDecimal getNetTaxByQty() {
		return netTaxByQty;
	}

	public void setNetTaxByQty(BigDecimal netTaxByQty) {
		this.netTaxByQty = netTaxByQty;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

	public BigDecimal getAnaNetTaxByValue() {
		return anaNetTaxByValue;
	}

	public void setAnaNetTaxByValue(BigDecimal anaNetTaxByValue) {
		this.anaNetTaxByValue = anaNetTaxByValue;
	}

	public BigDecimal getAnaNetTaxByQty() {
		return anaNetTaxByQty;
	}

	public void setAnaNetTaxByQty(BigDecimal anaNetTaxByQty) {
		this.anaNetTaxByQty = anaNetTaxByQty;
	}

	public BigDecimal getAnaNetTaxAmt() {
		return anaNetTaxAmt;
	}

	public void setAnaNetTaxAmt(BigDecimal anaNetTaxAmt) {
		this.anaNetTaxAmt = anaNetTaxAmt;
	}

	public BigDecimal getDiffNetTaxByValue() {
		return diffNetTaxByValue;
	}

	public void setDiffNetTaxByValue(BigDecimal diffNetTaxByValue) {
		this.diffNetTaxByValue = diffNetTaxByValue;
	}

	public BigDecimal getDiffNetTaxByQty() {
		return diffNetTaxByQty;
	}

	public void setDiffNetTaxByQty(BigDecimal diffNetTaxByQty) {
		this.diffNetTaxByQty = diffNetTaxByQty;
	}

	public BigDecimal getDiffNetTaxAmt() {
		return diffNetTaxAmt;
	}

	public void setDiffNetTaxAmt(BigDecimal diffNetTaxAmt) {
		this.diffNetTaxAmt = diffNetTaxAmt;
	}

}
