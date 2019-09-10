
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfKpi;

public interface IaRiskProEfKpiRepository
    extends CommonJpaCrudRepository<IaRiskProEfKpi, BigDecimal>
{

	@Query(value = " Select e.* from IA_RISK_PRO_EF_KPI e  WHERE e.TS_ID = ?1 AND Is_Deleted = 'N' ", nativeQuery = true)
	public List<IaRiskProEfKpi> findByTsId(BigDecimal tsId);
}
