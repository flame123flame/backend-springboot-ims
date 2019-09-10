
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsSummary;

public interface OaLubricantsSummaryRepository
    extends CommonJpaCrudRepository<OaLubricantsSummary, BigDecimal>
{
	public List<OaLubricantsSummary> findByOaLubricantsIdAndIsDeleted(BigDecimal oaLubricantsId, String idDeleted);
	

}
