package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;

@Repository
public class IaChartOfAccJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaChartOfAcc> findAll() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CHART_OF_ACC WHERE IS_DELETED = 'N' AND COA_TYPE = '5' ORDER BY COA_CODE ASC ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaChartOfAcc> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaChartOfAcc.class));
		return datas;
	}
}
