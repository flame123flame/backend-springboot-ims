package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaStampType;

@Repository
public class IaStampTypeJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaStampType> getDataAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM IA_STAMP_TYPE ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaStampType> datas = this.commonJdbcTemplate.query(sql.toString() ,
				new BeanPropertyRowMapper(IaStampType.class));

		return datas;
	}
}
