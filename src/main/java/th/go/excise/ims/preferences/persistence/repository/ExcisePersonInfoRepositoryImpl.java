package th.go.excise.ims.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

public class ExcisePersonInfoRepositoryImpl implements ExcisePersonInfoRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<ExcisePersonInfoVo> findByWorkOffcode(String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * ");
		sql.append(" FROM EXCISE_PERSON_INFO");
		sql.append(" WHERE WORK_OFFCODE ");
		sql.append(" LIKE ? ");
		params.add(officeCode.substring(0, 3)+'%');

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonInfoRowMapper);

	}

	private static final RowMapper<ExcisePersonInfoVo> edPersonInfoRowMapper = new RowMapper<ExcisePersonInfoVo>() {
		@Override
		public ExcisePersonInfoVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonInfoVo personInfo = new ExcisePersonInfoVo();
			personInfo.setId(rs.getLong("ID"));
			personInfo.setPersonLogin(rs.getString("PERSON_LOGIN"));
			personInfo.setPersonId(rs.getString("PERSON_ID"));
			personInfo.setPersonType(rs.getString("PERSON_TYPE"));
			personInfo.setPersonThTitle(rs.getString("PERSON_TH_TITLE"));
			personInfo.setPersonThName(rs.getString("PERSON_TH_NAME"));
			personInfo.setPersonThSurname(rs.getString("PERSON_TH_SURNAME"));
			personInfo.setPersonEnTitle(rs.getString("PERSON_EN_TITLE"));
			personInfo.setPersonEnName(rs.getString("PERSON_EN_NAME"));
			personInfo.setPersonEnSurname(rs.getString("PERSON_EN_SURNAME"));
			personInfo.setUnderOffcode(rs.getString("UNDER_OFFCODE"));
			personInfo.setUnderOffname(rs.getString("UNDER_OFFNAME"));
			personInfo.setUnderDeptcode(rs.getString("UNDER_DEPTCODE"));
			personInfo.setUnderDeptname(rs.getString("UNDER_DEPTNAME"));
			personInfo.setWorkOffcode(rs.getString("WORK_OFFCODE"));
			personInfo.setWorkOffname(rs.getString("WORK_OFFNAME"));
			personInfo.setWorkDeptcode(rs.getString("WORK_DEPTCODE"));
			personInfo.setLinePositionCode(rs.getString("LINE_POSITION_CODE"));
			personInfo.setLinePositionLevel(rs.getString("LINE_POSITION_LEVEL"));
			personInfo.setLinePosition(rs.getString("LINE_POSITION"));
			personInfo.setExcPositionCode(rs.getString("EXC_POSITION_CODE"));
			personInfo.setExcPosition(rs.getString("EXC_POSITION"));
			personInfo.setActingExcpositionCode(rs.getString("ACTING_EXCPOSITION_CODE"));
			personInfo.setActingExcposition(rs.getString("ACTING_EXCPOSITION"));
			personInfo.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
			personInfo.setDeptPhoneNo(rs.getString("DEPT_PHONE_NO"));
			personInfo.setPersonStatus(rs.getString("PERSON_STATUS"));
			personInfo.setCouplePid(rs.getString("COUPLE_PID"));
			personInfo.setFatherPid(rs.getString("FATHER_PID"));
			personInfo.setMotherPid(rs.getString("MOTHER_PID"));
			personInfo.setPersonAddrno(rs.getString("PERSON_ADDRNO"));
			personInfo.setPersonMoono(rs.getString("PERSON_MOONO"));
			personInfo.setPersonVillagename(rs.getString("PERSON_VILLAGENAME"));
			personInfo.setPersonSoiname(rs.getString("PERSON_SOINAME"));
			personInfo.setPersonRoadname(rs.getString("PERSON_ROADNAME"));
			personInfo.setPersonTabbolCode(rs.getString("PERSON_TABBOL_CODE"));
			personInfo.setPersonAmphurCode(rs.getString("PERSON_AMPHUR_CODE"));
			personInfo.setPersonProvinceCode(rs.getString("PERSON_PROVINCE_CODE"));
			personInfo.setCoupleThTitle(rs.getString("COUPLE_TH_TITLE"));
			personInfo.setCoupleName(rs.getString("COUPLE_NAME"));
			personInfo.setCoupleSurnameName(rs.getString("COUPLE_SURNAME_NAME"));
			personInfo.setFatherThTitle(rs.getString("FATHER_TH_TITLE"));
			personInfo.setFatherName(rs.getString("FATHER_NAME"));
			personInfo.setFatherSurnameName(rs.getString("FATHER_SURNAME_NAME"));
			personInfo.setMotherThTitle(rs.getString("MOTHER_TH_TITLE"));
			personInfo.setMotherName(rs.getString("MOTHER_NAME"));
			personInfo.setMotherSurnameName(rs.getString("MOTHER_SURNAME_NAME"));
			personInfo.setZipCode(rs.getString("ZIP_CODE"));
			return personInfo;
		}
	};

}
