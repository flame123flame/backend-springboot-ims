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
import th.go.excise.ims.ia.vo.WsPmQtDVo;
import th.go.excise.ims.ws.persistence.entity.WsPmQtD;

public class WsPmQtDRepositoryImpl implements WsPmQtDRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsPmQtDRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsPmQtD> pmQtDList) {
		logger.info("batchInsert pmQtDList.size()={}", pmQtDList.size());	
		final int BATCH_SIZE = 1000;	
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
				"TOPIC_LEVEL = ?",
				"TOPIC_NAME = ?",
				"TOPIC_ANSWER = ?",
				"TOPIC_RESULT = ?",
				"IS_DELETED = ?",
				"UPDATED_BY = ?",
				"UPDATED_DATE = ?"
			));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
				"PM_QT_D_SEQ",
				"OFF_CODE",
				"FORM_CODE",
				"TOPIC_LEVEL",
				"TOPIC_CODE",
				"TOPIC_NAME",
				"TOPIC_ANSWER",
				"TOPIC_RESULT",
				"CREATED_BY",
				"CREATED_DATE"
		));
			
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_QT_D QTD ");
		sql.append(" USING DUAL ");
		sql.append(" ON (QTD.OFF_CODE = ? AND QTD.FORM_CODE = ? AND QTD.TOPIC_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_QT_D_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmQtDList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmQtD>() {
			public void setValues(PreparedStatement ps, WsPmQtD pmQtD) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(pmQtD.getOffCode());
				paramList.add(pmQtD.getFormCode());
				paramList.add(pmQtD.getTopicCode());
				// Update Statement
				paramList.add(pmQtD.getTopicLevel());
				paramList.add(pmQtD.getTopicName());
				paramList.add(pmQtD.getTopicAnswer());
				paramList.add(pmQtD.getTopicResult());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmQtD.getOffCode());
				paramList.add(pmQtD.getFormCode());
				paramList.add(pmQtD.getTopicLevel());
				paramList.add(pmQtD.getTopicCode());
				paramList.add(pmQtD.getTopicName());
				paramList.add(pmQtD.getTopicAnswer());
				paramList.add(pmQtD.getTopicResult());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsPmQtDVo> filterWsPmQtD(String offCode ,String formCode ) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM WS_PM_QT_D ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(offCode)) {
			sql.append(" AND OFF_CODE = ? ");
			params.add(offCode.trim());
		}
		
		if(StringUtils.isNotBlank(formCode)) {
			sql.append(" AND FORM_CODE = ? ");
			params.add(formCode.trim());
		}
		
		sql.append(" ORDER BY PM_QT_D_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<WsPmQtDVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(WsPmQtDVo.class));
		return response; 
	}

}
