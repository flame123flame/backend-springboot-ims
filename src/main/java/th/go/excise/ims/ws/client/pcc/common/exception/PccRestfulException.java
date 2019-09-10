package th.go.excise.ims.ws.client.pcc.common.exception;

import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;

public class PccRestfulException extends Exception {

	private static final long serialVersionUID = 4914379263857406100L;

	public PccRestfulException(PccResponseHeader<?> response) {
		super("ResponseCode: " + response.getResponseCode() + ", ResponseMessage: " + response.getResponseMessage());
	}

	public PccRestfulException(String message) {
		super(message);
	}

	public PccRestfulException(String message, Throwable cause) {
		super(message, cause);
	}

}
