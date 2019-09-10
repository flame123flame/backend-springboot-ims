package th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffCodeAddress {
	
	@SerializedName("OFFCODE")
	@Expose
	private String offcode;

	@SerializedName("ADDRESS")
	@Expose
	private String address;

	@SerializedName("TEL_NO")
	@Expose
	private String telNo;

	@SerializedName("FAX")
	@Expose
	private String fax;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}