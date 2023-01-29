package fa.training.config;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateAdapter implements JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		// TODO Auto-generated method stub
		return null;
	}
}