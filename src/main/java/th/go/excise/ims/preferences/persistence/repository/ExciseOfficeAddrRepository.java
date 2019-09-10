package th.go.excise.ims.preferences.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseOfficeAddr;

public interface ExciseOfficeAddrRepository extends CommonJpaCrudRepository<ExciseOfficeAddr, Long>, ExciseOfficeAddrRepositoryCustom {

	@Modifying
	@Query(
		value = "UPDATE EXCISE_OFFICE_ADDR SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
}
