package com.example.ymu.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ymu.framework.utils.security.AESUtils;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private String[] encryptPropNames = { "username1", "jdbc.password" };
	private String KeyStr = "4F979A45A894F20C0A3286593780C869";

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			for (int i = 0; i < encryptPropNames.length; i++) {
				String value = props.getProperty(encryptPropNames[i]);
				if (value != null) {
					props.setProperty(encryptPropNames[i],AESUtils.jdkAESDecode(KeyStr, value));
				}
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
