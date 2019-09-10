package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;

@Repository
public class Oa07JdbcRepository {

	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;

	private void sqlBuilder(StringBuilder sql, List<Object> params, Oa07FormVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ,  ");
		sql.append("   R4000.FAC_FULLNAME ,  ");
		sql.append("   R4000.NEW_REG_ID ,  ");
		sql.append("   R4000.FAC_ADDRESS ,  ");
		sql.append("   R4000.DUTY_CODE ,  ");
		sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000 ,  ");
		sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE ,  ");
		sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC ,  ");
		sql.append("   ED_AREA.OFFICE_CODE AREA_CODE ,  ");
		sql.append("   ED_AREA.OFF_SHORT_NAME AREA_DESC ,  ");
		sql.append("   R4000.REG_STATUS,  ");
		sql.append("   R4000.REG_DATE,  ");
		sql.append("   R4000.REG_CAPITAL  ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN (  ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE,  ");
		sql.append("     OFF_NAME,  ");
		sql.append("     OFF_SHORT_NAME  ");
		sql.append("   FROM EXCISE_DEPARTMENT  ");
		sql.append("   WHERE IS_DELETED = 'N'  ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 2),'0000') = OFF_CODE  ");
		sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000')  ");
		sql.append(" INNER JOIN (  ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE,  ");
		sql.append("     OFF_NAME,  ");
		sql.append("     OFF_SHORT_NAME  ");
		sql.append("   FROM EXCISE_DEPARTMENT  ");
		sql.append("   WHERE IS_DELETED = 'N'  ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 4),'00') = OFF_CODE  ");
		sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE R4000.IS_DELETED='N' ");
		sql.append(" AND R4000.OFFICE_CODE = ?");
		
		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND r4000.new_reg_id=?  ");
			params.add(StringUtils.trim(formVo.getNewRegId()));
		}
		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			sql.append(" and FAC_FULLNAME like ? ");
			params.add("%"+StringUtils.trim(formVo.getFacFullname())+"%");
		}
	}
	public Long reg4000Count(Oa07FormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sqlBuilder(sql, params, formVo);
		
		String countSql = OracleUtils.countForDataTable(sql.toString());
		return this.commonJdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	}

	public List<Oa07Reg4000Vo> reg4000(Oa07FormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sqlBuilder(sql, params, formVo);
		
		return this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(),
				reg4000Rowmapper);
	}

	private RowMapper<Oa07Reg4000Vo> reg4000Rowmapper = new RowMapper<Oa07Reg4000Vo>() {
		@Override
		public Oa07Reg4000Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa07Reg4000Vo vo = new Oa07Reg4000Vo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setDutyCode(rs.getString("DUTY_CODE"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setRegStatus(rs.getString("REG_STATUS"));
			vo.setRegDate(rs.getString("REG_DATE"));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			return vo;
		}
	};

}
