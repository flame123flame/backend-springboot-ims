package th.go.excise.ims.ws.client.pcc.anafri0001.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

	@SerializedName("FormList")
	@Expose
	private List<Form> formList;

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

}
