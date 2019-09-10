package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa0207CodeVo;
import th.go.excise.ims.oa.vo.Oa0207CustomerVo;
import th.go.excise.ims.oa.vo.Oa0207Vo;

@Repository
public class Oa0207JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0207CodeVo> getDataFilter(Oa0207Vo request, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND L.OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getArea()) + '%');
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND L.OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getSector()) + '%');
		} else if (StringUtils.isNotBlank(offCode)) {
			if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
				params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
				sql.append(" AND L.OFF_CODE LIKE ? ");
			}
		}
		
		sql.append(" GROUP BY L.IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE ");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Oa0207CodeVo> datas = this.commonJdbcTemplate.query(limit + " ORDER BY rnum DESC ", params.toArray(),
				new BeanPropertyRowMapper(Oa0207CodeVo.class));

		return datas;
	}

	/* count datatable */
	public Integer countDatafilter(Oa0207Vo request, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND L.OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getArea()) + '%');
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND L.OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getSector()) + '%');
		} else if (StringUtils.isNotBlank(offCode)) {
			if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
				params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
				sql.append(" AND L.OFF_CODE LIKE ? ");
			}
		}
		
		sql.append(" GROUP BY L.IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE ");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public List<Oa020106FormVo> findByCustomerIdAndLicenseType(BigDecimal customerId, String licenseType) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSTOMER_ID = ? AND IS_DELETED = 'N' ");
		sql.append(" AND LICENSE_TYPE = ? AND IS_DELETED = 'N' ");
		sql.append(" ORDER BY OA_CUSLICENSE_ID DESC ");
		params.add(customerId);
		params.add(licenseType);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa020106FormVo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa020106FormVo.class));
		return lists;
	}

	public List<OaCustomerLicenDetail> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN_DETAIL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaCustomerLicenDetail> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaCustomerLicenDetail.class));
		return lists;
	}
	
	public List<Oa0207CustomerVo> findCustomers(String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT DISTINCT IDENTIFY_NO, ");
		sql.append("   NAME ");
		sql.append(" FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
			params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
			sql.append(" AND OFF_CODE LIKE ? ");
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa0207CustomerVo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa0207CustomerVo.class));
		return lists;
	}
	
	public Oa020106FormVo findCustomer(String offCode, String customerNo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * ");
		sql.append(" FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
			params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
			sql.append(" AND OFF_CODE LIKE ? ");
		}
		sql.append(" AND IDENTIFY_NO  = ? ");
		params.add(customerNo);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Oa020106FormVo lists = (Oa020106FormVo) commonJdbcTemplate.queryForObject(OracleUtils.limit(sql.toString(), 0, 1).toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa020106FormVo.class));
		return lists;
	}
	
	public List<OaCustomerLicen> licenseAll(Oa0207Vo request, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * ");
		sql.append(" FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getArea()) + '%');
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND OFF_CODE LIKE ? ");
			params.add(OaOfficeCode.officeCodeLike(request.getSector()) + '%');
		} else if (StringUtils.isNotBlank(offCode)) {
			if (StringUtils.isNotBlank(OaOfficeCode.officeCodeLike(offCode))) {
				params.add(OaOfficeCode.officeCodeLike(offCode) + '%');
				sql.append(" AND OFF_CODE LIKE ? ");
			}
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaCustomerLicen> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaCustomerLicen.class));
		return lists;
	}

}
