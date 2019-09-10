package th.go.excise.ims.preferences.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.service.Ed0101Service;
import th.go.excise.ims.preferences.vo.Ed0101DepartmentVo;
import th.go.excise.ims.preferences.vo.Ed0101PositionVo;
import th.go.excise.ims.preferences.vo.Ed0101Vo;

@Controller
@RequestMapping("/api/ed/ed01/01")
public class Ed0101Controller {
	
	private Logger logger = LoggerFactory.getLogger(Ed0101Controller.class);
	
	@Autowired
	private Ed0101Service ed0101Service;

	@GetMapping("/listUser")
	@ResponseBody
	public ResponseData<List<Ed0101Vo>> listUser() {
		ResponseData<List<Ed0101Vo>> responseData = new ResponseData<List<Ed0101Vo>>();
		List<Ed0101Vo> data = new ArrayList<>();
		try {
			data = ed0101Service.listUser();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listPosition")
	@ResponseBody
	public ResponseData<List<Ed0101PositionVo>> listPosition() {
		ResponseData<List<Ed0101PositionVo>> responseData = new ResponseData<List<Ed0101PositionVo>>();
		List<Ed0101PositionVo> data = new ArrayList<>();
		try {
			data = ed0101Service.listPosition();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listDepartment00")
	@ResponseBody
	public ResponseData<List<Ed0101DepartmentVo>> listDepartment00() {
		ResponseData<List<Ed0101DepartmentVo>> responseData = new ResponseData<List<Ed0101DepartmentVo>>();
		List<Ed0101DepartmentVo> data = new ArrayList<>();
		try {
			data = ed0101Service.listDepartment00();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listDepartment01")
	@ResponseBody
	public ResponseData<List<Ed0101DepartmentVo>> listDepartment01() {
		ResponseData<List<Ed0101DepartmentVo>> responseData = new ResponseData<List<Ed0101DepartmentVo>>();
		List<Ed0101DepartmentVo> data = new ArrayList<>();
		try {
			data = ed0101Service.listDepartment01();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	
	
	
}
