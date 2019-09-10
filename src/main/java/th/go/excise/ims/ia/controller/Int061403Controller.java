package th.go.excise.ims.ia.controller;

import org.apache.poi.ss.formula.functions.T;
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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.service.Int061403Service;
import th.go.excise.ims.ia.vo.AuditTxinsurD1Vo;
import th.go.excise.ims.ia.vo.Int061403FormVo;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

@Controller
@RequestMapping("/api/ia/int06/14/03")
public class Int061403Controller {
	private Logger logger = LoggerFactory.getLogger(Int061402Controller.class);
	
	@Autowired 
	private Int061403Service int061403Service;
	
	@GetMapping("/get-reg-4000/{wsReg4000Id}")
	@ResponseBody
	public ResponseData<TaWsReg4000> getReg4000(@PathVariable("wsReg4000Id") Long wsReg4000Id){
		logger.info("wsReg4000Id: {}", wsReg4000Id);
		ResponseData<TaWsReg4000> response = new ResponseData<TaWsReg4000>();
		try {
			response.setData(int061403Service.findReg4000ById(wsReg4000Id));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/get-header/{iaAuditTxinsurHId}")
	@ResponseBody
	public ResponseData<IaAuditTxinsurH> getDataHeader(@PathVariable("iaAuditTxinsurHId") Long iaAuditTxinsurHId){
		logger.info("iaAuditTxinsurHId: {}", iaAuditTxinsurHId);
		ResponseData<IaAuditTxinsurH> response = new ResponseData<IaAuditTxinsurH>();
		try {
			response.setData(int061403Service.getHeader(iaAuditTxinsurHId));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/get-detail/{newRegId}/{iaAuditTxinsurHId}")
	@ResponseBody
	public ResponseData<AuditTxinsurD1Vo> getDataDetail(@PathVariable("newRegId") String newRegId, @PathVariable("iaAuditTxinsurHId") Long iaAuditTxinsurHId){
		logger.info("newRegId: {}", newRegId);
		logger.info("iaAuditTxinsurHId: {}", iaAuditTxinsurHId);
		ResponseData<AuditTxinsurD1Vo> response = new ResponseData<AuditTxinsurD1Vo>();
		try {
			response.setData(int061403Service.getDetail(newRegId, iaAuditTxinsurHId));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseData<T> save(@RequestBody Int061403FormVo request){
		
		ResponseData<T> response = new ResponseData<T>();
		try {
			int061403Service.update(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
}
