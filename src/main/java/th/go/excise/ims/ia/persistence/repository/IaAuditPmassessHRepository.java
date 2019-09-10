
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;

public interface IaAuditPmassessHRepository
		extends CommonJpaCrudRepository<IaAuditPmassessH, Long>, IaAuditPmassessHRepositoryCustom {

	List<IaAuditPmassessH> findByAuditPmassessNoAndIsDeleted(String auditPmassessNo, String isDeleted);

}
