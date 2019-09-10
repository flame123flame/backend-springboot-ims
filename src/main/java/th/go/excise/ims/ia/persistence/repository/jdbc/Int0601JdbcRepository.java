package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
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
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.vo.IaAuditIncD1WasteReceiptVo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Repository
public class Int0601JdbcRepository {

	private static final Logger logger = LoggerFactory.getLogger(Int0601JdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<WsIncfri8020Inc> findByCriteria(Int0601RequestVo criteria, String strOrder) {
		logger.info("findByCriteria");

		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM WS_INCFRI8020_INC WS ");
		sql.append(" WHERE WS.IS_DELETED = 'N' ");

		if (StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
			paramList.add(criteria.getOfficeReceive());
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateFrom());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateTo());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		
		if (StringUtils.isNotEmpty(criteria.getTaxCode())) {
			sql.append(" AND WS.INCOME_CODE = ? ");
			paramList.add(criteria.getTaxCode());
		}
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" ORDER BY ").append(strOrder);

		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab1RowMapper);
	}

	private RowMapper<WsIncfri8020Inc> tab1RowMapper = new RowMapper<WsIncfri8020Inc>() {
		@Override
		public WsIncfri8020Inc mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsIncfri8020Inc vo = new WsIncfri8020Inc();
			vo.setWsIncfri8020IncId(rs.getLong("WS_INCFRI8020_INC_ID"));
			vo.setReceiptDate(rs.getDate("RECEIPT_DATE"));
			vo.setDepositDate(rs.getDate("DEPOSIT_DATE"));
			vo.setSendDate(rs.getDate("SEND_DATE"));
			vo.setIncomeName(rs.getString("INCOME_NAME"));
			vo.setReceiptNo(rs.getString("RECEIPT_NO"));
			vo.setNetTaxAmt(rs.getBigDecimal("NET_TAX_AMT"));
			vo.setNetLocAmt(rs.getBigDecimal("NET_LOC_AMT"));
			vo.setLocOthAmt(rs.getBigDecimal("LOC_OTH_AMT"));
			vo.setLocExpAmt(rs.getBigDecimal("LOC_EXP_AMT"));
			vo.setSssFundAmt(rs.getBigDecimal("SSS_FUND_AMT"));
			vo.setTpbsFundAmt(rs.getBigDecimal("TPBS_FUND_AMT"));
			vo.setSportFundAmt(rs.getBigDecimal("SPORT_FUND_AMT"));
			vo.setOlderFundAmt(rs.getBigDecimal("OLDER_FUND_AMT"));
			vo.setSendAmt(rs.getBigDecimal("SEND_AMT"));
			vo.setStampAmt(rs.getBigDecimal("STAMP_AMT"));
			vo.setCustomAmt(rs.getBigDecimal("CUSTOM_AMT"));
			vo.setTrnDate(rs.getDate("TRN_DATE"));
			vo.setOfficeReceive(rs.getString("OFFICE_RECEIVE"));
			vo.setIncomeCode(rs.getString("INCOME_CODE"));
			vo.setReceiptNoSssFund(rs.getString("RECEIPT_NO_SSS_FUND"));
			vo.setReceiptNoTpbsFund(rs.getString("RECEIPT_NO_TPBS_FUND"));
			vo.setReceiptNoSportFund(rs.getString("RECEIPT_NO_SPORT_FUND"));
			vo.setReceiptNoOlderFund(rs.getString("RECEIPT_NO_OLDER_FUND"));
			vo.setPinNidId(rs.getString("PIN_NID_ID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusName(rs.getString("CUS_NAME"));
			vo.setFacName(rs.getString("FAC_NAME"));
			vo.setIncCtlNo(rs.getString("INC_CTL_NO"));
			vo.setOfflineStatus(rs.getString("OFFLINE_STATUS"));
			return vo;
		}
	};

	public List<IaAuditIncD2Vo> findDataTab2(Int0601RequestVo criteria) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT WS.RECEIPT_DATE RECEIPT_DATE, SUM(WS.NET_TAX_AMT) NET_TAX_AMT, COUNT(1) PRINT_PER_DAY ");
		sql.append(" FROM WS_INCFRI8020_INC WS ");
		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
			paramList.add(ExciseUtils.whereInLocalOfficeCode(criteria.getOfficeReceive()));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateFrom());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateTo());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" GROUP BY WS.RECEIPT_DATE ");
		sql.append(" ORDER BY WS.RECEIPT_DATE ");
		
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab2RowMapper);
	}

	private RowMapper<IaAuditIncD2Vo> tab2RowMapper = new RowMapper<IaAuditIncD2Vo>() {
		@Override
		public IaAuditIncD2Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaAuditIncD2Vo vo = new IaAuditIncD2Vo();
			if (rs.getDate("RECEIPT_DATE") != null) {
				vo.setReceiptDate(ConvertDateUtils.formatDateToString(rs.getDate("RECEIPT_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			}
			vo.setAmount(rs.getBigDecimal("NET_TAX_AMT"));
			vo.setPrintPerDay(rs.getBigDecimal("PRINT_PER_DAY"));
			return vo;
		}
	};

	public List<IaAuditIncD3Vo> findDataTab3(Int0601RequestVo criteria) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" WS.INCOME_CODE TAX_CODE, ");
		sql.append(" WS.INCOME_NAME TAX_NAME, ");
		sql.append(" SUM(WS.NET_TAX_AMT) AMOUNT, ");
		sql.append(" COUNT(1) COUNT_RECEIPT ");
		sql.append(" FROM ");
		sql.append(" WS_INCFRI8020_INC WS ");

		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
			paramList.add(ExciseUtils.whereInLocalOfficeCode(criteria.getOfficeReceive()));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateFrom());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
			sql.append(" AND TRUNC(WS.RECEIPT_DATE) <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateTo());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" GROUP BY ");
		sql.append(" WS.INCOME_CODE, ");
		sql.append(" WS.INCOME_NAME ");
		sql.append(" ORDER BY ");
		sql.append(" WS.INCOME_CODE ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab3RowMapper);
	}

	private RowMapper<IaAuditIncD3Vo> tab3RowMapper = new RowMapper<IaAuditIncD3Vo>() {
		@Override
		public IaAuditIncD3Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaAuditIncD3Vo vo = new IaAuditIncD3Vo();
			vo.setTaxCode(rs.getString("TAX_CODE"));
			vo.setTaxName(rs.getString("TAX_NAME"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setCountReceipt(rs.getBigDecimal("COUNT_RECEIPT"));
			return vo;
		}
	};
	
	private void buildFindWasteReceiptQuery(StringBuilder sql, List<Object> paramList, Int0601RequestVo criteria) {
		sql.append(" SELECT * ");
		sql.append(" FROM WS_INCR0004 ");
		sql.append(" WHERE OFFCODE = ? ");
		paramList.add(criteria.getOfficeReceive());
		
		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
			sql.append("   AND TRUNC(TRN_DATE) >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateFrom());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		
		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
			sql.append("   AND TRUNC(TRN_DATE) <= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(criteria.getReceiptDateTo());
			paramList.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
	}
	
	public List<IaAuditIncD1WasteReceiptVo> findWasteReceipt(Int0601RequestVo criteria) {
		logger.info("findWasteReceipt officeReceive={}, dateFrom={}, dateTo={}",
			criteria.getOfficeReceive(), criteria.getReceiptDateFrom(), criteria.getReceiptDateTo());
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		buildFindWasteReceiptQuery(sql, paramList, criteria);
		sql.append(" ORDER BY TRN_DATE, INCCTL_NO, INCCTL_SEQ ");
		
		List<IaAuditIncD1WasteReceiptVo> voList = commonJdbcTemplate.query(
			OracleUtils.limitForDatable(sql.toString(), criteria.getStart(), criteria.getLength()),
			paramList.toArray(),
			new RowMapper<IaAuditIncD1WasteReceiptVo>() {
				@Override
				public IaAuditIncD1WasteReceiptVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					IaAuditIncD1WasteReceiptVo vo = new IaAuditIncD1WasteReceiptVo();
					vo.setTrnDate(ThaiBuddhistDate.from(LocalDateConverter.convertToEntityAttribute(rs.getDate("TRN_DATE"))).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					vo.setIncctlNo(rs.getString("INCCTL_NO"));
					vo.setReceiptNo(rs.getString("RECEIPT_NO"));
					vo.setReceiptNoNew(rs.getString("RECEIPT_NO_NEW"));
					vo.setPaidAmt( rs.getBigDecimal("PAID_AMT"));
					vo.setReasonCode(rs.getString("REASON_CODE"));
					vo.setReasonDesc(rs.getString("REASON_DESC"));
					return vo;
				}
			}
		);
		
		return voList;
	}
	
	public Long countWasteReceipt(Int0601RequestVo criteria) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildFindWasteReceiptQuery(sql, params, criteria);
		
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

}
