package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_WORKSHEET_COND_MAIN_HDR")
public class TaWorksheetCondMainHdr extends BaseEntity {

	private static final long serialVersionUID = 1530094003754133011L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_MAIN_HDR_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_MAIN_HDR_GEN", sequenceName = "TA_WORKSHEET_COND_MAIN_HDR_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_MAIN_HDR_ID")
	private Long worksheetCondMainHdrId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "COND_GROUP_DESC")
	private String condGroupDesc;
	@Column(name = "MONTH_NUM")
	private Integer monthNum;
	@Column(name = "YEAR_MONTH_START")
	private String yearMonthStart;
	@Column(name = "YEAR_MONTH_END")
	private String yearMonthEnd;
	@Column(name = "COND_GROUP_NUM")
	private String condGroupNum;
	@Column(name = "NEW_FAC_FLAG")
	private String newFacFlag;
	@Column(name = "COMP_TYPE")
	private String compType;
	@Column(name = "REG_DATE_START")
	private LocalDate regDateStart;
	@Column(name = "REG_DATE_END")
	private LocalDate regDateEnd;
	@Column(name = "COMP_MONTH_NUM")
	private Integer compMonthNum;

	public Long getWorksheetCondMainHdrId() {
		return worksheetCondMainHdrId;
	}

	public void setWorksheetCondMainHdrId(Long worksheetCondMainHdrId) {
		this.worksheetCondMainHdrId = worksheetCondMainHdrId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getCondGroupDesc() {
		return condGroupDesc;
	}

	public void setCondGroupDesc(String condGroupDesc) {
		this.condGroupDesc = condGroupDesc;
	}

	public Integer getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}

	public String getYearMonthStart() {
		return yearMonthStart;
	}

	public void setYearMonthStart(String yearMonthStart) {
		this.yearMonthStart = yearMonthStart;
	}

	public String getYearMonthEnd() {
		return yearMonthEnd;
	}

	public void setYearMonthEnd(String yearMonthEnd) {
		this.yearMonthEnd = yearMonthEnd;
	}

	public String getCondGroupNum() {
		return condGroupNum;
	}

	public void setCondGroupNum(String condGroupNum) {
		this.condGroupNum = condGroupNum;
	}

	public String getNewFacFlag() {
		return newFacFlag;
	}

	public void setNewFacFlag(String newFacFlag) {
		this.newFacFlag = newFacFlag;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public LocalDate getRegDateStart() {
		return regDateStart;
	}

	public void setRegDateStart(LocalDate regDateStart) {
		this.regDateStart = regDateStart;
	}

	public LocalDate getRegDateEnd() {
		return regDateEnd;
	}

	public void setRegDateEnd(LocalDate regDateEnd) {
		this.regDateEnd = regDateEnd;
	}

	public Integer getCompMonthNum() {
		return compMonthNum;
	}

	public void setCompMonthNum(Integer compMonthNum) {
		this.compMonthNum = compMonthNum;
	}

}
