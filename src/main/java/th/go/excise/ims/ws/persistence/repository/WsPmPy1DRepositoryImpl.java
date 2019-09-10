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
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;

public class WsPmPy1DRepositoryImpl implements WsPmPy1DRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsPmPy1DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsPmPy1D> pmPy1D) {
		logger.info("batchMerge wsPmPy1D.size()={}", pmPy1D.size());

		final int BATCH_SIZE = 1000;

		List<String> updateColumnNames = new ArrayList<>(Arrays.asList("PY1D.TOPIC_NAME = ?", "PY1D.TOPIC_DESC = ?",
				"PY1D.TOPIC_ANSWER = ?", "PY1D.IS_DELETED = ?", "PY1D.UPDATED_BY = ?", "PY1D.UPDATED_DATE = ?"));

		List<String> insertColumnNames = new ArrayList<>(
				Arrays.asList("PY1D.PM_PY1_D_SEQ", "PY1D.OFF_CODE", "PY1D.FORM_CODE", "PY1D.TOPIC_CODE",
						"PY1D.TOPIC_NAME", "PY1D.TOPIC_DESC", "PY1D.TOPIC_ANSWER", "PY1D.CREATED_BY"
//			"PY1D.CREATED_DATE"
				));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_PY1_D PY1D ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PY1D.OFF_CODE = ? AND PY1D.FORM_CODE = ? AND PY1D. TOPIC_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT ("
				+ org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append(
				"   VALUES (WS_PM_PY1_D_SEQ.NEXTVAL" + StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), pmPy1D, BATCH_SIZE,
				new ParameterizedPreparedStatementSetter<WsPmPy1D>() {
					public void setValues(PreparedStatement ps, WsPmPy1D wsPmPy1D) throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						// Using Condition
						paramList.add(wsPmPy1D.getOffCode());
						paramList.add(wsPmPy1D.getFormCode());
						paramList.add(wsPmPy1D.getTopicCode());
						// Update Statement
						paramList.add(wsPmPy1D.getTopicName());

						paramList.add(wsPmPy1D.getTopicDesc());
						paramList.add(wsPmPy1D.getTopicAnswer());
						paramList.add(FLAG.N_FLAG);
						paramList.add(SYSTEM_USER.BATCH);
						paramList.add(LocalDateTime.now());
//				// Insert Statement
						paramList.add(wsPmPy1D.getOffCode());
						paramList.add(wsPmPy1D.getFormCode());
						paramList.add(wsPmPy1D.getTopicCode());
						paramList.add(wsPmPy1D.getTopicName());
						paramList.add(wsPmPy1D.getTopicDesc());
						paramList.add(wsPmPy1D.getTopicAnswer());
						paramList.add(SYSTEM_USER.BATCH);
//				paramList.add(LocalDateTime.now());
						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});
	}

}
