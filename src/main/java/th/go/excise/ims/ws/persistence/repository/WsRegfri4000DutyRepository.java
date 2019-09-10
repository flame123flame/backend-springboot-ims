package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;

public interface WsRegfri4000DutyRepository extends CommonJpaCrudRepository<WsRegfri4000Duty, Long>, WsRegfri4000DutyRepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE WS_REGFRI4000_DUTY SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
}
