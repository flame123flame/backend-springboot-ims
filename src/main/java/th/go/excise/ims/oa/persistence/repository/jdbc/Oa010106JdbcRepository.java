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
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.vo.Oa010106ButtonVo;

@Repository
public class Oa010106JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public Oa010106ButtonVo findButtonIdById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_PLAN_ID      AS OA_PLAN_ID, ");
		sql.append("   CL.LICENSE_TYPE         AS LICENSE_TYPE, ");
		sql.append("   CL.IDENTIFY_TYPE        AS IDENTIFY_TYPE, ");
		sql.append("   CL.OLD_CUSTOMER         AS OLD_CUSTOMER, ");
		sql.append("   HY.OA_HYDROCARB_ID      AS OA_HYDROCARB_ID, ");
		sql.append("   CL.LICENSE_NO           AS LICENSE_NO, ");
		sql.append("   HYD.OA_HYDROCARB_DTL_ID AS OA_HYDROCARB_DTL_ID, ");
		sql.append("   CL.OA_CUSLICENSE_ID     AS OA_CUSLICENSE_ID ");
		sql.append(" FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_HYDROCARB HY ");
		sql.append(" ON (HY.OA_PLAN_ID = LP.OA_PLAN_ID AND HY.LICENSE_ID = LP.LICENSE_ID) ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN CL ");
		sql.append(" ON CL.OA_CUSLICENSE_ID = LP.LICENSE_ID ");
		sql.append(" INNER JOIN OA_HYDROCARB_DTL HYD ");
		sql.append(" ON HYD.OA_HYDROCARB_ID    = HY.OA_HYDROCARB_ID ");
		sql.append(" WHERE HY.IS_DELETED       = 'N' ");
		sql.append(" AND LP.IS_DELETED         = 'N' ");
		sql.append(" AND HYD.IS_DELETED        = 'N' ");
		sql.append(" AND LP.OA_LICENSE_PLAN_ID = ? ");
		params.add(id);
		Oa010106ButtonVo data = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}

	private RowMapper<Oa010106ButtonVo> dataRowmapper = new RowMapper<Oa010106ButtonVo>() {
		@Override
		public Oa010106ButtonVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa010106ButtonVo vo = new Oa010106ButtonVo();
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setOaPlanId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setOaHydrocarbDtlId(rs.getBigDecimal("OA_HYDROCARB_DTL_ID"));
			vo.setOaHydrocarbId(rs.getBigDecimal("OA_HYDROCARB_ID"));
			vo.setOldCustomer(rs.getString("OLD_CUSTOMER"));
			return vo;
		}
	};
	
	public List<OaHydCustomerLicenDtl> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_HYD_CUSTOMER_LICEN_DTL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaHydCustomerLicenDtl> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OaHydCustomerLicenDtl.class));
		return lists;
	}
}
