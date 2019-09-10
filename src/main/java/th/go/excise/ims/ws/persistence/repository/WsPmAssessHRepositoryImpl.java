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
import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;

public class WsPmAssessHRepositoryImpl implements WsPmAssessHRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(WsPmAssessDRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<WsPmAssessH> pmAssessH) {
		logger.info("batchMerge pmAssessH.size()={}", pmAssessH.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"PAH.OFF_NAME = ?",
			"PAH.FORM_YEAR = ?",
			"PAH.FORM_NAME = ?",
			"PAH.FORM_ROUND = ?",
			"PAH.FORM_STATUS = ?",
			"PAH.FORM_STATUS_DESC = ?",
			"PAH.SUMMARY = ?",
			"PAH.PROCESS_BY = ?",
			"PAH.PROCESS_POSITION = ?",
			"PAH.PROCESS_DATE = ?",
			"PAH.IS_DELETED = ?",
			"PAH.UPDATED_BY = ?",
			"PAH.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PAH.PM_ASSESS_H_SEQ",
			"PAH.OFF_CODE",
			"PAH.OFF_NAME",
			"PAH.FORM_YEAR",
			"PAH.FORM_CODE",
			"PAH.FORM_NAME",
			"PAH.FORM_ROUND",
			"PAH.FORM_STATUS",
			"PAH.FORM_STATUS_DESC",
			"PAH.SUMMARY",
			"PAH.PROCESS_BY",
			"PAH.PROCESS_POSITION",
			"PAH.PROCESS_DATE",
			"PAH.CREATED_BY",
			"PAH.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_ASSESS_H PAH ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PAH.OFF_CODE = ? AND PAH.FORM_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_ASSESS_H_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmAssessH, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmAssessH>() {
			public void setValues(PreparedStatement ps, WsPmAssessH pmAssessH) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(pmAssessH.getOffCode());
				paramList.add(pmAssessH.getFormCode());
				// Update Statement=
				paramList.add(pmAssessH.getOffName());
				paramList.add(pmAssessH.getFormYear());
				paramList.add(pmAssessH.getFormName());
				paramList.add(pmAssessH.getFormRound());
				paramList.add(pmAssessH.getFormStatus());
				paramList.add(pmAssessH.getFormStatusDesc());
				paramList.add(pmAssessH.getSummary());
				paramList.add(pmAssessH.getProcessBy());
				paramList.add(pmAssessH.getProcessPosition());
				paramList.add(pmAssessH.getProcessDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmAssessH.getOffCode());
				paramList.add(pmAssessH.getOffName());
				paramList.add(pmAssessH.getFormYear());
				paramList.add(pmAssessH.getFormCode());
				paramList.add(pmAssessH.getFormName());
				paramList.add(pmAssessH.getFormRound());
				paramList.add(pmAssessH.getFormStatus());
				paramList.add(pmAssessH.getFormStatusDesc());
				paramList.add(pmAssessH.getSummary());
				paramList.add(pmAssessH.getProcessBy());
				paramList.add(pmAssessH.getProcessPosition());
				paramList.add(pmAssessH.getProcessDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsPmAssessHVo> filterWsPaAssess(Int1301Filter request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM WS_PM_ASSESS_H ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND FORM_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFF_CODE = ? ");
			params.add(request.getOfficeCode());
		}

		sql.append(" ORDER BY PM_ASSESS_H_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<WsPmAssessHVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(WsPmAssessHVo.class));

		return response; 
	}

}
