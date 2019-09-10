package th.go.excise.ims.preferences.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.service.AdminPreferencesService;
import th.go.excise.ims.preferences.vo.AdminPreferencesFormVo;

@Controller
@RequestMapping("/api/preferences/admin-preferences")
public class AdminPreferencesController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminPreferencesController.class);
	
	@Autowired
	private AdminPreferencesService adminPreferencesService;
	
	@Autowired
	private ApplicationCache applicationCache;
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseData<Map<String, String>> taConfigUpdate(@RequestBody AdminPreferencesFormVo formVo) {
		logger.info("taConfigUpdate ->");
		
		ResponseData<Map<String, String>> response = new ResponseData<>();
		
		try {
			adminPreferencesService.taConfigUpdate(formVo);
			
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

}
