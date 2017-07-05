package com.example.ymu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Config {

	/**
	 * 自定义配置加载，方法定义为static的，保证优先加载
	 * 
	 * @return
	 */
	@Bean
	public static EncryptPropertyPlaceholderConfigurer encryptPropertyPlaceholderConfigurer() {
		EncryptPropertyPlaceholderConfigurer epc = new EncryptPropertyPlaceholderConfigurer();
		epc.setLocation(new ClassPathResource("config/encrypted.properties"));
		return epc;
	}

	@Bean
	public JsonViewHttpMessageConverter mappingJackson2HttpMessageConverter() {
		JsonViewHttpMessageConverter jsonConverter = new JsonViewHttpMessageConverter();
		ObjectMapper objectMapper = new CustomObjectMapper();
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

}
