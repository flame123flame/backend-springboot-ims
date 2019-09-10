
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;

public interface IaRiskFactorsRepository
    extends CommonJpaCrudRepository<IaRiskFactors, BigDecimal>
{
	@Query(value = "Select e.* from IA_RISK_FACTORS e  WHERE e.inspection_Work = ?1 And e.BUDGET_YEAR = ?2 ",nativeQuery = true)
	public List<IaRiskFactors> findByInspectionWorkByBudgetYear(BigDecimal inspectionWork,String budgetYear );
	
	@Query(value = "Select e.* from IA_RISK_FACTORS e  WHERE e.ID_MASTER = ?1 ",nativeQuery = true)
	public List<IaRiskFactors> findByIdMaster(BigDecimal idMaster );
}
