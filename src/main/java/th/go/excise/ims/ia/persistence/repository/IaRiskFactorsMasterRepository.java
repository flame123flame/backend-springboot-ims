
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public interface IaRiskFactorsMasterRepository extends CommonJpaCrudRepository<IaRiskFactorsMaster, BigDecimal>
{
	@Query(value = "Select e.* from IA_RISK_FACTORS_MASTER e  WHERE e.inspection_Work = ?1 AND Is_Deleted = 'N'",nativeQuery = true)
	public List<IaRiskFactorsMaster> findByInspectionWork(BigDecimal inspectionWork);

}
