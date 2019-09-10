package th.co.baiwa.buckwaframework.security.domain;

import java.util.List;

public class UserProfileVo {

	private String username;
	private String userThaiName;
	private String userThaiSurname;
	private String title;
	private String officeCode;
	private String departmentName;
	private List<String> authorityList;
	private String subdeptCode;
	private String subdeptLevel;
	private Boolean isCentral;
	private String systemName;

	public Boolean getIsCentral() {
		return isCentral;
	}

	public void setIsCentral(Boolean isCentral) {
		this.isCentral = isCentral;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserThaiName() {
		return userThaiName;
	}

	public void setUserThaiName(String userThaiName) {
		this.userThaiName = userThaiName;
	}

	public String getUserThaiSurname() {
		return userThaiSurname;
	}

	public void setUserThaiSurname(String userThaiSurname) {
		this.userThaiSurname = userThaiSurname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	public String getSubdeptCode() {
		return subdeptCode;
	}

	public void setSubdeptCode(String subdeptCode) {
		this.subdeptCode = subdeptCode;
	}

	public String getSubdeptLevel() {
		return subdeptLevel;
	}

	public void setSubdeptLevel(String subdeptLevel) {
		this.subdeptLevel = subdeptLevel;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}
