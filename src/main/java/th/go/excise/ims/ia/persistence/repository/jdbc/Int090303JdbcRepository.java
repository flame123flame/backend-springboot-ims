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
import th.go.excise.ims.ia.persistence.entity.IaCheckControlRegis;
import th.go.excise.ims.ia.vo.Int0903FormVo;

@Repository
public class Int090303JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL_IA_CHECK_CONTROL_REGIS = "SELECT * FROM IA_CHECK_CONTROL_REGIS A WHERE A.OFFCODE = ? ";

	public List<IaCheckControlRegis> list(Int0903FormVo form) {
		List<IaCheckControlRegis> iaCheckControlRegis = new ArrayList<IaCheckControlRegis>();

		StringBuilder sql = new StringBuilder(SQL_IA_CHECK_CONTROL_REGIS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getOffcode());

		if (StringUtils.isNotBlank(form.getStartDate())) {
			sql.append("AND A.PAYMENT_DATE >=  ?  ");
			Date dateForm = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateForm);
		}

		if (StringUtils.isNotBlank(form.getEndDate())) {
			sql.append("AND A.PAYMENT_DATE <=  ?  ");
			Date dateTo = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateTo);
		}

		if (StringUtils.isNotBlank(form.getBudgetType())) {
			sql.append("AND A.BUDGET_TYPE = ?  ");
			params.add(form.getBudgetType());
		}

		iaCheckControlRegis = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaCheckControlRegis;
	}

	private RowMapper<IaCheckControlRegis> listRowmapper = new RowMapper<IaCheckControlRegis>() {
		@Override
		public IaCheckControlRegis mapRow(ResultSet rs, int arg1) throws SQLException {
			IaCheckControlRegis vo = new IaCheckControlRegis();
			vo.setPaymentDate(rs.getDate("PAYMENT_DATE"));
			vo.setRefPayment(rs.getString("REF_PAYMENT"));
			vo.setBankName(rs.getString("BANK_NAME"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setBudgetType(rs.getString("BUDGET_TYPE"));
			vo.setItemDesc(rs.getString("ITEM_DESC"));
			vo.setPayee(rs.getString("PAYEE"));
			vo.setOffcode(rs.getString("OFFCODE"));
			return vo;
		}
	};

	public List<Int0903FormVo> budgetTypeDropdown() {
		List<Int0903FormVo> response = new ArrayList<Int0903FormVo>();
		StringBuilder sql = new StringBuilder("SELECT DISTINCT(A.BUDGET_TYPE) AS BUDGET_TYPE " + "FROM IA_CHECK_CONTROL_REGIS A  " + "WHERE A.IS_DELETED = 'N' " + "ORDER BY A.BUDGET_TYPE DESC ");
		response = commonJdbcTemplate.query(sql.toString(), budgetYearDropdownRowmapper);
		return response;
	}

	private RowMapper<Int0903FormVo> budgetYearDropdownRowmapper = new RowMapper<Int0903FormVo>() {
		@Override
		public Int0903FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0903FormVo vo = new Int0903FormVo();
			vo.setBudgetType(rs.getString("BUDGET_TYPE"));
			return vo;
		}
	};

}
