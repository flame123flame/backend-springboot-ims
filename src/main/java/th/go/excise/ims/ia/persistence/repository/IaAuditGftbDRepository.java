
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbD;

public interface IaAuditGftbDRepository extends CommonJpaCrudRepository<IaAuditGftbD, BigDecimal> {
	public List<IaAuditGftbD> findByAuditGftbNoAndIsDeletedOrderByAccNoAscGftbSeqAsc(String auditGftbNo, String isDeleted);
}
