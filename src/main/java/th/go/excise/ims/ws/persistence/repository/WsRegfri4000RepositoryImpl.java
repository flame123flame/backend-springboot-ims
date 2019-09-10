package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;
import th.go.excise.ims.ws.persistence.rowmapper.WsRegfri4000VoRowMapper;
import th.go.excise.ims.ws.vo.WsRegfri4000Vo;

public class WsRegfri4000RepositoryImpl implements WsRegfri4000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsRegfri4000> regfri4000List) {
		logger.info("batchMerge regfri4000List.size()={}", regfri4000List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000.REG_ID = ?",
			"WREG4000.REG_STATUS = ?",
			"WREG4000.REG_STATUS_DESC = ?",
			"WREG4000.REG_STATUS_DATE = ?",
			"WREG4000.CUS_ID = ?",
			"WREG4000.CUS_FULLNAME = ?",
			"WREG4000.CUS_ADDRESS = ?",
			"WREG4000.CUS_TELNO = ?",
			"WREG4000.CUS_EMAIL = ?",
			"WREG4000.CUS_URL = ?",
			"WREG4000.FAC_ID = ?",
			"WREG4000.FAC_FULLNAME = ?",
			"WREG4000.FAC_ADDRESS = ?",
			"WREG4000.FAC_TELNO = ?",
			"WREG4000.FAC_EMAIL = ?",
			"WREG4000.FAC_URL = ?",
			"WREG4000.FAC_TYPE = ?",
			"WREG4000.REG_CAPITAL = ?",
			"WREG4000.REG_DATE = ?",
			"WREG4000.OFFICE_CODE = ?",
			"WREG4000.ACTIVE_FLAG = ?",
			"WREG4000.SYNC_DATE = ?",
			"WREG4000.IS_DELETED = ?",
			"WREG4000.UPDATED_BY = ?",
			"WREG4000.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000.REGFRI4000_ID",
			"WREG4000.NEW_REG_ID",
			"WREG4000.REG_ID",
			"WREG4000.REG_STATUS",
			"WREG4000.REG_STATUS_DESC",
			"WREG4000.REG_STATUS_DATE",
			"WREG4000.CUS_ID",
			"WREG4000.CUS_FULLNAME",
			"WREG4000.CUS_ADDRESS",
			"WREG4000.CUS_TELNO",
			"WREG4000.CUS_EMAIL",
			"WREG4000.CUS_URL",
			"WREG4000.FAC_ID",
			"WREG4000.FAC_FULLNAME",
			"WREG4000.FAC_ADDRESS",
			"WREG4000.FAC_TELNO",
			"WREG4000.FAC_EMAIL",
			"WREG4000.FAC_URL",
			"WREG4000.FAC_TYPE",
			"WREG4000.REG_CAPITAL",
			"WREG4000.REG_DATE",
			"WREG4000.OFFICE_CODE",
			"WREG4000.ACTIVE_FLAG",
			"WREG4000.SYNC_DATE",
			"WREG4000.CREATED_BY",
			"WREG4000.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_REGFRI4000 WREG4000 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (WREG4000.NEW_REG_ID = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_REGFRI4000_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), regfri4000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsRegfri4000>() {
			public void setValues(PreparedStatement ps, WsRegfri4000 regfri4000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(regfri4000.getNewRegId());
				// Update Statement
				paramList.add(regfri4000.getRegId());
				paramList.add(regfri4000.getRegStatus());
				paramList.add(regfri4000.getRegStatusDesc());
				paramList.add(regfri4000.getRegStatusDate());
				paramList.add(regfri4000.getCusId());
				paramList.add(regfri4000.getCusFullname());
				paramList.add(regfri4000.getCusAddress());
				paramList.add(regfri4000.getCusTelno());
				paramList.add(regfri4000.getCusEmail());
				paramList.add(regfri4000.getCusUrl());
				paramList.add(regfri4000.getFacId());
				paramList.add(regfri4000.getFacFullname());
				paramList.add(regfri4000.getFacAddress());
				paramList.add(regfri4000.getFacTelno());
				paramList.add(regfri4000.getFacEmail());
				paramList.add(regfri4000.getFacUrl());
				paramList.add(regfri4000.getFacType());
				paramList.add(regfri4000.getRegCapital());
				paramList.add(regfri4000.getRegDate());
				paramList.add(regfri4000.getOfficeCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getSyncDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(regfri4000.getUpdatedBy());
				paramList.add(regfri4000.getUpdatedDate());
				// Insert Statement
				paramList.add(regfri4000.getNewRegId());
				paramList.add(regfri4000.getRegId());
				paramList.add(regfri4000.getRegStatus());
				paramList.add(regfri4000.getRegStatusDesc());
				paramList.add(regfri4000.getRegStatusDate());
				paramList.add(regfri4000.getCusId());
				paramList.add(regfri4000.getCusFullname());
				paramList.add(regfri4000.getCusAddress());
				paramList.add(regfri4000.getCusTelno());
				paramList.add(regfri4000.getCusEmail());
				paramList.add(regfri4000.getCusUrl());
				paramList.add(regfri4000.getFacId());
				paramList.add(regfri4000.getFacFullname());
				paramList.add(regfri4000.getFacAddress());
				paramList.add(regfri4000.getFacTelno());
				paramList.add(regfri4000.getFacEmail());
				paramList.add(regfri4000.getFacUrl());
				paramList.add(regfri4000.getFacType());
				paramList.add(regfri4000.getRegCapital());
				paramList.add(regfri4000.getRegDate());
				paramList.add(regfri4000.getOfficeCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getSyncDate());
				paramList.add(regfri4000.getCreatedBy());
				paramList.add(regfri4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, WsRegfri4000Vo regfri4000Vo) {
		sql.append(" SELECT R4000.NEW_REG_ID ");
		sql.append(" ,R4000.CUS_ID ");
		sql.append(" ,R4000.CUS_FULLNAME ");
		sql.append(" ,R4000.CUS_ADDRESS ");
		sql.append(" ,R4000.CUS_TELNO ");
		sql.append(" ,R4000.CUS_EMAIL ");
		sql.append(" ,R4000.CUS_URL ");
		sql.append(" ,R4000.FAC_ID ");
		sql.append(" ,R4000.FAC_FULLNAME ");
		sql.append(" ,R4000.FAC_ADDRESS ");
		sql.append(" ,R4000.FAC_TELNO ");
		sql.append(" ,R4000.FAC_EMAIL ");
		sql.append(" ,R4000.FAC_URL ");
		sql.append(" ,R4000.FAC_TYPE ");
		sql.append(" ,R4000.REG_ID ");
		sql.append(" ,R4000.REG_STATUS ");
		sql.append(" ,R4000.REG_DATE ");
		sql.append(" ,R4000.REG_CAPITAL ");
		sql.append(" ,R4000.OFFICE_CODE ");
		sql.append(" ,R4000.SYNC_DATE ");
		sql.append(" ,R4000D.GROUP_ID ");
		sql.append(" FROM WS_REGFRI4000 R4000 ");
		sql.append(" INNER JOIN WS_REGFRI4000_DUTY R4000D ON R4000D.NEW_REG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");
		sql.append("   AND R4000D.IS_DELETED = 'N' ");

		// Factory Type
		if (StringUtils.isNotBlank(regfri4000Vo.getFacType())) {
			sql.append(" AND R4000.FAC_TYPE = ?");
			params.add(regfri4000Vo.getFacType());
		}

		// Duty Code
		if (StringUtils.isNotBlank(regfri4000Vo.getDutyGroupId())) {
			sql.append(" AND R4000D.GROUP_ID = ?");
			params.add(regfri4000Vo.getDutyGroupId());
		}

		// Office Code
		if (StringUtils.isNotBlank(regfri4000Vo.getOfficeCode())) {
			sql.append(" AND R4000.OFFICE_CODE LIKE ?");
			params.add(ExciseUtils.whereInLocalOfficeCode(regfri4000Vo.getOfficeCode()));
		}

		// Factory Fullname
		if (StringUtils.isNotBlank(regfri4000Vo.getFacFullname())) {
			sql.append(" AND R4000.FAC_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(regfri4000Vo.getFacFullname()) + "%");
		}

		// Customer Fullname
		if (StringUtils.isNotBlank(regfri4000Vo.getCusFullname())) {
			sql.append(" AND R4000.CUS_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(regfri4000Vo.getCusFullname()) + "%");
		}
		
		// newRegId
		if(StringUtils.isNotBlank(regfri4000Vo.getNewRegId())) {
			sql.append(" AND R4000.NEW_REG_ID = ?");
			params.add(regfri4000Vo.getNewRegId());
		}
	}

	@Override
	public List<WsRegfri4000Vo> findByCriteria(WsRegfri4000Vo regfri4000Vo, boolean isPaging) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, regfri4000Vo);
		
		sql.append(" ORDER BY R4000D.GROUP_ID, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");
		
		if (isPaging) {
			String sqlPaging = OracleUtils.limitForDatable(sql.toString(), regfri4000Vo.getStart(), regfri4000Vo.getLength());
			return this.commonJdbcTemplate.query(sqlPaging, params.toArray(), WsRegfri4000VoRowMapper.getInstance());
		} else {
			return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), WsRegfri4000VoRowMapper.getInstance());
		}
	}

	@Override
	public Long countByCriteria(WsRegfri4000Vo regfri4000Vo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, regfri4000Vo);

		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}
	
}
