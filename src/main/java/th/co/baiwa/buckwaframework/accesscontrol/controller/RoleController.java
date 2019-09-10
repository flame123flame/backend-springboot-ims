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
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.service.RoleService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Controller
@RequestMapping("/api/access-control/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping("/list")
	@ApiOperation(
			tags = MODULE_NAME.ACCESS_CONTROL,
			value = "Get Role by Criteria"
		)
	@ResponseBody
	public DataTableAjax<Role> listRole(@RequestBody RoleFormVo request) {
		logger.info("listRole");

		DataTableAjax<Role> response = new DataTableAjax<>();
		try {
			response = roleService.list(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/create")
	@ApiOperation(
			tags = MODULE_NAME.ACCESS_CONTROL,
			value = "Create Role"
		)
	@ResponseBody
	public ResponseData<Role> createRole(@RequestBody Role request) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.createRole(request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::create ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/{id}")
	@ApiOperation(
			tags = MODULE_NAME.ACCESS_CONTROL,
			value = "Update Role"
		)
	@ResponseBody
	public ResponseData<Role> update(@PathVariable("id") String idStr, @RequestBody Role role) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.updateRole(idStr, role));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::update ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(
			tags = MODULE_NAME.ACCESS_CONTROL,
			value = "Delete Role"
		)
	@ResponseBody
	public ResponseData<Role> delete(@PathVariable("id") String idStr) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.deleteRole(idStr));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::delete ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find-by-id/{id}")
	@ApiOperation(
			tags = MODULE_NAME.ACCESS_CONTROL,
			value = "Get Role by Id"
		)
	@ResponseBody
	public ResponseData<Role> findById(@PathVariable("id") String idStr) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.getRoleById(idStr));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::findById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
