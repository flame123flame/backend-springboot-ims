package th.go.excise.ims.ta.vo;

import java.util.List;

public class AuditCalendarCriteriaFormVo {

	private List<AuditCalendarCheckboxVo> auditType;
	private List<AuditCalendarCheckboxVo> auditStatus;
	
	public List<AuditCalendarCheckboxVo> getAuditType() {
		return auditType;
	}
	public void setAuditType(List<AuditCalendarCheckboxVo> auditType) {
		this.auditType = auditType;
	}
	public List<AuditCalendarCheckboxVo> getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(List<AuditCalendarCheckboxVo> auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
