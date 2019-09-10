
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmcommitH;

public interface IaAuditPmcommitHRepository extends CommonJpaCrudRepository<IaAuditPmcommitH, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditPmcommitNo desc")
	public List<IaAuditPmcommitH> findIaAuditPmcommitHAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPmcommitNo = :auditPmcommitNo")
	public IaAuditPmcommitH findByAuditPmcommitNo(@Param("auditPmcommitNo") String auditPmcommitNo);

}
