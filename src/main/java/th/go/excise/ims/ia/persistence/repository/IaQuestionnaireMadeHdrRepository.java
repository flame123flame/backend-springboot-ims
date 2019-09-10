
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;

public interface IaQuestionnaireMadeHdrRepository extends CommonJpaCrudRepository<IaQuestionnaireMadeHdr, BigDecimal> {

	List<IaQuestionnaireMadeHdr> findByIdHdrAndIsDeleted(BigDecimal idHead, String isDeleted);

}
