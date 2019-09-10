
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;

public interface IaRiskFactorsConfigAllRepository
    extends CommonJpaCrudRepository<IaRiskFactorsConfigAll, BigDecimal>
{
	
	@Query(value = "Select e.* from IA_RISK_FACTORS_CONFIG_ALL e  WHERE e.BUDGET_YEAR = ?1 AND e.INSPECTION_WORK = ?2 AND e.Is_Deleted = 'N' ",nativeQuery = true)
	public IaRiskFactorsConfigAll findByBudgetYearByInspectionWork(String budgetYear,BigDecimal inspectionWork);

}
