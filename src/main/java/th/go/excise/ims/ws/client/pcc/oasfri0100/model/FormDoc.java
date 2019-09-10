package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormDoc {

	@SerializedName("rec0142No")
	@Expose
	private String rec0142No;

	@SerializedName("rec0142Date")
	@Expose
	private String rec0142Date;

	@SerializedName("rec0142By")
	@Expose
	private String rec0142By;

	public String getRec0142No() {
		return rec0142No;
	}

	public void setRec0142No(String rec0142No) {
		this.rec0142No = rec0142No;
	}

	public String getRec0142Date() {
		return rec0142Date;
	}

	public void setRec0142Date(String rec0142Date) {
		this.rec0142Date = rec0142Date;
	}

	public String getRec0142By() {
		return rec0142By;
	}

	public void setRec0142By(String rec0142By) {
		this.rec0142By = rec0142By;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
