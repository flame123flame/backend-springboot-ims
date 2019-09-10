package th.go.excise.ims.ws.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmQtD;

public interface WsPmQtDRepository extends CommonJpaCrudRepository<WsPmQtD, Long> , WsPmQtDRepositoryCustom {
	

}
