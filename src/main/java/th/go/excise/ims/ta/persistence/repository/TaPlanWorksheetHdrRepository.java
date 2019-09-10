package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;

public interface TaPlanWorksheetHdrRepository extends CommonJpaCrudRepository<TaPlanWorksheetHdr, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear")
	public TaPlanWorksheetHdr findByBudgetYear(@Param("budgetYear") String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.planNumber = :planNumber")
	public TaPlanWorksheetHdr findByPlanNumber(@Param("planNumber") String planNumber);
	
	@Modifying
	@Query(value = "DELETE FROM TA_PLAN_WORKSHEET_HDR WHERE BUDGET_YEAR = :budgetYear", nativeQuery = true)
	public void forceDeleteByBudgetYear(@Param("budgetYear") String budgetYear);
	
}
