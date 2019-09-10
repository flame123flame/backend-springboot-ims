package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskBudgetProject;

@Repository
public class IaRiskBudgetProjectJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaRiskBudgetProject> getProjectByYear(Date date) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT PROJECTID,PROJECTNAME FROM ( " + 
				"  SELECT bp.PROJECTID,bp.PROJECTNAME,COUNT(sc.PROJECT_CODE) AS COUNT " + 
				"  FROM IA_RISK_BUDGET_PROJECT bp " + 
				"  LEFT JOIN ( SELECT * " + 
				"                    FROM ia_risk_SELECT_CASE sc " + 
				"                    WHERE INSPECTION_WORK = 3 " + 
				"                    AND BUDGET_YEAR       = ? ) sc " + 
				"        ON bp.PROJECTID=sc.PROJECT_CODE " + 
				"  WHERE bp.Projectyear = ?  " + 
				"  GROUP BY  bp.PROJECTID,bp.PROJECTNAME ) " + 
				"where COUNT=0  ");
		
		params.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));
		params.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaRiskBudgetProject> dataRes = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaRiskBudgetProject.class));

		return dataRes;
	}
}
