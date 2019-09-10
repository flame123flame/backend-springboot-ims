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
@Table(name = "TA_PAPER_BA_D3")
public class TaPaperBaD3 extends BaseEntity {

	private static final long serialVersionUID = -6503780266316865350L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D3_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D3_GEN", sequenceName = "TA_PAPER_BA_D3_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D3_SEQ")
	private Long paperBaD3Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC_TEXT")
	private String goodsDescText;
	@Column(name = "TAX_QTY")
	private BigDecimal taxQty;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "GOODS_VALUE_AMT")
	private BigDecimal goodsValueAmt;

	public Long getPaperBaD3Seq() {
		return paperBaD3Seq;
	}

	public void setPaperBaD3Seq(Long paperBaD3Seq) {
		this.paperBaD3Seq = paperBaD3Seq;
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

	public String getGoodsDescText() {
		return goodsDescText;
	}

	public void setGoodsDescText(String goodsDescText) {
		this.goodsDescText = goodsDescText;
	}

	public BigDecimal getTaxQty() {
		return taxQty;
	}

	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getGoodsValueAmt() {
		return goodsValueAmt;
	}

	public void setGoodsValueAmt(BigDecimal goodsValueAmt) {
		this.goodsValueAmt = goodsValueAmt;
	}

}
