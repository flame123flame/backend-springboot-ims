
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD2;

public interface IaAuditLicD2Repository extends CommonJpaCrudRepository<IaAuditLicD2, Long> {
	
	@Query(value =" SELECT * FROM IA_AUDIT_LIC_D2 WHERE IS_DELETED = 'N' AND AUDIT_LIC_NO = ?1 ORDER BY LIC_COUNT DESC " ,   nativeQuery = true)
	public List<IaAuditLicD2> findByAuditLicNo(String auditLicNo );

}
