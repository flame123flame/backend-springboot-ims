package th.go.excise.ims.preferences.vo;

import java.time.LocalDate;

public class Ed04ExciseTitleVo {

	private String titleCode;
	private String titleName;
	private Integer titleSeq;
	private String titleType;
	private String suffixName;
	private String shortTitle;
	private String shortSuffix;
	private LocalDate beginDate;

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

	public Integer getTitleSeq() {
		return titleSeq;
	}

	public void setTitleSeq(Integer titleSeq) {
		this.titleSeq = titleSeq;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getShortSuffix() {
		return shortSuffix;
	}

	public void setShortSuffix(String shortSuffix) {
		this.shortSuffix = shortSuffix;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

}
