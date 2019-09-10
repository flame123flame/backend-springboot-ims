package th.go.excise.ims.ia.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.vo.IaAuditPmQtHVo;
import th.go.excise.ims.ia.vo.IaAuditPmassessHVo;


public class IaAuditPmqtHRepositoryImpl implements IaAuditPmqtHRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(IaAuditPmassessHRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<IaAuditPmqtH> getAuditPmQtNoList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PMQT_NO ");
		sql.append(" FROM IA_AUDIT_PMQT_H ");
		sql.append(" GROUP BY AUDIT_PMQT_NO  ");
		sql.append(" ORDER BY MIN(AUDIT_PMQT_H_ID) ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmqtH> dropdownList = this.commonJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(IaAuditPmqtH.class));
		return dropdownList;
	}
	
	@Override
	public List<IaAuditPmQtHVo> filterIaPmQtByAuditPmQtNo(String auditPmQtNo) {
		logger.debug("auditPmaQtNo: {}", auditPmQtNo);
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_AUDIT_PMQT_H ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		sql.append(" AND AUDIT_PMQT_NO = ? ");
		params.add(auditPmQtNo);
		
		sql.append(" ORDER BY PM_QT_H_SEQ ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditPmQtHVo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaAuditPmQtHVo.class));

		return response; 
	}


}
