package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

public interface WsLicfri6010Repository extends CommonJpaCrudRepository<WsLicfri6010, Long>, WsLicfri6010RepositoryCustom {
	
	@Modifying
	@Query(
		value = " DELETE WS_LICFRI6010 " +
				" WHERE OFFCODE = :officeCode " +
				"   AND ( " +
				"     TRUNC(LIC_DATE) >= TO_DATE(:dateStart,'YYYYMMDD') " +
				"     AND TRUNC(LIC_DATE) <= TO_DATE(:dateEnd,'YYYYMMDD') " +
				"   ) ",
		nativeQuery = true
	)
	public void forceDeleteByOfficeCodeAndLicDate(@Param("officeCode") String officeCode, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
	
}
