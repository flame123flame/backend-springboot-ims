package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaWsLic6010;
import th.go.excise.ims.ia.vo.AuditLicexpDVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Repository
public class Int0604JdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0604JdbcRepository.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<WsLicfri6010> findWsLicfri6010Formapping() {
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT W.* FROM WS_LICFRI6010 W ");
		sql.append(" WHERE 1=1 ");
		sql.append("   AND NOT EXISTS( SELECT 1 FROM IA_WS_LIC6010 I WHERE I.CURRENT_LIC_ID = W.WS_LICFRI6010_ID ) ");
		sql.append(" ORDER BY W.LIC_DATE ");
		
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), rowMapper);
	}

	public List<WsLicfri6010> nextLic(WsLicfri6010 wsLicfri6010) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM WS_LICFRI6010 L ");
		sql.append(" WHERE L.IS_DELETED = 'N' ");
		sql.append(" AND L.FAC_ID = ? ");
		sql.append(" AND L.LIC_TYPE =? ");
		sql.append(" AND L.START_DATE > to_date(? , 'yyyy-mm-dd') ");
		sql.append(" ORDER BY LIC_DATE ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(wsLicfri6010.getFacId());
		paramList.add(wsLicfri6010.getLicType());
		paramList.add(wsLicfri6010.getExpDate() != null ? wsLicfri6010.getExpDate().toString() : LocalDate.now());
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), rowMapper);
	}

	private RowMapper<WsLicfri6010> rowMapper = new RowMapper<WsLicfri6010>() {
		@Override
		public WsLicfri6010 mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsLicfri6010 vo = new WsLicfri6010();
			vo.setWsLicfri6010Id(rs.getLong("WS_LICFRI6010_ID"));
			vo.setOffcode(rs.getString("OFFCODE"));
			vo.setLicType(rs.getString("LIC_TYPE"));
			vo.setLicNo(rs.getString("LIC_NO"));
			vo.setLicName(rs.getString("LIC_NAME"));
			vo.setLicFee(rs.getBigDecimal("LIC_FEE"));
			vo.setLicInterior(rs.getBigDecimal("LIC_INTERIOR"));
			vo.setLicPrice(rs.getBigDecimal("LIC_PRICE"));
			vo.setLicDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("LIC_DATE")));
			vo.setStartDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("START_DATE")));
			vo.setExpDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("EXP_DATE")));
			vo.setSendDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("SEND_DATE")));
			vo.setPrintCount(NumberUtils.toBigDecimal(rs.getString("PRINT_COUNT")));
			vo.setNid(rs.getString("NID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setIncCode(rs.getString("INC_CODE"));
			vo.setFacId(rs.getString("FAC_ID"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE")));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE")));
			return vo;
		}
	};
	
	public List<IaWsLic6010> findNextLicense(String dateStart, String dateEnd) {
		logger.info("findNextLicense dateStart={}, dateEnd={}", dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT SL.WS_LICFRI6010_ID AS OLD_ID, L.WS_LICFRI6010_ID AS NEW_ID, L.LIC_DATE, L.LIC_NO ");
		sql.append(" FROM WS_LICFRI6010 L ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT W.* ");
		sql.append("   FROM WS_LICFRI6010 W ");
		sql.append("   WHERE W.IS_DELETED = 'N' ");
		sql.append("     AND W.LIC_DATE >= TO_DATE(?, 'YYYYMMDD') ");
		paramList.add(dateStart);
		sql.append("     AND W.LIC_DATE <= TO_DATE(?, 'YYYYMMDD') ");
		paramList.add(dateEnd);
		sql.append("     AND NOT EXISTS ( ");
		sql.append("   	   SELECT 1 ");
		sql.append("   	   FROM IA_WS_LIC6010 I ");
		sql.append("   	   WHERE I.CURRENT_LIC_ID = W.WS_LICFRI6010_ID ");
		sql.append("   	 ) ");
		sql.append(" ) SL ON L.FAC_ID = SL.FAC_ID ");
		sql.append("   AND L.LIC_TYPE = SL.LIC_TYPE ");
		sql.append(" WHERE L.IS_DELETED = 'N' ");
		sql.append("   AND L.START_DATE > NVL(SL.EXP_DATE, TRUNC(SYSDATE)) ");
		sql.append(" ORDER BY L.LIC_DATE ");
		
		List<IaWsLic6010> iaWsLic6010List = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new RowMapper<IaWsLic6010>() {
			@Override
			public IaWsLic6010 mapRow(ResultSet rs, int rowNum) throws SQLException {
				IaWsLic6010 entity = new IaWsLic6010();
				entity.setCurrentLicId(rs.getLong("OLD_ID"));
				entity.setNewLicId(rs.getLong("NEW_ID"));
				entity.setNewLicDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("LIC_DATE")));
				entity.setNewLicNo(StringUtils.trim(rs.getString("LIC_NO")));
				return entity;
			}
		});
		
		return iaWsLic6010List;
	}

	public List<WsLicfri6010> findByCriteria(Int0602FormVo vo, String strOrder) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM WS_LICFRI6010 WS ");
		sql.append(" WHERE WS.IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND WS.OFFCODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}
		if (StringUtils.isNotBlank(vo.getLicDateFrom())) {
			sql.append(" AND WS.LIC_DATE >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = ConvertDateUtils.parseStringToLocalDate(vo.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		if (StringUtils.isNotBlank(vo.getLicDateTo())) {
			sql.append(" AND WS.LIC_DATE <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = ConvertDateUtils.parseStringToLocalDate(vo.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		sql.append(" ORDER BY ").append(strOrder);
		
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab1RowMapper);

	}

	private RowMapper<WsLicfri6010> tab1RowMapper = new RowMapper<WsLicfri6010>() {
		@Override
		public WsLicfri6010 mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsLicfri6010 vo = new WsLicfri6010();
			vo.setWsLicfri6010Id(rs.getLong("WS_LICFRI6010_ID"));
			vo.setOffcode(rs.getString("OFFCODE"));
			vo.setLicType(rs.getString("LIC_TYPE"));
			vo.setLicNo(rs.getString("LIC_NO"));
			vo.setLicName(rs.getString("LIC_NAME"));
			vo.setLicFee(rs.getBigDecimal("LIC_FEE"));
			vo.setLicInterior(rs.getBigDecimal("LIC_INTERIOR"));
			vo.setLicPrice(rs.getBigDecimal("LIC_PRICE"));
			vo.setLicDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("LIC_DATE")));
			vo.setStartDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("START_DATE")));
			vo.setExpDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("EXP_DATE")));
			vo.setSendDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("SEND_DATE")));
			vo.setPrintCount(NumberUtils.toBigDecimal(rs.getString("PRINT_COUNT")));
			vo.setNid(rs.getString("NID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setIncCode(rs.getString("INC_CODE"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE")));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE")));
			return vo;
		}
	};

	public List<AuditLicexpDVo> findByCriteria(Int0602FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT WS.NEW_REG_ID,WS.CUS_FULLNAME,WS.FAC_FULLNAME,WS.LIC_NAME,WS.LIC_TYPE,WS.LIC_NO,WS.LIC_DATE,WS.EXP_DATE,IA.NEW_LIC_NO AS LIC_NO_NEW,IA.NEW_LIC_DATE AS LIC_DATE_NEW");
		sql.append(" FROM WS_LICFRI6010 WS ");
		sql.append(" LEFT JOIN IA_WS_LIC6010 IA ");
		sql.append(" ON WS.WS_LICFRI6010_ID = IA.CURRENT_LIC_ID ");
		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND WS.OFFCODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}
		if (StringUtils.isNotBlank(vo.getLicDateFrom())) {
			sql.append(" AND WS.LIC_DATE >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = ConvertDateUtils.parseStringToLocalDate(vo.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		if (StringUtils.isNotBlank(vo.getLicDateTo())) {
			sql.append(" AND WS.LIC_DATE <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = ConvertDateUtils.parseStringToLocalDate(vo.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		sql.append(" ORDER BY EXP_DATE");
		
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), int0604RowMapper);

	}

	private RowMapper<AuditLicexpDVo> int0604RowMapper = new RowMapper<AuditLicexpDVo>() {
		@Override
		public AuditLicexpDVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			AuditLicexpDVo vo = new AuditLicexpDVo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullName(rs.getString("CUS_FULLNAME"));
			vo.setFacFullName(rs.getString("FAC_FULLNAME"));
			vo.setLicName(rs.getString("LIC_NAME"));
			vo.setLicType(rs.getString("LIC_TYPE"));
			vo.setLicNo(rs.getString("LIC_NO"));
			vo.setLicDate(ConvertDateUtils.formatDateToString(rs.getDate("LIC_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setExpDate(ConvertDateUtils.formatDateToString(rs.getDate("EXP_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setLicDateNew(ConvertDateUtils.formatDateToString(rs.getDate("LIC_DATE_NEW"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setLicNoNew(rs.getString("LIC_NO_NEW"));

			return vo;
		}
	};

}
