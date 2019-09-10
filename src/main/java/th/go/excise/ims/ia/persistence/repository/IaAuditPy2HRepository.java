
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;

public interface IaAuditPy2HRepository
		extends CommonJpaCrudRepository<IaAuditPy2H, BigDecimal>, IaAuditPy2HRepositoryCustom {

	IaAuditPy2H findByAuditPy2NoAndIsDeleted(String auditPy2No, String isDeleted);

}
