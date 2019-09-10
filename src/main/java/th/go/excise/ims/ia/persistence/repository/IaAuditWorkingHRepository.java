
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;

public interface IaAuditWorkingHRepository extends CommonJpaCrudRepository<IaAuditWorkingH, Long> {

	public IaAuditWorkingH findByAuditWorkingNo(String auditWorkingNo);
	
//	@Query(value = "Select e.* from IA_RISK_FACTORS_CONFIG e  WHERE e.ID_FACTORS = ?1 AND e.Is_Deleted = 'N'",nativeQuery = true)
//	public IaAuditWorkingH findByIdFactors(String auditWorkingNo);
}
