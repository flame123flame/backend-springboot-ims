package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseTitle;

public interface ExciseTitleRepository extends CommonJpaCrudRepository<ExciseTitle, Long>, ExciseTitleRepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE EXCISE_TITLE SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
	@Query(value = " SELECT * FROM EXCISE_TITLE WHERE IS_DELETED = '" + FLAG.N_FLAG + "' ORDER BY TITLE_SEQ ", nativeQuery = true)
	public List<ExciseTitle> listPersonThTitle();
	
	
}
