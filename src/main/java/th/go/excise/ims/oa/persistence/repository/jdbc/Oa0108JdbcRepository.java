package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa010801ApproveVo;
import th.go.excise.ims.oa.vo.Oa010801CheckerVo;
import th.go.excise.ims.oa.vo.Oa0108ApproveVo;
import th.go.excise.ims.oa.vo.Oa0108Vo;

@Repository
public class Oa0108JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0108Vo> findByBudgetYear(String budgetYear, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT O.OA_PLAN_ID AS OA_PLAN_ID, ");
		sql.append(" O.AUDIT_START AS AUDIT_START, ");
		sql.append(" O.AUDIT_END AS AUDIT_END, ");
		sql.append(" O.FISCOL_YEAR AS FISCOL_YEAR ");
		sql.append(" FROM OA_PLAN O ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN P ");
		sql.append(" ON P.OA_PLAN_ID = O.OA_PLAN_ID ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN H ");
		sql.append(" ON H.OA_CUSLICENSE_ID = P.LICENSE_ID ");
		sql.append(" WHERE O.IS_DELETED = 'N' AND O.STATUS = 2 "); // WAITING FOR APPROVE
		if (StringUtils.isNotBlank(budgetYear)) {
			params.add(budgetYear);
			sql.append(" AND O.FISCOL_YEAR = ? ");
		}
		if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
			params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
			sql.append(" AND O.OFFICE_CODE LIKE ? ");
		}
		sql.append(" GROUP BY O.OA_PLAN_ID, O.AUDIT_START, O.AUDIT_END, O.FISCOL_YEAR ");
		List<Oa0108Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}

	private RowMapper<Oa0108Vo> dataRowmapper = new RowMapper<Oa0108Vo>() {
		@Override
		public Oa0108Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa0108Vo vo = new Oa0108Vo();
			vo.setId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setAuditStart(rs.getDate("AUDIT_START"));
			vo.setAuditEnd(rs.getDate("AUDIT_END"));
			vo.setBudgetYear(rs.getString("FISCOL_YEAR"));
			return vo;
		}
	};

	public List<String> findByPlanId(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.COMPANY_NAME AS COMPANY_NAME FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN CL ");
		sql.append(" ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.IS_DELETED = 'N' AND CL.IS_DELETED = 'N' ");
		sql.append(" AND LP.OA_PLAN_ID = ? ");
		params.add(planId);
		List<String> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), stringRowmapper);
		return datas;
	}

	private RowMapper<String> stringRowmapper = new RowMapper<String>() {
		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("COMPANY_NAME");
		}
	};

	public List<Oa0108ApproveVo> findApproveByBudgetYear(String budgetYear, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT O.OA_PLAN_ID AS OA_PLAN_ID, ");
		sql.append(" O.AUDIT_START AS AUDIT_START, ");
		sql.append(" O.AUDIT_END AS AUDIT_END, ");
		sql.append(" O.FISCOL_YEAR AS FISCOL_YEAR, ");
		sql.append(" O.OFFICE_CODE AS OFFICE_CODE, ");
		sql.append(" O.STATUS AS STATUS ");
		sql.append(" FROM OA_PLAN O ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN P ");
		sql.append(" ON P.OA_PLAN_ID = O.OA_PLAN_ID ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN H ");
		sql.append(" ON H.OA_CUSLICENSE_ID = P.LICENSE_ID ");
		sql.append(" WHERE O.IS_DELETED = 'N' AND O.STATUS = 3 "); // APPROVED
		if (StringUtils.isNotBlank(budgetYear)) {
			params.add(budgetYear);
			sql.append(" AND O.FISCOL_YEAR = ? ");
		}
		if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
			params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
			sql.append(" AND O.OFFICE_CODE LIKE ? ");
		}
		sql.append(" GROUP BY O.OA_PLAN_ID, O.AUDIT_START, O.AUDIT_END, O.FISCOL_YEAR, O.OFFICE_CODE, O.STATUS ");
		List<Oa0108ApproveVo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), dataApproveRowmapper);
		return datas;
	}
	
	private RowMapper<Oa0108ApproveVo> dataApproveRowmapper = new RowMapper<Oa0108ApproveVo>() {
		@Override
		public Oa0108ApproveVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa0108ApproveVo vo = new Oa0108ApproveVo();
			vo.setId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setAuditStart(rs.getDate("AUDIT_START"));
			vo.setAuditEnd(rs.getDate("AUDIT_END"));
			vo.setBudgetYear(rs.getString("FISCOL_YEAR"));
			vo.setSectorName(rs.getString("OFFICE_CODE"));
			vo.setStatus(rs.getString("STATUS"));
			return vo;
		}
	};
	
	public List<Oa010801ApproveVo> findApproveList(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.COMPANY_NAME AS COMPANY_NAME, CL.OFF_CODE AS OFF_CODE, ");
		sql.append(" CL.LICENSE_TYPE AS LICENSE_TYPE ");
		sql.append(" FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN CL ");
		sql.append(" ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.IS_DELETED = 'N' AND CL.IS_DELETED = 'N' ");
		sql.append(" AND LP.OA_PLAN_ID = ? ");
		params.add(planId);
		List<Oa010801ApproveVo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), approveRowmapper);
		return datas;
	}

	private RowMapper<Oa010801ApproveVo> approveRowmapper = new RowMapper<Oa010801ApproveVo>() {
		@Override
		public Oa010801ApproveVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa010801ApproveVo vo = new Oa010801ApproveVo();
			vo.setSectorName(rs.getString("OFF_CODE"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			return vo;
		}
	};
	
	public List<Oa010801CheckerVo> findChecker(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT WU.USER_THAI_NAME AS USER_THAI_NAME, ");
		sql.append("   WU.USER_THAI_SURNAME   AS USER_THAI_SURNAME, ");
		sql.append("   WU.TITLE AS TITLE ");
		sql.append(" FROM OA_PERSON_AUDIT_PLAN PP ");
		sql.append(" INNER JOIN WS_USER WU ");
		sql.append(" ON WU.WS_USER_ID    = PP.OA_PERSON_ID ");
		sql.append(" WHERE PP.OA_PLAN_ID = ? AND PP.IS_DELETED = 'N' AND WU.IS_DELETED = 'N' ");
		params.add(planId);
		List<Oa010801CheckerVo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), checkerRowmapper);
		return datas;
	}
	
	private RowMapper<Oa010801CheckerVo> checkerRowmapper = new RowMapper<Oa010801CheckerVo>() {
		@Override
		public Oa010801CheckerVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa010801CheckerVo vo = new Oa010801CheckerVo();
			vo.setName(rs.getString("USER_THAI_NAME") + " " + rs.getString("USER_THAI_SURNAME"));
			vo.setPosition(rs.getString("TITLE"));
			return vo;
		}
	};
}
