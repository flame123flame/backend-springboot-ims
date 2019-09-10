package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.IaAuditPmQtDVo;

public interface IaAuditPmqtDRepositoryCustom {
	
	public List<IaAuditPmQtDVo> filterIaPmQtDByAuditPmQtNo(String auditPmQtNo, String formCode);

}
