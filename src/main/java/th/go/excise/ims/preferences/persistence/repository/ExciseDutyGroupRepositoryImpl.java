package th.go.excise.ims.preferences.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup;

public class ExciseDutyGroupRepositoryImpl implements ExciseDutyGroupRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseDutyGroupRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<ExciseDutyGroup> dutyGroupList) {
		logger.info("batchMerge dutyGroupList.size()={}", dutyGroupList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EDG.DUTY_GROUP_NAME = ?",
			"EDG.DUTY_GROUP_STATUS = ?",
			"EDG.SUP_DUTY_GROUP_CODE = ?",
			"EDG.DUTY_GROUP_TYPE = ?",
			"EDG.REG_STATUS = ?",
			"EDG.IS_DELETED = ?",
			"EDG.UPDATED_BY = ?",
			"EDG.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EDG.DUTY_GROUP_ID",
			"EDG.DUTY_GROUP_CODE",
			"EDG.DUTY_GROUP_NAME",
			"EDG.DUTY_GROUP_STATUS",
			"EDG.SUP_DUTY_GROUP_CODE",
			"EDG.DUTY_GROUP_TYPE",
			"EDG.REG_STATUS",
			"EDG.BEGIN_DATE",
			"EDG.CREATED_BY",
			"EDG.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_DUTY_GROUP EDG ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EDG.DUTY_GROUP_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_DUTY_GROUP_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), dutyGroupList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<ExciseDutyGroup>() {
			public void setValues(PreparedStatement ps, ExciseDutyGroup dutyGroup) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(dutyGroup.getDutyGroupCode());
				// Update Statement
				paramList.add(dutyGroup.getDutyGroupName());
				paramList.add(dutyGroup.getDutyGroupStatus());
				paramList.add(dutyGroup.getSupDutyGroupCode());
				paramList.add(dutyGroup.getDutyGroupType());
				paramList.add(dutyGroup.getRegStatus());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(dutyGroup.getDutyGroupCode());
				paramList.add(dutyGroup.getDutyGroupName());
				paramList.add(dutyGroup.getDutyGroupStatus());
				paramList.add(dutyGroup.getSupDutyGroupCode());
				paramList.add(dutyGroup.getDutyGroupType());
				paramList.add(dutyGroup.getRegStatus());
				paramList.add(dutyGroup.getBeginDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
