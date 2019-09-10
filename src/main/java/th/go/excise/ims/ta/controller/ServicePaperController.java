package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.go.excise.ims.ta.service.AbstractProductPaperService;
import th.go.excise.ims.ta.service.AbstractServicePaperService;
import th.go.excise.ims.ta.service.ServicePaperBalanceGoodsService;
import th.go.excise.ims.ta.service.ServicePaperMemberService;
import th.go.excise.ims.ta.service.ServicePaperPricePerUnitService;
import th.go.excise.ims.ta.service.ServicePaperQtyService;
import th.go.excise.ims.ta.service.ServicePaperTaxAmtAdditionalService;
import th.go.excise.ims.ta.vo.ProductPaperVo;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperVo;

@Controller
@RequestMapping("/api/ta/service-paper")
public class ServicePaperController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperController.class);
	
	private Map<String, AbstractServicePaperService> servicePaperServiceMap = new HashMap<>();
	
	@Autowired
	public ServicePaperController(
			ServicePaperQtyService servicePaperQtyService,
			ServicePaperPricePerUnitService servicePaperPricePerUnitService,
			ServicePaperMemberService servicePaperMemberService,
			ServicePaperBalanceGoodsService servicePaperBalanceGoodsService,
	        ServicePaperTaxAmtAdditionalService servicePaperTaxAmtAdditionalService) {
		servicePaperServiceMap.put("qty", servicePaperQtyService);
		servicePaperServiceMap.put("price-per-unit", servicePaperPricePerUnitService);
		servicePaperServiceMap.put("member", servicePaperMemberService);
		servicePaperServiceMap.put("balance-goods", servicePaperBalanceGoodsService);
		servicePaperServiceMap.put("tax-amt-additional", servicePaperTaxAmtAdditionalService);
	}
	
	@PostMapping("/inquiry-{servicePaperType}")
	@ResponseBody
	public ResponseData<?> inquiryData(@PathVariable("servicePaperType") String servicePaperType, @RequestBody ServicePaperFormVo formVo) {
		logger.info("inquiryData servicePaperType={}", servicePaperType);
		
		ResponseData<ServicePaperVo> responseData = new ResponseData<>();
		try {
			AbstractServicePaperService<Object, BaseEntity> service = servicePaperServiceMap.get(servicePaperType);
			responseData.setData(service.inquiry(formVo));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return responseData;
	}
	
	@PostMapping("/export-{servicePaperType}")
	@ResponseBody
	public void exportData(@PathVariable("servicePaperType") String servicePaperType, @ModelAttribute ServicePaperFormVo formVo, HttpServletResponse response) throws IOException {
		logger.info("exportData servicePaperType={}, paperSvNumber={}", servicePaperType, formVo.getPaperSvNumber());

		//String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		AbstractServicePaperService<Object, BaseEntity> service = servicePaperServiceMap.get(servicePaperType);
		byte[] bytes = service.export(formVo);
		String fileName = service.getExportFileName(formVo);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(bytes);
		outStream.flush();
		outStream.close();
	}
	
	@PostMapping("/upload-{servicePaperType}")
	@ResponseBody
	public ResponseData<?> uploadData(@PathVariable("servicePaperType") String servicePaperType, @ModelAttribute ServicePaperFormVo formVo) {
		logger.info("uploadData");
		
		ResponseData<ServicePaperVo> responseData = new ResponseData<>();
		try {
			AbstractServicePaperService<Object, BaseEntity> service = servicePaperServiceMap.get(servicePaperType);
			responseData.setData(service.upload(formVo));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/save-{servicePaperType}")
	@ResponseBody
	public ResponseData<String> saveData(@PathVariable("servicePaperType") String servicePaperType, @RequestBody ServicePaperFormVo formVo) {
		logger.info("saveData");
		
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			AbstractServicePaperService<Object, BaseEntity> service = servicePaperServiceMap.get(servicePaperType);
			String paperSvNumber = service.save(formVo);
			responseData.setData(paperSvNumber);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/paper-sv-number-list/{servicePaperType}")
	@ResponseBody
	public ResponseData<List<String>> getPaperSvNumberList(@PathVariable("servicePaperType") String servicePaperType, @RequestBody ServicePaperFormVo formVo) {
		logger.info("getPaperSvNumberList");
		
		ResponseData<List<String>> responseData = new ResponseData<List<String>>();
		try {
			AbstractServicePaperService<Object, BaseEntity> service = servicePaperServiceMap.get(servicePaperType);
			responseData.setData(service.getPaperSvNumberList(formVo));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
}
