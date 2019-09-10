package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.vo.Int020101NameVo;
import th.go.excise.ims.ia.vo.Int020101Vo;
import th.go.excise.ims.ia.vo.Int020101YearVo;
import th.go.excise.ims.preferences.vo.ExcelHeaderNameVo;

@Repository
public class IaQuestionnaireSideJdbcRepository {

	private static final Logger logger = LoggerFactory.getLogger(IaQuestionnaireSideJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int020101Vo> findAll() {
		String sql = " SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE IS_DELETED = 'N' ";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020101Vo> datas = commonJdbcTemplate.query(sql, new BeanPropertyRowMapper(Int020101Vo.class));
		return datas;
	}

	public List<Int020101Vo> findByIdHead(BigDecimal idHead) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT S.ID, ");
		sqlBuilder.append("   S.ID_HEAD, ");
		sqlBuilder.append("   S.SIDE_NAME, ");
		sqlBuilder.append("   (SELECT COUNT(1) ");
		sqlBuilder.append("   FROM IA_QUESTIONNAIRE_SIDE_DTL D ");
		sqlBuilder.append("   WHERE D.IS_DELETED = 'N' ");
		sqlBuilder.append("   AND D.ID_SIDE      = S.ID ");
		sqlBuilder.append("   ) AS QUANTITY ");
		sqlBuilder.append(" FROM IA_QUESTIONNAIRE_SIDE S ");
		sqlBuilder.append(" WHERE S.ID_HEAD  = ? ");
		sqlBuilder.append(" AND S.IS_DELETED = 'N' ");
		sqlBuilder.append(" ORDER BY S.SEQ ASC ");
		params.add(idHead);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int020101Vo.class));
		return datas;
	}

	public List<Int020101YearVo> findByUsername(String username) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT DISTINCT BUDGET_YEAR FROM IA_QUESTIONNAIRE_HDR WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND CREATED_BY = ? ");
		params.add(username);
		List<Int020101YearVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				int020101YearRowMapper);
		return datas;
	}

	public List<Int020101YearVo> findByStatus() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
//		sqlBuilder.append(" SELECT H.BUDGET_YEAR FROM IA_QUESTIONNAIRE_HDR H ");
//		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_MADE_HDR MH ");
//		sqlBuilder.append(" ON MH.ID_HDR = H.ID ");
//		sqlBuilder.append(" WHERE 1=1 AND H.IS_DELETED = 'N' AND H.USAGE_PATTERNS = ? " );
//		sqlBuilder.append(" GROUP BY H.BUDGET_YEAR ");
		sqlBuilder.append(" SELECT DISTINCT H.BUDGET_YEAR FROM IA_QUESTIONNAIRE_HDR H ");
		sqlBuilder.append(" WHERE 1=1 AND H.IS_DELETED = 'N' AND H.USAGE_PATTERNS = ? ");
		params.add(IaConstants.USAGE_PATTERNS_QTN.FACTOR_DESC);
		List<Int020101YearVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				int020101YearRowMapper);
		return datas;
	}

	private RowMapper<Int020101YearVo> int020101YearRowMapper = new RowMapper<Int020101YearVo>() {
		@Override
		public Int020101YearVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020101YearVo vo = new Int020101YearVo();
			vo.setYear(rs.getString("BUDGET_YEAR"));
			return vo;
		}
	};

	public List<Int020101NameVo> findByYearAndUsername(String year, String username) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND BUDGET_YEAR = ? ");
		sqlBuilder.append(" AND CREATED_BY = ? ");
		params.add(year);
		params.add(username);
		List<Int020101NameVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				int020101NameRowMapper);
		return datas;
	}

	public List<Int020101NameVo> findByYearAndStatus(String year, String id) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
//		sqlBuilder.append(" SELECT H.ID,H.QTN_HEADER_NAME FROM IA_QUESTIONNAIRE_HDR H ");
//		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_MADE_HDR MH ");
//		sqlBuilder.append(" ON MH.ID_HDR = H.ID ");
//		sqlBuilder.append(" WHERE 1=1 AND H.IS_DELETED = 'N' AND H.USAGE_PATTERNS = ? " );
//		sqlBuilder.append(" AND H.BUDGET_YEAR = ? ");
//		sqlBuilder.append(" GROUP BY H.ID,H.QTN_HEADER_NAME ");

//		sqlBuilder.append(" SELECT H.ID,H.QTN_HEADER_NAME FROM IA_QUESTIONNAIRE_HDR H ");
//		sqlBuilder.append(" WHERE 1=1 AND H.IS_DELETED = 'N' AND H.USAGE_PATTERNS = ? " );
//		sqlBuilder.append(" AND H.BUDGET_YEAR = ? ");
//		sqlBuilder.append(" GROUP BY H.ID,H.QTN_HEADER_NAME ");

		sqlBuilder.append(" SELECT A.* FROM IA_QUESTIONNAIRE_HDR A LEFT join IA_RISK_FACTORS_CONFIG B  ");
		sqlBuilder.append(" on A.id = B.INFO_USED_RISK ");
		sqlBuilder.append(" where A.USAGE_PATTERNS = ? AND A.BUDGET_YEAR=? And B.INFO_USED_RISK Is NULL ");

		params.add(IaConstants.USAGE_PATTERNS_QTN.FACTOR_DESC);
		params.add(year);
		
		
		if(!("null".equals(id))) {
			sqlBuilder.append(" OR A.ID=? ");
			params.add(id);
		}
		
		List<Int020101NameVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				int020101NameRowMapper);
		return datas;
	}

	private RowMapper<Int020101NameVo> int020101NameRowMapper = new RowMapper<Int020101NameVo>() {
		@Override
		public Int020101NameVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020101NameVo vo = new Int020101NameVo();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setName(rs.getString("QTN_HEADER_NAME"));
			return vo;
		}
	};

	public IaQuestionnaireSide findOne(BigDecimal id) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID = ? ");
		params.add(id);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		IaQuestionnaireSide data = (IaQuestionnaireSide) commonJdbcTemplate.queryForObject(sqlBuilder.toString(),
				params.toArray(), new BeanPropertyRowMapper(IaQuestionnaireSide.class));
		return data;
	}

	public List<IaQuestionnaireSideDtl> findBySideIds(List<BigDecimal> ids) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM IA_QUESTIONNAIRE_SIDE_DTL WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_SIDE IN (");
		sqlBuilder.append(StringUtils.join(ids, ", "));
		sqlBuilder.append(")");
		logger.info(" QUERY ", StringUtils.join(ids, ", "));
		List<IaQuestionnaireSideDtl> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				int020101RowMapper);
		return datas;
	}

	private RowMapper<IaQuestionnaireSideDtl> int020101RowMapper = new RowMapper<IaQuestionnaireSideDtl>() {
		@Override
		public IaQuestionnaireSideDtl mapRow(ResultSet rs, int arg1) throws SQLException {
			IaQuestionnaireSideDtl vo = new IaQuestionnaireSideDtl();
			vo.setIdSide(rs.getBigDecimal("ID_SIDE"));
			vo.setQtnLevel(rs.getBigDecimal("QTN_LEVEL"));
			vo.setSeq(rs.getBigDecimal("SEQ"));
			vo.setSeqDtl(rs.getBigDecimal("SEQ_DTL"));
			vo.setSideDtl(rs.getString("SIDE_DTL"));
			return vo;
		}
	};

	public List<ExcelHeaderNameVo> findNameByIdHdr(BigDecimal idHdr) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_HEAD = ? ");
		params.add(idHdr);
		List<ExcelHeaderNameVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				excelHeaderNameMapper);
		return datas;
	}

	private RowMapper<ExcelHeaderNameVo> excelHeaderNameMapper = new RowMapper<ExcelHeaderNameVo>() {
		@Override
		public ExcelHeaderNameVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ExcelHeaderNameVo vo = new ExcelHeaderNameVo();
			vo.setName(rs.getString("SIDE_NAME"));
			return vo;
		}
	};

	public Integer checkCountSide(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT COUNT(*) ");
		sql.append(" FROM  IA_QUESTIONNAIRE_SIDE S ");
		sql.append(" WHERE S.ID_HEAD  = ? ");
		sql.append(" AND S.IS_DELETED ='N' ");

		params.add(idHdr);

		Integer count = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);

		return count;
	}

	public Integer checkCountSideDtl(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT COUNT(*) AS COUNT FROM ( ");
		sql.append(" SELECT  S.ID ");
		sql.append("         ,COUNT(SD.ID_SIDE) AS COUNT ");
		sql.append(" FROM IA_QUESTIONNAIRE_SIDE S ");
		sql.append(" Full JOIN (SELECT * FROM IA_QUESTIONNAIRE_SIDE_DTL WHERE IS_DELETED='N') SD ");
		sql.append(" ON S.ID = SD.ID_SIDE ");
		sql.append(" WHERE S.ID_HEAD  = ? ");
		sql.append(" AND S.IS_DELETED ='N' ");
		sql.append(" GROUP BY S.ID) WHERE COUNT  = 0 ");

		params.add(idHdr);

		Integer count = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);

		return count;
	}

	public String checkUseQtn(BigDecimal idHead) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT COUNT(*) AS QUANTITY ");
		sqlBuilder.append(" FROM IA_QUESTIONNAIRE_HDR A ");
		sqlBuilder.append(" INNER JOIN IA_RISK_FACTORS_CONFIG B ");
		sqlBuilder.append(" ON A.ID = B.INFO_USED_RISK ");
		sqlBuilder.append(" WHERE B.INFO_USED_RISK = ? ");
		params.add(idHead);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int020101Vo.class));
		String data = datas.get(0).getQuantity().toString();
		return data;
	}

}
