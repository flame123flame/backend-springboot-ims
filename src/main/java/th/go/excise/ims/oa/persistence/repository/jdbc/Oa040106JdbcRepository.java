package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.vo.Oa040106ButtonVo;

@Repository
public class Oa040106JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public Oa040106ButtonVo findButtonIdById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_PLAN_ID AS OA_PLAN_ID, ");
		sql.append(" CL.LICENSE_TYPE AS LICENSE_TYPE, ");
		sql.append(" LU.OA_ALCOHOL_ID AS OA_ALCOHOL_ID, ");
		sql.append(" CL.LICENSE_NO AS LICENSE_NO, ");
		sql.append(" LUD.OA_ALCOHOL_DTL_ID AS OA_ALCOHOL_DTL_ID, ");
		sql.append(" CL.OA_CUSLICENSE_ID AS OA_CUSLICENSE_ID ");
		sql.append(" FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_ALCOHOL LU ");
		sql.append(" ON LU.OA_PLAN_ID = LP.OA_PLAN_ID ");
		sql.append(" INNER JOIN OA_ACH_CUSTOMER_LICEN CL ");
		sql.append(" ON CL.OA_CUSLICENSE_ID = LP.LICENSE_ID ");
		sql.append(" INNER JOIN OA_ALCOHOL_DTL LUD ");
		sql.append(" ON LUD.OA_ALCOHOL_ID = LU.OA_ALCOHOL_ID ");
		sql.append(" WHERE LU.IS_DELETED = 'N' AND LP.IS_DELETED = 'N' AND LUD.IS_DELETED = 'N' AND LP.OA_LICENSE_PLAN_ID = ? ");
		params.add(id);
		Oa040106ButtonVo data = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}

	private RowMapper<Oa040106ButtonVo> dataRowmapper = new RowMapper<Oa040106ButtonVo>() {
		@Override
		public Oa040106ButtonVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa040106ButtonVo vo = new Oa040106ButtonVo();
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setOaPlanId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setOaAlcoholDtlId(rs.getBigDecimal("OA_ALCOHOL_DTL_ID"));
			vo.setOaAlcoholId(rs.getBigDecimal("OA_ALCOHOL_ID"));
			return vo;
		}
	};
	
	public List<OaAchCustomerLicenDtl> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER_LICEN_DTL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaAchCustomerLicenDtl> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OaAchCustomerLicenDtl.class));
		return lists;
	}
}
