package th.go.excise.ims.bi.persistence.entity;

import java.time.LocalDate;

public class TempRegist {

	private String regId;
	private String newregId;
	private String facId;
	private String facSeq;
	private String facAddrseq;
	private String cusSeq;
	private String cusAddrseq;
	private String divcode;
	private String offcode;
	private String regReason;
	private String regStatus;
	private LocalDate statusDate;
	private String activeFlag;
	private LocalDate updDate;
	private String cusId;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacSeq() {
		return facSeq;
	}

	public void setFacSeq(String facSeq) {
		this.facSeq = facSeq;
	}

	public String getFacAddrseq() {
		return facAddrseq;
	}

	public void setFacAddrseq(String facAddrseq) {
		this.facAddrseq = facAddrseq;
	}

	public String getCusSeq() {
		return cusSeq;
	}

	public void setCusSeq(String cusSeq) {
		this.cusSeq = cusSeq;
	}

	public String getCusAddrseq() {
		return cusAddrseq;
	}

	public void setCusAddrseq(String cusAddrseq) {
		this.cusAddrseq = cusAddrseq;
	}

	public String getDivcode() {
		return divcode;
	}

	public void setDivcode(String divcode) {
		this.divcode = divcode;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getRegReason() {
		return regReason;
	}

	public void setRegReason(String regReason) {
		this.regReason = regReason;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public LocalDate getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public LocalDate getUpdDate() {
		return updDate;
	}

	public void setUpdDate(LocalDate updDate) {
		this.updDate = updDate;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

}
