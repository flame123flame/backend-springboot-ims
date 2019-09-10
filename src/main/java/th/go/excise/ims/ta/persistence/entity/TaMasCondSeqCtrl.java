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
@Table(name = "TA_MAS_COND_SEQ_CTRL")
public class TaMasCondSeqCtrl extends BaseEntity {

	private static final long serialVersionUID = -8088604068461530933L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_SEQ_CTRL_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_SEQ_CTRL_GEN", sequenceName = "TA_MAS_COND_SEQ_CTRL_SEQ", allocationSize = 1)
	@Column(name = "MAS_COND_SEQ_CTRL_ID")
	private Long masCondSeqCtrlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "RUNNING_NUMBER")
	private Integer runningNumber;

	public Long getMasCondSeqCtrlId() {
		return masCondSeqCtrlId;
	}

	public void setMasCondSeqCtrlId(Long masCondSeqCtrlId) {
		this.masCondSeqCtrlId = masCondSeqCtrlId;
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

}
