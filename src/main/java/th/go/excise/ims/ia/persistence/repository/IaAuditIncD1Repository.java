package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;

public interface IaAuditIncD1Repository extends CommonJpaCrudRepository<IaAuditIncD1, Long>, IaAuditIncD1RepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditIncNo = :auditIncNo order by e.seqNo ")
	public List<IaAuditIncD1> findByAuditIncNoOrderBySeqNo(@Param("auditIncNo") String auditIncNo);
	
}
