package th.go.excise.ims.ws.persistence.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000M;

public class WsIncfri8000MRepositoryImpl implements WsIncfri8000MRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000MRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void forceDeleteByDateType(String dateType, String taxYear, String taxMonth) {
		logger.info("forceDeleteByDateType dateType={}, taxYear={}, taxMonth={}", dateType, taxYear, taxMonth);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE WS_INCFRI8000_M ");
		sql.append(" WHERE DATE_TYPE = ? ");
		sql.append("   AND TAX_YEAR = ? ");
		sql.append("   AND TAX_MONTH = ? ");
		
		int rowsAffected = commonJdbcTemplate.update(sql.toString(), new Object[] {
			dateType,
			taxYear,
			taxMonth
		});
		
		logger.debug("rowsAffected={}", rowsAffected);
	}
	
	@Override
	public void batchInsert(List<WsIncfri8000M> incfri8000MList) {
		logger.info("batchInsert incfri8000MList.size()={}", incfri8000MList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"INCFRI8000_M_ID",
			"DATE_TYPE",
			"REG_ID",
			"NEW_REG_ID",
			"DUTY_GROUP_ID",
			"TAX_YEAR",
			"TAX_MONTH",
			"TAX_AMOUNT",
			"IS_DELETED",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8000_M", insertColumnNames, "WS_INCFRI8000_M_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, incfri8000MList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000M>() {
			public void setValues(PreparedStatement ps, WsIncfri8000M incfri8000M) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(incfri8000M.getDateType());
				paramList.add(incfri8000M.getRegId());
				paramList.add(incfri8000M.getNewRegId());
				paramList.add(incfri8000M.getDutyGroupId());
				paramList.add(incfri8000M.getTaxYear());
				paramList.add(incfri8000M.getTaxMonth());
				paramList.add(incfri8000M.getTaxAmount());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public Map<String, Map<String, BigDecimal>> findByMonthRange(List<String> newRegIdList, String startMonth, String endMonth) {
		logger.info("findByMonthRange startMonth={}, endMonth={}", startMonth, endMonth);

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT INC.NEW_REG_ID, INC.DUTY_GROUP_ID, INC.YEAR_MONTH, INC.TAX_AMOUNT ");
		sql.append(" FROM ( ");
		sql.append("   SELECT I.NEW_REG_ID, I.DUTY_GROUP_ID, I.TAX_AMOUNT, I.TAX_YEAR || DECODE(LENGTH(I.TAX_MONTH), 2 ,I.TAX_MONTH , '0' || I.TAX_MONTH) YEAR_MONTH ");
		sql.append("   FROM WS_INCFRI8000_M I ");
		sql.append("   WHERE I.IS_DELETED = 'N' ");
		if (newRegIdList != null && newRegIdList.size() > 0) {
			sql.append("     AND (I.NEW_REG_ID || I.DUTY_GROUP_ID) IN ( ").append(StringUtils.repeat("?", ",", newRegIdList.size())).append(" ) ");
		}
		sql.append(" ) INC ");
		sql.append(" WHERE 1=1 ");
		sql.append("   AND INC.YEAR_MONTH >= ? ");
		sql.append("   AND INC.YEAR_MONTH <= ? ");
		sql.append(" ORDER BY INC.NEW_REG_ID, INC.DUTY_GROUP_ID, INC.YEAR_MONTH ");

		List<Object> paramList = new ArrayList<>();
		if (newRegIdList != null && newRegIdList.size() > 0) {
			paramList.addAll(newRegIdList);
		}
		paramList.add(startMonth);
		paramList.add(endMonth);

		Map<String, Map<String, BigDecimal>> incfri8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, Map<String, BigDecimal>>>() {
			public Map<String, Map<String, BigDecimal>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Map<String, BigDecimal>> newRegIdMap = new HashMap<>();
				Map<String, BigDecimal> incomeMap = null;
				String mainKey = null;
				while (rs.next()) {
					mainKey = rs.getString("NEW_REG_ID") + "|" + rs.getString("DUTY_GROUP_ID");
					incomeMap = newRegIdMap.get(mainKey);
					if (incomeMap == null) {
						incomeMap = new HashMap<>();
					}
					incomeMap.put(rs.getString("YEAR_MONTH"), rs.getBigDecimal("TAX_AMOUNT"));
					newRegIdMap.put(mainKey, incomeMap);
				}
				return newRegIdMap;
			}
		});

		return incfri8000MMap;
	}
	
}
