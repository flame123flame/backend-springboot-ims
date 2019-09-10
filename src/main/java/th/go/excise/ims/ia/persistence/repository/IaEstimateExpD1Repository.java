
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpD1;

public interface IaEstimateExpD1Repository
    extends CommonJpaCrudRepository<IaEstimateExpD1, Long>
{
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.estExpNo = :estExpNo order by e.seqNo ")
	public List<IaEstimateExpD1> findIaEstimateD1ByestExpNo(@Param("estExpNo") String estExpNo);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'and e.flagNotWithdrawing = 'N' and e.estExpNo = :estExpNo order by e.seqNo ")
	public List<IaEstimateExpD1> findDataExpNo(@Param("estExpNo") String estExpNo);

}
