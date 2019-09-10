package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.vo.Int091201ViewFullDetailRequstVo;

@Repository
public class Int091201JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaEmpWorkingDtl> findIaEmpWorkingDtlBy(Int091201ViewFullDetailRequstVo vo){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM IA_EMP_WORKING_DTL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" AND USER_OFFCODE = ? ");
		sql.append(" AND WORKING_DATE >= to_date(? , 'dd/mm/yyyy') ");
		sql.append(" AND WORKING_DATE <= to_date(? , 'dd/mm/yyyy') ");
		sql.append(" AND USER_LOGIN =  ? ");
		sql.append(" ORDER BY WORKING_DATE  ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(vo.getOfficeCode());
		int year = Integer.parseInt(vo.getYearMonth().substring(0, 4));
		int month = Integer.parseInt(vo.getYearMonth().substring(4, 6));
		LocalDate initial = LocalDate.of(year, month, 1);
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.with(TemporalAdjusters.lastDayOfMonth());
		paramList.add(ConvertDateUtils.formatLocalDateToString(start, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		paramList.add(ConvertDateUtils.formatLocalDateToString(end, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		paramList.add(vo.getUserLogin());
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), findUserNameAndWorkingDate);
	}
	
	
	private RowMapper<IaEmpWorkingDtl> findUserNameAndWorkingDate = new RowMapper<IaEmpWorkingDtl>() {
		@Override
		public IaEmpWorkingDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaEmpWorkingDtl vo = new IaEmpWorkingDtl();
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserLogin(rs.getString("USER_LOGIN"));
			vo.setWorkingFlag(rs.getString("WORKING_FLAG"));
			vo.setWorkingDate(rs.getDate("WORKING_DATE"));
			return vo;
		}
	};
	public List<IaEmpWorkingDtl> findUsernameWorkByOfficeCodeAndWorkMonth(Int091201ViewFullDetailRequstVo vo){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT USER_NAME , USER_LOGIN FROM IA_EMP_WORKING_DTL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" AND USER_OFFCODE = ? ");
		sql.append(" AND WORKING_DATE >= to_date(? , 'dd/mm/yyyy') ");
		sql.append(" AND WORKING_DATE <= to_date(? , 'dd/mm/yyyy') ");
		sql.append(" GROUP BY USER_NAME , USER_LOGIN ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(vo.getOfficeCode());
		int year = Integer.parseInt(vo.getYearMonth().substring(0, 4));
		int month = Integer.parseInt(vo.getYearMonth().substring(4, 6));
		LocalDate initial = LocalDate.of(year, month, 1);
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.with(TemporalAdjusters.lastDayOfMonth());
		paramList.add(ConvertDateUtils.formatLocalDateToString(start, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		paramList.add(ConvertDateUtils.formatLocalDateToString(end, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));

		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), mapUsernameAndUserLogin);
	}
	
	
	private RowMapper<IaEmpWorkingDtl> mapUsernameAndUserLogin = new RowMapper<IaEmpWorkingDtl>() {
		@Override
		public IaEmpWorkingDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaEmpWorkingDtl vo = new IaEmpWorkingDtl();
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserLogin(rs.getString("USER_LOGIN"));
			return vo;
		}
	};

}
