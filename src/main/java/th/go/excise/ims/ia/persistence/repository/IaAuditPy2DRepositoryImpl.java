package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.IaAuditPy2DVo;

public class IaAuditPy2DRepositoryImpl implements IaAuditPy2DRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPy2DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public List<IaAuditPy2DVo> findByAuditPy2No(String auditPy2No) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * ");
		sql.append(" FROM IA_AUDIT_PY2_D ");
		sql.append(" WHERE IS_DELETED = 'N'  ");
		sql.append(" AND AUDIT_PY2_NO = ?  ");
		params.add(auditPy2No);
		sql.append(" ORDER BY AUDIT_PY2_D_ID ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPy2DVo> response = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaAuditPy2DVo.class));

		return response;
	}
}
