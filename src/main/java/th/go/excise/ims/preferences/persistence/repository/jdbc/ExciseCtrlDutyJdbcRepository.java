package th.go.excise.ims.preferences.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.preferences.vo.Ed0101DepartmentVo;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Repository
public class ExciseCtrlDutyJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed03Vo> findByDutyGroupName(Ed03FormVo form) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_CTRL_DUTY WHERE DUTY_GROUP_NAME LIKE ? AND IS_DELETED ='N' ");
		params.add("%" + form.getDutyGroupName().replaceAll(" ", "%") + "%" ); 
		sql.append(" ORDER BY EXCISE_CTRL_DUTY_ID DESC ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed03Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed03Vo.class));
		return datas;
	}

	public boolean checkByDutyGroupName(Ed03FormVo form) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_CTRL_DUTY ");
		sql.append(" WHERE DUTY_GROUP_NAME = ? AND DUTY_GROUP_CODE = ? AND RES_OFFCODE = ? AND IS_DELETED ='N' ");
		params.add(form.getDutyGroupName());
		params.add(form.getDutyGroupCode());
		params.add(form.getResOffcode());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed03Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed03Vo.class));
		boolean repeat = false;
		if (datas.size() == 0) {
			repeat = true;
		}
		return repeat;
	}
	
	public List<Ed0101DepartmentVo> listDepartment0014() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE OFF_CODE LIKE '0014%' ");
		List<Ed0101DepartmentVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listDepartment00);
		return datas;
	}
	
	private RowMapper<Ed0101DepartmentVo> listDepartment00 = new RowMapper<Ed0101DepartmentVo>() {
		@Override
		public Ed0101DepartmentVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed0101DepartmentVo vo = new Ed0101DepartmentVo();
			vo.setOffName(rs.getString("OFF_NAME"));
			vo.setOffCode(rs.getNString("OFF_CODE"));
			return vo;
		}
	};
}
