
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;

public interface IaAuditPmqtHRepository
    extends CommonJpaCrudRepository<IaAuditPmqtH, BigDecimal> , IaAuditPmqtHRepositoryCustom
{
	
	List<IaAuditPmqtH> findByAuditPmqtNoAndIsDeleted(String auditPmqtNo, String isDeleted);
	
}
