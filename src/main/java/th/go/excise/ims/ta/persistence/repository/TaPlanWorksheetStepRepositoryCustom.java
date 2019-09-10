package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.vo.AuditStepVo;

public interface TaPlanWorksheetStepRepositoryCustom {
	
	public List<AuditStepVo> findByAuditPlanCode(String auditPlanCode);
	
}
