
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEf;

public interface IaRiskProEfRepository
    extends CommonJpaCrudRepository<IaRiskProEf, BigDecimal>
{
	@Query(value = " Select e.* from IA_RISK_PRO_EF e  WHERE e.PROJECT_YEAR = ?1 AND Is_Deleted = 'N' ", nativeQuery = true)
	public List<IaRiskProEf> findByProjectYear(String projectYear);

}
