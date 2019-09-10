package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ia.vo.WsPmAssessDVo;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessD;

public class WsPmAssessDRepositoryImpl implements WsPmAssessDRepositoryCustom{
	private static final Logger logger = LoggerFactory.getLogger(WsPmAssessDRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<WsPmAssessD> pmAssessD) {
		logger.info("batchMerge pmAssessD.size()={}", pmAssessD.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"PAD.TOPIC_LEVEL = ?",
			"PAD.TOPIC_NAME = ?",
			"PAD.TOPIC_ANSWER = ?",
			"PAD.IS_DELETED = ?",
			"PAD.UPDATED_BY = ?",
			"PAD.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PAD.PM_ASSESS_D_SEQ",
			"PAD.OFF_CODE",
			"PAD.FORM_CODE",
			"PAD.TOPIC_LEVEL",
			"PAD.TOPIC_CODE",
			"PAD.TOPIC_NAME",
			"PAD.TOPIC_ANSWER",
			"PAD.CREATED_BY",
			"PAD.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_ASSESS_D PAD ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PAD.OFF_CODE  = ? AND PAD.FORM_CODE = ? AND PAD.TOPIC_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_ASSESS_D_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmAssessD, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmAssessD>() {
			public void setValues(PreparedStatement ps, WsPmAssessD pmAssessD) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(pmAssessD.getOffCode());
				paramList.add(pmAssessD.getFormCode());
				paramList.add(pmAssessD.getTopicCode());
				// Update Statement
				paramList.add(pmAssessD.getTopicLevel());
				paramList.add(pmAssessD.getTopicName());
				paramList.add(pmAssessD.getTopicAnswer());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmAssessD.getOffCode());
				paramList.add(pmAssessD.getFormCode());
				paramList.add(pmAssessD.getTopicLevel());
				paramList.add(pmAssessD.getTopicCode());
				paramList.add(pmAssessD.getTopicName());
				paramList.add(pmAssessD.getTopicAnswer());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsPmAssessDVo> filterWsPaAssessD(String offCode, String formCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM WS_PM_ASSESS_D ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(offCode)) {
			sql.append(" AND OFF_CODE = ? ");
			params.add(offCode.trim());
		}
		
		if(StringUtils.isNotBlank(formCode)) {
			sql.append(" AND FORM_CODE = ? ");
			params.add(formCode.trim());
		}

		sql.append(" ORDER BY PM_ASSESS_D_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<WsPmAssessDVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(WsPmAssessDVo.class));

		return response; 
	}
}
