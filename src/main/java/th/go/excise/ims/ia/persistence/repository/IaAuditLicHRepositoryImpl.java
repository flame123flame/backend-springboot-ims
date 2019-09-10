package th.go.excise.ims.ia.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class IaAuditLicHRepositoryImpl implements IaAuditLicHRepositoryCustom{

	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public String generateAuditLicNo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AUDIT_LIC_NO_SEQ.NEXTVAL FROM DUAL");
		String dataRes = commonJdbcTemplate.queryForObject(sql.toString(), String.class);
		return StringUtils.leftPad(dataRes, 8, "0");
	}
	
}
