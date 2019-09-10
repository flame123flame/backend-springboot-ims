package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.IaAuditPmassessDVo;

public class IaAuditPmassessDRepositoryImpl implements IaAuditPmassessDRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPmassessDRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<IaAuditPmassessDVo> filterIaPaAssessDByAuditPmassessNo(String auditPmassessNo, String formCode) {
		logger.debug("auditPmassessNo: {}", auditPmassessNo);
		logger.debug("formCode: {}", formCode);
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_AUDIT_PMASSESS_D ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		sql.append(" AND AUDIT_PM_ASSESS_NO = ? ");
		params.add(auditPmassessNo.trim());
		
		sql.append(" AND FORM_CODE = ? ");
		params.add(formCode.trim());

		sql.append(" ORDER BY PM_ASSESS_D_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmassessDVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaAuditPmassessDVo.class));

		return response; 
	}
}
