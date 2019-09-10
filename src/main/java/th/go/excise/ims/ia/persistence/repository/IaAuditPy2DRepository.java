
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2D;

public interface IaAuditPy2DRepository
		extends CommonJpaCrudRepository<IaAuditPy2D, BigDecimal>, IaAuditPy2DRepositoryCustom {
}
