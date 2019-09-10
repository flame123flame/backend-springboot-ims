package th.go.excise.ims.ws.client.pcc.common.service;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccRequestHeader;
import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;
import th.go.excise.ims.ws.client.service.RestfulClientService;

public abstract class AbstractPccRestfulService<REQUEST_DATA, RESPONSE_DATA> {
	
	protected String url;
	protected PccServiceProperties pccServicePrpperties;
	protected RestfulClientService restfulClientService;
	protected Gson gson;
	
	public abstract RESPONSE_DATA execute(REQUEST_DATA requestData) throws PccRestfulException;
	
	protected RESPONSE_DATA executePost(REQUEST_DATA requestData) throws PccRestfulException {
		PccRequestHeader<REQUEST_DATA> request = new PccRequestHeader<>();
		request.setSystemId(pccServicePrpperties.getSystemId());
		request.setUserName(pccServicePrpperties.getUsername());
		request.setPassword(pccServicePrpperties.getPassword());
		request.setIpAddress(pccServicePrpperties.getIpAddress());
		request.setRequestData(requestData);
		
		RESPONSE_DATA responseData = null;
		try {
			String jsonRequest = gson.toJson(request);
			String jsonResponse = restfulClientService.post(url, jsonRequest);
			PccResponseHeader<RESPONSE_DATA> response = gson.fromJson(jsonResponse, getResponseDataType());
			
			if (WEB_SERVICE.PCC_RESPONSE_CODE_OK.equals(response.getResponseCode())) {
				responseData = response.getResponseData();
			} else {
				throw new PccRestfulException(response);
			}
		} catch (JsonSyntaxException | IOException e) {
			throw new PccRestfulException(e.getMessage(), e);
		}

		return responseData;
	}
	
	protected abstract Type getResponseDataType();
	
}
