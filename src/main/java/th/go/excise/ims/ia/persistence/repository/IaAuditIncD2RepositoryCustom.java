package th.go.excise.ims.ia.persistence.repository;

import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;

import java.util.List;

public interface IaAuditIncD2RepositoryCustom {
	public void batchInsert( List<IaAuditIncD2Vo> wsAuditIncD2List );
}
