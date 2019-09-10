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
@Table(name = "TA_PAPER_SV02_D")
public class TaPaperSv02D extends BaseEntity {

	private static final long serialVersionUID = -3926148496915786760L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV02_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV02_D_GEN", sequenceName = "TA_PAPER_SV02_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV02_D_SEQ")
	private Long paperSv02DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "INVOICE_PRICE")
	private BigDecimal invoicePrice;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "AUDIT_PRICE")
	private BigDecimal auditPrice;
	@Column(name = "GOODS_PRICE")
	private BigDecimal goodsPrice;
	@Column(name = "DIFF_PRICE")
	private BigDecimal diffPrice;

	public Long getPaperSv02DSeq() {
		return paperSv02DSeq;
	}

	public void setPaperSv02DSeq(Long paperSv02DSeq) {
		this.paperSv02DSeq = paperSv02DSeq;
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

	public BigDecimal getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(BigDecimal invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getAuditPrice() {
		return auditPrice;
	}

	public void setAuditPrice(BigDecimal auditPrice) {
		this.auditPrice = auditPrice;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getDiffPrice() {
		return diffPrice;
	}

	public void setDiffPrice(BigDecimal diffPrice) {
		this.diffPrice = diffPrice;
	}

}
