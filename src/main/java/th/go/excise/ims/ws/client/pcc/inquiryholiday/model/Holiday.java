package th.go.excise.ims.ws.client.pcc.inquiryholiday.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Holiday {

	@SerializedName("HOLIDAY_DATE")
	@Expose
	private String holidayDate;

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

}
