package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int1102Vo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Repository
public class Int1102JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	
	public List<Int11Vo> getDataConFol(Int1102FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CONCLUDE_FOLLOW_HDR   WHERE ID = ? AND Is_Deleted = 'N' ");
		params.add(request.getId());

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int11Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int11Vo.class));
		return datas;
	}

	public List<Int1102Vo> getData(Int1102FormVo form) {
		List<Int1102Vo> Int1102VoList = new ArrayList<Int1102Vo>();
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT a.ID , a.ISSUES , a.WHAT_SHOULD_BE , a.GUIDELINES_DEVELOPING , a.REFERENCE ");
		sql.append("FROM IA_CONCLUDE_FOLLOW_DETAIL a ");
		sql.append("WHERE a.ID_HDR = ? AND a.IS_DELETED = 'N' ");
		params.add(form.getId());
		Int1102VoList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return Int1102VoList;
	}

	private RowMapper<Int1102Vo> listRowmapper = new RowMapper<Int1102Vo>() {
		@Override
		public Int1102Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int1102Vo vo = new Int1102Vo();
			vo.setId(rs.getLong("ID"));
			vo.setIssues(rs.getString("ISSUES"));
			vo.setWhatShouldBe(rs.getString("WHAT_SHOULD_BE"));
			vo.setGuidelinesDeveloping(rs.getString("GUIDELINES_DEVELOPING"));
			vo.setReference(rs.getString("REFERENCE"));
			return vo;
		}
	};
	
	public void updateCheckStatus(Int1102FormVo form) {
		String ApproveDateCon = ConvertDateUtils.formatDateToString(form.getApproveDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC,ConvertDateUtils.LOCAL_TH);	
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE IA_CONCLUDE_FOLLOW_HDR C  ");
		sql.append("SET C.CHECK_STATUS = ?  , ");
		sql.append("C.APPROVE_DATE = ? , ");
		sql.append("C.NOTATION = ? ");
		sql.append("WHERE C.ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getCheckStatus(),ApproveDateCon,form.getNotation(),form.getId()});
	}
	
	

}
