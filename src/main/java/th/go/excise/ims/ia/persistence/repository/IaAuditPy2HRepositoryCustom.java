package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;
import th.go.excise.ims.ia.vo.IaAuditPy2HVo;

public interface IaAuditPy2HRepositoryCustom {

	public List<IaAuditPy2H> getAuditPy2NoList();

	public List<IaAuditPy2HVo> filterIaPmPy2ByAuditPy2No(String auditPy2No);

}
