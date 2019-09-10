package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;

public class LocalDateJsonDeserializer implements JsonDeserializer<LocalDate> {
	
	private LocalDateJsonDeserializer() {
	}
	
	private static class SingletonHolder {
		private static final LocalDateJsonDeserializer instance = new LocalDateJsonDeserializer();
	}
	
	public static LocalDateJsonDeserializer getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		try {
			return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT, Locale.US));
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
}
