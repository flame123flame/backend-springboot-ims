package th.go.excise.ims.preferences.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.service.ParameterInfoService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.preferences.vo.AdminPreferencesFormVo;

@Service
public class AdminPreferencesService {
	
	@Autowired
	private ParameterInfoService parameterInfoService;
	
	public void taConfigUpdate(AdminPreferencesFormVo formVo) {
		
		if (StringUtils.isNotBlank(formVo.getSendAllFlag())) {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.SEND_ALL_FLAG);
			ParameterInfo parameterInfo = parameterInfoService.getById(paramInfo.getParamInfoId());
			parameterInfo.setValue1(formVo.getSendAllFlag());
			parameterInfoService.updateParamInfo(parameterInfo);
		}
		if (StringUtils.isNotBlank(formVo.getSeeFlag())) {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.SEE_FLAG);
			ParameterInfo parameterInfo = parameterInfoService.getById(paramInfo.getParamInfoId());
			parameterInfo.setValue1(formVo.getSeeFlag());
			parameterInfoService.updateParamInfo(parameterInfo);
		}
		if (StringUtils.isNotBlank(formVo.getSelectFlag())) {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.SELECT_FLAG);
			ParameterInfo parameterInfo = parameterInfoService.getById(paramInfo.getParamInfoId());
			parameterInfo.setValue1(formVo.getSelectFlag());
			parameterInfoService.updateParamInfo(parameterInfo);
		}
		if (StringUtils.isNotBlank(formVo.getIncomeType())) {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.INCOME_TYPE);
			ParameterInfo parameterInfo = parameterInfoService.getById(paramInfo.getParamInfoId());
			parameterInfo.setValue1(formVo.getIncomeType());
			parameterInfoService.updateParamInfo(parameterInfo);
		}
	}	

}
