package th.go.excise.ims.ws.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int0609TableVo;
import th.go.excise.ims.ia.vo.SearchVo;

@Repository
public class WsGfr01051JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int0609TableVo> findByFilter(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT TRN_DATE, GF_DATE, OFFCODE, ACTCOST_CENT, GF_REF_NO, SUM(CNT) CNT, SUM(TOTAL_AMT) TOTAL_AMT, SUM(TOTAL_SEND_AMT) TOTAL_SEND_AMT ");
		sql.append(" FROM WS_GFR0105_1 ");
		sql.append(" WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}

		sql.append(" GROUP BY TRN_DATE, GF_DATE, OFFCODE, ACTCOST_CENT, GF_REF_NO ");
		sql.append(" ORDER BY TRN_DATE ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0609TableVo>() {
			@Override
			public Int0609TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0609TableVo vo = new Int0609TableVo();
				vo.setTrnDate(rs.getDate("TRN_DATE"));
				vo.setGfDate(rs.getDate("GF_DATE"));
				vo.setOffcode(rs.getString("OFFCODE"));
				vo.setActcostCent(rs.getString("ACTCOST_CENT"));
				vo.setGfRefNo(rs.getString("GF_REF_NO"));
				vo.setCnt(rs.getBigDecimal("CNT"));
				vo.setTotalAmt(rs.getBigDecimal("TOTAL_AMT"));
				vo.setTotalSendAmt(rs.getBigDecimal("TOTAL_SEND_AMT"));
				return vo;
			}
		});
	}

	public Int0609TableVo summaryByRequest(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT SUM(CNT) CNT, SUM(TOTAL_AMT) TOTAL_AMT, SUM(TOTAL_SEND_AMT) TOTAL_SEND_AMT ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 	SELECT TRN_DATE, GF_DATE, OFFCODE, ACTCOST_CENT, GF_REF_NO, SUM(CNT) CNT, SUM(TOTAL_AMT) TOTAL_AMT, SUM(TOTAL_SEND_AMT) TOTAL_SEND_AMT ");
		sql.append(" 	FROM WS_GFR0105_1 ");
		sql.append(" 	WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}

		sql.append(" 	GROUP BY TRN_DATE, GF_DATE, OFFCODE, ACTCOST_CENT, GF_REF_NO ");
		sql.append(" 	ORDER BY TRN_DATE ");
		sql.append(" 	) ");

		return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new RowMapper<Int0609TableVo>() {
			@Override
			public Int0609TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0609TableVo vo = new Int0609TableVo();
				vo.setCnt(rs.getBigDecimal("CNT"));
				vo.setTotalAmt(rs.getBigDecimal("TOTAL_AMT"));
//				vo.setSum1Sum2(rs.getBigDecimal("TOTAL_SUM1_2"));
//				vo.setSum4Sum5(rs.getBigDecimal("TOTAL_SUM4_5"));
//				vo.setSum7(rs.getBigDecimal("TOTAL_SUM7"));
				vo.setTotalSendAmt(rs.getBigDecimal("TOTAL_SEND_AMT"));
				return vo;
			}
		});
	}
}
