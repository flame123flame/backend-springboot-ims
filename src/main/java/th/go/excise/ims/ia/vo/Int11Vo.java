package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowHdr;

public class Int11Vo extends IaConcludeFollowHdr {
	private String dateToString;
	private String dateFromString;
	private String approveDateString;
	
	/* ExciseDepartmentVo */
	private ExciseDepartmentVo exciseDepartmentVo;

	public String getDateToString() {
		return dateToString;
	}

	public void setDateToString(String dateToString) {
		this.dateToString = dateToString;
	}

	public String getDateFromString() {
		return dateFromString;
	}

	public void setDateFromString(String dateFromString) {
		this.dateFromString = dateFromString;
	}

	public String getApproveDateString() {
		return approveDateString;
	}

	public void setApproveDateString(String approveDateString) {
		this.approveDateString = approveDateString;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
