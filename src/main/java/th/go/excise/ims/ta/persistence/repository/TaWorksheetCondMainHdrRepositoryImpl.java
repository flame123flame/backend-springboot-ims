package th.go.excise.ims.ta.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

public class TaWorksheetCondMainHdrRepositoryImpl implements TaWorksheetCondMainHdrRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaWorksheetCondMainHdrRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public YearMonthVo findMonthStartByAnalysisNumber(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT C_M_HDR.YEAR_MONTH_START ");
		sql.append("   ,C_M_HDR.YEAR_MONTH_END ");
		sql.append("   ,C_M_HDR.MONTH_NUM AS MONTH_TOTAL ");
		sql.append("   ,TO_NUMBER(SUBSTR(C_M_HDR.YEAR_MONTH_START,5,2)) AS MONTH_START ");
		sql.append("   ,W_HDR.COND_SUB_CAPITAL_FLAG ");
		sql.append("   ,W_HDR.COND_SUB_RISK_FLAG ");
		sql.append("   ,W_HDR.COND_SUB_NO_AUDIT_FLAG ");
		sql.append("   ,W_HDR.WORKSHEET_STATUS ");
		sql.append(" FROM TA_WORKSHEET_COND_MAIN_HDR C_M_HDR ");
		sql.append(" INNER JOIN TA_WORKSHEET_HDR W_HDR ");
		sql.append(" ON W_HDR.ANALYSIS_NUMBER=C_M_HDR.ANALYSIS_NUMBER ");
		sql.append(" WHERE C_M_HDR.IS_DELETED    = ?");
		sql.append(" AND C_M_HDR.ANALYSIS_NUMBER = ? ");
		
		YearMonthVo yearMonthVo = null;
		try {
			yearMonthVo = commonJdbcTemplate.queryForObject(
				sql.toString(),
				new Object[] {
					FLAG.N_FLAG,
					StringUtils.defaultIfBlank(analysisNumber, "")
				},
				new BeanPropertyRowMapper<YearMonthVo>(YearMonthVo.class)
			);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			yearMonthVo = new YearMonthVo();
		}
		
		return yearMonthVo;
	}

}
