package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;

public interface IaRiskQtnConfigRepository extends CommonJpaCrudRepository<IaRiskQtnConfig, BigDecimal> {

	public IaRiskQtnConfig findByIdQtnHdrAndIsDeleted(BigDecimal idQtnHdr, String idDeleted);

	@Query(value = " Select e.* from IA_RISK_QTN_CONFIG e  WHERE e.ID_QTN_HDR = ?1 AND Is_Deleted = 'N' ", nativeQuery = true)
	public IaRiskQtnConfig findByIdQtnHdr(BigDecimal idQtnHdr);

}
