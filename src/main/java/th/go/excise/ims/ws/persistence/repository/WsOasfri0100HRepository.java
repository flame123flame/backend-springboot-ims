package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100H;

public interface WsOasfri0100HRepository extends CommonJpaCrudRepository<WsOasfri0100H, Long>, WsOasfri0100HRepositoryCustom {
	
	@Modifying
	@Query(
		value = "DELETE WS_OASFRI0100_H WHERE FORMDOC_REC0142_NO = :docNo",
		nativeQuery = true
	)
	public void forceDeleteByDocNo(@Param("docNo") String docNo);
	
}
