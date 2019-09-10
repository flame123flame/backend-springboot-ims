package th.go.excise.ims.ia.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int0601Service;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD1WasteReceiptVo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD3DatatableDtlVo;
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;
import th.go.excise.ims.ia.vo.IaAuditIncHVo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;

@Controller
@RequestMapping("/api/ia/int06/01")
public class Int0601Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0601Controller.class);
	
	@Autowired
	private Int0601Service int0601Service;

	@PostMapping("/find-tab1")
	@ResponseBody
	public ResponseData<List<IaAuditIncD1Vo>> findTab1(@RequestBody Int0601RequestVo request) {
		ResponseData<List<IaAuditIncD1Vo>> response = new ResponseData<List<IaAuditIncD1Vo>>();
		try {
			response.setData(int0601Service.findTab1ByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab2")
	@ResponseBody
	public ResponseData<List<IaAuditIncD2Vo>> findTab2(@RequestBody Int0601RequestVo request) {
		ResponseData<List<IaAuditIncD2Vo>> response = new ResponseData<List<IaAuditIncD2Vo>>();
		try {
			response.setData(int0601Service.findIaAuditIncD2ByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab3")
	@ResponseBody
	public ResponseData<List<IaAuditIncD3Vo>> findTab3(@RequestBody Int0601RequestVo request) {
		ResponseData<List<IaAuditIncD3Vo>> response = new ResponseData<List<IaAuditIncD3Vo>>();
		try {
			response.setData(int0601Service.findIaAuditIncD3ByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-all-audit-no")
	@ResponseBody
	public ResponseData<List<IaAuditIncHVo>> findAllDataHeader() {
		ResponseData<List<IaAuditIncHVo>> response = new ResponseData<List<IaAuditIncHVo>>();
		try {
			response.setData(int0601Service.findAllIaAuditIncH());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaAuditIncHVo> save(@RequestBody Int0601SaveVo request) {
		ResponseData<IaAuditIncHVo> response = new ResponseData<IaAuditIncHVo>();
		try {
			response.setData(int0601Service.saveIaAuditInc(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-header-by-audit-no")
	@ResponseBody
	public ResponseData<IaAuditIncHVo> findAuditLicDupHByAuditLicDupNo(@RequestBody String auditIncNo) {
		ResponseData<IaAuditIncHVo> response = new ResponseData<IaAuditIncHVo>();
		try {
			response.setData(int0601Service.findIaAuditIncHByAuditIncNo(auditIncNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab1-by-auditnumber")
	@ResponseBody
	public ResponseData<List<IaAuditIncD1Vo>> findIaAuditIncD1ByAuditIncNo(@RequestBody String auditIncNo) {
		ResponseData<List<IaAuditIncD1Vo>> response = new ResponseData<List<IaAuditIncD1Vo>>();
		try {
			response.setData(int0601Service.findIaAuditIncD1ByAuditIncNo(auditIncNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab2-by-auditnumber")
	@ResponseBody
	public ResponseData<List<IaAuditIncD2Vo>> findIaAuditIncD2ByAuditIncNo(@RequestBody String auditIncNo) {
		ResponseData<List<IaAuditIncD2Vo>> response = new ResponseData<List<IaAuditIncD2Vo>>();
		try {
			response.setData(int0601Service.findIaAuditIncD2ByAuditIncNo(auditIncNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab3-by-auditnumber")
	@ResponseBody
	public ResponseData<List<IaAuditIncD3Vo>> findIaAuditIncD3ByAuditIncNo(@RequestBody String auditIncNo) {
		ResponseData<List<IaAuditIncD3Vo>> response = new ResponseData<List<IaAuditIncD3Vo>>();
		try {
			response.setData(int0601Service.findIaAuditIncD3ByAuditIncNo(auditIncNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab3-dtl")
	@ResponseBody
	public ResponseData<IaAuditIncD3DatatableDtlVo> findTab3Dtl(@RequestBody Int0601RequestVo request) {
		ResponseData<IaAuditIncD3DatatableDtlVo> response = new ResponseData<IaAuditIncD3DatatableDtlVo>();
		try {
			response.setData(int0601Service.findTab3Dtl(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/export/{auditIncNo}")
	public void export(@PathVariable("auditIncNo") String auditIncNo, HttpServletResponse response) throws Exception {
		String fileName = URLEncoder.encode("ตรวจสอบใบเสร็จรับเงินภาษีสรรพสามิต ", "UTF-8");
		String replaceString = auditIncNo.replace('_', '/');

		// write it as an excel attachment
		byte[] outByteStream = int0601Service.export(replaceString);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
	}

	@PostMapping("/find-waste-receipt")
	@ResponseBody
	public DataTableAjax<IaAuditIncD1WasteReceiptVo> findWasteReceipt(@RequestBody Int0601RequestVo request) {
		DataTableAjax<IaAuditIncD1WasteReceiptVo> dataTableAjax = null;
		
		try {
			dataTableAjax = int0601Service.findWasteReceipt(request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return dataTableAjax;
	}
	
}
