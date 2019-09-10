package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.TaFileUploadService;
import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

@Controller
@RequestMapping("/api/ta/file-upload")
public class TaFileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFileUploadController.class);
	
	@Autowired
	private TaFileUploadService taFileUploadService;
	
	@PostMapping("/upload")
	@ResponseBody
	public ResponseData<?> upload(@ModelAttribute FileUploadFormVo formVo) {
		logger.info("upload");
		
		ResponseData<String> responseData = new ResponseData<>();
		try {
			String uploadNumber = taFileUploadService.upload(formVo);
			responseData.setData(uploadNumber);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseData<List<FileUploadVo>> list(@RequestBody FileUploadFormVo formVo) {
		logger.info("list");
		
		ResponseData<List<FileUploadVo>> responseData = new ResponseData<>();
		try {
			List<FileUploadVo> voList = taFileUploadService.getUploadFileList(formVo);
			responseData.setData(voList);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@DeleteMapping("/delete/{uploadNo}")
	@ResponseBody
	public ResponseData<?> delete(@PathVariable("uploadNo") String uploadNo) {
		logger.info("delete");
		
		ResponseData<?> responseData = new ResponseData<>();
		try {
			taFileUploadService.deleteUploadFile(uploadNo);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@GetMapping("/download/{uploadNumber}")
	public void download(@PathVariable("uploadNumber") String uploadNumber, HttpServletResponse response) throws IOException {
		logger.info("download");
		
		FileUploadVo vo = taFileUploadService.getUploadFile(uploadNumber);
		byte[] bytes = vo.getBytes();

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", vo.getFileName()));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}

}
