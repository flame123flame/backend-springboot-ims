package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_MAS_COND_MAIN_HDR")
public class TaMasCondMainHdr extends BaseEntity {

	private static final long serialVersionUID = -8020226827680362667L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_MAIN_HDR_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_MAIN_HDR_GEN", sequenceName = "TA_MAS_COND_MAIN_HDR_SEQ", allocationSize = 1)
	@Column(name = "MAS_COND_MAIN_HDR_ID")
	private Long masCondMainHdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "COND_NUMBER")
	private String condNumber;
	@Column(name = "COND_GROUP_DESC")
	private String condGroupDesc;
	@Column(name = "MONTH_NUM")
	private Integer monthNum;
	@Column(name = "COND_GROUP_NUM")
	private Integer condGroupNum;
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

	public Long getMasCondMainHdrId() {
		return masCondMainHdrId;
	}

	public void setMasCondMainHdrId(Long masCondMainHdrId) {
		this.masCondMainHdrId = masCondMainHdrId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getCondNumber() {
		return condNumber;
	}

	public void setCondNumber(String condNumber) {
		this.condNumber = condNumber;
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

	public Integer getCondGroupNum() {
		return condGroupNum;
	}

	public void setCondGroupNum(Integer condGroupNum) {
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
