package th.go.excise.ims.ta.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.service.ParameterInfoService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.service.PlanWorksheetService;

@Controller
@RequestMapping("/api/ta/config")
public class TaxAuditConfigureController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaxAuditConfigureController.class);
	
	@Autowired
	private ParameterInfoService parameterInfoService;
	@Autowired
	private PlanWorksheetService planWorksheetService;
	@Autowired
	private ApplicationCache applicationCache;
	
	@GetMapping("/list")
	@ResponseBody
	public ResponseData<Map<String, String>> taConfigList() {
		logger.info("taConfigList");
		
		ResponseData<Map<String, String>> response = new ResponseData<>();
		
		try {
			List<ParamInfo> paramInfoList = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_CONFIG);
			Map<String, String> taConfigMap = paramInfoList.stream().collect(Collectors.toMap(ParamInfo::getParamCode, ParamInfo::getValue1));
			
			response.setData(taConfigMap);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/update/{paramCode}/{value1}")
	@ResponseBody
	public ResponseData<Map<String, String>> taConfigUpdate(@PathVariable("paramCode") String paramCode, @PathVariable("value1") String value1) {
		logger.info("taConfigUpdate paramCode={}, value1={}", paramCode, value1);
		
		ResponseData<Map<String, String>> response = new ResponseData<>();
		
		try {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, paramCode);
			if (paramInfo == null) {
				throw new Exception("paramCode=" + paramCode + " Not Found");
			}
			
			ParameterInfo parameterInfo = parameterInfoService.getById(paramInfo.getParamInfoId());
			parameterInfo.setValue1(value1);
			parameterInfoService.updateParamInfo(parameterInfo);
			
			applicationCache.reloadCache();
			
			response.setMessage("Update Parameter Success");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/clear-plan-data/{budgetYear}")
	@ResponseBody
	public ResponseData<Map<String, String>> clearPlanData(@PathVariable("budgetYear") String budgetYear) {
		logger.info("clearPlanData budgetYear={}", budgetYear);
		
		ResponseData<Map<String, String>> response = new ResponseData<>();
		
		try {
			planWorksheetService.clearDataByBudgetYear(budgetYear);
			response.setMessage("Clear Data in BudgetYear=" + budgetYear + " Success");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
}
