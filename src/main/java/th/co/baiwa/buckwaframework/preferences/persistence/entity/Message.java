package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_MESSAGE")
public class Message extends BaseEntity {

	private static final long serialVersionUID = -2541426099627546386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_ID")
	private Long messageId;

	@Column(name = "MESSAGE_CODE")
	private String messageCode;

	@Column(name = "MESSAGE_EN")
	private String messageEn;

	@Column(name = "MESSAGE_TH")
	private String messageTh;

	@Column(name = "MESSAGE_TYPE")
	private String messageType;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageEn() {
		return messageEn;
	}

	public void setMessageEn(String messageEn) {
		this.messageEn = messageEn;
	}

	public String getMessageTh() {
		return messageTh;
	}

	public void setMessageTh(String messageTh) {
		this.messageTh = messageTh;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
