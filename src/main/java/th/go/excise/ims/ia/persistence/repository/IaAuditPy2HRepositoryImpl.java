package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;
import th.go.excise.ims.ia.vo.IaAuditPy2HVo;

public class IaAuditPy2HRepositoryImpl implements IaAuditPy2HRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPy2HRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<IaAuditPy2H> getAuditPy2NoList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PY2_NO ");
		sql.append(" FROM IA_AUDIT_PY2_H ");
		sql.append(" GROUP BY AUDIT_PY2_NO  ");
		sql.append(" ORDER BY MIN(IA_AUDIT_PY2_H_ID) ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPy2H> dropdownList = this.commonJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(IaAuditPy2H.class));

		return dropdownList;
	}
	
	@Override
	public List<IaAuditPy2HVo> filterIaPmPy2ByAuditPy2No(String auditPy2No) {
		logger.debug("auditPy2No: {}", auditPy2No);
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_AUDIT_PY2_H ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		sql.append(" AND AUDIT_PY2_NO = ? ");
		params.add(auditPy2No);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPy2HVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaAuditPy2HVo.class));

		return response; 
	}

}
