package th.go.excise.ims.oa.vo;

import java.util.List;

public class Oa041301Vo {
	private String status;
	private String remark;
	private List<Oa041301ApproveVo> approves;
	private List<Oa041301CheckerVo> checkers;
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
	public List<Oa041301ApproveVo> getApproves() {
		return approves;
	}
	public void setApproves(List<Oa041301ApproveVo> approves) {
		this.approves = approves;
	}
	public List<Oa041301CheckerVo> getCheckers() {
		return checkers;
	}
	public void setCheckers(List<Oa041301CheckerVo> checkers) {
		this.checkers = checkers;
	}
}
