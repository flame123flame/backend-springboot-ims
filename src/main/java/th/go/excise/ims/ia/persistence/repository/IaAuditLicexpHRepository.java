
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpH;

public interface IaAuditLicexpHRepository extends CommonJpaCrudRepository<IaAuditLicexpH, Long>{

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditLicexpNo desc")
	public List<IaAuditLicexpH> findIaAuditLicexpHAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicexpNo = :auditLicexpNo")
	public IaAuditLicexpH findByAuditLicexpNo(@Param("auditLicexpNo") String auditLicexpNo);
}
