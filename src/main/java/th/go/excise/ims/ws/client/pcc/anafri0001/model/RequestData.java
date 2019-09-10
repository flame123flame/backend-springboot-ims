package th.go.excise.ims.ws.client.pcc.anafri0001.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("RegistrationId")
	@Expose
	private String registrationId;

	@SerializedName("StartDate")
	@Expose
	private String startDate;

	@SerializedName("EndDate")
	@Expose
	private String endDate;

	@SerializedName("FormCode")
	@Expose
	private String formCode;

	@SerializedName("PageNo")
	@Expose
	private String pageNo;

	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

}
