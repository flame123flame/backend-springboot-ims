
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;

public interface IaAuditPy1HRepository extends CommonJpaCrudRepository<IaAuditPy1H, Long> {

	@Query(value = "Select HDR.* from IA_AUDIT_PY1_H HDR  WHERE HDR.AUDIT_PY1_NO = ?1 AND HDR.Is_Deleted = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public IaAuditPy1H findByAuditPy1No(String auditPy1No);

	@Query(value = " SELECT  HDR.* FROM IA_AUDIT_PY1_H HDR WHERE HDR.IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public List<IaAuditPy1H> getAuditPy1NoList();

}