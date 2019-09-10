package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

public class WsOasfri0100DRepositoryImpl implements WsOasfri0100DRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsOasfri0100DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(List<WsOasfri0100D> oasfri0100DList) {
		logger.info("batchInsert oasfri0100DList.size()={}", oasfri0100DList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"OASFRI0100_D_SEQ",
			"DATA_TYPE",
			"FORMDOC_REC0142_NO",
			"DATA_SEQ",
			"DATA_ID",
			"DATA_NAME",
			"BAL_BF_QTY",
			"SEQ_NO",
			"ACCOUNT_NAME",
			"IN_QTY",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_OASFRI0100_D", insertColumnNames, "WS_OASFRI0100_D_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), oasfri0100DList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsOasfri0100D>() {
			public void setValues(PreparedStatement ps, WsOasfri0100D oasfri0100D) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(oasfri0100D.getDataType());
				paramList.add(oasfri0100D.getFormdocRec0142No());
				paramList.add(oasfri0100D.getDataSeq());
				paramList.add(oasfri0100D.getDataId());
				paramList.add(oasfri0100D.getDataName());
				paramList.add(oasfri0100D.getBalBfQty());
				paramList.add(oasfri0100D.getSeqNo());
				paramList.add(oasfri0100D.getAccountName());
				paramList.add(oasfri0100D.getInQty());
				paramList.add(oasfri0100D.getCreatedBy());
				paramList.add(oasfri0100D.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public List<WsOasfri0100Vo> findByCriteria(WsOasfri0100FromVo formVo) {
		logger.info("findByCriteria newRegId={}, dutyGroupId={}, dataType={}, yearMonthStart={}, yearMonthEnd={}",
				formVo.getNewRegId(), formVo.getDutyGroupId(), formVo.getDataType(), formVo.getYearMonthStart(), formVo.getYearMonthEnd());
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT H.NEW_REG_ID ");
		sql.append("   ,H.YEAR_MONTH ");
		sql.append("   ,H.FORMDOC_REC0142_NO ");
		sql.append("   ,H.FORMDOC_REC0142_DATE ");
		sql.append("   ,D.DATA_TYPE ");
		sql.append("   ,D.DATA_SEQ ");
		sql.append("   ,D.DATA_ID ");
		sql.append("   ,D.DATA_NAME ");
		sql.append("   ,D.BAL_BF_QTY ");
		sql.append("   ,D.SEQ_NO ");
		sql.append("   ,D.ACCOUNT_NAME ");
		sql.append("   ,D.IN_QTY ");
		sql.append(" FROM ( ");
		sql.append("   SELECT NEW_REG_ID ");
		sql.append("     ,TAX_YEAR||LPAD(TAX_MONTH ,2 ,'0') AS YEAR_MONTH ");
		sql.append("     ,FORMDOC_REC0142_NO ");
		sql.append("     ,FORMDOC_REC0142_DATE ");
		sql.append("   FROM WS_OASFRI0100_H ");
		sql.append("   WHERE NEW_REG_ID = ? ");
		sql.append("     AND IS_DELETED = 'N' ");
		sql.append(" ) H ");
		sql.append(" INNER JOIN WS_OASFRI0100_D D ON D.FORMDOC_REC0142_NO = H.FORMDOC_REC0142_NO ");
		sql.append("   AND D.IS_DELETED = 'N' ");
		sql.append(" WHERE DATA_TYPE = ? ");

		paramList.add(formVo.getNewRegId());
		paramList.add(formVo.getDataType());
		
		if(StringUtils.isNotEmpty(formVo.getDutyGroupId())) {
			sql.append("   AND D.DATA_ID LIKE ? ");
			paramList.add(formVo.getDutyGroupId() + "%");
		}

		sql.append("   AND H.YEAR_MONTH >= ? ");
		sql.append("   AND H.YEAR_MONTH <= ? ");
		paramList.add(formVo.getYearMonthStart());
		paramList.add(formVo.getYearMonthEnd());
		
		if (StringUtils.isNotEmpty(formVo.getDataName())) {
			sql.append("   AND D.DATA_NAME LIKE ? ");
			paramList.add("%" + formVo.getDataName() + "%");
		}
		
		if (StringUtils.isNotEmpty(formVo.getAccountName())) {
			sql.append("   AND D.ACCOUNT_NAME LIKE ? ");
			paramList.add("%" + formVo.getAccountName() + "%");
		}
		
		sql.append(" ORDER BY H.YEAR_MONTH, H.FORMDOC_REC0142_NO, D.DATA_SEQ, D.SEQ_NO ");
		
		List<WsOasfri0100Vo> voList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new RowMapper<WsOasfri0100Vo>() {
			@Override
			public WsOasfri0100Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WsOasfri0100Vo vo = new WsOasfri0100Vo();
				vo.setNewRegId(rs.getString("NEW_REG_ID"));
				vo.setYearMonth(rs.getString("YEAR_MONTH"));
				vo.setFormdocRec0142No(rs.getString("FORMDOC_REC0142_NO"));
				vo.setFormdocRec0142Date(LocalDateConverter.convertToEntityAttribute(rs.getDate("FORMDOC_REC0142_DATE")));
				vo.setDataType(rs.getString("DATA_TYPE"));
				vo.setDataSeq(rs.getInt("DATA_SEQ"));
				vo.setDataId(rs.getString("DATA_ID"));
				vo.setDataName(rs.getString("DATA_NAME"));
				vo.setBalBfQty(rs.getBigDecimal("BAL_BF_QTY"));
				vo.setSeqNo(rs.getInt("SEQ_NO"));
				vo.setAccountName(rs.getString("ACCOUNT_NAME"));
				vo.setInQty(rs.getBigDecimal("IN_QTY"));
				return vo;
			}
		});
		
		return voList;
	}
	
}
