package th.go.excise.ims.ws.client.pcc.licfri6010.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

	@SerializedName("LicenseList")
	@Expose
	private List<License> licenseList;

	public List<License> getLicenseList() {
		return licenseList;
	}

	public void setLicenseList(List<License> licenseList) {
		this.licenseList = licenseList;
	}

}
