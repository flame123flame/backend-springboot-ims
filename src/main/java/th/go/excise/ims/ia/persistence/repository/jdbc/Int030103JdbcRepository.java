package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Repository
public class Int030103JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void listUpdatePercent(IaRiskFactorsConfig form) {		
		StringBuilder sql = new StringBuilder("UPDATE IA_RISK_FACTORS_CONFIG SET PERCENT = ? WHERE ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getPercent() , form.getId() });
	}
	
	
	public List<IaRiskFactorsConfigAll> listConfigAll(IaRiskFactorsConfigAll form) {
		List<IaRiskFactorsConfigAll> response = new ArrayList<IaRiskFactorsConfigAll>();
		StringBuilder sql = new StringBuilder("SELECT * FROM IA_RISK_FACTORS_CONFIG_ALL WHERE INSPECTION_WORK = ?");	
		List<Object> params = new ArrayList<Object>();
		params.add(form.getInspectionWork());

		if (StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		response = commonJdbcTemplate.query(sql.toString(), params.toArray(), listConfigAllRowmapper);
		return response;		
	}
	
	private RowMapper<IaRiskFactorsConfigAll> listConfigAllRowmapper = new RowMapper<IaRiskFactorsConfigAll>() {
		@Override
		public IaRiskFactorsConfigAll mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactorsConfigAll vo = new IaRiskFactorsConfigAll();	
			vo.setId(rs.getBigDecimal("ID"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			
			vo.setVerylow(rs.getString("VERYLOW"));
			vo.setVerylowColor(rs.getString("VERYLOW_COLOR"));
			vo.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));
			vo.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			vo.setVerylowStart(rs.getString("VERYLOW_START"));
			vo.setVerylowEnd(rs.getString("VERYLOW_END"));
			
			vo.setLow(rs.getString("LOW"));
			vo.setLowColor(rs.getString("LOW_COLOR"));
			vo.setLowCondition(rs.getString("LOW_CONDITION"));
			vo.setLowRating(rs.getBigDecimal("LOW_RATING"));
			vo.setLowStart(rs.getString("LOW_START"));
			vo.setLowEnd(rs.getString("LOW_END"));
			
			vo.setMedium(rs.getString("MEDIUM"));
			vo.setMediumColor(rs.getString("MEDIUM_COLOR"));
			vo.setMediumCondition(rs.getString("MEDIUM_CONDITION"));
			vo.setMediumRating(rs.getBigDecimal("MEDIUM_RATING"));
			vo.setMediumStart(rs.getString("MEDIUM_START"));
			vo.setMediumEnd(rs.getString("MEDIUM_END"));
			
			vo.setHigh(rs.getString("HIGH"));
			vo.setHighColor(rs.getString("HIGH_COLOR"));
			vo.setHighCondition(rs.getString("HIGH_CONDITION"));
			vo.setHighRating(rs.getBigDecimal("HIGH_RATING"));
			vo.setHighStart(rs.getString("HIGH_START"));
			vo.setHighEnd(rs.getString("HIGH_END"));
			
			vo.setVeryhigh(rs.getString("VERYHIGH"));
			vo.setVeryhighColor(rs.getString("VERYHIGH_COLOR"));
			vo.setVeryhighCondition(rs.getString("VERYHIGH_CONDITION"));
			vo.setVeryhighRating(rs.getBigDecimal("VERYHIGH_RATING"));
			vo.setVeryhighStart(rs.getString("VERYHIGH_START"));
			vo.setVeryhighEnd(rs.getString("VERYHIGH_END"));
				
			return vo;
		}
	};
}
