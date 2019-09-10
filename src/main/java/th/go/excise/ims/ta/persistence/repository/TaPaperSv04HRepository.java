package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv04H;

public interface TaPaperSv04HRepository extends CommonJpaCrudRepository<TaPaperSv04H, Long> {
	
	@Query("select new java.lang.String(e.paperSvNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPlanCode = :auditPlanCode order by e.paperSvNumber desc")
	public List<String> findPaperSvNumberByAuditPlanCode(@Param("auditPlanCode") String auditPlanCode);
	
}
