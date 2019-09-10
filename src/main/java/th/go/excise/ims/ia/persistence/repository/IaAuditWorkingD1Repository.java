
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;

public interface IaAuditWorkingD1Repository extends CommonJpaCrudRepository<IaAuditWorkingD1, Long> {
	public List<IaAuditWorkingD1> findByAuditWorkingNo(String auditWorkingNo);

//	public List<IaAuditWorkingD1> findByIaAuditWorkingD1Id(String iaAuditWorkingD1Id);

	@Query(value = "Select e.* from IA_AUDIT_WORKING_D1 e  WHERE e.IA_AUDIT_WORKING_D1_ID = ?1 AND e.Is_Deleted = 'N'", nativeQuery = true)
	public IaAuditWorkingD1 findByIaAuditWorkingD1Id(String iaAuditWorkingD1Id);
}