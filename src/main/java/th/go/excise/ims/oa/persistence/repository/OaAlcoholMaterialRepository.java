
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholMaterial;

public interface OaAlcoholMaterialRepository extends CommonJpaCrudRepository<OaAlcoholMaterial, BigDecimal> {

	public List<OaAlcoholMaterial> findByOaAlcoholIdAndIsDeleted(BigDecimal oaAlcoholId, String isDeleted);
	
}
