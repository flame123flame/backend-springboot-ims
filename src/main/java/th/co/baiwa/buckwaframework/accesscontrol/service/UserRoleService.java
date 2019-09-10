package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.RoleOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.UserRole;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRoleRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Service
public class UserRoleService {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	public DataTableAjax<UserRoleVo> list(UserRoleFormVo request) {

		List<UserRoleVo> data = userRoleRepository.findByCriteria(request);

		DataTableAjax<UserRoleVo> dataTableAjax = new DataTableAjax<UserRoleVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(userRoleRepository.countByCriteria(request));
		dataTableAjax.setRecordsFiltered(userRoleRepository.countByCriteria(request));
		return dataTableAjax;
	}


	public DataTableAjax<UserRoleVo> listData(UserRoleFormVo request) {
		List<UserRoleVo> data = userRoleRepository.findById(request);

		DataTableAjax<UserRoleVo> dataTableAjax = new DataTableAjax<UserRoleVo>();
		dataTableAjax.setData(data);
		return dataTableAjax;
	}

	public UserRole findById(Long id) {
		return userRoleRepository.findById(id).get();
	}

	public List<UserRole> findAll(Long id) {
		return userRoleRepository.findAll();
	}

	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	public void delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
	}
}
