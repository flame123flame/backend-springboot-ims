package th.go.excise.ims.preferences.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePositionJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed0101DepartmentVo;
import th.go.excise.ims.preferences.vo.Ed0101PositionVo;
import th.go.excise.ims.preferences.vo.Ed0101Vo;

@Service
public class Ed0101Service {
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	@Autowired
	private ExcisePositionJdbcRepository excisePositionJdbcRepository;
	
	public List<Ed0101Vo> listUser() {
		List<Ed0101Vo> lists = new ArrayList<>();
		lists = excisePersonJdbcRepository.listUser();	
		for (Ed0101Vo ed0101Vo : lists) {
			if(ed0101Vo.getEdOffcode() != null) {
				ExciseDepartmentVo exciseDepartmentVo = ExciseDepartmentUtil.getExciseDepartment(ed0101Vo.getEdOffcode());
				ed0101Vo.setExciseDepartmentVo(exciseDepartmentVo);
			}
		}
		return lists;
	}
	
	public List<Ed0101PositionVo> listPosition() {
		List<Ed0101PositionVo> dataList = excisePositionJdbcRepository.listPosition();
		return dataList;
	}
	
	public List<Ed0101DepartmentVo> listDepartment00() {
		List<Ed0101DepartmentVo> dataList = excisePositionJdbcRepository.listDepartment00();
		return dataList;
	}
	
	public List<Ed0101DepartmentVo> listDepartment01() {
		List<Ed0101DepartmentVo> dataList = excisePositionJdbcRepository.listDepartment01();
		return dataList;
	}
	
	
	
}
