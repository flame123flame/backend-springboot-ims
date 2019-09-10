
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbSummary;

public interface OaHydrocarbSummaryRepository
    extends CommonJpaCrudRepository<OaHydrocarbSummary, BigDecimal>
{
	public List<OaHydrocarbSummary> findByoaHydrocarbIdAndIsDeleted(BigDecimal oaHydrocarbId, String idDeleted);

}
