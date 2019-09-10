package th.co.baiwa.buckwaframework.security.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.audit.service.AuditLogService;
import th.co.baiwa.buckwaframework.audit.vo.AuditLogFormVo;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.domain.UserBean;

@Component
public class SessionDestroyedListener implements ApplicationListener<HttpSessionDestroyedEvent> {
	
	@Autowired
	private AuditLogService auditLogService;
	
	@Override
	public void onApplicationEvent(HttpSessionDestroyedEvent event) {
		UserBean userBean = null;
		// Get only one record
		for (SecurityContext securityContext : event.getSecurityContexts()) {
			if (event.getSession().getAttribute(SecurityConstants.LOGOUT_FLAG) == null) {
				userBean = (UserBean) securityContext.getAuthentication().getPrincipal();
				
				AuditLogFormVo auditLogFormVo = new AuditLogFormVo();
				auditLogFormVo.setActionName("LOGOUT");
				auditLogFormVo.setActionDesc(String.format("userId=%s Session Timeout on %s", userBean.getUsername(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
				auditLogService.saveAuditLogNoIp(auditLogFormVo);
			}
		}
	}

}
