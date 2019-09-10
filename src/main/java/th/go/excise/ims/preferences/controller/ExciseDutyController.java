package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.vo.ExciseDutyGroup;

@RestController
@RequestMapping("/api/preferences/duty-group")
public class ExciseDutyController {

	private static final Logger logger = LoggerFactory.getLogger(ExciseDutyController.class);

	@PostMapping("/list/{type}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Duty Group"
	)
	public ResponseData<List<ExciseDutyGroup>> getDutyGroupListByGroupType(@PathVariable("type") String type) {
		logger.info("getDutyGroupListByGroupType type={}", type);

		ResponseData<List<ExciseDutyGroup>> response = new ResponseData<>();
		List<ExciseDutyGroup> exciseSectorList = ApplicationCache.getExciseDutyGroupListByType(type);
		if (!CollectionUtils.isEmpty(exciseSectorList)) {
			response.setData(exciseSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Duty Group List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
}
