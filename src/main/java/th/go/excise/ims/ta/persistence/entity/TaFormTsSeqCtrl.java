package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_FORM_TS_SEQ_CTRL")
public class TaFormTsSeqCtrl extends BaseEntity {

	private static final long serialVersionUID = -4839285670789436043L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS_SEQ_CTRL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS_SEQ_CTRL_GEN", sequenceName = "TA_FORM_TS_SEQ_CTRL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS_SEQ_CTRL_ID")
	private Long formTsSeqCtrlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "RUNNING_NUMBER")
	private Integer runningNumber;

	public Long getFormTsSeqCtrlId() {
		return formTsSeqCtrlId;
	}

	public void setFormTsSeqCtrlId(Long formTsSeqCtrlId) {
		this.formTsSeqCtrlId = formTsSeqCtrlId;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
