package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001H;

public interface WsAnafri0001HRepository extends CommonJpaCrudRepository<WsAnafri0001H, Long>, WsAnafri0001HRepositoryCustom {
	
	@Modifying
	@Query(
		value = " DELETE FROM WS_ANAFRI0001_H H " +
				" WHERE H.NEW_REG_ID = :newRegId " +
				"   AND H.FORM_CODE = :formCode " +
				"   AND (TRUNC(H.REG_IN_DATE) >= TO_DATE(:dateStart,'YYYYMMDD') AND TRUNC(H.REG_IN_DATE) <= TO_DATE(:dateEnd,'YYYYMMDD')) ",
		nativeQuery = true
	)
	public void forceDeleteByFormCode(@Param("newRegId") String newRegId, @Param("formCode") String formCode, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
	
}
