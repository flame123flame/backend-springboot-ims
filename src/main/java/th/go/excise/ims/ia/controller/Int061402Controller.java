package th.go.excise.ims.ia.controller;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int061402Service;
import th.go.excise.ims.ia.vo.AuditTxinsurHVo;
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Int061402FormVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Controller
@RequestMapping("/api/ia/int06/14/02")
public class Int061402Controller {
	private Logger logger = LoggerFactory.getLogger(Int061402Controller.class);
	
	@Autowired
	private Int061402Service int061402Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Ws_Reg4000Vo> filter(@RequestBody Int061402FilterVo formVo){
		logger.info("officeCode: {}", formVo.getOfficeCode());
		
		DataTableAjax<Ws_Reg4000Vo> response =  new DataTableAjax<Ws_Reg4000Vo>();
		try {
			response = int061402Service.filter(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody Int061402FormVo request){
		logger.info("save IA_AUDIT_TXINSUR_H and IA_AUDIT_TXINSUR_D1 !!");
		
		ResponseData<T> response = new ResponseData<T>();
		try {
			int061402Service.save(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/get-data-header")
	@ResponseBody
	public ResponseData<AuditTxinsurHVo> getDataHeader(@RequestBody String auditTxinsurNo){
		
		ResponseData<AuditTxinsurHVo> response = new ResponseData<AuditTxinsurHVo>();
		try {
			response.setData(int061402Service.findHeader(auditTxinsurNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}
