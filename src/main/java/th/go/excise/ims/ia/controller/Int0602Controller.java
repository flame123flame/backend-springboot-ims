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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int0602Service;
import th.go.excise.ims.ia.vo.AuditLicD1Vo;
import th.go.excise.ims.ia.vo.AuditLicD2Vo;
import th.go.excise.ims.ia.vo.AuditLicHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0602ResultTab2Vo;
import th.go.excise.ims.ia.vo.Int0602SaveVo;

@Controller
@RequestMapping("/api/ia/int06/02")
public class Int0602Controller {

	private static final Logger logger = LoggerFactory.getLogger(Int0602Controller.class);

	@Autowired
	private Int0602Service int0602Service;

	@PostMapping("/find-all-head")
	@ResponseBody
	public ResponseData<List<AuditLicHVo>> findAuditLicHVoAll(@RequestBody Int0602FormVo request) {
		ResponseData<List<AuditLicHVo>> response = new ResponseData<List<AuditLicHVo>>();
		try {
			response.setData(int0602Service.findAuditLicHVoList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save-lic-data")
	@ResponseBody
	public ResponseData<AuditLicHVo> saveLic(@RequestBody Int0602SaveVo request) {
		ResponseData<AuditLicHVo> response = new ResponseData<>();
		try {
			response.setData(int0602Service.saveLicListService(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab1")
	@ResponseBody
	public ResponseData<List<Int0602ResultTab1Vo>> findByCriteria(@RequestBody Int0602FormVo request) {
		ResponseData<List<Int0602ResultTab1Vo>> response = new ResponseData<List<Int0602ResultTab1Vo>>();
		try {
			response.setData(int0602Service.findByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab2")
	@ResponseBody
	public ResponseData<List<Int0602ResultTab2Vo>> findTab2ByCriteria(@RequestBody Int0602FormVo request) {
		ResponseData<List<Int0602ResultTab2Vo>> response = new ResponseData<List<Int0602ResultTab2Vo>>();
		try {
			response.setData(int0602Service.findTab2Criteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-header-by-audit-no")
	@ResponseBody
	public ResponseData<AuditLicHVo> findAuditHByAuditLicNo(@RequestBody String auditLicNo) {
		ResponseData<AuditLicHVo> response = new ResponseData<AuditLicHVo>();
		try {
			response.setData(int0602Service.findIaAuditLicHByAuditLicNo(auditLicNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab1-bylicno")
	@ResponseBody
	public ResponseData<List<AuditLicD1Vo>> findTab1ByauditLicNo(@RequestBody String auditLicNo) {
		ResponseData<List<AuditLicD1Vo>> response = new ResponseData<List<AuditLicD1Vo>>();
		try {
			response.setData(int0602Service.findAuditLicD1ByAuditLicNo(auditLicNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-tab2-bylicno")
	@ResponseBody
	public ResponseData<List<AuditLicD2Vo>> findTab2ByauditLicNo(@RequestBody String auditLicNo) {
		ResponseData<List<AuditLicD2Vo>> response = new ResponseData<List<AuditLicD2Vo>>();
		try {
			response.setData(int0602Service.findAuditLicD2ByAuditLicNo(auditLicNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/export/{auditLicNo}")
	public void export(@PathVariable("auditLicNo") String auditLicNo, HttpServletResponse response) throws Exception {
		String fileName = URLEncoder.encode("ตรวจสอบการใช้ใบอนุญาต ", "UTF-8");
		String replaceString = auditLicNo.replace('_', '/');

		// write it as an excel attachment
		byte[] outByteStream = int0602Service.export(replaceString);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
	}
}
