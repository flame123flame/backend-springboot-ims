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
import th.go.excise.ims.ws.client.pcc.inquirybank.model.Bank;

public class ExciseBankRepositoryImpl implements ExciseBankRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseBankRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<Bank> bankList) {
		logger.info("batchMerge bankList.size()={}", bankList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EBK.BANK_NAME = ?",
			"EBK.SHORT_NAME = ?",
			"EBK.IS_DELETED = ?",
			"EBK.UPDATED_BY = ?",
			"EBK.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EBK.BANK_ID",
			"EBK.BANK_CODE",
			"EBK.BANK_NAME",
			"EBK.SHORT_NAME",
			"EBK.BEGIN_DATE",
			"EBK.CREATED_BY",
			"EBK.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_BANK EBK ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EBK.BANK_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_BANK_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), bankList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<Bank>() {
			public void setValues(PreparedStatement ps, Bank bank) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(bank.getBankCode());
				// Update Statement
				paramList.add(bank.getBankName());
				paramList.add(bank.getShortName());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(bank.getBankCode());
				paramList.add(bank.getBankName());
				paramList.add(bank.getShortName());
				paramList.add(LocalDate.parse(bank.getBeginDate(), DateTimeFormatter.BASIC_ISO_DATE));
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
