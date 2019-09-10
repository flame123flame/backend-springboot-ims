
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;

public interface IaPlanDayActivityRepository extends CommonJpaCrudRepository<IaPlanDayActivity, BigDecimal> {

	List<IaPlanDayActivity> findByPlanDtlIdAndIsDeleted(BigDecimal planDtlId, String isDeleted);

}
