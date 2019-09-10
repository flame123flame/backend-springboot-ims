package th.go.excise.ims.preferences.vo;

import th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty;

public class Ed03Vo extends ExciseCtrlDuty {

	private static final long serialVersionUID = 7339042584563576646L;
	
	private th.go.excise.ims.ia.vo.ExciseDepartmentVo exciseDepartmentVo;

	public th.go.excise.ims.ia.vo.ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(th.go.excise.ims.ia.vo.ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}



//	private String dutyGroupCode;
//	private String dutyGroupName;
//	private String resOffcode;
//

//	public String getDutyGroupCode() {
//		return dutyGroupCode;
//	}
//
//	public void setDutyGroupCode(String dutyGroupCode) {
//		this.dutyGroupCode = dutyGroupCode;
//	}
//
//	public String getDutyGroupName() {
//		return dutyGroupName;
//	}
//
//	public void setDutyGroupName(String dutyGroupName) {
//		this.dutyGroupName = dutyGroupName;
//	}
//
//	public String getResOffcode() {
//		return resOffcode;
//	}
//
//	public void setResOffcode(String resOffcode) {
//		this.resOffcode = resOffcode;
//	}

}
