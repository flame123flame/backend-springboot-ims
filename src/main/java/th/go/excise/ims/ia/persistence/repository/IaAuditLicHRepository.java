
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicH;

public interface IaAuditLicHRepository extends CommonJpaCrudRepository<IaAuditLicH, Long>, IaAuditLicHRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditLicSeq desc")
	public List<IaAuditLicH> findIaAuditLicHAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicNo = :auditLicNo")
	public IaAuditLicH findByAuditLicNo(@Param("auditLicNo") String auditLicNo);
}
