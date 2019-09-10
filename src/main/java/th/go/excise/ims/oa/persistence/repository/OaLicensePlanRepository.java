
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;

public interface OaLicensePlanRepository
    extends CommonJpaCrudRepository<OaLicensePlan, BigDecimal>
{

	public List<OaLicensePlan> findByoaPlanIdAndIsDeleted(BigDecimal oaPlanId, String idDeleted);

}
