package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;

@Service
public class ParameterInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterInfoService.class);
	
	private final ParameterInfoRepository parameterInfoRepository;
	
	@Autowired
	public ParameterInfoService(ParameterInfoRepository parameterInfoRepository) {
		this.parameterInfoRepository = parameterInfoRepository;
	}
	
	public List<ParameterInfo> getAll() {
		logger.info("getAll");
		
		return parameterInfoRepository.findAll();
	}
	
	public ParameterInfo getById(Long paramInfoId) {
		logger.info("getById paramInfoId={}", paramInfoId);
		
		return parameterInfoRepository.findById(paramInfoId).orElse(null);
	}
	
	public long count() {
		logger.info("count");
		
		return parameterInfoRepository.count();
	}
	
	public ParameterInfo addParamInfo(ParameterInfo paramInfo) {
		logger.info("addParamInfo");
		
		parameterInfoRepository.save(paramInfo);
		
		return paramInfo;
	}
	
	public void updateParamInfo(ParameterInfo paramInfo) {
		logger.info("updateParamInfo");
		ParameterInfo updateParamInfo = parameterInfoRepository.findById(paramInfo.getParamInfoId()).orElse(null);
		
		updateParamInfo.setValue1(paramInfo.getValue1());
		updateParamInfo.setValue2(paramInfo.getValue2());
		updateParamInfo.setValue3(paramInfo.getValue3());
		updateParamInfo.setValue4(paramInfo.getValue4());
		updateParamInfo.setValue5(paramInfo.getValue5());
		updateParamInfo.setValue6(paramInfo.getValue6());
		updateParamInfo.setSortingOrder(paramInfo.getSortingOrder());
		updateParamInfo.setIsDefault(paramInfo.getIsDefault());
		
		parameterInfoRepository.save(updateParamInfo);
	}
	
	public void deleteById(Long paramInfoId) {
		logger.info("deleteById paramInfoId={}", paramInfoId);
		
		parameterInfoRepository.deleteById(paramInfoId);
	}
	
}
