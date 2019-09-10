package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;

public interface IaAuditIncD1RepositoryCustom {
	public void batchInsert( List<IaAuditIncD1> wsAuditIncD1s ,String auditIncNo);
	
}
