package th.co.baiwa.buckwaframework.preferences.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class MessageCriteriaVo extends DataTableRequest {

	private String messageCode;
	private String messageEn;
	private String messageTh;
	private String messageType;

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
