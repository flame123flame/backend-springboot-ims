package th.co.baiwa.buckwaframework.common.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8097234880020524307L;

	public static class Field {
		public static final String IS_DELETED = "IS_DELETED";
		public static final String CREATED_BY = "CREATED_BY";
		public static final String CREATED_DATE = "CREATED_DATE";
		public static final String UPDATED_BY = "UPDATED_BY";
		public static final String UPDATED_DATE = "UPDATED_DATE";
		public static final String VERSION = "VERSION";
	}
	
	
	@Column(name = "IS_DELETED")
	protected String isDeleted;

	@Version
	@Column(name = "VERSION")
	protected Integer version;

	@Column(name = "CREATED_BY", updatable = false)
	protected String createdBy;

	@Column(name = "CREATED_DATE", updatable = false)
	protected LocalDateTime createdDate;

	@Column(name = "UPDATED_BY", nullable = true)
	protected String updatedBy;

	@Column(name = "UPDATED_DATE", nullable = true)
	protected LocalDateTime updatedDate;

	@PrePersist
	public void prePersist() {
		isDeleted = FLAG.N_FLAG;
		version = 1;
		if (StringUtils.isBlank(createdBy)) {
			createdBy = UserLoginUtils.getCurrentUsername();
		}
		createdDate = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		if (StringUtils.isBlank(updatedBy)) {
			updatedBy = UserLoginUtils.getCurrentUsername();
		}
		updatedDate = LocalDateTime.now();
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
