
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaPersonAuditPlan;

public interface OaPersonAuditPlanRepository
    extends CommonJpaCrudRepository<OaPersonAuditPlan, BigDecimal>
{

	public List<OaPersonAuditPlan> findByoaPlanIdAndIsDeleted(BigDecimal oaPlanId, String idDeleted);
}
