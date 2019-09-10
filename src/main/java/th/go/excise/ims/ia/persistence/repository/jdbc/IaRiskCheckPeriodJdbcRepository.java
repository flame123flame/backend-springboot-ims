package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;

@Repository
public class IaRiskCheckPeriodJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaRiskCheckPeriod> getDataFilter() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT  EXCISE_CODE, AREA_NAME, Max(DATE_END) AS DATE_END, DATE_START, SECTOR_NAME, BUDGET_YEAR "); 
		sql.append(" FROM IA_RISK_CHECK_PERIOD GROUP by EXCISE_CODE, AREA_NAME,SECTOR_NAME,BUDGET_YEAR, DATE_START ORDER BY EXCISE_CODE DESC ");
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<IaRiskCheckPeriod> datas = commonJdbcTemplate.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(IaRiskCheckPeriod.class));

		return datas;
	}
}
