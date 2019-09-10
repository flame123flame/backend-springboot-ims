
package th.go.excise.ims.oa.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCust;

public interface OaHydrocarbCustRepository extends CommonJpaCrudRepository<OaHydrocarbCust, BigDecimal> {

	public List<OaHydrocarbCust> findByOaHydrocarbIdAndIsDeleted(BigDecimal oaHydrocarbId, String idDeleted);

}
