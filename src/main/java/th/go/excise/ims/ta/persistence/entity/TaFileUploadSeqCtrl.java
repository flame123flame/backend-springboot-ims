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
@Table(name = "TA_FILE_UPLOAD_SEQ_CTRL")
public class TaFileUploadSeqCtrl extends BaseEntity {

	private static final long serialVersionUID = 2483180041093196841L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FILE_UPLOAD_SEQ_CTRL_GEN")
	@SequenceGenerator(name = "TA_FILE_UPLOAD_SEQ_CTRL_GEN", sequenceName = "TA_FILE_UPLOAD_SEQ_CTRL_SEQ", allocationSize = 1)
	@Column(name = "TA_FILE_UPLOAD_SEQ_CTRL_ID")
	private Long taFileUploadSeqCtrlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "RUNNING_NUMBER")
	private Integer runningNumber;
	@Column(name = "RUNNING_TYPE")
	private String runningType;

	public Long getTaFileUploadSeqCtrlId() {
		return taFileUploadSeqCtrlId;
	}

	public void setTaFileUploadSeqCtrlId(Long taFileUploadSeqCtrlId) {
		this.taFileUploadSeqCtrlId = taFileUploadSeqCtrlId;
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
