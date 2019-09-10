package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;
import th.go.excise.ims.ia.vo.IaAuditPmassessHVo;

public interface IaAuditPmassessHRepositoryCustom {

	public List<IaAuditPmassessH> getAuditPmassessNoList();

	public List<IaAuditPmassessHVo> filterIaPmAssessByAuditPmassessNo(String auditPmassessNo);

}
