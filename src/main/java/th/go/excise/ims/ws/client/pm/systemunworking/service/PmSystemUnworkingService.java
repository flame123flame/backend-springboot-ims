package th.go.excise.ims.ws.client.pm.systemunworking.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import th.go.excise.ims.ws.client.pm.systemunworking.model.RequestData;
import th.go.excise.ims.ws.client.pm.systemunworking.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class PmSystemUnworkingService {
	
	private String url;
	private RestfulClientService restfulClientService;
	private Gson gson;
	
	@Autowired
	public PmSystemUnworkingService(
			@Value("${ws.excise.endpoint.pm.system-unworking}") String url,
			RestfulClientService restfulClientService,
			Gson gson) {
		this.url = url;
		this.restfulClientService = restfulClientService;
		this.gson = gson;
	}

	public ResponseData execute(RequestData requestData) throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("year", requestData.getYear());
		urlBuilder.addQueryParameter("month", requestData.getMonth());
		
		String respJson = restfulClientService.get(urlBuilder.build().toString());
		ResponseData responseData = gson.fromJson(respJson, ResponseData.class);
		
		return responseData;
	}

}