
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;

public interface IaQuestionnaireHdrRepository extends CommonJpaCrudRepository<IaQuestionnaireHdr, BigDecimal> {

	void deleteByIdAndIsDeleted(BigDecimal id, String isDeleted);

}
