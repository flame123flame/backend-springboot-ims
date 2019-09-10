package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.vo.Oa0106FormVo;
import th.go.excise.ims.oa.vo.Oa0106Vo;

@Repository
public class Oa0106JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0106Vo> getData(Oa0106FormVo request, String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.* FROM OA_CUSTOMER_LICEN CL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.STATUS = '6' AND LP.OFFICE_CODE LIKE  ?  ");
		sql.append(" ORDER BY START_DATE DESC");
		params.add(officeCode + "%");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		List<Oa0106Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(), dataRowmapper);

		return datas;
	}

	public Integer countData(Oa0106FormVo request, String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.* FROM OA_CUSTOMER_LICEN CL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.STATUS = '6' AND LP.OFFICE_CODE LIKE  ?  ");
		sql.append(" ORDER BY START_DATE DESC");
		params.add(officeCode+ "%");
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public List<Oa0106Vo> getDataHyd(Oa0106FormVo request, String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.* FROM OA_HYD_CUSTOMER_LICEN CL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.STATUS = '6' AND LP.OFFICE_CODE LIKE  ?  ");
		sql.append(" ORDER BY START_DATE DESC");
		params.add(officeCode + "%");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		List<Oa0106Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(), dataRowmapper);

		return datas;
	}

	public Integer countDataHyd(Oa0106FormVo request, String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.* FROM OA_HYD_CUSTOMER_LICEN CL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE LP.STATUS = '6' AND LP.OFFICE_CODE LIKE  ?  ");
		sql.append(" ORDER BY START_DATE DESC");
		params.add(officeCode + "%");
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public Oa0106Vo getCustomerLicenseById(String licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_HYD_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N'  AND OA_CUSLICENSE_ID = ? ");

		params.add(licenseId);

		Oa0106Vo data = this.commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);

		return data;
	}

	private RowMapper<Oa0106Vo> dataRowmapper = new RowMapper<Oa0106Vo>() {
		@Override
		public Oa0106Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa0106Vo vo = new Oa0106Vo();
			vo.setAddress(rs.getString("ADDRESS"));
			vo.setOaCusLicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setApprove(rs.getString("APPROVE"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setIdentityType(rs.getString("IDENTIFY_TYPE"));
			vo.setStartDate(rs.getDate("START_DATE"));
			vo.setEndDate(rs.getDate("END_DATE"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setIdentifyNo(rs.getString("IDENTIFY_NO"));
			vo.setWarehouseAddress(rs.getString("WAREHOUSE_ADDRESS"));
			vo.setTelephone(rs.getString("MOBILE"));
			return vo;
		}
	};

}
