
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;

public interface OaPlanRepository
    extends CommonJpaCrudRepository<OaPlan, BigDecimal>
{

	public List<OaPlan> findByfiscolYearAndIsDeleted(String fiscolYear, String idDeleted);
}
