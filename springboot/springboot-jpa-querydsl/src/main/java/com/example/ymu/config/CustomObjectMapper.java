package com.example.ymu.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2966548053833703113L;

	public CustomObjectMapper() {
		super();
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
				jgen.writeString("");
			}
		});
		SimpleModule module = new SimpleModule();
		module.addSerializer(boolean.class, new JsonSerializer<Boolean>() {
			@Override
			public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
				jgen.writeNumber(value ? 1 : 0);
			}
		});
		this.registerModule(module);
	}

}
