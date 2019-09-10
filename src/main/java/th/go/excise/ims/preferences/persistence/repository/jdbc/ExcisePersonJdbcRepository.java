package th.go.excise.ims.preferences.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.preferences.vo.Ed0101Vo;
import th.go.excise.ims.preferences.vo.Ed01Vo;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Repository
public class ExcisePersonJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed0101Vo> listUser() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_PERSON WHERE IS_DELETED = 'N' ORDER BY ED_PERSON_SEQ ASC ");
		List<Ed0101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listUserRowMapper);
		return datas;
	}

	private RowMapper<Ed0101Vo> listUserRowMapper = new RowMapper<Ed0101Vo>() {
		@Override
		public Ed0101Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed0101Vo vo = new Ed0101Vo();
			vo.setEdPersonId(rs.getString("ED_PERSON_ID"));
			vo.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			vo.setEdOffcode(rs.getString("ED_OFFCODE"));
			vo.setEdLogin(rs.getString("ED_LOGIN"));
			return vo;
		}
	};
	
	public List<Ed01Vo> getIdCard(String username) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_PERSON WHERE ED_LOGIN = ? AND IS_DELETED ='N' ");
		params.add(username);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed01Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed01Vo.class));
		return datas;
	}
	
	public List<Int0501Vo> listPerson(Int0501FormVo form) {
		List<Int0501Vo> Int0501VoList = new ArrayList<Int0501Vo>();
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT EP.ED_PERSON_SEQ , EP.ED_LOGIN , EP.ED_PERSON_NAME , EP.ED_POSITION_NAME , 2 * PO.ALLOWANCES_DAY as ALLOWANCES_DAY , 2 * PO.ACCOM_FEE_PACKAGES as ACCOM_FEE_PACKAGES ");
		sqlBuilder.append(" FROM EXCISE_PERSON EP ");
		sqlBuilder.append(" LEFT JOIN EXCISE_POSITION PO ");
		sqlBuilder.append(" ON EP.ED_POSITION_SEQ = PO.ED_POSITION_SEQ ");
		sqlBuilder.append(" WHERE EP.ED_OFFCODE = ? ");	
		params.add(form.getEdOffcode());
		Int0501VoList = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listRowmapper);
		return Int0501VoList;
	}
	
	private RowMapper<Int0501Vo> listRowmapper = new RowMapper<Int0501Vo>() {
		@Override
		public Int0501Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0501Vo vo = new Int0501Vo();
			vo.setEdPersonSeq(rs.getString("ED_PERSON_SEQ"));
			vo.setEdLogin(rs.getString("ED_LOGIN"));
			vo.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));		
			vo.setAllowancesDay(rs.getBigDecimal("ALLOWANCES_DAY"));
			vo.setAccomFeePackages(rs.getBigDecimal("ACCOM_FEE_PACKAGES"));	
			return vo;
		}
	};
	
	
	
	
}
