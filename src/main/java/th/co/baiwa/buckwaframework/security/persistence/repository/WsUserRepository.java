package th.co.baiwa.buckwaframework.security.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsUser;

@Repository
public class WsUserRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(WsUserRepository.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public WsUser findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM WS_USER WHERE IS_DELETED = 'N' AND USER_ID = ? AND PASSWORD = ?";
		
		WsUser wsUser = null;
		try {
			wsUser = commonJdbcTemplate.queryForObject(
				sql,
				new Object[] {
					username,
					password
				},
				new RowMapper<WsUser>() {
					@Override
					public WsUser mapRow(ResultSet rs, int rowNum) throws SQLException {
						WsUser wsUser = new WsUser();
						wsUser.setUserThaiId(rs.getString("USER_THAI_ID"));
						wsUser.setUserThaiName(rs.getString("USER_THAI_NAME"));
						wsUser.setUserThaiSurname(rs.getString("USER_THAI_SURNAME"));
						wsUser.setUserEngName(rs.getString("USER_ENG_NAME"));
						wsUser.setUserEngSurname(rs.getString("USER_ENG_SURNAME"));
						wsUser.setTitle(rs.getString("TITLE"));
						wsUser.setUserId(rs.getString("USER_ID"));
						wsUser.setPassword(rs.getString("PASSWORD"));
						wsUser.setEmail(rs.getString("EMAIL"));
						wsUser.setCnName(rs.getString("CN_NAME"));
						wsUser.setOfficeId(rs.getString("OFFICE_ID"));
						wsUser.setAccessAttr(rs.getString("ACCESS_ATTR"));
						wsUser.setOfficeCode(rs.getString("OFFICE_CODE"));
						return wsUser;
					}
				}
			);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage(), e);
		}
		
		return wsUser;
	}
	
}
