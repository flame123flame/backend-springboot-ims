package th.go.excise.ims.preferences.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExcisePosition;
import th.go.excise.ims.preferences.persistence.repository.ExcisePositionRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePositionJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed0201FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Service
public class Ed0201Service {
	
	@Autowired
	private ExcisePositionRepository excisePositionRepository;
	
	@Autowired
	private ExcisePositionJdbcRepository excisePositionJdbcRepository;
	
	
	@Transactional
	public void saveConfigPosition(Ed0201FormVo form) {
		ExcisePosition entity = new ExcisePosition();
		BigDecimal AllowancesHalfDay = new BigDecimal(form.getAllowancesHalfDay()); 
		BigDecimal AllowancesDay = new BigDecimal(form.getAllowancesDay()); 
		BigDecimal AccomFeeSingle = new BigDecimal(form.getAccomFeeSingle()); 
		BigDecimal AccomFeeDouble = new BigDecimal(form.getAccomFeeDouble()); 
		BigDecimal AccomFeePackages = new BigDecimal(form.getAccomFeePackages()); 
		entity.setEdPositionName(form.getEdPositionName());
		entity.setAllowancesHalfDay(AllowancesHalfDay);
		entity.setAllowancesDay(AllowancesDay);
		entity.setAccomFeeSingle(AccomFeeSingle);
		entity.setAccomFeeDouble(AccomFeeDouble);
		entity.setAccomFeePackages(AccomFeePackages);
		excisePositionRepository.save(entity);
	}
	
	
	public List<Ed02Vo> getConfigEdit(BigDecimal edPersonSeq) {
		List<Ed02Vo> dataList = excisePositionJdbcRepository.getConfigEdit(edPersonSeq);
		return dataList;
	}
	
	public void updateConfigposition(Ed0201FormVo form) {
		ExcisePosition configData = new ExcisePosition();
		configData = excisePositionRepository.findById(form.getEdPersonSeq()).get();
		BigDecimal AllowancesHalfDay = new BigDecimal(form.getAllowancesHalfDay()); 
		BigDecimal AllowancesDay = new BigDecimal(form.getAllowancesDay()); 
		BigDecimal AccomFeeSingle = new BigDecimal(form.getAccomFeeSingle()); 
		BigDecimal AccomFeeDouble = new BigDecimal(form.getAccomFeeDouble()); 
		BigDecimal AccomFeePackages = new BigDecimal(form.getAccomFeePackages()); 
		configData.setEdPositionName(form.getEdPositionName());
		configData.setAllowancesHalfDay(AllowancesHalfDay);
		configData.setAllowancesDay(AllowancesDay);
		configData.setAccomFeeSingle(AccomFeeSingle);
		configData.setAccomFeeDouble(AccomFeeDouble);
		configData.setAccomFeePackages(AccomFeePackages);
		excisePositionRepository.save(configData);
	}
}
