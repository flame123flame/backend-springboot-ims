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
import th.go.excise.ims.ws.persistence.entity.WsPmPy2D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2DVo;

public class WsPmPy2DRepositoryImpl implements WsPmPy2DRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsPmPy2DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<WsPmPy2D> pmPy2DList) {
		logger.info("batchMerge pmPy2DList.size()={}", pmPy2DList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
//			"PY2.OFF_CODE = ? ",
//			"PY2.FORM_CODE = ? ",
//			"PY2.DIV_SEQ = ? ",
			"PY2.DIV_NAME = ? ",
//			"PY2.JOB_NAME = ? ",
			"PY2.PROCESS_BY = ? ",
			"PY2.PROCESS_POSITION = ? ",
			"PY2.PROCESS_DATE = ? ",
//			"PY2.PY2_TOPIC_SEQ = ? ",
			"PY2.PY2_TOPIC_NAME = ? ",
			"PY2.PY2_TOPIC1_MAIN = ? ",
			"PY2.PY2_TOPIC2_CTL = ? ",
			"PY2.PY2_TOPIC3_ASSESS = ? ",
			"PY2.PY2_TOPIC4_RISK = ? ",
			"PY2.PY2_TOPIC5_IMPROVE = ? ",
			"PY2.PY2_TOPIC6_OWNER = ? ",
			"PY2.PY2_TOPIC7_REMARK = ? ",
			"PY2.IS_DELETED = ?",
			"PY2.UPDATED_BY = ?",
			"PY2.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PY2.PM_PY2_D_SEQ",
			"PY2.OFF_CODE",
			"PY2.FORM_CODE",
			"PY2.DIV_SEQ",
			"PY2.DIV_NAME",
			"PY2.JOB_NAME",
			"PY2.PROCESS_BY",
			"PY2.PROCESS_POSITION",
			"PY2.PROCESS_DATE",
			"PY2.PY2_TOPIC_SEQ",
			"PY2.PY2_TOPIC_NAME",
			"PY2.PY2_TOPIC1_MAIN",
			"PY2.PY2_TOPIC2_CTL",
			"PY2.PY2_TOPIC3_ASSESS",
			"PY2.PY2_TOPIC4_RISK",
			"PY2.PY2_TOPIC5_IMPROVE",
			"PY2.PY2_TOPIC6_OWNER",
			"PY2.PY2_TOPIC7_REMARK",
			"PY2.CREATED_BY",
			"PY2.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_PY2_D PY2 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PY2.OFF_CODE = ? AND PY2.FORM_CODE = ? AND PY2.DIV_SEQ = ? AND PY2.JOB_NAME = ? AND PY2.PY2_TOPIC_SEQ = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_PY2_D_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmPy2DList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmPy2D>() {
			public void setValues(PreparedStatement ps, WsPmPy2D pmPy2D) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(pmPy2D.getOffCode());
				paramList.add(pmPy2D.getFormCode());
				paramList.add(pmPy2D.getDivSeq());
				paramList.add(pmPy2D.getJobName());
				paramList.add(pmPy2D.getPy2TopicSeq());
				// Update Statement
				paramList.add(pmPy2D.getDivName());
				paramList.add(pmPy2D.getProcessBy());
				paramList.add(pmPy2D.getProcessPosition());
				paramList.add(pmPy2D.getProcessDate());
				paramList.add(pmPy2D.getPy2TopicName());
				paramList.add(pmPy2D.getPy2Topic1Main());
				paramList.add(pmPy2D.getPy2Topic2Ctl());
				paramList.add(pmPy2D.getPy2Topic3Assess());
				paramList.add(pmPy2D.getPy2Topic4Risk());
				paramList.add(pmPy2D.getPy2Topic5Improve());
				paramList.add(pmPy2D.getPy2Topic6Owner());
				paramList.add(pmPy2D.getPy2Topic7Remark());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmPy2D.getOffCode());
				paramList.add(pmPy2D.getFormCode());
				paramList.add(pmPy2D.getDivSeq());
				paramList.add(pmPy2D.getDivName());
				paramList.add(pmPy2D.getJobName());
				paramList.add(pmPy2D.getProcessBy());
				paramList.add(pmPy2D.getProcessPosition());
				paramList.add(pmPy2D.getProcessDate());
				paramList.add(pmPy2D.getPy2TopicSeq());
				paramList.add(pmPy2D.getPy2TopicName());
				paramList.add(pmPy2D.getPy2Topic1Main());
				paramList.add(pmPy2D.getPy2Topic2Ctl());
				paramList.add(pmPy2D.getPy2Topic3Assess());
				paramList.add(pmPy2D.getPy2Topic4Risk());
				paramList.add(pmPy2D.getPy2Topic5Improve());
				paramList.add(pmPy2D.getPy2Topic6Owner());
				paramList.add(pmPy2D.getPy2Topic7Remark());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsPmPy2DVo> filterWsPaPy2D(String formCode, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM WS_PM_PY2_D ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(formCode.trim())) {
			sql.append(" AND FORM_CODE = ? ");
			params.add(formCode);
		}
		
		if(StringUtils.isNotBlank(offCode.trim())) {
			sql.append(" AND OFF_CODE = ? ");
			params.add(offCode);
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<WsPmPy2DVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(WsPmPy2DVo.class));

		return response; 
	}
}
