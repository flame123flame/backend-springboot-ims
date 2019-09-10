package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.common.util.ExciseUtils;

public class TaPlanWorksheetHisRepositoryImpl implements TaPlanWorksheetHisRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaPlanWorksheetHisRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public Map<String, String> findAuditPlanCodeByOfficeCodeAndBudgetYearList(String officeCode, List<String> budgetYearList) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();

		sql.append(" SELECT PLAN_HIS.BUDGET_YEAR ");
		sql.append("   ,PLAN_HIS.NEW_REG_ID ");
		sql.append("   ,PLAN_HIS.OFFICE_CODE ");
		sql.append("   ,PLAN_HIS.AUDIT_PLAN_CODE ");
		sql.append(" FROM TA_PLAN_WORKSHEET_HIS PLAN_HIS ");
		sql.append(" WHERE PLAN_HIS.OFFICE_CODE LIKE ? ");
		params.add(ExciseUtils.whereInLocalOfficeCode(officeCode));

		sql.append("   AND PLAN_HIS.BUDGET_YEAR IN (").append(StringUtils.repeat("?", ",", budgetYearList.size())).append(") ");
		params.addAll(budgetYearList);

		Map<String, String> resultMap = commonJdbcTemplate.query(sql.toString(), params.toArray(), new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, String> resultMap = new HashMap<>();
				while (rs.next()) {
					resultMap.put(rs.getString("BUDGET_YEAR") + rs.getString("NEW_REG_ID"), rs.getString("AUDIT_PLAN_CODE"));
				}
				return resultMap;
			}
		});

		return resultMap;
	}

	@Override
	public Map<String, String> findMaxTaxAuditYear() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT NEW_REG_ID, MAX(BUDGET_YEAR) MAX_YEAR ");
		sql.append(" FROM TA_PLAN_WORKSHEET_HIS ");
		sql.append(" WHERE PLAN_FLAG = 'Y' ");
		sql.append(" GROUP BY NEW_REG_ID ");

		Map<String, String> resultMap = commonJdbcTemplate.query(sql.toString(), new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, String> resultMap = new HashMap<>();
				while (rs.next()) {
					resultMap.put(rs.getString("NEW_REG_ID"), rs.getString("MAX_YEAR"));
				}
				return resultMap;
			}
		});

		return resultMap;
	}

	@Override
	public Map<String, List<String>> findAuditPlanCodeByNewRegId(List<String> newRegIdList, List<String> budgetYearList) {
		logger.info("findAuditPlanCodeByNewRegId");

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT BUDGET_YEAR, NEW_REG_ID, AUDIT_PLAN_CODE ");
		sql.append(" FROM TA_PLAN_WORKSHEET_HIS ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append("   AND NEW_REG_ID IN ( ");
		sql.append(StringUtils.repeat("?", ",", newRegIdList.size()));
		sql.append("   ) ");
		sql.append("   AND BUDGET_YEAR IN ( ");
		sql.append(StringUtils.repeat("?", ",", budgetYearList.size()));
		sql.append("   ) ");
		sql.append(" ORDER BY NEW_REG_ID, BUDGET_YEAR, AUDIT_SEQ ");

		List<Object> paramList = new ArrayList<>();
		paramList.addAll(newRegIdList);
		paramList.addAll(budgetYearList);

		Map<String, List<String>> auditPlanMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, List<String>>>() {
			@Override
			public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<String>> tmpAuditPlanMap = new HashMap<>();
				List<String> auditPlanList = null;
				String key = null;
				while (rs.next()) {
					key = rs.getString("BUDGET_YEAR") + rs.getString("NEW_REG_ID");
					auditPlanList = tmpAuditPlanMap.get(key);
					if (auditPlanList == null) {
						auditPlanList = new ArrayList<>();
					}
					auditPlanList.add(rs.getString("AUDIT_PLAN_CODE"));
					tmpAuditPlanMap.put(key, auditPlanList);
				}
				return tmpAuditPlanMap;
			}
		});

		return auditPlanMap;
	}


}
