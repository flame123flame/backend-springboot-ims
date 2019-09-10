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
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000Credit;

public class WsIncfri8000CreditRepositoryImpl implements WsIncfri8000CreditRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000CreditRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd) {
		logger.info("forceDeleteByDateType dateType={}, dateStart={}, dateEnd={}", dateType, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE WS_INCFRI8000_CREDIT ");
		sql.append(" WHERE DATE_TYPE = ? ");
		sql.append("   AND ( ");
		sql.append("     TRUNC(REF_DATE) >= TO_DATE(?,'YYYYMMDD') ");
		sql.append("     AND TRUNC(REF_DATE) <= TO_DATE(?,'YYYYMMDD') ");
		sql.append("   ) ");
		
		int rowsAffected = commonJdbcTemplate.update(sql.toString(), new Object[] {
			dateType,
			dateStart,
			dateEnd
		});
		
		logger.debug("rowsAffected={}", rowsAffected);
	}
	
	@Override
	public void batchInsert(List<WsIncfri8000Credit> incfri8000CreditList) {
		logger.info("batchInsert incfri8000CreditList.size()={}", incfri8000CreditList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"INCFRI8000_ID",
			"DATE_TYPE",
			"REG_ID",
			"NEW_REG_ID",
			"APPROVE_NO",
			"APPROVE_DATE",
			"REF_DATE",
			"IS_DELETED",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8000_CREDIT", insertColumnNames, "WS_INCFRI8000_CREDIT_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, incfri8000CreditList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000Credit>() {
			public void setValues(PreparedStatement ps, WsIncfri8000Credit incfri8000Credit) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(incfri8000Credit.getDateType());
				paramList.add(incfri8000Credit.getRegId());
				paramList.add(incfri8000Credit.getNewRegId());
				paramList.add(incfri8000Credit.getApproveNo());
				paramList.add(incfri8000Credit.getApproveDate());
				paramList.add(incfri8000Credit.getRefDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
