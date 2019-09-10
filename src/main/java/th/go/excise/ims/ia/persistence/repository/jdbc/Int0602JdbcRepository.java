package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab2Vo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Repository
public class Int0602JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

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
			sql.append(" AND WS.LIC_DATE >= ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(date);
		}
		if (StringUtils.isNotBlank(vo.getLicDateTo())) {
			sql.append(" AND WS.LIC_DATE <= ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(date);
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
	
	
	public List<Int0602ResultTab2Vo> findTab2Criteria(Int0602FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT WS.INC_CODE , WS.LIC_NAME , SUM(WS.LIC_PRICE) LIC_PRICE, COUNT(1) LIC_COUNT FROM WS_LICFRI6010 WS ");
		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'  ");
		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND WS.OFFCODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}
		if (StringUtils.isNotBlank(vo.getLicDateFrom())) {
			sql.append(" AND WS.LIC_DATE >= ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(date);
		}
		if (StringUtils.isNotBlank(vo.getLicDateTo())) {
			sql.append(" AND WS.LIC_DATE <= ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(date);
		}
		sql.append(" GROUP BY WS.INC_CODE , WS.LIC_NAME ");
		sql.append(" ORDER BY COUNT(1) DESC ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab2RowMapper);

	}
	
	private RowMapper<Int0602ResultTab2Vo> tab2RowMapper = new RowMapper<Int0602ResultTab2Vo>() {
		@Override
		public Int0602ResultTab2Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int0602ResultTab2Vo vo = new Int0602ResultTab2Vo();
			vo.setTaxCode(rs.getString("INC_CODE"));
			vo.setLicName(rs.getString("LIC_NAME"));
			vo.setLicCount(rs.getLong("LIC_COUNT"));
			vo.setLicPrice(rs.getBigDecimal("LIC_PRICE"));
			
			return vo;
		}
	};

}
