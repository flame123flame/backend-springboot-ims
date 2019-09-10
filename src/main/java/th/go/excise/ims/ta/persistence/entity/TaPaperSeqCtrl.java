package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_SEQ_CTRL")
public class TaPaperSeqCtrl extends BaseEntity {

	private static final long serialVersionUID = 7874407142442154401L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SEQ_CTRL_GEN")
	@SequenceGenerator(name = "TA_PAPER_SEQ_CTRL_GEN", sequenceName = "TA_PAPER_SEQ_CTRL_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SEQ_CTRL_ID")
	private Long paperSeqCtrlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "RUNNING_NUMBER")
	private Integer runningNumber;
	@Column(name = "RUNNING_TYPE")
	private String runningType;

	public Long getPaperSeqCtrlId() {
		return paperSeqCtrlId;
	}

	public void setPaperSeqCtrlId(Long paperSeqCtrlId) {
		this.paperSeqCtrlId = paperSeqCtrlId;
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

	public Integer getRunningNumber() {
		return runningNumber;
	}

	public void setRunningNumber(Integer runningNumber) {
		this.runningNumber = runningNumber;
	}

	public String getRunningType() {
		return runningType;
	}

	public void setRunningType(String runningType) {
		this.runningType = runningType;
	}

}
