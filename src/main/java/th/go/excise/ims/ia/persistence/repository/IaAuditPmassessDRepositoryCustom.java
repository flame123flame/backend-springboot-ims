package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.IaAuditPmassessDVo;

public interface IaAuditPmassessDRepositoryCustom {

	public List<IaAuditPmassessDVo> filterIaPaAssessDByAuditPmassessNo(String auditPmassessNo, String formCode);

}
