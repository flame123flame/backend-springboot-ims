package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.vo.Oa0201001Vo;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;

@Repository
public class Oa0201JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Oa0201001Vo> findLicenseById(Oa0206FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT ED_SECTOR.OFF_NAME OFFICE_NAME_MIAN ,ED_AREA.OFF_NAME OFFICE_NAME_SUB , LIC.* FROM OA_CUSTOMER_LICEN LIC");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')  ");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')  ");
//		sql.append("  WHERE LIC.OA_CUSLICENSE_ID = ? ");
		sql.append(" WHERE 1=1 ");
		if (request.getListID().size() > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < request.getListID().size(); i++) {
				list.add( request.getListID().get(i).getId());
			}
			sql.append(" AND LIC.OA_CUSLICENSE_ID NOT IN (");
			sql.append(StringUtils.join(list, ", "));
			sql.append(")");
		}
		
//		params.add(id);
		sql.append(" ORDER BY LIC.CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Oa0201001Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),dataRowmapper);
//		List<Oa0201001Vo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return datas;
	}
	
	public int countLicenseById(Oa0206FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT COUNT(1) FROM OA_CUSTOMER_LICEN LIC");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')  ");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')  ");
//		sql.append("  WHERE LIC.OA_CUSLICENSE_ID = ? ");
//		params.add(id);
		sql.append(" WHERE 1=1 ");
		if (request.getListID().size() > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < request.getListID().size(); i++) {
				list.add( request.getListID().get(i).getId());
			}
			sql.append(" AND LIC.OA_CUSLICENSE_ID NOT IN (");
			sql.append(StringUtils.join(list, ", "));
			sql.append(")");
		}
		


		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);
		return count;
	}
	
	public List<Oa0201001Vo> findLicenseById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT ED_SECTOR.OFF_NAME OFFICE_NAME_MIAN ,ED_AREA.OFF_NAME OFFICE_NAME_SUB , LIC.* FROM OA_CUSTOMER_LICEN LIC");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')  ");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')  ");
//		sql.append("  WHERE LIC.OA_CUSLICENSE_ID = ? ");
		params.add(id);
		List<Oa0201001Vo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}
	
	public List<OaCustomerLicen> findCustomerLicenseByPlanId(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT CL.* FROM OA_LICENSE_PLAN LP INNER JOIN OA_CUSTOMER_LICEN CL ");
		sql.append("  ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID  ");
		sql.append("  WHERE LP.IS_DELETED = 'N'  ");
		if (id != null ) {
			sql.append(" AND LP.OA_PLAN_ID = ? ");
		}
//		params.add(id);
		List<OaCustomerLicen> data = commonJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(OaCustomerLicen.class));
		return data;
	}
	
	public List<Oa020103Vo> findUserAuditer(String officeCode,Oa0206FormVo request){
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT WS.* FROM WS_USER WS ");
		sql.append(" INNER JOIN WS_USER_ROLE USE_ROL ON USE_ROL.USER_ID = WS.USER_ID " );
		sql.append(" WHERE USE_ROL.ROLE_CODE = 'ROLE_OA_AUDITOR' ");
		if (StringUtils.isNotBlank(officeCode)) {
			sql.append(" AND OFFICE_CODE LIKE ?  ");
			params.add(officeCode+"%");
		}
		
		if (request.getListID().size() > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < request.getListID().size(); i++) {
				list.add( request.getListID().get(i).getId());
			}
			sql.append(" AND WS.WS_USER_ID NOT IN (");
			sql.append(StringUtils.join(list, ", "));
			sql.append(")");
		}
		sql.append(" ORDER BY WS.WS_USER_ID ");
		
		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Oa020103Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),userRowmapper);
		return datas;
	}
	
	public int countUserAuditer(String officeCode,Oa0206FormVo request){
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT WS.* FROM WS_USER WS ");
		sql.append(" INNER JOIN WS_USER_ROLE USE_ROL ON USE_ROL.USER_ID = WS.USER_ID " );
		sql.append(" WHERE USE_ROL.ROLE_CODE = 'ROLE_OA_AUDITOR' ");
		if (StringUtils.isNotBlank(officeCode)) {
			sql.append(" AND OFFICE_CODE LIKE ?  ");
			params.add(officeCode+"%");
		}
		if (request.getListID().size() > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < request.getListID().size(); i++) {
				list.add( request.getListID().get(i).getId());
			}
			sql.append(" AND WS.WS_USER_ID NOT IN (");
			sql.append(StringUtils.join(list, ", "));
			sql.append(")");
		}
		
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);
		return count;
	}
	
	public List<Oa020103Vo> findUserAuditerByPlanId(String officeCode,BigDecimal planId){
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT AU.OA_PERSON_AUDIT_PLAN_ID ,  WS.* FROM WS_USER WS ");
		sql.append(" INNER JOIN WS_USER_ROLE USE_ROL ON USE_ROL.USER_ID = WS.USER_ID " );
		sql.append(" INNER JOIN OA_PERSON_AUDIT_PLAN AU ON AU.OA_PERSON_ID = WS.WS_USER_ID  ");
		sql.append( "  WHERE AU.OA_PLAN_ID = ? AND AU.IS_DELETED = 'N' " );
		params.add(planId);
//		if (StringUtils.isNotBlank(officeCode)) {
//			sql.append(" AND OFFICE_CODE LIKE ?  ");
//			params.add(officeCode+"%");
//		}

		sql.append(" ORDER BY WS.WS_USER_ID ");
		

		List<Oa020103Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),auditerRowmapper);
		return datas;
	}
	
	public List<Oa0201001Vo> findLicenseByPlanId(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_LICENSE_PLAN_ID ,  ED_SECTOR.OFF_NAME OFFICE_NAME_MIAN ,ED_AREA.OFF_NAME OFFICE_NAME_SUB , LIC.* FROM OA_CUSTOMER_LICEN LIC ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')   ");
		sql.append("  INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = LIC.OA_CUSLICENSE_ID ");
		sql.append("   WHERE LP.OA_PLAN_ID = ? AND LP.IS_DELETED = 'N' ");
		params.add(planId);
		List<Oa0201001Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),dataRowmapper);

		return datas;
	}
	
	public List<Oa0201001Vo> findLicenseHydroByPlanId(BigDecimal planId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT LP.OA_LICENSE_PLAN_ID , ED_SECTOR.OFF_NAME OFFICE_NAME_MIAN ,ED_AREA.OFF_NAME OFFICE_NAME_SUB , LIC.* FROM OA_HYD_CUSTOMER_LICEN LIC ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 2),'0000')");
		sql.append("  INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(LIC.OFF_CODE, 0, 4),'00')   ");
		sql.append("  INNER JOIN OA_LICENSE_PLAN LP ON LP.LICENSE_ID = LIC.OA_CUSLICENSE_ID ");
		sql.append("   WHERE LP.OA_PLAN_ID = ? AND LP.IS_DELETED = 'N'");
		params.add(planId);
		List<Oa0201001Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),dataRowmapper);

		return datas;
	}
	
	public List<Oa020103Vo> findUserApprover(String officeCode){
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT  WS.* FROM WS_USER WS ");
		sql.append(" INNER JOIN WS_USER_ROLE USE_ROL ON USE_ROL.USER_ID = WS.USER_ID " );
		sql.append(" WHERE USE_ROL.ROLE_CODE = 'ROLE_OA_HEAD' ");
		if (StringUtils.isNotBlank(officeCode)) {
			sql.append(" AND OFFICE_CODE LIKE ?  ");
			params.add(officeCode+"%");
		}
		
		List<Oa020103Vo> data = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),userRowmapper);
		return data;
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
	
	private RowMapper<Oa020103Vo> userRowmapper = new RowMapper<Oa020103Vo>() {

		@Override
		public Oa020103Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Oa020103Vo vo = new Oa020103Vo();
			vo.setUserThaiName(rs.getString("USER_THAI_NAME")+" "+rs.getString("USER_THAI_SURNAME"));
			vo.setWsUserId(rs.getBigDecimal("WS_USER_ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setUserThaiSurname(rs.getString("USER_THAI_SURNAME"));
			vo.setUserId(rs.getString("USER_ID"));
			return vo;
		}
	};

	private RowMapper<Oa020103Vo> auditerRowmapper = new RowMapper<Oa020103Vo>() {

		@Override
		public Oa020103Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Oa020103Vo vo = new Oa020103Vo();
			vo.setOaPersonAuditPlanId(rs.getBigDecimal("OA_PERSON_AUDIT_PLAN_ID"));
			vo.setUserThaiName(rs.getString("USER_THAI_NAME")+" "+rs.getString("USER_THAI_SURNAME"));
			vo.setWsUserId(rs.getBigDecimal("WS_USER_ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setUserThaiSurname(rs.getString("USER_THAI_SURNAME"));
			vo.setUserId(rs.getString("USER_ID"));
			return vo;
		}
	};
}
