package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;

@Repository
public class Int030405JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private final String SQL_IA_RISK_FACTORS = " SELECT a.* , " + " b.ID AS ID_CONFIG, " + " b.*"
			+ "FROM IA_RISK_FACTORS a " + "LEFT JOIN IA_RISK_FACTORS_CONFIG b "
			+ "ON a.ID = b.ID_FACTORS WHERE a.INSPECTION_WORK = ? AND a.IS_DELETED = 'N' AND b.ID = ? AND BUDGET_YEAR = ? ";

	public List<Int0301Vo> getForm0304(Int0301FormVo form) {
		List<Int0301Vo> int0301VoList = new ArrayList<Int0301Vo>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getInspectionWork());
		params.add(form.getIdConfig());
		params.add(form.getBudgetYear());
		
		int0301VoList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return int0301VoList;
	}
	
	private RowMapper<Int0301Vo> listRowmapper = new RowMapper<Int0301Vo>() {
		@Override
		public Int0301Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0301Vo vo = new Int0301Vo();
			IaRiskFactors irf = new IaRiskFactors();
			IaRiskFactorsConfig irfc = new IaRiskFactorsConfig();

//			setentityIaRiskFactors
			irf.setId(rs.getBigDecimal("ID"));
			irf.setRiskFactors(rs.getString("RISK_FACTORS"));
			irf.setBudgetYear(rs.getString("BUDGET_YEAR"));
			irf.setSide(rs.getString("SIDE"));
			irf.setStatusScreen(rs.getString("STATUS_SCREEN"));
			irf.setDateCriteria(rs.getString("DATE_CRITERIA"));
			irf.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			irf.setCreatedBy(rs.getString("CREATED_BY"));
			irf.setIdMaster(rs.getBigDecimal("ID_MASTER"));
			irf.setDataEvaluate(rs.getString("DATA_EVALUATE"));
			// vo.setCreatedDateDesc(ConvertDateUtils.formatDateToString(rs
			// .getDate("CREATED_DATE"), ConvertDateUtils.DD_MM_YYYY,
			// ConvertDateUtils.LOCAL_TH));
			LocalDateTime createdDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE"));
			irf.setCreatedDate(createdDate);

			irf.setUpdatedBy(rs.getString("UPDATED_BY"));

			LocalDateTime updatedDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE"));
			irf.setUpdatedDate(updatedDate);

			vo.setCreatedDateDesc(ConvertDateUtils.formatDateToString(rs.getDate("CREATED_DATE"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setUpdateDateDesc(ConvertDateUtils.formatDateToString(rs.getDate("UPDATED_DATE"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setIaRiskFactors(irf);

			// vo.setUpdateDateDesc(ConvertDateUtils.formatDateToString(rs
			// .getDate("UPDATED_DATE"), ConvertDateUtils.DD_MM_YYYY,
			// ConvertDateUtils.LOCAL_TH));
//			setentityIaRiskFactorsConfig		

			irfc.setId(rs.getBigDecimal("ID_CONFIG"));
			irfc.setStartDate(rs.getDate("START_DATE"));
			irfc.setEndDate(rs.getDate("END_DATE"));

			irfc.setInfoUsedRisk(rs.getString("INFO_USED_RISK"));
			irfc.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));

			irfc.setIdFactors(rs.getBigDecimal("ID_FACTORS"));
			irfc.setFactorsLevel(rs.getBigDecimal("FACTORS_LEVEL"));
			
			irfc.setVerylow(rs.getString("VERYLOW"));
			irfc.setVerylowStart(rs.getString("VERYLOW_START"));
			irfc.setVerylowEnd(rs.getString("VERYLOW_END"));
			irfc.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));
			irfc.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			irfc.setVerylowColor(rs.getString("VERYLOW_COLOR"));

			irfc.setLow(rs.getString("LOW"));
			irfc.setLowStart(rs.getString("LOW_START"));
			irfc.setLowEnd(rs.getString("LOW_END"));
			irfc.setLowCondition(rs.getString("LOW_CONDITION"));
			irfc.setLowRating(rs.getBigDecimal("LOW_RATING"));
			irfc.setLowColor(rs.getString("LOW_COLOR"));
			
			irfc.setMedium(rs.getString("MEDIUM"));
			irfc.setMediumStart(rs.getString("MEDIUM_START"));
			irfc.setMediumEnd(rs.getString("MEDIUM_END"));
			irfc.setMediumCondition(rs.getString("MEDIUM_CONDITION"));
			irfc.setMediumRating(rs.getBigDecimal("MEDIUM_RATING"));
			irfc.setMediumColor(rs.getString("MEDIUM_COLOR"));
			
			irfc.setHigh(rs.getString("HIGH"));
			irfc.setHighStart(rs.getString("HIGH_START"));
			irfc.setHighEnd(rs.getString("HIGH_END"));
			irfc.setHighCondition(rs.getString("HIGH_CONDITION"));
			irfc.setHighRating(rs.getBigDecimal("HIGH_RATING"));
			irfc.setHighColor(rs.getString("High_COLOR"));
			
			irfc.setVeryhigh(rs.getString("VERYHIGH"));
			irfc.setVeryhighStart(rs.getString("VERYHIGH_START"));
			irfc.setVeryhighEnd(rs.getString("VERYHIGH_END"));
			irfc.setVeryhighCondition(rs.getString("VERYHIGH_CONDITION"));
			irfc.setVeryhighRating(rs.getBigDecimal("VERYHIGH_RATING"));
			irfc.setVeryhighColor(rs.getString("VERYHIGH_COLOR"));
			
			irfc.setRiskUnit(rs.getString("RISK_UNIT"));
			irfc.setPercent(rs.getBigDecimal("PERCENT"));

			vo.setIaRiskFactorsConfig(irfc);
			return vo;
		}
	};
	
	public List<Int030405Vo> findByStartMonthByEndMonthGroup(String startMonth,String endMonth) {
		List<Int030405Vo> res = new ArrayList<Int030405Vo>();

		StringBuilder sql = new StringBuilder(" SELECT e.SYSTEM_CODE,e.SYSTEM_NAME " + 
				"   ,SUM(e.COUNT_ALL) AS SUM_COUNT_ALL,SUM(e.COUNT_NORMAL)AS SUM_COUNT_NORMAL,SUM(e.COUNT_ERROR)AS SUM_COUNT_ERROR " +
				"	FROM IA_RISK_SYSTEM_UNWORKING e " + 
				"	WHERE CONCAT(e.YEAR,e.MONTH) >= ? " + 
				"	AND CONCAT(e.YEAR,e.MONTH)   <= ? GROUP BY e.SYSTEM_CODE,e.SYSTEM_NAME ");
		List<Object> params = new ArrayList<Object>();

		params.add(startMonth);
		params.add(endMonth);
		
		res = commonJdbcTemplate.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(Int030405Vo.class));
		
		return res;
	}
	
	public void updateStartDate(Int030405FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("     UPDATE IA_RISK_FACTORS_CONFIG C   ");
		sql.append("      SET C.START_DATE   = ? ,         ");
		sql.append("      C.END_DATE         = ?           ");
		sql.append("      WHERE C.ID = ?                   ");	
		Date start = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		Date end = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		commonJdbcTemplate.update(sql.toString(), new Object[] { start , end, form.getId()});
	}
	
	
	public List<Int030405Vo> getSystemByYear(Date date) {
		List<Int030405Vo> res = new ArrayList<Int030405Vo>();

		StringBuilder sql = new StringBuilder(" SELECT SYSTEM_CODE,SYSTEM_NAME FROM ( " + 
				"      SELECT e.SYSTEM_CODE, " + 
				"      e.SYSTEM_NAME, " + 
				"      COUNT(sc.SYSTEM_CODE) COUNT " + 
				"      FROM IA_RISK_SYSTEM_UNWORKING e " + 
				"      LEFT JOIN ( SELECT * " + 
				"                  FROM ia_risk_SELECT_CASE sc " + 
				"                  WHERE INSPECTION_WORK = 4 " + 
				"                  AND BUDGET_YEAR       = ? ) sc " + 
				"      ON e.SYSTEM_CODE=sc.SYSTEM_CODE " + 
				"      WHERE e.YEAR    =  ? " + 
				"      GROUP BY e.SYSTEM_CODE,e.SYSTEM_NAME ) " + 
				"	   where COUNT=0 ");
		List<Object> params = new ArrayList<Object>();

		params.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));
		params.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));
		
		res = commonJdbcTemplate.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(Int030405Vo.class));
		
		return res;
	}
	

}
