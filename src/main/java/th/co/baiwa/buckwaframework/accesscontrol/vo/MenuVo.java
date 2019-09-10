package th.co.baiwa.buckwaframework.accesscontrol.vo;

import java.util.List;

public class MenuVo {

	private String parentId;
	private String rootId;
	private String menuId;
	private String menuCode;
	private String menuName;
	private String url;
	private String sortingOrder;
	private Integer lvl;
	private List<MenuVo> menuVoList;

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public String getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuVo> getMenuVoList() {
		return menuVoList;
	}

	public void setMenuVoList(List<MenuVo> menuVoList) {
		this.menuVoList = menuVoList;
	}

}
