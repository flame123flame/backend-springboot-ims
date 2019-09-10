package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.IaChartAndIncVo;
import th.go.excise.ims.ia.vo.Int1502FormVo;
import th.go.excise.ims.ia.vo.Int1503FormVo;

@Repository
public class IaChartAndIncJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
		
	public List<IaChartAndIncVo> listData() {
		List<IaChartAndIncVo> iaChartAndIncVoList = new ArrayList<IaChartAndIncVo>();
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT a.CHART_AND_INC_ID, B.COA_CODE, B.COA_NAME, C.INC_CODE, C.INC_NAME   ");
		sqlBuilder.append(" FROM IA_CHART_AND_INC a,                                                    ");
		sqlBuilder.append("      IA_CHART_OF_ACC b,                                                     ");
		sqlBuilder.append("      EXCISE_INC_MAST c                                                      ");
		sqlBuilder.append(" WHERE A.IS_DELETED ='N' AND C.INC_CODE = A.INC_CODE                           ");	
		sqlBuilder.append(" AND   B.COA_CODE = A.COA_CODE ORDER BY B.COA_CODE                           ");	                                                                
		iaChartAndIncVoList = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listRowmapper);
		return iaChartAndIncVoList;
	}
	
	private RowMapper<IaChartAndIncVo> listRowmapper = new RowMapper<IaChartAndIncVo>() {
		@Override
		public IaChartAndIncVo mapRow(ResultSet rs, int arg1) throws SQLException {
			IaChartAndIncVo vo = new IaChartAndIncVo();
			vo.setChartAndIncId(rs.getLong("CHART_AND_INC_ID"));
			vo.setCoaCode(rs.getString("COA_CODE"));
			vo.setCoaName(rs.getString("COA_NAME"));
			vo.setIncCode(rs.getString("INC_CODE"));
			vo.setIncName(rs.getString("INC_NAME"));
			return vo;
		}
	};
	
	public void deleteById(Int1502FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("UPDATE IA_CHART_AND_INC SET IS_DELETED = 'Y' WHERE CHART_AND_INC_ID = ? ");
		params.add(request.getChartAndIncId());
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}	
	
	
	

}
