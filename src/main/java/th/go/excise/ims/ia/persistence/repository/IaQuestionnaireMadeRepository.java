
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;

public interface IaQuestionnaireMadeRepository extends CommonJpaCrudRepository<IaQuestionnaireMade, BigDecimal> {

	List<IaQuestionnaireMade> findByIdSideDtl(BigDecimal idSideDtl);

	@Transactional
	void deleteByIdSideDtl(BigDecimal idSideDtl);

	List<IaQuestionnaireMade> findByIdMadeHdrAndIsDeleted(BigDecimal id, String isDeleted);

}
