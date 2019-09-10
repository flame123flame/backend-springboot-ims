package th.go.excise.ims.preferences.persistence.repository.jdbc;

import java.math.BigDecimal;
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
import th.go.excise.ims.preferences.vo.Ed0101PositionVo;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Repository
public class ExcisePositionJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed0101PositionVo> listPosition() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_POSITION WHERE IS_DELETED = 'N' ");
		List<Ed0101PositionVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listPosition);
		return datas;
	}

	private RowMapper<Ed0101PositionVo> listPosition = new RowMapper<Ed0101PositionVo>() {
		@Override
		public Ed0101PositionVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed0101PositionVo vo = new Ed0101PositionVo();
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			vo.setEdPersonSeq(rs.getBigDecimal("ED_POSITION_SEQ"));
			return vo;
		}
	};
	
	public List<Ed0101DepartmentVo> listDepartment00() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE OFF_CODE LIKE '00%' ");
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
	
	public List<Ed0101DepartmentVo> listDepartment01() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE OFF_CODE NOT LIKE '00%' ");
		List<Ed0101DepartmentVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listDepartment01);
		return datas;
	}
	
	private RowMapper<Ed0101DepartmentVo> listDepartment01 = new RowMapper<Ed0101DepartmentVo>() {
		@Override
		public Ed0101DepartmentVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed0101DepartmentVo vo = new Ed0101DepartmentVo();
			vo.setOffName(rs.getString("OFF_NAME"));
			vo.setOffCode(rs.getNString("OFF_CODE"));
			return vo;
		}
	};
	
	public List<Ed02Vo> list(Ed02FormVo form) {
		List<Ed02Vo> Ed02VoList = new ArrayList<Ed02Vo>();
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_POSITION WHERE ED_POSITION_NAME LIKE ? ");
		
		params.add("%" +  form.getPosition().replaceAll(" ", "%") + "%");
		sqlBuilder.append(" ORDER BY ED_POSITION_SEQ DESC ");
		Ed02VoList = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listRowmapper);
		return Ed02VoList;
	}
	
	private RowMapper<Ed02Vo> listRowmapper = new RowMapper<Ed02Vo>() {
		@Override
		public Ed02Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed02Vo vo = new Ed02Vo();
			vo.setEdPositionSeq(rs.getBigDecimal("ED_POSITION_SEQ"));
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			vo.setAllowancesDay(rs.getBigDecimal("ALLOWANCES_DAY"));
			vo.setAllowancesHalfDay(rs.getBigDecimal("ALLOWANCES_HALF_DAY"));
			vo.setAccomFeeSingle(rs.getBigDecimal("ACCOM_FEE_SINGLE"));
			vo.setAccomFeeDouble(rs.getBigDecimal("ACCOM_FEE_DOUBLE"));
			vo.setAccomFeePackages(rs.getBigDecimal("ACCOM_FEE_PACKAGES"));
			return vo;
		}
	};
	
	public void deleteById(Ed02FormVo formvo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" DELETE FROM EXCISE_POSITION WHERE ED_POSITION_SEQ = ? ");
		params.add(formvo.getEdPersonSeq());
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}	
	
	
	
	public List<Ed02Vo> getConfigEdit(BigDecimal edPersonSeq) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_POSITION WHERE ED_POSITION_SEQ = ? AND IS_DELETED ='N' ");
		params.add(edPersonSeq);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed02Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed02Vo.class));
		return datas;
	}
}
