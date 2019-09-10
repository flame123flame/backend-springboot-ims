
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public interface IaAuditIncHRepository extends CommonJpaCrudRepository<IaAuditIncH, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditIncNo desc")
	public List<IaAuditIncH> findIaAuditIncHAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditIncNo = :auditIncNo")
	public IaAuditIncH findByAuditNo(@Param("auditIncNo") String auditIncNo);

}
