package th.go.excise.ims.ta.persistence.repository;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.TA_DUTY_TYPE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000Vo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;

public class TaWsReg4000RepositoryImpl implements TaWsReg4000RepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsReg4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<TaWsReg4000> taWsReg4000List) {
		logger.info("batchMerge taWsReg4000List.size()={}", taWsReg4000List.size());

		final int BATCH_SIZE = 1000;

		List<String> updateColumnNames = new ArrayList<>(Arrays.asList("REG4000.CUS_ID = ?", "REG4000.CUS_FULLNAME = ?", "REG4000.CUS_ADDRESS = ?", "REG4000.CUS_TELNO = ?", "REG4000.CUS_EMAIL = ?", "REG4000.CUS_URL = ?",
				"REG4000.FAC_ID = ?", "REG4000.FAC_FULLNAME = ?", "REG4000.FAC_ADDRESS = ?", "REG4000.FAC_TELNO = ?", "REG4000.FAC_EMAIL = ?", "REG4000.FAC_URL = ?", "REG4000.FAC_TYPE = ?", "REG4000.REG_ID = ?",
				"REG4000.REG_STATUS = ?", "REG4000.REG_DATE = ?", "REG4000.REG_CAPITAL = ?", "REG4000.OFFICE_CODE = ?", "REG4000.ACTIVE_FLAG = ?", "REG4000.IS_DELETED = ?", "REG4000.UPDATED_BY = ?",
				"REG4000.UPDATED_DATE = ?"));

		List<String> insertColumnNames = new ArrayList<>(Arrays.asList("REG4000.WS_REG4000_ID", "REG4000.NEW_REG_ID", "REG4000.CUS_ID", "REG4000.CUS_FULLNAME", "REG4000.CUS_ADDRESS", "REG4000.CUS_TELNO",
				"REG4000.CUS_EMAIL", "REG4000.CUS_URL", "REG4000.FAC_ID", "REG4000.FAC_FULLNAME", "REG4000.FAC_ADDRESS", "REG4000.FAC_TELNO", "REG4000.FAC_EMAIL", "REG4000.FAC_URL", "REG4000.FAC_TYPE", "REG4000.REG_ID",
				"REG4000.REG_STATUS", "REG4000.REG_DATE", "REG4000.REG_CAPITAL", "REG4000.OFFICE_CODE", "REG4000.ACTIVE_FLAG", "REG4000.DUTY_CODE", "REG4000.CREATED_BY", "REG4000.CREATED_DATE"));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO TA_WS_REG4000 REG4000 ");
		sql.append(" USING DUAL ");
		sql.append(" ON ( ");
		sql.append("   REG4000.NEW_REG_ID = ? ");
		sql.append("   AND REG4000.DUTY_CODE = ? ");
		sql.append(" ) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (TA_WS_REG4000_SEQ.NEXTVAL" + StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), taWsReg4000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TaWsReg4000>() {
			public void setValues(PreparedStatement ps, TaWsReg4000 wsReg4000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(wsReg4000.getNewRegId());
				paramList.add(wsReg4000.getDutyCode());
				// Update Statement
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(FLAG.N_FLAG);
				paramList.add(wsReg4000.getUpdatedBy());
				paramList.add(wsReg4000.getUpdatedDate());
				// Insert Statement
				paramList.add(wsReg4000.getNewRegId());
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(wsReg4000.getDutyCode());
				paramList.add(wsReg4000.getCreatedBy());
				paramList.add(wsReg4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
		sql.append(" SELECT R4000.*, R4000D.GROUP_ID, R4000D.GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN TA_WS_REG4000_DUTY R4000D ON R4000D.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");

		// Factory Type
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			params.add(formVo.getFacType());
			sql.append(" AND R4000.FAC_TYPE = ?");
		}

		// Duty Code
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R4000D.GROUP_ID = ?");
			params.add(formVo.getDutyCode());
		}

		// Office Code
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND R4000.OFFICE_CODE LIKE ?");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}

		// Fac fullname
		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			sql.append(" AND R4000.FAC_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
		}

		// Cus fullname
		if (StringUtils.isNotBlank(formVo.getCusFullname())) {
			sql.append(" AND R4000.CUS_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
		}

		// newRegId
		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND R4000.NEW_REG_ID = ?");
			params.add(StringUtils.trim(formVo.getNewRegId()));
		}

		if (StringUtils.isNotBlank(formVo.getCuscatId())) {
			sql.append(" AND R4000.CUSCAT_ID = ?");
			params.add(StringUtils.trim(formVo.getCuscatId()));
		}
		if (StringUtils.isNotBlank(formVo.getPinnitId())) {
			sql.append(" AND R4000.PINNIT_ID = ?");
			params.add(StringUtils.trim(formVo.getPinnitId()));
		}
		
		// REG DATE START
		if (formVo.getRegDateStart() != null) {
			sql.append(" AND R4000.REG_DATE >= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getRegDateStart(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}

		// REG DATE END
		if (formVo.getRegDateEnd() != null) {
			sql.append(" AND R4000.REG_DATE <= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getRegDateEnd(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}
		
		// REG STATUS
		if (formVo.getRegStatus() != null && formVo.getRegStatus().size() > 0) {
			sql.append(" AND R4000.REG_STATUS IN ( ");
			sql.append(StringUtils.repeat("?", ",", formVo.getRegStatus().size()));
			sql.append(" ) ");
			params.addAll(formVo.getRegStatus());
		}else {
			sql.append(" AND R4000.REG_STATUS IN ('1','2','3','41','51') ");
		}
		
		//sql.append("   AND R4000.REG_STATUS IN ('1','2','3','41','51') "); // REG_STATUS = '1' is Active
	}

	@Override
	public List<WsReg4000Vo> findByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY R4000D.GROUP_ID, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		if (StringUtils.isNotBlank(formVo.getFlagPage())) {
			return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), wsReg4000RowMapper);
		} else {
			return this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), wsReg4000RowMapper);
		}
	}

	@Override
	public Long countByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	private static final RowMapper<WsReg4000Vo> wsReg4000RowMapper = new RowMapper<WsReg4000Vo>() {
		@Override
		public WsReg4000Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsReg4000Vo vo = new WsReg4000Vo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusId(rs.getString("CUS_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setCusAddress(rs.getString("CUS_ADDRESS"));
			vo.setCusTelno(rs.getString("CUS_TELNO"));
			vo.setCusEmail(rs.getString("CUS_EMAIL"));
			vo.setCusUrl(rs.getString("CUS_URL"));
			vo.setFacId(rs.getString("FAC_ID"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setFacTelno(rs.getString("FAC_TELNO"));
			vo.setFacEmail(rs.getString("FAC_EMAIL"));
			vo.setFacUrl(rs.getString("FAC_URL"));
			vo.setFacType(rs.getString("FAC_TYPE"));
			vo.setRegStatus(rs.getString("REG_STATUS"));
			vo.setRegDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_DATE")));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setDutyGroupId(rs.getString("GROUP_ID"));
			vo.setDutyGroupDesc(rs.getString("GROUP_NAME"));
			vo.setMultiDutyFlag(rs.getString("MULTI_DUTY_FLAG"));
			return vo;
		}
	};

	private void sqlOutsidePlan(StringBuilder sql, List<Object> params, OutsidePlanFormVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ");
		sql.append("   ,R4000.NEW_REG_ID ");
		sql.append("   ,R4000.FAC_FULLNAME ");
		sql.append("   ,R4000.FAC_ADDRESS ");
		sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000 ");
		sql.append("   ,ED_SECTOR.OFF_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.OFF_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFF_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.OFF_SHORT_NAME AREA_DESC ");
		sql.append("   ,R4000.REG_STATUS ");
		sql.append("   ,R4000.REG_STATUS_DESC ");
		sql.append("   ,R4000.REG_DATE ");
		sql.append("   ,DUTY_LIST.DUTY_GROUP_NAME ");
		sql.append("   ,DUTY_CODE_LIST.DUTY_GROUP_ID ");
		sql.append("   ,DUTY_TYPE_LIST.DUTY_GROUP_TYPE ");
		sql.append(" FROM TA_WS_REG4000 R4000  ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = SUBSTR( R4000.OFFICE_CODE,  0, 2  ) || '0000' ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = SUBSTR( R4000.OFFICE_CODE,0,4)||'00' ");
		sql.append(" LEFT JOIN ( ");
		sql.append("   SELECT DUTY.NEWREG_ID , LISTAGG(DUTY.GROUP_NAME,',') WITHIN GROUP (ORDER BY DUTY.GROUP_ID) AS DUTY_GROUP_NAME ");
		sql.append("   FROM TA_WS_REG4000_DUTY DUTY ");
		sql.append("   GROUP BY DUTY.NEWREG_ID ");
		sql.append(" ) DUTY_LIST ");
		sql.append(" ON DUTY_LIST.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" LEFT JOIN ( ");
		sql.append("   SELECT DUTY.NEWREG_ID , LISTAGG(DUTY.GROUP_ID,',') WITHIN GROUP (ORDER BY DUTY.GROUP_ID) AS DUTY_GROUP_ID ");
		sql.append("   FROM TA_WS_REG4000_DUTY DUTY ");
		sql.append("   GROUP BY DUTY.NEWREG_ID ");
		sql.append(" ) DUTY_CODE_LIST ");
		sql.append(" ON DUTY_CODE_LIST.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" LEFT JOIN ( ");
		sql.append("   SELECT DUTY.NEWREG_ID , LISTAGG(EDG.DUTY_GROUP_TYPE,',') WITHIN GROUP (ORDER BY DUTY.GROUP_ID) AS DUTY_GROUP_TYPE ");
		sql.append("   FROM TA_WS_REG4000_DUTY DUTY ");
		sql.append("   INNER JOIN EXCISE_DUTY_GROUP EDG ON EDG.DUTY_GROUP_CODE = DUTY.GROUP_ID ");
		sql.append("   GROUP BY DUTY.NEWREG_ID ");
		sql.append(" ) DUTY_TYPE_LIST ");
		sql.append(" ON DUTY_TYPE_LIST.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE 1 = 1 ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND R4000.OFFICE_CODE LIKE ? ");
		params.add(formVo.getOfficeCode());

		// REG STATUS
		if (formVo.getRegStatus() != null && formVo.getRegStatus().size() > 0) {
			sql.append(" AND R4000.REG_STATUS IN ( ");
			sql.append(StringUtils.repeat("?", ",", formVo.getRegStatus().size()));
			sql.append(" ) ");
			params.addAll(formVo.getRegStatus());
		}

		// DUTY GROUP
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			sql.append(" AND DUTY_TYPE_LIST.DUTY_GROUP_TYPE LIKE ? ");
			params.add("%" + formVo.getFacType() + "%");
		}

		// CUS FULLNAME
		if (StringUtils.isNotBlank(formVo.getCusFullname())) {
			sql.append(" AND R4000.CUS_FULLNAME LIKE ? ");
			params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
		}

		// FAC FULLNAME
		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			sql.append(" AND R4000.FAC_FULLNAME LIKE ? ");
			params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
		}

		// DUTY_CODE
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND DUTY_CODE_LIST.DUTY_GROUP_ID LIKE ? ");
			params.add("%" + formVo.getDutyCode() + "%");
		}

		// CUS TYPE
		if (StringUtils.isNotBlank(formVo.getCusType())) {
			sql.append(" AND R4000.CUSCAT_ID = ? ");
			params.add(formVo.getCusType());
		}

		// CUS ID
		if (StringUtils.isNotBlank(formVo.getCusId())) {
			sql.append(" AND R4000.PINNIT_ID = ? ");
			params.add(formVo.getCusId());
		}

		// REG DATE START
		if (formVo.getFromDate() != null) {
			sql.append(" AND R4000.REG_DATE >= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getFromDate(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}

		// REG DATE END
		if (formVo.getToDate() != null) {
			sql.append(" AND R4000.REG_DATE <= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getToDate(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}
		
		if (StringUtils.isNotBlank(formVo.getPlanType())) {
			sql.append(" AND R4000.NEW_REG_ID NOT IN ( SELECT NEW_REG_ID FROM TA_PLAN_WORKSHEET_DTL WHERE IS_DELETED  = 'N' AND PLAN_NUMBER =  ? ) ");
			params.add(formVo.getPlanNumber());
		}
		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND R4000.NEW_REG_ID = ? ");
			params.add(formVo.getNewRegId());
		}
		
	}

	@Override
	public List<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlOutsidePlan(sql, params, formVo);
		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), outsidePlanRowMapper);
	}

	@Override
	public Long countOutsidePlan(OutsidePlanFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlOutsidePlan(sql, params, formVo);
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	protected RowMapper<OutsidePlanVo> outsidePlanRowMapper = new RowMapper<OutsidePlanVo>() {
		@Override
		public OutsidePlanVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			OutsidePlanVo vo = new OutsidePlanVo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setDutyDesc(rs.getString("DUTY_GROUP_NAME"));
			vo.setRegDate(ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY));
			vo.setRegStatus(rs.getString("REG_STATUS_DESC") + " " + ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY));
			vo.setRegStatusDesc(rs.getString("REG_STATUS_DESC"));
			return vo;
		}
	};

	@Override
	public WsRegfri4000FormVo findByNewRegId(String newRegId) {
		logger.info("findByNewRegId newRegId={}", newRegId);

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();

		sql.append(" SELECT R4000.*, R4000D.GROUP_ID, R4000D.GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN TA_WS_REG4000_DUTY R4000D ON R4000D.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");
		sql.append("   AND R4000.REG_STATUS IN ('1','2','3','41','51') "); // REG_STATUS = '1' is Active
		sql.append("   AND R4000.NEW_REG_ID = ? ");
		params.add(newRegId);

		WsRegfri4000FormVo result = commonJdbcTemplate.query(sql.toString(), params.toArray(), new ResultSetExtractor<WsRegfri4000FormVo>() {
			@Override
			public WsRegfri4000FormVo extractData(ResultSet rs) throws SQLException, DataAccessException {
				WsRegfri4000FormVo vo = new WsRegfri4000FormVo();
				List<RegDuty> regDutyList = new ArrayList<>();
				RegDuty regDuty = null;
				int i = 0;
				while (rs.next()) {
					if (i == 0) {
						vo.setNewregId(rs.getString("NEW_REG_ID"));
						vo.setNewRegId(rs.getString("NEW_REG_ID"));
						vo.setRegId(rs.getString("REG_ID"));
						vo.setRegStatus(rs.getString("REG_STATUS"));
						vo.setRegStatusDesc(rs.getString("REG_STATUS_DESC"));
						vo.setStatusDate(rs.getString("REG_STATUS_DATE"));
						vo.setCusId(rs.getString("CUS_ID"));
						vo.setCusSeq(rs.getString("CUS_SEQ"));
						vo.setCusAddrseq(rs.getString("CUS_ADDR_SEQ"));
						vo.setCusFullname(rs.getString("CUS_FULLNAME"));
						vo.setCusHouseno(rs.getString("CUS_HOUSE_NO"));
						vo.setCusAddrno(rs.getString("CUS_ADDR_NO"));
						vo.setCusBuildname(rs.getString("CUS_BUILD_NAME"));
						vo.setCusFloorno(rs.getString("CUS_FLOOR_NO"));
						vo.setCusRoomno(rs.getString("CUS_ROOM_NO"));
						vo.setCusMoono(rs.getString("CUS_MOO_NO"));
						vo.setCusVillage(rs.getString("CUS_VILLAGE"));
						vo.setCusSoiname(rs.getString("CUS_SOI_NAME"));
						vo.setCusThnname(rs.getString("CUS_THN_NAME"));
						vo.setCusTambolcode(rs.getString("CUS_TAMBOL_CODE"));
						vo.setCusTambolname(rs.getString("CUS_TAMBOL_NAME"));
						vo.setCusAmphurcode(rs.getString("CUS_AMPHUR_CODE"));
						vo.setCusAmphurname(rs.getString("CUS_AMPHUR_NAME"));
						vo.setCusProvincecode(rs.getString("CUS_PROVINCE_CODE"));
						vo.setCusProvincename(rs.getString("CUS_PROVINCE_NAME"));
						vo.setCusZipcode(rs.getString("CUS_ZIP_CODE"));
						vo.setCustomerAddress(rs.getString("CUS_ADDRESS"));
						vo.setFacId(rs.getString("FAC_ID"));
						vo.setFacSeq(rs.getString("FAC_SEQ"));
						vo.setFacAddrseq(rs.getString("FAC_ADDR_SEQ"));
						vo.setFacFullname(rs.getString("FAC_FULLNAME"));
						vo.setFacHouseno(rs.getString("FAC_HOUSE_NO"));
						vo.setFacAddrno(rs.getString("FAC_ADDR_NO"));
						vo.setFacBuildname(rs.getString("FAC_BUILD_NAME"));
						vo.setFacFloorno(rs.getString("FAC_FLOOR_NO"));
						vo.setFacRoomno(rs.getString("FAC_ROOM_NO"));
						vo.setFacMoono(rs.getString("FAC_MOO_NO"));
						vo.setFacVillage(rs.getString("FAC_VILLAGE"));
						vo.setFacSoiname(rs.getString("FAC_SOI_NAME"));
						vo.setFacThnname(rs.getString("FAC_THN_NAME"));
						vo.setFacTambolcode(rs.getString("FAC_TAMBOL_CODE"));
						vo.setFacTambolname(rs.getString("FAC_TAMBOL_NAME"));
						vo.setFacAmphurcode(rs.getString("FAC_AMPHUR_CODE"));
						vo.setFacAmphurname(rs.getString("FAC_AMPHUR_NAME"));
						vo.setFacProvincecode(rs.getString("FAC_PROVINCE_CODE"));
						vo.setFacProvincename(rs.getString("FAC_PROVINCE_NAME"));
						vo.setFacZipcode(rs.getString("FAC_ZIP_CODE"));
						vo.setFacAddress(rs.getString("FAC_ADDRESS"));
						vo.setOffcode(rs.getString("OFFICE_CODE"));
						vo.setCapital(rs.getString("REG_CAPITAL"));
						vo.setFactoryType(rs.getString("FAC_TYPE"));
					}
					regDuty = new RegDuty();
					regDuty.setGroupId(rs.getString("GROUP_ID"));
					regDuty.setGroupName(rs.getString("GROUP_NAME"));
					regDuty.setRegDate("REG_DATE");
					regDutyList.add(regDuty);
					i++;
				}
				vo.setRegDutyList(regDutyList);
				return vo;
			}
		});

		return result;
	}

	@Override
	public Map<String, List<String>> findDutyByNewRegId(List<String> newRegIdList) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NEWREG_ID, REG_ID, GROUP_ID, GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000_DUTY ");
		sql.append(" WHERE NEWREG_ID IN ( ");
		sql.append(StringUtils.repeat("?", ",", newRegIdList.size()));
		sql.append(" ) ");
		sql.append(" ORDER BY NEWREG_ID, GROUP_ID ");

		List<Object> paramList = new ArrayList<>();
		paramList.addAll(newRegIdList);

		Map<String, List<String>> dutyMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, List<String>>>() {
			public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<String>> tmpDutyMap = new HashMap<>();
				List<String> dutyGroupList = null;
				while (rs.next()) {
					dutyGroupList = tmpDutyMap.get(rs.getString("NEWREG_ID"));
					if (dutyGroupList == null) {
						dutyGroupList = new ArrayList<>();
					}
					dutyGroupList.add(rs.getString("GROUP_NAME"));
					tmpDutyMap.put(rs.getString("NEWREG_ID"), dutyGroupList);
				}
				return tmpDutyMap;
			}
		});

		return dutyMap;
	}
	
	private void buildByCriteriaPivotQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo, String incomeTaxType, String dutyType) {
		logger.info("buildByCriteriaPivotQuery dutyType={}", dutyType);
		
		sql.append(" SELECT R.NEW_REG_ID AS R4000_NEW_REG_ID ");
		sql.append("   ,CASE WHEN DG.DUTY_GROUP_CODE IS NOT NULL THEN DG.DUTY_GROUP_CODE ELSE D.GROUP_ID END AS GROUP_ID ");
		sql.append("   ,CASE WHEN DG.DUTY_GROUP_CODE IS NOT NULL THEN DG.DUTY_GROUP_NAME ELSE D.GROUP_NAME END AS GROUP_NAME ");
		sql.append("   ,R.CUS_FULLNAME ");
		sql.append("   ,R.FAC_FULLNAME ");
		sql.append("   ,R.FAC_ADDRESS ");
		sql.append("   ,R.OFFICE_CODE ");
		sql.append("   ,R.REG_STATUS ");
		sql.append("   ,R.REG_STATUS_DESC ");
		sql.append("   ,R.REG_DATE ");
		sql.append("   ,R.REG_CAPITAL ");
		sql.append("   ,R.MULTI_DUTY_FLAG ");
		sql.append("   ,M.* ");
		sql.append(" FROM TA_WS_REG4000 R ");
		sql.append(" LEFT JOIN TA_WS_REG4000_DUTY D ON D.NEWREG_ID = R.NEW_REG_ID ");
		sql.append(" LEFT JOIN ( ");
		sql.append("   SELECT * ");
		sql.append("   FROM ( ");
		sql.append("     SELECT NEW_REG_ID ");
		sql.append("       ,DUTY_CODE ");
		sql.append("       ,TAX_YEAR || TAX_MONTH AS YEAR_MONTH ");
		if (TA_CONFIG.INCOME_TYPE_TAX.equals(incomeTaxType)) {
			sql.append("       ,SUM(TAX_AMOUNT) AS TAX_AMOUNT ");
		} else {
			sql.append("       ,SUM(NET_TAX_AMOUNT) AS TAX_AMOUNT ");
		}
		sql.append("     FROM TA_WS_INC8000_M ");
		sql.append("     GROUP BY NEW_REG_ID, TAX_YEAR, TAX_MONTH, DUTY_CODE ");
		sql.append("   ) PIVOT (SUM(TAX_AMOUNT) FOR YEAR_MONTH IN (").append(org.springframework.util.StringUtils.collectionToDelimitedString(formVo.getYearMonthList(), ",")).append(")) ");
		sql.append(" ) M ON M.NEW_REG_ID = R.NEW_REG_ID ");
		if (TA_DUTY_TYPE.SEPARATE.equals(dutyType)) {
			sql.append("   AND D.GROUP_ID = M.DUTY_CODE ");
		} else if (TA_DUTY_TYPE.GROUP.equals(dutyType)) {
			sql.append("   AND D.GROUP_ID LIKE SUBSTR(M.DUTY_CODE, 0, 2) || '__' ");
		}
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP DG ON DG.DUTY_GROUP_CODE = M.DUTY_CODE ");
		sql.append("   AND DG.IS_DELETED = 'N' ");
		sql.append(" WHERE R.IS_DELETED = 'N' ");
		sql.append("   AND GROUP_ID IN (SELECT DUTY_CODE FROM TA_DUTY_CONFIG WHERE DUTY_TYPE = ?) ");
		params.add(dutyType);
		
		// FAC_TYPE
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			sql.append(" AND R.FAC_TYPE = ? ");
			params.add(formVo.getFacType());
		}
		// GROUP_ID
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R.GROUP_ID = ? ");
			params.add(formVo.getDutyCode());
		}
		// OFFICE_CODE
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND R.OFFICE_CODE LIKE ? ");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}
		// FAC_FULLNAME
		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			sql.append(" AND R.FAC_FULLNAME LIKE ? ");
			params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
		}
		// CUS_FULLNAME
		if (StringUtils.isNotBlank(formVo.getCusFullname())) {
			sql.append(" AND R.CUS_FULLNAME LIKE ? ");
			params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
		}
		// NEW_REG_ID
		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND R.NEW_REG_ID = ? ");
			params.add(StringUtils.trim(formVo.getNewRegId()));
		}
		// CUSCAT_ID
		if (StringUtils.isNotBlank(formVo.getCuscatId())) {
			sql.append(" AND R.CUSCAT_ID = ? ");
			params.add(StringUtils.trim(formVo.getCuscatId()));
		}
		// PINNIT_ID
		if (StringUtils.isNotBlank(formVo.getPinnitId())) {
			sql.append(" AND R.PINNIT_ID = ? ");
			params.add(StringUtils.trim(formVo.getPinnitId()));
		}
		// REG_DATE START
		if (formVo.getRegDateStart() != null) {
			sql.append(" AND R.REG_DATE >= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getRegDateStart(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}
		// REG_DATE END
		if (formVo.getRegDateEnd() != null) {
			sql.append(" AND R.REG_DATE <= TO_DATE(?, 'YYYYMMDD') ");
			params.add(ConvertDateUtils.formatDateToString(formVo.getRegDateEnd(), ConvertDateUtils.YYYYMMDD, Locale.US));
		}
		// REG_STATUS
		if (formVo.getRegStatus() != null && formVo.getRegStatus().size() > 0) {
			sql.append(" AND R.REG_STATUS IN ( ");
			sql.append(StringUtils.repeat("?", ",", formVo.getRegStatus().size()));
			sql.append(" ) ");
			params.addAll(formVo.getRegStatus());
		} else {
			sql.append(" AND R.REG_STATUS IN ('1','2','3','41','51') ");
		}
	}

	public List<TaxOperatorDetailVo> findByCriteriaPivotDatatable(TaxOperatorFormVo formVo, Map<String, String> auditPlanMap, Map<String, String> maxYearMap, String incomeTaxType) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		sql.append(" SELECT * FROM ( ");
		buildByCriteriaPivotQuery(sql, params, formVo, incomeTaxType, TA_DUTY_TYPE.SEPARATE);
		sql.append(" UNION ");
		buildByCriteriaPivotQuery(sql, params, formVo, incomeTaxType, TA_DUTY_TYPE.GROUP);
		sql.append(" ) X ");
		sql.append(" ORDER BY X.GROUP_ID, X.R4000_NEW_REG_ID ");
		
		List<TaxOperatorDetailVo> taxOperatorDetailVoList = commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), new ResultSetExtractor<List<TaxOperatorDetailVo>>() {
			public List<TaxOperatorDetailVo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				int budgetYear = Integer.parseInt(formVo.getBudgetYear());
				List<TaxOperatorDetailVo> voList = new ArrayList<>();
				TaxOperatorDetailVo vo = null;
				ResultSetMetaData rsmd = rs.getMetaData();
				ExciseDepartment exciseDeptSector;
				ExciseDepartment exciseDeptArea;
				Map<String, Integer> incMultiDutyMap = new HashMap<>();
				while (rs.next()) {
					vo = new TaxOperatorDetailVo();
					vo.setNewRegId(rs.getString("R4000_NEW_REG_ID"));
					vo.setDutyCode(rs.getString("GROUP_ID"));
					vo.setDutyName(rs.getString("GROUP_NAME"));
					vo.setCusFullname(rs.getString("CUS_FULLNAME"));
					vo.setFacFullname(rs.getString("FAC_FULLNAME"));
					vo.setFacAddress(rs.getString("FAC_ADDRESS"));
					vo.setOfficeCode(rs.getString("OFFICE_CODE"));
					vo.setMultiDutyFlag(rs.getString("MULTI_DUTY_FLAG"));
					vo.setRegDate(ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), "dd/MM/yy", ConvertDateUtils.LOCAL_TH));
					vo.setRegStatus(rs.getString("REG_STATUS_DESC") + " " + vo.getRegDate());
					vo.setRegCapital(rs.getString("REG_CAPITAL"));
					vo.setTaxAuditLast1(auditPlanMap.get(String.valueOf(budgetYear - 1) + vo.getNewRegId()));
					vo.setTaxAuditLast2(auditPlanMap.get(String.valueOf(budgetYear - 2) + vo.getNewRegId()));
					vo.setTaxAuditLast3(auditPlanMap.get(String.valueOf(budgetYear - 3) + vo.getNewRegId()));
					vo.setLastAuditYear(maxYearMap.get(vo.getNewRegId()));
					BigDecimal sumTaxAmtG1 = BigDecimal.ZERO;
					BigDecimal sumTaxAmtG2 = BigDecimal.ZERO;
					int indKey1 = 1;
					int indKey2 = 1;
					int taxMonthNo = 0;
					String columnName = "";
					
					for (int i = 15; i <= rsmd.getColumnCount(); i++) {
						columnName = rsmd.getColumnName(i);
						if (columnName.indexOf("G1") >= 2) {
							if (rs.getString(columnName) != null) {
								taxMonthNo++;
								setTaxAmount(vo, "G1M" + indKey1, rs.getString(columnName));
								sumTaxAmtG1 = sumTaxAmtG1.add(NumberUtils.nullToZero(NumberUtils.toBigDecimal(rs.getString(columnName))));
							} else {
								setTaxAmount(vo, "G1M" + indKey1, "-");
							}
						} else {
							if (rs.getString(columnName) != null) {
								taxMonthNo++;
								setTaxAmount(vo, "G2M" + indKey2, rs.getString(columnName));
								sumTaxAmtG2 = sumTaxAmtG2.add(NumberUtils.nullToZero(NumberUtils.toBigDecimal(rs.getString(columnName))));
							} else {
								setTaxAmount(vo, "G2M" + indKey2, "-");
							}
							indKey2++;
						}
						indKey1++;
					}
					vo.setTaxMonthNo(String.valueOf(taxMonthNo));
					
					int monthNum = formVo.getDateRange();
					String notPayTaxMonthNo = null;
					if (monthNum == taxMonthNo) {
						notPayTaxMonthNo = "-";
					} else {
						notPayTaxMonthNo = String.valueOf(monthNum - taxMonthNo);
					}
					vo.setNotPayTaxMonthNo(notPayTaxMonthNo);
					
					exciseDeptSector = ApplicationCache.getExciseDepartment(vo.getOfficeCode().substring(0, 2) + "0000");
					if (exciseDeptSector != null) {
						vo.setSecCode(exciseDeptSector.getOfficeCode());
						vo.setSecDesc(exciseDeptSector.getDeptShortName());
					}
					
					exciseDeptArea = ApplicationCache.getExciseDepartment(vo.getOfficeCode().substring(0, 4) + "00");
					if (exciseDeptArea != null) {
						vo.setAreaCode(exciseDeptArea.getOfficeCode());
						vo.setAreaDesc(exciseDeptArea.getDeptShortName());
					}
					
					vo.setSumTaxAmtG1(BigDecimal.ZERO.equals(sumTaxAmtG1) ? "-" : (sumTaxAmtG1.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					vo.setSumTaxAmtG2(BigDecimal.ZERO.equals(sumTaxAmtG2) ? "-" : (sumTaxAmtG2.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					BigDecimal percentTax = NumberUtils.calculatePercent(sumTaxAmtG1, sumTaxAmtG2);
					vo.setTaxAmtChnPnt(BigDecimal.ZERO.equals(percentTax) ? "-" : percentTax.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					Integer incMultiDutyCount = 0;
					incMultiDutyCount = incMultiDutyMap.get(vo.getNewRegId());
					if (incMultiDutyCount == null) {
						incMultiDutyCount = 0;
					}
					if (taxMonthNo > 0) {
						incMultiDutyCount++;
					}
					incMultiDutyMap.put(vo.getNewRegId(), incMultiDutyCount);
					voList.add(vo);
				}
				calculateIncMultiDuty(voList, incMultiDutyMap);
				logger.info("End : {}", voList.size());
				
				return voList;
			}
		});
		
		return taxOperatorDetailVoList;
	}
	
	private void calculateIncMultiDuty(List<TaxOperatorDetailVo> detailVoList, Map<String, Integer> incMultiDutyMap) {
		for (TaxOperatorDetailVo detailVo : detailVoList) {
			int incMultiDutyCount = incMultiDutyMap.get(detailVo.getNewRegId());
			if (incMultiDutyCount > 1) {
				detailVo.setIncMultiDutyFlag(FLAG.Y_FLAG);
			}
		}
	}

	private void setTaxAmount(TaxOperatorDetailVo detailVo, String groupMonthNo, String taxAmount) {
		try {
			Method method = TaxOperatorDetailVo.class.getDeclaredMethod("setTaxAmt" + groupMonthNo, String.class);
			method.invoke(detailVo, taxAmount);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}

}
