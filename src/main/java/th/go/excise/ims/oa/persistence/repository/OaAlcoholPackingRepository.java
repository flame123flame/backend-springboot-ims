
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholPacking;

public interface OaAlcoholPackingRepository extends CommonJpaCrudRepository<OaAlcoholPacking, BigDecimal> {

	public List<OaAlcoholPacking> findByOaAlcoholIdAndIsDeleted(BigDecimal oaAlcoholId, String isDeleted);
	
}
