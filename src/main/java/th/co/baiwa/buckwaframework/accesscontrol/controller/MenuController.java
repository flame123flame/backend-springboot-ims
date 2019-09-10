package th.co.baiwa.buckwaframework.accesscontrol.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.accesscontrol.service.MenuService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;

@Controller
@RequestMapping("/api/access-control/menu")
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/list")
	@ApiOperation(
		tags = MODULE_NAME.ACCESS_CONTROL,
		value = "Get Menu List"
	)
	@ResponseBody
	public ResponseData<List<MenuVo>> getMenu() {
		logger.info("getMenu");

		ResponseData<List<MenuVo>> responseData = new ResponseData<>();
		try {
			responseData.setData(menuService.findByRoles());
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
