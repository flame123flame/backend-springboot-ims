package th.go.excise.ims.bi.persistence.entity;

import java.time.LocalDate;

public class TempRegDuty {

	private String newregId;
	private String regId;
	private String groupId;
	private String payrtnFlag;
	private String factypeOil;
	private LocalDate regDate;
	private LocalDate prodDate;
	private String activeFlag;

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPayrtnFlag() {
		return payrtnFlag;
	}

	public void setPayrtnFlag(String payrtnFlag) {
		this.payrtnFlag = payrtnFlag;
	}

	public String getFactypeOil() {
		return factypeOil;
	}

	public void setFactypeOil(String factypeOil) {
		this.factypeOil = factypeOil;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public LocalDate getProdDate() {
		return prodDate;
	}

	public void setProdDate(LocalDate prodDate) {
		this.prodDate = prodDate;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}
