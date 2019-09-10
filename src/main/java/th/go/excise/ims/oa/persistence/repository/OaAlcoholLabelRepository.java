
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholLabel;

public interface OaAlcoholLabelRepository extends CommonJpaCrudRepository<OaAlcoholLabel, BigDecimal> {

	public List<OaAlcoholLabel> findByOaAlcoholIdAndIsDeleted(BigDecimal oaAlcoholId, String isDeleted);

}
