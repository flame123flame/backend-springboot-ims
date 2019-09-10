package th.co.baiwa.buckwaframework.security.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsRole;

@Repository
public class WsUserRoleRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(WsUserRepository.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<WsRole> findByUsername(String username) {
		String sql = "SELECT * FROM WS_USER_ROLE WHERE IS_DELETED = 'N' AND USER_ID = ?";
		
		List<WsRole> wsRoleList = null;
		try {
			wsRoleList = commonJdbcTemplate.query(
				sql,
				new Object[] {
					username
				},
				new RowMapper<WsRole>() {
					@Override
					public WsRole mapRow(ResultSet rs, int rowNum) throws SQLException {
						WsRole wsRole = new WsRole();
						wsRole.setRoleCode(rs.getString("ROLE_CODE"));
						return wsRole;
					}
				}
			);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage() + " Role with User={} Not Found", username);
		}
		
		return wsRoleList;
	}
}
