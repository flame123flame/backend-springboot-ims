package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.vo.Oa0201001Vo;

@Repository
public class Oa0401JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	public List<Oa0201001Vo> findLicenseACHPlanId(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_LICENSE_PLAN_ID , ED_SECTOR.OFF_NAME OFFICE_NAME_MIAN ,ED_AREA.OFF_NAME OFFICE_NAME_SUB , LIC.* FROM OA_ACH_CUSTOMER_LICEN LIC ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')   ");
		sql.append("  INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = LIC.OA_CUSLICENSE_ID ");
		sql.append("   WHERE LP.OA_PLAN_ID = ? AND LP.IS_DELETED = 'N'");
		params.add(planId);
		List<Oa0201001Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),dataRowmapper);

		return datas;
	}
	
	private RowMapper<Oa0201001Vo> dataRowmapper = new RowMapper<Oa0201001Vo>() {
		@Override
		public Oa0201001Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa0201001Vo vo = new Oa0201001Vo();
			vo.setOfficeName1(rs.getString("OFFICE_NAME_MIAN"));
			vo.setOfficeName2(rs.getString("OFFICE_NAME_SUB"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setLicensePlanId(rs.getBigDecimal("OA_LICENSE_PLAN_ID"));
			return vo;
		}
	};

}
