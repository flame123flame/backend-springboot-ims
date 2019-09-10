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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.go.excise.ims.ta.service.AbstractProductPaperService;
import th.go.excise.ims.ta.service.ProductPaperBalanceMaterialService;
import th.go.excise.ims.ta.service.ProductPaperInformPriceService;
import th.go.excise.ims.ta.service.ProductPaperInputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperInputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperOutputForeignGoodsService;
import th.go.excise.ims.ta.service.ProductPaperOutputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperOutputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperReduceTaxService;
import th.go.excise.ims.ta.service.ProductPaperRelationProducedGoodsService;
import th.go.excise.ims.ta.service.ProductPaperTaxAmtAdditionalService;
import th.go.excise.ims.ta.service.ProductPaperUnitPriceReduceTaxService;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperVo;

@Controller
@RequestMapping("/api/ta/product-paper")
public class ProductPaperController {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperController.class);

	private Map<String, AbstractProductPaperService> productPaperServiceMap = new HashMap<>();
	
	@Autowired
	public ProductPaperController(
			ProductPaperInputMaterialService productPaperInputMaterialService,
			ProductPaperOutputMaterialService productPaperOutputMaterialService,
			ProductPaperBalanceMaterialService productPaperBalanceMaterialService,
			ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService,
			ProductPaperInputGoodsService productPaperInputGoodsService,
			ProductPaperOutputGoodsService productPaperOutputGoodsService,
			ProductPaperReduceTaxService productPaperReduceTaxService,
			ProductPaperUnitPriceReduceTaxService productPaperUnitPriceReduceTaxService,
			ProductPaperInformPriceService productPaperInformPriceService,
			ProductPaperOutputForeignGoodsService productPaperOutputForeignGoodsService,
			ProductPaperTaxAmtAdditionalService productPaperTaxAmtAdditionalService) {
		productPaperServiceMap.put("input-material", productPaperInputMaterialService);
		productPaperServiceMap.put("output-material", productPaperOutputMaterialService);
		productPaperServiceMap.put("balance-material", productPaperBalanceMaterialService);
		productPaperServiceMap.put("relation-produced-goods", productPaperRelationProducedGoodsService);
		productPaperServiceMap.put("input-goods", productPaperInputGoodsService);
		productPaperServiceMap.put("output-goods", productPaperOutputGoodsService);
		productPaperServiceMap.put("reduce-tax", productPaperReduceTaxService);
		productPaperServiceMap.put("unit-price-reduce-tax", productPaperUnitPriceReduceTaxService);
		productPaperServiceMap.put("inform-price", productPaperInformPriceService);
		productPaperServiceMap.put("output-foreign-goods", productPaperOutputForeignGoodsService);
		productPaperServiceMap.put("tax-amt-additional", productPaperTaxAmtAdditionalService);
	}
	
	@PostMapping("/inquiry-{productPaperType}")
	@ResponseBody
	public ResponseData<?> inquiryData(@PathVariable("productPaperType") String productPaperType, @RequestBody ProductPaperFormVo formVo) {
		logger.info("inquiryData productPaperType={}", productPaperType);
		
		ResponseData<ProductPaperVo> responseData = new ResponseData<>();
		try {
			AbstractProductPaperService<Object, BaseEntity> service = productPaperServiceMap.get(productPaperType);
			responseData.setData(service.inquiry(formVo));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return responseData;
	}
	
	@PostMapping("/export-{productPaperType}")
	@ResponseBody
	public void exportData(@PathVariable("productPaperType") String productPaperType, @ModelAttribute ProductPaperFormVo formVo, HttpServletResponse response) throws IOException {
		logger.info("exportData productPaperType={}, paperPrNumber={}", productPaperType, formVo.getPaperPrNumber());

		//String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		AbstractProductPaperService<Object, BaseEntity> service = productPaperServiceMap.get(productPaperType);
		byte[] bytes = service.export(formVo);
		String fileName = service.getExportFileName(formVo);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(bytes);
		outStream.flush();
		outStream.close();
	}
	
	@PostMapping("/upload-{productPaperType}")
	@ResponseBody
	public ResponseData<?> uploadData(@PathVariable("productPaperType") String productPaperType, @ModelAttribute ProductPaperFormVo formVo) throws IOException {
		logger.info("uploadData");
		
		ResponseData<ProductPaperVo> responseData = new ResponseData<>();
		try {
			AbstractProductPaperService<Object, BaseEntity> service = productPaperServiceMap.get(productPaperType);
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
	
	@PostMapping("/save-{productPaperType}")
	@ResponseBody
	public ResponseData<String> saveData(@PathVariable("productPaperType") String productPaperType, @RequestBody ProductPaperFormVo formVo) {
		logger.info("saveData");
		
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			AbstractProductPaperService<Object, BaseEntity> service = productPaperServiceMap.get(productPaperType);
			String paperPrNumber = service.save(formVo);
			responseData.setData(paperPrNumber);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/paper-pr-number-list/{productPaperType}")
	@ResponseBody
	public ResponseData<List<String>> getPaperPrNumberList(@PathVariable("productPaperType") String productPaperType, @RequestBody ProductPaperFormVo formVo) {
		logger.info("getPaperPrNumberList");
		
		ResponseData<List<String>> responseData = new ResponseData<List<String>>();
		try {
			AbstractProductPaperService<Object, BaseEntity> service = productPaperServiceMap.get(productPaperType);
			responseData.setData(service.getPaperPrNumberList(formVo));
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
