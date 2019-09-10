package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public class TaWorksheetHdrRepositoryImpl implements TaWorksheetHdrRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<TaWorksheetHdr> findAllAnalysisNumberHead(String officeCode,String budgetYear) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM TA_WORKSHEET_HDR ");
		sql.append(" WHERE  ( office_code = ? OR office_code = '001401' ) ");
		sql.append(" AND worksheet_status IN ( 'S', 'C' ) AND BUDGET_YEAR = ?  " );
		sql.append(" ORDER BY worksheet_status DESC ");
		paramList.add(officeCode);
		paramList.add(budgetYear);

		return commonJdbcTemplate.query(sql.toString(),paramList.toArray(),worksheetHdr);
//		return null;
	}
	private RowMapper<TaWorksheetHdr> worksheetHdr = new RowMapper<TaWorksheetHdr>() {
		@Override
		public TaWorksheetHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaWorksheetHdr vo = new TaWorksheetHdr();
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setWorksheetStatus(rs.getString("WORKSHEET_STATUS"));
			return vo;
		}
	};

}
