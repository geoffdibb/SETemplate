package com.qa.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	private ObjectMapper json;

	public JSONUtil() {
		this.json = new ObjectMapper();
	}

	public String getJSONForObject(Object obj) {

		try {
			return json.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {

		try {
			return json.readValue(jsonString, clazz);

		} catch (JsonParseException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

}