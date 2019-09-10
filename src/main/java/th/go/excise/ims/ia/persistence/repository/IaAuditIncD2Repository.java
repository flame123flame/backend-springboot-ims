
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;

public interface IaAuditIncD2Repository extends CommonJpaCrudRepository<IaAuditIncD2, Long>, IaAuditIncD2RepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditIncNo = :auditIncNo order by e.receiptDate")
	public List<IaAuditIncD2> findByAuditIncNoOrderByReceiptDate(@Param("auditIncNo") String auditIncNo);
}
