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
@Table(name = "TA_PLAN_WORKSHEET_SEND")
public class TaPlanWorksheetSend extends BaseEntity {

	private static final long serialVersionUID = -5096395203704670552L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_SEND_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_SEND_GEN", sequenceName = "TA_PLAN_WORKSHEET_SEND_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_SEND_ID")
	private Long planWorksheetSendId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "SEND_DATE")
	private LocalDate sendDate;
	@Column(name = "SUBMIT_DATE")
	private LocalDate submitDate;
	@Column(name = "FAC_IN_NUM")
	private Integer facInNum;
	@Column(name = "FAC_OUT_NUM")
	private Integer facOutNum;
	@Column(name = "RS_NUM")
	private Integer facRsNum;

	public Long getPlanWorksheetSendId() {
		return planWorksheetSendId;
	}

	public void setPlanWorksheetSendId(Long planWorksheetSendId) {
		this.planWorksheetSendId = planWorksheetSendId;
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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getFacInNum() {
		return facInNum;
	}

	public void setFacInNum(Integer facInNum) {
		this.facInNum = facInNum;
	}

	public Integer getFacOutNum() {
		return facOutNum;
	}

	public void setFacOutNum(Integer facOutNum) {
		this.facOutNum = facOutNum;
	}

	public Integer getFacRsNum() {
		return facRsNum;
	}

	public void setFacRsNum(Integer facRsNum) {
		this.facRsNum = facRsNum;
	}

}
