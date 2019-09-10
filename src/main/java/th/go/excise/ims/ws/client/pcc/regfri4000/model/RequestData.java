package th.go.excise.ims.ws.client.pcc.regfri4000.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("Type")
	@Expose
	private String type;

	@SerializedName("Nid")
	@Expose
	private String nid;

	@SerializedName("NewregId")
	@Expose
	private String newregId;

	@SerializedName("HomeOfficeId")
	@Expose
	private String homeOfficeId;

	@SerializedName("Active")
	@Expose
	private String active;

	@SerializedName("PageNo")
	@Expose
	private String pageNo;

	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getHomeOfficeId() {
		return homeOfficeId;
	}

	public void setHomeOfficeId(String homeOfficeId) {
		this.homeOfficeId = homeOfficeId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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
