package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowDetailJdbcRepository;
import th.go.excise.ims.ia.vo.Int11050101FormVo;

@Service
public class Int11050101Service {

	@Autowired
	private IaConcludeFollowDetailJdbcRepository iaConcludeFollowDetailJdbcRepository;
	
	public void editDetailPerformance(Int11050101FormVo form) {
		iaConcludeFollowDetailJdbcRepository.editDetailPerformance(form);
	}
	
}
