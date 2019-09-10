
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;

public interface OaLubricantsCompareRepository
    extends CommonJpaCrudRepository<OaLubricantsCompare, BigDecimal>
{
	public List<OaLubricantsCompare> findByOaLubricantsIdAndIsDeleted(BigDecimal oaLubricantsId, String idDeleted);
	
//	public void deleteByOaLubricantsId(BigDecimal oaLubricantsId);


}
