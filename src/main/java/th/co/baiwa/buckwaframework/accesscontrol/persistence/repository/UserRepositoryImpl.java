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

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserFormVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

public class UserRepositoryImpl implements UserRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public UserRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	private void buildSearchQuery(StringBuilder sql, List<Object> params, UserFormVo userFormVo) {
		sql.append(" SELECT user_id, username, enabled");
		sql.append(" FROM adm_user ");
		sql.append(" WHERE is_deleted = ? ");

		params.add(FLAG.N_FLAG);

		if (StringUtils.isNotBlank(userFormVo.getUsername())) {
			sql.append(" AND username LIKE ? ");
			params.add("%" + StringUtils.trim(userFormVo.getUsername()) + "%");
		}

		if (StringUtils.isNotBlank(userFormVo.getEnabled())) {
			sql.append(" AND enabled LIKE ? ");
			params.add("%" + StringUtils.trim(userFormVo.getEnabled()) + "%");
		}

	}
	@Override
	public Integer countByCriteria(UserFormVo userFormVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, userFormVo);

		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}
	@Override
	public List<User> findByCriteria(UserFormVo userFormVo) {
		logger.debug("findByCriteria userFormVo.username={}, userFormVo.enabled={}", userFormVo.getUsername(),userFormVo.getEnabled());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, userFormVo);

		sql.append(" ORDER BY user_id ");

		List<User> datas = this.commonJdbcTemplate.query(
			OracleUtils.limitForDatable(sql.toString(), userFormVo.getStart(), userFormVo.getLength()), 
			params.toArray(), 
			new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUsername(rs.getString("username"));
				user.setEnabled(rs.getString("enabled"));
				return user;
			}

		});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}




}
