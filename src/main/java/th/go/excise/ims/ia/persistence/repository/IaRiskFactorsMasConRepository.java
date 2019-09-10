
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMasCon;

public interface IaRiskFactorsMasConRepository
    extends CommonJpaCrudRepository<IaRiskFactorsMasCon, BigDecimal>
{
	
	@Query(value = "Select e.* from IA_RISK_FACTORS_MAS_CON e  WHERE e.ID_MASTER = ?1 AND Is_Deleted = 'N'",nativeQuery = true)
	public IaRiskFactorsMasCon findByIdMaster(BigDecimal idMaster);

}
