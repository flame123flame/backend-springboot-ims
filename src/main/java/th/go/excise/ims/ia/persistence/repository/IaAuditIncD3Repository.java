
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;

public interface IaAuditIncD3Repository extends CommonJpaCrudRepository<IaAuditIncD3, Long>, IaAuditIncD3RepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditIncNo = :auditIncNo order by e.taxCode")
	public List<IaAuditIncD3> findByAuditIncNoOrderByTaxCode(@Param("auditIncNo") String auditIncNo);
}
