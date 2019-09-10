package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;

public interface IaAuditIncD3RepositoryCustom {
	public void batchInsert( List<IaAuditIncD3Vo> wsAuditIncD3List );
}
