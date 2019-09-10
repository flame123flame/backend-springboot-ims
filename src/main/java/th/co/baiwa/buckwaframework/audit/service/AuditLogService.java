package th.co.baiwa.buckwaframework.audit.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.audit.persistence.entity.AuditLog;
import th.co.baiwa.buckwaframework.audit.persistence.repository.AuditLogRepository;
import th.co.baiwa.buckwaframework.audit.vo.AuditLogFormVo;
import th.co.baiwa.buckwaframework.common.util.WebContextUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Service
public class AuditLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogService.class);
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	public void saveAuditLog(AuditLogFormVo formVo) {
		logger.info("saveAuditLog");
		
		AuditLog auditLog = new AuditLog();
		auditLog.setActionDate(LocalDateTime.now());
		auditLog.setUserId(UserLoginUtils.getCurrentUsername());
		auditLog.setIpAddress(WebContextUtils.getIpAddress());
		auditLog.setActionName(formVo.getActionName());
		auditLog.setActionDesc(formVo.getActionDesc());
		auditLogRepository.save(auditLog);
	}
	
	public void saveAuditLogNoIp(AuditLogFormVo formVo) {
		logger.info("saveAuditLog");
		
		AuditLog auditLog = new AuditLog();
		auditLog.setActionDate(LocalDateTime.now());
		auditLog.setUserId(UserLoginUtils.getCurrentUsername());
		auditLog.setActionName(formVo.getActionName());
		auditLog.setActionDesc(formVo.getActionDesc());
		auditLogRepository.save(auditLog);
	}
	
}
