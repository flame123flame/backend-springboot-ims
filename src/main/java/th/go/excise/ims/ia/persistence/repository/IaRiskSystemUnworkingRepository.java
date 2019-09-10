
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;

public interface IaRiskSystemUnworkingRepository
    extends CommonJpaCrudRepository<IaRiskSystemUnworking, BigDecimal>
{

	@Query(value = "Select e.* from IA_RISK_SYSTEM_UNWORKING e  WHERE CONCAT(e.YEAR,e.MONTH) >= ?1 AND  CONCAT(e.YEAR,e.MONTH) <= ?2 ", nativeQuery = true)
	public  List<IaRiskSystemUnworking> findByStartMonthByEndMonth(String startMonth,String endMonth);
	
}
