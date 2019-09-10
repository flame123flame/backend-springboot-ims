
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendD;

public interface IaAuditIncSendDRepository extends CommonJpaCrudRepository<IaAuditIncSendD, BigDecimal>, IaAuditIncSendDRepositoryCustom {

	public List<IaAuditIncSendD> findByIncsendNoAndIsDeletedOrderByIncsendNo(String incsendNo, String isDeleted);

}
