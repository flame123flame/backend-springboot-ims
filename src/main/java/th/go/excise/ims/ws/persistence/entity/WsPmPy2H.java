package th.go.excise.ims.ws.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_PM_PY2_H")
public class WsPmPy2H extends BaseEntity {

	private static final long serialVersionUID = 4031935511630938897L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_PM_PY2_H_GEN")
	@SequenceGenerator(name = "WS_PM_PY2_H_GEN", sequenceName = "WS_PM_PY2_H_SEQ", allocationSize = 1)
	@Column(name = "PM_PY2_H_SEQ")
	private Long pmPy2HSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "OFF_NAME")
	private String offName;
	@Column(name = "FORM_YEAR")
	private String formYear;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "FORM_NAME")
	private String formName;
	@Column(name = "FORM_ROUND")
	private String formRound;
	@Column(name = "FORM_STATUS")
	private String formStatus;
	@Column(name = "FORM_STATUS_DESC")
	private String formStatusDesc;

	public Long getPmPy2HSeq() {
		return pmPy2HSeq;
	}

	public void setPmPy2HSeq(Long pmPy2HSeq) {
		this.pmPy2HSeq = pmPy2HSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getFormYear() {
		return formYear;
	}

	public void setFormYear(String formYear) {
		this.formYear = formYear;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormRound() {
		return formRound;
	}

	public void setFormRound(String formRound) {
		this.formRound = formRound;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormStatusDesc() {
		return formStatusDesc;
	}

	public void setFormStatusDesc(String formStatusDesc) {
		this.formStatusDesc = formStatusDesc;
	}

}
