
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessD;

public interface IaAuditPmassessDRepository
		extends CommonJpaCrudRepository<IaAuditPmassessD, BigDecimal>, IaAuditPmassessDRepositoryCustom {

}
