package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Repository
public class Int0913JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int091301ResultSearchVo> findIaUtilityBillByCriteria(Int091301SearchVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM IA_UTILITY_BILL U WHERE U.IS_DELETED = 'N' ");
		if(vo.getUtilityBillSeq() != null) {
			sql.append(" AND UTILITY_BILL_SEQ = ? ");
			paramList.add(vo.getUtilityBillSeq());
		}
		if (StringUtils.isNotBlank(vo.getUbillType())) {
			sql.append(" AND U.UBILL_TYPE = ? ");
			paramList.add(Long.valueOf(vo.getUbillType()));
		}
		if (StringUtils.isNoneBlank(vo.getOfficeCode())) {
			sql.append(" AND U.EXCISE_CODE LIKE ? ");
			paramList.add(ExciseUtils.whereInLocalOfficeCode(vo.getOfficeCode()));
		}
		if (StringUtils.isNoneBlank(vo.getMonthWdPayFrom())) {
			sql.append(" AND U.MONTH_WD_PAY >= ? ");
			paramList.add(vo.getMonthWdPayFrom());
		}
		if (StringUtils.isNoneBlank(vo.getMonthWdPayTo())) {
			sql.append(" AND U.MONTH_WD_PAY <= ? ");
			paramList.add(vo.getMonthWdPayTo());
		}
		if(StringUtils.isNoneBlank(vo.getBudgetYear())) {
			int year = Integer.parseInt(vo.getBudgetYear());
			sql.append(" AND U.MONTH_WD_PAY >= ? ");
			paramList.add((year-1)+ProjectConstants.QUARTER.Q1[0]);
			
			sql.append(" AND U.MONTH_WD_PAY <= ? ");
			paramList.add((year)+ProjectConstants.QUARTER.Q4[2]);
		}
		
		sql.append(" ORDER BY MONTH_WD_PAY DESC, RECEIVE_INV_DATE DESC ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), int091301Mapping);
	}

	private RowMapper<Int091301ResultSearchVo> int091301Mapping = new RowMapper<Int091301ResultSearchVo>() {
		@Override
		public Int091301ResultSearchVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int091301ResultSearchVo vo = new Int091301ResultSearchVo();
			vo.setUtilityBillSeq(rs.getLong("UTILITY_BILL_SEQ"));
			vo.setExciseCode(rs.getString("EXCISE_CODE"));
			vo.setUbillType(rs.getString("UBILL_TYPE"));
			vo.setMonthWdPay(rs.getString("MONTH_WD_PAY"));
			vo.setInvoiceSeq(rs.getBigDecimal("INVOICE_SEQ"));
			vo.setInvoiceMonth(rs.getString("INVOICE_MONTH"));
			vo.setInvoiceNo(rs.getString("INVOICE_NO"));
			vo.setTelInvNumber(rs.getString("TEL_INV_NUMBER"));
			vo.setInvoiceDate(ConvertDateUtils.formatDateToString(rs.getDate("INVOICE_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setReceiveInvDate(ConvertDateUtils.formatDateToString(rs.getDate("RECEIVE_INV_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setInvoiceAmt(rs.getBigDecimal("INVOICE_AMT"));
			vo.setReqWdDate(ConvertDateUtils.formatDateToString(rs.getDate("REQ_WD_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setReqWdNo(rs.getString("REQ_WD_NO"));
			vo.setReqWdAmt(rs.getBigDecimal("REQ_WD_AMT"));
			vo.setReqTaxAmt(rs.getBigDecimal("REQ_TAX_AMT"));
			vo.setReqNetAmt(rs.getBigDecimal("REQ_NET_AMT"));
			vo.setReqPayNo(rs.getString("REQ_PAY_NO"));
			vo.setReqReceiptDate(ConvertDateUtils.formatDateToString(rs.getDate("REQ_RECEIPT_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setLatePayCause(rs.getString("LATE_PAY_CAUSE"));
			vo.setUbillRemark(rs.getString("UBILL_REMARK"));
			return vo;
		}
	};
}
