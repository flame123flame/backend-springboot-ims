
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicdupD;

public interface IaAuditLicdupDRepository extends CommonJpaCrudRepository<IaAuditLicdupD, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicdupNo = :auditLicdupNo order by e.printCount , e.runCheck ")
	public List<IaAuditLicdupD> findByAuditLicdupNoOrderByPrintCount(@Param("auditLicdupNo") String auditLicdupNo);
}
