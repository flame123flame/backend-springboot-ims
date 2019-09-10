
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfTask;

public interface IaRiskProEfTaskRepository
    extends CommonJpaCrudRepository<IaRiskProEfTask, BigDecimal>
{
	@Query(value = " Select e.* from IA_RISK_PRO_EF_TASK e  WHERE e.PE_ID = ?1 AND Is_Deleted = 'N' ", nativeQuery = true)
	public List<IaRiskProEfTask> findByPeId(BigDecimal peId);
}
