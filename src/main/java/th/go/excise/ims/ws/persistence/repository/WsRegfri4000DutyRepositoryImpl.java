package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;

public class WsRegfri4000DutyRepositoryImpl implements WsRegfri4000DutyRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000DutyRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<WsRegfri4000Duty> regfri4000DutyList) {
		logger.info("batchMerge regfri4000DutyList.size()={}", regfri4000DutyList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000D.DUTY_GROUP_NAME = ?",
			"WREG4000D.REG_DATE = ?",
			"WREG4000D.IS_DELETED = ?",
			"WREG4000D.UPDATED_BY = ?",
			"WREG4000D.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000D.REGFRI4000_DUTY_ID",
			"WREG4000D.NEW_REG_ID",
			"WREG4000D.DUTY_GROUP_ID",
			"WREG4000D.DUTY_GROUP_NAME",
			"WREG4000D.REG_DATE",
			"WREG4000D.CREATED_BY",
			"WREG4000D.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_REGFRI4000_DUTY WREG4000D ");
		sql.append(" USING DUAL ");
		sql.append(" ON ( ");
		sql.append("   WREG4000D.NEW_REG_ID = ? ");
		sql.append("   AND WREG4000D.DUTY_GROUP_ID = ? ");
		sql.append(" ) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_REGFRI4000_DUTY_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), regfri4000DutyList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsRegfri4000Duty>() {
			public void setValues(PreparedStatement ps, WsRegfri4000Duty regfri4000Duty) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(regfri4000Duty.getNewRegId());
				paramList.add(regfri4000Duty.getDutyGroupId());
				// Update Statement
				paramList.add(regfri4000Duty.getDutyGroupName());
				paramList.add(regfri4000Duty.getRegDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(regfri4000Duty.getUpdatedBy());
				paramList.add(regfri4000Duty.getUpdatedDate());
				// Insert Statement
				paramList.add(regfri4000Duty.getNewRegId());
				paramList.add(regfri4000Duty.getDutyGroupId());
				paramList.add(regfri4000Duty.getDutyGroupName());
				paramList.add(regfri4000Duty.getRegDate());
				paramList.add(regfri4000Duty.getCreatedBy());
				paramList.add(regfri4000Duty.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
