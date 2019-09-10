package th.co.baiwa.buckwaframework.security.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class GrantedAuthorityRowMapper implements RowMapper<GrantedAuthority> {
	
	private static class SingletonHolder {
		private static final GrantedAuthorityRowMapper instance = new GrantedAuthorityRowMapper();
	}
	
	public static GrantedAuthorityRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SimpleGrantedAuthority(rs.getString("authority_code"));
	}
	
}
