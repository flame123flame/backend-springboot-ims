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
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0107CodeVo;
import th.go.excise.ims.oa.vo.Oa040106FormVo;
import th.go.excise.ims.oa.vo.Oa0412Vo;

@Repository
public class Oa0412JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0107CodeVo> getDataFilter(Oa0412Vo request,String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_ACH_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");
		if (request.getListID() != null) {
			if (request.getListID().size() > 0) {
				List<String> list = new ArrayList<>();
				for (int i = 0; i < request.getListID().size(); i++) {
					list.add( request.getListID().get(i).getId());
				}
				sql.append(" AND L.OA_CUSLICENSE_ID NOT IN (");
				sql.append(StringUtils.join(list, ", "));
				sql.append(")");
			}
		}
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
		List<Oa0107CodeVo> datas = this.commonJdbcTemplate.query(limit + " ORDER BY rnum DESC ", params.toArray(),
				new BeanPropertyRowMapper(Oa0107CodeVo.class));

		return datas;
	}

	/* count datatable */
	public Integer countDatafilter(Oa0412Vo request,String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_ACH_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");
		
		if (request.getListID() != null) {
			if (request.getListID().size() > 0) {
				List<String> list = new ArrayList<>();
				for (int i = 0; i < request.getListID().size(); i++) {
					list.add( request.getListID().get(i).getId());
				}
				sql.append(" AND L.OA_CUSLICENSE_ID NOT IN (");
				sql.append(StringUtils.join(list, ", "));
				sql.append(")");
			}
		}
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

	public List<Oa040106FormVo> findByCustomerIdAndLicenseType(BigDecimal customerId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER_LICEN WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSTOMER_ID = ? AND IS_DELETED = 'N' ");
		sql.append(" AND IS_DELETED = 'N' ");
		sql.append(" ORDER BY OA_CUSLICENSE_ID DESC ");
		params.add(customerId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa040106FormVo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa040106FormVo.class));
		return lists;
	}

	public List<OaAchCustomerLicenDtl> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER_LICEN_DTL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaAchCustomerLicenDtl> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaAchCustomerLicenDtl.class));
		return lists;
	}
	public List<OaAchCustomerLicen> licenseAll(Oa0412Vo request, String offCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * ");
		sql.append(" FROM OA_ACH_CUSTOMER_LICEN ");
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
		List<OaAchCustomerLicen> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaAchCustomerLicen.class));
		return lists;
	}

}
