package th.go.excise.ims.ia.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class IaAuditIncGfImplRepository implements IaAuditIncGfCustomRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

}
