package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;
import th.go.excise.ims.ia.vo.Int12040101ValidateSearchFormVo;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Repository
public class IaExpensesJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL = "SELECT * FROM IA_EXPENSES WHERE 1=1 ";

	public List<IaExpenses> findByYearByCoa(Int120401FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (formVo.getAccountId() != null) {
			sql.append(" AND ACCOUNT_ID = ? ");
			params.add(formVo.getAccountId());
		}
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			if (StringUtils.isNoneBlank(formVo.getArea())) {
				sql.append(" AND OFFICE_CODE = ? ");
				params.add(formVo.getArea());
			} else {
				sql.append(" AND SUBSTR(OFFICE_CODE, 0, 2) = ? ");
				params.add(formVo.getOfficeCode().substring(0, 2));
			}
		}
//		if(StringUtils.isNotBlank(formVo.getOfficeCode())) {
//			sql.append(" AND OFFICE_CODE = ? ");
//			params.add(formVo.getOfficeCode());
//		}
//		if (StringUtils.isNotBlank(formVo.getYear())) {
//			Integer yearFormInt = Integer.parseInt(formVo.getYear()) - 544;
//			Integer yearToInt = Integer.parseInt(formVo.getYear()) - 543;
//			String yearFormStr = "01-DEC-" + yearFormInt.toString();
//			String yearToStr = "30-NOV-" + yearToInt.toString();
//			sql.append(" AND CREATED_DATE >= ? ");
//			params.add(yearFormStr);
////			params.add(formVo.getYearFrom());
//			sql.append(" AND CREATED_DATE <= ? ");
//			params.add(yearToStr);
////			params.add(formVo.getYearTo());
//		}
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(formVo.getYear());
		}
		sql.append(" AND IS_DELETED = 'N' ");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<IaExpenses> data = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaExpenses.class));
		return data;
	}
//
//	public Long count(Int120401FormVo formVo) {
//		StringBuilder sql = new StringBuilder(SQL);
//		List<Object> params = new ArrayList<>();
//
//		if (StringUtils.isNotBlank(formVo.getAccountId())) {
//			sql.append(" AND ACCOUNT_ID LIKE ?");
//			params.add("%" + StringUtils.trim(formVo.getAccountId()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getAccountName())) {
//			sql.append(" AND ACCOUNT_NAME LIKE ? ");
//			params.add("%" + StringUtils.trim(formVo.getAccountName()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getYear())) {
//			sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
//			params.add(StringUtils.trim(formVo.getYearFrom()));
//			params.add(StringUtils.trim(formVo.getYearTo()));
//		}
//
//		String countSql = OracleUtils.countForDatatable(sql);
//		Long count = commonJdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
//		return count;
//	}
//	
//	public List<Int120401Vo> findAll(Int120401FormVo formVo) {
//		StringBuilder sql = new StringBuilder(SQL);
//		List<Object> params = new ArrayList<>();
//
//		if (StringUtils.isNotBlank(formVo.getAccountId())) {
//			sql.append(" AND ACCOUNT_ID LIKE ?");
//			params.add("%" + StringUtils.trim(formVo.getAccountId()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getAccountName())) {
//			sql.append(" AND ACCOUNT_NAME LIKE ? ");
//			params.add("%" + StringUtils.trim(formVo.getAccountName()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getYear())) {
//			sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
//			params.add(StringUtils.trim(formVo.getYearFrom()));
//			params.add(StringUtils.trim(formVo.getYearTo()));
//		}
//
//		sql.append(" ORDER BY CREATED_DATE DESC ");
//		String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
//		List<Int120401Vo> list = commonJdbcTemplate.query(limit, params.toArray(), expensesRowmapper);
//		return list;
//
//	}
//	
//	private RowMapper<Int120401Vo> expensesRowmapper = new RowMapper<Int120401Vo>() {
//		@Override
//		public Int120401Vo mapRow(ResultSet rs, int arg1) throws SQLException {
//			Int120401Vo vo = new Int120401Vo();
//
//			vo.setId(rs.getString("ID"));
//			vo.setAccountId(rs.getString("ACCOUNT_ID"));
//			vo.setAccountName(rs.getString("ACCOUNT_NAME"));
//			vo.setServiceReceive(rs.getString("SERVICE_RECEIVE"));
//			vo.setServiceWithdraw(rs.getString("SERVICE_WITHDRAW"));
//			vo.setServiceBalance(rs.getString("SERVICE_BALANCE"));
//			vo.setSuppressReceive(rs.getString("SUPPRESS_RECEIVE"));
//			vo.setSuppressWithdraw(rs.getString("SUPPRESS_WITHDRAW"));
//			vo.setSuppressBalance(rs.getString("SUPPRESS_BALANCE"));
//			vo.setBudgetReceive(rs.getString("BUDGET_RECEIVE"));
//			vo.setBudgetWithdraw(rs.getString("BUDGET_WITHDRAW"));
//			vo.setBudgetBalance(rs.getString("BUDGET_BALANCE"));
//			vo.setSumReceive(rs.getString("SUM_RECEIVE"));
//			vo.setSumWithdraw(rs.getString("SUM_WITHDRAW"));
//			vo.setSumBalance(rs.getString("SUM_BALANCE"));
//			vo.setMoneyBudget(rs.getString("MONEY_BUDGET"));
//			vo.setMoneyOut(rs.getString("MONEY_OUT"));
//			vo.setAverageCost(rs.getString("AVERAGE_COST"));
//			vo.setAverageGive(rs.getString("AVERAGE_GIVE"));
//			vo.setAverageFrom(rs.getString("AVERAGE_FROM"));
//			vo.setAverageComeCost(rs.getString("AVERAGE_COME_COST"));
//			vo.setCreatedBy(rs.getString("CREATED_BY"));
//			vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE")));
//			vo.setNote(rs.getString("NOTE"));
//			return vo;
//		}
//	};

	public List<Int090101Vo> findCompare(Int090101CompareFormVo form) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT IEX.ACCOUNT_ID, ");
		sql.append("   IEX.ACCOUNT_NAME, ");
		sql.append("   SUM(IEX.SERVICE_RECEIVE)   AS SERVICE_RECEIVE, ");
		sql.append("   SUM(IEX.SUPPRESS_RECEIVE)  AS SUPPRESS_RECEIVE, ");
		sql.append("   SUM(IEX.BUDGET_RECEIVE)    AS BUDGET_RECEIVE, ");
		sql.append("   SUM(IEX.SUM_RECEIVE)       AS SUM_RECEIVE, ");
		sql.append("   SUM(IEX.SERVICE_WITHDRAW)  AS SERVICE_WITHDRAW, ");
		sql.append("   SUM(IEX.SUPPRESS_WITHDRAW) AS SUPPRESS_WITHDRAW, ");
		sql.append("   SUM(IEX.BUDGET_WITHDRAW)   AS BUDGET_WITHDRAW, ");
		sql.append("   SUM(IEX.SUM_WITHDRAW)      AS SUM_WITHDRAW, ");
		sql.append("   SUM(IEX.SERVICE_BALANCE)   AS SERVICE_BALANCE, ");
		sql.append("   SUM(IEX.SUPPRESS_BALANCE)  AS SUPPRESS_BALANCE, ");
		sql.append("   SUM(IEX.BUDGET_BALANCE)    AS BUDGET_BALANCE, ");
		sql.append("   SUM(IEX.SUM_BALANCE)       AS SUM_BALANCE, ");
		sql.append("   SUM(IEX.AVERAGE_COST)      AS AVERAGE_COST, ");
		sql.append("   SUM(IEX.AVERAGE_GIVE)      AS AVERAGE_GIVE, ");
		sql.append("   SUM(IEX.AVERAGE_FROM)      AS AVERAGE_FROM, ");
		sql.append("   SUM(IEX.AVERAGE_COME_COST) AS AVERAGE_COME_COST, ");
		sql.append("   SUM(IEX.MONEY_BUDGET)      AS MONEY_BUDGET, ");
		sql.append("   SUM(IEX.MONEY_OUT)         AS MONEY_OUT, ");
		sql.append("   NVL2( SUM(IGB.BRING_FORWARD), SUM(IGB.BRING_FORWARD), 0 )  AS EXPERIMENTAL_BUDGET, ");
		sql.append("   NVL2( SUM(IGB.BRING_FORWARD), SUM(IGB.BRING_FORWARD), 0 ) - SUM(IEX.SUM_WITHDRAW)  AS DIFFERENCE_EXPERIMENTAL_BUDGET, ");
		sql.append("   NVL2(SUM(IGA.CURR_AMT), SUM(IGA.CURR_AMT), 0) AS LEDGER, ");
		sql.append("   NVL2(SUM(IGA.CURR_AMT), SUM(IGA.CURR_AMT), 0) - SUM(IEX.SUM_WITHDRAW) AS DIFFERENCE_LEDGER, "); 
		sql.append("   IEX.OFFICE_CODE ");  
		sql.append(" FROM IA_EXPENSES IEX ");
		sql.append(" INNER JOIN EXCISE_ORG_DISB EOD ");
		sql.append("   ON IEX.OFFICE_CODE = EOD.OFFICE_CODE ");
		sql.append(" LEFT JOIN IA_GFTRIAL_BALANCE IGB ");
		sql.append("   ON '00000'||EOD.DISBURSE_UNIT = IGB.DEPT_DISB ");
		sql.append(" LEFT JOIN IA_GFLEDGER_ACCOUNT IGA ");
		sql.append("   ON IGA.GL_ACC_NO = IEX.ACCOUNT_ID ");
		sql.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(form.getArea())) {
			sql.append(" AND IEX.OFFICE_CODE = ? ");
			params.add(form.getArea());
		}
//		if (StringUtils.isNotBlank(form.getYear())) {
//			sql.append(" AND IEX.BUDGET_YEAR = ? ");
//			params.add(form.getYear());
//		}
		if (StringUtils.isNotBlank(form.getStartYear()) && StringUtils.isNoneBlank(form.getPeriodMonthStart())) {
			sql.append(" AND IEX.EXPENSE_YEAR||EXPENSE_MONTH  >= ? ");
			params.add(form.getStartYear() + StringUtils.leftPad(form.getPeriodMonthStart(), 2, '0').substring(0, 2));
		}
		if (StringUtils.isNotBlank(form.getEndYear()) && StringUtils.isNoneBlank(form.getPeriodMonthEnd())) {
			sql.append(" AND IEX.EXPENSE_YEAR||EXPENSE_MONTH  <= ? ");
			params.add(form.getEndYear() + StringUtils.leftPad(form.getPeriodMonthEnd(), 2, '0').substring(0, 2));
		}
//		sql.append(" AND TO_NUMBER(IGA.PK_CODE) = 40 ");
		sql.append(" GROUP BY IEX.ACCOUNT_ID, ");
		sql.append("   IEX.ACCOUNT_NAME, ");
		sql.append("   IEX.OFFICE_CODE ");
		sql.append(" ORDER BY IEX.ACCOUNT_ID ASC ");
		List<Int090101Vo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), compareRowmapper);
		return data;
	}

	private RowMapper<Int090101Vo> compareRowmapper = new RowMapper<Int090101Vo>() {
		@Override
		public Int090101Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int090101Vo vo = new Int090101Vo();
			vo.setAccountId(rs.getString("ACCOUNT_ID"));
			vo.setAccountName(rs.getString("ACCOUNT_NAME"));
			vo.setAverageComeCost(rs.getString("AVERAGE_COME_COST"));
//			vo.setAverageComeCostOut(rs.getString("AVERAGE_COME_COST_OUT"));
			vo.setAverageCost(rs.getBigDecimal("AVERAGE_COST"));
//			vo.setAverageCostOut(rs.getBigDecimal("AVERAGE_COST_OUT"));
			vo.setAverageFrom(rs.getBigDecimal("AVERAGE_FROM"));
//			vo.setAverageFromOut(rs.getBigDecimal("AVERAGE_FROM_OUT"));
			vo.setAverageGive(rs.getString("AVERAGE_GIVE"));
//			vo.setAverageGiveOut(rs.getString("AVERAGE_GIVE_OUT"));
			vo.setBudgetBalance(rs.getBigDecimal("BUDGET_BALANCE"));
			vo.setBudgetReceive(rs.getBigDecimal("BUDGET_RECEIVE"));
			vo.setBudgetWithdraw(rs.getBigDecimal("BUDGET_WITHDRAW"));
//			vo.setExpenseDate(rs.getDate("EXPENSE_DATE"));
//			vo.setId(rs.getBigDecimal("ID"));
			vo.setMoneyBudget(rs.getBigDecimal("MONEY_BUDGET"));
			vo.setMoneyOut(rs.getBigDecimal("MONEY_OUT"));
//			vo.setNote(rs.getString("NOTE"));
//			vo.setOfficeDesc(rs.getString("OFFICE_DESC"));
			vo.setServiceBalance(rs.getBigDecimal("SERVICE_BALANCE"));
			vo.setServiceReceive(rs.getBigDecimal("SERVICE_RECEIVE"));
			vo.setServiceWithdraw(rs.getBigDecimal("SERVICE_WITHDRAW"));
			vo.setSumBalance(rs.getBigDecimal("SUM_BALANCE"));
			vo.setSumReceive(rs.getBigDecimal("SUM_RECEIVE"));
			vo.setSumWithdraw(rs.getBigDecimal("SUM_WITHDRAW"));
			vo.setSuppressBalance(rs.getBigDecimal("SUPPRESS_BALANCE"));
			vo.setSuppressReceive(rs.getBigDecimal("SUPPRESS_RECEIVE"));
			vo.setSuppressWithdraw(rs.getBigDecimal("SUPPRESS_WITHDRAW"));
			
			vo.setExperimentalBudget(rs.getDouble("EXPERIMENTAL_BUDGET"));
			vo.setDifferenceExperimentalBudget(rs.getDouble("DIFFERENCE_EXPERIMENTAL_BUDGET"));
			vo.setLedger(rs.getDouble("LEDGER"));
			vo.setDifferenceLedger(rs.getDouble("DIFFERENCE_LEDGER"));
			
//			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
//			vo.setExpenseMonth(rs.getString("EXPENSE_MONTH"));
//			vo.setExpenseYear(rs.getString("EXPENSE_YEAR"));
			return vo;
		}
	};

	public List<Int090101Vo> findValidate(Int12040101ValidateSearchFormVo formReq) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM IA_EXPENSES ");
		sql.append(" WHERE ACCOUNT_ID= ? ");
		sql.append(" AND OFFICE_CODE= ? ");
		sql.append(" AND EXPENSE_DATE =TO_DATE( ?, 'YYYYMMDD') ");
		sql.append(" AND IS_DELETED='N'");
		params.add(formReq.getAccountId());
		params.add(formReq.getArea());
		params.add(formReq.getExpenseDateStr());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int090101Vo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int090101Vo.class));
		return data;
	}
}
