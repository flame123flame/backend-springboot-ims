package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;
import th.go.excise.ims.ws.vo.WsIncfri8000MVo;

public class WsIncfri8000RepositoryImpl implements WsIncfri8000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000RepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd) {
		logger.info("forceDeleteByDateType dateType={}, dateStart={}, dateEnd={}", dateType, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE WS_INCFRI8000 ");
		sql.append(" WHERE DATE_TYPE = ? ");
		sql.append("   AND ( ");
		sql.append("     TRUNC(" + getColumnTypeByDateType(dateType) + ") >= TO_DATE(?,'YYYYMMDD') ");
		sql.append("     AND TRUNC(" + getColumnTypeByDateType(dateType) + ") <= TO_DATE(?,'YYYYMMDD') ");
		sql.append("   ) ");
		
		int rowsAffected = commonJdbcTemplate.update(sql.toString(), new Object[] {
			dateType,
			dateStart,
			dateEnd
		});
		
		logger.debug("rowsAffected={}", rowsAffected);
	}
	
	@Override
	public void batchInsert(List<WsIncfri8000> incfri8000List) {
		logger.info("batchInsert incfri8000List.size()={}", incfri8000List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"INCFRI8000_ID",
			"DATE_TYPE",
			"REG_ID",
			"NEW_REG_ID",
			"RECEIPT_NO",
			"RECEIPT_DATE",
			"TAX_AMOUNT",
			"PEN_AMOUNT",
			"ADD_AMOUNT",
			"REDUCE_AMOUNT",
			"CREDIT_AMOUNT",
			"OFFICE_RECEIVE_CODE",
			"TRN_DATE",
			"DEPOSIT_DATE",
			"SEND_DATE",
			"INCOME_CODE",
			"INCOME_TYPE",
			"DUTY_GROUP_ID",
			"IS_DELETED",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8000", insertColumnNames, "WS_INCFRI8000_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, incfri8000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000>() {
			public void setValues(PreparedStatement ps, WsIncfri8000 incfri8000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Insert Statement
				paramList.add(incfri8000.getDateType());
				paramList.add(incfri8000.getRegId());
				paramList.add(incfri8000.getNewRegId());
				paramList.add(incfri8000.getReceiptNo());
				paramList.add(incfri8000.getReceiptDate());
				paramList.add(incfri8000.getTaxAmount());
				paramList.add(incfri8000.getPenAmount());
				paramList.add(incfri8000.getAddAmount());
				paramList.add(incfri8000.getReduceAmount());
				paramList.add(incfri8000.getCreditAmount());
				paramList.add(incfri8000.getOfficeReceiveCode());
				paramList.add(incfri8000.getTrnDate());
				paramList.add(incfri8000.getDepositDate());
				paramList.add(incfri8000.getSendDate());
				paramList.add(incfri8000.getIncomeCode());
				paramList.add(incfri8000.getIncomeType());
				paramList.add(incfri8000.getDutyGroupId());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public List<WsIncfri8000MVo> findFor8000M(String dateType, String dateStart, String dateEnd) {
		logger.info("findFor8000M dateType={}, dateStart={}, dateEnd={}", dateType, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT REG_ID, NEW_REG_ID, DUTY_GROUP_ID, SUM(TAX_AMOUNT) AS SUM_TAX_AMOUNT ");
		sql.append(" FROM WS_INCFRI8000 ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append("   AND DATE_TYPE = ? ");
		sql.append("   AND ( ");
		sql.append("     TRUNC(" + getColumnTypeByDateType(dateType) + ") >= TO_DATE(?,'YYYYMMDD') ");
		sql.append("     AND TRUNC(" + getColumnTypeByDateType(dateType) + ") <= TO_DATE(?,'YYYYMMDD') ");
		sql.append("   ) ");
		//sql.append("   AND ( ");
		//sql.append("     REG_ID IS NOT NULL ");
		//sql.append("     OR NEW_REG_ID IS NOT NULL ");
		//sql.append("   ) ");
		sql.append("   AND NEW_REG_ID IS NOT NULL ");
		sql.append("   AND DUTY_GROUP_ID IS NOT NULL ");
		sql.append(" GROUP BY REG_ID, NEW_REG_ID, DUTY_GROUP_ID ");
		
		List<WsIncfri8000MVo> incfri8000MVoList = commonJdbcTemplate.query(
			sql.toString(),
			new Object[] {
				dateType,
				dateStart,
				dateEnd
			},
			new RowMapper<WsIncfri8000MVo>() {
				@Override
				public WsIncfri8000MVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					WsIncfri8000MVo vo = new WsIncfri8000MVo();
					vo.setRegId(rs.getString("REG_ID"));
					vo.setNewRegId(rs.getString("NEW_REG_ID"));
					vo.setDutyGroupId(rs.getString("DUTY_GROUP_ID"));
					vo.setSumTaxAmount(rs.getBigDecimal("SUM_TAX_AMOUNT"));
					return vo;
				}
			}
		);
		
		return incfri8000MVoList;
	}
	
	private String getColumnTypeByDateType(String dateType) {
		if (WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME_CODE.equals(dateType)) {
			return "TRN_DATE";
		} else {
			return "RECEIPT_DATE";
		}
	}
}
