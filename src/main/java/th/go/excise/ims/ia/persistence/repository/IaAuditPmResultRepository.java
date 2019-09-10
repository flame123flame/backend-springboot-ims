
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmResult;

public interface IaAuditPmResultRepository extends CommonJpaCrudRepository<IaAuditPmResult, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditPmresultNo desc")
	public List<IaAuditPmResult> findIaAuditPmResultAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPmresultNo = :auditPmresultNo")
	public IaAuditPmResult findByAuditPmresultNo(@Param("auditPmresultNo") String auditPmresultNo);
}
