package th.go.excise.ims.ws.client.ims.regp0101.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doc {

	@SerializedName("docName")
	@Expose
	private String docName;

	@SerializedName("docLink")
	@Expose
	private String docLink;

	@SerializedName("docSeq")
	@Expose
	private Integer docSeq;

	@SerializedName("docCode")
	@Expose
	private String docCode;

	@SerializedName("docDate")
	@Expose
	private String docDate;

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocLink() {
		return docLink;
	}

	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	public Integer getDocSeq() {
		return docSeq;
	}

	public void setDocSeq(Integer docSeq) {
		this.docSeq = docSeq;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

}
