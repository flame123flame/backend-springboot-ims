package th.go.excise.ims.preferences.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed01FormVo;
import th.go.excise.ims.preferences.vo.Ed01Vo;

@Service
public class Ed01Service {
	@Autowired
	private ExcisePersonRepository excisePersonRepository;
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	
	@Transactional
	public void saveUserProfile(Ed01FormVo form) {
		ExcisePerson entity = new ExcisePerson();
		entity.setEdLogin(form.getUsername());
		entity.setEdPersonName(form.getName());
		entity.setEdPositionSeq(Long.parseLong(form.getPositionSeq()));
		entity.setEdPositionName(form.getPosition());
		entity.setEdOffcode(form.getOfficeCode());
		entity.setEdPersonId(form.getIdCardNumber());
		
		excisePersonRepository.save(entity);
	}
	
	public List<Ed01Vo> getIdCard(String username) {
		List<Ed01Vo> dataList = excisePersonJdbcRepository.getIdCard(username);
		return dataList;
	}
	
	
	
}
