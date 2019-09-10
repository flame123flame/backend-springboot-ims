package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.MenuRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Service
public class MenuService {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	private MenuRepository menuRepository;

	public List<MenuVo> findByRoles() {
		List<String> roleList = UserLoginUtils.getGrantedAuthorityList();
		String subdeptLevel = UserLoginUtils.getCurrentUserBean().getSubdeptLevel();
		logger.info("findByRole roleList={}, subdeptLevel={}", org.springframework.util.StringUtils.collectionToCommaDelimitedString(roleList), subdeptLevel);
		
		// ==> query list menu
		List<MenuVo> menuList = menuRepository.findByRolesAndSubdeptLevel(roleList, subdeptLevel);
		
		List<MenuVo> roots = menuList.stream().filter(p -> p.getParentId() == null).collect(Collectors.toList());
		// ==> add root
		List<MenuVo> resultList = new ArrayList<MenuVo>();
		roots.forEach(e -> {
			MenuVo vo = new MenuVo();
			vo.setMenuId(e.getMenuId());
			vo.setParentId(e.getParentId());
			vo.setMenuName(e.getMenuName());
			vo.setUrl(e.getUrl());
			vo.setSortingOrder(e.getSortingOrder());
			vo.setMenuCode(e.getMenuCode());
			if (1 == e.getLvl()) {
				List<MenuVo> list = recursiveFunc(e.getMenuId(), e.getUrl(), menuList);
				vo.setMenuVoList(list);
			}
			resultList.add(vo);
		});
		
		return resultList;
	}

	private List<MenuVo> recursiveFunc(String id, String url, List<MenuVo> menuList) {
		List<MenuVo> dataList = new ArrayList<MenuVo>();
		menuList.forEach(e -> {
			if (id.equals(e.getParentId())) {
				dataList.add(e);
			}
		});
		for (int i = 0; i < dataList.size(); i++) {
			if (StringUtils.isNotBlank(dataList.get(i).getMenuId()) && dataList.get(i).getUrl() == null) {
				dataList.get(i).setMenuVoList(recursiveFunc(dataList.get(i).getMenuId(), dataList.get(i).getUrl(), menuList));
			}
		}
		return dataList;
	}

}
