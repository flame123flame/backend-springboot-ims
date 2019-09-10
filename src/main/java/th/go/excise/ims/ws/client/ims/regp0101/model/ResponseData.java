package th.go.excise.ims.ws.client.ims.regp0101.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

	@SerializedName("docList")
	@Expose
	private List<Doc> docList;

	@SerializedName("statusCode")
	@Expose
	private String statusCode;

	public List<Doc> getDocList() {
		return docList;
	}

	public void setDocList(List<Doc> docList) {
		this.docList = docList;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
