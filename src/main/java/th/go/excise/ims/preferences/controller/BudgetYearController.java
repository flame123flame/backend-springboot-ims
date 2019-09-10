package th.go.excise.ims.preferences.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;

@RestController
@RequestMapping("/api/preferences/budget-year")
public class BudgetYearController {

	@PostMapping
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Budget Year"
	)
	public ResponseData<String> getBudgetYear() {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			responseData.setData(ExciseUtils.getCurrentBudgetYear());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
