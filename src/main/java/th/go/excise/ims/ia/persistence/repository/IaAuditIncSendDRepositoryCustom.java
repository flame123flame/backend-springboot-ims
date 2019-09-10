package th.go.excise.ims.ia.persistence.repository;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendD;
import th.go.excise.ims.ia.vo.SearchVo;

public interface IaAuditIncSendDRepositoryCustom {
	public IaAuditIncSendD summaryByIncsendNo(SearchVo request);
}
