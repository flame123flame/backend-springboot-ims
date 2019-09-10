package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.vo.Int0801Vo;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;
import th.go.excise.ims.ia.vo.Int0803Search;
import th.go.excise.ims.ia.vo.Int0803TableVo;
import th.go.excise.ims.ia.vo.SearchVo;

public class IaGftrialBalanceRepositoryImpl implements IaGftrialBalanceRepositorCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFTRIAL_BALANCE",
				Arrays.asList("IA_GFTRIAL_BALANCE_ID", "DEPT_DISB", "PERIOD_FROM", "PERIOD_TO", "PERIOD_YEAR", "ACC_NO",
						"ACC_NAME", "CARRY_FORWARD", "BRING_FORWARD", "DEBIT", "CREDIT", "CREATED_BY"),
				"IA_GFTRIAL_BALANCE_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGftrialBalances, 1000,
				new ParameterizedPreparedStatementSetter<IaGftrialBalance>() {
					public void setValues(PreparedStatement ps, IaGftrialBalance entity) throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						paramList.add(entity.getDeptDisb());
						paramList.add(entity.getPeriodFrom());
						paramList.add(entity.getPeriodTo());
						paramList.add(entity.getPeriodYear());
						paramList.add(entity.getAccNo());
						paramList.add(entity.getAccName());
						paramList.add(entity.getCarryForward());
						paramList.add(entity.getBringForward());
						paramList.add(entity.getDebit());
						paramList.add(entity.getCredit());
						paramList.add(username);
						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});

	}

	@Override
	public List<IaGftrialBalance> findByGfDisburseUnit(String gfDisburseUnit) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT DISTINCT PERIOD_FROM ");
		sql.append(" FROM IA_GFTRIAL_BALANCE ");
		sql.append(" WHERE IS_DELETED = 'N' ");

		sql.append(" AND DEPT_DISB LIKE ? ");
		params.add("%".concat(gfDisburseUnit.trim()));

		sql.append(" ORDER BY PERIOD_FROM ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaGftrialBalance> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaGftrialBalance.class));
		return response;
	}

	@Override
	public List<Int0802Vo> findDiferrenceByConditionTab1(Int0802SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" SELECT G.ACC_NO, G.ACC_NAME, SUM(G.BRING_FORWARD) BRING_FORWARD, SUM(G.DEBIT) DEBIT, SUM(G.CREDIT) CREDIT, ");
		sql.append(" 	SUM(G.CARRY_FORWARD) CARRY_FORWARD, GA.PK_CODE, sum(GA.CURR_AMT) CURR_AMT  ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 	SELECT X.* FROM ");
		sql.append(" 		( ");
		sql.append(" 		SELECT CONCAT(G.PERIOD_YEAR, G.PERIOD_FROM) AS YEAR_MONTH, G.* FROM IA_GFTRIAL_BALANCE G ");
		sql.append(" 		) X WHERE X.IS_DELETED = 'N'");

		sql.append(" 	 AND YEAR_MONTH >= ? AND YEAR_MONTH <= ? ");
		params.add(request.getPeriodFromYear());
		params.add(request.getPeriodToYear());

		sql.append(" 	 ) G ");
		sql.append(" LEFT JOIN EXCISE_ORG_GFMIS E ");
		sql.append(" 	ON G.DEPT_DISB  = '00000' || E.GF_DISBURSE_UNIT ");
		sql.append(" 	AND E.GF_DISBURSE_UNIT = E.GF_COST_CENTER  ");
		sql.append(" 	AND E.IS_DELETED = 'N' ");
		sql.append(" LEFT JOIN IA_GFLEDGER_ACCOUNT GA ");
		sql.append(" 	ON GA.GL_ACC_NO = G.ACC_NO ");
		sql.append(" 	AND GA.IS_DELETED = 'N' ");

		sql.append(" WHERE E.GF_DISBURSE_UNIT = ? ");
		params.add(request.getGfDisburseUnit());

		sql.append(" GROUP BY G.ACC_NO, G.ACC_NAME, GA.PK_CODE ");
		sql.append(" ORDER BY G.ACC_NO ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0802Vo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int0802Vo.class));
		return response;
	}
	
	@Override
	public List<Int0802Vo> findDiferrenceByConditionTab2(Int0802SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" SELECT G.ACC_NO, G.ACC_NAME, SUM(G.BRING_FORWARD) BRING_FORWARD, SUM(G.DEBIT) DEBIT, SUM(G.CREDIT) CREDIT, ");
		sql.append(" 	SUM(G.CARRY_FORWARD) CARRY_FORWARD, GA.PK_CODE, sum(GA.CURR_AMT) CURR_AMT, SUM(GFM.DEBIT) DEBIT3, SUM(GFM.CREDIT) CREDIT3 ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 	SELECT X.* FROM ");
		sql.append(" 		( ");
		sql.append(" 		SELECT CONCAT(G.PERIOD_YEAR, G.PERIOD_FROM) AS YEAR_MONTH, G.* FROM IA_GFTRIAL_BALANCE G ");
		sql.append(" 		) X WHERE X.IS_DELETED = 'N'");

		sql.append(" 	 AND YEAR_MONTH >= ? AND YEAR_MONTH <= ? ");
		params.add(request.getPeriodFromYear());
		params.add(request.getPeriodToYear());

		sql.append(" 	 ) G ");
		sql.append(" LEFT JOIN EXCISE_ORG_GFMIS E ");
		sql.append(" 	ON G.DEPT_DISB  = '00000' || E.GF_DISBURSE_UNIT ");
		sql.append(" 	AND E.GF_DISBURSE_UNIT = E.GF_COST_CENTER  ");
		sql.append(" 	AND E.IS_DELETED = 'N' ");
		sql.append(" LEFT JOIN IA_GFLEDGER_ACCOUNT GA ");
		sql.append(" 	ON GA.GL_ACC_NO = G.ACC_NO ");
		sql.append(" 	AND GA.IS_DELETED = 'N' ");
		sql.append(" LEFT JOIN IA_GFMOVEMENT_ACCOUNT GFM ");
		sql.append(" 	ON G.ACC_NO = GFM.ACC_TYPE_NO ");
		sql.append(" 	AND GFM.IS_DELETED = 'N' ");

		sql.append(" WHERE E.GF_DISBURSE_UNIT = ? ");
		params.add(request.getGfDisburseUnit());

		sql.append(" GROUP BY G.ACC_NO, G.ACC_NAME, GA.PK_CODE ");
		sql.append(" ORDER BY G.ACC_NO ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0802Vo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int0802Vo.class));
		return response;
	}
	
	@Override
	public List<Int0803TableVo> findExperimentalBudgetByRequest(Int0803Search request) {
		final String SQL = " SELECT H.ACC_NO, D.OFFICE_CODE, D.DISBURSE_UNIT, SUM(H.CARRY_FORWARD) SUM_CARRY_FORWARD,  " +
				" SUM(H.BRING_FORWARD) BRING_FORWARD, SUM(H.DEBIT) DEBIT, SUM(H.CREDIT) CREDIT " +
				" FROM IA_GFTRIAL_BALANCE H " +
				" RIGHT JOIN EXCISE_ORG_DISB D " +
				" 	ON '00000'||D.DISBURSE_UNIT = H.DEPT_DISB " +
				" 	AND concat(H.period_year, H.period_from) >= ? " +
				" 	AND concat(H.period_year, H.period_from) <= ? " +
				" 	AND H.ACC_NO = ? " +
				" WHERE D.OFFICE_CODE LIKE ? " +
				" GROUP BY H.ACC_NO, D.OFFICE_CODE, D.DISBURSE_UNIT " +
				" ORDER BY H.ACC_NO, D.OFFICE_CODE ";
		
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<Object>();
		
		params.add(request.getPeriodDateFromStr());
		params.add(request.getPeriodDateToStr());
		params.add(request.getCoaCode());
		params.add(request.getOfficeCode().substring(0, 4).concat("%"));
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0803TableVo>() {
			@Override
			public Int0803TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0803TableVo vo = new Int0803TableVo();
				vo.setAccNo(rs.getString("ACC_NO"));
				vo.setBringForward(rs.getBigDecimal("BRING_FORWARD"));
				vo.setCarryForward(rs.getBigDecimal("SUM_CARRY_FORWARD"));
				vo.setCredit(rs.getBigDecimal("CREDIT"));
				vo.setDebit(rs.getBigDecimal("DEBIT"));
				vo.setOfficeCode(rs.getString("OFFICE_CODE"));
				vo.setDisburseUnit(rs.getString("DISBURSE_UNIT"));
				return vo;
			}
		});
	}
	
	@Override
	public List<Int0803TableVo> findDepositsReportByRequest(Int0803Search request) {
		final String SQL = " SELECT H.ACC_NO, D.OFFICE_CODE, D.DISBURSE_UNIT, SUM(H.CARRY_FORWARD) SUM_CARRY_FORWARD,  " +
				" SUM(H.DEBIT) DEBIT, SUM(H.CREDIT) CREDIT " +
				" FROM IA_GFMOVEMENT_ACCOUNT H " +
				" RIGHT JOIN EXCISE_ORG_DISB D " +
				" 	ON D.DISBURSE_UNIT = 0||H.DEPT_DISB " +
				" 	AND H.ACC_NO = ? " +
				" 	AND TRUNC(H.GF_DOC_DATE) >= TO_DATE(?, 'dd/mm/yyyy') " +
				" 	AND TRUNC(H.GF_DOC_DATE) <= TO_DATE(?, 'dd/mm/yyyy') " +
				" WHERE D.OFFICE_CODE LIKE ? " +
				" GROUP BY H.ACC_NO, D.OFFICE_CODE, D.DISBURSE_UNIT " +
				" ORDER BY H.ACC_NO, D.OFFICE_CODE ";
		
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<Object>();
		
		params.add(request.getGfDepositCode());
		params.add(request.getPeriodDateFrom());
		params.add(request.getPeriodDateTo());
		params.add(request.getOfficeCode().substring(0, 4).concat("%"));
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0803TableVo>() {
			@Override
			public Int0803TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0803TableVo vo = new Int0803TableVo();
				vo.setAccNo(rs.getString("ACC_NO"));
//				vo.setBringForward(rs.getBigDecimal("BRING_FORWARD"));
				vo.setCarryForward(rs.getBigDecimal("SUM_CARRY_FORWARD"));
				vo.setCredit(rs.getBigDecimal("CREDIT"));
				vo.setDebit(rs.getBigDecimal("DEBIT"));
				vo.setOfficeCode(rs.getString("OFFICE_CODE"));
				vo.setDisburseUnit(rs.getString("DISBURSE_UNIT"));
				return vo;
			}
		});
	}
	
	@Override
	public List<Int0801Vo> findDataAccByRequest(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" SELECT B.ACC_NO, B.ACC_NAME, B.BRING_FORWARD, B.CREDIT, B.DEBIT, B.CARRY_FORWARD ");
		sql.append(" , C.BALANCE_ACC_TYPE, C.VALUE_TRUE_TYPE ");
		sql.append(" FROM IA_GFTRIAL_BALANCE B ");
		sql.append(" INNER JOIN IA_CHART_OF_ACC C ON B.ACC_NO = C.COA_CODE ");
		sql.append(" WHERE 1=1 ");
		
		if(StringUtils.isNotBlank(request.getDeptDisb())) {
			sql.append(" AND B.DEPT_DISB = ? ");
			params.add("00000".concat(request.getDeptDisb()));
		}
		
		if(StringUtils.isNotBlank(request.getPeriod()) && StringUtils.isNotBlank(request.getPeriodYear())) {
			sql.append(" AND B.PERIOD_YEAR||B.PERIOD_FROM = ? ");
			params.add(request.getPeriodYear().concat(request.getPeriod()));
		}
		
		if(StringUtils.isNotBlank(request.getFlag())) {
			sql.append(" AND B.ACC_NO LIKE ? ");
			params.add(request.getFlag().concat("%"));
		}
		sql.append(" ORDER BY B.ACC_NO ");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0801Vo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Int0801Vo.class));
		return response;
	}

}
