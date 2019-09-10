package th.go.excise.ims.ws.client.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import th.co.baiwa.buckwaframework.common.rest.interceptor.LoggingInterceptor;

@Service
public class RestfulClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulClientService.class);
	
	public final OkHttpClient client = new OkHttpClient.Builder()
		//.addInterceptor(new LoggingInterceptor(logger))
		.readTimeout(20, TimeUnit.SECONDS)
		.build();
	
	public static final MediaType MEDIA_TYPE_JSON = MediaType.get(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);

	public String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
		Request request = new Request.Builder()
			.url(url)
			.post(body)
			.build();
		
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public String get(String url) throws IOException {
		Request request = new Request.Builder()
			.url(url)
			.build();
		
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public byte[] getBytes(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			return response.body().bytes();
		}
	}

}
