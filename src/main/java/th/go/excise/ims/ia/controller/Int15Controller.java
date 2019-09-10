package th.go.excise.ims.ia.controller;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.IaGfdrawAccountService;
import th.go.excise.ims.ia.service.IaGfledgerAccountService;
import th.go.excise.ims.ia.service.IaGfmovementAccountService;
import th.go.excise.ims.ia.service.IaGftrialBalanceService;
import th.go.excise.ims.ia.vo.Int15ResponseUploadVo;
import th.go.excise.ims.ia.vo.Int15SaveVo;
import th.go.excise.ims.ia.vo.Int15UploadVo;

@Controller
@RequestMapping("/api/ia/int15/01")
public class Int15Controller {
	private Logger logger = LoggerFactory.getLogger(Int15Controller.class);

	@Autowired
	private IaGfdrawAccountService iaGfdrawAccountService;

	@Autowired
	private IaGftrialBalanceService iaGftrialBalanceService;

	@Autowired
	private IaGfledgerAccountService iaGfledgerAccountService;

	@Autowired
	private IaGfmovementAccountService iaGfmovementAccountService;


	@PostMapping("/upload/ia-type-data1")
	@ResponseBody
	public ResponseData<Int15ResponseUploadVo> uploadT1(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfdrawAccountService.addDataByExcel(file);
		} catch (Exception e) {
			logger.error("Int030102Controller upload1 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data2")
	@ResponseBody
	public ResponseData<Int15ResponseUploadVo> uploadT2(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGftrialBalanceService.addDataByExcel(file);
		} catch (Exception e) {
			logger.error("Int030102Controller upload2 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data3")
	@ResponseBody
	public ResponseData<Int15ResponseUploadVo> uploadT3(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfledgerAccountService.addDataByExcel(file);
		} catch (Exception e) {
			logger.error("Int030102Controller upload3 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data4")
	@ResponseBody
	public ResponseData<Int15ResponseUploadVo> uploadT4(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfmovementAccountService.addDataByExcel(file);
		} catch (Exception e) {
			logger.error("Int030102Controller upload4 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data1")
	@ResponseBody
	public ResponseData<String> save1(@RequestBody Int15SaveVo form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfdrawAccountService.saveData(form);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save1 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data2")
	@ResponseBody
	public ResponseData<String> save2(@RequestBody Int15SaveVo form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGftrialBalanceService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save2 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data3")
	@ResponseBody
	public ResponseData<String> save3(@RequestBody Int15SaveVo form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfledgerAccountService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save3 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data4")
	@ResponseBody
	public ResponseData<String> save4(@RequestBody Int15SaveVo form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfmovementAccountService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save4 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
