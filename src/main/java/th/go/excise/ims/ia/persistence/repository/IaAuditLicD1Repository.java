
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD1;

public interface IaAuditLicD1Repository extends CommonJpaCrudRepository<IaAuditLicD1, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicNo = :auditLicNo order by e.seqNo ")
	public List<IaAuditLicD1> findByAuditLicNoOrderBySeqNo(@Param("auditLicNo") String auditLicNo);
}
