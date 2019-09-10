
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;

public interface IaQuestionnaireSideRepository extends CommonJpaCrudRepository<IaQuestionnaireSide, BigDecimal>{
	List<IaQuestionnaireSide> findByidHeadAndIsDeletedOrderBySeqAsc(BigDecimal idHead, String isDeleted);

}
