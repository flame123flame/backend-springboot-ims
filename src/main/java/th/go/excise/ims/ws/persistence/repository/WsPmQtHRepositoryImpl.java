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
import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ia.vo.WsPmQtHVo;
import th.go.excise.ims.ws.persistence.entity.WsPmQtH;

public class WsPmQtHRepositoryImpl implements WsPmQtHRepositoryCustom  {
	
	private static final Logger logger = LoggerFactory.getLogger(WsPmQtHRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsPmQtH> pmQtHList) {
		logger.info("batchInsert pmQtHList.size()={}", pmQtHList.size());	
		final int BATCH_SIZE = 1000;	
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
				"OFF_NAME = ?",
				"FORM_YEAR = ?",
				"FORM_NAME = ?",
				"FORM_ROUND = ?",
				"FORM_STATUS = ?",
				"FORM_STATUS_DESC = ?",
				"SUMMARY = ?",
				"PROCESS_BY = ?",
				"PROCESS_POSITION = ?",
				"PROCESS_DATE = ?",
				"IS_DELETED = ?",
				"UPDATED_BY = ?",
				"UPDATED_DATE = ?"
			));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PM_QT_H_SEQ",
			"OFF_CODE",
			"OFF_NAME",
			"FORM_YEAR",
			"FORM_CODE",
			"FORM_NAME",
			"FORM_ROUND",
			"FORM_STATUS",
			"FORM_STATUS_DESC",
			"SUMMARY",
			"PROCESS_BY",
			"PROCESS_POSITION",
			"PROCESS_DATE",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_QT_H QTH ");
		sql.append(" USING DUAL ");
		sql.append(" ON (QTH.OFF_CODE = ? AND QTH.FORM_CODE = ? ) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_QT_H_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmQtHList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmQtH>() {
			public void setValues(PreparedStatement ps, WsPmQtH pmQtH) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(pmQtH.getOffCode());
				paramList.add(pmQtH.getFormCode());
				// Update Statement

				paramList.add(pmQtH.getOffName());
				paramList.add(pmQtH.getFormYear());
				paramList.add(pmQtH.getFormName());
				paramList.add(pmQtH.getFormRound());
				paramList.add(pmQtH.getFormStatus());
				paramList.add(pmQtH.getFormStatusDesc());
				paramList.add(pmQtH.getSummary());
				paramList.add(pmQtH.getProcessBy());
				paramList.add(pmQtH.getProcessPosition());
				paramList.add(pmQtH.getProcessDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmQtH.getOffCode());
				paramList.add(pmQtH.getOffName());
				paramList.add(pmQtH.getFormYear());
				paramList.add(pmQtH.getFormCode());
				paramList.add(pmQtH.getFormName());
				paramList.add(pmQtH.getFormRound());
				paramList.add(pmQtH.getFormStatus());
				paramList.add(pmQtH.getFormStatusDesc());
				paramList.add(pmQtH.getSummary());
				paramList.add(pmQtH.getProcessBy());
				paramList.add(pmQtH.getProcessPosition());
				paramList.add(pmQtH.getProcessDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsPmQtHVo> filterWsPmQt(Int1304FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM WS_PM_QT_H ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND FORM_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFF_CODE = ? ");
			params.add(request.getOfficeCode());
		}

		sql.append(" ORDER BY PM_QT_H_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<WsPmQtHVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(WsPmQtHVo.class));

		return response; 
	}

}
