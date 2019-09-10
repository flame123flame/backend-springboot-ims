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
@Table(name = "TA_PAPER_BA_H")
public class TaPaperBaH extends BaseEntity {

	private static final long serialVersionUID = 4775932454894074687L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_H_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_H_GEN", sequenceName = "TA_PAPER_BA_H_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_H_SEQ")
	private Long paperBaHSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DUTY_GROUP_ID")
	private String dutyGroupId;
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;
	@Column(name = "MONTH_INC_TYPE")
	private String monthIncType;
	@Column(name = "YEAR_INC_TYPE")
	private String yearIncType;
	@Column(name = "YEAR_NUM")
	private Integer yearNum;
	@Column(name = "ANA_RESULT_TEXT1")
	private String anaResultText1;
	@Column(name = "ANA_RESULT_TEXT2")
	private String anaResultText2;
	@Column(name = "ANA_RESULT_TEXT3")
	private String anaResultText3;
	@Column(name = "ANA_RESULT_TEXT4")
	private String anaResultText4;
	@Column(name = "ANA_RESULT_TEXT5")
	private String anaResultText5;
	@Column(name = "ANA_RESULT_TEXT6")
	private String anaResultText6;
	@Column(name = "ANA_RESULT_TEXT7")
	private String anaResultText7;
	@Column(name = "ANA_RESULT_TEXT8")
	private String anaResultText8;

	public Long getPaperBaHSeq() {
		return paperBaHSeq;
	}

	public void setPaperBaHSeq(Long paperBaHSeq) {
		this.paperBaHSeq = paperBaHSeq;
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

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getPaperBaNumber() {
		return paperBaNumber;
	}

	public void setPaperBaNumber(String paperBaNumber) {
		this.paperBaNumber = paperBaNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getMonthIncType() {
		return monthIncType;
	}

	public void setMonthIncType(String monthIncType) {
		this.monthIncType = monthIncType;
	}

	public String getYearIncType() {
		return yearIncType;
	}

	public void setYearIncType(String yearIncType) {
		this.yearIncType = yearIncType;
	}

	public Integer getYearNum() {
		return yearNum;
	}

	public void setYearNum(Integer yearNum) {
		this.yearNum = yearNum;
	}

	public String getAnaResultText1() {
		return anaResultText1;
	}

	public void setAnaResultText1(String anaResultText1) {
		this.anaResultText1 = anaResultText1;
	}

	public String getAnaResultText2() {
		return anaResultText2;
	}

	public void setAnaResultText2(String anaResultText2) {
		this.anaResultText2 = anaResultText2;
	}

	public String getAnaResultText3() {
		return anaResultText3;
	}

	public void setAnaResultText3(String anaResultText3) {
		this.anaResultText3 = anaResultText3;
	}

	public String getAnaResultText4() {
		return anaResultText4;
	}

	public void setAnaResultText4(String anaResultText4) {
		this.anaResultText4 = anaResultText4;
	}

	public String getAnaResultText5() {
		return anaResultText5;
	}

	public void setAnaResultText5(String anaResultText5) {
		this.anaResultText5 = anaResultText5;
	}

	public String getAnaResultText6() {
		return anaResultText6;
	}

	public void setAnaResultText6(String anaResultText6) {
		this.anaResultText6 = anaResultText6;
	}

	public String getAnaResultText7() {
		return anaResultText7;
	}

	public void setAnaResultText7(String anaResultText7) {
		this.anaResultText7 = anaResultText7;
	}

	public String getAnaResultText8() {
		return anaResultText8;
	}

	public void setAnaResultText8(String anaResultText8) {
		this.anaResultText8 = anaResultText8;
	}

}
