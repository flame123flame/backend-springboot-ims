
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGf;

public interface IaAuditIncGfRepository extends CommonJpaCrudRepository<IaAuditIncGf, BigDecimal>, IaAuditIncGfCustomRepository {

}
