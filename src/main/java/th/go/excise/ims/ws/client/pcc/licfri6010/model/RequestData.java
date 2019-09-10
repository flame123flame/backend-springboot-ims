package th.go.excise.ims.ws.client.pcc.licfri6010.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("Offcode")
	@Expose
	private String offcode;

	@SerializedName("YearMonthFrom")
	@Expose
	private String yearMonthFrom;

	@SerializedName("YearMonthTo")
	@Expose
	private String yearMonthTo;

	@SerializedName("PageNo")
	@Expose
	private String pageNo;

	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getYearMonthFrom() {
		return yearMonthFrom;
	}

	public void setYearMonthFrom(String yearMonthFrom) {
		this.yearMonthFrom = yearMonthFrom;
	}

	public String getYearMonthTo() {
		return yearMonthTo;
	}

	public void setYearMonthTo(String yearMonthTo) {
		this.yearMonthTo = yearMonthTo;
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
