package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int020201JoinVo {
	/* questionnaire made detail */
	private BigDecimal id;
	private BigDecimal idSideDtl;
	private String checkFlag;
	private String status;
	private BigDecimal qtnLevel;
	private BigDecimal idMadeHdr;
	private String officeCode;
	private String note;
	
	/* questionnaire side detail */
	private BigDecimal idSide;
	private String sideDtl;
	private BigDecimal seq;
	private BigDecimal seqDtl;
	private BigDecimal idHeading;
	
	/* set children */
	private List<Int020201JoinVo> children = null;
	
	/* check null of level */
	private String statusSides;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getIdSideDtl() {
		return idSideDtl;
	}
	public void setIdSideDtl(BigDecimal idSideDtl) {
		this.idSideDtl = idSideDtl;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getQtnLevel() {
		return qtnLevel;
	}
	public void setQtnLevel(BigDecimal qtnLevel) {
		this.qtnLevel = qtnLevel;
	}
	public BigDecimal getIdMadeHdr() {
		return idMadeHdr;
	}
	public void setIdMadeHdr(BigDecimal idMadeHdr) {
		this.idMadeHdr = idMadeHdr;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
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
	public BigDecimal getIdHeading() {
		return idHeading;
	}
	public void setIdHeading(BigDecimal idHeading) {
		this.idHeading = idHeading;
	}
	public List<Int020201JoinVo> getChildren() {
		return children;
	}
	public void setChildren(List<Int020201JoinVo> children) {
		this.children = children;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatusSides() {
		return statusSides;
	}
	public void setStatusSides(String statusSides) {
		this.statusSides = statusSides;
	}
	
}