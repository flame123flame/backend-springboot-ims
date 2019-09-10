package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.vo.Oa0701Reg8000Vo;

@Repository
public class Oa0701JdbcRepository {

	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Oa0701Reg8000Vo> reg8000M(String newRegId, String startDate, String endDate) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT ");
		sql.append("      new_reg_id, ");
		sql.append("      TAX_AMOUNT, ");
		sql.append("      concat(tax_year, case LENGTH(TAX_MONTH) when 1 then concat('0',TAX_MONTH) WHEN 2   THEN tax_month end) year_month ");
		sql.append("  FROM ");
		sql.append("      ta_ws_inc8000_m ");
		sql.append("  WHERE ");
		sql.append("      is_deleted = 'N' ");
		sql.append("      AND new_reg_id =? ");		
		
		params.add(newRegId);		
		
		if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
			sql.append("      AND concat(tax_year, case LENGTH(TAX_MONTH) when 1 then concat('0',TAX_MONTH)end) BETWEEN ? and ? ");
			params.add(startDate);
			params.add(endDate);
		}
				
		sql.append("  ORDER BY ");
		sql.append("      tax_year,TAX_MONTH ");
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Oa0701Reg8000Vo.class));
	}

}
