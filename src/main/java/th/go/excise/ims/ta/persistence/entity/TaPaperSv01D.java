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
@Table(name = "TA_PAPER_SV01_D")
public class TaPaperSv01D extends BaseEntity {

	private static final long serialVersionUID = -2904794431425584723L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV01_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV01_D_GEN", sequenceName = "TA_PAPER_SV01_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV01_D_SEQ")
	private Long paperSv01DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "SERVICE_DOC_NO_QTY")
	private BigDecimal serviceDocNoQty;
	@Column(name = "INCOME_DAILY_ACCOUNT_QTY")
	private BigDecimal incomeDailyAccountQty;
	@Column(name = "PAYMENT_DOC_NO_QTY")
	private BigDecimal paymentDocNoQty;
	@Column(name = "AUDIT_QTY")
	private BigDecimal auditQty;
	@Column(name = "GOODS_QTY")
	private BigDecimal goodsQty;
	@Column(name = "DIFF_QTY")
	private BigDecimal diffQty;

	public Long getPaperSv01DSeq() {
		return paperSv01DSeq;
	}

	public void setPaperSv01DSeq(Long paperSv01DSeq) {
		this.paperSv01DSeq = paperSv01DSeq;
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

	public BigDecimal getServiceDocNoQty() {
		return serviceDocNoQty;
	}

	public void setServiceDocNoQty(BigDecimal serviceDocNoQty) {
		this.serviceDocNoQty = serviceDocNoQty;
	}

	public BigDecimal getIncomeDailyAccountQty() {
		return incomeDailyAccountQty;
	}

	public void setIncomeDailyAccountQty(BigDecimal incomeDailyAccountQty) {
		this.incomeDailyAccountQty = incomeDailyAccountQty;
	}

	public BigDecimal getPaymentDocNoQty() {
		return paymentDocNoQty;
	}

	public void setPaymentDocNoQty(BigDecimal paymentDocNoQty) {
		this.paymentDocNoQty = paymentDocNoQty;
	}

	public BigDecimal getAuditQty() {
		return auditQty;
	}

	public void setAuditQty(BigDecimal auditQty) {
		this.auditQty = auditQty;
	}

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public BigDecimal getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(BigDecimal diffQty) {
		this.diffQty = diffQty;
	}

}
