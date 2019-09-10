package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_SUBDEPT")
public class ExciseSubdept extends BaseEntity {

	private static final long serialVersionUID = -3865399150045748933L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_SUBDEPT_GEN")
	@SequenceGenerator(name = "EXCISE_SUBDEPT_GEN", sequenceName = "EXCISE_SUBDEPT_SEQ", allocationSize = 1)
	@Column(name = "ED_SUBDEPT_SEQ")
	private Long edSubdeptSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "SUBDEPT_CODE")
	private String subdeptCode;
	@Column(name = "SUBDEPT_NAME")
	private String subdeptName;
	@Column(name = "SUBDEPT_SHORT_NAME")
	private String subdeptShortName;
	@Column(name = "AUDIT_SELECT_FLAG")
	private String auditSelectFlag;

	public Long getEdSubdeptSeq() {
		return edSubdeptSeq;
	}

	public void setEdSubdeptSeq(Long edSubdeptSeq) {
		this.edSubdeptSeq = edSubdeptSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getSubdeptCode() {
		return subdeptCode;
	}

	public void setSubdeptCode(String subdeptCode) {
		this.subdeptCode = subdeptCode;
	}

	public String getSubdeptName() {
		return subdeptName;
	}

	public void setSubdeptName(String subdeptName) {
		this.subdeptName = subdeptName;
	}

	public String getSubdeptShortName() {
		return subdeptShortName;
	}

	public void setSubdeptShortName(String subdeptShortName) {
		this.subdeptShortName = subdeptShortName;
	}

	public String getAuditSelectFlag() {
		return auditSelectFlag;
	}

	public void setAuditSelectFlag(String auditSelectFlag) {
		this.auditSelectFlag = auditSelectFlag;
	}
	

}
