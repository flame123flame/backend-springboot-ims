package th.co.baiwa.buckwaframework.preferences.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@RestController
@RequestMapping("/api/preferences/reload-cache")
public class PreferencesController {
	
	private static final Logger logger = LoggerFactory.getLogger(PreferencesController.class);
	
	private final ApplicationCache applicationCache;
	
	@Autowired
	public PreferencesController(ApplicationCache applicationCache) {
		this.applicationCache = applicationCache;
	}
	
	@GetMapping
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Reload Cache",
		notes = "Reload Parameter and Message into Cache"
	)
	@ApiResponses(value = {
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Reload Cache Successful")
	})
	public ResponseData<?> reloadCache() {
		logger.info("reloadCache");
		
		applicationCache.reloadCache();
		
		ResponseData<String> respData = new ResponseData<>();
		respData.setStatus(RESPONSE_STATUS.SUCCESS);
		respData.setMessage("Reload Cache Successful");
		
		return respData;
	}
	
}
