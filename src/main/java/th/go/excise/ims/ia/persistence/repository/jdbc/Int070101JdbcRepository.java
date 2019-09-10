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
import th.go.excise.ims.ia.persistence.entity.IaCheckStamp;
import th.go.excise.ims.ia.vo.Int070101FormVo;

@Repository
public class Int070101JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	private final String SQL_IA_RISK_FACTORS = " SELECT * FROM IA_CHECK_STAMP A WHERE OFF_CODE = ?  ";

	public List<IaCheckStamp> list(Int070101FormVo form) {
		List<IaCheckStamp> iaCheckStampList = new ArrayList<IaCheckStamp>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getOffCode());
		
		if (StringUtils.isNotBlank(form.getStartDate())) {
			sql.append("AND A.DATE_REC_PAY_RE >=  ?  ");
			Date dateForm = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateForm);
		} 
		
		if (StringUtils.isNotBlank(form.getEndDate())) {
			sql.append("AND  A.DATE_REC_PAY_RE <=  ?  ");
			Date dateTo = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateTo);
		}
		
		if (StringUtils.isNotBlank(form.getStatus())) {
			sql.append("AND A.STATUS_REC_PAY = ?  ");
			params.add(form.getStatus());
		}
		
		iaCheckStampList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaCheckStampList;
	}
	
	private RowMapper<IaCheckStamp> listRowmapper = new RowMapper<IaCheckStamp>() {
		@Override
		public IaCheckStamp mapRow(ResultSet rs, int arg1) throws SQLException {
			IaCheckStamp vo = new IaCheckStamp();	
			vo.setOffCode(rs.getString("OFF_CODE"));
			vo.setDateRecPayRe(rs.getDate("DATE_REC_PAY_RE"));
			vo.setStatusRecPay(rs.getString("STATUS_REC_PAY"));
			vo.setAgenAcceptPayStamps(rs.getString("AGEN_ACCEPT_PAY_STAMPS"));
			vo.setBookNumRequestStamp(rs.getString("BOOK_NUM_REQUEST_STAMP"));
			vo.setDateRequestStamp(rs.getDate("DATE_REQUEST_STAMP"));
			vo.setBookNumSendStamps(rs.getString("BOOK_NUM_SEND_STAMPS"));
			vo.setDateSendStamps(rs.getDate("DATE_SEND_STAMPS"));
			vo.setLeafNum5(rs.getString("LEAF_NUM5"));
			vo.setDownDate(rs.getDate("DOWN_DATE"));
			vo.setCountDate(rs.getDate("COUNT_DATE"));
			vo.setReviewer1(rs.getString("REVIEWER1"));
			vo.setReviewer2(rs.getString("REVIEWER2"));
			vo.setReviewer3(rs.getString("REVIEWER3"));
			vo.setStampTypePacksize(rs.getString("STAMP_TYPE_PACKSIZE"));
			vo.setNumBook(rs.getBigDecimal("NUM_BOOK"));
			vo.setNumHoroscope(rs.getBigDecimal("NUM_HOROSCOPE"));
			vo.setPrintBahtPerHor(rs.getBigDecimal("PRINT_BAHT_PER_HOR"));
			vo.setIncludPrintFeeB(rs.getBigDecimal("INCLUD_PRINT_FEE_B"));
			vo.setTaxBaht(rs.getBigDecimal("TAX_BAHT"));
			vo.setStampCodeStart(rs.getString("STAMP_CODE_START"));
			vo.setStampCodeEnd(rs.getString("STAMP_CODE_END"));
			vo.setOffName(rs.getString("OFF_NAME"));
			vo.setOffShortName(rs.getString("OFF_SHORT_NAME"));
			return vo;
		}
	};
}
