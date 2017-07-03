package com.example.ymu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * spring mvc自定义配置
 * @author Administrator
 *
 */
@Configuration
@AutoConfigureAfter(Config.class)
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private JsonViewHttpMessageConverter JsonViewHttpMessageConverter;
	
	/**
	 * 异常统一处理
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		super.configureHandlerExceptionResolvers(exceptionResolvers);
	}
	
	/**
	 * 消息转换
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(JsonViewHttpMessageConverter);
	}
	
	/**
	 * 跨域设置，跨域攻击设置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
}
