package th.co.baiwa.buckwaframework.audit.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_AUDIT_LOG")
public class AuditLog extends BaseEntity {

	private static final long serialVersionUID = -4800470689270619436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_AUDIT_LOG_GEN")
	@SequenceGenerator(name = "SYS_AUDIT_LOG_GEN", sequenceName = "SYS_AUDIT_LOG_SEQ", allocationSize = 1)
	@Column(name = "SYS_AUDIT_LOG_SEQ")
	private Long sysAuditLogSeq;
	@Column(name = "ACTION_DATE")
	private LocalDateTime actionDate;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "IP_ADDRESS")
	private String ipAddress;
	@Column(name = "ACTION_NAME")
	private String actionName;
	@Column(name = "ACTION_DESC")
	private String actionDesc;

	public Long getSysAuditLogSeq() {
		return sysAuditLogSeq;
	}

	public void setSysAuditLogSeq(Long sysAuditLogSeq) {
		this.sysAuditLogSeq = sysAuditLogSeq;
	}

	public LocalDateTime getActionDate() {
		return actionDate;
	}

	public void setActionDate(LocalDateTime actionDate) {
		this.actionDate = actionDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

}
