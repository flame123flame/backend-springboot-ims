package th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuedatePs0112 {

	@SerializedName("YEAR")
	@Expose
	private String year;

	@SerializedName("MONTH")
	@Expose
	private String month;

	@SerializedName("DUE_DATE")
	@Expose
	private String duedate;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

}
