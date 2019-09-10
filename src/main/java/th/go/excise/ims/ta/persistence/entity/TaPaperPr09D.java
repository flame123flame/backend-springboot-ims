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
@Table(name = "TA_PAPER_PR09_D")
public class TaPaperPr09D extends BaseEntity {

	private static final long serialVersionUID = -6205380031096317113L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR09_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR09_D_GEN", sequenceName = "TA_PAPER_PR09_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR09_D_SEQ")
	private Long paperPr09DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "EXTERNAL_PRICE")
	private BigDecimal externalPrice;
	@Column(name = "DECLARE_PRICE")
	private BigDecimal declarePrice;
	@Column(name = "RETAIL_PRICE")
	private BigDecimal retailPrice;
	@Column(name = "TAX_PRICE")
	private BigDecimal taxPrice;
	@Column(name = "DIFF_PRICE")
	private BigDecimal diffPrice;

	public Long getPaperPr09DSeq() {
		return paperPr09DSeq;
	}

	public void setPaperPr09DSeq(Long paperPr09DSeq) {
		this.paperPr09DSeq = paperPr09DSeq;
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

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getExternalPrice() {
		return externalPrice;
	}

	public void setExternalPrice(BigDecimal externalPrice) {
		this.externalPrice = externalPrice;
	}

	public BigDecimal getDeclarePrice() {
		return declarePrice;
	}

	public void setDeclarePrice(BigDecimal declarePrice) {
		this.declarePrice = declarePrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getDiffPrice() {
		return diffPrice;
	}

	public void setDiffPrice(BigDecimal diffPrice) {
		this.diffPrice = diffPrice;
	}

}
