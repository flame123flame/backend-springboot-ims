package th.go.excise.ims.ws.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ws.vo.WsRegfri4000Vo;

public class WsRegfri4000VoRowMapper implements RowMapper<WsRegfri4000Vo> {
	
	private static class SingletonHolder {
		private static final WsRegfri4000VoRowMapper instance = new WsRegfri4000VoRowMapper();
	}
	
	public static WsRegfri4000VoRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public WsRegfri4000Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
		WsRegfri4000Vo regfri4000Vo = new WsRegfri4000Vo();
		regfri4000Vo.setNewRegId(rs.getString("NEW_REG_ID"));
		regfri4000Vo.setCusId(rs.getString("CUS_ID"));
		regfri4000Vo.setCusFullname(rs.getString("CUS_FULLNAME"));
		regfri4000Vo.setCusAddress(rs.getString("CUS_ADDRESS"));
		regfri4000Vo.setCusTelno(rs.getString("CUS_TELNO"));
		regfri4000Vo.setCusEmail(rs.getString("CUS_EMAIL"));
		regfri4000Vo.setCusUrl(rs.getString("CUS_URL"));
		regfri4000Vo.setFacId(rs.getString("FAC_ID"));
		regfri4000Vo.setFacFullname(rs.getString("FAC_FULLNAME"));
		regfri4000Vo.setFacAddress(rs.getString("FAC_ADDRESS"));
		regfri4000Vo.setFacTelno(rs.getString("FAC_TELNO"));
		regfri4000Vo.setFacEmail(rs.getString("FAC_EMAIL"));
		regfri4000Vo.setFacUrl(rs.getString("FAC_URL"));
		regfri4000Vo.setFacType(rs.getString("FAC_TYPE"));
		regfri4000Vo.setRegId(rs.getString("REG_ID"));
		regfri4000Vo.setRegStatus(rs.getString("REG_STATUS"));
		regfri4000Vo.setRegDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_DATE")));
		regfri4000Vo.setRegCapital(rs.getBigDecimal("REG_CAPITAL"));
		regfri4000Vo.setOfficeCode(rs.getString("OFFICE_CODE"));
		regfri4000Vo.setSyncDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("SYNC_DATE")));
		regfri4000Vo.setDutyGroupId(rs.getString("GROUP_ID"));
		return regfri4000Vo;
	}
	
}