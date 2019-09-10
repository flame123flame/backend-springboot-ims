
package th.go.excise.ims.preferences.persistence.repository;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;

public interface ExciseOrgDisbRepository extends CommonJpaCrudRepository<ExciseOrgDisb, String> {
	@Query(value = " SELECT * FROM EXCISE_ORG_DISB WHERE DISBURSE_UNIT = ? AND IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public ExciseOrgDisb findExciseOrgGfmisByGfDisburseUnit(String gfDisburseUnit);
}
