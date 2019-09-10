package th.go.excise.ims.oa.vo;

import java.util.List;

public class Oa010801Vo {
	private String status;
	private String remark;
	private List<Oa010801ApproveVo> approves;
	private List<Oa010801CheckerVo> checkers;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Oa010801ApproveVo> getApproves() {
		return approves;
	}

	public void setApproves(List<Oa010801ApproveVo> approves) {
		this.approves = approves;
	}

	public List<Oa010801CheckerVo> getCheckers() {
		return checkers;
	}

	public void setCheckers(List<Oa010801CheckerVo> checkers) {
		this.checkers = checkers;
	}
}
