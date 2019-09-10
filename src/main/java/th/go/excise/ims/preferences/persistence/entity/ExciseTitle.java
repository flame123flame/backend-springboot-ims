package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_TITLE")
public class ExciseTitle extends BaseEntity {

	private static final long serialVersionUID = -6648010199560537084L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_TITLE_GEN")
	@SequenceGenerator(name = "EXCISE_TITLE_GEN", sequenceName = "EXCISE_TITLE_SEQ", allocationSize = 1)
	@Column(name = "TITLE_ID")
	private Long titleId;
	@Column(name = "TITLE_CODE")
	private String titleCode;
	@Column(name = "TITLE_NAME")
	private String titleName;
	@Column(name = "TITLE_SEQ")
	private Integer titleSeq;
	@Column(name = "TITLE_TYPE")
	private String titleType;
	@Column(name = "SUFFIX_NAME")
	private String suffixName;
	@Column(name = "SHORT_TITLE")
	private String shortTitle;
	@Column(name = "SHORT_SUFFIX")
	private String shortSuffix;
	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

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
