package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskBudgetProject;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;

@Repository
public class Int030403JdbcRepository {

	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private final String SQL_IA_RISK_FACTORS = "SELECT * FROM IA_RISK_BUDGET_PROJECT WHERE PROJECTTYPECODE = ? ";

	public List<Int030403Vo> list(Int030403FormVo form) {
		List<Int030403Vo> int030403FormVoList = new ArrayList<Int030403Vo>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getProjecttypecode());

		if (StringUtils.isNotBlank(form.getProjectyear())) {
			sql.append("AND PROJECTYEAR = ? ");
			Date date = ConvertDateUtils.parseStringToDate(form.getProjectyear(), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
		}
		int030403FormVoList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return int030403FormVoList;
	}
	
	private RowMapper<Int030403Vo> listRowmapper = new RowMapper<Int030403Vo>() {
		@Override
		public Int030403Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int030403Vo vo = new Int030403Vo();
			IaRiskBudgetProject irbp = new IaRiskBudgetProject();

			irbp.setId(rs.getBigDecimal("ID"));
			irbp.setProjecttypecode(rs.getString("PROJECTTYPECODE"));
			irbp.setProjectyear(rs.getString("PROJECTYEAR"));		
			irbp.setProjectid(rs.getString("PROJECTID"));
			irbp.setProjectname(rs.getString("PROJECTNAME"));
			irbp.setProjecttypename(rs.getString("PROJECTTYPENAME"));
			irbp.setOwnerofficeid(rs.getString("OWNEROFFICEID"));
			irbp.setOwnerofficename(rs.getString("OWNEROFFICENAME"));
			irbp.setExpensebudgetamountm(rs.getString("EXPENSEBUDGETAMOUNTM"));
			irbp.setExpensebudgetamounta(rs.getString("EXPENSEBUDGETAMOUNTA"));
			irbp.setExpensebudgetamountx(rs.getString("EXPENSEBUDGETAMOUNTX"));
			irbp.setApprovedbudgetamount(rs.getString("APPROVEDBUDGETAMOUNT"));

			
			vo.setIaRiskBudgetProject(irbp);
			return vo;
		}
	};
	
	
}
