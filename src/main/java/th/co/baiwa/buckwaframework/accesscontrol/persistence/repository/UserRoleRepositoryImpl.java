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
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

public class UserRoleRepositoryImpl implements UserRoleRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public UserRoleRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private void buildSearchQuery(StringBuilder sql, List<Object> params, UserRoleFormVo request) {
		sql.append(" SELECT u.user_role_id,u.user_id,u.role_id,r.role_code,r.role_desc,u.is_deleted ");
		sql.append(" FROM adm_role r ");
		sql.append(" LEFT JOIN adm_user_role u ");
		sql.append(" ON u.role_id = r.role_id  ");

		if (request.getUserId() != null) {
			sql.append(" AND u.user_id = ? ");
			params.add(request.getUserId());
		}
		sql.append(" WHERE r.is_deleted = ? ");
		params.add(FLAG.N_FLAG);

	}

	@Override
	public Integer countByCriteria(UserRoleFormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()),
				params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}

	@Override
	public List<UserRoleVo> findByCriteria(UserRoleFormVo request) {
		logger.debug("findByCriteria userRole.roleId={}", request.getUserId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY r.role_id ");

		List<UserRoleVo> datas = this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength()), params.toArray(),
				new RowMapper<UserRoleVo>() {
					@Override
					public UserRoleVo mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserRoleVo userRoleVo = new UserRoleVo();
						userRoleVo.setUserRoleId(rs.getLong("user_role_id"));
						userRoleVo.setIsDeleted(rs.getString("is_deleted"));
						userRoleVo.setRoleCode(rs.getString("role_code"));
						userRoleVo.setRoleDesc(rs.getString("role_desc"));
						userRoleVo.setRoleId(rs.getLong("role_id"));
						return userRoleVo;
					}

				});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}

	@Override
	public List<UserRoleVo> findById(UserRoleFormVo request) {
		logger.debug("findById roleoperation.roleId={}", request.getUserId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY r.role_id ");

		List<UserRoleVo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new RowMapper<UserRoleVo>() {
					@Override
					public UserRoleVo mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserRoleVo userRoleVo = new UserRoleVo();
						userRoleVo.setUserRoleId(rs.getLong("user_role_id"));
						userRoleVo.setIsDeleted(rs.getString("is_deleted"));
						userRoleVo.setRoleCode(rs.getString("role_code"));
						userRoleVo.setRoleDesc(rs.getString("role_desc"));
						userRoleVo.setRoleId(rs.getLong("role_id"));
						return userRoleVo;
					}

				});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}
}
