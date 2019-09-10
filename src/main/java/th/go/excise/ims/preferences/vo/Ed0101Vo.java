package th.go.excise.ims.preferences.vo;

import th.go.excise.ims.ia.vo.ExciseDepartmentVo;

public class Ed0101Vo {

	private String edPersonName;
	private String edPositionName;
	private String edPersonId;
	private String edOffcode;
	private String edLogin;

	ExciseDepartmentVo exciseDepartmentVo;

	public String getEdPersonName() {
		return edPersonName;
	}

	public void setEdPersonName(String edPersonName) {
		this.edPersonName = edPersonName;
	}

	public String getEdPositionName() {
		return edPositionName;
	}

	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}

	public String getEdPersonId() {
		return edPersonId;
	}

	public void setEdPersonId(String edPersonId) {
		this.edPersonId = edPersonId;
	}

	public String getEdOffcode() {
		return edOffcode;
	}

	public void setEdOffcode(String edOffcode) {
		this.edOffcode = edOffcode;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public String getEdLogin() {
		return edLogin;
	}

	public void setEdLogin(String edLogin) {
		this.edLogin = edLogin;
	}

}
