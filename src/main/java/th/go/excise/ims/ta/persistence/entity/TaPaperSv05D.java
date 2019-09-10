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
@Table(name = "TA_PAPER_SV05_D")
public class TaPaperSv05D extends BaseEntity {

	private static final long serialVersionUID = -6438900804168078052L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV05_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV05_D_GEN", sequenceName = "TA_PAPER_SV05_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV05_D_SEQ")
	private Long paperSv05DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "GOODS_QTY")
	private BigDecimal goodsQty;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "GOODS_VALUE")
	private BigDecimal goodsValue;
	@Column(name = "TAX_RATE_BY_VALUE")
	private BigDecimal taxRateByValue;
	@Column(name = "TAX_RATE_BY_QTY")
	private BigDecimal taxRateByQty;
	@Column(name = "TAX_ADDITIONAL_AMT")
	private BigDecimal taxAdditionalAmt;
	@Column(name = "PENALTY_AMT")
	private BigDecimal penaltyAmt;
	@Column(name = "SURCHARGE_AMT")
	private BigDecimal surchargeAmt;
	@Column(name = "MOI_TAX_AMT")
	private BigDecimal moiTaxAmt;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;

	public Long getPaperSv05DSeq() {
		return paperSv05DSeq;
	}

	public void setPaperSv05DSeq(Long paperSv05DSeq) {
		this.paperSv05DSeq = paperSv05DSeq;
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

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(BigDecimal goodsValue) {
		this.goodsValue = goodsValue;
	}

	public BigDecimal getTaxRateByValue() {
		return taxRateByValue;
	}

	public void setTaxRateByValue(BigDecimal taxRateByValue) {
		this.taxRateByValue = taxRateByValue;
	}

	public BigDecimal getTaxRateByQty() {
		return taxRateByQty;
	}

	public void setTaxRateByQty(BigDecimal taxRateByQty) {
		this.taxRateByQty = taxRateByQty;
	}

	public BigDecimal getTaxAdditionalAmt() {
		return taxAdditionalAmt;
	}

	public void setTaxAdditionalAmt(BigDecimal taxAdditionalAmt) {
		this.taxAdditionalAmt = taxAdditionalAmt;
	}

	public BigDecimal getPenaltyAmt() {
		return penaltyAmt;
	}

	public void setPenaltyAmt(BigDecimal penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	public BigDecimal getSurchargeAmt() {
		return surchargeAmt;
	}

	public void setSurchargeAmt(BigDecimal surchargeAmt) {
		this.surchargeAmt = surchargeAmt;
	}

	public BigDecimal getMoiTaxAmt() {
		return moiTaxAmt;
	}

	public void setMoiTaxAmt(BigDecimal moiTaxAmt) {
		this.moiTaxAmt = moiTaxAmt;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

}
