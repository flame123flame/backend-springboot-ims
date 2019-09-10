package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.preferences.service.ExcisePersonInfoService;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

@RestController
@RequestMapping("/api/person-info")
public class ExcisePersonInfoController {

	private static final Logger logger = LoggerFactory.getLogger(ExcisePersonController.class);
	
	@Autowired
	private ExcisePersonInfoService excisePersonInfoService;
	
	@GetMapping("/person-info-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Person Info List"
	)
	public ResponseData<List<ExcisePersonInfoVo>> findByWorkOffcode() {
		ResponseData<List<ExcisePersonInfoVo>> response = new ResponseData<>();
		List<ExcisePersonInfoVo> personInfo = excisePersonInfoService.findByWorkOffcode();
		if (personInfo.size() > 0) {
			response.setData(personInfo);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Person Info List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
}
