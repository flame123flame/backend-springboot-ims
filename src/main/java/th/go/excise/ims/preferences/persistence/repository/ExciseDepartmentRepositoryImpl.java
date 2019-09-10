package th.go.excise.ims.preferences.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVoReq;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVoRes;
import th.go.excise.ims.ws.client.pcc.inquiryedoffice.model.EdOffice;

public class ExciseDepartmentRepositoryImpl implements ExciseDepartmentRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseDepartmentRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<EdOffice> edOfficeList) {
		logger.info("batchMerge edOfficeList.size()={}", edOfficeList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"ED.OFF_NAME = ?",
			"ED.OFF_SHORT_NAME = ?",
			"ED.OFF_INDC = ?",
			"ED.OFF_LOCATION_CODE = ?",
			"ED.OFF_SUP_CODE = ?",
			"ED.TELNO = ?",
			"ED.IS_DELETED = ?",
			"ED.UPDATED_BY = ?",
			"ED.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"ED.OFF_ID",
			"ED.OFF_CODE",
			"ED.OFF_NAME",
			"ED.OFF_SHORT_NAME",
			"ED.OFF_INDC",
			"ED.OFF_LOCATION_CODE",
			"ED.OFF_SUP_CODE",
			"ED.TELNO",
			"ED.CREATED_BY",
			"ED.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_DEPARTMENT ED ");
		sql.append(" USING DUAL ");
		sql.append(" ON (ED.OFF_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_DEPARTMENT_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), edOfficeList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<EdOffice>() {
			public void setValues(PreparedStatement ps, EdOffice edOffice) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(edOffice.getOffcode());
				// Update Statement
				paramList.add(edOffice.getOffname());
				paramList.add(edOffice.getShortName());
				paramList.add(edOffice.getIndcOff());
				paramList.add(edOffice.getTambolCode());
				paramList.add(edOffice.getSupoffcode());
				paramList.add(edOffice.getTelno());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(edOffice.getOffcode());
				paramList.add(edOffice.getOffname());
				paramList.add(edOffice.getShortName());
				paramList.add(edOffice.getIndcOff());
				paramList.add(edOffice.getTambolCode());
				paramList.add(edOffice.getSupoffcode());
				paramList.add(edOffice.getTelno());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	
	
	
	private void buildSearchQuery(StringBuilder sql, List<Object> params, ExciseDepartmentVoReq request) {
		sql.append(" SELECT off_code,off_name,off_short_name ");
		sql.append(" FROM excise_department ");
		sql.append(" WHERE is_deleted = ? ");

		params.add(FLAG.N_FLAG);

		if (StringUtils.isNotBlank(request.getFlag()) && "Y".equals(request.getFlag())) {
			sql.append(" AND off_code LIKE '00____' ");
		}else if(StringUtils.isNotBlank(request.getFlag()) && "N".equals(request.getFlag())) {
			sql.append(" AND off_code NOT LIKE '00____' ");
		}


	}
	
	@Override
	public Integer countByDepartmentFlag(ExciseDepartmentVoReq request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}

	@Override
	public List<ExciseDepartmentVoRes> findDepartmentFlag(ExciseDepartmentVoReq request) {
		logger.debug("findDepartmentFlag  request.getFlag={}", request.getFlag());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY off_code ");

		List<ExciseDepartmentVoRes> datas = this.commonJdbcTemplate.query(
			OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength()), 
			params.toArray(), 
			new RowMapper<ExciseDepartmentVoRes>() {
			@Override
			public ExciseDepartmentVoRes mapRow(ResultSet rs, int rowNum) throws SQLException {
				ExciseDepartmentVoRes data = new ExciseDepartmentVoRes();
				data.setOffCode(rs.getString("off_code"));
				data.setOffName(rs.getString("off_name"));
				data.setOffShortName(rs.getString("off_short_name"));
				return data;
			}

		});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}

}
