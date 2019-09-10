package th.go.excise.ims.preferences.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ExcisePersonInfo1Vo {

	private Long id;
	private String personLogin;
	private BigDecimal childNo;
	private String childPid;
	private String childBirthDate;
	private String instituteDesc;
	private String instituteAmphurCode;
	private String instituteProvinceCode;
	private String childThTitle;
	private String childName;
	private String childSurnameName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonLogin() {
		return personLogin;
	}

	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}

	public BigDecimal getChildNo() {
		return childNo;
	}

	public void setChildNo(BigDecimal childNo) {
		this.childNo = childNo;
	}

	public String getChildPid() {
		return childPid;
	}

	public void setChildPid(String childPid) {
		this.childPid = childPid;
	}

	public String getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(String childBirthDate) {
		this.childBirthDate = childBirthDate;
	}

	public String getInstituteDesc() {
		return instituteDesc;
	}

	public void setInstituteDesc(String instituteDesc) {
		this.instituteDesc = instituteDesc;
	}

	public String getInstituteAmphurCode() {
		return instituteAmphurCode;
	}

	public void setInstituteAmphurCode(String instituteAmphurCode) {
		this.instituteAmphurCode = instituteAmphurCode;
	}

	public String getInstituteProvinceCode() {
		return instituteProvinceCode;
	}

	public void setInstituteProvinceCode(String instituteProvinceCode) {
		this.instituteProvinceCode = instituteProvinceCode;
	}

	public String getChildThTitle() {
		return childThTitle;
	}

	public void setChildThTitle(String childThTitle) {
		this.childThTitle = childThTitle;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildSurnameName() {
		return childSurnameName;
	}

	public void setChildSurnameName(String childSurnameName) {
		this.childSurnameName = childSurnameName;
	}

}
