package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster2;
import th.go.excise.ims.ia.vo.Int0305FormVo;
import th.go.excise.ims.ia.vo.Int0305Vo;

@Repository
public class IaRiskFactorsMaster2JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int0305Vo> list(Int0305FormVo form) {
		List<Int0305Vo> iaRiskFactorsMasterList = new ArrayList<Int0305Vo>();
		StringBuilder sql = new StringBuilder(" SELECT a.ID   AS ID_MASTER_RES, " + 
				"  a.RISK_FACTORS_MASTER, " + 
				"  a.CREATED_BY   AS CREATED_BY_RES, " + 
				"  a.CREATED_DATE AS CREATED_DATE_RES, " + 
				"  a.DATA_EVALUATE , " + 
				"  a.NOT_DELETE , " + 
				"  a.SIDE  " + 
				"FROM IA_RISK_FACTORS_MASTER_2 a " + 
				"WHERE a.IS_DELETED      = 'N' " + 
				"AND a.INSPECTION_WORK   = ? " + 
				"ORDER BY a.CREATED_DATE,a.ID ASC ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(form.getInspectionWork());
		
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaRiskFactorsMasterList;
	}



	private RowMapper<Int0305Vo> listRowmapper = new RowMapper<Int0305Vo>() {
		@Override
		public Int0305Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0305Vo vo = new Int0305Vo();
			IaRiskFactorsMaster2 iarfm = new IaRiskFactorsMaster2();	
			
			

			iarfm.setId(rs.getBigDecimal("ID_MASTER_RES"));
			iarfm.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			
			LocalDateTime createdDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE_RES"));
			iarfm.setCreatedDate(createdDate);
			iarfm.setCreatedBy(rs.getString("CREATED_BY_RES"));
			iarfm.setNotDelete(rs.getString("NOT_DELETE"));
			iarfm.setDataEvaluate(rs.getString("DATA_EVALUATE"));
			iarfm.setSide(rs.getString("SIDE"));
			
			String date = checkAndConvertDateToString(rs.getDate("CREATED_DATE_RES"));
			
			vo.setCreatedDateDesc(date);
			vo.setIaRiskFactorsMaster2(iarfm);		
			
			IaRiskFactorsConfig iarfc = new IaRiskFactorsConfig();	
			vo.setIaRiskFactorsConfig(iarfc);
	
			return vo;
		}
	};
	
	private String checkAndConvertDateToString(Date date) {
		String dateSting = "";
		if (date != null) {
			dateSting = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
		}
		return dateSting;
	}

	public void delete(Int0305FormVo form) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_MASTER_2 SET IS_DELETED = 'Y' WHERE ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getId() });

	}
	

}
