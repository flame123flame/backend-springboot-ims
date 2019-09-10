package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int110101FormVo;
import th.go.excise.ims.ia.vo.Int110101Vo;
import th.go.excise.ims.ia.vo.Int1101FormVo;
import th.go.excise.ims.ia.vo.Int1101Vo;
import th.go.excise.ims.ia.vo.Int11050101FormVo;

@Repository
public class IaConcludeFollowDetailJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int1101Vo> getDataDetailList(String id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CONCLUDE_FOLLOW_DETAIL WHERE ID_HDR = ? AND Is_Deleted = 'N' ORDER by SEQ ASC ");

		params.add(id);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int1101Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int1101Vo.class));

		return datas;
	}
	
	public void editDetailPerformance(Int11050101FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("   UPDATE IA_CONCLUDE_FOLLOW_DETAIL C     ");
		sql.append("   SET C.ISSUES = ? ,                     ");
		sql.append("   C.WHAT_SHOULD_BE = ? ,                 ");
		sql.append("   C.GUIDELINES_DEVELOPING = ? ,          ");
		sql.append("   C.REFERENCE = ?                        ");
		sql.append("   WHERE C.ID = ?                         ");
		commonJdbcTemplate.update(sql.toString(), new Object[] {form.getIssues(),form.getWhatShouldBe(),form.getGuidelinesDeveloping(),form.getReference(),form.getId()});
	}
	

	public void editDetails(Int110101FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE IA_CONCLUDE_FOLLOW_DETAIL C ");
		sql.append("SET C.ISSUES = ? , ");
		sql.append("C.DETECTED_OBSERVED = ? , ");
		sql.append("C.WHAT_SHOULD_BE = ? , ");
		sql.append("C.RISK_EFFECT = ? , ");
		sql.append("C.CAUSE = ? , ");
		sql.append("C.RECOMMEND = ? ");
		sql.append("WHERE C.ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] {form.getIssues(),form.getDetectedObserved(),form.getWhatShouldBe(),form.getRiskEffect(),form.getCause(),form.getRecommend(),form.getId()});
	}
	
	public List<Int110101Vo> findConcludeFollowEdit(String id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CONCLUDE_FOLLOW_DETAIL WHERE ID = ? AND Is_Deleted = 'N'");
		params.add(id);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int110101Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int110101Vo.class));
		return datas;
	}
	
	

}
