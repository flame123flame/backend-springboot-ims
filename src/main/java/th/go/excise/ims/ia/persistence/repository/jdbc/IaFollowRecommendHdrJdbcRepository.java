package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendHdr;

@Repository
public class IaFollowRecommendHdrJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaFollowRecommendHdr> getDataFilter(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_FOLLOW_RECOMMEND_HDR ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" 	AND BUDGET_YEAR = ? ");
		sql.append(" 	AND INSPECTION_WORK = ? ");
 		sql.append(" ORDER BY CREATED_DATE DESC");
 		
 		params.add(budgetYear);
 		params.add(inspectionWork);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaFollowRecommendHdr> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaFollowRecommendHdr.class));

		return response; 
	}

}
