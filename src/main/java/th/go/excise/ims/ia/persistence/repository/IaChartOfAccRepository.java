
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;

public interface IaChartOfAccRepository
		extends CommonJpaCrudRepository<IaChartOfAcc, BigDecimal>, IaChartOfAccRepositoryCustom {
	
	@Query(value = " SELECT * FROM IA_CHART_OF_ACC WHERE IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public List<IaChartOfAcc> getCoaCodeList();
	
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.coaCode = :coaCode")
	public IaChartOfAcc findBycoaCode (@Param("coaCode") String coaCode);
}
