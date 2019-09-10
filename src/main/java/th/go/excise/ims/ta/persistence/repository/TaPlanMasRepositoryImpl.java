package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.TaPlanMasVo;

public class TaPlanMasRepositoryImpl implements TaPlanMasRepositoryCustom{
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private void buildfindPlanCountByOfficeCode(StringBuilder sql, List<Object> params,String officeCode,String budgetYear) {
		
		sql.append(" SELECT MAS.*, DTL.*, DTL2.* ");
		sql.append(" FROM TA_PLAN_MAS MAS LEFT JOIN ( SELECT COUNT(PLAN.AUDIT_START_DATE) AUDITCOUNT, ");
		sql.append("  TO_NUMBER (TO_CHAR(PLAN.AUDIT_START_DATE, 'MM')) AUDITMONTH, PLAN.OFFICE_CODE ");
		sql.append("  	FROM TA_PLAN_WORKSHEET_DTL PLAN WHERE  AUDIT_START_DATE IS NOT NULL ");
		sql.append(" 	AND OFFICE_CODE = ? AND PLAN.AUDIT_STATUS = '0501' ");
		params.add(officeCode);
		
		sql.append("  	GROUP BY TO_CHAR(PLAN.AUDIT_START_DATE, 'MM'), PLAN.OFFICE_CODE ) DTL ");
		sql.append("  	  ON MAS.OFFICE_CODE = DTL.OFFICE_CODE ");
		sql.append(" 	  AND TO_CHAR( MAS.MONTH) = TO_CHAR( DTL.AUDITMONTH)   ");
		sql.append(" LEFT JOIN ( SELECT COUNT(PLAN.AUDIT_START_DATE) SUMCOUNT, ");
		sql.append("  	TO_NUMBER (TO_CHAR(PLAN.AUDIT_START_DATE, 'MM')) SUMMONTH, PLAN.OFFICE_CODE ");
		sql.append(" FROM  TA_PLAN_WORKSHEET_DTL PLAN ");
		sql.append(" WHERE  AUDIT_START_DATE IS NOT NULL  AND OFFICE_CODE = ? AND PLAN.AUDIT_STATUS = '0503' ");
		params.add(officeCode);
		
		sql.append(" GROUP BY TO_CHAR(PLAN.AUDIT_START_DATE, 'MM'),  PLAN.OFFICE_CODE ");
		sql.append(" ) DTL2 ON MAS.OFFICE_CODE = DTL.OFFICE_CODE ");
		sql.append(" AND TO_CHAR(  MAS.MONTH ) = TO_CHAR(  DTL2.SUMMONTH) ");
		sql.append(" WHERE   MAS.BUDGET_YEAR = ?  AND MAS.OFFICE_CODE = ? ");
		params.add(budgetYear);
		params.add(officeCode);
		
	}

	
	private void buildfindPlanCountByCentral(StringBuilder sql, List<Object> params,String officeCode,String budgetYear) {
		
		sql.append(" SELECT  MAS.MONTH, SUM ( MAS.FAC_NUM) FACNUM,DTL.AUDITCOUNT,DTL2.SUMCOUNT  ");
		sql.append(" FROM TA_PLAN_MAS MAS LEFT JOIN ( SELECT COUNT(PLAN.AUDIT_START_DATE) AUDITCOUNT, ");
		sql.append("  TO_NUMBER (TO_CHAR(PLAN.AUDIT_START_DATE, 'MM')) AUDITMONTH ");
		sql.append("  	FROM TA_PLAN_WORKSHEET_DTL PLAN WHERE  AUDIT_START_DATE IS NOT NULL ");
		sql.append(" 	AND OFFICE_CODE LIKE ? AND PLAN.AUDIT_STATUS = '0501' ");
		params.add(officeCode);
		
		sql.append("  	GROUP BY TO_CHAR(PLAN.AUDIT_START_DATE, 'MM') ) DTL ");
		sql.append("  	  ON  ");
		sql.append(" 	   TO_CHAR( MAS.MONTH) = TO_CHAR( DTL.AUDITMONTH)   ");
		sql.append(" LEFT JOIN ( SELECT COUNT(PLAN.AUDIT_START_DATE) SUMCOUNT, ");
		sql.append("  	TO_NUMBER (TO_CHAR(PLAN.AUDIT_START_DATE, 'MM')) SUMMONTH ");
		sql.append(" FROM  TA_PLAN_WORKSHEET_DTL PLAN ");
		sql.append(" WHERE  AUDIT_START_DATE IS NOT NULL  AND OFFICE_CODE LIKE ? AND PLAN.AUDIT_STATUS = '0503' ");
		params.add(officeCode);
		
		sql.append(" GROUP BY TO_CHAR(PLAN.AUDIT_START_DATE, 'MM') ");
		sql.append(" ) DTL2 ON  ");
		sql.append("  TO_CHAR(  MAS.MONTH ) = TO_CHAR(  DTL2.SUMMONTH) ");
		sql.append(" WHERE   MAS.BUDGET_YEAR = ?  AND MAS.OFFICE_CODE LIKE ? ");
		sql.append(" GROUP BY MAS.MONTH,DTL.AUDITCOUNT,DTL2.SUMCOUNT ");
		params.add(budgetYear);
		params.add(officeCode);
		
	}
	
	@Override
	public List<TaPlanMasVo> findPlanCountByOfficeCode(String officeCode,String budgetYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();

		buildfindPlanCountByOfficeCode(sql, params, officeCode,budgetYear);
		
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanMas);

	}
	
	private static final RowMapper<TaPlanMasVo> taPlanMas = new RowMapper<TaPlanMasVo>() {
		@Override
		public TaPlanMasVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaPlanMasVo vo = new TaPlanMasVo();
			
			vo.setPlanMasId(rs.getLong("PLAN_MAS_ID"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setMonth(rs.getString("MONTH"));
			vo.setFacNum(rs.getInt("FAC_NUM"));
			vo.setAuditCount(rs.getString("AUDITCOUNT"));
			vo.setAuditMonth(rs.getString("AUDITMONTH"));
			vo.setSumCount(rs.getString("SUMCOUNT"));
			vo.setSumMonth(rs.getString("SUMMONTH"));
			
			return vo;
		}
	};
	
	private static final RowMapper<TaPlanMasVo> taPlanMasCentral = new RowMapper<TaPlanMasVo>() {
		@Override
		public TaPlanMasVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaPlanMasVo vo = new TaPlanMasVo();

			vo.setMonth(rs.getString("MONTH"));
			vo.setFacNum(rs.getInt("FACNUM"));
			vo.setAuditCount(rs.getString("AUDITCOUNT"));
			vo.setSumCount(rs.getString("SUMCOUNT"));

			
			return vo;
		}
	};

	@Override
	public List<TaPlanMasVo> findPlanCountByCentral(String budgetYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		String officeCode = "0014__";
		buildfindPlanCountByCentral(sql, params, officeCode,budgetYear);
		
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanMasCentral);
	}

}
