package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;

public class LocalDateTimeJsonSerializer implements JsonSerializer<LocalDateTime> {
	
	private LocalDateTimeJsonSerializer() {
	}
	
	private static class SingletonHolder {
		private static final LocalDateTimeJsonSerializer instance = new LocalDateTimeJsonSerializer();
	}
	
	public static LocalDateTimeJsonSerializer getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATETIME_FORMAT, Locale.US).format(src));
	}
	
}
