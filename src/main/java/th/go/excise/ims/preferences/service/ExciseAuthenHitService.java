package th.go.excise.ims.preferences.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseAuthenHit;
import th.go.excise.ims.preferences.persistence.repository.ExciseAuthenHitRepository;

@Service
public class ExciseAuthenHitService {
	
	@Autowired
	private ExciseAuthenHitRepository exciseAuthenHitRepository;
	
	public ExciseAuthenHit save(ExciseAuthenHit entity) {
		return exciseAuthenHitRepository.save(entity);
	}
	
}
