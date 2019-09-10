package th.co.baiwa.buckwaframework.security.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;
import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.domain.UserProfileVo;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;

@Controller
public class AuthenController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenController.class);
	
	@RequestMapping(value = URL.LOGIN_WEB, method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request) {
		
		logger.info("login");
		
		if (StringUtils.isNotBlank(error)) {
			logger.warn("login error: " + error);
		}
		
		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", getErrorMessage(request, WebAttributes.AUTHENTICATION_EXCEPTION));
		}
		
		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		
		mav.setViewName("security/login");
		
		return mav;
	}
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {
		
		Exception exception = (Exception) request.getSession().getAttribute(key);
		
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username or password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		
		return error;
	}
	
	@PostMapping("/api/security/on-login-success")
	@ApiOperation(
		tags = MODULE_NAME.AUTHENTICATION,
		value = "Checking Login?"
	)
	@ResponseBody
	public ResponseData<String> onLoginSuccess() {
		ResponseData<String> respData = new ResponseData<>();
		respData.setStatus(RESPONSE_STATUS.SUCCESS);
		respData.setMessage("Session Alive");
		return respData;
	}
	
	@PostMapping("/api/security/user-profile")
	@ApiOperation(
			tags = MODULE_NAME.AUTHENTICATION,
			value = "Get User Profile"
			)
	@ResponseBody
	public ResponseData<UserProfileVo> getUserProfile() {
		ResponseData<UserProfileVo> respData = new ResponseData<>();
		
		try {
			UserBean userBean = UserLoginUtils.getCurrentUserBean();
			UserProfileVo userProfileVo = new UserProfileVo();
			BeanUtils.copyProperties(userProfileVo, userBean);
			userProfileVo.setAuthorityList(UserLoginUtils.getGrantedAuthorityList());
			userProfileVo.setDepartmentName(ApplicationCache.getExciseDepartment(userProfileVo.getOfficeCode()).getDeptShortName());
			userProfileVo.setIsCentral(ExciseUtils.isCentral(UserLoginUtils.getCurrentUserBean().getOfficeCode()));
			userProfileVo.setSystemName(getCurrentUserSystemByRole(UserLoginUtils.getGrantedAuthorityList()));
			respData.setData(userProfileVo);
			respData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (IllegalAccessException | InvocationTargetException e) {
			respData.setStatus(RESPONSE_STATUS.FAILED);
			respData.setMessage(e.getMessage());
		}
		
		return respData;
	}
	
	public String getCurrentUserSystemByRole(List<String> roleList) {
		String systemName = ROLE.PREFIX_TA;
		for (String role : roleList) {
			if (role.startsWith(ROLE.PREFIX_TA)) {
				systemName = ROLE.PREFIX_TA;
				break;
			} else if (role.startsWith(ROLE.PREFIX_IA)) {
				systemName = ROLE.PREFIX_IA;
				break;
			} else if (role.startsWith(ROLE.PREFIX_OA)) {
				systemName = ROLE.PREFIX_OA;
				break;
			}
		}
		return systemName;
	}
	
}
