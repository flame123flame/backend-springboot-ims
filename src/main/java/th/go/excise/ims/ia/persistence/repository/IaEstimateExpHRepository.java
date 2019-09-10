
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;

public interface IaEstimateExpHRepository
    extends CommonJpaCrudRepository<IaEstimateExpH, Long>
{

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.estExpNo = :estExpNo")
	public IaEstimateExpH findByEstExpNo(@Param("estExpNo") String estExpNo);
	
	@Query(value = " SELECT * FROM IA_ESTIMATE_EXP_H WHERE IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public List<IaEstimateExpH> getEstimateNoList();
	

}
