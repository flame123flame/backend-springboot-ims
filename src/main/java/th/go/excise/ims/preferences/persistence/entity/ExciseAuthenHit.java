
package th.go.excise.ims.preferences.persistence.entity;

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
@Table(name = "EXCISE_AUTHEN_HIT")
public class ExciseAuthenHit extends BaseEntity {

	
	private static final long serialVersionUID = 3257656837015199785L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_AUTHEN_HIT_GEN")
	@SequenceGenerator(name = "EXCISE_AUTHEN_HIT_GEN", sequenceName = "EXCISE_AUTHEN_HIT_SEQ", allocationSize = 1)
	@Column(name = "AUTHEN_HIT_ID")
	private Long authenHitId;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "TIME")
	private LocalDateTime time;
	@Column(name = "EXCISE_PERSON_ID")
	private Long excisePersonId;
	@Column(name = "ACTION_TYPE")
	private String actionType;

	public Long getAuthenHitId() {
		return authenHitId;
	}

	public void setAuthenHitId(Long authenHitId) {
		this.authenHitId = authenHitId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}


	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Long getExcisePersonId() {
		return excisePersonId;
	}

	public void setExcisePersonId(Long excisePersonId) {
		this.excisePersonId = excisePersonId;
	}

}
