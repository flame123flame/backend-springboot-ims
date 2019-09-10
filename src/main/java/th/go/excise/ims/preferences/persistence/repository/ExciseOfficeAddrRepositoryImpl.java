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
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.model.OffCodeAddress;

public class ExciseOfficeAddrRepositoryImpl implements ExciseOfficeAddrRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(ExciseOfficeAddrRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<OffCodeAddress> offCodeAddressList) {
		logger.info("batchMerge offCodeAddressList.size()={}", offCodeAddressList.size());

		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EOA.ADDRESS = ?",
			"EOA.FAX = ?",
			"EOA.IS_DELETED = ?",
			"EOA.UPDATED_BY = ?",
			"EOA.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EOA.OFFICE_ADDR_ID",
			"EOA.OFF_CODE",
			"EOA.ADDRESS",
			"EOA.FAX",
			"EOA.CREATED_BY",
			"EOA.CREATED_DATE"
		));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_OFFICE_ADDR EOA ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EOA.OFF_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_OFFICE_ADDR_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), offCodeAddressList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<OffCodeAddress>() {
			public void setValues(PreparedStatement ps, OffCodeAddress offCodeAddress) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(offCodeAddress.getOffcode());
				// Update Statement
				paramList.add(offCodeAddress.getAddress());
				paramList.add(offCodeAddress.getFax());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(offCodeAddress.getOffcode());
				paramList.add(offCodeAddress.getAddress());
				paramList.add(offCodeAddress.getFax());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
