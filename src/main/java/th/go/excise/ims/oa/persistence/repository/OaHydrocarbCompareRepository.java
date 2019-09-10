
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;

public interface OaHydrocarbCompareRepository
    extends CommonJpaCrudRepository<OaHydrocarbCompare, BigDecimal>
{
	public List<OaHydrocarbCompare> findByoaHydrocarbIdAndIsDeleted(BigDecimal oaHydrocarbId, String idDeleted);

}
