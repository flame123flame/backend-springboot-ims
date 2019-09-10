package th.go.excise.ims.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.persistence.entity.TaPlanMas;
import th.go.excise.ims.ta.service.MasterPlanService;
import th.go.excise.ims.ta.vo.TaPlanMasVo;

@Controller
@RequestMapping("/api/ta/plan-mas")
public class MasterPlanController {

	@Autowired
	private MasterPlanService masterPlanService;
	
	@PostMapping("/insert")
	@ResponseBody
	public ResponseData<List<TaPlanMas>> insertPlan(@RequestBody List<TaPlanMas> form) {
		ResponseData<List<TaPlanMas>> responseData = new ResponseData<List<TaPlanMas>>();
		try {
			masterPlanService.insertPlan(form);
			responseData.setData(null);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/getplan")
	@ResponseBody
	public ResponseData<List<TaPlanMas>> getPlan(@RequestBody TaPlanMas form) {
		ResponseData<List<TaPlanMas>> responseData = new ResponseData<List<TaPlanMas>>();
		try {
			responseData.setData(masterPlanService.getPlan(form));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/getplanmas")
	@ResponseBody
	public ResponseData<List<TaPlanMasVo>> getPlanMas(@RequestBody TaPlanMas form) {
		ResponseData<List<TaPlanMasVo>> responseData = new ResponseData<List<TaPlanMasVo>>();
		try {
			responseData.setData(masterPlanService.getPlanMas(form));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
