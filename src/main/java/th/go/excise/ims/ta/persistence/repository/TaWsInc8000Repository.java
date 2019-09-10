package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000;

public interface TaWsInc8000Repository extends CommonJpaCrudRepository<TaWsInc8000, BigDecimal>, TaWsInc8000RepositoryCustom {

}
