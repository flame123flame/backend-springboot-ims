package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.IaAuditPmQtDVo;

public class IaAuditPmqtDRepositoryImpl implements IaAuditPmqtDRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPmqtDRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<IaAuditPmQtDVo> filterIaPmQtDByAuditPmQtNo(String auditPmQtNo, String formCode) {
		logger.debug("auditPmQtNo: {}", auditPmQtNo);
		logger.debug("formCode: {}", formCode);
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_AUDIT_PMQT_D ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		sql.append(" AND AUDIT_PMQT_NO = ? ");
		params.add(auditPmQtNo.trim());
		
		sql.append(" AND FORM_CODE = ? ");
		params.add(formCode.trim());

		sql.append(" ORDER BY PM_QT_D_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmQtDVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaAuditPmQtDVo.class));

		return response; 
	}
}
