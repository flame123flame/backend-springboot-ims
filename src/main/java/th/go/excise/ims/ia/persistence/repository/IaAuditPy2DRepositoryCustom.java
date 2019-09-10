package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.IaAuditPy2DVo;

public interface IaAuditPy2DRepositoryCustom {

	public List<IaAuditPy2DVo> findByAuditPy2No(String auditPy2No);

}
