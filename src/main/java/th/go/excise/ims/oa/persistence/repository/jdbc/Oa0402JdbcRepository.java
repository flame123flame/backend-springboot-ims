package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.vo.Oa0202Vo;

@Repository
public class Oa0402JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0202Vo> findAll(String offCode, int addDate) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_PLAN_ID         AS OA_PLAN_ID, ");
		sql.append("   LP.OA_LICENSE_PLAN_ID      AS OA_LICENSE_PLAN_ID, ");
		sql.append("   CL.OA_CUSLICENSE_ID        AS OA_CUSLICENSE_ID, ");
		sql.append("   CL.COMPANY_NAME            AS COMPANY_NAME, ");
		sql.append("   CL.ADDRESS                 AS ADDRESS ");
		sql.append(" FROM OA_LICENSE_PLAN LP ");
		sql.append(" INNER JOIN OA_ACH_CUSTOMER_LICEN CL ");
		sql.append(" ON CL.OA_CUSLICENSE_ID = LP.LICENSE_ID ");
		sql.append(" WHERE LP.IS_DELETED     = 'N' ");
		sql.append(" AND ((TRUNC(SYSDATE + ?)) BETWEEN LP.AUDIT_START AND LP.AUDIT_END) ");
		sql.append(" AND (LP.STATUS = '3' OR LP.STATUS = '5') ");
		params.add(addDate);
		if (StringUtils.isNotBlank(offCode)) {
			sql.append(" AND LP.OFFICE_CODE LIKE ? ");
			params.add(offCode + "%");
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa0202Vo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa0202Vo.class));
		return lists;
	}

}
