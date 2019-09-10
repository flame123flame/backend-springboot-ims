package th.go.excise.ims.ws.client.pcc.incfri8020.service;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;
import th.go.excise.ims.ws.client.pcc.common.service.AbstractPccRestfulService;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.RequestData;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class IncFri8020Service extends AbstractPccRestfulService<RequestData, ResponseData> {

	@Autowired
	public IncFri8020Service(
			@Value("${ws.excise.endpoint.inc.incfri8020}") String url,
			PccServiceProperties pccServicePrpperties,
			RestfulClientService restfulClientService,
			Gson gson) {
		super.url = url;
		super.pccServicePrpperties = pccServicePrpperties;
		super.restfulClientService = restfulClientService;
		super.gson = gson;
	}

	@Override
	public ResponseData execute(RequestData requestData) throws PccRestfulException {
		return executePost(requestData);
	}

	@Override
	protected Type getResponseDataType() {
		return new TypeToken<PccResponseHeader<ResponseData>>(){}.getType();
	}	

}
