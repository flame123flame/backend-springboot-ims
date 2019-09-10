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
import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.vo.Int091304SearchVo;

@Repository
public class IaUtilityBillJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	public List<IaUtilityBill> findQuarter(Int091304SearchVo formVo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT UBILL_TYPE, MONTH_WD_PAY, EXCISE_CODE, SUM(REQ_WD_AMT) REQ_WD_AMT FROM IA_UTILITY_BILL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		List<Object> params = new ArrayList<Object>();
		
		if (StringUtils.isNotBlank(formVo.getUbillType())) {
			sql.append("AND UBILL_TYPE = ?  ");
			params.add(formVo.getUbillType());
		}
		
		if (StringUtils.isNotBlank(formVo.getMonthWdPayFrom())) {
			sql.append("AND MONTH_WD_PAY >= ?  ");
			params.add(formVo.getMonthWdPayFrom());
		}
		
		if (StringUtils.isNotBlank(formVo.getMonthWdPayTo())) {
			sql.append("AND MONTH_WD_PAY <= ?  ");
			params.add(formVo.getMonthWdPayTo());
		}
		
		sql.append(" GROUP BY UBILL_TYPE, MONTH_WD_PAY, EXCISE_CODE ");
		sql.append(" ORDER BY UBILL_TYPE ");
		
		List<IaUtilityBill> iaCheckControlRegis = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaCheckControlRegis;
	}
	
	private RowMapper<IaUtilityBill> listRowmapper = new RowMapper<IaUtilityBill>() {
		@Override
		public IaUtilityBill mapRow(ResultSet rs, int arg1) throws SQLException {
			IaUtilityBill vo = new IaUtilityBill();	
			vo.setReqWdAmt(rs.getBigDecimal("REQ_WD_AMT"));
			vo.setMonthWdPay(rs.getString("MONTH_WD_PAY"));
			vo.setUbillType(rs.getString("UBILL_TYPE"));
			vo.setExciseCode(rs.getString("EXCISE_CODE"));

			return vo;
		}
	};
}
