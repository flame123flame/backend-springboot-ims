package th.co.baiwa.buckwaframework.common.rest.interceptor;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

public class LoggingInterceptor implements Interceptor {
	
	private final Logger logger;
	
	public LoggingInterceptor(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		// Logging Request
		Request request = chain.request();
		logger.info("--> {} {}", request.method(), request.url());
		
		RequestBody requestBody = request.body();
		if (requestBody == null) {
			logger.info("--> END {}", request.method());
		} else if (bodyHasUnknownEncoding(request.headers())) {
			logger.info("--> END {} (encoded body omitted)", request.method());
		} else {
			Buffer buffer = new Buffer();
			requestBody.writeTo(buffer);

			if (isPlaintext(buffer)) {
				logger.info(buffer.readString(StandardCharsets.UTF_8));
				logger.info("--> END {} ({}-byte body)", request.method(), requestBody.contentLength());
			} else {
				logger.info("--> END {} (binary {}-byte body omitted)", request.method(), requestBody.contentLength());
			}
		}
		
		// Process
		long startNs = System.nanoTime();
		Response response;
		try {
			response = chain.proceed(request);
		} catch (IOException e) {
			logger.info("<-- HTTP FAILED: " + e);
			throw e;
		}
		long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
		
		// Logging Response
		logger.info("<-- {} {} {} ({}ms)", response.code(), (response.message().isEmpty() ? "" : response.message()), response.request().url(), tookMs);
		
		ResponseBody responseBody = response.body();
		if (!HttpHeaders.hasBody(response)) {
			logger.info("<-- END HTTP");
		} else if (bodyHasUnknownEncoding(response.headers())) {
			logger.info("<-- END HTTP (encoded body omitted)");
		} else {
			BufferedSource source = responseBody.source();
			source.request(Long.MAX_VALUE); // Buffer the entire body.
			Buffer buffer = source.getBuffer();
			
			if (!isPlaintext(buffer)) {
				logger.info("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
				return response;
			}
			
//			if (responseBody.contentLength() != 0) {
//				logger.info(buffer.clone().readString(StandardCharsets.UTF_8));
//			}
			
			logger.info("<-- END HTTP (" + buffer.size() + "-byte body)");
		}

		return response;
	}
	
	/**
	 * Returns true if the body in question probably contains human readable
	 * text. Uses a small sample of code points to detect unicode control
	 * characters commonly used in binary file signatures.
	 */
	static boolean isPlaintext(Buffer buffer) {
		try {
			Buffer prefix = new Buffer();
			long byteCount = buffer.size() < 64 ? buffer.size() : 64;
			buffer.copyTo(prefix, 0, byteCount);
			for (int i = 0; i < 16; i++) {
				if (prefix.exhausted()) {
					break;
				}
				int codePoint = prefix.readUtf8CodePoint();
				if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
					return false;
				}
			}
			return true;
		} catch (EOFException e) {
			return false; // Truncated UTF-8 sequence.
		}
	}
	
	private static boolean bodyHasUnknownEncoding(Headers headers) {
		String contentEncoding = headers.get("Content-Encoding");
		return contentEncoding != null
			&& !contentEncoding.equalsIgnoreCase("identity")
			&& !contentEncoding.equalsIgnoreCase("gzip");
	}
	
}