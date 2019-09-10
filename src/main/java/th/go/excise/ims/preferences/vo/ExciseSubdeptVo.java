package th.go.excise.ims.preferences.vo;

public class ExciseSubdeptVo implements ExciseSubdept {

	private String officeCode;
	private String subdeptCode;
	private String subdeptName;
	private String subdeptShortName;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSubdeptCode() {
		return subdeptCode;
	}

	public void setSubdeptCode(String subdeptCode) {
		this.subdeptCode = subdeptCode;
	}

	public String getSubdeptName() {
		return subdeptName;
	}

	public void setSubdeptName(String subdeptName) {
		this.subdeptName = subdeptName;
	}

	public String getSubdeptShortName() {
		return subdeptShortName;
	}

	public void setSubdeptShortName(String subdeptShortName) {
		this.subdeptShortName = subdeptShortName;
	}

}
