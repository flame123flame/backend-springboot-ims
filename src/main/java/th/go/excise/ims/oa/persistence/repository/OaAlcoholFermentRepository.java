
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholFerment;

public interface OaAlcoholFermentRepository extends CommonJpaCrudRepository<OaAlcoholFerment, BigDecimal> {

	public List<OaAlcoholFerment> findByOaAlcoholIdAndIsDeleted(BigDecimal oaAlcoholId, String isDeleted);
	
}
