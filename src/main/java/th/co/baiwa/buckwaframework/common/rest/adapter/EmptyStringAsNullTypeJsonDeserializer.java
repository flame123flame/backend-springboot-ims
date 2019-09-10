package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class EmptyStringAsNullTypeJsonDeserializer<T> implements JsonDeserializer<T> {

	private EmptyStringAsNullTypeJsonDeserializer() {
	}

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json.isJsonPrimitive()) {
			final JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
			if (jsonPrimitive.isString() && jsonPrimitive.getAsString().isEmpty()) {
				return null;
			}
		}
		return context.deserialize(json, typeOfT);
	}

}
