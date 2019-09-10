package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.vo.IaAuditPmQtHVo;

public interface IaAuditPmqtHRepositoryCustom {
	
	public List<IaAuditPmqtH> getAuditPmQtNoList();
	
	public List<IaAuditPmQtHVo> filterIaPmQtByAuditPmQtNo(String auditPmqtNo);

}
