package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int02010101Vo extends BaseEntity {
	private BigDecimal id;
	private BigDecimal idSide;
	private String sideDtl;
	private BigDecimal qtnLevel;
	private BigDecimal seq;
	private BigDecimal seqDtl;
	private List<Int02010101Vo> children;
	private BigDecimal idHeading;

	public BigDecimal getIdHeading() {
		return idHeading;
	}

	public void setIdHeading(BigDecimal idHeading) {
		this.idHeading = idHeading;
	}

	public List<Int02010101Vo> getChildren() {
		return children;
	}

	public void setChildren(List<Int02010101Vo> children) {
		this.children = children;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdSide() {
		return idSide;
	}

	public void setIdSide(BigDecimal idSide) {
		this.idSide = idSide;
	}

	public String getSideDtl() {
		return sideDtl;
	}

	public void setSideDtl(String sideDtl) {
		this.sideDtl = sideDtl;
	}

	public BigDecimal getQtnLevel() {
		return qtnLevel;
	}

	public void setQtnLevel(BigDecimal qtnLevel) {
		this.qtnLevel = qtnLevel;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public BigDecimal getSeqDtl() {
		return seqDtl;
	}

	public void setSeqDtl(BigDecimal seqDtl) {
		this.seqDtl = seqDtl;
	}
}
