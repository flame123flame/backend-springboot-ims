package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;
import th.go.excise.ims.ia.vo.IaAuditPmassessHVo;

public class IaAuditPmassessHRepositoryImpl implements IaAuditPmassessHRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPmassessHRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public List<IaAuditPmassessH> getAuditPmassessNoList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PMASSESS_NO ");
		sql.append(" FROM IA_AUDIT_PMASSESS_H ");
		sql.append(" GROUP BY AUDIT_PMASSESS_NO  ");
		sql.append(" ORDER BY MIN(AUDIT_PMASSESS_H_ID) ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmassessH> dropdownList = this.commonJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(IaAuditPmassessH.class));

		return dropdownList;
	}
	
	@Override
	public List<IaAuditPmassessHVo> filterIaPmAssessByAuditPmassessNo(String auditPmassessNo) {
		logger.debug("auditPmassessNo: {}", auditPmassessNo);
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_AUDIT_PMASSESS_H ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		sql.append(" AND AUDIT_PMASSESS_NO = ? ");
		params.add(auditPmassessNo);
		
		sql.append(" ORDER BY PM_ASSESS_H_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmassessHVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaAuditPmassessHVo.class));

		return response; 
	}
}
