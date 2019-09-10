package th.go.excise.ims.preferences.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExcisePositionRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePositionJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Service
public class Ed02Service {

	@Autowired
	private ExcisePositionJdbcRepository excisePositionJdbcRepository;
	

	public List<Ed02Vo> list(Ed02FormVo form) {
		List<Ed02Vo> positionList = new ArrayList<Ed02Vo>();
		positionList = excisePositionJdbcRepository.list(form);
		return positionList;
	}
	
	public void delete(Ed02FormVo formvo) {
//		ExcisePosition entity = excisePositionRepository.findById(formvo.getEdPersonSeq()).get();
		excisePositionJdbcRepository.deleteById(formvo);
	}
}
