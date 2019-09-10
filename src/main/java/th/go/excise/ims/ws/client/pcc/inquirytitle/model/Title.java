package th.go.excise.ims.ws.client.pcc.inquirytitle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.go.excise.ims.ws.client.pcc.common.model.BaseModel;

public class Title extends BaseModel {

	@SerializedName("TITLE_CODE")
	@Expose
	private String titleCode;

	@SerializedName("TITLE_NAME")
	@Expose
	private String titleName;

	@SerializedName("SHORT_TITLE")
	@Expose
	private String shortTitle;

	@SerializedName("TITLE_TYPE")
	@Expose
	private String titleType;

	@SerializedName("TITLE_SEQ")
	@Expose
	private String titleSeq;

	@SerializedName("SUFFIX_NAME")
	@Expose
	private String suffixName;

	@SerializedName("SHORT_SUFFIX")
	@Expose
	private String shortSuffix;

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getTitleSeq() {
		return titleSeq;
	}

	public void setTitleSeq(String titleSeq) {
		this.titleSeq = titleSeq;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getShortSuffix() {
		return shortSuffix;
	}

	public void setShortSuffix(String shortSuffix) {
		this.shortSuffix = shortSuffix;
	}

}