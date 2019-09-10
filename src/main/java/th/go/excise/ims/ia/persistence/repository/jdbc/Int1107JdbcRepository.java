package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Repository
public class Int1107JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int11Vo> getData(Int11FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" Select * from IA_CONCLUDE_FOLLOW_HDR   WHERE INSPECTION_WORK = ? AND BUDGET_YEAR = ? AND  SEND_STATUS = 'Sent' AND Is_Deleted = 'N' ");
		params.add(request.getInspecWork());
		params.add(request.getBudgetYear());

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int11Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int11Vo.class));
		return datas;
	}
}
