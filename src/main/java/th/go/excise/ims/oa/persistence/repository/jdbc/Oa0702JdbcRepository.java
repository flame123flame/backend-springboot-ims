package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.vo.Oa0702Reg8000Vo;

@Repository
public class Oa0702JdbcRepository {

	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Oa0702Reg8000Vo> reg8000M(String newRegId, List<String> yearsMonths) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();

		sql.append(" SELECT NEW_REG_ID , (TAX_YEAR || LPAD(TAX_MONTH, 2, 0)) AS YEAR_MONTH, TAX_AMOUNT ");
		sql.append(" FROM TA_WS_INC8000_M  ");
		sql.append(" WHERE IS_DELETED = 'N'  ");
		sql.append(" AND NEW_REG_ID = ? ");

		params.add(newRegId);

		if (!yearsMonths.isEmpty()){
			sql.append(" AND ( TAX_YEAR || LPAD(TAX_MONTH,2,0) ) IN( ");
			int i = 0;
			for (String yearMonth : yearsMonths) {
				sql.append(" ? ");
				params.add(yearMonth);
				if (i != (yearsMonths.size() - 1)) {
					sql.append(" , ");
				}
				i++;
			}
			sql.append(" ) ");
		}
		sql.append(" group by NEW_REG_ID, (TAX_YEAR || LPAD(TAX_MONTH, 2, 0)), TAX_AMOUNT ");
		sql.append(" order by (TAX_YEAR || LPAD(TAX_MONTH, 2, 0)) ");

		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Oa0702Reg8000Vo.class));
	}

}
