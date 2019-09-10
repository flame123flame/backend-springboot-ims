package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int020101SideVo {
	private BigDecimal id;
	private BigDecimal idHead;
	private String quantity;
	private String seq;
	private String sideName;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdHead() {
		return idHead;
	}

	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSideName() {
		return sideName;
	}

	public void setSideName(String sideName) {
		this.sideName = sideName;
	}

}
