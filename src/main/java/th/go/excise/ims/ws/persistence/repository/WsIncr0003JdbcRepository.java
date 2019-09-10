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
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.ia.vo.WsIncr0003Vo;

@Repository
public class WsIncr0003JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<WsIncr0003Vo> findByFilter(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT TRN_DATE, SUM(SUM1 + SUM2) SUM1_2, SUM(SUM4 + SUM5) SUM4_5, SUM(SUM7) SUM7, ");
		sql.append(" SUM(SUM1 + SUM2 + SUM3 + SUM4 + SUM5 + SUM6 + SUM7) SUMALL ");
		sql.append(" FROM WS_INCR0003 ");
		sql.append(" WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}

		if (StringUtils.isNotBlank(request.getIncomeCode())) {
			sql.append(" AND INC_CODE = ? ");
			params.add(request.getIncomeCode());
		}

		sql.append(" GROUP BY TRN_DATE ");
		sql.append(" ORDER BY TRN_DATE ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<WsIncr0003Vo>() {
			@Override
			public WsIncr0003Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WsIncr0003Vo vo = new WsIncr0003Vo();
				vo.setTrnDate(rs.getDate("TRN_DATE"));
				vo.setSum1Sum2(rs.getBigDecimal("SUM1_2"));
				vo.setSum4Sum5(rs.getBigDecimal("SUM4_5"));
				vo.setSum7(rs.getBigDecimal("SUM7"));
				vo.setSum4(rs.getBigDecimal("SUMALL"));
				return vo;
			}
		});
	}

	public WsIncr0003Vo summaryByRequest(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT SUM(SUM1_2) SUM1_2, SUM(SUM4_5) SUM4_5, SUM(SUM7) SUM7, SUM(SUMALL) SUMALL ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 	SELECT TRN_DATE, SUM(SUM1 + SUM2) SUM1_2, SUM(SUM4 + SUM5) SUM4_5, SUM(SUM7) SUM7, ");
		sql.append(" 		SUM(SUM1 + SUM2 + SUM3 + SUM4 + SUM5 + SUM6 + SUM7) SUMALL ");
		sql.append(" 	FROM WS_INCR0003 ");
		sql.append(" 	WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}

		if (StringUtils.isNotBlank(request.getIncomeCode())) {
			sql.append(" AND INC_CODE = ? ");
			params.add(request.getIncomeCode());
		}

		sql.append(" 	GROUP BY TRN_DATE ");
		sql.append(" 	ORDER BY TRN_DATE ");
		sql.append(" 	) ");

		return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new RowMapper<WsIncr0003Vo>() {
			@Override
			public WsIncr0003Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WsIncr0003Vo vo = new WsIncr0003Vo();
				vo.setSum1Sum2(rs.getBigDecimal("SUM1_2"));
				vo.setSum4Sum5(rs.getBigDecimal("SUM4_5"));
				vo.setSum7(rs.getBigDecimal("SUM7"));
				vo.setSum4(rs.getBigDecimal("SUMALL"));
				return vo;
			}
		});
	}
}
