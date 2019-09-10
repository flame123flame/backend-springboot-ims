
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1D;

public interface IaAuditPy1DRepository
    extends CommonJpaCrudRepository<IaAuditPy1D, Long>
{
	@Query(value = "Select DTL.* from IA_AUDIT_PY1_D DTL  WHERE DTL.AUDIT_PY1_NO = ?1 AND DTL.Is_Deleted = 'N' ", nativeQuery = true)
	public List<IaAuditPy1D> findByAuditPy1No(String auditPy1No);

}
