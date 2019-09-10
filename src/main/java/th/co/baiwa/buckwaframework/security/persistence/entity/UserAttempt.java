package th.co.baiwa.buckwaframework.security.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADM_USER_ATTEMPT")
public class UserAttempt {

	@Id
	@GeneratedValue(generator = "USER_ATTEMPT_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "USER_ATTEMPT_SEQ", sequenceName = "ADM_USER_ATTEMPT_SEQ", allocationSize = 1)
	@Column(name = "USER_ATTEMPT_ID")
	private Long userAttemptId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "ATTEMPTS")
	private Integer attempts;

	@Column(name = "LAST_MODIFIED")
	private LocalDateTime lastModified;

	public Long getUserAttemptId() {
		return userAttemptId;
	}

	public void setUserAttemptId(Long userAttemptId) {
		this.userAttemptId = userAttemptId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

}
