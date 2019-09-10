package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public interface TaWsReg4000Repository extends CommonJpaCrudRepository<TaWsReg4000, Long>, TaWsReg4000RepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE TA_WS_REG4000 SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.newRegId = :newRegId")
	public TaWsReg4000 getByNewRegId(@Param("newRegId") String newRegId);
}
