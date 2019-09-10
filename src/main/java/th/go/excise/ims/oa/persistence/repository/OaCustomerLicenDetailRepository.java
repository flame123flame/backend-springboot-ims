
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;

public interface OaCustomerLicenDetailRepository
    extends CommonJpaCrudRepository<OaCustomerLicenDetail, BigDecimal>
{
	public List<OaCustomerLicenDetail> findByoaCuslicenseIdAndIsDeleted(BigDecimal oaLubricantsId, String idDeleted);


}
