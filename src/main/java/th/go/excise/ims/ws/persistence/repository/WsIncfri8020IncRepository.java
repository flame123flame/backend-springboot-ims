package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public interface WsIncfri8020IncRepository extends CommonJpaCrudRepository<WsIncfri8020Inc, Long>, WsIncfri8020IncRepositoryCustom {
	@Query(
			value = " SELECT DISTINCT INCOME_NAME FROM WS_INCFRI8020_INC " +
					" WHERE INCOME_CODE = ? ",
			nativeQuery = true
		)
		public String findIncomeName(String incomeCode);
}
