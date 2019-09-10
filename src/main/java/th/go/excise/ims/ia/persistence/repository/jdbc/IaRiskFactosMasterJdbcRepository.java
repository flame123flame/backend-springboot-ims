package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
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
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Repository
public class IaRiskFactosMasterJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int030102Vo> listIgnoreIsDeleted(Int030102FormVo form) {
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		StringBuilder sql = new StringBuilder(" SELECT A.ID AS ID_MASTER_RES, " + 
				"  A.RISK_FACTORS_MASTER, " + 
				"  A.BUDGET_YEAR, " + 
				"  A.CREATED_DATE AS CREATED_DATE_RES, " + 
				"  A.CREATED_BY   AS CREATED_BY_RES, " + 
				"  A.NOT_DELETE, " +
				"  A.STATUS, " + 
				"  A.DATA_EVALUATE, "+
				"  A.SIDE, " + 
				"  A.INSPECTION_WORK, " + 
				"  B.*, " + 
				"  B.ID AS ID_CONFIG_RES " + 
				" FROM IA_RISK_FACTORS_MASTER A " + 
				" JOIN IA_RISK_FACTORS_MAS_CON B " + 
				" ON A.ID = B.ID_MASTER " + 
				" WHERE A.IS_DELETED = 'N' " + 
				" AND B.IS_DELETED   = 'N' " + 
				" AND A.BUDGET_YEAR = ? " + 
				" AND A.INSPECTION_WORK = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(form.getBudgetYear());
		params.add(form.getInspectionWork());
//		sql.append(" ORDER BY a.CREATED_DATE,a.ID ASC");
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaRiskFactorsMasterList;
	}

	private RowMapper<Int030102Vo> listRowmapper = new RowMapper<Int030102Vo>() {
		@Override
		public Int030102Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int030102Vo vo = new Int030102Vo();
			IaRiskFactorsMaster iarfm = new IaRiskFactorsMaster();
			IaRiskFactorsConfig iarfc = new IaRiskFactorsConfig();

			iarfm.setId(rs.getBigDecimal("ID_MASTER_RES"));
			iarfm.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			iarfm.setBudgetYear(rs.getString("BUDGET_YEAR"));
			iarfm.setStatus(rs.getString("STATUS"));
			LocalDateTime createdDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE_RES"));
			iarfm.setCreatedDate(createdDate);
			iarfm.setCreatedBy(rs.getString("CREATED_BY_RES"));
			iarfm.setNotDelete(rs.getString("NOT_DELETE"));
			iarfm.setDataEvaluate(rs.getString("DATA_EVALUATE"));
			iarfm.setSide(rs.getString("SIDE"));
			iarfm.setInspectionWork(new BigDecimal(rs.getString("INSPECTION_WORK")));

			String date = checkAndConvertDateToString(rs.getDate("CREATED_DATE_RES"));

			vo.setCreatedDateDesc(date);
			vo.setIaRiskFactorsMaster(iarfm);
			vo.setIdConfig(rs.getBigDecimal("ID_CONFIG_RES"));

			iarfc.setId(rs.getBigDecimal("ID_CONFIG_RES"));
//			iarfc.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));
//			iarfc.setStartDate(new Date(rs.getTimestamp("START_DATE").getTime()));
//			iarfc.setEndDate(new Date(rs.getTimestamp("END_DATE").getTime()));
//			iarfc.setFactorsLevel(rs.getBigDecimal("FACTORS_LEVEL"));

			iarfc.setStartDate(rs.getDate("START_DATE"));
			iarfc.setEndDate(rs.getDate("END_DATE"));

			iarfc.setInfoUsedRisk(rs.getString("INFO_USED_RISK"));
			iarfc.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));

//			iarfc.setIdFactors(rs.getBigDecimal("ID_FACTORS"));
			iarfc.setFactorsLevel(rs.getBigDecimal("FACTORS_LEVEL"));

			iarfc.setVerylow(rs.getString("VERYLOW"));
			iarfc.setVerylowStart(rs.getString("VERYLOW_START"));
			iarfc.setVerylowEnd(rs.getString("VERYLOW_END"));
			iarfc.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));
			iarfc.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			iarfc.setVerylowColor(rs.getString("VERYLOW_COLOR"));

			iarfc.setLow(rs.getString("LOW"));
			iarfc.setLowStart(rs.getString("LOW_START"));
			iarfc.setLowEnd(rs.getString("LOW_END"));
			iarfc.setLowCondition(rs.getString("LOW_CONDITION"));
			iarfc.setLowRating(rs.getBigDecimal("LOW_RATING"));
			iarfc.setLowColor(rs.getString("LOW_COLOR"));

			iarfc.setMedium(rs.getString("MEDIUM"));
			iarfc.setMediumStart(rs.getString("MEDIUM_START"));
			iarfc.setMediumEnd(rs.getString("MEDIUM_END"));
			iarfc.setMediumCondition(rs.getString("MEDIUM_CONDITION"));
			iarfc.setMediumRating(rs.getBigDecimal("MEDIUM_RATING"));
			iarfc.setMediumColor(rs.getString("MEDIUM_COLOR"));

			iarfc.setHigh(rs.getString("HIGH"));
			iarfc.setHighStart(rs.getString("HIGH_START"));
			iarfc.setHighEnd(rs.getString("HIGH_END"));
			iarfc.setHighCondition(rs.getString("HIGH_CONDITION"));
			iarfc.setHighRating(rs.getBigDecimal("HIGH_RATING"));
			iarfc.setHighColor(rs.getString("High_COLOR"));

			iarfc.setVeryhigh(rs.getString("VERYHIGH"));
			iarfc.setVeryhighStart(rs.getString("VERYHIGH_START"));
			iarfc.setVeryhighEnd(rs.getString("VERYHIGH_END"));
			iarfc.setVeryhighCondition(rs.getString("VERYHIGH_CONDITION"));
			iarfc.setVeryhighRating(rs.getBigDecimal("VERYHIGH_RATING"));
			iarfc.setVeryhighColor(rs.getString("VERYHIGH_COLOR"));

			iarfc.setRiskUnit(rs.getString("RISK_UNIT"));
			iarfc.setPercent(rs.getBigDecimal("PERCENT"));
			iarfc.setRiskIndicators(rs.getString("RISK_INDICATORS"));
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

}
