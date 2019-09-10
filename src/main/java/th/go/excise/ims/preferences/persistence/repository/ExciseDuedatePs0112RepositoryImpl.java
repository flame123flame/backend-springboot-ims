package th.go.excise.ims.preferences.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.go.excise.ims.preferences.persistence.entity.ExciseDuedatePs0112;

public class ExciseDuedatePs0112RepositoryImpl implements ExciseDuedatePs0112RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseDuedatePs0112RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<ExciseDuedatePs0112> exciseDuedatePs0112List) {
		logger.info("batchMerge exciseDuedatePs0112List.size()={}", exciseDuedatePs0112List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EDPS0112.DUEDATE = ?",
			"EDPS0112.IS_DELETED = ?",
			"EDPS0112.UPDATED_BY = ?",
			"EDPS0112.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EDPS0112.DUEDATE_PS0112_ID",
			"EDPS0112.YEAR",
			"EDPS0112.MONTH",
			"EDPS0112.DUEDATE",
			"EDPS0112.CREATED_BY",
			"EDPS0112.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_DUEDATE_PS0112 EDPS0112 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EDPS0112.YEAR = ? AND EDPS0112.MONTH = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_DUEDATE_PS0112_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), exciseDuedatePs0112List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<ExciseDuedatePs0112>() {
			public void setValues(PreparedStatement ps, ExciseDuedatePs0112 exciseDuedatePs0112) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(exciseDuedatePs0112.getYear());
				paramList.add(exciseDuedatePs0112.getMonth());
				// Update Statement
				paramList.add(exciseDuedatePs0112.getDuedate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(exciseDuedatePs0112.getUpdatedBy());
				paramList.add(exciseDuedatePs0112.getUpdatedDate());
				// Insert Statement
				paramList.add(exciseDuedatePs0112.getYear());
				paramList.add(exciseDuedatePs0112.getMonth());
				paramList.add(exciseDuedatePs0112.getDuedate());
				paramList.add(exciseDuedatePs0112.getCreatedBy());
				paramList.add(exciseDuedatePs0112.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public Map<String, LocalDate> findByMonthRange(String startMonthTh, String endMonthTh) {
		logger.info("findByMonthRange startMonthTh={}, endMonthTh={}", startMonthTh, endMonthTh);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DD.YEAR_MONTH, DD.DUEDATE FROM ( ");
		sql.append("   SELECT DP.YEAR || DECODE(LENGTH(DP.MONTH), 2, DP.MONTH, '0' || DP.MONTH) YEAR_MONTH, DP.DUEDATE, DP.IS_DELETED ");
		sql.append("   FROM EXCISE_DUEDATE_PS0112 DP ");
		sql.append(" ) DD ");
		sql.append(" WHERE DD.IS_DELETED = 'N' ");
		sql.append("   AND DD.YEAR_MONTH >= ? ");
		sql.append("   AND DD.YEAR_MONTH <= ? ");
		sql.append(" ORDER BY DD.YEAR_MONTH ");
		
		List<Object> paramList = new ArrayList<>();
		paramList.add(startMonthTh);
		paramList.add(endMonthTh);
		
		Map<String, LocalDate> duedateMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, LocalDate>>() {
			public Map<String, LocalDate> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, LocalDate> dataMap = new HashMap<>();
				while (rs.next()) {
					dataMap.put(rs.getString("YEAR_MONTH"), LocalDateConverter.convertToEntityAttribute(rs.getDate("DUEDATE")));
				}
				return dataMap;
			}
		});
		
		return duedateMap;
	}
	
}
