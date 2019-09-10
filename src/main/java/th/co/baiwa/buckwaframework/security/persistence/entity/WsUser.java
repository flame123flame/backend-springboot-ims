package th.co.baiwa.buckwaframework.security.persistence.entity;

public class WsUser {

	private Long wsUserId;
	private String userThaiId;
	private String userThaiName;
	private String userThaiSurname;
	private String userEngName;
	private String userEngSurname;
	private String title;
	private String userId;
	private String password;
	private String email;
	private String cnName;
	private String officeId;
	private String accessAttr;
	private String officeCode;
	private String isDeleted;

	public Long getWsUserId() {
		return wsUserId;
	}

	public void setWsUserId(Long wsUserId) {
		this.wsUserId = wsUserId;
	}

	public String getUserThaiId() {
		return userThaiId;
	}

	public void setUserThaiId(String userThaiId) {
		this.userThaiId = userThaiId;
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

	public String getUserEngName() {
		return userEngName;
	}

	public void setUserEngName(String userEngName) {
		this.userEngName = userEngName;
	}

	public String getUserEngSurname() {
		return userEngSurname;
	}

	public void setUserEngSurname(String userEngSurname) {
		this.userEngSurname = userEngSurname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getAccessAttr() {
		return accessAttr;
	}

	public void setAccessAttr(String accessAttr) {
		this.accessAttr = accessAttr;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
