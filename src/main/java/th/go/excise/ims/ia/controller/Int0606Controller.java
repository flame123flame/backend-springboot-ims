package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowDetail;
import th.go.excise.ims.ia.service.Int0606Service;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030101Vo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int0606FormDetail;
import th.go.excise.ims.ia.vo.Int0606FormHdrId;
import th.go.excise.ims.ia.vo.Int0606FormVo;
import th.go.excise.ims.ia.vo.Int0606Vo;

@Controller
@RequestMapping("/api/ia/int06/06")
public class Int0606Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0606Controller.class);

	@Autowired
	Int0606Service int0606Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaCheckLicense> list(@RequestBody Int0606FormVo form) {
		DataTableAjax<IaCheckLicense> response = new DataTableAjax<IaCheckLicense>();
		List<IaCheckLicense> iaCheckLicenseList = new ArrayList<IaCheckLicense>();
		try {	
			iaCheckLicenseList = int0606Service.list(form);
			response.setData(iaCheckLicenseList);
		} catch (Exception e) {
			logger.error("Int0606Controller : " , e);
		}
		return response;
	}
	
	
	@PostMapping("/FindIdHdr")
	@ResponseBody
	public DataTableAjax<Int0606Vo> FindIdHdr(@RequestBody Int0606FormHdrId form) {
		DataTableAjax<Int0606Vo> response = new DataTableAjax<Int0606Vo>();
		try {	
			response.setData(int0606Service.FindIdHdr(form));
		} catch (Exception e) {
			logger.error("Int0606Controller : " , e);
		}
		return response;
	}
	
	
	@PostMapping("/saveConcludeFollow")
	@ResponseBody
	public ResponseData<String> saveConcludeFollow(@RequestBody Int0606FormDetail form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int0606Service.saveConcludeFollow(form);
			response.setData("บันทึกสำเร็จ	  ");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0606Controller saveConcludeFollow : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	
	

}
