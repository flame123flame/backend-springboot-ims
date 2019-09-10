package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;
import th.go.excise.ims.ia.persistence.entity.IaStampType;
import th.go.excise.ims.ia.service.Int0501Service;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncHVo;
import th.go.excise.ims.ia.vo.IaEstimateD1VoType;
import th.go.excise.ims.ia.vo.IaEstimateExpD1Vo;
import th.go.excise.ims.ia.vo.IaEstimateExpHVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501SaveVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;

@Controller
@RequestMapping("/api/ia/int05/01")
public class Int0501Controller {

	private static final Logger logger = LoggerFactory.getLogger(Int0501Controller.class);

	@Autowired
	private Int0501Service int0501Service;

	@PostMapping("/listPerson")
	@ResponseBody
	public DataTableAjax<Int0501Vo> listPerson(@RequestBody Int0501FormVo form) {
		DataTableAjax<Int0501Vo> response = new DataTableAjax<Int0501Vo>();
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		try {
			personList = int0501Service.listPerson(form);
			response.setData(personList);
		} catch (Exception e) {
			logger.error("Int0501Controller : ", e);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaEstimateExpHVo> save(@RequestBody Int0501SaveVo request) {
		ResponseData<IaEstimateExpHVo> response = new ResponseData<IaEstimateExpHVo>();
		try {
			response.setData(int0501Service.saveIaEstimateExp(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/get-dropdown-estimateno")
	@ResponseBody
	public ResponseData<List<IaEstimateExpH>> getDropdownEstimateNo() {
		ResponseData<List<IaEstimateExpH>> response = new ResponseData<List<IaEstimateExpH>>();
		try {
			response.setData(int0501Service.getDropdownEstimateNo());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/find-tab1-by-estimateno")
	@ResponseBody
	public ResponseData<List<IaEstimateD1VoType>> findIaEstimateD1ByestExpNo(@RequestBody String estExpNo) {
		ResponseData<List<IaEstimateD1VoType>> response = new ResponseData<List<IaEstimateD1VoType>>();
		try {
			response.setData(int0501Service.findIaEstimateD1ByestExpNo(estExpNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/find-header-by-estimateno")
	@ResponseBody
	public ResponseData<IaEstimateExpHVo> findIaEstimateHByestExpNo(@RequestBody String estExpNo) {
		ResponseData<IaEstimateExpHVo> response = new ResponseData<IaEstimateExpHVo>();
		try {
			response.setData(int0501Service.findIaEstimateHByestExpNo(estExpNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/estexpno/export/{estExpNo}")
	public void exportByestExpNo(@PathVariable("estExpNo") String estExpNo, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("เอกสารประมาณการค่าใช้จ่ายในการเดินทางไปราชการ", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int0501Service.exportInt0501(estExpNo);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

}
