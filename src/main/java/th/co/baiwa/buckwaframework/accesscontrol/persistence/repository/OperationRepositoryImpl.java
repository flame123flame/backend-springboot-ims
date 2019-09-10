package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.vo.OperationFormVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.MySqlUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

public class OperationRepositoryImpl implements OperationRepositoryCustom  {
private static final Logger logger = LoggerFactory.getLogger(OperationRepositoryImpl.class);
	
	private  CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public OperationRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	private void buildSearchQuery(StringBuilder sql, List<Object> params, OperationFormVo operationFormVo) {
		sql.append(" SELECT operation_id, operation_code, operation_desc  ");
		sql.append(" FROM adm_operation ");
		sql.append(" WHERE is_deleted = ? ");
		
		params.add(FLAG.N_FLAG);
		
		if (StringUtils.isNotBlank(operationFormVo.getOperationCode())) {
			sql.append(" AND operation_code LIKE  ? ");
			params.add("%" + StringUtils.trim(operationFormVo.getOperationCode()) + "%");
		}
	
		
		if (StringUtils.isNotBlank(operationFormVo.getOperationDesc())) {
			sql.append(" AND operation_code LIKE  ? ");
			params.add("%" + StringUtils.trim(operationFormVo.getOperationDesc()) + "%");
		}
		
	
	}
	
	@Override
	public Integer countByCriteria(OperationFormVo operationFormVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, operationFormVo);
		
		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Integer.class);
		
		logger.info("count={}", count);
		
		return count;
	}
	
	@Override
	public List<Operation> findByCriteria(OperationFormVo operationFormVo) {
		logger.debug("findByCriteria operationFormVo.OperationCode={}, operationFormVo.OperationDesc={}, ",
				operationFormVo.getOperationCode(),operationFormVo.getOperationDesc() );
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, operationFormVo);
		
		sql.append(" ORDER BY operation_id ");
		
		List<Operation> datas = this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), operationFormVo.getStart(), operationFormVo.getLength()), 
				params.toArray(), 
				new RowMapper<Operation>() {
				@Override
				public Operation mapRow(ResultSet rs, int rowNum) throws SQLException {
					Operation operation = new Operation();
					operation.setOperationId(rs.getLong("operation_id"));
					operation.setOperationCode(rs.getString("operation_code"));
					operation.setOperationDesc(rs.getString("operation_desc"));
					return operation;
				}

			});
		logger.info("resultList.size()={}", datas.size());
		
		return datas;
	}

	
	
}
