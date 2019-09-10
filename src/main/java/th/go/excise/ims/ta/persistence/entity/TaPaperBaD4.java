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
@Table(name = "TA_PAPER_BA_D4")
public class TaPaperBaD4 extends BaseEntity {

	private static final long serialVersionUID = -5708323574322691417L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D4_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D4_GEN", sequenceName = "TA_PAPER_BA_D4_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D4_SEQ")
	private Long paperBaD4Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "TAX_RATE_BY_PRICE")
	private BigDecimal taxRateByPrice;
	@Column(name = "TAX_RATE_BY_QTY")
	private BigDecimal taxRateByQty;
	@Column(name = "ANA_TAX_RATE_BY_PRICE")
	private BigDecimal anaTaxRateByPrice;
	@Column(name = "ANA_TAX_RATE_BY_QTY")
	private BigDecimal anaTaxRateByQty;
	@Column(name = "DIFF_TAX_RATE_BY_PRICE")
	private BigDecimal diffTaxRateByPrice;
	@Column(name = "DIFF_TAX_RATE_BY_QTY")
	private BigDecimal diffTaxRateByQty;

	public Long getPaperBaD4Seq() {
		return paperBaD4Seq;
	}

	public void setPaperBaD4Seq(Long paperBaD4Seq) {
		this.paperBaD4Seq = paperBaD4Seq;
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

	public BigDecimal getTaxRateByPrice() {
		return taxRateByPrice;
	}

	public void setTaxRateByPrice(BigDecimal taxRateByPrice) {
		this.taxRateByPrice = taxRateByPrice;
	}

	public BigDecimal getTaxRateByQty() {
		return taxRateByQty;
	}

	public void setTaxRateByQty(BigDecimal taxRateByQty) {
		this.taxRateByQty = taxRateByQty;
	}

	public BigDecimal getAnaTaxRateByPrice() {
		return anaTaxRateByPrice;
	}

	public void setAnaTaxRateByPrice(BigDecimal anaTaxRateByPrice) {
		this.anaTaxRateByPrice = anaTaxRateByPrice;
	}

	public BigDecimal getAnaTaxRateByQty() {
		return anaTaxRateByQty;
	}

	public void setAnaTaxRateByQty(BigDecimal anaTaxRateByQty) {
		this.anaTaxRateByQty = anaTaxRateByQty;
	}

	public BigDecimal getDiffTaxRateByPrice() {
		return diffTaxRateByPrice;
	}

	public void setDiffTaxRateByPrice(BigDecimal diffTaxRateByPrice) {
		this.diffTaxRateByPrice = diffTaxRateByPrice;
	}

	public BigDecimal getDiffTaxRateByQty() {
		return diffTaxRateByQty;
	}

	public void setDiffTaxRateByQty(BigDecimal diffTaxRateByQty) {
		this.diffTaxRateByQty = diffTaxRateByQty;
	}

}
