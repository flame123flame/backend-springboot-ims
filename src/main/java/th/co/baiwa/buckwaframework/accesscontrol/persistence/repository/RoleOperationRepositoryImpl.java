package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

public class RoleOperationRepositoryImpl implements RoleOperationRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(RoleOperationRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public RoleOperationRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private void buildSearchQuery(StringBuilder sql, List<Object> params, RoleOperationFormVo request) {
		sql.append(
				" SELECT  r.role_operation_id,r.role_id,o.operation_id,o.operation_code,o.operation_desc,r.is_deleted ");
		sql.append(" FROM adm_operation o ");
		sql.append(" LEFT JOIN adm_role_operation r ");
		sql.append(" ON r.operation_id = o.operation_id ");
		

		if (request.getRoleId() != null) {
			sql.append(" AND r.role_id = ? ");
			params.add(request.getRoleId());
		}
		sql.append(" WHERE o.is_deleted = ? ");
		params.add(FLAG.N_FLAG);

	}

	@Override
	public Integer countByCriteria(RoleOperationFormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()),
				params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}

	@Override
	public List<RoleOperationVo> findByCriteria(RoleOperationFormVo request) {
		logger.debug("findByCriteria roleoperation.roleId={}", request.getRoleId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY o.operation_id ");

		List<RoleOperationVo> datas = this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength()), params.toArray(),
				new RowMapper<RoleOperationVo>() {
					@Override
					public RoleOperationVo mapRow(ResultSet rs, int rowNum) throws SQLException {
						RoleOperationVo roleOperationVo = new RoleOperationVo();
						roleOperationVo.setRoleOperationId(rs.getLong("role_operation_id"));
						roleOperationVo.setRoleId(rs.getLong("role_id"));
						roleOperationVo.setOperationId(rs.getLong("operation_id"));
						roleOperationVo.setOperationCode(rs.getString("operation_code"));
						roleOperationVo.setOperationDesc(rs.getString("operation_desc"));
						roleOperationVo.setIsDeleted(rs.getString("is_deleted"));
						return roleOperationVo;
					}

				});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}

	@Override
	public List<RoleOperationVo> findById(RoleOperationFormVo request) {
		logger.debug("findById roleoperation.roleId={}", request.getRoleId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY o.operation_id ");

		List<RoleOperationVo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new RowMapper<RoleOperationVo>() {
					@Override
					public RoleOperationVo mapRow(ResultSet rs, int rowNum) throws SQLException {
						RoleOperationVo roleOperationVo = new RoleOperationVo();
						roleOperationVo.setRoleOperationId(rs.getLong("role_operation_id"));
						roleOperationVo.setRoleId(rs.getLong("role_id"));
						roleOperationVo.setOperationId(rs.getLong("operation_id"));
						roleOperationVo.setOperationCode(rs.getString("operation_code"));
						roleOperationVo.setOperationDesc(rs.getString("operation_desc"));
						roleOperationVo.setIsDeleted(rs.getString("is_deleted"));
						return roleOperationVo;
					}

				});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}
}
