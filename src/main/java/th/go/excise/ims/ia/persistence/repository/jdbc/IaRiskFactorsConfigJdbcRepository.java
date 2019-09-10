package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;

@Repository
public class IaRiskFactorsConfigJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaRiskFactorsConfig> findbyIdMaster(String id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_RISK_FACTORS_CONFIG WHERE ID_FACTORS = ? ");
		params.add(id);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaRiskFactorsConfig> dataRes = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaRiskFactorsConfig.class));

		return dataRes;
	}
}
