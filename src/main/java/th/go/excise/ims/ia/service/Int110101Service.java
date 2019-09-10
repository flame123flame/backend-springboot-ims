package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowDetailJdbcRepository;
import th.go.excise.ims.ia.vo.Int110101FormVo;
import th.go.excise.ims.ia.vo.Int110101Vo;

@Service
public class Int110101Service {
	
	@Autowired
	private IaConcludeFollowDetailJdbcRepository iaConcludeFollowDetailJdbcRepository;
	
	public void editDetails(Int110101FormVo form) {
		iaConcludeFollowDetailJdbcRepository.editDetails(form);
	}
	
	public List<Int110101Vo> findConcludeFollowEdit(String id) {
		List<Int110101Vo> dataList = iaConcludeFollowDetailJdbcRepository.findConcludeFollowEdit(id);	
		return dataList;
	}
	
}
