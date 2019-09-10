package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;

public interface WsRegfri4000Repository extends CommonJpaCrudRepository<WsRegfri4000, Long>, WsRegfri4000RepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE WS_REGFRI4000 SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
}
