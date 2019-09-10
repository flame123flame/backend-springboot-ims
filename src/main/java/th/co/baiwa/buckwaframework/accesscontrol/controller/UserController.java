package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.service.UserService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Controller
@RequestMapping("/api/access-control/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/listUser")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Get User by Criteria")
	@ResponseBody
	public DataTableAjax<User> listUser(@RequestBody UserFormVo userFormVo) {
		logger.info("listUser",userFormVo);

		DataTableAjax<User> response = new DataTableAjax<>();
		try {
			response = userService.list(userFormVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/create")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Create User")
	@ResponseBody
	public ResponseData<User> save(@RequestBody User request) {
		ResponseData<User> responseData = new ResponseData<User>();
		try {
			responseData.setData(userService.createUser(request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::create ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Delete User")
	@ResponseBody
	public ResponseData<User> delete(@PathVariable("id") String idStr) {
		ResponseData<User> responseData = new ResponseData<User>();
		try {
			responseData.setData(userService.deleteUser(idStr));
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::delete ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/{id}")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Update User")
	@ResponseBody
	public ResponseData<User> update(@PathVariable("id") String idStr, @RequestBody User user) {
		ResponseData<User> responseData = new ResponseData<User>();
		try {
			responseData.setData(userService.updateUser(idStr, user));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("UserController::update ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find-by-id/{id}")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Get User by Id")
	@ResponseBody
	public ResponseData<User> findById(@PathVariable("id") String idStr) {
		ResponseData<User> responseData = new ResponseData<User>();
		try {
			responseData.setData(userService.getUserById(idStr));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("UserController::findById ", e);
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
