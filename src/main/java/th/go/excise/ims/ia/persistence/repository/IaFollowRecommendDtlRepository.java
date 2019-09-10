
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendDtl;

public interface IaFollowRecommendDtlRepository extends CommonJpaCrudRepository<IaFollowRecommendDtl, BigDecimal> {

	List<IaFollowRecommendDtl> findByIdFollowRecommendHdrAndIsDeleted(BigDecimal idHdr, String isDeleted);

}
