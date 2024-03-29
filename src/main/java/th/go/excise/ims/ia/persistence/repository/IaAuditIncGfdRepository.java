
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfd;

public interface IaAuditIncGfdRepository extends CommonJpaCrudRepository<IaAuditIncGfd, BigDecimal> {

	public List<IaAuditIncGfd> findByAuditIncGfNoAndIsDeleted(String auditIncGfNo, String string);

}
