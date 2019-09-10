package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendDtl;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendHdr;

@Repository
public class IaFollowRecommendDtlJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaFollowRecommendDtl> getDataInDeadline(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_FOLLOW_RECOMMEND_DTL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" 	AND ID_FOLLOW_RECOMMEND_HDR = ? ");
		sql.append(" 	AND DAEDLINES_START <= ? ");
		sql.append(" 	AND DAEDLINES_END >= ? ");
 		
		Date currentDate = new Date();
		params.add(idHdr);
 		params.add(currentDate);
 		params.add(currentDate);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaFollowRecommendDtl> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaFollowRecommendDtl.class));

		return response; 
	}
}
