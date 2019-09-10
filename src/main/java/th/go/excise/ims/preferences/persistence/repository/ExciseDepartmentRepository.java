package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepartment;

public interface ExciseDepartmentRepository extends CommonJpaCrudRepository<ExciseDepartment, Long>, ExciseDepartmentRepositoryCustom {
	
	@Query(
		value = "SELECT * FROM EXCISE_DEPARTMENT WHERE IS_DELETED = '" + FLAG.N_FLAG + "' AND SYSDATE <= DECODE(END_DATE, NULL, TO_DATE('31/12/9999', 'DD/MM/YYYY'), END_DATE) ORDER BY OFF_CODE",
		nativeQuery = true
	)
	public List<ExciseDepartment> findAllActiveDepartment();
	
	@Modifying
	@Query(
		value = "UPDATE EXCISE_DEPARTMENT SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
}
