package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int020201SidesFormVo {
	private BigDecimal idMadeHdr;
	private BigDecimal idSide;
	private String sideName;
	private BigDecimal idHead;
	private String status;
	private List<Int020201JoinVo> sides = null;

	/* check null of level */
	private Boolean statusSides = false;

	public BigDecimal getIdMadeHdr() {
		return idMadeHdr;
	}

	public void setIdMadeHdr(BigDecimal idMadeHdr) {
		this.idMadeHdr = idMadeHdr;
	}

	public BigDecimal getIdSide() {
		return idSide;
	}

	public void setIdSide(BigDecimal idSide) {
		this.idSide = idSide;
	}

	public String getSideName() {
		return sideName;
	}

	public void setSideName(String sideName) {
		this.sideName = sideName;
	}

	public BigDecimal getIdHead() {
		return idHead;
	}

	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Int020201JoinVo> getSides() {
		return sides;
	}

	public void setSides(List<Int020201JoinVo> sides) {
		this.sides = sides;
	}

	public Boolean getStatusSides() {
		return statusSides;
	}

	public void setStatusSides(Boolean statusSides) {
		this.statusSides = statusSides;
	}

}
