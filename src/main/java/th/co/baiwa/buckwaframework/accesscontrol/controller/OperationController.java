package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.service.OperationService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.OperationFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;

@Controller
@RequestMapping("/api/access-control/operation")
public class OperationController {

	private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

	private OperationService operationService;

	@Autowired
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}

	@PostMapping("/list")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Get Operation by Criteria")
	@ResponseBody
	public DataTableAjax<Operation> listOperation(@RequestBody OperationFormVo operationFormVo) {
		logger.info("listOperation");

		DataTableAjax<Operation> response = new DataTableAjax<>();
		try {
			response = operationService.list(operationFormVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/create")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Create Operation")
	@ResponseBody
	public ResponseData<Operation> save(@RequestBody Operation request) {
		ResponseData<Operation> responseData = new ResponseData<Operation>();
		try {
			responseData.setData(operationService.createOperation(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("operationController::create ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/{id}")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Update Operation")
	@ResponseBody
	public ResponseData<Operation> update(@PathVariable("id") String idStr, @RequestBody Operation operation) {
		ResponseData<Operation> responseData = new ResponseData<Operation>();
		try {
			responseData.setData(operationService.updateOperation(idStr, operation));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("OperationController::update ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(tags = MODULE_NAME.ACCESS_CONTROL, value = "Delete Operation")
	@ResponseBody
	public ResponseData<Operation> delete(@PathVariable("id") String idStr) {
		ResponseData<Operation> responseData = new ResponseData<Operation>();
		try {
			responseData.setData(operationService.deleteOperation(idStr));
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("operationController::delete ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
