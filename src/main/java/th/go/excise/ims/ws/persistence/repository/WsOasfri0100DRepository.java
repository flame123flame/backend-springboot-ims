package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D;

public interface WsOasfri0100DRepository extends CommonJpaCrudRepository<WsOasfri0100D, Long>, WsOasfri0100DRepositoryCustom {
	
	@Modifying
	@Query(
		value = "DELETE WS_OASFRI0100_D WHERE FORMDOC_REC0142_NO = :docNo",
		nativeQuery = true
	)
	public void forceDeleteByDocNo(@Param("docNo") String docNo);
	
}
