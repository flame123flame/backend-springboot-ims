package th.go.excise.ims.preferences.vo;

import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;

public class ExcisePersonVoSelect extends ExcisePerson {
	
	private String name;
	private String value;
	private String officeName;
	private String subDeptName;
	private String subDeptCode;
	private String level;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getSubDeptName() {
		return subDeptName;
	}
	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}
	public String getSubDeptCode() {
		return subDeptCode;
	}
	public void setSubDeptCode(String subDeptCode) {
		this.subDeptCode = subDeptCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}
