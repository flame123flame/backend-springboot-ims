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
import th.go.excise.ims.oa.vo.Oa020106ButtonVo;

@Repository
public class Oa020106JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public Oa020106ButtonVo findButtonIdById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_PLAN_ID AS OA_PLAN_ID, ");
		sql.append(" CL.LICENSE_TYPE AS LICENSE_TYPE, ");
		sql.append(" CL.IDENTIFY_TYPE AS IDENTIFY_TYPE, ");
		sql.append(" CL.OLD_CUSTOMER AS OLD_CUSTOMER, ");
		sql.append(" LU.OA_LUBRICANTS_ID AS OA_LUBRICANTS_ID, ");
		sql.append(" CL.LICENSE_NO AS LICENSE_NO, ");
		sql.append(" LUD.OA_LUBRICANTS_DTL_ID AS OA_LUBRICANTS_DTL_ID, ");
		sql.append(" CL.OA_CUSLICENSE_ID AS OA_CUSLICENSE_ID ");
		sql.append(" FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_LUBRICANTS LU ");
		sql.append(" ON (LU.OA_PLAN_ID = LP.OA_PLAN_ID AND LU.LICENSE_ID = LP.LICENSE_ID) ");
		sql.append(" INNER JOIN OA_CUSTOMER_LICEN CL ");
		sql.append(" ON CL.OA_CUSLICENSE_ID = LP.LICENSE_ID ");
		sql.append(" INNER JOIN OA_LUBRICANTS_DTL LUD ");
		sql.append(" ON LUD.OA_LUBRICANTS_ID = LU.OA_LUBRICANTS_ID ");
		sql.append(" WHERE LU.IS_DELETED = 'N' AND LP.IS_DELETED = 'N' AND LUD.IS_DELETED = 'N' AND LP.OA_LICENSE_PLAN_ID = ? ");
		params.add(id);
		Oa020106ButtonVo data = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}

	private RowMapper<Oa020106ButtonVo> dataRowmapper = new RowMapper<Oa020106ButtonVo>() {
		@Override
		public Oa020106ButtonVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa020106ButtonVo vo = new Oa020106ButtonVo();
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setOaPlanId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setOaLubricantsDtlId(rs.getBigDecimal("OA_LUBRICANTS_DTL_ID"));
			vo.setOaLubricantsId(rs.getBigDecimal("OA_LUBRICANTS_ID"));
			vo.setIdentifyType(rs.getString("IDENTIFY_TYPE"));
			vo.setOldCustomer(rs.getString("OLD_CUSTOMER"));
			return vo;
		}
	};
	
	public List<OaCustomerLicenDetail> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN_DETAIL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaCustomerLicenDetail> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OaCustomerLicenDetail.class));
		return lists;
	}
}
