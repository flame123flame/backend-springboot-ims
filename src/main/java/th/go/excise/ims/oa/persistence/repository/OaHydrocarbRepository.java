
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.Optional;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarb;

public interface OaHydrocarbRepository
    extends CommonJpaCrudRepository<OaHydrocarb, BigDecimal>
{

	Optional<OaHydrocarb> findByLicenseIdAndOaPlanIdAndIsDeleted(BigDecimal oaPlanId, BigDecimal licenseId, String isDeleted);

}
