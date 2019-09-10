package th.co.baiwa.buckwaframework.common.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebContextUtils {
	
	public static Object getBean(String beanName) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> classType) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(classType);
	}
	
	public static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		return request;
	}
	
	public static HttpSession getHttpSession() {
		HttpSession session = getHttpServletRequest().getSession();
		return session;
	}
	
	public static ServletContext getServletContext() {
		ServletContext context = getHttpSession().getServletContext();
		return context;
	}
	
	public static String getIpAddress() {
		String remoteAddress = null;
		HttpServletRequest request = getHttpServletRequest();
		
		if (request != null) {
			remoteAddress = request.getHeader("X-FORWADED-FOR");
			if (StringUtils.isEmpty(remoteAddress)) {
				remoteAddress = request.getRemoteAddr();
			}
		}
		
		return remoteAddress;
	}
	
}
