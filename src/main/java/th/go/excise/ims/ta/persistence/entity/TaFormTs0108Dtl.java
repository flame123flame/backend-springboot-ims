package th.go.excise.ims.ta.persistence.entity;

import java.util.Date;
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
@Table(name = "TA_FORM_TS0108_DTL")
public class TaFormTs0108Dtl extends BaseEntity {

	private static final long serialVersionUID = 8697452273870943503L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0108_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0108_DTL_GEN", sequenceName = "TA_FORM_TS0108_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0108_DTL_ID")
	private Long formTs0108DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_POSITION")
	private String officerPosition;
	@Column(name = "AUDIT_TIME")
	private String auditTime;
	@Column(name = "AUDIT_DEST")
	private String auditDest;
	@Column(name = "AUDIT_TOPIC")
	private String auditTopic;
	@Column(name = "APPROVED_ACK")
	private String approvedAck;
	@Column(name = "OFFICER_ACK")
	private String officerAck;
	@Column(name = "AUDIT_RESULT_DOC_NO")
	private String auditResultDocNo;
	@Column(name = "AUDIT_RESULT_DATE")
	private Date auditResultDate;
	@Column(name = "AUDIT_COMMENT")
	private String auditComment;

	public Long getFormTs0108DtlId() {
		return formTs0108DtlId;
	}

	public void setFormTs0108DtlId(Long formTs0108DtlId) {
		this.formTs0108DtlId = formTs0108DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditDest() {
		return auditDest;
	}

	public void setAuditDest(String auditDest) {
		this.auditDest = auditDest;
	}

	public String getAuditTopic() {
		return auditTopic;
	}

	public void setAuditTopic(String auditTopic) {
		this.auditTopic = auditTopic;
	}

	public String getApprovedAck() {
		return approvedAck;
	}

	public void setApprovedAck(String approvedAck) {
		this.approvedAck = approvedAck;
	}

	public String getOfficerAck() {
		return officerAck;
	}

	public void setOfficerAck(String officerAck) {
		this.officerAck = officerAck;
	}

	public String getAuditResultDocNo() {
		return auditResultDocNo;
	}

	public void setAuditResultDocNo(String auditResultDocNo) {
		this.auditResultDocNo = auditResultDocNo;
	}

	public Date getAuditResultDate() {
		return auditResultDate;
	}

	public void setAuditResultDate(Date auditResultDate) {
		this.auditResultDate = auditResultDate;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
