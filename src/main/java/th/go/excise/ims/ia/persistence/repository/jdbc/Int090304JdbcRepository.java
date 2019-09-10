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
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.ia.vo.Int090304Vo;
import th.go.excise.ims.ia.vo.Int0903FormVo;

@Repository
public class Int090304JdbcRepository {

	private static final Logger logger = LoggerFactory.getLogger(Int090304JdbcRepository.class);
	private final String CHECK = "เช็ค";
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private void buildSearchQuery(StringBuilder sql, List<Object> params, Int0903FormVo request) {

		sql.append(" SELECT  ps.payment_date ,ps.withdrawal_id,ps.ref_payment,ps.payee,ps.bank_name,ls.item_desc,ps.office_code,ls.budget_type,ps.amount ");
		sql.append(" FROM ia_withdrawal_persons ps ");
		sql.append(" LEFT JOIN ia_withdrawal_list ls ");
		sql.append(" ON ls.withdrawal_id = ps.withdrawal_id ");

		sql.append(" WHERE ps.is_deleted = ? ");
		if (StringUtils.isNotBlank(request.getOffcode())) {
			params.add(FLAG.N_FLAG);
		} else {
			params.add("");
		}

		sql.append(" AND ps.payment_method = ? ");
		params.add(CHECK);

		if (StringUtils.isNotBlank(request.getOffcode())) {
			sql.append(" AND ps.office_code LIKE ?");
			params.add(request.getOffcode());
		}

		if (StringUtils.isNotEmpty(request.getStartDate())) {
			sql.append(" AND TRUNC(ps.payment_date) >= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(request.getStartDate());
			params.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}

		if (StringUtils.isNotEmpty(request.getEndDate())) {
			sql.append(" AND TRUNC(ps.payment_date)<= TO_DATE(?, 'YYYYMMDD') ");
			LocalDate localDate = LocalDateUtils.thaiDateSlash2LocalDate(request.getEndDate());
			params.add(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		}

		if (StringUtils.isNotBlank(request.getBudgetType())) {
			sql.append(" AND ls.budget_type = ?");
			params.add(request.getBudgetType());
		}

	}

	public Integer countByCriteria(Int0903FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		Integer count = this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}

	public List<Int090304Vo> findByCriteria(Int0903FormVo request) {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY ps.withdrawal_persons_id ");

		List<Int090304Vo> datas = this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength()), params.toArray(), new RowMapper<Int090304Vo>() {
			@Override
			public Int090304Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int090304Vo vo = new Int090304Vo();
				vo.setPayee(rs.getString("payee"));
				vo.setRefPayment(rs.getString("ref_payment"));
				vo.setBudgetType(rs.getString("budget_type"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setAmount(rs.getBigDecimal("amount"));
				vo.setPaymentDate(rs.getDate("payment_date") != null ? ConvertDateUtils.formatDateToString(rs.getDate("payment_date"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				return vo;
			}
		});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}

	public List<Int0903FormVo> budgetTypeDropdown() {
		List<Int0903FormVo> response = new ArrayList<Int0903FormVo>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT(w.budget_type) AS budget_type  ");
		sql.append(" FROM ia_withdrawal_list w  ");
		sql.append(" WHERE w.is_deleted = 'N' ");
		sql.append(" ORDER BY w.budget_type DESC ");
		response = commonJdbcTemplate.query(sql.toString(), budgetTypeDropdownRowmapper);
		return response;
	}

	private RowMapper<Int0903FormVo> budgetTypeDropdownRowmapper = new RowMapper<Int0903FormVo>() {
		@Override
		public Int0903FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0903FormVo vo = new Int0903FormVo();
			vo.setBudgetType(rs.getString("BUDGET_TYPE"));
			return vo;
		}
	};

	public List<Int090304Vo> getDataExport(Int0903FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY ps.withdrawal_persons_id ");

		List<Int090304Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int090304Vo>() {
			@Override
			public Int090304Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int090304Vo vo = new Int090304Vo();
				vo.setPayee(rs.getString("payee"));
				vo.setRefPayment(rs.getString("ref_payment"));
				vo.setBudgetType(rs.getString("budget_type"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setAmount(rs.getBigDecimal("amount"));
				vo.setPaymentDate(rs.getDate("payment_date") != null ? ConvertDateUtils.formatDateToString(rs.getDate("payment_date"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				return vo;
			}
		});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}
}
