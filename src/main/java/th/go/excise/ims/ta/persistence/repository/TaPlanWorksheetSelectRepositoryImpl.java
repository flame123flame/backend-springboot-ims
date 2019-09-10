package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.vo.TaxDraftVo;

public class TaPlanWorksheetSelectRepositoryImpl implements TaPlanWorksheetSelectRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(String budgetYear, List<TaxDraftVo> taxDraftVoList) {
		String sql = SqlGeneratorUtils.genSqlInsert(
			"TA_PLAN_WORKSHEET_SELECT",
			Arrays.asList(
				"PLAN_WORKSHEET_SELECT_ID", "BUDGET_YEAR", "NEW_REG_ID", "DUTY_GROUP_ID", "CREATED_BY", "CREATED_DATE"
			),
			"TA_PLAN_WORKSHEET_SELECT_SEQ"
		);
		
		String username = UserLoginUtils.getCurrentUsername();
		LocalDateTime createdDate = LocalDateTime.now();

		commonJdbcTemplate.batchUpdate(sql, taxDraftVoList, 1000, new ParameterizedPreparedStatementSetter<TaxDraftVo>() {
			public void setValues(PreparedStatement ps, TaxDraftVo taxDraftVo) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(budgetYear);
				paramList.add(taxDraftVo.getNewRegId());
				paramList.add(taxDraftVo.getDutyCode());
				paramList.add(username);
				paramList.add(createdDate);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public Integer findCentalAllSend() {
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		
		sql.append( " SELECT  COUNT(1) COUNT_SUBMIT FROM TA_PLAN_WORKSHEET_SEND SEND   " );     
		sql.append( " INNER JOIN EXCISE_SUBDEPT SUBD ON " );
		sql.append( " SUBD.OFF_CODE = SEND.OFFICE_CODE     " );                   
		sql.append( " WHERE SUBD.SUBDEPT_CODE IS NULL AND SUBD.AUDIT_SELECT_FLAG = ? AND SEND.SUBMIT_DATE IS NULL " );
		
		paramList.add(FLAG.Y_FLAG);
//		paramList.add(ProjectConstants.EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR1);
//		paramList.add(ProjectConstants.EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR2);
//		paramList.add(ProjectConstants.EXCISE_OFFICE_CODE.TA_CENTRAL_SELECTOR);
		
		return commonJdbcTemplate.queryForObject(sql.toString(), paramList.toArray(), Integer.class);
	}

}
