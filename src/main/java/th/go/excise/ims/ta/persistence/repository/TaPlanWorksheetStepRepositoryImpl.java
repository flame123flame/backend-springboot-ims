package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ta.vo.AuditStepVo;

public class TaPlanWorksheetStepRepositoryImpl implements TaPlanWorksheetStepRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaPlanWorksheetStepRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<AuditStepVo> findByAuditPlanCode(String auditPlanCode) {
		logger.info("findByAuditPlanCode auditPlanCode={}", auditPlanCode);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PSTEP.AUDIT_PLAN_CODE, PSTEP.AUDIT_STEP_STATUS, PSTEP.AUDIT_STEP_SUB_STATUS, ");
		sql.append("   PSTEP.AUDIT_STEP_FLAG, PSTEP.FORM_TS_CODE, PSTEP.FORM_TS_NUMBER, PSTEP.PROCESS_DATE ");
		sql.append(" FROM TA_PLAN_WORKSHEET_STEP PSTEP ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT AUDIT_PLAN_CODE, AUDIT_STEP_STATUS, MAX(FORM_TS_NUMBER) AS FORM_TS_NUMBER ");
		sql.append("   FROM TA_PLAN_WORKSHEET_STEP ");
		sql.append("   WHERE IS_DELETED = 'N' ");
		sql.append("     AND AUDIT_PLAN_CODE = ? ");
		sql.append("   GROUP BY AUDIT_PLAN_CODE, AUDIT_STEP_STATUS ");
		sql.append(" ) X ON PSTEP.AUDIT_PLAN_CODE = X.AUDIT_PLAN_CODE ");
		sql.append("   AND PSTEP.AUDIT_STEP_STATUS = X.AUDIT_STEP_STATUS ");
		sql.append("   AND PSTEP.FORM_TS_NUMBER = X.FORM_TS_NUMBER ");
		sql.append(" ORDER BY PSTEP.AUDIT_PLAN_CODE, PSTEP.AUDIT_STEP_STATUS, PSTEP.FORM_TS_NUMBER ");
		
		List<AuditStepVo> voList = commonJdbcTemplate.query(
			sql.toString(),
			new Object[] {
				auditPlanCode
			},
			new RowMapper<AuditStepVo>() {
				@Override
				public AuditStepVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					AuditStepVo vo = new AuditStepVo();
					vo.setAuditPlanCode(rs.getString("AUDIT_PLAN_CODE"));
					vo.setAuditStepStatus(rs.getString("AUDIT_STEP_STATUS"));
					vo.setAuditStepSubStatus(rs.getString("AUDIT_STEP_SUB_STATUS"));
					vo.setAuditStepFlag(rs.getString("AUDIT_STEP_FLAG"));
					vo.setFormTsCode(rs.getString("FORM_TS_CODE"));
					vo.setFormTsNumber(rs.getString("FORM_TS_NUMBER"));
					vo.setProcessDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("PROCESS_DATE")).toLocalDate());
					return vo;
				}
			}
		);
		
		return voList;
	}
	
}
