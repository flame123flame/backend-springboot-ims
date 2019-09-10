
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholDistil;

public interface OaAlcoholDistilRepository extends CommonJpaCrudRepository<OaAlcoholDistil, BigDecimal> {
	
	public List<OaAlcoholDistil> findByOaAlcoholIdAndIsDeleted(BigDecimal oaAlcoholId, String isDeleted);

}
