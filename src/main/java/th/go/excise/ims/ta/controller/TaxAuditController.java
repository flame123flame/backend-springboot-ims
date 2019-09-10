package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.service.AuditStepService;
import th.go.excise.ims.ta.service.TaxAuditService;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.AuditCalendarFormVo;
import th.go.excise.ims.ta.vo.AuditStepFormVo;
import th.go.excise.ims.ta.vo.AuditStepVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;

@Controller
@RequestMapping("/api/ta/tax-audit")
public class TaxAuditController {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditController.class);

	@Autowired
	private TaxAuditService taxAuditService;
	@Autowired
	private AuditStepService auditStepService;

	@PostMapping("/get-operator-details")
	@ResponseBody
	public ResponseData<WsRegfri4000FormVo> getOperatorDetails(@RequestBody WsRegfri4000FormVo wsRegfri4000FormVo) {
		logger.info("getOperatorDetails newRegId={}", wsRegfri4000FormVo.getNewRegId());

		ResponseData<WsRegfri4000FormVo> response = new ResponseData<>();
		try {
			WsRegfri4000FormVo formVo = taxAuditService.getOperatorDetails(wsRegfri4000FormVo.getNewRegId());
			response.setData(formVo);
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@PostMapping("/get-audit-type")
	@ResponseBody
	public ResponseData<List<?>> getAuditType(@RequestBody AuditCalendarCheckboxVo form) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getAuditType(form));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@PostMapping("/get-audit-status")
	@ResponseBody
	public ResponseData<List<?>> getAuditStatus(@RequestBody AuditCalendarCheckboxVo form) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getAuditStatus(form));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	// TODO outside-plan
	@PostMapping("/outside-plan")
	@ResponseBody
	public DataTableAjax<OutsidePlanVo> outsidePlan(@RequestBody OutsidePlanFormVo formVo) {
		return taxAuditService.outsidePlan(formVo);
	}

	@PostMapping("/get-plan-ws-dtl")
	@ResponseBody
	public ResponseData<List<PlanWorksheetDtlVo>> getPlanWsDtl(@RequestBody AuditCalendarCriteriaFormVo formVo) {
		ResponseData<List<PlanWorksheetDtlVo>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getPlanWsDtl(formVo));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@PostMapping("/update-plan-ws-dtl")
	@ResponseBody
	public ResponseData<List<PlanWorksheetDtlVo>> savePlanWsDtl(@RequestBody AuditStepFormVo formVo) {
		ResponseData<List<PlanWorksheetDtlVo>> res = new ResponseData<>();
		try {
			taxAuditService.savePlanWsDtl(formVo);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}
	
	@GetMapping("/get-reg-status")
	@ResponseBody
	public ResponseData<List<ParamInfo>> getRegStatus() {
		ResponseData<List<ParamInfo>> responseData = new ResponseData<List<ParamInfo>>();
		try {
			responseData.setData(taxAuditService.getRegStatus());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/audit-step-list")
	@ResponseBody
	public ResponseData<?> getAuditStepList(@RequestBody AuditStepFormVo formVo) {
		ResponseData<List<AuditStepVo>> responseData = new ResponseData<>();
		try {
			responseData.setData(auditStepService.getAuditStepVoList(formVo));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("get-operator-details-by-audit-plan-code")
	@ResponseBody
	public ResponseData<TaxAuditDetailVo> getOperatorDetailsByAuditPlanCode(@RequestBody TaxAuditDetailFormVo formVo) {
		ResponseData<TaxAuditDetailVo> responseData = new ResponseData<>();
		try {
			responseData.setData(taxAuditService.getOperatorDetailsByAuditPlanCode(formVo));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/get-plan-ws-dtl-person")
	@ResponseBody
	public ResponseData<List<PlanWorksheetDtlVo>> getPlanWsDtlByPerson(
			@RequestBody AuditCalendarCriteriaFormVo formVo) {
		ResponseData<List<PlanWorksheetDtlVo>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getPlanWsDtlByPerson(formVo));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@PostMapping("/get-audit-type/{budgetyear}")
	@ResponseBody
	public ResponseData<List<?>> getAuditType(@PathVariable("budgetyear") String budgetyear) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getCountPlanDtlByPlanStatus(budgetyear));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}
	
	@PostMapping("/get-audit-area/{budgetyear}")
	@ResponseBody
	public ResponseData<List<?>> getAuditArea(@PathVariable("budgetyear") String budgetyear) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getCountPlanDtlAreaByOfficeCode(budgetyear));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}
	
	@PostMapping("/get-audit-count-month/{budgetyear}")
	@ResponseBody
	public ResponseData<List<?>> countAuditMonth(@PathVariable("budgetyear") String budgetyear) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.countAuditMonth(budgetyear));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@PostMapping("/get-audit-count-status/{budgetyear}")
	@ResponseBody
	public ResponseData<List<?>> countAuditStatus(@PathVariable("budgetyear") String budgetyear) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.countAuditStatus(budgetyear));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}
	
	@PostMapping("/get-plan-calendar-holiday")
	@ResponseBody
	public ResponseData<List<AuditCalendarFormVo>> getPlanHoliday(@RequestBody AuditCalendarFormVo from) {
		ResponseData<List<AuditCalendarFormVo>> responseData = new ResponseData<List<AuditCalendarFormVo>>();
		try {
			responseData.setData(taxAuditService.getPlanHoliday(from));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	

}
