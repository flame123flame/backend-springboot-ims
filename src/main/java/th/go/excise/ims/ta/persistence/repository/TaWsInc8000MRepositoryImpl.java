package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.StringUtils;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

public class TaWsInc8000MRepositoryImpl implements TaWsInc8000MRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsInc8000MRepositoryImpl.class);
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth) {
		logger.info("findByMonthRange startMonth={}, endMonth={}", startMonth, endMonth);

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ( ");
		sql.append("   SELECT I.*, I.TAX_YEAR || DECODE(LENGTH(I.TAX_MONTH), 2 ,I.TAX_MONTH , '0' || I.TAX_MONTH) YEAR_MONTH ");
		sql.append("   FROM TA_WS_INC8000_M I ");
		sql.append(" ) INC ");
		sql.append(" WHERE INC.IS_DELETED = 'N' ");
		sql.append("   AND INC.YEAR_MONTH >= ? ");
		sql.append("   AND INC.YEAR_MONTH <= ? ");
		sql.append(" ORDER BY INC.YEAR_MONTH ");

		List<Object> paramList = new ArrayList<>();
		paramList.add(startMonth);
		paramList.add(endMonth);

		Map<String, List<TaWsInc8000M>> wsInc8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, List<TaWsInc8000M>>>() {
			public Map<String, List<TaWsInc8000M>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<TaWsInc8000M>> dataMap = new HashMap<>();
				List<TaWsInc8000M> dataList = null;
				while (rs.next()) {
					dataList = dataMap.get(rs.getString(TaWsInc8000M.Field.NEW_REG_ID));
					if (dataList == null) {
						dataList = new ArrayList<>();
					}
					TaWsInc8000M taWsInc8000M = new TaWsInc8000M();
					taWsInc8000M.setNewRegId(rs.getString(TaWsInc8000M.Field.WS_INC8000_M_ID));
					taWsInc8000M.setTaxAmount(rs.getBigDecimal(TaWsInc8000M.Field.TAX_AMOUNT));
					taWsInc8000M.setTaxYear(rs.getString(TaWsInc8000M.Field.TAX_YEAR));
					taWsInc8000M.setTaxMonth(rs.getString(TaWsInc8000M.Field.TAX_MONTH));
					dataList.add(taWsInc8000M);
					dataMap.put(rs.getString(TaWsInc8000M.Field.NEW_REG_ID), dataList);
				}
				return dataMap;
			}
		});

		return wsInc8000MMap;
	}
	
	@Override
	public Map<String, BigDecimal> findByMonthRangeDuty(String newRegId, String dutyCode, WorksheetDateRangeVo dateRangeVo, String incomeTaxType) {
		logger.info("findByMonthRange newRegId={}, dutyCode={}, incomeTaxType={}", newRegId, dutyCode, incomeTaxType);

		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT INC.* FROM ( ");
		sql.append("   SELECT NEW_REG_ID, DUTY_CODE, TAX_YEAR||LPAD(TAX_MONTH ,2 ,'0') AS YEAR_MONTH, TAX_AMOUNT, NET_TAX_AMOUNT ");
		sql.append("   FROM TA_WS_INC8000_M ");
		sql.append("   WHERE IS_DELETED = 'N' ");
		sql.append("     AND NEW_REG_ID = ? ");
		sql.append("     AND DUTY_CODE = ? ");
		sql.append(" ) INC ");
		sql.append(" WHERE 1 = 1 ");
		sql.append("   AND ( ");
		sql.append("     (INC.YEAR_MONTH >= ? AND INC.YEAR_MONTH <= ?) ");
		sql.append("     OR ");
		sql.append("     (INC.YEAR_MONTH >= ? AND INC.YEAR_MONTH <= ?) ");
		sql.append("   ) ");
		sql.append(" ORDER BY INC.NEW_REG_ID, INC.DUTY_CODE, INC.YEAR_MONTH ");

		paramList.add(newRegId);
		paramList.add(dutyCode);
		paramList.add(dateRangeVo.getYmG1StartInc8000M());
		paramList.add(dateRangeVo.getYmG1EndInc8000M());
		paramList.add(dateRangeVo.getYmG2StartInc8000M());
		paramList.add(dateRangeVo.getYmG2EndInc8000M());
		
		Map<String, BigDecimal> incfri8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, BigDecimal>>() {
			public Map<String, BigDecimal> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, BigDecimal> incomeMap = new HashMap<>();
				while (rs.next()) {
					if (TA_CONFIG.INCOME_TYPE_TAX.equals(incomeTaxType)) {
						incomeMap.put(rs.getString("YEAR_MONTH"), rs.getBigDecimal("TAX_AMOUNT"));
					} else {
						incomeMap.put(rs.getString("YEAR_MONTH"), rs.getBigDecimal("NET_TAX_AMOUNT"));
					}
				}
				return incomeMap;
			}
		});

		return incfri8000MMap;
	}
	
	@Override
	public Map<String, BigDecimal> findByMonthRangePivot(String newRegId, String dutyCode, List<String> yyyyMMList, String incomeTaxType) {
		logger.info("findByMonthRange newRegId={}, dutyCode={}, incomeTaxType={}", newRegId, dutyCode, incomeTaxType);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM ( ");
		sql.append("   SELECT NEW_REG_ID, DUTY_CODE, TAX_YEAR || TAX_MONTH AS YEAR_MONTH ");
		if (TA_CONFIG.INCOME_TYPE_TAX.equals(incomeTaxType)) {
			sql.append(" , SUM(TAX_AMOUNT) AS TAX_AMOUNT ");
		} else {
			sql.append(" , SUM(NET_TAX_AMOUNT) AS TAX_AMOUNT ");
		}
		sql.append("   FROM TA_WS_INC8000_M ");
		sql.append("   WHERE NEW_REG_ID = ? ");
		sql.append("   AND DUTY_CODE = ? ");
		sql.append("   GROUP BY NEW_REG_ID, DUTY_CODE, TAX_YEAR, TAX_MONTH ");
		sql.append(" ) ");
		sql.append(" PIVOT(SUM(TAX_AMOUNT) FOR YEAR_MONTH IN ( ");
		sql.append(StringUtils.collectionToDelimitedString(yyyyMMList, ",", "'", "'"));
		sql.append(" )) ");
		
		List<Object> paramList = new ArrayList<>();
		paramList.add(newRegId);
		paramList.add(dutyCode);
		
		Map<String, BigDecimal> incfri8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, BigDecimal>>() {
			public Map<String, BigDecimal> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, BigDecimal> incomeMap = new HashMap<>();
				while (rs.next()) {
					for (String yyyyMM : yyyyMMList) {
						incomeMap.put(yyyyMM, rs.getBigDecimal("'" + yyyyMM + "'"));
					}
				}
				return incomeMap;
			}
		});
		
		return incfri8000MMap;
	}

}
