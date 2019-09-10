package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Repository
public class Int0301JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL_IA_RISK_FACTORS = " SELECT a.* , " + " b.ID AS ID_CONFIG, " + " b.*"
			+ "FROM IA_RISK_FACTORS a " + "LEFT JOIN IA_RISK_FACTORS_CONFIG b "
			+ "ON a.ID = b.ID_FACTORS WHERE a.INSPECTION_WORK = ? AND a.IS_DELETED = 'N' ";

	public List<Int0301Vo> list(Int0301FormVo form) {
		List<Int0301Vo> int0301VoList = new ArrayList<Int0301Vo>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getInspectionWork());

		if (StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		int0301VoList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return int0301VoList;
	}
	
	public List<Int0301Vo> listInt0304(Int0301FormVo form) {
		List<Int0301Vo> int0301VoList = new ArrayList<Int0301Vo>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getInspectionWork());

		if (StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		sql.append(" AND STATUS_SCREEN = ? ");
		params.add(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_3_CODE);
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
			   
			   
			  if(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_1_CODE.equals(irf.getStatusScreen())) {
				  vo.setStatusScreenDesc(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_1_DESC);
			  }	else if(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_2_CODE.equals(irf.getStatusScreen())) {
				  vo.setStatusScreenDesc(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_2_DESC);
			  }	else if(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_3_CODE.equals(irf.getStatusScreen())) {
				  vo.setStatusScreenDesc(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_3_DESC);
			  }			   

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
			   irfc.setRiskIndicators(rs.getString("RISK_INDICATORS"));
			   vo.setIaRiskFactorsConfig(irfc);
			   return vo;
		}
	};

	public void saveRiskFactorsLevel(Int0301FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("   UPDATE IA_RISK_FACTORS_CONFIG C                       ");
		sql.append("   SET C.FACTORS_LEVEL   = ? ,                           ");
		sql.append("   C.VERYLOW             = null,                         ");
		sql.append("   C.VERYLOW_START       = null,                         ");
		sql.append("   C.VERYLOW_END         = null,                         ");
		sql.append("   C.VERYLOW_RATING      = null,                         ");
		sql.append("   C.VERYLOW_COLOR       = null,                         ");
		sql.append("   C.VERYLOW_CONDITION   = null,                         ");
		sql.append("   C.LOW                 = null,                         ");
		sql.append("   C.LOW_START           = null,                         ");
		sql.append("   C.LOW_END             = null,                         ");
		sql.append("   C.LOW_RATING          = null,                         ");
		sql.append("   C.LOW_COLOR           = null,                         ");
		sql.append("   C.LOW_CONDITION       = null,                         ");
		sql.append("   C.MEDIUM              = null,                         ");
		sql.append("   C.MEDIUM_START        = null,                         ");
		sql.append("   C.MEDIUM_END          = null,                         ");
		sql.append("   C.MEDIUM_RATING       = null,                         ");
		sql.append("   C.MEDIUM_COLOR        = null,                         ");
		sql.append("   C.MEDIUM_CONDITION    = null,                         ");
		sql.append("   C.HIGH                = null,                         ");
		sql.append("   C.HIGH_START          = null,                         ");
		sql.append("   C.HIGH_END            = null,                         ");
		sql.append("   C.HIGH_RATING         = null,                         ");
		sql.append("   C.HIGH_COLOR          = null,                         ");
		sql.append("   C.HIGH_CONDITION      = null,                         ");
		sql.append("   C.VERYHIGH            = null,                         ");
		sql.append("   C.VERYHIGH_START      = null,                         ");
		sql.append("   C.VERYHIGH_END        = null,                         ");
		sql.append("   C.VERYHIGH_RATING     = null,                         ");
		sql.append("   C.VERYHIGH_COLOR      = null,                         ");
		sql.append("   C.VERYHIGH_CONDITION  = null                          ");
		sql.append("   WHERE C.ID IN (SELECT B.ID                            ");
		sql.append("                 FROM IA_RISK_FACTORS A                  ");
		sql.append("                 INNER JOIN IA_RISK_FACTORS_CONFIG B     ");
		sql.append("                 ON A.ID = B.ID_FACTORS                  ");
		sql.append("                 WHERE A.BUDGET_YEAR = ? )               ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getFactorsLevel(), form.getBudgetYear() });

	}
	

	public void claerDateCir(Int0301FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("    UPDATE IA_RISK_FACTORS C                                                  ");
		sql.append("    SET C.STATUS_SCREEN       = 'ยังไม่ได้กำหนด',                                         ");
		sql.append("    C.DATE_CRITERIA           = null                                          ");
		sql.append("    WHERE C.ID IN ( SELECT A.ID                                               ");
		sql.append("                    FROM IA_RISK_FACTORS A                                    ");
		sql.append("                    WHERE  A.BUDGET_YEAR = ? AND A.INSPECTION_WORK = ? )      ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getBudgetYear(), form.getInspectionWork() });
	}
	
	

}
