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
import th.go.excise.ims.ws.client.pcc.inquirytitle.model.Title;

public class ExciseTitleRepositoryImpl implements ExciseTitleRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(ExciseTitleRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<Title> titleList) {
		logger.info("batchMerge titleList.size()={}", titleList.size());

		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"ET.TITLE_NAME = ?",
			"ET.TITLE_SEQ = ?",
			"ET.TITLE_TYPE = ?",
			"ET.SUFFIX_NAME = ?",
			"ET.SHORT_TITLE = ?",
			"ET.SHORT_SUFFIX = ?",
			"ET.IS_DELETED = ?",
			"ET.UPDATED_BY = ?",
			"ET.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"ET.TITLE_ID", 
			"ET.TITLE_CODE", 
			"ET.TITLE_NAME", 
			"ET.TITLE_SEQ",
			"ET.TITLE_TYPE",
			"ET.SUFFIX_NAME",
			"ET.SHORT_TITLE",
			"ET.SHORT_SUFFIX",
			"ET.BEGIN_DATE",
			"ET.CREATED_BY",
			"ET.CREATED_DATE"
		));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_TITLE ET ");
		sql.append(" USING DUAL ");
		sql.append(" ON (ET.TITLE_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_TITLE_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), titleList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<Title>() {
			public void setValues(PreparedStatement ps, Title title) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(title.getTitleCode());
				// Update Statement
				paramList.add(title.getTitleName());
				paramList.add(title.getTitleSeq());
				paramList.add(title.getTitleType());
				paramList.add(title.getSuffixName());
				paramList.add(title.getShortTitle());
				paramList.add(title.getShortSuffix());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(title.getTitleCode());
				paramList.add(title.getTitleName());
				paramList.add(title.getTitleSeq());
				paramList.add(title.getTitleType());
				paramList.add(title.getSuffixName());
				paramList.add(title.getShortTitle());
				paramList.add(title.getShortSuffix());
				paramList.add(LocalDate.parse(title.getBeginDate(), DateTimeFormatter.BASIC_ISO_DATE));
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
}
