package th.go.excise.ims.ws.client.pcc.inquiryedoffice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.go.excise.ims.ws.client.pcc.common.model.BaseModel;

public class EdOffice extends BaseModel {

	@SerializedName("OFFCODE")
	@Expose
	private String offcode;

	@SerializedName("OFFNAME")
	@Expose
	private String offname;

	@SerializedName("SHORT_NAME")
	@Expose
	private String shortName;

	@SerializedName("INDC_OFF")
	@Expose
	private String indcOff;

	@SerializedName("SUPOFFCODE")
	@Expose
	private String supoffcode;

	@SerializedName("TAMBOL_CODE")
	@Expose
	private String tambolCode;

	@SerializedName("TELNO")
	@Expose
	private String telno;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getOffname() {
		return offname;
	}

	public void setOffname(String offname) {
		this.offname = offname;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getIndcOff() {
		return indcOff;
	}

	public void setIndcOff(String indcOff) {
		this.indcOff = indcOff;
	}

	public String getSupoffcode() {
		return supoffcode;
	}

	public void setSupoffcode(String supoffcode) {
		this.supoffcode = supoffcode;
	}

	public String getTambolCode() {
		return tambolCode;
	}

	public void setTambolCode(String tambolCode) {
		this.tambolCode = tambolCode;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

}
