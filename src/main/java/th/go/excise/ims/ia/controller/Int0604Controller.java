package th.go.excise.ims.ia.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import th.go.excise.ims.ia.service.Int0604Service;
import th.go.excise.ims.ia.vo.AuditLicexpDVo;
import th.go.excise.ims.ia.vo.AuditLicexpHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0604SaveVo;

@Controller
@RequestMapping("/api/ia/int06/04")
public class Int0604Controller {

	@Autowired
	private Int0604Service int0604Service;

	@PostMapping("/find-by-criteria")
	@ResponseBody
	public ResponseData<List<AuditLicexpDVo>> findByCriteria(@RequestBody Int0602FormVo request) {
		ResponseData<List<AuditLicexpDVo>> response = new ResponseData<List<AuditLicexpDVo>>();
		try {
			response.setData(int0604Service.findByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-all-audit-lic-exp-no")
	@ResponseBody
	public ResponseData<List<AuditLicexpHVo>> findAllDataHeader() {
		ResponseData<List<AuditLicexpHVo>> response = new ResponseData<List<AuditLicexpHVo>>();
		try {
			response.setData(int0604Service.findAuditLicdupHList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<AuditLicexpHVo> saveLicdupH(@RequestBody Int0604SaveVo request) {
		ResponseData<AuditLicexpHVo> response = new ResponseData<>();
		try {
			response.setData(int0604Service.save(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-data-by-audit-lic-exp-no")
	@ResponseBody
	public ResponseData<List<AuditLicexpDVo>> findAuditLicdupDByAuditLicdupNo(@RequestBody String auditLicexpNo) {
		ResponseData<List<AuditLicexpDVo>> response = new ResponseData<List<AuditLicexpDVo>>();
		try {
			response.setData(int0604Service.findAuditLicexpDByAuditLicdupNo(auditLicexpNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-header-by-audit-lic-exp-no")
	@ResponseBody
	public ResponseData<AuditLicexpHVo> findAuditLicdupHByAuditLicdupNo(@RequestBody String auditLicexpNo) {
		ResponseData<AuditLicexpHVo> response = new ResponseData<AuditLicexpHVo>();
		try {
			response.setData(int0604Service.findByAuditLicexpNo(auditLicexpNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/export/{auditLicexpNo}")
	public void export(@PathVariable("auditLicexpNo") String auditLicexpNo, HttpServletResponse response) throws Exception {
		String fileName = URLEncoder.encode("ตรวจสอบการต่อใบอนุญาต", "UTF-8");
		String replaceString = auditLicexpNo.replace('_', '/');

		// write it as an excel attachment
		byte[] outByteStream = int0604Service.export(replaceString);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
	}

}
