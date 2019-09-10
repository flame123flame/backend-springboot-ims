package th.go.excise.ims.ws.persistence.repository;

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
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;

public class WsPmPy1HRepositoryImpl implements WsPmPy1HRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsPmPy1DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<WsPmPy1H> pmPy1D) {
		logger.info("batchMerge wsPmPy1D.size()={}", pmPy1D.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"PY1H.OFF_NAME = ?",
			"PY1H.FORM_YEAR = ?",
			"PY1H.FORM_NAME = ?",
			"PY1H.FORM_ROUND = ?",
			"PY1H.FORM_STATUS = ?",
			"PY1H.FORM_STATUS_DESC = ?",
			"PY1H.SUMMARY = ?",
			"PY1H.PROCESS_BY = ?",
			"PY1H.PROCESS_POSITION = ?",
			"PY1H.PROCESS_DATE = ?",
			"PY1H.IS_DELETED = ?",
			"PY1H.UPDATED_BY = ?",
			"PY1H.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PY1H.PM_PY1_H_SEQ",
			"PY1H.OFF_CODE",
			"PY1H.OFF_NAME",
			"PY1H.FORM_YEAR",
			"PY1H.FORM_CODE",
			"PY1H.FORM_NAME",
			"PY1H.FORM_ROUND",
			"PY1H.FORM_STATUS",
			"PY1H.FORM_STATUS_DESC",
			"PY1H.SUMMARY",
			"PY1H.PROCESS_BY",
			"PY1H.PROCESS_POSITION",
			"PY1H.PROCESS_DATE",
			"PY1H.CREATED_BY",
			"PY1H.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_PY1_H PY1H ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PY1H.OFF_CODE = ? AND PY1H.FORM_CODE = ? ) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_PY1_H_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmPy1D, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmPy1H>() {
			public void setValues(PreparedStatement ps, WsPmPy1H wsPmPy1D) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(wsPmPy1D.getOffCode());
				paramList.add(wsPmPy1D.getFormCode());
				// Update Statement
				paramList.add(wsPmPy1D.getOffName());
				paramList.add(wsPmPy1D.getFormYear());
				paramList.add(wsPmPy1D.getFormName());
				paramList.add(wsPmPy1D.getFormRound());
				paramList.add(wsPmPy1D.getFormStatus());
				paramList.add(wsPmPy1D.getFormStatusDesc());
				paramList.add(wsPmPy1D.getSummary());
				paramList.add(wsPmPy1D.getProcessBy());
				paramList.add(wsPmPy1D.getProcessPosition());
				paramList.add(wsPmPy1D.getProcessDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(wsPmPy1D.getOffCode());
				paramList.add(wsPmPy1D.getOffName());
				paramList.add(wsPmPy1D.getFormYear());
				paramList.add(wsPmPy1D.getFormCode());
				paramList.add(wsPmPy1D.getFormName());
				paramList.add(wsPmPy1D.getFormRound());
				paramList.add(wsPmPy1D.getFormStatus());
				paramList.add(wsPmPy1D.getFormStatusDesc());
				paramList.add(wsPmPy1D.getSummary());
				paramList.add(wsPmPy1D.getProcessBy());
				paramList.add(wsPmPy1D.getProcessPosition());
				paramList.add(wsPmPy1D.getProcessDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
