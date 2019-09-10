package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ia.persistence.entity.IaCheckStatisticPaint;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int060501Vo;
import th.go.excise.ims.ia.vo.Int0613FormVo;
import th.go.excise.ims.ia.vo.Int0613Vo;

@Repository
public class IaCheckStatisticPaintJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int0613Vo> getDataFilter(Int0613FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CHECK_STATISTIC_PAINT WHERE 1=1 AND IS_DELETED='N' ");

//		if (StringUtils.isNotBlank(request.getBudgetYear())) {
//			sql.append(" AND BUDGET_YEAR = ? ");
//			params.add(request.getBudgetYear());
//		}

		sql.append(" ORDER BY CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0613Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(Int0613Vo.class));

		return datas;
	}

	public Integer countDatafilter(Int0613FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CHECK_STATISTIC_PAINT WHERE IS_DELETED='N' ");

		sql.append(" ORDER BY CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public void batchInsert(List<IaCheckStatisticPaint> iaCheckTaxReceiptList) {
		String sql = SqlGeneratorUtils.genSqlInsert("IA_CHECK_STATISTIC_PAINT",
				Arrays.asList("ID", "OFFCODE", "LIC_TYPE", "LIC_CODE", "LIC_NO", "LIC_NAME", "LIC_FEE", "LIC_INTERIOR",
						"LIC_PRICE", "LIC_DATE", "START_DATE", "EXP_DATE", "SEND_DATE", "PRINT_COUNT", "NID",
						"NEWREG_ID", "CUS_FULL_NAME", "FAC_FULL_NAME"),
				"IA_CHECK_STATISTIC_PAINT_SEQ");

		commonJdbcTemplate.batchUpdate(sql, iaCheckTaxReceiptList, 1000,
				new ParameterizedPreparedStatementSetter<IaCheckStatisticPaint>() {
					public void setValues(PreparedStatement ps, IaCheckStatisticPaint iaCheckTaxReceipt)
							throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						paramList.add(iaCheckTaxReceipt.getOffcode());
						paramList.add(iaCheckTaxReceipt.getLicType());
						paramList.add(iaCheckTaxReceipt.getLicCode());
						paramList.add(iaCheckTaxReceipt.getLicNo());
						paramList.add(iaCheckTaxReceipt.getLicName());
						paramList.add(iaCheckTaxReceipt.getLicFee());
						paramList.add(iaCheckTaxReceipt.getLicInterior());
						paramList.add(iaCheckTaxReceipt.getLicPrice());
						paramList.add(iaCheckTaxReceipt.getLicDate());
						paramList.add(iaCheckTaxReceipt.getStartDate());
						paramList.add(iaCheckTaxReceipt.getExpDate());
						paramList.add(iaCheckTaxReceipt.getSendDate());
						paramList.add(iaCheckTaxReceipt.getPrintCount());
						paramList.add(iaCheckTaxReceipt.getNid());
						paramList.add(iaCheckTaxReceipt.getNewregId());
						paramList.add(iaCheckTaxReceipt.getCusFullName());
						paramList.add(iaCheckTaxReceipt.getFacFullName());

//						paramList.add(worksheetDtl.getAnalysisNumber());
//						paramList.add(worksheetDtl.getNewRegId());
//
//						paramList.add(worksheetDtl.getSumTaxAmtG1());
//						paramList.add(worksheetDtl.getSumTaxAmtG2());
//						paramList.add(worksheetDtl.getTaxAmtChnPnt());
//						paramList.add(worksheetDtl.getTaxMonthNo());
//						paramList.add(worksheetDtl.getTaxAuditLast3());
//						paramList.add(worksheetDtl.getTaxAuditLast2());

						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});
	}
}
