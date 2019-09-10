package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;

public class IaChartOfAccRepositoryImpl implements IaChartOfAccRepositoryCustom {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	@Override
	public List<IaChartOfAcc> getDropdown() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT COA_ID, COA_CODE, COA_NAME ");
		sql.append(" FROM IA_CHART_OF_ACC ");

		sql.append(" ORDER BY COA_CODE ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaChartOfAcc> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaChartOfAcc.class));
		return response;
	}
}
