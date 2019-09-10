package th.go.excise.ims.preferences.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.service.Ed01Service;
import th.go.excise.ims.preferences.vo.Ed01FormVo;
import th.go.excise.ims.preferences.vo.Ed01Vo;

@Controller
@RequestMapping("/api/ed/ed01")
public class Ed01Controller {
	
	private Logger logger = LoggerFactory.getLogger(Ed01Controller.class);
	
	@Autowired
	private Ed01Service ed01Service;
		
	@PostMapping("/userProfile")
	@ResponseBody
	public ResponseData<Ed01Vo> getUserProfile() {
		ResponseData<Ed01Vo> respData = new ResponseData<>();
		try {
			UserBean userBean = UserLoginUtils.getCurrentUserBean();
			Ed01Vo userProfileVo = new Ed01Vo();
			BeanUtils.copyProperties(userProfileVo, userBean);
			userProfileVo.setDepartmentName(ApplicationCache.getExciseDepartment(userProfileVo.getOfficeCode()).getDeptShortName());
			respData.setData(userProfileVo);
			respData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (IllegalAccessException | InvocationTargetException e) {
			respData.setStatus(RESPONSE_STATUS.FAILED);
			respData.setMessage(e.getMessage());
		}
		return respData;
	}
	
	@PostMapping("/saveUserProfile")
	@ResponseBody
	public ResponseData<String> saveUserProfile(@RequestBody Ed01FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			ed01Service.saveUserProfile(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed01Controller saveRiskFactorsConfig : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/getIdCard/{username}")
	@ResponseBody
	public ResponseData<List<Ed01Vo>> getIdCard(@PathVariable("username") String username) {
		ResponseData<List<Ed01Vo>> responseData = new ResponseData<List<Ed01Vo>>();
		List<Ed01Vo> data = new ArrayList<Ed01Vo>();
		try {
			data = ed01Service.getIdCard(username);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller findConcludeFollowHdrDetailList : ", e);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
		}
		return responseData;
	}
	
	
	
	
	
}
