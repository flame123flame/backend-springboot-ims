package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int01HdrVo;

@Repository
public class IaPlanHdrJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public Int01HdrVo getDataFilter(String budgetYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_HDR WHERE 1=1 AND IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(budgetYear)) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(budgetYear);
		}
		sql.append(" ORDER BY CREATED_DATE ASC");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Int01HdrVo planHdr = (Int01HdrVo) commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Int01HdrVo.class));
		return planHdr;
	}

}
