package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int1101FormVo;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Repository
public class IaConcludeFollowHdrJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int11Vo> getData(Int11FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" Select * from IA_CONCLUDE_FOLLOW_HDR   WHERE INSPECTION_WORK = ? AND BUDGET_YEAR = ? AND Is_Deleted = 'N' ");

		params.add(request.getInspecWork());
		params.add(request.getBudgetYear());

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int11Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int11Vo.class));

		return datas;
	}
	
	public void updateSentStatus(Int1101FormVo form) {	
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE IA_CONCLUDE_FOLLOW_HDR C ");
		sql.append("SET C.SEND_STATUS = ? ");
		sql.append("WHERE C.ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getSendStatus(),form.getId()});
	}
	
	
}
