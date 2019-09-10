package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSelect;

public interface TaPlanWorksheetSelectRepository extends CommonJpaCrudRepository<TaPlanWorksheetSelect, Long>, TaPlanWorksheetSelectRepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.newRegId = :newRegId")
	public List<TaPlanWorksheetSelect> findByBudgetYearAndNewRegId(@Param("budgetYear") String budgetYear, @Param("newRegId")  String newRegId);	
	
	@Modifying
	@Query(value = "DELETE FROM TA_PLAN_WORKSHEET_SELECT WHERE BUDGET_YEAR = :budgetYear", nativeQuery = true)
	public void forceDeleteByBudgetYear(@Param("budgetYear") String budgetYear);
	
}
