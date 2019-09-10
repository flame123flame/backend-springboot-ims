package th.go.excise.ims.ws.client.pcc.inquirytitle.service;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;
import th.go.excise.ims.ws.client.pcc.common.service.AbstractPccRestfulService;
import th.go.excise.ims.ws.client.pcc.inquirytitle.model.Title;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class InquiryTitleService extends AbstractPccRestfulService<Object, List<Title>> {
	
    public InquiryTitleService(
    		@Value("${ws.excise.endpoint.rdb.inquiry-title}") String url,
			PccServiceProperties pccServicePrpperties,
			RestfulClientService restfulClientService,
			Gson gson) {
		super.url = url;
		super.pccServicePrpperties = pccServicePrpperties;
		super.restfulClientService = restfulClientService;
		super.gson = gson;
	}
    
    @Override
	public List<Title> execute(Object requestData) throws PccRestfulException {
		return executePost(requestData);
	}

	@Override
	protected Type getResponseDataType() {
		return new TypeToken<PccResponseHeader<List<Title>>>(){}.getType();
	}

}
