package com.wobiancao.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	public interface PublicView {
	};

	public interface PrivateView extends PublicView {
	};

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public static String objectToJson(Object object) {
		String json = "";
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String objectToJsonWithView(Object object, Class<?> view) {
		String json = "";
		try {
			json = mapper.writerWithView(view).writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static <T> T jsonToObject(String json, Class<T> valueType) {
		T object = null;
		try {
			object = mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}
