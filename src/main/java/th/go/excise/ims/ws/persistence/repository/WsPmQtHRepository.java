package th.go.excise.ims.ws.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmQtH;

public interface WsPmQtHRepository extends CommonJpaCrudRepository<WsPmQtH, Long> , WsPmQtHRepositoryCustom {

}
