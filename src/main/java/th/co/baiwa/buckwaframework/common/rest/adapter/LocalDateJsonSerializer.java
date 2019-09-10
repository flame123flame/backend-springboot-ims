package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;

public class LocalDateJsonSerializer implements JsonSerializer<LocalDate> {
	
	private LocalDateJsonSerializer() {
	}
	
	private static class SingletonHolder {
		private static final LocalDateJsonSerializer instance = new LocalDateJsonSerializer();
	}
	
	public static LocalDateJsonSerializer getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT, Locale.US).format(src));
	}
	
}
