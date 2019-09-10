package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.vo.Oa0703TaxpayVo;

@Repository
public class Oa0703JdbcRepository {

	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Oa0703TaxpayVo> reg8000M(String newRegId, List<Integer> budgetYears) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT  ");
		sql.append("    NEW_REG_ID, ");
		sql.append("    TAX_YEAR, ");
		sql.append("    SUM(TAX_AMOUNT) SUM_TAX_AMOUNT");
		sql.append(" FROM  TA_WS_INC8000_M WHERE IS_DELETED='N' ");
		sql.append(" AND NEW_REG_ID=? ");
		params.add(newRegId);
		if (!budgetYears.isEmpty()) {
			sql.append(" AND TAX_YEAR IN( ");
			int i = 0;
			for (Integer budgetYear : budgetYears) {
				sql.append(" ? ");
				params.add(budgetYear);
				if (i != (budgetYears.size() - 1)) {
					sql.append(" , ");
				}
				i++;
			}

			sql.append(" ) ");
		}

		sql.append(" GROUP BY NEW_REG_ID, TAX_YEAR ");
		sql.append(" ORDER BY TAX_YEAR ");

		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa0703TaxpayVo.class));
	}

}
