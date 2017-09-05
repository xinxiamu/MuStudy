package com.terran4j.springboot.web;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableWebMvc
public class HelloWebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(HelloWebMvcConfigurer.class);

	public static final ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

		// 属性为空时（包括 null, 空串，空集合，空对象），不参与序列化。
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		// Date 对象在序列化时，格式为 yyyy年MM月dd日 HH时mm分ss秒 。
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));

		objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

		// json串以良好的格式输出。
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// 当属性为空或有问题时不参与序列化。
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		// 未知的属性不参与反序列化。
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		if (log.isInfoEnabled()) {
			log.info("create objectMapper done.");
		}
		return objectMapper;
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		ObjectMapper objectMapper = createObjectMapper();
		MappingJackson2HttpMessageConverter convertor = new MappingJackson2HttpMessageConverter(objectMapper);
		converters.add(0, convertor);
	}

}