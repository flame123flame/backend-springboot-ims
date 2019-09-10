package th.go.excise.ims.ws.client.pcc.incfri8020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("OfficeCode")
	@Expose
	private String officeCode;

	@SerializedName("YearMonthFrom")
	@Expose
	private String yearMonthFrom;

	@SerializedName("YearMonthTo")
	@Expose
	private String yearMonthTo;

	@SerializedName("DateType")
	@Expose
	private String dateType;

	@SerializedName("PageNo")
	@Expose
	private String pageNo;

	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
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

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
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
