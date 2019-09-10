
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpD;

public interface IaAuditLicexpDRepository extends CommonJpaCrudRepository<IaAuditLicexpD, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicexpNo = :auditLicexpNo order by e.expDate")
	public List<IaAuditLicexpD> findByAuditLicexpNoOrderByExpDate(@Param("auditLicexpNo") String auditLicexpNo);
}
