package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;

@Repository
public class Int020301JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int020301HeaderVo> findHeaderByIdSide(BigDecimal idSide, String budgetYear) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT IQS.SIDE_NAME ,IQH.CONCLUDE FROM IA_QUESTIONNAIRE_HDR IQH ");
		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE IQS ");
		sqlBuilder.append(" ON IQH.ID = IQS.ID_HEAD WHERE 1=1 AND IQH.IS_DELETED = 'N' AND IQS.IS_DELETED = 'N' ");
		sqlBuilder.append(" AND IQH.ID = ? ");
		sqlBuilder.append(" AND IQH.BUDGET_YEAR = ? ");
		sqlBuilder.append(" ORDER BY  IQS.SEQ ASC ");
		
		List<Object> params = new ArrayList<>();
		params.add(idSide);
		params.add(budgetYear);
		List<Int020301HeaderVo> data = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
				headerRowMapper);
		return data;
	}

	private RowMapper<Int020301HeaderVo> headerRowMapper = new RowMapper<Int020301HeaderVo>() {
		@Override
		public Int020301HeaderVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020301HeaderVo vo = new Int020301HeaderVo();
			vo.setName(rs.getString("SIDE_NAME"));
			vo.setConclude(rs.getString("CONCLUDE"));
			return vo;
		}
	};



	public List<Int020301InfoVo> findInfoByIdSide(BigDecimal idSide, String budgetYear, String secter) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" select qmh.OFFICE_CODE as OFFICE_CODE, qmh.UPDATED_DATE as UPDATED_DATE, qmh.STATUS as STATUS,qmh.ID AS ID_MADE_HDR,qmh.UPDATED_BY AS UPDATED_BY from IA_QUESTIONNAIRE_HDR qhr ");
		sqlBuilder.append(" inner join IA_QUESTIONNAIRE_MADE_HDR qmh on qmh.ID_HDR = qhr.ID ");
		sqlBuilder.append(" where qhr.BUDGET_YEAR = ? and qhr.ID = ? AND qhr.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(idSide);
		if (!"all".equals(secter) && secter != null && !StringUtils.isEmpty(secter)) {
			sqlBuilder.append(" AND qmh.IS_DELETED = 'N'AND qmh.OFFICE_CODE like ? ");
			params.add(secter.substring(0, 2) + "%");
		}
		sqlBuilder.append(" ORDER BY qmh.OFFICE_CODE ASC ");
		List<Int020301InfoVo> data = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), infoRowMapper);
		return data;
	}

	private RowMapper<Int020301InfoVo> infoRowMapper = new RowMapper<Int020301InfoVo>() {
		@Override
		public Int020301InfoVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020301InfoVo vo = new Int020301InfoVo();
			vo.setIdMadeHdr(rs.getBigDecimal("ID_MADE_HDR"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setSectorName(rs.getString("OFFICE_CODE"));
			vo.setAreaName(rs.getString("OFFICE_CODE"));
			vo.setSentDate(rs.getDate("UPDATED_DATE"));
			vo.setSentBy(rs.getString("UPDATED_BY"));
			vo.setStatusText(rs.getString("STATUS"));
			vo.setStatus(rs.getString("STATUS"));
			return vo;
		}
	};

	public List<Int020301DataVo> findDataByIdHdr(BigDecimal idHdr, String budgetYear, String officeCode) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT QSR.ID AS ID,QSR.SEQ, ");
		sqlBuilder.append(" (SELECT COUNT(1) FROM IA_QUESTIONNAIRE_SIDE_DTL QDL ");
		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_MADE QME ON QME.ID_SIDE_DTL = QDL.ID AND QME.OFFICE_CODE = ? ");
		sqlBuilder.append(" WHERE QME.IS_DELETED = 'N' AND QME.CHECK_FLAG = 'T' AND QME.STATUS = ? ");
		sqlBuilder.append(" AND QDL.ID_SIDE = QSR.ID GROUP BY QME.ID_MADE_HDR) AS ACCEPT, ");
		sqlBuilder.append(" (SELECT COUNT(1) FROM IA_QUESTIONNAIRE_SIDE_DTL QDL2 ");
		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_MADE QME2 ON QME2.ID_SIDE_DTL = QDL2.ID AND QME2.OFFICE_CODE = ? ");
		sqlBuilder.append(" WHERE QME2.IS_DELETED = 'N' AND QME2.CHECK_FLAG = 'F' AND QME2.STATUS = ? ");
		sqlBuilder.append(" AND QDL2.ID_SIDE = QSR.ID GROUP BY QME2.ID_MADE_HDR) AS DECLINE ");
		sqlBuilder.append(" FROM IA_QUESTIONNAIRE_HDR QHR ");
		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE QSR ON QSR.ID_HEAD = QHR.ID ");
		sqlBuilder.append(" WHERE 1=1 AND QHR.IS_DELETED = 'N' AND QSR.IS_DELETED = 'N' AND QHR.ID = ? AND QHR.BUDGET_YEAR = ? ");
		sqlBuilder.append(" GROUP BY QSR.ID,QSR.SEQ ORDER BY QSR.SEQ ASC");
		List<Object> params = new ArrayList<>();
		params.add(officeCode);
		params.add(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE);
		params.add(officeCode);
		params.add(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE);
		params.add(idHdr);
		params.add(budgetYear);
		List<Int020301DataVo> data = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), dataRowMapper);
		return data;
	}

	private RowMapper<Int020301DataVo> dataRowMapper = new RowMapper<Int020301DataVo>() {
		@Override
		public Int020301DataVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020301DataVo vo = new Int020301DataVo();
			vo.setAcceptValue(rs.getBigDecimal("ACCEPT"));
			vo.setDeclineValue(rs.getBigDecimal("DECLINE"));
			return vo;
		}
	};

}
