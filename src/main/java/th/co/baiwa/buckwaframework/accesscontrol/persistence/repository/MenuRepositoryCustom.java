package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;

public interface MenuRepositoryCustom {

	//public List<MenuVo> listMenu();
	
	public List<MenuVo> findByRolesAndSubdeptLevel(List<String> roleList, String subdeptLevel);

}
