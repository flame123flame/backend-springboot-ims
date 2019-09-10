package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty;
import th.go.excise.ims.preferences.persistence.repository.ExciseCtrlDutyRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExciseCtrlDutyJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed0101DepartmentVo;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Service
public class Ed03Service {
	@Autowired
	private ExciseCtrlDutyJdbcRepository exciseCtrlDutyJdbcRepository;

	@Autowired
	private ExciseCtrlDutyRepository exciseCtrlDutyRepository;

	public List<Ed03Vo> findByDutyGroupName(Ed03FormVo form) {
		List<Ed03Vo> resList = exciseCtrlDutyJdbcRepository.findByDutyGroupName(form);
		ExciseDepartmentVo exciseDepartmentVo = null;
		for (Ed03Vo ed03Vo : resList) {
			if (ed03Vo.getResOffcode() != null) {
				exciseDepartmentVo = ExciseDepartmentUtil.getExciseDepartment(ed03Vo.getResOffcode());
				ed03Vo.setExciseDepartmentVo(exciseDepartmentVo);
			}
		}
		return resList;
	}

	public ResponseData<String> saveExciseCtrlDuty(Ed03FormVo form) {
//		boolean check = exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form);
		ResponseData<String> response = new ResponseData<String>();
		if(!exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form)) {
//			Duplicstion Data
			response.setData("Duplicstion Data");
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
			return response;
		} else if(exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form)) {
			ExciseCtrlDuty data = new ExciseCtrlDuty();
			data.setDutyGroupCode(form.getDutyGroupCode());
			data.setDutyGroupName(form.getDutyGroupName());
			data.setResOffcode(form.getResOffcode());
			exciseCtrlDutyRepository.save(data);
			
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		}
		return response;

	}

	public ResponseData<String> editExciseCtrlDuty(Long id, Ed03FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		
		if(!exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form)) {
			response.setData("Duplicstion Data");
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
			return response;
		} else if(exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form)) {
			ExciseCtrlDuty data = exciseCtrlDutyRepository.findById(id).get();
			data.setDutyGroupCode(form.getDutyGroupCode());
			data.setDutyGroupName(form.getDutyGroupName());
			data.setResOffcode(form.getResOffcode());
			exciseCtrlDutyRepository.save(data);
			
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		}
		
		return response;
	}

	public void deleteExciseCtrlDuty(Long id) {
		exciseCtrlDutyRepository.deleteById(id);
	}

	public List<Ed0101DepartmentVo> listDepartment0014() {
		List<Ed0101DepartmentVo> dataList = exciseCtrlDutyJdbcRepository.listDepartment0014();
		return dataList;
	}
}
