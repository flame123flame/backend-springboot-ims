package th.co.baiwa.buckwaframework.security.rest.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import th.co.baiwa.buckwaframework.audit.service.AuditLogService;
import th.co.baiwa.buckwaframework.audit.vo.AuditLogFormVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.domain.UserBean;

public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Autowired
	private AuditLogService auditLogService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		clearAuthenticationAttributes(request);
		
		UserBean userBean = (UserBean) authentication.getPrincipal();
		if (userBean != null && (PROFILE.UAT.equals(profileActive) || PROFILE.PROD.equals(profileActive))) {
			AuditLogFormVo auditLogFormVo = new AuditLogFormVo();
			auditLogFormVo.setActionName("LOGIN");
			auditLogFormVo.setActionDesc(String.format("userId=%s Login on %s", userBean.getUsername(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
			auditLogService.saveAuditLog(auditLogFormVo);
		}
	}
	
}
