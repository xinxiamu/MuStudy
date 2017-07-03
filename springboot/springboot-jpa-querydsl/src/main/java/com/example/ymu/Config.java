package com.example.ymu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Config {

	@Bean
	public JsonViewHttpMessageConverter mappingJackson2HttpMessageConverter() {
		JsonViewHttpMessageConverter jsonConverter = new JsonViewHttpMessageConverter();
		ObjectMapper objectMapper = new CustomObjectMapper();
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}
}
