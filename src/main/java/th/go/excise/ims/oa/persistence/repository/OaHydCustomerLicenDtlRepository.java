
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;

public interface OaHydCustomerLicenDtlRepository
    extends CommonJpaCrudRepository<OaHydCustomerLicenDtl, BigDecimal>
{
	public List<OaHydCustomerLicenDtl> findByoaCuslicenseIdAndIsDeleted(BigDecimal oaLubricantsId, String idDeleted);

}
