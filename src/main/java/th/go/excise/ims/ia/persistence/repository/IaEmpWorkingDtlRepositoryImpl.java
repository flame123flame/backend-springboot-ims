package th.go.excise.ims.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;

public class IaEmpWorkingDtlRepositoryImpl implements IaEmpWorkingDtlRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private void buildByMonthQuery(StringBuilder sql, List<Object> params, String workingDate) {
		sql.append(" SELECT * ");
		sql.append(" FROM IA_EMP_WORKING_DTL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" AND USER_OFFCODE = ? ");
		sql.append(" AND EXTRACT(MONTH FROM TO_DATE(WORKING_DATE) ) = EXTRACT(MONTH FROM TO_DATE(?,'DD/MM/YYYY') ) ");
		sql.append(" AND EXTRACT(YEAR FROM TO_DATE(WORKING_DATE) ) = EXTRACT(YEAR FROM TO_DATE(?,'DD/MM/YYYY') ) ");

		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		params.add(workingDate);
		params.add(workingDate);
	}

	@Override
	public List<IaEmpWorkingDtl> findByMonth(String workingDate) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByMonthQuery(sql, params, workingDate);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), findByMonthRowMapper);
	}

	protected RowMapper<IaEmpWorkingDtl> findByMonthRowMapper = new RowMapper<IaEmpWorkingDtl>() {
		@Override
		public IaEmpWorkingDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaEmpWorkingDtl vo = new IaEmpWorkingDtl();
			vo.setIaEmpWorkingDtlSeq(rs.getLong("IA_EMP_WORKING_DTL_SEQ"));
			vo.setUserLogin(rs.getString("USER_LOGIN"));
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserPosition(rs.getString("USER_POSITION"));
			vo.setUserOffcode(rs.getString("USER_OFFCODE"));
			vo.setWorkingDate(rs.getDate("WORKING_DATE"));
			vo.setWorkingFlag(rs.getString("WORKING_FLAG"));
			vo.setWorkingDesc(rs.getString("WORKING_DESC"));
			vo.setWorkingRemark(rs.getString("WORKING_REMARK"));
			vo.setReimburseExpFlag(rs.getString("REIMBURSE_EXP_FLAG"));
			return vo;
		}
	};
	
	@Override
	public List<ExciseHoliday> getHoliday(String workingDate) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		sql.append(" SELECT * ");
		sql.append(" FROM EXCISE_HOLIDAY ");
		sql.append(" WHERE EXTRACT(YEAR FROM HOLIDAY_DATE) = ? ");
		sql.append(" AND EXTRACT(MONTH FROM HOLIDAY_DATE) = ? ");

		Date wkDate = ConvertDateUtils.parseStringToDate(workingDate, ConvertDateUtils.DD_MM_YYYY);
		Calendar cal = Calendar.getInstance();
		cal.setTime(wkDate);
		params.add(cal.get(Calendar.YEAR));
		params.add(cal.get(Calendar.MONTH)+1);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), getHolidayRowMapper);
	}

	protected RowMapper<ExciseHoliday> getHolidayRowMapper = new RowMapper<ExciseHoliday>() {
		@Override
		public ExciseHoliday mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExciseHoliday vo = new ExciseHoliday();
			String dateStr = ConvertDateUtils.formatDateToString(rs.getDate("HOLIDAY_DATE"), ConvertDateUtils.DD_MMM_YYYY_SPAC);
			LocalDate dateLocal = ConvertDateUtils.parseStringToLocalDate(dateStr, ConvertDateUtils.DD_MMM_YYYY_SPAC);
			vo.setHolidayId(rs.getLong("HOLIDAY_ID"));
			vo.setHolidayDate(dateLocal);
			return vo;
		}
	};

}
