package th.go.excise.ims.ta.vo;

public class PlanWorkSheetSendDetailVo {

	private String officeCode;
	private String sendDate;
	private String submitDate;
	private Integer facInNum;
	private Integer facOutNum;
	private Integer facRsNum;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getFacInNum() {
		return facInNum;
	}

	public void setFacInNum(Integer facInNum) {
		this.facInNum = facInNum;
	}

	public Integer getFacOutNum() {
		return facOutNum;
	}

	public void setFacOutNum(Integer facOutNum) {
		this.facOutNum = facOutNum;
	}

	public Integer getFacRsNum() {
		return facRsNum;
	}

	public void setFacRsNum(Integer facRsNum) {
		this.facRsNum = facRsNum;
	}

}
