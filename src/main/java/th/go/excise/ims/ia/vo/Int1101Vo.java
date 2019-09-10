package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowDetail;

public class Int1101Vo extends IaConcludeFollowDetail {
	private String approveDateString;
	private String dateFromString;
	private String ateToString;

	public String getApproveDateString() {
		return approveDateString;
	}

	public void setApproveDateString(String approveDateString) {
		this.approveDateString = approveDateString;
	}

	public String getDateFromString() {
		return dateFromString;
	}

	public void setDateFromString(String dateFromString) {
		this.dateFromString = dateFromString;
	}

	public String getAteToString() {
		return ateToString;
	}

	public void setAteToString(String ateToString) {
		this.ateToString = ateToString;
	}

}
