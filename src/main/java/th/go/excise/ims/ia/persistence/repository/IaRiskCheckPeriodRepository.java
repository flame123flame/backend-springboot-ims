
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;

public interface IaRiskCheckPeriodRepository
    extends CommonJpaCrudRepository<IaRiskCheckPeriod, BigDecimal>
{
	@Query(value = "SELECT e.* FROM IA_RISK_CHECK_PERIOD e ORDER by e.LONG_TIME ASC ", nativeQuery = true)
	public List<IaRiskCheckPeriod> findAllOderLongTimeDesc();


}
