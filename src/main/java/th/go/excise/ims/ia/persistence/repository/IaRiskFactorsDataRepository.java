
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;

public interface IaRiskFactorsDataRepository extends CommonJpaCrudRepository<IaRiskFactorsData, BigDecimal> {
	@Query(value = "Select e.* from IA_RISK_FACTORS_DATA e   WHERE e.ID_FACTORS = ?1 AND e.INSPECTION_WORK = ?2 AND e.BUDGET_YEAR = ?3 AND e.Is_Deleted = 'N'", nativeQuery = true)
	public  List<IaRiskFactorsData> findByIdFactorsByInspectionWorkByBudgetYear(BigDecimal idFactors, BigDecimal inspectionWork,
			String budgetYear);

}
