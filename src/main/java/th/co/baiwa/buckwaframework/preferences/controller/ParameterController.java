package th.co.baiwa.buckwaframework.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.preferences.service.ParameterInfoService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;

@Controller
@RequestMapping("/api/preferences/parameter")
public class ParameterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterController.class);
	
	private final ParameterInfoService parameterInfoService;
	
	@Autowired
	public ParameterController(ParameterInfoService parameterInfoService) {
		this.parameterInfoService = parameterInfoService;
	}
	
	@RequestMapping(value = "/{groupCode}/{infoCode}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get ParameterInfo by Code"
	)
	@ResponseBody
	public ResponseData<ParamInfo> getParamInfoByCode(
			@PathVariable("groupCode") String paramGroupCode,
			@PathVariable("infoCode") String paramInfoCode) {
		logger.info("getParamInfoByCode paramGroupCode={}, paramInfoCode={}", paramGroupCode, paramInfoCode);
		
		ResponseData<ParamInfo> response = new ResponseData<ParamInfo>();
		ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(paramGroupCode, paramInfoCode);
		if (paramInfo != null) {
			response.setData(paramInfo);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Parameter Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/{groupCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get ParameterInfoList by GroupCode"
	)
	@ResponseBody
	public ResponseData<List<ParamInfo>> getParamInfoListByGroupCode(@PathVariable("groupCode") String paramGroupCode) {
		logger.info("getParamInfoListByGroupCode paramGroupCode={}", paramGroupCode);
		
		ResponseData<List<ParamInfo>> response = new ResponseData<List<ParamInfo>>();
		List<ParamInfo> paramInfoList = ApplicationCache.getParamInfoListByGroupCode(paramGroupCode);
		if (!CollectionUtils.isEmpty(paramInfoList)) {
			response.setData(paramInfoList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Parameter Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
//	@GetMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Get All Parameter Info"
//	)
//	@ResponseBody
//	public ResponseEntity<?> getAll() {
//		logger.info("getAll");
//		
//		ResponseData<Map<String, ParameterInfo>> response = new ResponseData<>();
////		String paramInfoCode;
////		response.setData(ApplicationCache.getParameterInfoByCode(paramInfoCode));
//		
//		return new ResponseEntity<ResponseData<Map<String, ParameterInfo>>>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Get Parameter Info by Id"
//	)
//	public ResponseEntity<?> getParameterInfo(@PathVariable("id") long id) {
//		logger.info("getParameterInfo [id={}]", id);
//		
//		ParameterInfo parameter = parameterInfoService.getParameterInfoById(id);
//		ResponseData<ParameterInfo> response = new ResponseData<ParameterInfo>();
//		response.setData(parameter);
//		return new ResponseEntity<ResponseData<ParameterInfo>>(response, HttpStatus.OK);
//	}
//	
//	@PostMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Create Parameter Info"
//	)
//	public ResponseEntity<?> create(@RequestBody ParameterInfo parameter, UriComponentsBuilder ucBuilder) {
//		logger.info("create [parameter={}]", parameter);
//		
//		ParameterInfo newParameterInfo = parameterInfoService.insertParameterInfo(parameter);
//		
//		ResponseData<ParameterInfo> response = new ResponseData<ParameterInfo>();
//		response.setData(newParameterInfo);
//		
//		return new ResponseEntity<ResponseData<ParameterInfo>>(response, HttpStatus.CREATED);
//	}
//	
//	@PutMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Update Parameter Info"
//	)
//	public ResponseEntity<?> update(@RequestBody ParameterInfo parameter, UriComponentsBuilder ucBuilder) {
//		logger.info("update [parameter={}]", parameter);
//		
//		parameterInfoService.updateParameterInfo(parameter);
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/{id}")
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Delete Parameter Info"
//	)
//	public ResponseEntity<?> delete(@PathVariable("id") long id) {
//		logger.info("delete [id={}]", id);
//		
//		parameterInfoService.deleteParameterInfo(id);
//		return new ResponseEntity<ParameterInfo>(HttpStatus.NO_CONTENT);
//	}
	
}
