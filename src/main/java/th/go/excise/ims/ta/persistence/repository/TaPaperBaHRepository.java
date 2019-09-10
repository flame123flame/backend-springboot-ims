package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;

public interface TaPaperBaHRepository extends CommonJpaCrudRepository<TaPaperBaH, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperBaNumber = :paperBaNumber")
	public TaPaperBaH findByPaperBaNumber(@Param("paperBaNumber") String paperBaNumber);
	
	@Query("select new java.lang.String(e.paperBaNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPlanCode = :auditPlanCode order by e.paperBaNumber desc")
	public List<String> findPaperBaNumberByAuditPlanCode(@Param("auditPlanCode") String auditPlanCode);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.newRegId = :newRegId and e.officeCode = :officeCode and e.budgetYear = :budgetYear")
	public TaPaperBaH findByNewRegIdAndOfficeCodeAndBudgetYear(@Param("newRegId") String newRegId, @Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);
	
}
