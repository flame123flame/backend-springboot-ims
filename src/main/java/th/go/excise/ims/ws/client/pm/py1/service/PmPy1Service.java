package th.go.excise.ims.ws.client.pm.py1.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import th.go.excise.ims.ws.client.pm.py1.model.RequestData;
import th.go.excise.ims.ws.client.pm.py1.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class PmPy1Service {
	
	private String url;
	private RestfulClientService restfulClientService;
	private Gson gson;
	
	@Autowired
	public PmPy1Service(
			@Value("${ws.excise.endpoint.pm.py1}") String url,
			RestfulClientService restfulClientService,
			Gson gson) {
		this.url = url;
		this.restfulClientService = restfulClientService;
		this.gson = gson;
	}

	public ResponseData execute(RequestData requestData) throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("year", requestData.getYear());
		urlBuilder.addQueryParameter("oCode", requestData.getOfficeCode());
		
		String respJson = restfulClientService.get(urlBuilder.build().toString());
		ResponseData responseData = gson.fromJson(respJson, ResponseData.class);
		
		return responseData;
	}
	
}
