package th.go.excise.ims.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.thymeleaf.util.LoggingUtils;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

public class ExcisePersonRepositoryImpl implements ExcisePersonRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public List<ExcisePersonVoSelect> findByName(String officeCode, String name) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND ED_OFFCODE = ?  ");
		sql.append(" AND IS_DELETED = 'N'  AND SEQ = '0'  ");
		params.add("%" + name + "%");
		params.add(officeCode);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);

	}
	
	@Override
	public List<ExcisePersonVoSelect> findByOfficeCode(String officeCode,String subDeptCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_OFFCODE = ?  AND AU_SUBDEPT_CODE = ? ");
		sql.append(" AND IS_DELETED = 'N' AND SEQ = '0' ");
		params.add(officeCode);
		params.add(subDeptCode);
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);

	}

	private static final RowMapper<ExcisePersonVoSelect> edPersonRowMapper = new RowMapper<ExcisePersonVoSelect>() {
		@Override
		public ExcisePersonVoSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonVoSelect person = new ExcisePersonVoSelect();
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			person.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			person.setEdOffcode(rs.getString("ED_OFFCODE"));
			person.setName(rs.getString("ED_PERSON_NAME"));
			person.setValue(rs.getString("ED_LOGIN"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			return person;
		}
	};

	@Override
	public List<ExcisePersonVoSelect> findByEdLogin(String edLogin) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND IS_DELETED = 'N' AND SEQ = '0' ");
		params.add(" IN (" + edLogin + ")");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);
	}

	@Override
	public List<ExcisePersonVoSelect> findAllForAssign(DataTableRequest quest) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT  PERSON.*,DPT.OFF_SHORT_NAME OFF_NAME ,SUBDPT.SUBDEPT_NAME ,SUBDPT.SUBDEPT_CODE FROM EXCISE_PERSON PERSON  ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT DPT ON PERSON.ED_OFFCODE = DPT.OFF_CODE  ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT SUBDPT ON PERSON.AU_SUBDEPT_CODE = SUBDPT.SUBDEPT_CODE  ");
		sql.append(" WHERE PERSON.IS_DELETED = 'N' AND DPT.OFF_CODE LIKE  ?  AND PERSON.SEQ = '0' ");
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(offCode)) {
			offCode = "__"+offCode.substring(2, 4)+"__";
		} else if (ExciseUtils.isSector(offCode)) {
			offCode = offCode.substring(0, 2) + "____";
		} else if (ExciseUtils.isArea(offCode)) {
			offCode = offCode.substring(0, 4) + "__";
		}
		params.add(offCode);

		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), quest.getStart(), quest.getLength()),params.toArray(), edPersonRowMapperAssing);
	}
	
	private static final RowMapper<ExcisePersonVoSelect> edPersonRowMapperAssing = new RowMapper<ExcisePersonVoSelect>() {
		@Override
		public ExcisePersonVoSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonVoSelect person = new ExcisePersonVoSelect();
			person.setEdPersonSeq(rs.getLong("ED_PERSON_SEQ"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			person.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			person.setEdOffcode(rs.getString("ED_OFFCODE"));
			person.setName(rs.getString("ED_PERSON_NAME"));
			person.setValue(rs.getString("ED_LOGIN"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setAuSubdeptCode(rs.getString("AU_SUBDEPT_CODE"));
			person.setSubDeptName(rs.getString("SUBDEPT_NAME"));
			person.setLevel(rs.getString("AU_SUBDEPT_LEVEL"));
			person.setOfficeName(rs.getString("OFF_NAME"));
			return person;
		}
	};

	@Override
	public Integer countAllForAssign(DataTableRequest quest) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT   PERSON.* FROM EXCISE_PERSON PERSON  ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT DPT ON PERSON.ED_OFFCODE = DPT.OFF_CODE  ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT SUBDPT ON PERSON.AU_SUBDEPT_CODE = SUBDPT.SUBDEPT_CODE  ");
		sql.append(" WHERE PERSON.IS_DELETED = 'N' AND DPT.OFF_CODE LIKE  ?  AND PERSON.SEQ = '0' ");
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (ExciseUtils.isCentral(offCode)) {
			offCode = "__"+offCode.substring(2, 4)+"__";
		} else if (ExciseUtils.isSector(offCode)) {
			offCode = offCode.substring(0, 2) + "____";
		} else if (ExciseUtils.isArea(offCode)) {
			offCode = offCode.substring(0, 4) + "__";
		}
		params.add(offCode);
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()),params.toArray(), Integer.class);
	}
	


}
