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
@Table(name = "TA_PAPER_PR11_D")
public class TaPaperPr11D extends BaseEntity {

	private static final long serialVersionUID = 6548498277535625984L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR11_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR11_D_GEN", sequenceName = "TA_PAPER_PR11_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR11_D_SEQ")
	private Long paperPr11DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "TAX_QTY")
	private BigDecimal taxQty;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "TAX_VALUE")
	private BigDecimal taxValue;
	@Column(name = "TAX_RATE_BY_VALUE")
	private BigDecimal taxRateByValue;
	@Column(name = "TAX_RATE_BY_QTY")
	private BigDecimal taxRateByQty;
	@Column(name = "TAX_ADDITIONAL")
	private BigDecimal taxAdditional;
	@Column(name = "PENALTY_AMT")
	private BigDecimal penaltyAmt;
	@Column(name = "SURCHARGE_AMT")
	private BigDecimal surchargeAmt;
	@Column(name = "MOI_TAX_AMT")
	private BigDecimal moiTaxAmt;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;

	public Long getPaperPr11DSeq() {
		return paperPr11DSeq;
	}

	public void setPaperPr11DSeq(Long paperPr11DSeq) {
		this.paperPr11DSeq = paperPr11DSeq;
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

	public BigDecimal getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(BigDecimal taxValue) {
		this.taxValue = taxValue;
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

	public BigDecimal getTaxAdditional() {
		return taxAdditional;
	}

	public void setTaxAdditional(BigDecimal taxAdditional) {
		this.taxAdditional = taxAdditional;
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
