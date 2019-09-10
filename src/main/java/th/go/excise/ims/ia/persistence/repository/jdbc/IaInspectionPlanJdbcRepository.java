package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;

@Repository
public class IaInspectionPlanJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaInspectionPlan> getDataFilter(String budgetYear, BigDecimal inspectionWork, String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_INSPECTION_PLAN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" 	AND BUDGET_YEAR = ? ");
		sql.append(" 	AND INSPECTION_WORK = ? ");
 		sql.append(" ORDER BY SECTOR ASC");
 		
 		params.add(budgetYear);
 		params.add(inspectionWork);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaInspectionPlan> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaInspectionPlan.class));

		return response; 
	}
	
	public List<IaInspectionPlan> getDataFilterIdParams(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_INSPECTION_PLAN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" 	AND ID = ? ");
	
 		params.add(id);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaInspectionPlan> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaInspectionPlan.class));

		return response; 
	}
}
