package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.ExciseOrgGfmisService;
import th.go.excise.ims.ia.vo.ExciseOrgGfDisburseUnitVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;

@RestController
@RequestMapping("/api/preferences/org-gfmis")
public class ExciseOrgGfmisController {

	private Logger logger = LoggerFactory.getLogger(ExciseOrgGfmisController.class);

	@Autowired
	private ExciseOrgGfmisService exciseOrgGfmisService;

	@PostMapping("/find/disburseunit-and-name")
	@ResponseBody
	public ResponseData<List<ExciseOrgGfDisburseUnitVo>> findGfDisburseUnitAndName() {
		ResponseData<List<ExciseOrgGfDisburseUnitVo>> responseData = new ResponseData<List<ExciseOrgGfDisburseUnitVo>>();
		try {
			responseData.setData(exciseOrgGfmisService.findGfDisburseUnitAndName());
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			logger.error("disburseUnitAndName find : ", RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("disburseUnitAndName find : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/find/excise-org-disb-by-gfdisburseunit")
	@ResponseBody
	public ResponseData<ExciseOrgDisb> findExciseOrgDisbByGfDisburseUnit(String gfDisburseUnit) {
		ResponseData<ExciseOrgDisb> responseData = new ResponseData<ExciseOrgDisb>();
		try {
			responseData.setData(exciseOrgGfmisService.findExciseOrgGfmisByGfDisburseUnit(gfDisburseUnit));
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			logger.error("disburseUnitAndName find : ", RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("disburseUnitAndName find : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
