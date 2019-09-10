package th.go.excise.ims.preferences.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDuedatePs0112;

public interface ExciseDuedatePs0112Repository extends CommonJpaCrudRepository<ExciseDuedatePs0112, Long>, ExciseDuedatePs0112RepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE EXCISE_DUEDATE_PS0112 SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
}
