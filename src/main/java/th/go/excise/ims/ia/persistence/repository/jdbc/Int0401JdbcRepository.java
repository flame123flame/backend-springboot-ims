package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.Int0401CalDataVo;
import th.go.excise.ims.ia.vo.Int0401CalVo;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401ListVo;
import th.go.excise.ims.ia.vo.Int0401Vo;

@Repository
public class Int0401JdbcRepository {

	@Autowired
	private CommonJdbcTemplate jdbcTemplate;

	// ROW WITHOUT STATUS
	public List<IaRiskSelectCase> findRowWithoutStatus(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" SELECT S.* FROM IA_RISK_SELECT_CASE S WHERE S.BUDGET_YEAR = ? AND S.INSPECTION_WORK = ? AND S.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		List<IaRiskSelectCase> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				rowWithoutStatusRowMapper);
		return lists;
	}
	
	public long findCountRowWithoutStatus(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" SELECT COUNT(*) FROM IA_RISK_SELECT_CASE S WHERE S.BUDGET_YEAR = ? AND S.INSPECTION_WORK = ? AND S.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		long lists = jdbcTemplate.queryForObject(sqlBuilder.toString(), params.toArray(),long.class);
		return lists;
	}

	// ROW WITHOUT STATUS RowMapper
	private RowMapper<IaRiskSelectCase> rowWithoutStatusRowMapper = new RowMapper<IaRiskSelectCase>() {
		@Override
		public IaRiskSelectCase mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskSelectCase vo = new IaRiskSelectCase();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			
			vo.setProject(rs.getString("PROJECT"));
			vo.setProjectCode(rs.getString("PROJECT_CODE"));
			
			vo.setExciseCode(rs.getString("EXCISE_CODE"));
			vo.setSector(rs.getString("SECTOR"));
			vo.setArea(rs.getString("AREA"));
			
			vo.setSystemCode(rs.getString("SYSTEM_CODE"));
			vo.setSystemName(rs.getString("SYSTEM_NAME"));
			
			vo.setStatus(rs.getString("STATUS"));
			return vo;
		}
	};

	// ROW
	public List<IaRiskSelectCase> findRow(String budgetYear, BigDecimal inspectionWork, String status) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" SELECT S.* FROM IA_RISK_SELECT_CASE S WHERE S.BUDGET_YEAR = ? AND S.INSPECTION_WORK = ? AND S.STATUS = ? AND S.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		params.add(status);
		List<IaRiskSelectCase> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), rowRowMapper);
		return lists;
	}

	// ROW RowMapper
	private RowMapper<IaRiskSelectCase> rowRowMapper = new RowMapper<IaRiskSelectCase>() {
		@Override
		public IaRiskSelectCase mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskSelectCase vo = new IaRiskSelectCase();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			
			vo.setProjectCode(rs.getString("PROJECT_CODE"));
			vo.setProject(rs.getString("PROJECT"));
			
			vo.setExciseCode(rs.getString("EXCISE_CODE"));
			vo.setSector(rs.getString("SECTOR"));
			vo.setArea(rs.getString("AREA"));
			
			vo.setSystemCode(rs.getString("SYSTEM_CODE"));
			vo.setSystemName(rs.getString("SYSTEM_NAME"));
			
			vo.setStatus(rs.getString("STATUS"));
			
			return vo;
		}
	};

	// HEAD
	public List<Int0401HeaderVo> findHead(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT  F.RISK_FACTORS AS NAME, " + 
				"        C.INFO_USED_RISK_DESC AS DATA_CAL, " + 
				"        C.PERCENT AS PERCENT2,  " + 
				"        F.*  " + 
				"FROM IA_RISK_FACTORS F " + 
				"LEFT JOIN IA_RISK_FACTORS_CONFIG C " + 
				"ON F.ID               = C.ID_FACTORS " + 
				"WHERE F.BUDGET_YEAR   = ? " + 
				"AND F.INSPECTION_WORK = ? " + 
				"AND F.IS_DELETED      = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		List<Int0401HeaderVo> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), headRowMapper);
		return lists;
	}

	// HEAD RowMapper
	private RowMapper<Int0401HeaderVo> headRowMapper = new RowMapper<Int0401HeaderVo>() {
		@Override
		public Int0401HeaderVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0401HeaderVo vo = new Int0401HeaderVo();
			IaRiskFactors iaRiskFactors = new IaRiskFactors();
			
			vo.setName(rs.getString("NAME"));
			vo.setDataCal(rs.getString("DATA_CAL"));
			vo.setPercent(rs.getBigDecimal("PERCENT2"));
			
			iaRiskFactors.setId(rs.getBigDecimal("ID"));
			iaRiskFactors.setIdMaster(rs.getBigDecimal("ID_MASTER"));
			iaRiskFactors.setBudgetYear(rs.getString("BUDGET_YEAR"));
			iaRiskFactors.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			iaRiskFactors.setRiskFactors(rs.getString("RISK_FACTORS"));
			iaRiskFactors.setSide(rs.getString("SIDE"));
			iaRiskFactors.setStatusScreen(rs.getString("STATUS_SCREEN"));
			iaRiskFactors.setDataEvaluate(rs.getString("DATA_EVALUATE"));
			
			vo.setIaRiskFactors(iaRiskFactors);
			
			
			return vo;
		}
	};

	// DETAILS
	public List<Int0401CalVo> findDetails(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT C.ID_FACTORS AS C_ID_FACTORS,C.ID AS C_ID_CONFIG,  C.*, D.*, ");
		sqlBuilder.append(" D.ID_FACTORS AS D_ID_FACTORS FROM IA_RISK_FACTORS F ");
		sqlBuilder.append(" INNER JOIN IA_RISK_FACTORS_DATA D ON D.ID_FACTORS = F.ID ");
		sqlBuilder.append(" INNER JOIN IA_RISK_FACTORS_CONFIG C ON F.ID = C.ID_FACTORS ");
		sqlBuilder.append(" WHERE F.BUDGET_YEAR = ? AND F.INSPECTION_WORK = ? ");
		sqlBuilder.append(" AND C.IS_DELETED = 'N' AND D.IS_DELETED = 'N' AND F.IS_DELETED = 'N' ");
		sqlBuilder.append(" AND C.MEDIUM IS NOT NULL ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		List<Int0401CalVo> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), detailsRowMapper);
		return lists;
	}

	// DETAILS RowMapper
	private RowMapper<Int0401CalVo> detailsRowMapper = new RowMapper<Int0401CalVo>() {
		@Override
		public Int0401CalVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0401CalVo vo = new Int0401CalVo();
			Int0401CalDataVo data = new Int0401CalDataVo();
			data.setIdFactors(rs.getBigDecimal("D_ID_FACTORS"));
			data.setIdSelect(rs.getBigDecimal("ID_SELECT"));
			data.setBudgetYear(rs.getString("BUDGET_YEAR"));
			data.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			data.setProject(rs.getString("PROJECT"));
			data.setExciseCode(rs.getString("EXCISE_CODE"));
			data.setSector(rs.getString("SECTOR"));
			data.setArea(rs.getString("AREA"));
			data.setRiskCost(rs.getString("RISK_COST"));
			data.setRiskRate(rs.getString("RISK_RATE"));
			data.setRiskStep(rs.getString("RISK_STEP"));

			Int0401CalConfigVo config = new Int0401CalConfigVo();
			config.setId(rs.getBigDecimal("C_ID_CONFIG"));
			config.setIdFactors(rs.getBigDecimal("C_ID_FACTORS"));
			config.setFactorsLevel(rs.getBigDecimal("FACTORS_LEVEL"));
			config.setStartDate(rs.getDate("START_DATE"));
			config.setEndDate(rs.getDate("END_DATE"));
			config.setInfoUsedRisk(rs.getString("INFO_USED_RISK"));
			config.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));

			config.setVerylow(rs.getString("VERYLOW"));
			config.setVerylowStart(rs.getString("VERYLOW_START"));
			config.setVerylowEnd(rs.getString("VERYLOW_END"));
			config.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			config.setVerylowColor(rs.getString("VERYLOW_COLOR"));
			config.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));

			config.setLow(rs.getString("LOW"));
			config.setLowStart(rs.getString("LOW_START"));
			config.setLowEnd(rs.getString("LOW_END"));
			config.setLowRating(rs.getBigDecimal("LOW_RATING"));
			config.setLowColor(rs.getString("LOW_COLOR"));
			config.setLowCondition(rs.getString("LOW_CONDITION"));

			config.setMedium(rs.getString("MEDIUM"));
			config.setMediumStart(rs.getString("MEDIUM_START"));
			config.setMediumEnd(rs.getString("MEDIUM_END"));
			config.setMediumRating(rs.getBigDecimal("MEDIUM_RATING"));
			config.setMediumColor(rs.getString("MEDIUM_COLOR"));
			config.setMediumCondition(rs.getString("MEDIUM_CONDITION"));

			config.setHigh(rs.getString("HIGH"));
			config.setHighStart(rs.getString("HIGH_START"));
			config.setHighEnd(rs.getString("HIGH_END"));
			config.setHighRating(rs.getBigDecimal("HIGH_RATING"));
			config.setHighColor(rs.getString("HIGH_COLOR"));
			config.setHighCondition(rs.getString("HIGH_CONDITION"));

			config.setVeryhigh(rs.getString("VERYHIGH"));
			config.setVeryhighStart(rs.getString("VERYHIGH_START"));
			config.setVeryhighEnd(rs.getString("VERYHIGH_END"));
			config.setVeryhighRating(rs.getBigDecimal("VERYHIGH_RATING"));
			config.setVeryhighColor(rs.getString("VERYHIGH_COLOR"));
			config.setVeryhighCondition(rs.getString("VERYHIGH_CONDITION"));

			config.setRiskUnit(rs.getString("RISK_UNIT"));
			config.setPercent(rs.getBigDecimal("PERCENT"));

			vo.setConfig(config);
			vo.setData(data);
			return vo;
		}
	};

	// CONFIG ALL
	public Int0401CalConfigVo findConfigAll(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT C.* FROM IA_RISK_FACTORS_CONFIG_ALL C ");
		sqlBuilder.append(" WHERE C.IS_DELETED = 'N' AND C.BUDGET_YEAR = ? AND C.INSPECTION_WORK = ? ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		Int0401CalConfigVo config = jdbcTemplate.queryForObject(sqlBuilder.toString(), params.toArray(), configAllRowMapper);
		return config;
	}

	// CONFIG ALL RowMapper
	private RowMapper<Int0401CalConfigVo> configAllRowMapper = new RowMapper<Int0401CalConfigVo>() {
		@Override
		public Int0401CalConfigVo mapRow(ResultSet rs, int arg1) throws SQLException {

			Int0401CalConfigVo config = new Int0401CalConfigVo();
			config.setFactorsLevel(rs.getBigDecimal("FACTORS_LEVEL"));

			config.setVerylow(rs.getString("VERYLOW"));
			config.setVerylowStart(rs.getString("VERYLOW_START"));
			config.setVerylowEnd(rs.getString("VERYLOW_END"));
			config.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			config.setVerylowColor(rs.getString("VERYLOW_COLOR"));
			config.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));

			config.setLow(rs.getString("LOW"));
			config.setLowStart(rs.getString("LOW_START"));
			config.setLowEnd(rs.getString("LOW_END"));
			config.setLowRating(rs.getBigDecimal("LOW_RATING"));
			config.setLowColor(rs.getString("LOW_COLOR"));
			config.setLowCondition(rs.getString("LOW_CONDITION"));

			config.setMedium(rs.getString("MEDIUM"));
			config.setMediumStart(rs.getString("MEDIUM_START"));
			config.setMediumEnd(rs.getString("MEDIUM_END"));
			config.setMediumRating(rs.getBigDecimal("MEDIUM_RATING"));
			config.setMediumColor(rs.getString("MEDIUM_COLOR"));
			config.setMediumCondition(rs.getString("MEDIUM_CONDITION"));

			config.setHigh(rs.getString("HIGH"));
			config.setHighStart(rs.getString("HIGH_START"));
			config.setHighEnd(rs.getString("HIGH_END"));
			config.setHighRating(rs.getBigDecimal("HIGH_RATING"));
			config.setHighColor(rs.getString("HIGH_COLOR"));
			config.setHighCondition(rs.getString("HIGH_CONDITION"));

			config.setVeryhigh(rs.getString("VERYHIGH"));
			config.setVeryhighStart(rs.getString("VERYHIGH_START"));
			config.setVeryhighEnd(rs.getString("VERYHIGH_END"));
			config.setVeryhighRating(rs.getBigDecimal("VERYHIGH_RATING"));
			config.setVeryhighColor(rs.getString("VERYHIGH_COLOR"));
			config.setVeryhighCondition(rs.getString("VERYHIGH_CONDITION"));

			return config;
		}
	};

}
