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

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int060501FormVo;
import th.go.excise.ims.ia.vo.Int060501Vo;

@Repository
public class IaCheckTaxReceiptJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int060501Vo> fillterDate(Int060501FormVo res) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT * FROM IA_CHECK_TAX_RECEIPT WHERE IS_DELETED='N' ");

		if (StringUtils.isNoneEmpty(res.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(res.getOfficeCode());
		}
//		if (StringUtils.isNoneEmpty(res.getStartDate())) {
//			sql.append(" AND RECEIPT_DATE >= TO_DATE(?, 'dd/mm/yyyy') ");
//			Date startDate = ConvertDateUtils.parseStringToDate(res.getStartDate(), ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_TH);
//
//			params.add(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_EN));
//		}
//		if (StringUtils.isNoneEmpty(res.getEndDate())) {
//			sql.append(" AND RECEIPT_DATE <=  TO_DATE(?, 'dd/mm/yyyy') ");
//			Date endDate = ConvertDateUtils.parseStringToDate(res.getEndDate(), ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_TH);
//			params.add(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_EN));
//		}
		if (StringUtils.isNoneEmpty(res.getStartDate())) {
			sql.append(" AND TO_CHAR(RECEIPT_DATE, 'YYYYMMDD') >= ? ");
			Date startDate = ConvertDateUtils.parseStringToDate(res.getStartDate(), ConvertDateUtils.DD_MM_YYYY);
			params.add(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.YYYYMMDD,
					ConvertDateUtils.LOCAL_EN));
		}
		if (StringUtils.isNoneEmpty(res.getEndDate())) {
			sql.append(" AND TO_CHAR(RECEIPT_DATE, 'YYYYMMDD') <= ? ");
			Date endDate = ConvertDateUtils.parseStringToDate(res.getEndDate(), ConvertDateUtils.DD_MM_YYYY);
			params.add(
					ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		}
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		List<Int060501Vo> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(),
//				new BeanPropertyRowMapper(Int060501Vo.class));
		List<Int060501Vo> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return dataRes;
	}

	private RowMapper<Int060501Vo> listRowmapper = new RowMapper<Int060501Vo>() {
		@Override
		public Int060501Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int060501Vo vo = new Int060501Vo();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setTaxReceiptId(rs.getBigDecimal("TAX_RECEIPT_ID"));
			vo.setReceiptDate(rs.getDate("RECEIPT_DATE"));
			vo.setTrnDate(rs.getDate("TRN_DATE"));
			vo.setOfficeReceive(rs.getString("OFFICE_RECEIVE"));
			vo.setDepositDate(rs.getDate("DEPOSIT_DATE"));
			vo.setSendDate(rs.getDate("SEND_DATE"));
			vo.setIncomeCode(rs.getString("INCOME_CODE"));
			vo.setIncomeName(rs.getString("INCOME_NAME"));
			vo.setReceiptNo(rs.getString("RECEIPT_NO"));
			vo.setNettaxAmount(rs.getBigDecimal("NETTAX_AMOUNT"));
			vo.setNetLocAmount(rs.getBigDecimal("NET_LOC_AMOUNT"));
			vo.setLocOthAmount(rs.getBigDecimal("LOC_OTH_AMOUNT"));
			vo.setLocExpAmount(rs.getBigDecimal("LOC_EXP_AMOUNT"));
			vo.setReceiptNoOlderFund(rs.getString("RECEIPT_NO_OLDER_FUND"));
			vo.setOlderFundAmount(rs.getBigDecimal("OLDER_FUND_AMOUNT"));
			vo.setReceiptNoTpbsFund(rs.getString("RECEIPT_NO_TPBS_FUND"));
			vo.setTpbsFundAmount(rs.getBigDecimal("TPBS_FUND_AMOUNT"));
			vo.setReceiptNoSssFund(rs.getString("RECEIPT_NO_SSS_FUND"));
			vo.setSssFundAmount(rs.getBigDecimal("SSS_FUND_AMOUNT"));
			vo.setReceiptNoSportFund(rs.getString("RECEIPT_NO_SPORT_FUND"));
			vo.setSportFundAmount(rs.getBigDecimal("SPORT_FUND_AMOUNT"));
			vo.setSendAmount(rs.getBigDecimal("SEND_AMOUNT"));
			vo.setStampAmount(rs.getBigDecimal("STAMP_AMOUNT"));
			vo.setCustomAmount(rs.getBigDecimal("CUSTOM_AMOUNT"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setDateType(rs.getString("DATE_TYPE"));
			vo.setCheckedAmount(rs.getBigDecimal("CHECKED_AMOUNT"));
			vo.setTaxPrintNo(rs.getString("TAX_PRINT_NO"));
			vo.setPinNidId(rs.getString("PIN_NID_ID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusName(rs.getString("CUS_NAME"));
			vo.setFacName(rs.getString("FAC_NAME"));
			return vo;
		}
	};
}
