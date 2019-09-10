package th.go.excise.ims.ws.client.ims.regp0101.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import th.go.excise.ims.ws.client.ims.regp0101.model.RequestData;
import th.go.excise.ims.ws.client.ims.regp0101.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class RegP0101Service {
	
	private String url;
	private RestfulClientService restfulClientService;
	private Gson gson;
	
	@Autowired
	public RegP0101Service(
			@Value("${ws.excise.endpoint.ims.reg-p0101}") String url,
			RestfulClientService restfulClientService,
			Gson gson) {
		this.url = url;
		this.restfulClientService = restfulClientService;
		this.gson = gson;
	}
	
	public ResponseData execute(RequestData requestData) throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("newRegId", requestData.getNewRegId());
		
		String respJson = restfulClientService.get(urlBuilder.build().toString());
		ResponseData responseData = gson.fromJson(respJson, ResponseData.class);
		
		return responseData;
	}
	
	public byte[] executeDocLink(String docLink) throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(docLink).newBuilder();
		
		byte[] contents = restfulClientService.getBytes(urlBuilder.build().toString());
		
		return contents;
	}
	
}
