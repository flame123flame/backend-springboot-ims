package th.go.excise.ims.ws.client.pcc.oasfri0100.service;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccRequestHeader;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.RequestData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseData2;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseHeader;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class OasFri0100Service {
	
	private String url;
	private PccServiceProperties pccServicePrpperties;
	private RestfulClientService restfulClientService;
	private Gson gson;
	
	@Autowired
	public OasFri0100Service(
			@Value("${ws.excise.endpoint.oas.oasfri0100}") String url,
			PccServiceProperties pccServicePrpperties,
			RestfulClientService restfulClientService,
			Gson gson) {
		this.url = url;
		this.pccServicePrpperties = pccServicePrpperties;
		this.restfulClientService = restfulClientService;
		this.gson = gson;
	}

	public ResponseData2 execute(RequestData requestData) throws PccRestfulException {
		PccRequestHeader<RequestData> request = new PccRequestHeader<>();
		request.setSystemId(pccServicePrpperties.getSystemId());
		request.setUserName(pccServicePrpperties.getUsername());
		request.setPassword(pccServicePrpperties.getPassword());
		request.setIpAddress(pccServicePrpperties.getIpAddress());
		request.setRequestData(requestData);
		
		ResponseData2 responseData = null;
		try {
			String jsonRequest = gson.toJson(request);
			String jsonResponse = restfulClientService.post(url, jsonRequest);
			ResponseHeader responseHeader = gson.fromJson(jsonResponse, getResponseDataType());
			
			if (WEB_SERVICE.PCC_RESPONSE_CODE_OK.equals(responseHeader.getResponseData().getResponseCode())) {
				responseData = responseHeader.getResponseData().getResponseData();
			} else {
				ResponseData respData = responseHeader.getResponseData();
				String msg = "ResponseCode: " + respData.getResponseCode() + ", ResponseMessage: " + respData.getResponseMessage();
				throw new PccRestfulException(msg);
			}
		} catch (JsonSyntaxException | IOException e) {
			throw new PccRestfulException(e.getMessage(), e);
		}

		return responseData;
	}

	protected Type getResponseDataType() {
		return new TypeToken<ResponseHeader>(){}.getType();
	}

}
