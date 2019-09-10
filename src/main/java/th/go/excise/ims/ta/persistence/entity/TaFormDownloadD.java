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
@Table(name = "TA_FORM_DOWNLOAD_D")
public class TaFormDownloadD extends BaseEntity {

	private static final long serialVersionUID = -9188509287104895458L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_DOWNLOAD_D_GEN")
	@SequenceGenerator(name = "TA_FORM_DOWNLOAD_D_GEN", sequenceName = "TA_FORM_DOWNLOAD_D_SEQ", allocationSize = 1)
	@Column(name = "TA_FORM_DOWNLOAD_D_SEQ")
	private Long taFormDownloadDSeq;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "DOWNLOAD_NUMBER")
	private String downloadNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "APPROVED_STATUS")
	private String approvedStatus;
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;

	public Long getTaFormDownloadDSeq() {
		return taFormDownloadDSeq;
	}

	public void setTaFormDownloadDSeq(Long taFormDownloadDSeq) {
		this.taFormDownloadDSeq = taFormDownloadDSeq;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getDownloadNumber() {
		return downloadNumber;
	}

	public void setDownloadNumber(String downloadNumber) {
		this.downloadNumber = downloadNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
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

}
