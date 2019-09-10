package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "ADM_MENU")
public class Menu extends BaseEntity {

	private static final long serialVersionUID = -1840907549267978591L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_MENU_GEN")
	@SequenceGenerator(name = "ADM_MENU_GEN", sequenceName = "ADM_MENU_SEQ", allocationSize = 1)
	@Column(name = "MENU_ID")
	private Long menuId;

	@Column(name = "MENU_CODE")
	private String menuCode;

	@Column(name = "MENU_NAME")
	private String menuName;

	@Column(name = "PARENT_ID")
	private String parentId;

	@Column(name = "URL")
	private String URL;

	@Column(name = "SORTING_ORDER")
	private String sortingOrder;

	@Column(name = "SUBDEPT_LEVEL")
	private String subdeptLevel;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public String getSubdeptLevel() {
		return subdeptLevel;
	}

	public void setSubdeptLevel(String subdeptLevel) {
		this.subdeptLevel = subdeptLevel;
	}

}
