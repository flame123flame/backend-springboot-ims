
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCust;

public interface OaLubricantsCustRepository
    extends CommonJpaCrudRepository<OaLubricantsCust, BigDecimal>
{

	public List<OaLubricantsCust> findByOaLubricantsIdAndIsDeleted(BigDecimal oaLubricantsId, String idDeleted);
	
}
