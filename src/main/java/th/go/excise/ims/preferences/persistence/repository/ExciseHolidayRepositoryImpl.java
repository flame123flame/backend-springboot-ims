package th.go.excise.ims.preferences.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.inquiryholiday.model.Holiday;

public class ExciseHolidayRepositoryImpl implements ExciseHolidayRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseHolidayRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<Holiday> holidayList) {
		logger.info("batchMerge holidayList.size()={}", holidayList.size());

		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EH.IS_DELETED = ?",
			"EH.UPDATED_BY = ?",
			"EH.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EH.HOLIDAY_ID",
			"EH.HOLIDAY_DATE",
			"EH.CREATED_BY",
			"EH.CREATED_DATE"
		));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_HOLIDAY EH ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EH.HOLIDAY_DATE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_HOLIDAY_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), holidayList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<Holiday>() {
			public void setValues(PreparedStatement ps, Holiday holiday) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(LocalDate.parse(holiday.getHolidayDate(), DateTimeFormatter.BASIC_ISO_DATE));
				// Update Statement
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(LocalDate.parse(holiday.getHolidayDate(), DateTimeFormatter.BASIC_ISO_DATE));
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
