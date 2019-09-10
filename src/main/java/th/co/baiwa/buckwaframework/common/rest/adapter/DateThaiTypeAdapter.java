package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.io.IOException;
import java.util.Date;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;

public class DateThaiTypeAdapter extends TypeAdapter<Date> {
	
	private DateThaiTypeAdapter() {
	}
	
	private static class SingletonHolder {
		private static final DateThaiTypeAdapter instance = new DateThaiTypeAdapter();
	}
	
	public static DateThaiTypeAdapter getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	public void write(JsonWriter writer, Date value) throws IOException {
		writer.value(ConvertDateUtils.formatDateToString(value, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
	}

	@Override
	public Date read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull();
			return null;
		}
		return ConvertDateUtils.parseStringToDate(reader.nextString(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
	}

}
